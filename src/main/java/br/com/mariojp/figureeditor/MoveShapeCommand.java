package br.com.mariojp.figureeditor;

public class MoveShapeCommand implements Command {
	 private final DrawableShape shape;
	    private final int dx, dy;

	    public MoveShapeCommand(DrawableShape shape, int dx, int dy) {
	        this.shape = shape;
	        this.dx = dx;
	        this.dy = dy;
	    }

	    @Override
	    public void execute() {
	        shape.move(dx, dy);
	    }

	    @Override
	    public void undo() {
	        shape.move(-dx, -dy);
	    }
}
