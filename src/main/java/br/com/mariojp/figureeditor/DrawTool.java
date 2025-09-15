package br.com.mariojp.figureeditor;

import java.awt.*;
import java.awt.event.MouseEvent;


public class DrawTool implements Tool {
	
	private final ShapeFactory factory;
    private Point start;
    private Shape preview;

    public DrawTool(ShapeFactory factory) {
        this.factory = factory;
    }

    @Override
    public void mousePressed(MouseEvent e, DrawingPanel panel) {
        start = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e, DrawingPanel panel) {
        if (start != null) {
            Shape s = factory.create(start, e.getPoint());
            panel.executeCommand(new AddShapeCommand(panel, s));
            preview = null;
            panel.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, DrawingPanel panel) {
        if (start != null) {
            preview = factory.create(start, e.getPoint());
            panel.setPreview(preview);
            panel.repaint();
        }
    }
}