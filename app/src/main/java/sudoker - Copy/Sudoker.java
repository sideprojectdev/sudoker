package sudoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by qhyu on 2015-08-31.
 */
public class Sudoker {

    private Grid board;

    public Sudoker() {
        board = new Grid();
    }

    public void load(String address) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> matrix =  new ArrayList<>();
        File sudokerFile = new File(address);
        Scanner sc = new Scanner(sudokerFile);
        for (int i = 0; i < 9; i++){
            String str = sc.nextLine();
            matrix.add(new ArrayList<Integer>());
            for (int j = 0; j < 9; j++){
            	matrix.get(i).add(Integer.valueOf(str.charAt(j))-48);
            	//System.out.println(j);
            	//System.out.println(str.indexOf(j));
            	//System.out.println((Integer)str.indexOf(j));
            	//System.out.println();
            	}
        }
        board.parseArray(matrix);
    }


    public void solve() {
        System.out.println("Solving...");
        while(!board.isComplete()) {
        //for(int i = 0; i<10000;i++){
	        for (int row = 0; row <9; row++) {
	            for (int col = 0; col <9; col++) {
	                board.getCell(row,col).update();
	            }
	        }
	    
	        trackersUpdate();
        }
    }
        
    
    public void trackersUpdate(){
    	board.trackersUpdate();
    }
    

    public String toString() {
        return board.toString();
    }

}
