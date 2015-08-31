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
        File sudokerFile = new File(address);
        Scanner sc = new Scanner(sudokerFile);
        for (int i = 0; i < 9; i++){
            String str = sc.nextLine();
            for (int j = 0; j < 9; j++){matrix.get(i).set(j, (Integer)str.indexOf(j));}
        }
        board.parseArray(matrix);
    }


    public void solve() {
        System.out.println("Solving...");
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
