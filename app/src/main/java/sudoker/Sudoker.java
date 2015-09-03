package sudoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by qhyu on 2015-08-31.
 */
public class Sudoker {

    private Grid board;

    public Sudoker() {
        board = new Grid();
    }

    public void load(String address) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> matrix =  new ArrayList<>();
        Scanner matrixScanner = initializeScannerWithAddress(address);
        matrix = getSudokuMatrixFromFile(matrixScanner, matrix);
        board.parseArray(matrix);
        matrixScanner.close();
    }

    public void solve() {
        System.out.println("Solving...");
        while(board.isNotComplete()) {
        //for(int i = 0; i<10000;i++) {
        	String temp = board.toString();
	        fillAllSingleSpots();
	        trackersUpdate();
	        if (madeNoProgress(temp,board)) {
	        	solveByTree(board);
	        	break;
	        }
        }
    }
    
    public void solve(Grid board) {
        while(board.isNotComplete()) {
        //for(int i = 0; i<10000;i++) {
        	String temp = board.toString();
	        fillAllSingleSpots(board);
	        trackersUpdate(board);
	        if (madeNoProgress(temp,board)) {
	        	solveByTree(board);
	        	break;
	        }
        }
    }
    
    public String toString() {
        return board.toString();
    }
    
    ///////////////////////////////////
    //PRIVATE HELPERS BELOW THIS LINE//
    ///////////////////////////////////
    
    private boolean madeNoProgress(String temp, Grid board) {
    	return temp.equals(board.toString());
    }
    
    private void trackersUpdate() {
    	board.trackersUpdate();
    }
    
    private void trackersUpdate(Grid board) {
    	board.trackersUpdate();
    }
    
    private void fillAllSingleSpots() {
    	for (int row = 0; row <9; row++) {
            for (int col = 0; col <9; col++) {
            	if ((row == 0) && (col == 6))
                board.getCell(row,col).update();
            }
        }
    }
    
    private void fillAllSingleSpots(Grid board) {
    	for (int row = 0; row <9; row++) {
            for (int col = 0; col <9; col++) {
                board.getCell(row,col).update();
            }
        }
    }
    
    private Scanner initializeScannerWithAddress(String address) throws FileNotFoundException {
        File sudokerFile = new File(address);
        return new Scanner(sudokerFile);
    }
    
    private ArrayList<ArrayList<Integer>> getSudokuMatrixFromFile(Scanner matrixScanner, ArrayList<ArrayList<Integer>> matrix) {
    	for (int i = 0; i < 9; i++) {
            String str = matrixScanner.nextLine();
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < 9; j++) {
            	matrix.get(i).add(Integer.valueOf(str.charAt(j))-48);
            }
        }
    	return matrix;
    }
    
    private void solveByTree(Grid board) {
    	ArrayList<Integer> posiitonOfBreakPoint = board.findPositionOfTheCandidate();
    	Cell breakPoint = board.getCell(posiitonOfBreakPoint.get(0), posiitonOfBreakPoint.get(1));
    	if (breakPoint.getPossibleValueCount() != 1) {
	    	for (int possibleValue : breakPoint.getPossibleValue()) {
	    		Grid tempBoard = board.copyGrid();
	    		Cell trial = tempBoard.getCell(posiitonOfBreakPoint.get(0), posiitonOfBreakPoint.get(1));
	    		trial.setValue(possibleValue);
	    		solve(tempBoard);
	    		if (!tempBoard.isNotComplete())
	    			this.board = tempBoard;
	    	}
    	}
    }
}
