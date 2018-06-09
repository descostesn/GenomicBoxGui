package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GenomicBox.graphicClasses.formatClasses.Police;
import GenomicBox.graphicClasses.formatClasses.Police2;

public class UpstreamDownStreamLocationPane extends JPanel {
	
	private JLabel upstreambpLabel = new JLabel("Upstream bp:");
	private JTextField upstreamField = new JTextField("2000");
	private JLabel downstreambpLabel = new JLabel("Downstream bp:");
	private JTextField downstreamField = new JTextField("2000");
	private JLabel locationLabel = new JLabel("Location:");
	private String[] locationValue = {"start", "midpoint", "end", "composite"};
	private JComboBox<String> locationCombo = new JComboBox<String>(locationValue);
	
	
	public UpstreamDownStreamLocationPane(int preProcWidth, int preProcHeight){
		
		Font police = new Police();
		Font police2 = new Police2();
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		
		upstreambpLabel.setFont(police2);
		upstreamField.setFont(police);
		upstreamField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		upstreamField.setBorder(BorderFactory.createLineBorder(Color.black));
		upstreamField.setBackground(Color.white);
		downstreambpLabel.setFont(police2);
		downstreamField.setFont(police);
		downstreamField.setPreferredSize(new Dimension(preProcWidth/4, preProcHeight/20));
		downstreamField.setBorder(BorderFactory.createLineBorder(Color.black));
		downstreamField.setBackground(Color.white);
		locationLabel.setFont(police2);
		
		this.add(upstreambpLabel);
		this.add(upstreamField);
		this.add(downstreambpLabel);
		this.add(downstreamField);
		this.add(locationLabel);
		this.add(locationCombo);
	}

}
