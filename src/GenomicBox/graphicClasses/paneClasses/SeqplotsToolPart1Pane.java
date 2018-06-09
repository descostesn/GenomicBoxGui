package GenomicBox.graphicClasses.paneClasses;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import GenomicBox.graphicClasses.actionClasses.FillFormWithFiles;
import GenomicBox.graphicClasses.modelClasses.CustomTableModel;
import GenomicBox.toolsTabClasses.MetaProfiling;



//This class creates a panel to define a part of the options used by seqplots.
// It contains: a bigwig file selection panel, a GFF annotation panel,
// an organism/genome selection panel, and a upstream/downstream/location panel

public class SeqplotsToolPart1Pane extends JPanel {

	private JFrame frame;
	private LabelAndSelectButtonPane selectFilesPane;
	private FileSelectionDisplayAndNamePane annotationsPane;
	private OrganismGenomePane organismGenomePane;
	private UpstreamDownStreamLocationPane upstreamDownstreamLocationPane;
	
	public SeqplotsToolPart1Pane(int preProcWidth, int preProcHeight, String className, 
			CustomTableModel model, String format, boolean includeAnnotationPane, String fileTypeLabel){
		
		selectFilesPane = new LabelAndSelectButtonPane(fileTypeLabel);
		selectFilesPane.getButton().addActionListener(new FillFormWithFiles(SeqplotsToolPart1Pane.this, format, frame,
				className + " expects ." + format + " files as input", model));
		
		annotationsPane = new FileSelectionDisplayAndNamePane(preProcWidth, 
				preProcHeight, "GFF annotations:", "GFF name:", SeqplotsToolPart1Pane.this, frame, "GFF", "gff");
		
		organismGenomePane = new OrganismGenomePane();
		upstreamDownstreamLocationPane = new UpstreamDownStreamLocationPane(preProcWidth, preProcHeight);
		
		this.setSize(preProcWidth,preProcHeight);
		this.setBackground(Color.white);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(selectFilesPane);
		if(includeAnnotationPane)
			this.add(annotationsPane);
		this.add(organismGenomePane);
		this.add(upstreamDownstreamLocationPane);
		this.setVisible(true);
	}
}
