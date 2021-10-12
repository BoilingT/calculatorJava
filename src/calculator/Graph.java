package calculator;

import java.awt.Point;
import java.util.ArrayList;

public class Graph {

	private String formula;
	private Parser parser;	
	private ArrayList<double[]> Points = new ArrayList<double[]>();
	private double gap;
	private int width;
	
	public Graph(String formula, Parser parser, double gap, int width) {
		this.formula = formula;
		this.parser = parser;
		this.width = width;
		this.gap = gap;
		calculatePoints(-width/2/40, width/2/40);
	}
	
	public void calculatePoints(int start, int end) {
		int total = 0;
		System.out.println("start: " + start/gap + " end: " + end/gap);

		for (double i = start; i < end; i+=gap) {
			double parsedValue;
			try {
				i = Math.floor(i * 1000)/1000;
				parsedValue = parser.parse(getFormula(), "x", i);
				Points.add(new double[] {i, parsedValue});
				System.out.println("\nX: " + i + "\nY: " + parsedValue);
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
		return Points.size();
	}
	
	public double[] getPoint(int index) {
		return Points.get(index);
	}
}
