package br.com.mariojp.figureeditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public interface ShapeFactory {
	Shape create(Point start, Point end);
}
