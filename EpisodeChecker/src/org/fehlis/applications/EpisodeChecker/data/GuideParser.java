package org.fehlis.applications.EpisodeChecker.data;

import java.io.IOException;
import java.io.Reader;

import org.fehlis.applications.EpisodeTools.data.Serie;

public interface GuideParser {

	public abstract void parseSerie(Reader in, Serie s) throws IOException;

}