package br.com.mariojp.figureeditor;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class RectangleFactory implements ShapeFactory{
    @Override
    public Shape create(Point start, Point end) {
        int w = Math.abs(end.x - start.x);
        int h = Math.abs(end.y - start.y);
        return new Rectangle2D.Double(Math.min(start.x, end.x), Math.min(start.y, end.y), w, h);
    }
}
