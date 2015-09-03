package sudoker;

import java.io.FileNotFoundException;

/**
 * Created by Chuanqi on 2015-08-31.
 */
public class tester {
    public static void main(String[] args) throws FileNotFoundException {
        String address = "sudoker/app/src/main/java/sudoker/sudoku1.txt";
        Sudoker sudoker = new Sudoker();
        sudoker.load(address);
        System.out.println(sudoker.toString());
        sudoker.solve();
        System.out.println();
        System.out.println(sudoker.toString());
        System.exit(0);

    }
}
