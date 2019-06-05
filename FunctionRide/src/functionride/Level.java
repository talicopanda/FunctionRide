/*
 * Sukhraj Garcha
 * May 28th 2019 
 * template for a level type object
 */
package functionride;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Level {

    //attributes 
    private Point startPoint;
    private Point endPoint;
    private AbstractObstacle[] obstacles;
    private String function;

    //function area variables
    private int maxDataPoints = 500;
    private double xMin = -10;
    private double xMax = 10;
    private double yMin = -10;
    private double yMax = 10;

    private final int funcAreaWidth = 500;
    private final int funcAreaHeight = 500;
    private final int padding = 10;
    private final int labelPadding = 0;
    private final int hatchSize = 5;
    private Color lineColor = new Color(44, 102, 230, 180);
    private Color pointColor = new Color(100, 100, 100, 180);
    private Color gridColor = new Color(200, 200, 200, 200);
    private static final Stroke GRAPH_STROKE = new BasicStroke(5f);
    private int pointWidth = 1;
    private int numberYDivisions = (int)(yMax - yMin);
    double xScale = ((double) funcAreaWidth - (2 * padding) - labelPadding) / (xMax - xMin);
    double yScale = ((double) funcAreaHeight - 2 * padding - labelPadding) / (yMax - yMin);
    private List<Double> scores;

    /**
     * constructor for a new level with all attributes
     *
     * @param startPoint the position of the starting platform
     * @param endPoint the position of the ending platform
     * @param obstacles an array of all obstacles in the level
     */
    public Level(Point startPoint, Point endPoint, AbstractObstacle[] obstacles) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.obstacles = obstacles;
        function = null;
    }
    
    public void setFunction(String func){
        function = func;
    }
    
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Point getendPoint() {
        return endPoint;
    }

    /**
     * draws the starting platform
     *
     * @param g the drawing utensil
     */
    public void drawStart(Graphics2D g2d) {
        g2d.drawOval((int) startPoint.getX(), (int) startPoint.getY(), 10, 10);
    }

    /**
     * draws the ending platform
     *
     * @param g the drawing utensil
     */
    public void drawEnd(Graphics2D g2d) {
        g2d.drawOval((int) endPoint.getX(), (int) endPoint.getY(), 10, 10);
    }

    public boolean checkCollision(Player p, AbstractObstacle[] obstacles) {
        return false;
    }

    public void drawFuncArea(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //double xScale = ((double) funcAreaWidth - (2 * padding) - labelPadding) / (maxDataPoints - 1);
        //double yScale = ((double) funcAreaHeight - 2 * padding - labelPadding) / (yMax - yMin);
        

        // draw white background
        g2d.setColor(Color.WHITE);
        g2d.fillRect(padding + labelPadding, padding, funcAreaWidth - (2 * padding) - labelPadding, funcAreaHeight - 2 * padding - labelPadding);
        g2d.setColor(Color.BLACK);

        // create hatch marks and grid lines for y axis.
        for (int i = 0; i < numberYDivisions + 1; i++) {
            int x0 = padding + (funcAreaWidth - (2 * padding) - labelPadding - padding)/2;
            int x1 = pointWidth + padding + (- padding + funcAreaWidth - (2 * padding) - labelPadding)/2;
            int y0 = funcAreaHeight - ((i * (funcAreaHeight - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
            int y1 = y0;
            //grid lines
            if (maxDataPoints > 0) {
                g2d.setColor(gridColor);
                g2d.drawLine(padding + labelPadding + 1 + pointWidth, y0, funcAreaWidth - padding, y1);
                g2d.setColor(Color.BLACK);
                String yLabel = ((int) ((yMin + (yMax - yMin) * ((i * 1.0) / numberYDivisions)) * 100)) / 100 + "";
                FontMetrics metrics = g2d.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2d.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            //hatch marks
            g2d.drawLine(x0, y0, x1 + hatchSize, y1);
        }
        // and for x axis
        double xCurrent = xMin;
        for (int i = 0; i <= (xMax - xMin); i++) {
            if ((yMax - xMin) > 1) {
                int x0 = i * (funcAreaWidth - padding * 2 - labelPadding) / (int)(xMax - xMin) + padding + labelPadding;
                int x1 = x0;
                int y0 = padding + (funcAreaHeight - padding - labelPadding)/2;
                int y1 = y0 - pointWidth;
                if ((i % ((int) (((xMax - xMin) / 20.0)) + 1)) == 0) {
                    g2d.setColor(gridColor);
                    g2d.drawLine(x0, funcAreaHeight - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2d.setColor(Color.BLACK);
                    String xLabel = (int)xCurrent + "";
                    FontMetrics metrics = g2d.getFontMetrics();
                    int labelWidth = metrics.stringWidth(xLabel);
                    g2d.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
                }
                //hatch marks
                g2d.drawLine(x0, y0, x1, y1 - hatchSize);
            }
            xCurrent++;
        }

        // create x and y axes 
        g2d.drawLine(padding + (funcAreaWidth - (2 * padding) - labelPadding)/2, funcAreaHeight - padding - labelPadding, padding + (funcAreaWidth - (2 * padding) - labelPadding)/2, padding);
        g2d.drawLine(padding + labelPadding,(funcAreaHeight - labelPadding)/2, funcAreaWidth - padding, (funcAreaHeight - labelPadding)/2);

    }

    public List<Point> getGraphPoints(String function) {
        try {
            List<Point> graphPoints = new ArrayList<>();
            double xValue = xMin;
            double step = (xMax - xMin) / maxDataPoints;
            for (int i = 0; i < maxDataPoints; i++) {
                Expression e = new ExpressionBuilder(function)
                    .variables("x")
                    .build()
                    .setVariable("x", xValue);
                double yValue = e.evaluate();
                if(xValue >= xMin && xValue <= xMax && yValue <= yMax && yValue >= yMin){
                    graphPoints.add(coordTranslation(xValue, yValue));
                }
                xValue += step;
            }
            return graphPoints;
        } catch (Exception e) {
            System.out.println("Ops, there is a discontinuity in the rollercoaster. Try again!");
        }
        return null;
    }

    /**
     * draws all components of the level
     *
     * @param g the drawing utensil
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, FunctionRide.WIDTH + 32, FunctionRide.HEIGHT + 32);
        drawFuncArea(g2d);
        drawStart(g2d);
        drawEnd(g2d);
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i].draw(g2d);
        }
        setFunction("sin(x)");
        if(function != null){
            drawFunction(g2d, function);
        }
    }

    public Point coordTranslation(double x, double y) {
        int finalx = (int)Math.round((padding + (funcAreaWidth - (2 * padding) - labelPadding)/2) + xScale*x);
        int finaly = (int)Math.round((funcAreaHeight - labelPadding)/2 + yScale*y*-1);
        return new Point(finalx, finaly);
    }
    
    public void drawFunction(Graphics2D g2d, String function) {
        List<Point> graphPoints = getGraphPoints(function);
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(lineColor);
        g2d.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2d.drawLine(x1, y1, x2, y2);
        }
        g2d.setStroke(oldStroke);
        g2d.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int ovalW = pointWidth;
            int ovalH = pointWidth;
            g2d.fillOval(x, y, ovalW, ovalH);
        }
    }

    /**
     * makes an exact copy of a level
     *
     * @return the copied version of a level
     */
    public Level clone() {
        Level l2 = new Level(startPoint, endPoint, obstacles);
        return l2;
    }

    public String toString() {
        return "LEVEL:\nStart Point: " + startPoint + "\nEnd Point: " + endPoint
                + "\nObstacles: " + obstacles;
    }
}
