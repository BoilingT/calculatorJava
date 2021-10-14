package calculator;

import java.util.ArrayList;

public class Graph {

	private String formula;
	private Parser parser;	
	private ArrayList<double[]> Points = new ArrayList<double[]>();
	private double gap;
	private double width;
	
	public Graph(String formula, Parser parser, double gap, double width, double offsetX) {
		this.formula = formula;
		this.parser = parser;
		this.width = width;
		this.gap = gap;
		calculatePoints(-(width)/2/40, (width)/2/40, offsetX);
	}
	
	public void calculatePoints(double start, double end, double offsetX) {
		System.out.println("start: " + start/gap + " end: " + end/gap);
		if (Points.size() > 0) {
			Points.clear();			
		}
		for (double i = start*3; i < end*3; i+=gap) {
			double parsedValue;
			try {
				i = Math.floor(i * 1000)/1000;
				parsedValue = parser.parse(getFormula(), "x", i+offsetX);
				Points.add(new double[] {i, parsedValue});
				System.out.println("\nX: " + i+offsetX + "\nY: " + parsedValue);
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
