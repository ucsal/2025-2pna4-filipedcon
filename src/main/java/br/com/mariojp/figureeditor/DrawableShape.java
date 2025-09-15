package br.com.mariojp.figureeditor;

import java.awt.*;

public class DrawableShape {
	private final Shape shape;
    private final Color fill;
    private final Color stroke;

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
    }

    public Shape getShape() {
        return shape;
    }
}

