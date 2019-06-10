//Emily Becher
//Conway's Life Panel
//12/6/2018
//Based off code from videos
//1:01 Video Five

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.lang.reflect.Array;

import javax.swing.JPanel;

public class ConwayLifePanel extends JPanel{
	
	boolean[][] cells;
	double width;
	double height;
    

	public ConwayLifePanel(boolean[][] in) {
		cells = in;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		width = (double)this.getWidth() / cells[0].length;
		height = (double)this.getHeight() / cells[0].length;
		
		//Draw in the boxes
		g.setColor(new Color(71, 152, 30));
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				if (cells[row][column] == true) {
					g.fillRect((int)Math.round(column*width), (int)Math.round(row*height), 
							(int)(width+1), (int)(height+1));
				}
			}
		}
		//Draw lines
		g.setColor(Color.PINK);
		for (int x = 0; x < cells[0].length + 1; x++) {
			g.drawLine((int)Math.round(x*width),0,(int)Math.round(x*width),this.getHeight());
		}
		for (int y = 0; y < cells.length + 1; y++) {
			g.drawLine(0,(int)Math.round(y*height),this.getWidth(),(int)Math.round(y*height));
		}
		
	}

	public void setCells(boolean[][] nextCells) {
		// TODO Auto-generated method stub
		this.cells = nextCells;
		
	}
	
}
