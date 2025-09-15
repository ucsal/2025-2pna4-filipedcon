package br.com.mariojp.figureeditor;

import java.awt.Point;
import java.awt.event.MouseEvent;

public class SelectMoveTool implements Tool {
	
	private DrawableShape selectedShape;
    private Point lastPoint;
    private int totalDx, totalDy;

    @Override
    public void mousePressed(MouseEvent e, DrawingPanel panel) {
        Point p = e.getPoint();
        selectedShape = panel.findShapeAt(p);
        panel.clearSelection();
        if (selectedShape != null) {
            selectedShape.setSelected(true);
            lastPoint = p;
            totalDx = totalDy = 0;
        }
        panel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e, DrawingPanel panel) {
        if (selectedShape != null && lastPoint != null) {
            int dx = e.getX() - lastPoint.x;
            int dy = e.getY() - lastPoint.y;
            selectedShape.move(dx, dy);
            totalDx += dx;
            totalDy += dy;
            lastPoint = e.getPoint();
            panel.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e, DrawingPanel panel) {
        if (selectedShape != null && (totalDx != 0 || totalDy != 0)) {
            panel.executeCommand(new MoveShapeCommand(selectedShape, totalDx, totalDy));
        }
        selectedShape = null;
        lastPoint = null;
    }
}
