package calculator;

import java.awt.Point;

public class Graph {

	private String formula;
	private Parser parser;	
	private Point[] Points;
	private int gap;
	private int width;
	
	public Graph(String formula, Parser parser, int gap, int width) {
		this.formula = formula;
		this.parser = parser;
		this.width = width;
		Points = new Point[width/gap];
		System.out.println(Points.length);
		calculatePoints(Points.length);
	}
	
	private void calculatePoints(int n) {
		int total = 0;
		for (int i = -Points.length/2; i < Points.length/2; i+=gap) {
			double parsedValue;
			try {
				parsedValue = parser.parse(getFormula(), "x", i);
				Points[total++] = new Point(i, (int)parsedValue);
				//System.out.println("X: " + i + " Y: " + parsedValue);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
	
	public String getFormula() {
		return this.formula;
	}
	
	public int points() {
		return Points.length;
	}
	
	public Point getPoint(int index) {
		return Points[index];
	}
}
