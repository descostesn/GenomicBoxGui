package GenomicBox.graphicClasses.modelClasses;


import java.util.Arrays;
import javax.swing.table.AbstractTableModel;




public class CustomTableModel extends AbstractTableModel {
	
	private Object rowData[][];
	private String[] columnNames;
	
	public CustomTableModel(Object data[][], String[] titles){
		rowData = data;
		columnNames = titles;
	}
	
    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int getRowCount() {
        return rowData.length;
    }

    public Object getValueAt(int row, int column) {
        return rowData[row][column];
    }

    public Class getColumnClass(int column) {
        return this.rowData[0][column].getClass();
    }
    
    
    public int getColumnIndex(String columnName){
    	return Arrays.asList(columnNames).indexOf(columnName);
    }
    
    public void setValueAt(Object value, int row, int column) {
        rowData[row][column] = value;
        fireTableDataChanged();
    }
    
    public boolean isCellEditable(int row, int column) {
        
        if(column == 0 && columnNames[0].equals("Sample Name"))
        	return true;
        else
        	return (column != 0);
    }


}
