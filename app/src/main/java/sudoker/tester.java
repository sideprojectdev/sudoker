package sudoker;

import java.io.FileNotFoundException;

/**
 * Created by Chuanqi on 2015-08-31.
 */
public class tester {
    public static void main(String[] args) throws FileNotFoundException {
        String address = new String();
        Sudoker sudoker = new Sudoker();
        sudoker.load(address);
        sudoker.toString();
        sudoker.solve();
        sudoker.toString();

    }
}
