package br.com.mariojp.figureeditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class PngExporter implements Exporter {
	@Override
    public void export(List<DrawableShape> shapes, Dimension size, File file) throws Exception {
        BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, size.width, size.height);

        for (DrawableShape s : shapes) {
            s.draw(g2);
        }

        g2.dispose();
        ImageIO.write(img, "png", file);
    }
}
