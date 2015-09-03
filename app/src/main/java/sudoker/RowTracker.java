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

    public void fill() {
        for (int value = 1; value < 10; value++) {
            if (thisValueIsNotFilled(value) && valueHasOnlyOnePossiblePosition(value)) {
                putValueInThePosition(value);
            }  
        }
    }

    public ArrayList<Cell> getRowTracker(){
        return this.row;
    }

    public void setRowTracker(ArrayList<Cell> row){
        this.row = row;
    }
    
    private boolean thisValueIsNotFilled(int value) {
    	for (int col = 0; col < 9; col++) {
            if (row.get(col).getValue() == value)
                return false;
        }
    	return true;
    }

    private boolean valueHasOnlyOnePossiblePosition(int value) {
    	ArrayList<Integer> index = new ArrayList<Integer>();
        for (int col = 0; col < 9; col++) {
            if (row.get(col).getPossibleValue().contains(value))
                index.add(col);
        }
    	return index.size() == 1;
    }
    
    private void putValueInThePosition(int value) {
    	for (Cell candidate : row) {
    		if (candidate.getPossibleValue().contains(value))
    			candidate.setValue(value);
    	}
    }

}
