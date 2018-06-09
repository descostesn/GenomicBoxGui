package GenomicBox.toolsTabClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import GenomicBox.graphicClasses.formatClasses.Police2;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.graphicClasses.paneClasses.EmptyObjectArray;




public class GridJobs extends JPanel {
	
	private Font police2 = new Police2();
	
	private JButton finishedButton = new JButton();
	private JButton errorButton = new JButton();
	private JButton runningButton = new JButton();
	private JButton queuedButton = new JButton();
	private JLabel finishedLabel = new JLabel("Finished");
	private JLabel errorLabel = new JLabel("Error");
	private JLabel runningLabel = new JLabel("Running");
	private JLabel queuedLabel = new JLabel("Queued");
	private JPanel legendsPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private Object rawDataQStat[][] = new EmptyObjectArray(200, 7).getRowData();
	private String[] columnNamesQStat = {"JobID", "Name", "State", "Submit/Start at", "Node", "Nb CPU", "Task Number"};
	private CustomTableModel qStatTableModel = new CustomTableModel(rawDataQStat, columnNamesQStat);
	private JTable qStatTable = new JTable(qStatTableModel);
	private JScrollPane scrollPaneQStat = new JScrollPane(qStatTable);
	
	private JButton deleteButton = new JButton("Delete");
	private JButton refreshButton = new JButton("Refresh");
	private JPanel deleteRefreshPane = new JPanel();
	
	
	public GridJobs(int preProcWidth, int preProcHeight){
		
		finishedButton.setEnabled(false);
		errorButton.setEnabled(false);
		runningButton.setEnabled(false);
		queuedButton.setEnabled(false);
		finishedButton.setBackground(Color.green);
		errorButton.setBackground(Color.red);
		runningButton.setBackground(Color.orange);
		queuedButton.setBackground(Color.lightGray);
		
		finishedLabel.setBackground(Color.white);
		errorLabel.setBackground(Color.white);
		runningLabel.setBackground(Color.white);
		queuedLabel.setBackground(Color.white);
		finishedLabel.setFont(police2);
		errorLabel.setFont(police2);
		runningLabel.setFont(police2);
		queuedLabel.setFont(police2);
		
		legendsPane.setBackground(Color.white);
		legendsPane.add(finishedButton);
		legendsPane.add(finishedLabel);
		legendsPane.add(errorButton);
		legendsPane.add(errorLabel);
		legendsPane.add(runningButton);
		legendsPane.add(runningLabel);
		legendsPane.add(queuedButton);
		legendsPane.add(queuedLabel);
		
		deleteRefreshPane.setBackground(Color.white);
		deleteRefreshPane.add(deleteButton);
		deleteRefreshPane.add(refreshButton);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(legendsPane);
		this.add(scrollPaneQStat);
		this.add(deleteRefreshPane);
		this.setVisible(true);
	}

}
