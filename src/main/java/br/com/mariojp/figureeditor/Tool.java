package br.com.mariojp.figureeditor;
import java.awt.event.*;

public interface Tool {
    void mousePressed(MouseEvent e, DrawingPanel panel);
    void mouseReleased(MouseEvent e, DrawingPanel panel);
    void mouseDragged(MouseEvent e, DrawingPanel panel);
}
