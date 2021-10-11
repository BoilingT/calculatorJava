package calculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphCanvas extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public int height, width, X, Y;
	public int spacingY = 40, spacingX = 40;
	public Point origin = null;
	public ArrayList<Graph> Graphs = new ArrayList<Graph>();
	
	public GraphCanvas(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.height = this.getHeight();
		this.width = this.getWidth();
		origin = new Point(width/2, height/2);
		System.out.println("Width: " + this.width + " Height: " + this.height);
	}
		
	public int getColumns() {
		return (int) (width/ spacingX)*5;
	}
	
	public int getRows() {
		return (int) (height/ spacingY)*5;
	}
	
	private void createGrid(Graphics g, int width, int height) {
		int rows = getRows();
		int cols = getColumns();
		//System.out.println("X: " + X);
		//System.out.println("Y: " + Y);
		g.setColor(Color.BLACK);
		origin.x += X;
		origin.y += Y;
		for (int x = -cols; x <= cols; x++) {
			int tempx = x*spacingX;
			g.drawLine(origin.x + tempx, 0, origin.x + tempx, height);
		}
		
		for (int y = -rows/2; y <= rows/2; y++) { //Rows
			int tempy = y*spacingY;
			g.drawLine(0, origin.y + tempy, width, origin.y + tempy);
		}
		
		//g.setColor(Color.BLUE);
		//g.fillArc(origin.x-5, origin.y-5, 10, 10, 0, 360);
	}
	
	
	public void draw(int x, int y) {
		this.height = this.getHeight();
		this.width = this.getWidth();
		X = x; Y = y;
		repaint();
	}
	
	public void draw() {
		repaint();
	}

	public void paintComponent(Graphics g) {
		this.height = this.getHeight();
		this.width = this.getWidth();
		origin = new Point(width/2, height/2);
		g.clearRect(0, 0, width, height);
		g.drawLine(0, 0, width, height);
		g.drawLine(width, 0, 0, height);
		createGrid(g, this.width, this.height);
		g.fillArc(origin.x-5, origin.y + -5, 10, 10, 0, 360);

//		g.setColor(Color.BLUE);
//		for (int i = 0; i < Graphs.size(); i++) {
//			Graph graph = Graphs.get(i);
//			for (int j = 0; j < graph.points()-1; j++) {
//				Point prevPoint = graph.getPoint(j);
//				Point point = graph.getPoint(j+1);
//				int x1 = prevPoint.x * spacingX;
//				int y1 = prevPoint.y * spacingY;
//				int x2 = point.x * spacingX;
//				int y2 = point.y * spacingY;
//				g.drawLine(origin.x + x1, origin.y - y1, origin.x + x2, origin.y - y2);
//				//System.out.println(prevPoint.toString());
//			}
//		}
	}
	
	public void normalize(int x, int y) {
		float  nX = (x/(float)spacingX) - Math.round(x/spacingX);
		float nY = (y/(float)spacingY) - Math.round(y/spacingY);
		System.out.println("nx: " + X + "\n ny: " + Y);
		draw((int)(nX*spacingX), (int)(nY*spacingY));
	}
	
	public void addGraph(Graph graph) {
		Graphs.add(graph);
	}
	
	public void drawGraph(int index) throws ArrayIndexOutOfBoundsException{
		if (Graphs.size() > 0) {
			try {
				int var = 3;
				//System.out.println("Result: " + );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}else {
			throw new ArrayIndexOutOfBoundsException("No graphs added");
		}
	}
}
