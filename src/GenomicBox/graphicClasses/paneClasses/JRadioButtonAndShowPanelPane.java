package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import GenomicBox.graphicClasses.formatClasses.Police2;



//This panel contains two or three JRadioButton which are grouped. If the first choice is selected, it makes one panel to appear in the interface

public class JRadioButtonAndShowPanelPane extends JPanel {

	private Font police2 = new Police2();
	private JRadioButton choice1Button;
	private JRadioButton choice2Button;
	private JRadioButton choice3Button;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panelToShow;
	
	public JRadioButtonAndShowPanelPane(String choice1, String choice2, JPanel pane){
		
		panelToShow = pane;
		choice1Button = new JRadioButton(choice1);
		choice2Button = new JRadioButton(choice2);
		choice1Button.setFont(police2);
		choice2Button.setFont(police2);
		choice1Button.addActionListener(new AppearPanelChoice());
		choice2Button.addActionListener(new AppearPanelChoice());
		choice1Button.setBackground(Color.white);
		choice2Button.setBackground(Color.white);
		
		buttonGroup.add(choice1Button);
		buttonGroup.add(choice2Button);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(choice1Button);
		this.add(choice2Button);
	}
	
	
	public JRadioButtonAndShowPanelPane(String choice1, String choice2, String choice3, JPanel pane){
		
		panelToShow = pane;
		choice1Button = new JRadioButton(choice1);
		choice2Button = new JRadioButton(choice2);
		choice3Button = new JRadioButton(choice3);
		choice1Button.setFont(police2);
		choice2Button.setFont(police2);
		choice3Button.setFont(police2);
		choice1Button.addActionListener(new AppearPanelChoice());
		choice2Button.addActionListener(new AppearPanelChoice());
		choice3Button.addActionListener(new AppearPanelChoice());
		choice1Button.setBackground(Color.white);
		choice2Button.setBackground(Color.white);
		choice3Button.setBackground(Color.white);
		
		buttonGroup.add(choice1Button);
		buttonGroup.add(choice2Button);
		buttonGroup.add(choice3Button);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(choice1Button);
		this.add(choice2Button);
		this.add(choice3Button);
	}
	
	
	public JRadioButtonAndShowPanelPane(String choice1, String choice2){
		
		panelToShow = null;
		choice1Button = new JRadioButton(choice1);
		choice2Button = new JRadioButton(choice2);
		choice1Button.setFont(police2);
		choice2Button.setFont(police2);
		choice1Button.addActionListener(new AppearPanelChoice());
		choice2Button.addActionListener(new AppearPanelChoice());
		choice1Button.setBackground(Color.white);
		choice2Button.setBackground(Color.white);
		
		buttonGroup.add(choice1Button);
		buttonGroup.add(choice2Button);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(choice1Button);
		this.add(choice2Button);
	}
	
	
	public JRadioButtonAndShowPanelPane(String choice1, String choice2, String choice3){
		
		panelToShow = null;
		choice1Button = new JRadioButton(choice1);
		choice2Button = new JRadioButton(choice2);
		choice3Button = new JRadioButton(choice3);
		choice1Button.setFont(police2);
		choice2Button.setFont(police2);
		choice3Button.setFont(police2);
		choice1Button.addActionListener(new AppearPanelChoice());
		choice2Button.addActionListener(new AppearPanelChoice());
		choice3Button.addActionListener(new AppearPanelChoice());
		choice1Button.setBackground(Color.white);
		choice2Button.setBackground(Color.white);
		choice3Button.setBackground(Color.white);
		
		buttonGroup.add(choice1Button);
		buttonGroup.add(choice2Button);
		buttonGroup.add(choice3Button);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(choice1Button);
		this.add(choice2Button);
		this.add(choice3Button);
	}
	
	class AppearPanelChoice implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			
			if(panelToShow != null){
				if(e.getSource() == choice1Button)
					panelToShow.setVisible(true);
				else
					panelToShow.setVisible(false);
			}
		}
	}
	
	public JRadioButton getChoice1Button(){
		return choice1Button;
	}
	
	public JRadioButton getChoice2Button(){
		return choice2Button;
	}
	
	public JRadioButton getChoice3Button(){
		return choice3Button;
	}

}
