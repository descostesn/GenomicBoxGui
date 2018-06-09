package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.paneClasses.JRadioButtonAndShowPanelPane;
import GenomicBox.graphicClasses.paneClasses.EndTypePane;
import GenomicBox.graphicClasses.paneClasses.OrganismGenomePane;
import GenomicBox.graphicClasses.paneClasses.SubmitPane;
import GenomicBox.toolsTabClasses.MetaProfiling.SubmitProfiles;

//class defining the data pre-processing interface which can be selected in the tools menu

public class DataPreprocessing extends JPanel{
	
	private JFrame frame;
	private Font police = new Police();
	private Font police2 = new Police2();
	private JLabel sequenceFolderPath = new JLabel("");
	private JLabel sequenceFolderLabel = new JLabel("Fastq folder:");
	private JButton sequenceFolderButton = new JButton("Select");
	private JPanel selectFolderPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel nameLabel = new JLabel("Analysis name:");
	private JLabel nameField = new JLabel("");
	private JPanel analysisNamePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private OrganismGenomePane organismGenomePane = new OrganismGenomePane();
	private JRadioButtonAndShowPanelPane experimentTypePane;
	private JPanel alignerOptionsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel fragmentSizePane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JTextField fragmentSize = new JTextField();
	private JLabel fragmentSizeName = new JLabel("Fragment Size");
	private JLabel alignerOptionsLabel = new JLabel("Alignment tools:");
	private JCheckBox bowtie = new JCheckBox("Bowtie");
	private JCheckBox bowtie2 = new JCheckBox("Bowtie2");
	private JCheckBox Star = new JCheckBox("STAR");
	private JCheckBox tophat = new JCheckBox("Tophat");
	private JCheckBox tophat2 = new JCheckBox("Tophat2");
	private JLabel outputOptionsLabel = new JLabel("Output Options:");
	private JCheckBox wigFixed = new JCheckBox("Wig Fixed");
	private JCheckBox wigVariable = new JCheckBox("Wig Variable");
	private JCheckBox spikedIn = new JCheckBox("Spiked-in");
	private JPanel outputOptions = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private EndTypePane singlePairedPane = new EndTypePane();
	private JLabel maxNbCPULabel = new JLabel("Max nb CPU");
	private JComboBox maxNbCPU = new JComboBox();
	private int maxCPU = 20;
	private JPanel cpuNbPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel paramLabel = new JLabel("");
	private JLabel writingFileLabel = new JLabel("");
	private JLabel gridCommand = new JLabel("");
	private SubmitPane submitPane = new SubmitPane();
	private JPanel paramPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel fileWritenPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel gridPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	
	
