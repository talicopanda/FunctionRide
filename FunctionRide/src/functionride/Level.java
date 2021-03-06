/*
 * Tales, Sergio, Sukhraj
 * May 28th 2019 
 * template for a level type object
 */
package functionride;

import functionride.FunctionRide.STATE;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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
    private double xStartPoint;
    private double yStartPoint;
    private double xEndPoint;
    private double yEndPoint;
    private Rectangle[] obstacles;

    private Point startPoint;
    private Point endPoint;
    private Player p;
    private boolean intersectSp;

    private boolean firstRun;
    private boolean crash;
    private static boolean runBtn = false;
    public static String function;

    List<Point> graphPoints;
    private static int dataPoint = 0;
    List<double[]> areas;

    //function area variables
    private int maxDataPoints = 500;
    private double xMin = -10;
    private double xMax = 10;
    private double yMin = -10;
    private double yMax = 10;

    private int pointSize = 10;

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
    private int numberYDivisions = (int) (yMax - yMin);
    double xScale = ((double) funcAreaWidth - (2 * padding) - labelPadding) / (xMax - xMin);
    double yScale = ((double) funcAreaHeight - 2 * padding - labelPadding) / (yMax - yMin);
    private List<Double> scores;
    //font for printing information
    public static Font btnFont = new Font("arial", Font.PLAIN, 20);

    /**
     * primary and only constructor for a level with all attributes
     *
     * @param xsp x value of starting point
     * @param ysp y value of starting point
     * @param xep x value of end point
     * @param yep y value of end point
     * @param obstacles an array of obstacles
     * @param p a player
     */
    public Level(double xsp, double ysp, double xep, double yep, Rectangle[] obstacles, Player p) {
        xStartPoint = xsp;
        yStartPoint = ysp;
        xEndPoint = xep;
        yEndPoint = yep;
        this.obstacles = obstacles;
        function = null;
        startPoint = coordTranslation(xStartPoint, yStartPoint);
        endPoint = coordTranslation(xEndPoint, yEndPoint);
        this.p = p;
        intersectSp = false;
        firstRun = true;
        areas = new ArrayList<>();
        for (int i = 0; i < obstacles.length; i++) {
            areas.add(obstacles[i].getCollisionArea());
        }
        crash = false;
    }

    /**
     * set whether or not the run button has been clicked
     *
     * @param bool the boolean we want to change to
     */
    public static void setRunBtn(boolean bool) {
        runBtn = bool;
    }

    /**
     * set the function that will be drawn
     *
     * @param func the function as a string
     */
    public static void setFunction(String func) {
        function = func;
    }

    /**
     * test to see if the function works
     *
     * @param func the function
     */
    public void testFunction(String func) {
        Expression e = new ExpressionBuilder(func)
                .variables("x")
                .build()
                .setVariable("x", xStartPoint);
        double yValue = e.evaluate();
        //check whether or not the function intersects the starting point
        if (yStartPoint != Math.round(yValue * 100000.0) / 100000.0) { //rounded to 5 decimals places due to innacuracy of PI
            intersectSp = false;
        } else {
            intersectSp = true;
        }
    }

    /**
     * draws the starting platform
     *
     * @param g the drawing utensil
     */
    public void drawStart(Graphics2D g2d) {

        g2d.setColor(Color.GREEN);
        g2d.fillOval(startPoint.x - pointSize / 2, startPoint.y - pointSize / 2, pointSize, pointSize);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(startPoint.x - pointSize / 2, startPoint.y - pointSize / 2, pointSize, pointSize);
    }

    /**
     * draws the ending platform
     *
     * @param g the drawing utensil
     */
    public void drawEnd(Graphics2D g2d) {

        g2d.setColor(Color.RED);
        g2d.fillOval(endPoint.x - pointSize / 2, endPoint.y - pointSize / 2, pointSize, pointSize);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(endPoint.x - pointSize / 2, endPoint.y - pointSize / 2, pointSize, pointSize);
    }

    /**
     * draws the graphing area
     *
     * @param g2d the drawing utensil
     */
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
            int x0 = padding + (funcAreaWidth - (2 * padding) - labelPadding - padding) / 2;
            int x1 = pointWidth + padding + (-padding + funcAreaWidth - (2 * padding) - labelPadding) / 2;
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
                int x0 = i * (funcAreaWidth - padding * 2 - labelPadding) / (int) (xMax - xMin) + padding + labelPadding;
                int x1 = x0;
                int y0 = padding + (funcAreaHeight - padding - labelPadding) / 2;
                int y1 = y0 - pointWidth;
                if ((i % ((int) (((xMax - xMin) / 40.0)) + 1)) == 0) {
                    g2d.setColor(gridColor);
                    g2d.drawLine(x0, funcAreaHeight - padding - labelPadding - 1 - pointWidth, x1, padding);
                    g2d.setColor(Color.BLACK);
                    String xLabel = (int) xCurrent + "";
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
        g2d.drawLine(padding + (funcAreaWidth - (2 * padding) - labelPadding) / 2, funcAreaHeight - padding - labelPadding, padding + (funcAreaWidth - (2 * padding) - labelPadding) / 2, padding);
        g2d.drawLine(padding + labelPadding, (funcAreaHeight - labelPadding) / 2, funcAreaWidth - padding, (funcAreaHeight - labelPadding) / 2);

    }

    /**
     * get a list of the points the function goes through
     *
     * @param function the function
     * @return a list of all the points
     */
    public List<Point> getGraphPoints(String function) {
        try {
            List<Point> graphPoints = new ArrayList<>();
            double xValue = xMin;
            double step = (xMax - xMin) / maxDataPoints;
            for (int i = 0; i < maxDataPoints; i++) { //finds value of everyh point in graph
                Expression e = new ExpressionBuilder(function)
                        .variables("x")
                        .build()
                        .setVariable("x", xValue);
                double yValue = e.evaluate();
                if (xValue >= xStartPoint && xValue <= xEndPoint && yValue <= yMax && yValue >= yMin) {
                    graphPoints.add(coordTranslation(xValue, yValue));
                }
                xValue += step;
            }
            return graphPoints;
        } catch (Exception e) {
            System.out.println("Oops! There is a discontinuity in the rollercoaster. Try again!");
        }
        return null;
    }

    /**
     * check whether or not the function the user entered completes the level
     *
     * @return true or false depending on if the function worked
     */
    public boolean checkCompletion() {
        //add tolerance value of 1 pixel to any direction
        if (p.getX() + p.SIZE / 2 >= endPoint.x - 5 && p.getX() + p.SIZE / 2 <= endPoint.x + 5 && p.getY() + p.SIZE >= endPoint.y - 5 && p.getY() + p.SIZE <= endPoint.y + 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * draws all components of the level
     *
     * @param g the drawing utensil
     */
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (firstRun) {
            //move player to starting point
            p.updatePos(startPoint.x - p.SIZE / 2, startPoint.y - p.SIZE);
        }
        g2d.setColor(new Color(198, 168, 103));
        g2d.fillRect(0, 0, FunctionRide.WIDTH + 32, FunctionRide.HEIGHT + 32);
        drawFuncArea(g2d);
        drawStart(g2d);
        drawEnd(g2d);
        //draw all of the obstacles in the level
        for (int i = 0; i < obstacles.length; i++) {
            Point pos = coordTranslation(obstacles[i].getX(), obstacles[i].getY());
            Point size = new Point((int) (xScale * obstacles[i].getWidth()), (int) (yScale * obstacles[i].getHeight()));
            obstacles[i].draw(g2d, pos.x, pos.y, size.x, size.y);
        }
        drawInfoBreakdown(g2d);
        drawButtons(g2d, btnFont);
        //test the function when user clicks the "play" button
        if (runBtn && function != null) {
            firstRun = false;
            crash = false;
            drawFunction(g2d, function);
            drawStart(g2d);
            drawEnd(g2d);
            testFunction(function);
            //only continue if the function goes through the starting point
            if (intersectSp) {
                if (dataPoint < graphPoints.size() - 1) {
                    int x = graphPoints.get(dataPoint).x - pointWidth / 2;
                    int y = graphPoints.get(dataPoint).y - pointWidth / 2;
                    boolean collided = checkCollision(x, y);
                    dataPoint++;
                    //if player doesn't hit anything then they have finished the level
                    if (!collided) {
                        p.updatePos(x - p.SIZE / 2, y - p.SIZE);
                        if (checkCompletion()) {
                            boolean notAdded = true;
                            for (int levels : FunctionRide.levelsCompleted) {
                                if (levels == FunctionRide.preLevel) {
                                    notAdded = false;
                                }
                            }
                            if (notAdded) {
                                FunctionRide.levelsCompleted.add(FunctionRide.preLevel);
                            }
                            //change state to level completed screen
                            FunctionRide.State = STATE.COMPLETED_SCREEN;

                        }
                        //if they hit an obstacle
                    } else {
                        crash = true;
                        dataPoint = graphPoints.size() - 1;
                        //move player back to start
                        p.updatePos(startPoint.x - p.SIZE / 2, startPoint.y - p.SIZE);
                    }
                }
                //function doesn't go through start point
            } else {
                drawInfo(g, "This function does not go through the starting point!", btnFont, 25, FunctionRide.HEIGHT - 20);
            }
            if (dataPoint >= graphPoints.size() - 1) {
                runBtn = false;
            }
        }
        if (crash) {
            drawInfo(g, "Crash! Try again.", btnFont, 10, FunctionRide.HEIGHT - 20);
        }
        //draw player

        p.render(g2d);

    }

    /**
     * print messages to the screen
     *
     * @param g drawing utensil
     * @param msg the message to display
     * @param font the font to draw in
     * @param x x location
     * @param y y location
     */
    public static void drawInfo(Graphics g, String msg, Font font, int x, int y) {
        g.setFont(font);
        g.drawString(msg, x, y);
    }

    /**
     * check whether or not player hits an obstacle
     *
     * @param x1
     * @param y1
     * @return true or false depending on if they hit something or not
     */
    public boolean checkCollision(int x1, int y1) {
        for (double[] area : areas) {
            Point initialRange = coordTranslation(area[0], area[2]);
            Point finalRange = coordTranslation(area[1], area[3]);
            //if collides
            if (x1 >= initialRange.x && x1 <= finalRange.x && y1 >= initialRange.y && y1 <= finalRange.y) {
                return true;
            }
        }
        return false;
    }

    /**
     * draw all of the buttons on the level
     *
     * @param g2d drawing utensil
     * @param font font to draw text in
     */
    public void drawButtons(Graphics2D g2d, Font font) {
        //offsets for centering text in buttons
        int xOff = 25;
        int yOff = 28;
        g2d.setFont(font);
        g2d.setColor(Color.white);
        //draw butons
        g2d.fillRoundRect(FunctionRide.WIDTH / 2 - 140, FunctionRide.HEIGHT / 2 + 200, 150, 40, 20, 20);
        g2d.fillRoundRect(FunctionRide.WIDTH / 2 - 470, FunctionRide.HEIGHT / 2 + 200, 150, 40, 20, 20);
        g2d.fillRoundRect(FunctionRide.WIDTH / 2 - 305, FunctionRide.HEIGHT / 2 + 200, 150, 40, 20, 20);
        g2d.setColor(Color.black);
        g2d.drawString("Run Function", FunctionRide.WIDTH / 2 - 141 + xOff, FunctionRide.HEIGHT / 2 + 200 + yOff);
        g2d.drawString("Menu", FunctionRide.WIDTH / 2 - 450 + xOff, FunctionRide.HEIGHT / 2 + 200 + yOff);
        g2d.drawString("Level Select", FunctionRide.WIDTH / 2 - 305 + xOff, FunctionRide.HEIGHT / 2 + 200 + yOff);

    }

    public static void runBtn() {
        function = functionmaker.func;
        runBtn = true;
        dataPoint = 0;
    }

    /**
     * draw information about level
     *
     * @param g2d drawing utensil
     */
    public void drawInfoBreakdown(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        Font helvetica = new Font("Helvetica", Font.BOLD, 19);
        g2d.setFont(helvetica);
        //variable to keep track of x location of text
        int textX = FunctionRide.WIDTH - 425;
        g2d.drawString("INFORMATION BREAKDOWN", FunctionRide.WIDTH - 350, 50);
        Font info = new Font("Helvetica", Font.PLAIN, 19);
        g2d.setFont(info);
        g2d.drawString("Starting Point: (" + xStartPoint + ", " + yStartPoint + ")", textX, 100);
        g2d.drawString("Ending Point: (" + xEndPoint + ", " + yEndPoint + ")", textX, 120);
        //ranges of the obstacles
        int textPadding = 20;
        for (int i = 0; i < areas.size(); i++) {
            //range x 
            g2d.drawString("X Values of Obstacle " + (i + 1) + ": " + areas.get(i)[0] + " to " + areas.get(i)[1], textX, 120 + textPadding + (i + 1) * textPadding);
            g2d.drawString("Y Values of Obstacle " + (i + 1) + ": " + areas.get(i)[3] + " to " + areas.get(i)[2], textX, 170 + textPadding + (i + 1) * textPadding);

        }

    }

    /**
     * convert x and y coordinates to graph coordinates
     *
     * @param x x value
     * @param y y value
     * @return a point on the graph
     */
    public Point coordTranslation(double x, double y) {
        int finalx = (int) Math.round((padding + (funcAreaWidth - (2 * padding) - labelPadding) / 2) + xScale * x);
        int finaly = (int) Math.round((funcAreaHeight - labelPadding) / 2 + yScale * y * -1);
        return new Point(finalx, finaly);
    }

    /**
     * draw the function the user enters
     *
     * @param g2d drawing utensil
     * @param function the function to draw
     */
    public void drawFunction(Graphics2D g2d, String function) {
        graphPoints = getGraphPoints(function);
        Stroke oldStroke = g2d.getStroke();
        g2d.setColor(lineColor);
        g2d.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) { //loops through graph points
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2d.drawLine(x1, y1, x2, y2);
        }
        g2d.setStroke(oldStroke);
        g2d.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) { //loops through graph points
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
        Level l2 = new Level(xStartPoint, yStartPoint, xEndPoint, yEndPoint, obstacles, p);
        return l2;
    }

    public String toString() {
        return "LEVEL:\nStart Point: " + xStartPoint + "," + yStartPoint + "\nEnd Point: " + +xEndPoint + "," + yEndPoint
                + "\nObstacles: " + obstacles;
    }
}
