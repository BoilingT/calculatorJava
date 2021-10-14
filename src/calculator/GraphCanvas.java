package calculator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.awt.geom.Arc2D.Double;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphCanvas extends JPanel{
	
	private static final long serialVersionUID = 1L;

	private int height, width;
	private int spacingY = 40, spacingX = 40;
	private double gridOffsetX = 0;
	private double gridOffsetY = 0;
	private double originOffsetX = 0;
	private double originOffsetY = 0;
	private double graphOffsetX = 0;
	private double graphOffsetY = 0;
	private ArrayList<Graph> Graphs = new ArrayList<Graph>();
	
	public GraphCanvas(int width, int height) {
		this.setSize(new Dimension(width, height));
		this.height = this.getHeight();
		this.width = this.getWidth();
		gridOffsetX = width/2;
		gridOffsetY = height/2;
		System.out.println("Width: " + this.width + " Height: " + this.height);
		System.out.println("gridOffsetX: " + gridOffsetX + " gridOffsetY: " + gridOffsetY);
	}
		
	public int getColumns() {
		return (int) (width/spacingX)*1;
	}
	
	public int getRows() {
		return (int) (height/spacingY)*1;
	}
	
	public double[] getOffset() {
		return new double[]{gridOffsetX, gridOffsetY};
	}
	
	public void draw(double x, double y) {
		if (this.isVisible()) {
			height = this.getHeight();
			width = this.getWidth();
			gridOffsetX = width/2 + x; gridOffsetY = height/2 + y;
			System.out.println("Width: " + this.width + " Height: " + this.height);
			System.out.println("gridOffsetX: " + gridOffsetX + " gridOffsetY: " + gridOffsetY);
			repaint();			
		}
	}
	
	public void draw() {
		if (this.isVisible()) {
			this.height = this.getHeight();
			this.width = this.getWidth();
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(
			    RenderingHints.KEY_ANTIALIASING,
			    RenderingHints.VALUE_ANTIALIAS_ON);

		g2.clearRect(0, 0, width, height);
		g2.drawLine(0, 0, width, height);
		g2.drawLine(width, 0, 0, height);
		createGrid(g2, this.getWidth(), this.getHeight(), this.width/spacingX, this.height/spacingY);
		g2.setColor(Color.BLUE);
		Arc2D.Double arc = new Arc2D.Double(Arc2D.PIE);
		arc.setFrame(gridOffsetX + originOffsetX-5, gridOffsetY + originOffsetY-5, 10, 10);
		arc.setAngleStart(0);
		arc.setAngleExtent(360);
		g2.fill(arc);

		for (int i = 0; i < Graphs.size(); i++) {
			Graph graph = Graphs.get(i);
			for (int j = 0; j < graph.points()-1; j++) {
				double[] prevPoint = graph.getPoint(j);
				double[] point = graph.getPoint(j+1);
				double x1 = prevPoint[0] * spacingX + graphOffsetX + gridOffsetX;
				double y1 = prevPoint[1] * spacingY - graphOffsetY - gridOffsetY;
				double x2 = point[0] * spacingX + graphOffsetX + gridOffsetX;
				double y2 = point[1] * spacingY - graphOffsetY - gridOffsetY;
				if(prevPoint[1]*spacingY - gridOffsetY > this.getHeight()/2) continue;
				if(point[1]*spacingY - gridOffsetY > this.getHeight()/2) continue;

				g2.setStroke(new BasicStroke(2));
				g2.draw(new Line2D.Double(originOffsetX + x1, originOffsetY - y1, originOffsetX + x2, originOffsetY - y2));
				//System.out.println(prevPoint.toString());
			}
		}
	}
	
	private void createGrid(Graphics2D g, double width, double height, int rows, int cols) {
		
		g.setColor(Color.BLACK);
		g.setColor(new Color(0, 0, 0, 0.3f));
		System.out.println("off: " + gridOffsetX);
		for (double x = -cols/2; x <= cols/2; x++) { //Cols
			double tempx = x*spacingX;
			//g.draw(new Line2D.Double(gridOffsetX + tempx, 0, gridOffsetX + tempx, height));
			//g.drawString(String.format("%.0f" ,x - graphOffsetX/spacingX), (float) (gridOffsetX + tempx), (float) (gridOffsetY + g.getFontMetrics().getHeight()));

		}
		
		for (double y = -rows/2; y <= rows/2; y++) { //Rows
			double tempy = (-y+gridOffsetY/spacingY)*spacingY;
			g.draw(new Line2D.Double(0, tempy, width, tempy));
			String yNumb = String.format("%.0f" ,-y + graphOffsetY/spacingY);
			if(!yNumb.equals("0")) g.drawString(yNumb, (float) (gridOffsetX + graphOffsetX - 5 - g.getFontMetrics().charsWidth(yNumb.toCharArray(), 0, yNumb.length())), (float) (gridOffsetY + tempy));
		}
		
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.black);
		//g.draw(new Line2D.Double(0, gridOffsetY, width, gridOffsetY)); //Horizontal
		//System.out.println("Graphoffsety: " + graphOffsetY);
		//g.draw(new Line2D.Double(gridOffsetX + originOffsetX, 0, gridOffsetX, height)); //Vertical
		
	}
	
		private void createGrid2(Graphics2D g, double width, double height, int rows, int cols) {
		
		g.setColor(Color.BLACK);
		g.setColor(new Color(0, 0, 0, 0.3f));
		System.out.println("off: " + gridOffsetX);
		for (double x = -cols/2; x <= cols/2; x++) { //Cols
			double tempx = x*spacingX;
			g.draw(new Line2D.Double(gridOffsetX + tempx, 0, gridOffsetX + tempx, height));
			g.drawString(String.format("%.0f" ,x - graphOffsetX/spacingX), (float) (gridOffsetX + tempx), (float) (gridOffsetY + g.getFontMetrics().getHeight()));

		}
		
		for (double y = -rows/2; y <= rows/2; y++) { //Rows
			double tempy = y*spacingY;
			g.draw(new Line2D.Double(0, gridOffsetY + tempy, width, gridOffsetY + tempy));
			String yNumb = String.format("%.0f" ,-y + graphOffsetY/spacingY);
			if(!yNumb.equals("0")) g.drawString(yNumb, (float) (gridOffsetX + graphOffsetX - 5 - g.getFontMetrics().charsWidth(yNumb.toCharArray(), 0, yNumb.length())), (float) (gridOffsetY + tempy));
		}
		
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.black);
		g.draw(new Line2D.Double(0, gridOffsetY, width, gridOffsetY)); //Horizontal
		//System.out.println("Graphoffsety: " + graphOffsetY);
		g.draw(new Line2D.Double(gridOffsetX + originOffsetX, 0, gridOffsetX, height)); //Vertical
		
	}
	
	public void normalize() {

		double travelX = width/2 - gridOffsetX - originOffsetX;
		double travelY = height/2 - gridOffsetY - originOffsetY;
		System.out.println("Travel: " + travelY/spacingY);
		//draw(travelX, travelY);
	}
	
	public void addGraph(Graph graph) {
		Graphs.add(graph);
	}
}
