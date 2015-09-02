package sudoker;

import java.util.*;


/**
 * Created by qhyu on 2015-08-28.
 */
public class Cell extends Observable{
    private int value, row, col;
    private Set<Integer> possibleValueList = new HashSet<Integer>();
    private boolean status;

    public Cell(int value){
        setValue(value);
        this.status = false;
        for(int i = 1; i < 10; i++) {if (i != value) {this.addPossibleValue(i);}}
    }

    public Cell(){
        this.value = 0;
        this.status = true;
        for(int i = 1; i < 10; i++){this.addPossibleValue(i);}
    }

    public void setValue(int value){
        if (this.status) {
            this.value = value;
            setChanged();
            notifyObservers(getValue());
            this.clearPossibleValueList();
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

    public void cellRefresh(){
        this.value = 0;
        this.status = true;
    }

    public Set<Integer> getPossibleValue(){
        return this.possibleValueList;
    }
    
    public void setPossibleValue(Set<Integer> possibleValueList){
    	this.possibleValueList = possibleValueList;
    }

    public void addPossibleValue(int possibleValue){
        this.possibleValueList.add(possibleValue);
    }

    public void delPossibleValue(int possibleValue){
        if (this.possibleValueList.contains(possibleValue)){
            this.possibleValueList.remove(possibleValue);
        }
    }

    public void update(){
        if (readyForChange()) {
            setValue((int) possibleValueList.toArray()[0]);
            setChanged();
            notifyObservers(getValue());
        }
    }

    public boolean isEmpty(){
        return this.getValue() == 0;
    }
    
    public void clearPossibleValueList(){
    	if (!isEmpty()){
    		this.setPossibleValue(new HashSet<Integer>());
    		this.addPossibleValue(value);
    	}
    }
    
    public int possibleValueNumber(){
    	return getPossibleValue().size();
    }
    
    private boolean readyForChange() {
        return (this.possibleValueList.size() == 1) && (this.isEmpty() );
    }
    

}
