/**
 * AWT Sample application
 *
 * @author 
 * @version 1.00 16/09/13
 */
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
public class Sudoku {
	private SudokuWorld world;
	private boolean isComplete;
	
    public Sudoku(){
    	world = new SudokuWorld(this);
    	world.show();
    }
    
    public void playGame() {
    	
    }
    public static void main(String[] args) {
        (new Sudoku()).playGame();
    }
}