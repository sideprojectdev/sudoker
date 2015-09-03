package sudoker;

import java.util.ArrayList;

/**
 * Created by qhyu on 2015-08-29.
 */
public class Grid {

    private ArrayList<ArrayList<Cell>> board;
    private ArrayList<RowTracker> rowTrackers;
    private ArrayList<ColTracker> colTrackers;
    private ArrayList<ArrayList<SubGridTracker>> subGridTrackers;

    public Grid() {
    	initializeTrackers();
        board = CellStructureFactory.createGridOfCell(9, 9);
        linkCellsToTrackers();
    }

    public boolean isNotComplete() {
        for (int row = 0; row <9; row++) {
            for (int col = 0; col <9; col++) {
                if (getCell(row,col).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Cell getCell(int row, int col) {
        return board.get(row).get(col);
    }

    public void parseArray(ArrayList<ArrayList<Integer>> matrix) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                getCell(row,col).setValue(matrix.get(row).get(col));
            }
        }
    }
    
    public void trackersUpdate(){
    	for(int i = 0; i < 9; i++){
    		rowTrackers.get(i).fill();
    		colTrackers.get(i).fill();
    		subGridTrackers.get(i / 3).get(i % 3).fill();
    	}
    }
   
    public ArrayList<Integer> findPositionOfTheCandidate(){
    	ArrayList<Integer> position = new ArrayList<Integer>(2);
    	position.add(0);
    	position.add(0);
    	int min = 9;
    	int current = 0;
    	for(int row = 0; row < 9; row ++){
    		for(int col = 0; col < 9; col ++){
    			current = getCell(row,col).getPossibleValueCount();
        		if (cellHasLessPossibleValue(current, min)) {
        			min = current;
        			position.set(0, row);
        			position.set(1, col);
        		}
        	}
    	}
    	return position;
    }
    
    public Grid copyGrid(){
    	Grid copyGrid = new Grid();
    	for(int row = 0; row < 9; row ++) {
    		for(int col = 0; col < 9; col ++) {
    			copyGrid.getCell(row, col).setValue(board.get(row).get(col).getValue());
    		}
    	}
    	return copyGrid;
    }
    
    public String toString() {
        String flag = "";
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col <9; col++) {
                flag+=String.valueOf(getCell(row,col).getValue());
            }
            flag+="\n";
        }
        return flag;
    }
    
    ///////////////////////////////////
    //PRIVATE HELPERS BELOW THIS LINE//
    ///////////////////////////////////
    
    private void initializeTrackers() {
    	rowTrackers = new ArrayList<RowTracker>(9);
        for (int i = 0; i < 9; i++){rowTrackers.add(new RowTracker());}
        colTrackers = new ArrayList<ColTracker>(9);
        for (int i = 0; i < 9; i++){colTrackers.add(new ColTracker());}
        subGridTrackers = new ArrayList<ArrayList<SubGridTracker>>(3);
        for (int i = 0; i < 3; i++){
        	subGridTrackers.add(new ArrayList<SubGridTracker>());
        	for(int j = 0; j < 3; j++){
        		subGridTrackers.get(i).add(new SubGridTracker());
        	}
        }
    }
    
    private void linkCellsToTrackers() {
    	for (int row = 0; row < 9; row++) {
	    	for (int col = 0; col < 9; col++) {
	            Cell cell = getCell(row,col);
	            rowTrackers.get(row).getRowTracker().add(cell);
	            colTrackers.get(col).getColTracker().add(cell);
	            subGridTrackers.get(row / 3).get(col / 3).getSubGridTracker().get(row % 3).add(cell);
	            cell.addObserver(rowTrackers.get(row));
	            cell.addObserver(colTrackers.get(col));
	            cell.addObserver(subGridTrackers.get(row / 3).get(col / 3));
	        }
    	}
    }
    
    private boolean cellHasLessPossibleValue(int current, int min) {
    	return (current < min) && 
		(current > 1);
    }
}
