package br.com.mariojp.figureeditor;

import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawTool implements Tool {
	
	private final ShapeFactory factory;
    private Point start;
    private DrawableShape preview;
    private final Color fill;
    private final Color stroke;

    public DrawTool(ShapeFactory factory, Color fill, Color stroke) {
        this.factory = factory;
        this.fill = fill;
        this.stroke = stroke;
    }

    @Override
    public void mousePressed(MouseEvent e, DrawingPanel panel) {
        start = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e, DrawingPanel panel) {
        if (start != null) {
        	DrawableShape s = factory.create(start, e.getPoint(), fill, stroke);
            panel.executeCommand(new AddShapeCommand(panel, s));
            preview = null;
            panel.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, DrawingPanel panel) {
        if (start != null) {
            preview = factory.create(start, e.getPoint(), fill, stroke);
            panel.setPreview(preview);
            panel.repaint();
        }
    }
}