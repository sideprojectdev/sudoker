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
        board = new ArrayList<ArrayList<Cell>>(9);
        for (int i = 0; i < 9; i++) {
            board.add(newEmptyRow(i));
        }
    }

    public boolean isComplete() {
        boolean flag = true;
        for (int row = 0; row <9; row++) {
            for (int col = 0; col <9; col++) {
                if (board.get(row).get(col).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell getCell(int row, int col) {
        return board.get(row).get(col);
    }

    public void parseArray(ArrayList<ArrayList<Integer>> matrix) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                getCell(row,col).setValue(matrix.get(row).get(col));//different constructor when 0;
                
                //System.out.println("Row:"+row +"Col"+ col +"Value"+ matrix.get(row).get(col) + "PL:" +getCell(row,col).getPossibleValue() );
            }
        }
    }

    private ArrayList<Cell> newEmptyRow(int row) {
        ArrayList<Cell> rowOfCells = new ArrayList<Cell>(9);
        for (int col=0; col<9; col++) {
            Cell cell = new Cell();
            rowOfCells.add(cell);
            rowTrackers.get(row).getRowTracker().set(col, cell);
            colTrackers.get(col).getColTracker().set(row, cell);
            subGridTrackers.get(row / 3).get(col / 3).getSubGridTracker().get(row % 3).set(col % 3, cell);
            cell.addObserver(rowTrackers.get(row));
            cell.addObserver(colTrackers.get(col));
            cell.addObserver(subGridTrackers.get(row / 3).get(col / 3));
        }
        return rowOfCells;
    }
    
    public void trackersUpdate(){
    	for(int i = 0; i < 9; i++){
    		//System.out.println("Updating row " + i);
    		rowTrackers.get(i).fill();
    		//System.out.println(getCell(0,0).getValue());
    		//System.out.println("Updating col " + i);
    		colTrackers.get(i).fill();
    		//System.out.println(getCell(0,0).getValue());
    		//System.out.println("Updating subGrid " + i/3 +" "+ i%3 );
    		subGridTrackers.get(i / 3).get(i % 3).fill();
    		//System.out.println(getCell(0,0).getValue());
    	}
    }

    public String toString() {
        String flag = "";
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col <9; col++) {
                flag+=String.valueOf(getCell(row,col).getValue());
                //System.out.println(flag);
               // System.out.println(getCell(row,col).getValue());
            }
            flag+="\n";
           // System.out.println(flag);
           // System.out.println();
        }
        return flag;
    }
}
