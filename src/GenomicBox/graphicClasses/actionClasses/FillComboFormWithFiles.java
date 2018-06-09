package GenomicBox.graphicClasses.actionClasses;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class FillComboFormWithFiles implements ActionListener {
	
	private Component className;
	private String format;
	private JFrame frame;
	private String formatErrorMessage;
	private String columnNameToFill;
	private JComboBox<String> comboToFill;
	private JTable table;
	
	public FillComboFormWithFiles(Component className, String format, JFrame frame, 
			String formatErrorMessage, String columnNameToFill, 
			JComboBox<String> comboToFill, JTable table){
		
		this.className = className;
		this.format = format;
		this.frame = frame;
		this.formatErrorMessage = formatErrorMessage;
		this.columnNameToFill = columnNameToFill;
		this.comboToFill = comboToFill;
		this.table = table;
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
					
					
					comboToFill = new JComboBox<String>(filesNames);
					table.getColumn(columnNameToFill).setCellEditor(new DefaultCellEditor(comboToFill));
				}
			}
		}
	}
}