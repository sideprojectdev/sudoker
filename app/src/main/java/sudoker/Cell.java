package sudoker;

import java.util.*;
import java.lang.*;

/**
 * Created by qhyu on 2015-08-28.
 */
public class Cell {
    private int value, row, line;
    private Set<Integer> possibleValueList;
    private boolean status;

    public Cell(int value, int row, int line){
        setValue(value);
        this.row = row;
        this.line = line;
        this.status = false;
    }

    public Cell(int row, int line){
        this.row = row;
        this.line = line;
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
        return this.line;
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
        if ((this.possibleValueList.size() == 1) && (this.status)) {
            setValue((int) possibleValueList.toArray()[0]);
            this.status = false;
        } else {
        }
    }

    public boolean isEmpty(){
        return this.possibleValueList.isEmpty();
    }



}