	public DataPreprocessing(int preProcWidth, int preProcHeight){
		
		sequenceFolderPath.setFont(police);
		sequenceFolderLabel.setFont(police2);
		sequenceFolderPath.setPreferredSize(new Dimension(preProcWidth, preProcHeight/20));
		sequenceFolderPath.setBorder(BorderFactory.createLineBorder(Color.black));
		sequenceFolderPath.setBackground(Color.white);
		selectFolderPane.setBackground(Color.white);
		sequenceFolderButton.setFont(police2);
		sequenceFolderButton.addActionListener(new SelectFolderListener());
		selectFolderPane.add(sequenceFolderLabel);
		selectFolderPane.add(sequenceFolderPath);
		selectFolderPane.add(sequenceFolderButton);
		
		analysisNamePane.setBackground(Color.white);
		nameLabel.setFont(police2);
		nameField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		nameField.setBackground(Color.white);
		nameField.setFont(police);
		nameField.setBorder(BorderFactory.createLineBorder(Color.black));
		analysisNamePane.add(nameLabel);
		analysisNamePane.add(nameField);
		
		// Creating a field to enter the fragment size: The JPanel is declared at the top to modify its visibility
		fragmentSizePane.setBackground(Color.white);
		fragmentSize.setFont(police);
		fragmentSize.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		fragmentSize.setBackground(Color.white);
		fragmentSizeName.setFont(police2);
		fragmentSizeName.setBackground(Color.white);
		fragmentSizePane.add(fragmentSizeName);
		fragmentSizePane.add(fragmentSize);
		fragmentSizePane.setVisible(false);
				
		//Creating a two choices panel to say if experiments are ChIP-seq or RNA-seq
		experimentTypePane = new JRadioButtonAndShowPanelPane("Chip-seq", "RNA-seq", fragmentSizePane);
		
		//Creating panel for aligner options
		alignerOptionsPane.setBackground(Color.white);
		alignerOptionsLabel.setBackground(Color.white);
		alignerOptionsLabel.setFont(police2);
		bowtie.setFont(police);
		bowtie.setBackground(Color.white);
		bowtie2.setFont(police);
		bowtie2.setBackground(Color.white);
		Star.setFont(police);
		Star.setBackground(Color.white);
		tophat.setFont(police);
		tophat.setBackground(Color.white);
		tophat2.setFont(police);
		tophat2.setBackground(Color.white);
		alignerOptionsPane.add(alignerOptionsLabel);
		alignerOptionsPane.add(bowtie);
		alignerOptionsPane.add(bowtie2);
		alignerOptionsPane.add(Star);
		alignerOptionsPane.add(tophat);
		alignerOptionsPane.add(tophat2);
		
		//Creating panel for output options
		outputOptions.setBackground(Color.white);
		outputOptionsLabel.setFont(police2);
		wigFixed.setFont(police);
		wigVariable.setFont(police);
		spikedIn.setFont(police);
		outputOptionsLabel.setBackground(Color.white);
		wigFixed.setBackground(Color.white);
		wigVariable.setBackground(Color.white);
		spikedIn.setBackground(Color.white);
		wigFixed.setSelected(true);
		outputOptions.add(outputOptionsLabel);
		outputOptions.add(wigFixed);
		outputOptions.add(wigVariable);
		outputOptions.add(spikedIn);
			
		//Panel for choosing nb of CPU
		cpuNbPane.setBackground(Color.white);
		maxNbCPULabel.setFont(police2);
		maxNbCPULabel.setBackground(Color.white);
		for (int i = 1; i <= maxCPU; i++){
			maxNbCPU.addItem(i);
		}
		cpuNbPane.add(maxNbCPULabel);
		cpuNbPane.add(maxNbCPU);
			
		//Panel for the submit button
		submitPane.getButton().addActionListener(new SubmitListener());
			
		//Pane to print the parameters
		paramPane.setBackground(Color.white);
		paramLabel.setBackground(Color.white);
		paramLabel.setFont(police2);
		paramPane.add(paramLabel);
			
		//Pane indicating the file written
		fileWritenPane.setBackground(Color.white);
		writingFileLabel.setBackground(Color.white);
		writingFileLabel.setFont(police2);
		fileWritenPane.add(writingFileLabel);
			
		//Pane to indicate the grid command
		gridPane.setBackground(Color.white);
		gridCommand.setBackground(Color.white);
		gridCommand.setFont(police2);
		gridPane.add(gridCommand);
			
		//Placing all JPanels on main container
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(selectFolderPane);
		this.add(analysisNamePane);
		this.add(experimentTypePane);
		this.add(organismGenomePane);
		this.add(singlePairedPane);
		this.add(fragmentSizePane);
		this.add(alignerOptionsPane);
		this.add(outputOptions);
		this.add(cpuNbPane);
		this.add(submitPane);
		this.add(paramPane);
		this.add(fileWritenPane);
		this.add(gridPane);
		this.setVisible(true);
	}
	
	
	//Define class to select the input folder
	class SelectFolderListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e){
				
			final JFileChooser fc = new JFileChooser();
			int returnVal;
				
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setAcceptAllFileFilterUsed(false);
			
			returnVal = fc.showOpenDialog(DataPreprocessing.this);
			
			if(returnVal == JFileChooser.APPROVE_OPTION){
				File result = fc.getSelectedFile();
					
				if(!result.isDirectory())
					result = result.getParentFile();
					
				String folderPath = result.getAbsolutePath();
				sequenceFolderPath.setText(folderPath);
					
				String[] parts = folderPath.split("/");
				nameField.setText(parts[parts.length-1]);
			}
		}
	}


	class SubmitListener implements ActionListener{
			
		public void actionPerformed(ActionEvent e){
				
			//Checking that all parameters have been defined
			if(sequenceFolderPath.getText() == "")
				JOptionPane.showMessageDialog(frame, "Select a folder containing fastq files", "Missing parameter", JOptionPane.ERROR_MESSAGE);
			else if(!experimentTypePane.getChoice1Button().isSelected() && !experimentTypePane.getChoice2Button().isSelected())
				JOptionPane.showMessageDialog(frame, "Select chip-seq or rna-seq option", "Missing parameter", JOptionPane.ERROR_MESSAGE);
			else if(!singlePairedPane.getSingleButton().isSelected() && !singlePairedPane.getPairedButton().isSelected())
				JOptionPane.showMessageDialog(frame, "Select single-ended or paired-ended option", "Missing parameter", JOptionPane.ERROR_MESSAGE);
			else if(!wigFixed.isSelected() && !wigVariable.isSelected())
				JOptionPane.showMessageDialog(frame, "Select at least Wig Fixed or Wig variable", "Missing parameter", JOptionPane.ERROR_MESSAGE);
			else if(experimentTypePane.getChoice1Button().isSelected() && fragmentSize.getText().equals(""))
				JOptionPane.showMessageDialog(frame, "Enter fragment size", "Missing parameter", JOptionPane.ERROR_MESSAGE);
			else{
				
				//*** TO DO ***

			}
		}
	}	
}
