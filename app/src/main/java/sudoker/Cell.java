package sudoker;

import java.util.*;


/**
 * Created by qhyu on 2015-08-28.
 */
public class Cell extends Observable{
    private int value;
    private Set<Integer> possibleValueList = new HashSet<Integer>();

    public Cell() {
        this.value = 0;
        initializePossibleValue();
    }

    public void setValue(int value) {
    	this.value = value;
    	setChanged();
    	notifyObservers(getValue());
    	this.clearPossibleValueList();
    }

    public int getValue() {
        return this.value;
    }

    public Set<Integer> getPossibleValue() {
        return this.possibleValueList;
    }


    public void addPossibleValue(int possibleValue) {
        this.possibleValueList.add(possibleValue);
    }

    public void delPossibleValue(int possibleValue) {
        if (this.possibleValueList.contains(possibleValue)) {
            this.possibleValueList.remove(possibleValue);
        }
    }

    public void update() {
        if (readyForChange()) {
            setValue((int) possibleValueList.toArray()[0]);
            setChanged();
            notifyObservers(getValue());
        }
    }

    public boolean isEmpty() {
        return this.getValue() == 0;
    }
    
    public void clearPossibleValueList() {
    	if (!isEmpty()) {
    		this.setPossibleValue(new HashSet<Integer>());
    		this.addPossibleValue(value);
    	}
    }
    
    public int getPossibleValueCount() {
    	return getPossibleValue().size();
    }
    
    ///////////////////////////////////
    //PRIVATE HELPERS BELOW THIS LINE//
    ///////////////////////////////////
    
    private boolean readyForChange() {
        return (getPossibleValueCount() == 1) && (this.isEmpty() );
    }
    
    private void initializePossibleValue() {
    	for(int i = 1; i < 10; i++) {
        	this.addPossibleValue(i);
        }
    }
    
    private void setPossibleValue(Set<Integer> possibleValueList) {
    	this.possibleValueList = possibleValueList;
    }
}
