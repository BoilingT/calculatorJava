package calculator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.geom.Arc2D.Double;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphCanvas extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private int height, width;
	private int spacingY = 40, spacingX = 40;
	private double gridOffsetX = 0;
	private double gridOffsetY = 0;
	private double originX = 0;
	private double originY = 0;
	private double graphOffsetX = 0;
	private double graphOffsetY = 0;
	private ArrayList<Graph> Graphs = new ArrayList<Graph>();
	
	public GraphCanvas(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.height = this.getHeight();
		this.width = this.getWidth();
		originX = width/2;
		originY = height/2;
		System.out.println("Width: " + this.width + " Height: " + this.height);
	}
		
	public int getColumns() {
		return (int) (width/spacingX)*5;
	}
	
	public int getRows() {
		return (int) (height/spacingY)*5;
	}
	
	public double[] getOffset() {
		return new double[]{gridOffsetX, gridOffsetY};
	}
	
	public void draw(int x, int y) {
		this.height = this.getHeight();
		this.width = this.getWidth();
		gridOffsetX = x; gridOffsetY = y;
		originX = width/2+x;
		originY = height/2+y;
		repaint();
	}
	
	public void draw() {
		this.height = this.getHeight();
		this.width = this.getWidth();
		originX = width/2+gridOffsetX;
		originY = height/2+gridOffsetY;
		repaint();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		this.height = this.getHeight();
		this.width = this.getWidth();
		//origin = new Point(width/2, height/2);
		g2.clearRect(0, 0, width, height);
		g2.drawLine(0, 0, width, height);
		g2.drawLine(width, 0, 0, height);
		createGrid(g2, this.width, this.height);
		g2.setColor(Color.BLUE);
		Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
		arc.setFrame(originX-5, originY-5, 10, 10);
		arc.setAngleStart(0);
		arc.setAngleExtent(360);
		g2.fill(arc);

		for (int i = 0; i < Graphs.size(); i++) {
			Graph graph = Graphs.get(i);
			for (int j = 0; j < graph.points()-1; j++) {
				double[] prevPoint = graph.getPoint(j);
				double[] point = graph.getPoint(j+1);
				double x1 = prevPoint[0] * spacingX + graphOffsetX;
				double y1 = prevPoint[1] * spacingY - graphOffsetY;
				double x2 = point[0] * spacingX + graphOffsetX;
				double y2 = point[1] * spacingY - graphOffsetY;
				//g.fillArc(origin.x + x1-5, origin.y + y1-5, 10, 10, 0, 360);
				//g.fillArc(origin.x + x2-5, origin.y + y2-5, 10, 10, 0, 360);
				g2.setStroke(new BasicStroke(2));
				g2.draw(new Line2D.Double(originX + x1, originY - y1, originX + x2, originY - y2));
				//System.out.println(prevPoint.toString());
			}
		}
	}
	
	private void createGrid(Graphics2D g, int width, int height) {
		int rows = getRows();
		int cols = getColumns();
		//System.out.println("X: " + X);
		//System.out.println("Y: " + Y);
		g.setColor(Color.BLACK);
		g.setColor(new Color(0, 0, 0, 0.3f));
		
		for (int x = -cols; x <= cols; x++) {
			int tempx = x*spacingX;
			g.draw(new Line2D.Double(originX + tempx, 0, originX + tempx, height));
		}
		
		for (int y = -rows/2; y <= rows/2; y++) { //Rows
			int tempy = y*spacingY;
			g.draw(new Line2D.Double(0, originY + tempy, width, originY + tempy));
		}
		
		//g.setColor(Color.BLUE);
		//g.fillArc(origin.x-5, origin.y-5, 10, 10, 0, 360);
	}
	
	public void normalize(double x, double y) {
		double  nX = (x/(float)spacingX) - Math.round(x/spacingX);
		double nY = (y/(float)spacingY) - Math.round(y/spacingY);
		draw((int)(nX*spacingX), (int)(nY*spacingY));
	}

	private Point moveGraphs(int offsetX, int offsetY) {
		
		
		
		return null;
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
