package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            JFrame frame = new JFrame("Figure Editor");
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
            JButton selectBtn = new JButton("Mover");
            JButton exportPngBtn = new JButton("Exportar PNG");
            JButton exportSvgBtn = new JButton("Exportar SVG");

            ellipseBtn.addActionListener(e ->
	            panel.setTool(new DrawTool(new EllipseFactory(), currentFill[0], currentStroke[0]))
	        );
	        rectBtn.addActionListener(e ->
	            panel.setTool(new DrawTool(new RectangleFactory(), currentFill[0], currentStroke[0]))
	        );
	        selectBtn.addActionListener(e ->
            	panel.setTool(new SelectMoveTool())
    		);
            undoBtn.addActionListener(e -> panel.undo());
            redoBtn.addActionListener(e -> panel.redo());
           
            colorBtn.addActionListener(e -> {
                Color chosen = JColorChooser.showDialog(frame, "Escolher cor de preenchimento", currentFill[0]);
                if (chosen != null) {
                    currentFill[0] = chosen;
                }
            });
            
            exportPngBtn.addActionListener(e -> {
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();

                    if (!file.getName().toLowerCase().endsWith(".png")) {
                        file = new File(file.getAbsolutePath() + ".png");
                    }

                    try {
                        new PngExporter().export(panel.getShapes(), panel.getSize(), file);
                        JOptionPane.showMessageDialog(frame, 
                            "Exportado como PNG: " + file.getName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage());
                    }
                }
            });

            exportSvgBtn.addActionListener(e -> {
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();

                    if (!file.getName().toLowerCase().endsWith(".svg")) {
                        file = new File(file.getAbsolutePath() + ".svg");
                    }

                    try {
                        new SvgExporter().export(panel.getShapes(), panel.getSize(), file);
                        JOptionPane.showMessageDialog(frame, 
                            "Exportado como SVG: " + file.getName());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage());
                    }
                }
            });
            
            toolbar.add(ellipseBtn);
            toolbar.add(rectBtn);
            toolbar.addSeparator();
            toolbar.add(selectBtn);
            toolbar.addSeparator();
            toolbar.add(colorBtn);
            toolbar.addSeparator();
            toolbar.add(undoBtn);
            toolbar.add(redoBtn);
            toolbar.addSeparator();
            toolbar.add(exportPngBtn);
            toolbar.add(exportSvgBtn);

            frame.setLayout(new BorderLayout());
            frame.add(toolbar, BorderLayout.NORTH);
            frame.add(panel, BorderLayout.CENTER);

            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            
        });
    }
}


