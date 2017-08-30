/*
 * Copyright(c) 2007 Robert Glen Martin
 * (http://martin.apluscomputerscience.com/)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Robert Glen Martin
 */

import info.gridworld.world.World;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.HashMap;
import java.util.ArrayList;

public class SudokuWorld extends World
{
	private SudokuBoard board;
	private Sudoku game;
	private String key;
	private String[] digits = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
	private HashMap<String, String> nums;
	private HashMap<String, String> lets;
	private final int CLUES = 30;
	
	public SudokuWorld(Sudoku game)
	{
		super(new BoundedGrid(9, 9));
		this.game = game;
		board = new SudokuBoard();
		key = this.board.toString();
		this.board = new SudokuBoard(board.genPuzzle(CLUES));
		setMessage("Sudoku. Click a cell to play.");
		
		ArrayList<String> digs = new ArrayList<String>();
    	for(String d: digits)
    		digs.add(d);
    	nums = new HashMap<String, String>();
    	lets = new HashMap<String, String>();
    	int place = 1;
    	while(digs.size() > 0)
    	{
    		int rand = (int)(Math.random() * digs.size());
    		nums.put(digs.get(rand), Integer.toString(place));
    		lets.put(Integer.toString(place), digs.get(rand));
    		digs.remove(rand);
    		place++;
    	}
    	nums.put(" ",  "");
    	
		System.setProperty("info.gridworld.gui.selection", "hide");
		System.setProperty("info.gridworld.gui.tooltips", "hide");
		System.setProperty("info.gridworld.gui.watermark", "hide");
		Square squares[][] = board.getBoard();
		System.out.println (nums.get(squares[0][0].getValue()));
		for(int row=0; row<9; row++)
			for(int col=0; col<9; col++)
				place(nums.get(squares[row][col].getValue()), row, col, true);
	}

	public boolean locationClicked(Location loc)
	{
		Square squares[][] = board.getBoard();
		set(nums.get(squares[loc.getRow()][loc.getCol()].getValue()), loc.getRow(), loc.getCol());
		place(nums.get(squares[loc.getRow()][loc.getCol()].getValue()), loc.getRow(), loc.getCol(), false);
		if(isSolved())
		{
			System.out.println ("you win");
		}
		return true;
	}
	
	
	private void place(String num, int row, int col, boolean fixed)
	{
        switch (num) {
            case "1":  add(new Location(row, col), new One(fixed));
                     break;
            case "2":  add(new Location(row, col), new Two(fixed));
                     break;
            case "3":  add(new Location(row, col), new Three(fixed));
                     break;
            case "4":  add(new Location(row, col), new Four(fixed));
                     break;
            case "5":  add(new Location(row, col), new Five(fixed));
                     break;
            case "6":  add(new Location(row, col), new Six(fixed));
                     break;
            case "7":  add(new Location(row, col), new Seven(fixed));
                     break;
            case "8":  add(new Location(row, col), new Eight(fixed));
                     break;
            case "9":  add(new Location(row, col), new Nine(fixed));
                     break;
            default: add(new Location(row, col), new Space());
                     break;
        }
	}
	
	public void set(String num, int row, int col){
		switch (num) {
            case "1":  board.setSquare(lets.get("2"), row, col);
                     break;
            case "2":  board.setSquare(lets.get("3"), row, col);
                     break;
            case "3":  board.setSquare(lets.get("4"), row, col);
                     break;
            case "4":  board.setSquare(lets.get("5"), row, col);
                     break;
            case "5":  board.setSquare(lets.get("6"), row, col);
                     break;
            case "6":  board.setSquare(lets.get("7"), row, col);
                     break;
            case "7":  board.setSquare(lets.get("8"), row, col);
                     break;
            case "8":  board.setSquare(lets.get("9"), row, col);
                     break;
            case "9":  board.setSquare(lets.get("1"), row, col);
                     break;
            default: board.setSquare(lets.get("1"), row, col);
                     break;
		}
	}
	
	public boolean isSolved() {
		return board.toString().equals(key);
	}
}
