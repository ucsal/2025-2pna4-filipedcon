package br.com.mariojp.figureeditor;

import java.awt.Dimension;
import java.io.File;
import java.util.List;

public interface Exporter {
	void export(List<DrawableShape> shapes, Dimension size, File file) throws Exception;
}
