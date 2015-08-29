package sudoker;

import java.util.*;
import java.lang.*;

/**
 * Created by qhyu on 2015-08-28.
 */
public class Cell {
    private int value, row, col;
    private Set<Integer> possibleValueList;
    private boolean status;

    public Cell(int value, int row, int col){
        setValue(value);
        this.row = row;
        this.col = col;
        this.status = false;
    }

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.value = 0;
        this.status = true;
    }

    public void setValue(int value){
        if (!this.status) {
            this.value = value;
            this.status = false;
        }
    }

    public int getValue(){
        return this.value;
    }

    public int getLine(){
        return this.col;
    }

    public int getRow(){
        return this.row;
    }

    public Set<Integer> getPossibleValue(){
        return this.getPossibleValue();
    }

    public boolean addPossibleValue(int possibleValue){
        this.possibleValueList.add(possibleValue);
        return !this.possibleValueList.contains(possibleValue);
    }

    public boolean delPossibleValue(int possibleValue){
        if (this.possibleValueList.contains(possibleValue)){
            this.possibleValueList.remove(possibleValue);
            update();
            return true;
        }
        else{
            return false;
        }
    }

    public void update(){
        if (readyForChange()) {
            setValue((int) possibleValueList.toArray()[0]);
            this.status = false;
        } else {
        }
    }

    public boolean isEmpty(){
        return this.possibleValueList.isEmpty();
    }

    private boolean readyForChange() {
        return (this.possibleValueList.size() == 1) && (this.status);
    }

}
