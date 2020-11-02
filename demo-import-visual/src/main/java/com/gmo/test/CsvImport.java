package com.gmo.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Class responsible for create application window 
 * @author Gledson Oliveira
 * 
 */
public class CsvImport {
	private JFrame frame;
	private JTextField txtServer;
	private JTextField txtPort;
	private JTextField txtFilePath;
	private JFileChooser fileChooser;
	
	public CsvImport() {
		initialize();
	}
	
	//create frame
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Import CSV File into MongoDB");
		
		JLabel lblInfo = new JLabel("MongoDB Connection Properties");
		lblInfo.setBounds(100, 5, 250, 30);
		frame.getContentPane().add(lblInfo);
		
		JLabel lblServer = new JLabel("Server: ");
		lblServer.setBounds(40, 50, 85, 30);
		frame.getContentPane().add(lblServer);
		
		txtServer = new JTextField();
		txtServer.setBounds(100, 50, 200, 30);
		frame.getContentPane().add(txtServer);
		txtServer.setColumns(10);
		txtServer.setText("localhost");

		JLabel lblPort = new JLabel("Port: ");
		lblPort.setBounds(40, 100, 85, 30);
		frame.getContentPane().add(lblPort);
		
		txtPort = new JTextField();
		txtPort.setBounds(100, 100, 85, 30);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
		txtPort.setText("27017");

		JLabel lblInfo2 = new JLabel("File Information");
		lblInfo2.setBounds(100, 160, 250, 30);
		frame.getContentPane().add(lblInfo2);
		
		JLabel lblFile = new JLabel("File: ");
		lblFile.setBounds(40, 200, 85, 30);
		frame.getContentPane().add(lblFile);
		
		txtFilePath = new JTextField();
		txtFilePath.setBounds(100, 200, 200, 30);
		frame.getContentPane().add(txtFilePath);
		
		txtFilePath.setColumns(40);
		txtFilePath.setText("");
		
		fileChooser = new JFileChooser();
		
		JButton btnFile = new JButton("Select file...");
		btnFile.setBounds(305, 200, 120, 30);
		
		btnFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
		        int returnVal = fileChooser.showOpenDialog(frame);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File file = fileChooser.getSelectedFile();
		            txtFilePath.setText(file.getAbsolutePath());		            
		        } 
			}
		});
				
		JButton btnImport = new JButton("Import CSV!");
		btnImport.setBounds(100, 300, 120, 30);
		btnImport.addActionListener( new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
							
				if (txtFilePath.getText().trim().length() == 0 ) {
					JOptionPane.showMessageDialog(frame, "Choose file to import.");
				}else {

				    // test to see if a file exists
				    File file = new File(txtFilePath.getText());
				    
				    if (!file.exists()) {
				    	JOptionPane.showMessageDialog(frame, "Invalid path/file!");
				    	return;
				    }
				    
				    Pattern fileExtnPtrn = Pattern.compile("([^\\s]+(\\.(?i)(csv))$)");
				    
				    Matcher mtch = fileExtnPtrn.matcher(txtFilePath.getText());
			        if(!mtch.matches()){
			        	JOptionPane.showMessageDialog(frame, "Invalid path/file!");
				    	return;
			        }
			        			        
					Mongo mongo = new Mongo();
					
					String valid = mongo.validateMongoConnection(txtServer.getText(), txtPort.getText());
					
					if ( valid != null) {
						JOptionPane.showMessageDialog(frame, valid);
					} else {
						String process = mongo.importFile(txtServer.getText(), txtPort.getText(), txtFilePath.getText());
						JOptionPane.showMessageDialog(frame, process);
					}
				}
			}
		});
				
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(250, 300, 120, 30);
		btnClose.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		frame.getContentPane().add(btnImport);
		frame.getContentPane().add(btnClose);
		frame.getContentPane().add(btnFile);
		
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					CsvImport csvImport = new CsvImport();
					csvImport.frame.setVisible(true);
				} catch (Exception e) {					
					e.printStackTrace();
				}
				
			}
		});
	}
}
