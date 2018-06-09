package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class OrganismGenomePane extends JPanel {

	private Font police = new Font("Arial", Font.PLAIN, 12);
	private Font police2 = new Font("Arial", Font.BOLD, 12);
	private JLabel organismLabel = new JLabel("Organism");
	private JLabel genomeLabel = new JLabel("Genome");
	private String[] organismNames = {"human", "mouse"};
	private JComboBox<String> organismCombo = new JComboBox<String>(organismNames);
	private String[] genomeNames = {"hg19"};
	private JComboBox<String> genomeCombo = new JComboBox<String>(genomeNames);
	
	
	public OrganismGenomePane() {
		
		genomeLabel.setFont(police2);
		organismLabel.setFont(police2);
		organismCombo.addItemListener(new GenomeListener());
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Color.white);
		this.add(organismLabel);
		this.add(organismCombo);
		this.add(genomeLabel);
		this.add(genomeCombo);
		
	}
	
	//Define class to modify the genome combo according to the organism selected
	class GenomeListener implements ItemListener{
			
		public void itemStateChanged(ItemEvent event){
				
			String[] replaceGenomeNames;
			
			if(event.getStateChange() == ItemEvent.SELECTED){
				
				if(organismCombo.getSelectedItem() == "human")
					replaceGenomeNames = new String[]{"hg19"};
				else
					replaceGenomeNames = new String[]{"mm10"};
					
				DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(replaceGenomeNames);
				model.removeAllElements();
				
				for(String item : replaceGenomeNames)
					model.addElement(item);
				genomeCombo.setModel(model);
			}
		}
	}
}
