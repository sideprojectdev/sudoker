package sudoker;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by qhyu on 2015-08-29.
 */
public class SubGridTracker implements Observer{

    private ArrayList<ArrayList<Cell>> subGrid;

    public SubGridTracker() {
        subGrid = new ArrayList<ArrayList<Cell>>(3);
        for (int row=0; row<8; row++) {
            subGrid.set(row,new ArrayList<Cell>(3));
        }
    }

    public void update(Observable cell, Object o) {
        Integer value = (int)o;
        for(ArrayList<Cell> row:subGrid) {
            for (Cell c : row) {
                if (c.getPossibleValue().contains(value)) {
                    c.delPossibleValue(value);
                }
                c.update();
            }
        }
    }

    public ArrayList<ArrayList<Cell>> getSubGridTracker(){
        return this.subGrid;
    }

    public void setColTracker(ArrayList<ArrayList<Cell>> subGrid){
        this.subGrid = subGrid;
    }

}