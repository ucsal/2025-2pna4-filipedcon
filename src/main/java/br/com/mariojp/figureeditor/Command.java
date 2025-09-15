package br.com.mariojp.figureeditor;

public interface Command {
    void execute();
    void undo();
}
