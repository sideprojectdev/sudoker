package sudoker;

import java.util.ArrayList;

/**
 * Created by qhyu on 2015-08-29.
 */
public class Grid {

    private ArrayList<ArrayList<Cell>> board;

    public Grid() {
        ArrayList<RowTracker> rowTrackers = new ArrayList<RowTracker>(9);
        ArrayList<ColTracker> colTrackers = new ArrayList<ColTracker>(9);
        ArrayList<SubGridTracker> subGridTrackers = new ArrayList<SubGridTracker>(9);
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

    public void solve() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.get(row).get(col).update();
            }
        }
    }

    private ArrayList<Cell> newEmptyRow(int row) {
        ArrayList<Cell> rowOfCells = new ArrayList<Cell>(9);
        for (int col=0; col<8; col++) {
            rowOfCells.set(col, new Cell(row, col));
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
