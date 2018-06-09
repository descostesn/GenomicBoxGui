package GenomicBox.graphicClasses.actionClasses;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


// Class enabling to select one file and print this file in a label. It is used for GFF annotations, output folder

public class FileSelectionLabelSet implements ActionListener{
	
	private Component classObject;
	private String errorMessage;
	private String typeError;
	private JLabel labelToChange;
	private JTextField fieldToChange;
	private JFrame currentFrame;
	private String extensionFormat;
	private Boolean isDirectory = false;
	
	//This constructor is used for selecting directories
	public FileSelectionLabelSet(Component classObject,  
			JLabel labelToChange, JFrame currentFrame){
		
		this.classObject = classObject;
		this.errorMessage = "";
		this.typeError = "";
		this.labelToChange = labelToChange;
		this.fieldToChange = null;
		this.currentFrame = currentFrame;
		this.extensionFormat = "";
		this.isDirectory = true;
	}
	
	//This constructor is used to select a file and update a field. The file extension is also checked
	public FileSelectionLabelSet(Component classObject, String errorMessage, 
			String typeError, JLabel labelToChange, JTextField fieldToChange,
			JFrame currentFrame, String extensionFormat){
		
		this.classObject = classObject;
		this.errorMessage = errorMessage;
		this.typeError = typeError;
		this.labelToChange = labelToChange;
		this.fieldToChange = fieldToChange;
		this.currentFrame = currentFrame;
		this.extensionFormat = extensionFormat;
	}
	
	
	private void changeLabelField(File result){
		
		String fileName;
		
		if(isDirectory){
			fileName = result.getAbsolutePath();
		}
		else
			fileName = result.getName();
		
		labelToChange.setText(fileName);
		
		if(fieldToChange != null){
			String fileNameNoExtension = fileName.substring(0, fileName.lastIndexOf("."));
			fieldToChange.setText(fileNameNoExtension);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		
		final JFileChooser fc = new JFileChooser();
		int returnVal;
		
		if(isDirectory){
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
		}
		
		returnVal = fc.showOpenDialog(classObject);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
			File result = fc.getSelectedFile();
			String filePath = result.getAbsolutePath();
			
			if(!extensionFormat.equals("")){
				String extension = filePath.substring(filePath.lastIndexOf(".")+1);
				
				if(!extension.equals(extensionFormat))
					JOptionPane.showMessageDialog(currentFrame, errorMessage, typeError, JOptionPane.ERROR_MESSAGE);
				else{
					changeLabelField(result);
				}
			}else{
				changeLabelField(result);
			}
		}
	}
}
