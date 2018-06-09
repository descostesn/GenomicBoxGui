package GenomicBox.graphicClasses.paneClasses;

public class EmptyObjectArray {
	
	private Object[][] rawData;
	
	
	public EmptyObjectArray(int numRow, int numCol){
		
		rawData = new Object[numRow][numCol];
		
		
		for (int i = 0; i < numRow; i++) {
			
			for (int j = 0; j < numCol; j++) {
				rawData[i][j] = "";
			}
		}
	}
	
	public Object[][] getRowData(){
		return rawData;
	}

}
