package sudoker;

import java.util.ArrayList;

public class CellStructureFactory {
	
	public static ArrayList<Cell> createArrayOfCell(int numOfElements) {
		ArrayList<Cell> cells = new ArrayList<Cell>(numOfElements);
		for (int index = 0; index < 9; index++) {
            Cell cell = new Cell();
            cells.add(cell);
        }
		return cells;
	}
	
	public static ArrayList<ArrayList<Cell>> createGridOfCell(int row, int col) {
		ArrayList<ArrayList<Cell>> cells = new ArrayList<ArrayList<Cell>>(row);
		for (int i = 0; i < row; i++) {
            cells.add(createArrayOfCell(col));
        }
		return cells;
	}
	
}
