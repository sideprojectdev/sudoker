package sudoker;

import java.util.ArrayList;

/**
 * Created by qhyu on 2015-08-31.
 */
public class Sudoker {

    private Grid board;

    public Sudoker() {
        board = new Grid();
    }

    public void load(String address) {
        ArrayList<ArrayList<Integer>> matrix =  new ArrayList<>();

    }


    public void solve() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board.getCell(row,col).update();
            }
        }
    }
    
    public String toString() {
        return board.toString();
    }

}
