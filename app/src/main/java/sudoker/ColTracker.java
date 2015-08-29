package sudoker;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by qhyu on 2015-08-29.
 */
public class ColTracker {

    private ArrayList<Cell> col;

    public ColTracker() {
        col = new ArrayList<Cell>(9);
    }

    public void update(Observable cell, Object o) {
        Integer value = (int)o;
        for (Cell c: col) {
            if (c.getPossibleValue().contains(value)) {
                c.delPossibleValue(value);
            }
            c.update();
        }
    }

}
