
package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

class DrawingPanel extends JPanel {
	private final java.util.List<Shape> shapes = new ArrayList<>();
    private Shape preview;

    private Tool currentTool;

    // Undo/Redo stacks
    private final Deque<Command> undoStack = new ArrayDeque<>();
    private final Deque<Command> redoStack = new ArrayDeque<>();

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

    // Public API
    public void setTool(Tool tool) {
        this.currentTool = tool;
    }

    public void setPreview(Shape s) {
        this.preview = s;
    }

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

    // Internal helpers
    void addShapeInternal(Shape s) {
        shapes.add(s);
        repaint();
    }

    void removeShapeInternal(Shape s) {
        shapes.remove(s);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(30, 144, 255));
        for (Shape s : shapes) {
            g2.fill(s);
            g2.setColor(Color.BLACK);
            g2.draw(s);
            g2.setColor(new Color(30, 144, 255));
        }

        // Desenha a pré-visualização tracejada
        if (preview != null) {
            float dash[] = {5.0f};
            g2.setStroke(new BasicStroke(1.2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            g2.setColor(Color.GRAY);
            g2.draw(preview);
        }

        g2.dispose();
    }
}

