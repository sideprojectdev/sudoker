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
        colTrackers = new ArrayList<ColTracker>(9);
        subGridTrackers = new ArrayList<ArrayList<SubGridTracker>>(3);
        board = new ArrayList<ArrayList<Cell>>(9);
        for (int i = 0; i < 8; i++) {
            board.set(i, newEmptyRow(i));
        }
    }

    public boolean isComplete() {
        boolean flag = true;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
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
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                getCell(row,col).setValue(matrix.get(row).get(col));
            }
        }
    }

    private ArrayList<Cell> newEmptyRow(int row) {
        ArrayList<Cell> rowOfCells = new ArrayList<Cell>(9);
        for (int col=0; col<8; col++) {
            Cell cell = new Cell();
            rowOfCells.set(col, cell);
            rowTrackers.get(row).getRowTracker().set(col, cell);
            colTrackers.get(col).getColTracker().set(row, cell);
            subGridTrackers.get(col / 3).get(row / 3).getSubGridTracker().get(col % 3).set(row % 3, cell);
            cell.addObserver(rowTrackers.get(row));
            cell.addObserver(colTrackers.get(col));
            cell.addObserver(subGridTrackers.get(col / 3).get(row / 3));
        }
        return rowOfCells;
    }

    public String toString() {
        String flag = "";
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                flag+=String.valueOf(board.get(row).get(col).getValue());
            }
            flag+="\n";
        }
        return flag;
    }
}
