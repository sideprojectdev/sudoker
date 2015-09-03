package sudoker;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by qhyu on 2015-08-29.
 */
public class ColTracker implements Observer{

    private ArrayList<Cell> col;

    public ColTracker() {
        col = new ArrayList<Cell>(9);
        for (int i = 0;i < 9;i++) {
        	col.add(new Cell());
        }
    }

    public void update(Observable cell, Object o) {
        Integer value = (Integer)o;
        for (Cell c: col) {
            if (c.getPossibleValue().contains(value)) {
                c.delPossibleValue(value);
            }
        }
    }

    public void fill() {
        for (int value = 1; value < 10; value++) {
            if (thisValueIsNotFilled(value) && valueHasOnlyOnePossiblePosition(value)) {
                putValueInThePosition(value);
            }  
        }
    }

    public ArrayList<Cell> getColTracker() {
        return this.col;
    }

    public void setColTracker(ArrayList<Cell> col) {
        this.col = col;
    }
    
    private boolean thisValueIsNotFilled(int value) {
    	for (int row = 0; row < 9; row++) {
            if (col.get(row).getValue() == value)
                return false;
        }
    	return true;
    }

    private boolean valueHasOnlyOnePossiblePosition(int value) {
    	ArrayList<Integer> index = new ArrayList<Integer>();
        for (int row = 0; row < 9; row++) {
            if (col.get(row).getPossibleValue().contains(value))
                index.add(row);
        }
    	return index.size() == 1;
    }
    
    private void putValueInThePosition(int value) {
    	for (Cell candidate : col) {
    		if (candidate.getPossibleValue().contains(value))
    			candidate.setValue(value);
    	}
    }

}
