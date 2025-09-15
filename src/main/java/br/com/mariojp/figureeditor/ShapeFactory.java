package br.com.mariojp.figureeditor;
import java.awt.*;


public interface ShapeFactory {
	DrawableShape create(Point start, Point end, Color fill, Color stroke);
}
