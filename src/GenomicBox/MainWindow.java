package GenomicBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import GenomicBox.toolsTabClasses.ClusterProfiler;
import GenomicBox.toolsTabClasses.DEAnalysis;
import GenomicBox.toolsTabClasses.DataPreprocessing;
import GenomicBox.toolsTabClasses.ExtractSequences;
import GenomicBox.toolsTabClasses.FormatConversion;
import GenomicBox.toolsTabClasses.GagePane;
import GenomicBox.toolsTabClasses.GridJobs;
import GenomicBox.toolsTabClasses.Heatmaps;
import GenomicBox.toolsTabClasses.MetaProfiling;
import GenomicBox.toolsTabClasses.PeakDetection;
import GenomicBox.toolsTabClasses.UCSCViz;



public class MainWindow extends JFrame{

	private JTabbedPane container = new JTabbedPane();
	private JMenuBar menuBar = new JMenuBar();
	private JMenu openClose = new JMenu("File");
	private JMenu tools = new JMenu("Tools");
	// Menu in tools
	private JMenu fileOperations = new JMenu("File Operations");
	private JMenu goOptions = new JMenu("Gene Ontologies");
	//Elements of openClose
	private JMenuItem openClose_new = new JMenuItem("New");
	private JMenuItem openClose_exit = new JMenuItem("Exit");
	//Elements of tools
	private JMenuItem tools_dataPreProcessing = new JMenuItem("Data Pre-processing");
	private JMenuItem tools_DEExpression = new JMenuItem("Differential expression");
	private JMenuItem tools_metaProfiling = new JMenuItem("Meta-profiling");
	private JMenuItem tools_peakDetection = new JMenuItem("Peak detection");
	private JMenuItem tools_heatmaps = new JMenuItem("Heatmaps");
	private JMenuItem tools_gridJobs = new JMenuItem("Grid jobs");
	private JMenuItem tools_genomeBrowser = new JMenuItem("UCSC visualization");
	private JMenuItem vennDiagram = new JMenuItem("Venn diagrams");
	//Menus in fileOperations
	private JMenu fileOperations_fastaOperation = new JMenu("Fasta Operations");
	private JMenuItem fileOperations_formatConversion = new JMenuItem("Format Conversion");
	
	//Items in goOptions
	private JMenuItem goOptions_chipEnrich = new JMenuItem("ChIP Enrich");
	private JMenuItem goOptions_ClusterProfiler = new JMenuItem("Cluster Profiler");
	private JMenuItem goOptions_Gage = new JMenuItem("Gage"); 
	//Items fastaOperations
	private JMenuItem fastaOperation_extractSequence = new JMenuItem("Extract Sequence");
	
	
	
