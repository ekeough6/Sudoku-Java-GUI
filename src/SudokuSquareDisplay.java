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
 *
 * The following code is based on the Fish display code from the
 * MBS Case Study.
 */

import info.gridworld.gui.AbstractDisplay;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Font;

public class SudokuSquareDisplay extends AbstractDisplay
{
    private static final double PIECE_DIAMETER = .8;
    private static final int GRADIENT_SIZE = 50;
    private static final AffineTransform ATX =
    	AffineTransform.getScaleInstance(GRADIENT_SIZE, GRADIENT_SIZE);
	private HashMap<String, String> nums;
    private Shape body;
    private String[] digits = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};

    /** Constructs an object that knows how to draw simple piece.
     **/
    public SudokuSquareDisplay()
    {
    	ArrayList<String> digs = new ArrayList<String>();
    	for(String d: digits)
    		digs.add(d);
    	nums = new HashMap<String, String>();
    	int place = 1;
    	while(digs.size() > 0)
    	{
    		int rand = (int)(Math.random() * digs.size());
    		nums.put(digs.remove(rand), Integer.toString(place));
    		place++;
    	}
    }

    public void draw(Object obj, Component comp, Graphics2D g2)
    {
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						    RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setBackground(Color.blue);
		g2.setColor(Color.black);
		Font font = new Font("Serif", Font.PLAIN, 25);
    	g2.setFont(font);
    	SudokuSquare piece = (SudokuSquare)obj;
        g2.drawString("Hello", comp.WIDTH/2, comp.HEIGHT/2);
    }
}
