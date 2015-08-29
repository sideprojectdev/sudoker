package sudoker;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by qhyu on 2015-08-29.
 */
public class RowTracker extends Structure{

    private ArrayList<Cell> row;

    public RowTracker() {
        row = new ArrayList<Cell>(9);
    }

    public void update(Observable cell, Object o) {
        Integer value = (int)o;
        for (Cell c: row) {
            if (c.getPossibleValue().contains(value)) {
                c.delPossibleValue(value);
            }
            c.update();
        }
    }
}
