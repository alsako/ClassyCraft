package raf.dsw.classycraft.app.gui.swing.view.painters.connections;

import java.awt.*;

public class Utility {

    public  static Point[] calculateRhomboidPoints(double startX, double startY, double endX, double endY){
        Point direction = normalizedDirectionVector(startX, startY, endX, endY);
        Point p1 = new Point((int) startX, (int) startY);
        Point p3 = new Point((int) startX+direction.x, (int) startY+direction.y);
        Point middle = new Point((p1.x+p3.x)/2, (p1.y+p3.y)/2);
        Point p2 = rotate90Clockwise(p1, middle);
        Point p4 = rotate90Clockwise(p3,middle);
        Point [] points = {p1, p2, p3, p4};
        return points;
    }

    public  static Point[] calculateTrianglePoints(double startX, double startY, double endX, double endY){
        Point direction = normalizedDirectionVector(startX, startY, endX, endY);
        Point p1 = new Point((int) startX, (int) startY);
        Point p3 = new Point((int) startX+direction.x, (int) startY+direction.y);
        Point middle = new Point((p1.x+p3.x)/2, (p1.y+p3.y)/2);
        Point p2 = rotate120Clockwise(p1, middle);
        Point p4 = rotate120Counterclockwise(p1,middle);
        Point [] points = {p2,p1,p4};
        return points;
    }

    public static Point normalizedDirectionVector(double startX, double startY, double endX, double endY){
        Point direction = new Point( (int)(endX-startX), (int) (endY-startY));
        double norm = Math.sqrt(direction.x*direction.x + direction.y* direction.y);
        return new Point((int) (10*direction.x/norm), (int) (10*direction.y/norm));
    }
    public static Point rotate90Clockwise(Point a, Point b){ //rotira a oko b za 90
        return new Point(b.x-(a.y-b.y), b.y+(a.x-b.x));
    }

    private static Point rotate120Clockwise(Point a, Point b) {
        double angleInRadians = Math.toRadians(120);
        double cos120 = Math.cos(angleInRadians);
        double sin120 = Math.sin(angleInRadians);

        int rotatedX = (int) (b.x + cos120 * (a.x - b.x) - sin120 * (a.y - b.y));
        int rotatedY = (int) (b.y + sin120 * (a.x - b.x) + cos120 * (a.y - b.y));

        return new Point(rotatedX, rotatedY);
    }

    private static Point rotate120Counterclockwise(Point a, Point b) {
        double angleInRadians = Math.toRadians(-120);
        double cos120 = Math.cos(angleInRadians);
        double sin120 = Math.sin(angleInRadians);

        int rotatedX = (int) (b.x + cos120 * (a.x - b.x) - sin120 * (a.y - b.y));
        int rotatedY = (int) (b.y + sin120 * (a.x - b.x) + cos120 * (a.y - b.y));

        return new Point(rotatedX, rotatedY);
    }

    public static Point translationVector(Point a, Point b){ //a->b
        double initialX = a.x;
        double initialY = a.y;
        double finalX = b.x;
        double finalY = b.y;
        int deltaX = Math.abs(a.x-b.x);
        int deltaY = Math.abs(a.y-b.y);

        if (finalX>=initialX && finalY>=initialY){
            return new Point(deltaX, deltaY);
        } else if (finalX>=initialX && finalY<=initialY) {
            return new Point(deltaX, -deltaY);
        } else if (finalX<=initialX && finalY<=initialY) {
            return new Point(-deltaX, -deltaY);
        }else{
            return new Point(-deltaX, deltaY);
        }
    }
}
