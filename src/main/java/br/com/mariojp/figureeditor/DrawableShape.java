package br.com.mariojp.figureeditor;

import java.awt.*;
import java.awt.geom.RectangularShape;

public class DrawableShape {
	private final Shape shape;
    private final Color fill;
    private final Color stroke;
    private boolean selected = false;
    
    public DrawableShape(Shape shape, Color fill, Color stroke) {
        this.shape = shape;
        this.fill = fill;
        this.stroke = stroke;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(fill);
        g2.fill(shape);
        g2.setColor(stroke);
        g2.draw(shape);
        
        if (selected) {
            Stroke old = g2.getStroke();
            float dash[] = {5.0f};
            g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            g2.setColor(Color.RED);
            g2.draw(shape.getBounds2D());
            g2.setStroke(old);
        }
    }

    public boolean contains(Point p) {
        return shape.contains(p);
    }

    public void move(int dx, int dy) {
        if (shape instanceof RectangularShape rs) {
            rs.setFrame(rs.getX() + dx, rs.getY() + dy, rs.getWidth(), rs.getHeight());
        }
    }

    public Shape getShape() {
        return shape;
    }

    public void setSelected(boolean sel) {
        this.selected = sel;
    }

    public boolean isSelected() {
        return selected;
    }
}

