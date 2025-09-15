package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor — Cor + Factory + Tool + Undo/Redo");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DrawingPanel panel = new DrawingPanel();
            
            final Color[] currentFill = {new Color(30, 144, 255)};
            final Color[] currentStroke = {Color.BLACK};
            
            JToolBar toolbar = new JToolBar();
            JButton ellipseBtn = new JButton("Ellipse");
            JButton rectBtn = new JButton("Rectangle");
            JButton undoBtn = new JButton("Desfazer");
            JButton redoBtn = new JButton("Refazer");
            JButton colorBtn = new JButton("Cor...");

            ellipseBtn.addActionListener(e ->
	            panel.setTool(new DrawTool(new EllipseFactory(), currentFill[0], currentStroke[0]))
	        );
	        rectBtn.addActionListener(e ->
	            panel.setTool(new DrawTool(new RectangleFactory(), currentFill[0], currentStroke[0]))
	        );
            undoBtn.addActionListener(e -> panel.undo());
            redoBtn.addActionListener(e -> panel.redo());
           
            colorBtn.addActionListener(e -> {
                Color chosen = JColorChooser.showDialog(frame, "Escolher cor de preenchimento", currentFill[0]);
                if (chosen != null) {
                    currentFill[0] = chosen;
                }
            });
            
            toolbar.add(ellipseBtn);
            toolbar.add(rectBtn);
            toolbar.addSeparator();
            toolbar.add(colorBtn);
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
