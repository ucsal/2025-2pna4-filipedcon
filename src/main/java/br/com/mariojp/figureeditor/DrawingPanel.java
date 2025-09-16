
package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.ListIterator;

public class DrawingPanel extends JPanel {
	private final List<DrawableShape> shapes = new ArrayList<>();
    private DrawableShape preview;
    
    private Tool currentTool;

    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

    private Color currentFill = new Color(30, 144, 255);
    private Color currentStroke = Color.BLACK;

    DrawingPanel() {
        setBackground(Color.WHITE);
        setOpaque(true);

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                if (currentTool != null) currentTool.mousePressed(e, DrawingPanel.this);
            }

            @Override public void mouseReleased(MouseEvent e) {
                if (currentTool != null) currentTool.mouseReleased(e, DrawingPanel.this);
            }

            @Override public void mouseDragged(MouseEvent e) {
                if (currentTool != null) currentTool.mouseDragged(e, DrawingPanel.this);
            }
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void setTool(Tool tool) {
        this.currentTool = tool;
    }

    public void setPreview(DrawableShape s) {
        this.preview = s;
    }
    
    public List<DrawableShape> getShapes() { return shapes; }

    public void executeCommand(Command cmd) {
        cmd.execute();
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
            repaint();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
            repaint();
        }
    }

    void addShapeInternal(DrawableShape s) {
        shapes.add(s);
        repaint();
    }

    void removeShapeInternal(DrawableShape s) {
        shapes.remove(s);
        repaint();
    }
    
    public Color getCurrentFill() { return currentFill; }
    public Color getCurrentStroke() { return currentStroke; }
    public void setCurrentFill(Color c) { this.currentFill = c; }
    public void setCurrentStroke(Color c) { this.currentStroke = c; }
    
    public DrawableShape findShapeAt(Point p) {
        ListIterator<DrawableShape> it = shapes.listIterator(shapes.size());
        while (it.hasPrevious()) {
            DrawableShape s = it.previous();
            if (s.contains(p)) return s;
        }
        return null;
    }
    
    public void clearSelection() {
        for (DrawableShape s : shapes) s.setSelected(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (DrawableShape s : shapes) {
            s.draw(g2);
        }

        if (preview != null) {
            Stroke old = g2.getStroke();
            float dash[] = {5.0f};
            g2.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            preview.draw(g2);
            g2.setStroke(old);
        }

        g2.dispose();
    }
}

