package sudoker;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by qhyu on 2015-08-29.
 */
public class RowTracker extends Structure implements Observer{

    private ArrayList<Cell> row;

    public RowTracker() {
        row = new ArrayList<Cell>(9);
        for (int i=0;i<9;i++){row.add(new Cell());}
    }

    public void update(Observable cell, Object o) {
        Integer value = (Integer)o;
        for (Cell c: row) {
            if (c.getPossibleValue().contains(value)) {
                c.delPossibleValue(value);
            }
        }
    }

    public ArrayList<Cell> getRowTracker(){
        return this.row;
    }

    public void setRowTracker(ArrayList<Cell> row){
        this.row = row;
    }

}
