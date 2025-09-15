package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor — Factory + Tool + Undo/Redo");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();

            JToolBar toolbar = new JToolBar();
            JButton ellipseBtn = new JButton("Ellipse");
            JButton rectBtn = new JButton("Rectangle");
            JButton undoBtn = new JButton("Desfazer");
            JButton redoBtn = new JButton("Refazer");

            ellipseBtn.addActionListener(e -> panel.setTool(new DrawTool(new EllipseFactory())));
            rectBtn.addActionListener(e -> panel.setTool(new DrawTool(new RectangleFactory())));
            undoBtn.addActionListener(e -> panel.undo());
            redoBtn.addActionListener(e -> panel.redo());

            toolbar.add(ellipseBtn);
            toolbar.add(rectBtn);
            toolbar.addSeparator();
            toolbar.add(undoBtn);
            toolbar.add(redoBtn);

            frame.setLayout(new BorderLayout());
            frame.add(toolbar, BorderLayout.NORTH);
            frame.add(panel, BorderLayout.CENTER);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}

//Ideias de evolução
//
//Clique + Arraste: define o tamanho (pré-visualização tracejada).
//Botão "Cor..."** escolhe a cor.
//Seleção/movimentação de figuras
//Camadas e alinhamento magnético
//Exportar PNG/SVG
//Undo/Redo (Memento + Command)
