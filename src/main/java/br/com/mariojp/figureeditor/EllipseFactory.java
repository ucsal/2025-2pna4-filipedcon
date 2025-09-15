package br.com.mariojp.figureeditor;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.*;

public class EllipseFactory implements ShapeFactory {
	@Override
	public DrawableShape create(Point start, Point end, Color fill, Color stroke) {
        int w = Math.abs(end.x - start.x);
        int h = Math.abs(end.y - start.y);
        Shape s = new Ellipse2D.Double(Math.min(start.x, end.x), Math.min(start.y, end.y), w, h);
        return new DrawableShape(s, fill, stroke);
    }
}
