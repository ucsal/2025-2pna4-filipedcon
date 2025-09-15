package br.com.mariojp.figureeditor;
import java.awt.Shape;

public class AddShapeCommand implements Command{
	private final DrawingPanel panel;
    private final Shape shape;

    public AddShapeCommand(DrawingPanel panel, Shape shape) {
        this.panel = panel;
        this.shape = shape;
    }

    @Override
    public void execute() {
        panel.addShapeInternal(shape);
    }

    @Override
    public void undo() {
        panel.removeShapeInternal(shape);
    }
}
