//Emily Becher
//Conway's Life
//12/6/2018
//Based off code from videos

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ConwayLife implements MouseListener, ActionListener, Runnable{
	
	//Variables
	boolean[][] cells = new boolean[25][25];
	JFrame frame = new JFrame("Life simulation");
	ConwayLifePanel panel = new ConwayLifePanel(cells);
	Container south = new Container();
	JButton random = new JButton("Random Board");
	JButton step = new JButton("Step");
	JButton start = new JButton("Start");
	JButton stop = new JButton("Stop");
	boolean running = false;
	
	//Constructor
	public ConwayLife() {
		frame.setSize(600,600);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		panel.addMouseListener(this);
		//south container
		frame.add(south, BorderLayout.SOUTH);
		south.setLayout(new GridLayout(1, 4));
		south.add(random);
		random.addActionListener(this);
		south.add(step);
		step.addActionListener(this);
		south.add(start);
		start.addActionListener(this);
		south.add(stop);
		stop.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConwayLife();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getX() + "," + e.getY());
		double width = (double)panel.getWidth() / cells[0].length;
		double height = (double)panel.getHeight() / cells.length;
		int column = Math.min(cells[0].length - 1, (int)(e.getX() / width));
		int row = Math.min(cells.length - 1, (int)(e.getY() / height));
		System.out.println(column + "," + row);
		cells[row][column] = !cells[row][column];
		frame.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {//Not used
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {//Not used
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(step)) {
			System.out.println("Step");
			step();
		}
		if (e.getSource().equals(start)) {
			System.out.println("Start");
			if(running == false) {
				running = true;
				Thread t = new Thread(this);
				t.start();
			}
		}
		if (e.getSource().equals(stop)) {
			System.out.println("Stop");
			running = false;
		}
		if (e.getSource().equals(random)) {
			for (int i = 0; i < 113; i++) {
				System.out.println("Random");
				double width = (double)panel.getWidth() / cells[0].length;
				double height = (double)panel.getHeight() / cells.length;
				double X = Math.random()*600 + 1;
				double Y = Math.random()*600 + 1;
				int column = Math.min(cells[0].length - 1, (int)(X / width));
				int row = Math.min(cells.length - 1, (int)(Y / height));
				System.out.println(column + "," + row);
				cells[row][column] = !cells[row][column];
				frame.repaint();
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (running == true) {
			step();
			Thread a = new Thread(this);
			try {
				a.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void step() {
		// TODO Auto-generated method stub
		boolean [][] nextCells = new boolean[cells.length][cells[0].length];
		for (int row = 0; row < cells.length; row++) {
			for (int column = 0; column < cells[0].length; column++) {
				int neighbors = 0;
				if (row > 0 && column > 0 && cells[row-1][column-1] == true) {//upper left
					neighbors++;
				}
				if (row > 0 && cells[row-1][column] == true) { //above
					neighbors++;
				}
				if (row > 0 && column < cells[0].length-1 && cells[row-1][column+1] == true) {//upper right
					neighbors++;
				}
				if (column > 0 && cells[row][column-1] == true) {//left
					neighbors++;
				}
				if (column < cells[0].length-1 && cells[row][column+1] == true) {//right
					neighbors++;
				}
				if (row < cells.length-1 && column > 0 && cells[row+1][column-1] == true) {//lower left
					neighbors++;
				}
				if (row < cells.length-1 && cells[row+1][column] == true) {//below
					neighbors++;
				}
				if (row < cells.length-1 && column < cells[0].length-1 && cells[row+1][column+1] == true) {//lower right
					neighbors++;
				}
				//Rules
				if (cells[row][column] == true) { //cell is alive
					if (neighbors == 2 || neighbors == 3) {
						nextCells[row][column] = true; //stays alive
					}
					else {
						nextCells[row][column] = false; //dies
					}
				}
				else { //cell is dead
					if (neighbors == 3) {
						nextCells[row][column] = true; //becomes alive
					}
					else {
						nextCells[row][column] = false; //stays dead
					}
				}
			}
		}
		cells = nextCells;
		panel.setCells(nextCells);
		frame.repaint();
	}

	

}