	public MainWindow(){
		
		this.setTitle("Genomic Box toolkit");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setMinimumSize(new Dimension(400,400));
		this.setResizable(true);
		
		fileOperations_formatConversion.addActionListener(new openTab("Format Conversion"));
		
		this.fileOperations_fastaOperation.add(fastaOperation_extractSequence);
		
		this.fileOperations.add(fileOperations_fastaOperation);
		this.fileOperations.add(fileOperations_formatConversion);
		
		goOptions_ClusterProfiler.addActionListener(new openTab("Cluster Profiler"));
		goOptions_Gage.addActionListener(new openTab("Gage"));
		this.goOptions.add(goOptions_chipEnrich);
		this.goOptions.add(goOptions_ClusterProfiler);
		this.goOptions.add(goOptions_Gage);
		
		fastaOperation_extractSequence.addActionListener(new openTab("Extract Sequence"));
		tools_dataPreProcessing.addActionListener(new openTab("Data pre-processing"));
		tools_DEExpression.addActionListener(new openTab("DE Analysis"));
		tools_heatmaps.addActionListener(new openTab("Heatmaps"));
		tools_gridJobs.addActionListener(new openTab("Grid jobs"));
		tools_genomeBrowser.addActionListener(new openTab("UCSC visualization"));
		tools_metaProfiling.addActionListener(new openTab("Meta-profiling"));
		tools_peakDetection.addActionListener(new openTab("Peak detection"));
		this.tools.add(fileOperations);
		this.tools.add(goOptions);
		this.tools.add(tools_dataPreProcessing);
		this.tools.add(tools_DEExpression);
		this.tools.add(tools_genomeBrowser);
		this.tools.add(tools_gridJobs);
		this.tools.add(tools_heatmaps);
		this.tools.add(tools_metaProfiling);
		this.tools.add(tools_peakDetection);
		
		this.openClose.add(openClose_new);
		openClose_exit.addActionListener(new actionOnExit());
		this.openClose.add(openClose_exit);
		
		this.menuBar.add(openClose);
		this.menuBar.add(tools);
		this.setJMenuBar(menuBar);
		
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	
	class actionOnExit implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	
	class openTab implements ActionListener{
		
		private String tabName;
		
		public openTab(String tabName){
			this.tabName = tabName;
		}
		
		public String getTabName(){
			return tabName;
		}
		
		public void actionPerformed(ActionEvent e){
			
			switch(getTabName()){
			
				case "Data pre-processing": final DataPreprocessing dataPrecPanel = new DataPreprocessing(getWidth()/2, getHeight()/2);
											container.addTab("Data pre-processing", dataPrecPanel);
											tools_dataPreProcessing.setOpaque(true);
											//tools_dataPreProcessing.setBackground(Color.gray);
											tools_dataPreProcessing.setEnabled(false);
											break;
				case "Meta-profiling": 		final MetaProfiling metaProfPanel = new MetaProfiling(getWidth()/2, getHeight()/2);
											container.addTab("Meta-profiling", metaProfPanel);
											tools_metaProfiling.setOpaque(true);
											//tools_metaProfiling.setBackground(Color.gray);
											tools_metaProfiling.setEnabled(false);
											break;
				case "DE Analysis": 		final DEAnalysis DEPanel = new DEAnalysis(getWidth()/2, getHeight()/2);
											container.addTab("DE Analysis", DEPanel);
											tools_DEExpression.setOpaque(true);
											//tools_DEExpression.setBackground(Color.gray);
											tools_DEExpression.setEnabled(false);
											break;
				case "Heatmaps": 			final Heatmaps heatmapsPanel = new Heatmaps(getWidth()/2, getHeight()/2);
											container.addTab("Heatmaps", heatmapsPanel);
											tools_heatmaps.setOpaque(true);
											//tools_heatmaps.setBackground(Color.gray);
											tools_heatmaps.setEnabled(false);
											break;
				case "Format Conversion":	final FormatConversion formatConversionPanel = new FormatConversion(getWidth()/2, getHeight()/2);
											container.addTab("Format Conversion", formatConversionPanel);
											fileOperations_formatConversion.setOpaque(true);
											//fileOperations_formatConversion.setBackground(Color.gray);
											fileOperations_formatConversion.setEnabled(false);
											break;
				case "Peak detection":		final PeakDetection peakDetectionPanel = new PeakDetection(getWidth()/2, getHeight()/2);
											container.addTab("Peak detection", peakDetectionPanel);
											tools_peakDetection.setOpaque(true);
											//tools_peakDetection.setBackground(Color.gray);
											tools_peakDetection.setEnabled(false);
											break;
				case "Extract Sequence":	final ExtractSequences extractSequencesPanel = new ExtractSequences(getWidth()/2, getHeight()/2);
											container.addTab("Extract Sequence", extractSequencesPanel);
											fastaOperation_extractSequence.setOpaque(true);
											//fastaOperation_extractSequence.setBackground(Color.gray);
											fastaOperation_extractSequence.setEnabled(false);
											break;
				case "Cluster Profiler":	final ClusterProfiler clusterProfilerPanel = new ClusterProfiler(getWidth()/2, getHeight()/2);
											container.addTab("Cluster Profiler", clusterProfilerPanel);
											goOptions_ClusterProfiler.setOpaque(true);
											//goOptions_ClusterProfiler.setBackground(Color.gray);
											goOptions_ClusterProfiler.setEnabled(false);
											break;
				case "Gage":				final GagePane gagePanel = new GagePane(getWidth()/2, getHeight()/2);
											container.addTab("Gage", gagePanel);
											goOptions_Gage.setOpaque(true);
											//goOptions_Gage.setBackground(Color.gray);
											goOptions_Gage.setEnabled(false);
											break;
				case "Grid jobs":			final GridJobs gridJobsPanel = new GridJobs(getWidth()/2, getHeight()/2);
											container.addTab("Grid jobs", gridJobsPanel);
											tools_gridJobs.setOpaque(true);
											//tools_gridJobs.setBackground(Color.gray);
											tools_gridJobs.setEnabled(false);
											break;
				case "UCSC visualization":	final UCSCViz ucscViz = new UCSCViz(getWidth()/2, getHeight()/2);
											container.addTab("UCSC visualization", ucscViz);
											tools_genomeBrowser.setOpaque(true);
											tools_genomeBrowser.setEnabled(false);
											break;
			}
			
			//Make the tab closable
			int index = container.indexOfTab(getTabName());
			JPanel pnlTab = new JPanel(new GridBagLayout());
			pnlTab.setOpaque(false);
			JLabel lblTitle = new JLabel(getTabName());
			JButton btnClose = new JButton("  x");
			btnClose.setOpaque(false);
			btnClose.setFocusPainted(false);
			btnClose.setBorderPainted(false);
			btnClose.setContentAreaFilled(false);
			btnClose.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;
			
			pnlTab.add(lblTitle, gbc);
			gbc.gridx++;
			gbc.weightx = 0;
			pnlTab.add(btnClose, gbc);
			
			container.setTabComponentAt(index, pnlTab);
			btnClose.addActionListener(new MyCloseActionHandler(getTabName()));
		}
	}
	
	
	class MyCloseActionHandler implements ActionListener{
		
		private String tabName;
		
		public MyCloseActionHandler(String tabName){
			this.tabName = tabName;
		}
		
		public String getTabName(){
			return tabName;
		}
		
		public void actionPerformed(ActionEvent evt){
			int index = container.indexOfTab(getTabName());
			
			if(index >= 0){
				container.removeTabAt(index);
			}
			
			switch(getTabName()){
			
				case "Data pre-processing": tools_dataPreProcessing.setOpaque(false);
											//tools_dataPreProcessing.setBackground(null);
											tools_dataPreProcessing.setEnabled(true);
											break;
				case "Meta-profiling":		tools_metaProfiling.setOpaque(false);
											//tools_metaProfiling.setBackground(null);
											tools_metaProfiling.setEnabled(true);
											break;
				case "DE Analysis": 		tools_DEExpression.setOpaque(false);
											//tools_DEExpression.setBackground(null);
											tools_DEExpression.setEnabled(true);
											break;
				case "Heatmaps": 			tools_heatmaps.setOpaque(false);
								 			//tools_heatmaps.setBackground(null);
								 			tools_heatmaps.setEnabled(true);
								 			break;
				case "Format Conversion":	fileOperations_formatConversion.setOpaque(false);
											//fileOperations_formatConversion.setBackground(null);
											fileOperations_formatConversion.setEnabled(true);
											break;
				case "Peak detection": 		tools_peakDetection.setOpaque(false);
											//tools_peakDetection.setBackground(null);
											tools_peakDetection.setEnabled(true);
											break;
				case "Extract Sequence":	fastaOperation_extractSequence.setOpaque(false);
											//fastaOperation_extractSequence.setBackground(null);
											fastaOperation_extractSequence.setEnabled(true);
											break;
				case "Cluster Profiler":	goOptions_ClusterProfiler.setOpaque(false);
											//goOptions_ClusterProfiler.setBackground(null);
											goOptions_ClusterProfiler.setEnabled(true);
											break;
				case "Gage":				goOptions_Gage.setOpaque(false);
											//goOptions_Gage.setBackground(null);
											goOptions_Gage.setEnabled(true);
											break;
				case "Grid jobs":			tools_gridJobs.setOpaque(false);
											//tools_gridJobs.setBackground(null);
											tools_gridJobs.setEnabled(true);
											break;
				case "UCSC visualization":	tools_genomeBrowser.setOpaque(false);
											tools_genomeBrowser.setEnabled(true);
			}
		}
	}
}

