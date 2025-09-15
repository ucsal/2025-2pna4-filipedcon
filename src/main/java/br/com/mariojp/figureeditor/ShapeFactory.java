package br.com.mariojp.figureeditor;
import java.awt.*;


public interface ShapeFactory {
	Shape create(Point start, Point end);
}
