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
        for (int row=0; row<3; row++) {
            subGrid.add(new ArrayList<Cell>(3));
            for(int col = 0; col < 3; col++){
            	subGrid.get(row).add(new Cell());
            }
        }
    }

    public void update(Observable cell, Object o) {
        Integer value = (int)o;
        for(ArrayList<Cell> row:subGrid) {
            for (Cell c : row) {
                if (c.getPossibleValue().contains(value)) {
                    c.delPossibleValue(value);
                }
            }
        }
    }

    public void fill() {
        for (int value = 1; value < 10; value++) {
            ArrayList<Integer> index = new ArrayList<Integer>();
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (subGrid.get(row).get(col).getPossibleValue().contains(value)) {
                        index.add(row);
                        index.add(col);
                    }
                }
            }
            if (index.size() == 2)
            {
                subGrid.get(index.get(0)).get(index.get(1)).setValue(value);
                //System.out.println("subRow:"+index.get(0) +"subCol:"+ index.get(1) +" Value "+ value);
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
