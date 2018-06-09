package GenomicBox.graphicClasses.actionClasses;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.toolsTabClasses.DEAnalysis;

//This class is associated with the LabelAndSelectButtonPane. It helps filling two columns of a given JTable.
// The first column contains the path to the file and the second is the name of the file (without extension)


public class FillFormWithFiles implements ActionListener {

	private Component className;
	private String format;
	private JFrame frame;
	private String formatErrorMessage;
	private CustomTableModel model;
	private CustomTableModel secondModel; //Use if the event was triggered from DEseq2 to fill the Sample Name column of the second table
	
	public FillFormWithFiles(Component className, String format, JFrame frame,
			String formatErrorMessage, CustomTableModel model){
		
		this.className = className;
		this.format = format;
		this.frame = frame;
		this.formatErrorMessage = formatErrorMessage;
		this.model = model;
		this.secondModel = null;
	}
	
	public FillFormWithFiles(Component className, String format, JFrame frame,
			String formatErrorMessage, CustomTableModel model, CustomTableModel secondModel){
		
		this.className = className;
		this.format = format;
		this.frame = frame;
		this.formatErrorMessage = formatErrorMessage;
		this.model = model;
		this.secondModel = secondModel;
	}
	
	
	public void actionPerformed(ActionEvent e){
		
		final JFileChooser fc = new JFileChooser();
		int returnVal;
		
		fc.setMultiSelectionEnabled(true);
		returnVal = fc.showOpenDialog(className);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
			File[] results = fc.getSelectedFiles();
			String[] filesPathes = new String[results.length];
			String[] filesNames = new String[results.length];
			int nbValidFiles = 0;
			
			//Verify proper extension
			for (int i = 0; i < results.length; i++) {
				
				String filePath = results[i].getAbsolutePath();
				String fileNameWithExtension = results[i].getName();
				String fileName = fileNameWithExtension.substring(0, fileNameWithExtension.lastIndexOf("."));
				int indexPoint = filePath.lastIndexOf(".");
				String extension = filePath.substring(indexPoint+1);
				
				if(!extension.equals(format)){
					JOptionPane.showMessageDialog(frame, formatErrorMessage, "Format error", JOptionPane.ERROR_MESSAGE);
					break;
				}else{
					filesPathes[i] = filePath;
					filesNames[i] = fileName;
					nbValidFiles++;
				}
			}
			
			//Fill the table if all files provided have the correct format
			if(nbValidFiles == results.length){
				
				final int nbRow = model.getRowCount();
				
				if(nbValidFiles > nbRow){
					JOptionPane.showMessageDialog(frame, "A maximum of " + nbRow + " files can be given as input", "Input number error", JOptionPane.ERROR_MESSAGE);
				}else{
					for(int i = 0; i < results.length; i++){
						
					    	model.setValueAt(filesPathes[i], i, 0);
					    	model.setValueAt(filesNames[i], i, 1);
					    	
					    	if(secondModel != null){
								secondModel.setValueAt(filesNames[i], i, 0);
							}
					}
				}
			}
		}
	}
	
	public void clearTable(){
		
		for (int i = 0; i < model.getRowCount(); i++)
			for (int j = 0; j < model.getColumnCount(); j++)
				model.setValueAt("", i, j);
	}
	
	public void setFormat(String format){
		this.format = format;
	}
	
	public void setErrorMessage(String formatErrorMessage){
		this.formatErrorMessage = formatErrorMessage;
	}
	
	public void setModel(CustomTableModel model){
		this.model = model;
	}
}
