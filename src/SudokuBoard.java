import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {
	public Square[][] board;
	private String[] digits = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
	
	public SudokuBoard() {
		board = new Square[9][9];
		for(int row = 0; row<9; row++)
			for(int col = 0; col<9; col++)
				board[row][col] = new Square();
		fillBoard();
	}
	
	public SudokuBoard(String b){
		board = new Square[9][9];
		for(int row = 0; row<9; row++)
			for(int col = 0; col<9; col++)
				board[row][col] = new Square(b.substring(row*9 + col, row*9 + col+1));
	}
	
	public ArrayList<String> availableNumbers(int row, int col) {
		ArrayList<String> nums = new ArrayList<String>();
		for(String d:digits)
			if(board[row][col].meetsException(d) && isLegal(row, col, d))
				nums.add(d);
		return nums;
	}
	
	private boolean isLegal(int row, int col, String num) {
		return legalCol(col, num) && legalRow(row, num) && legalSquare(row, col, num);
	}
	
	private boolean legalCol(int col, String num) {
		for(int row=0; row<9; row++)
			if(board[row][col].getValue().equals(num))
				return false;
		return true;
	}
	
	private boolean legalRow(int row, String num) {
		for(int col=0; col<9; col++)
			if(board[row][col].getValue().equals(num))
				return false;
		return true;
	}
	
	private boolean legalSquare(int row, int col, String num){
		int rowStart = row / 3 * 3;
		int colStart = col / 3 * 3;
		for(int r=rowStart; r<rowStart+3; r++)
			for(int c=colStart; c<colStart+3; c++)
				if(board[r][c].getValue().equals(num))
					return false;
		return true;
	}
	
	private boolean fillBox(int row, int col){
		ArrayList<String> nums = availableNumbers(row, col);
		if(nums.size() == 0)
			return false;
		board[row][col].change(nums.get((int)(Math.random() * nums.size())));
		return true;
	}
	
	private void fillBoard(){
		int row = 0;
		int col = 0;
		while(row < 9){
			while(col < 9){
				if(!fillBox(row, col)){
					board[row][col].clearExceptions();
					board[row][col].change(" ");
					col = (col > 0) ? col - 1: 8;
					if(col == 8)
						row--;
					board[row][col].addException(board[row][col].getValue());
				}
				else{
					col++;
				}
			}
			row++;
			col = 0;
		}
	}
	
	public void printBoard(){
		for(Square[] row:board){
			for(Square num:row)
				System.out.print(num.getValue());
			System.out.println();
		}
	}
	
	public String toString(){
		String su = "";
		for(Square[] b: board)
			for(Square num: b)
				su+=num.getValue();
		return su;
	}
	private boolean equal = true;
	
	private boolean solve(String input){
		equal = true;
		SudokuBoard broken = new SudokuBoard(input);
		return fill(broken, 0, 0);
		
	}
	
	private boolean fill(SudokuBoard broken, int row, int col){
		if(equal){
			if(broken.isComplete() && !toString().equals(broken.toString())){
				equal = false;
			}
			else if(row < 9 && !broken.board[row][col].isFixed()){
				ArrayList<String> nums = broken.availableNumbers(row, col);
				for(int x=0; x<nums.size(); x++){
					broken.board[row][col].change(nums.get(x));
					if(col == 8)
						fill(broken, row+1, 0);
					else
						fill(broken, row, col+1);
					
					broken.board[row][col].change(" ");
					if(!equal)
						break;
				}
			}
			else if(row < 9){
				if(col == 8)
					fill(broken, row+1, 0);
				else
					fill(broken, row, col+1);
			}
		}
		return equal;
	}
	
	public String destroy(int n){
		char[] str = toString().toCharArray();
		for(int x=0; x<81-n; x++){
			boolean done = false;
			while(!done){
				int place = (int)(Math.random() * 81);
				if(str[place] != ' '){
					str[place] = ' ';
					done = true;
				}
			}
		}
		
		String puzzle = "";
		for(char s: str)
			puzzle += s;
		return puzzle;
	}
	
	public boolean isComplete(){
		return !toString().contains(" ");
	}
	
	public String genPuzzle(int n){
		boolean con = true;
		int count = 0;
		double time;
		String input = "";
		while(con){
			count++;
			input = destroy(n);
			time = System.currentTimeMillis();
			con = !solve(input);
			System.out.println(count+" took: "+(System.currentTimeMillis()-time)+" milliseconds");
		}
		return input;
	}
	
	private boolean cont = true;
	
	public void solve(){
		cont = true;
		solve_fill(0,0);
	}
	
	private void solve_fill(int row, int col){
		if(cont){
			if(isComplete()){
				cont = false;
			}
			else if(row < 9 && !board[row][col].isFixed()){
				ArrayList<String> nums = availableNumbers(row, col);
				for(int x=0; x<nums.size(); x++){
					board[row][col].change(nums.get(x));
					if(col == 8)
						solve_fill( row+1, 0);
					else
						solve_fill(row, col+1);
					if(!cont)
						break;
					board[row][col].change(" ");
					
				}
			}
			else if(row < 9){
				if(col == 8)
					solve_fill(row+1, 0);
				else
					solve_fill(row, col+1);
			}
		}
	}
	
	public Square[][] getBoard() {
		return board;
	}
	
	
	public void setSquare(String let, int row, int col){
		int loc = 0;
		if(!board[row][col].isFixed()){
			board[row][col].change(let);
		}
	}
	
	
	public static void main(String[] args) {
		/*SudokuBoard board = new SudokuBoard();
		board.printBoard();
		System.out.println();
		SudokuBoard board2 = new SudokuBoard(board.genPuzzle(35));
		SudokuBoard board3 = new SudokuBoard(board2.toString());
		board2.printBoard();
		board2.solve();
		System.out.println();
		board2.printBoard();
		System.out.println ();
		board3.printBoard();
		System.out.println ();
		board3.setSquare(1, 1);
		board3.printBoard();*/
	}

}