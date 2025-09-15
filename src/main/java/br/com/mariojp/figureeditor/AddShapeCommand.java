package br.com.mariojp.figureeditor;

public class AddShapeCommand implements Command{
	private final DrawingPanel panel;
    private final DrawableShape shape;

    public AddShapeCommand(DrawingPanel panel, DrawableShape s) {
        this.panel = panel;
        this.shape = s;
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
