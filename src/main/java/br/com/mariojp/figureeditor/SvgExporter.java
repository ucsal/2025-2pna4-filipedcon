package br.com.mariojp.figureeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class SvgExporter implements Exporter{
	@Override
    public void export(List<DrawableShape> shapes, Dimension size, File file) throws Exception {
        try (PrintWriter out = new PrintWriter(file)) {
            out.printf(
                "<svg xmlns='http://www.w3.org/2000/svg' width='%d' height='%d'>%n",
                size.width, size.height
            );

            for (DrawableShape ds : shapes) {
                Shape s = ds.getShape();
                if (s instanceof Ellipse2D) {
                    Ellipse2D e = (Ellipse2D) s;
                    out.printf(
                        "<ellipse cx='%f' cy='%f' rx='%f' ry='%f' fill='%s' stroke='%s' />%n",
                        e.getCenterX(), e.getCenterY(), e.getWidth() / 2, e.getHeight() / 2,
                        colorToHex(ds.getFill()), colorToHex(ds.getStroke())
                    );
                } else if (s instanceof Rectangle2D) {
                    Rectangle2D r = (Rectangle2D) s;
                    out.printf(
                        "<rect x='%f' y='%f' width='%f' height='%f' fill='%s' stroke='%s' />%n",
                        r.getX(), r.getY(), r.getWidth(), r.getHeight(),
                        colorToHex(ds.getFill()), colorToHex(ds.getStroke())
                    );
                }
            }

            out.println("</svg>");
        }
    }

    private String colorToHex(Color c) {
        return String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
    }
}
