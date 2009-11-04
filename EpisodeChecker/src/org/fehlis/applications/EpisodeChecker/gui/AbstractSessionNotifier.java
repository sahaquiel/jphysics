package org.fehlis.applications.EpisodeChecker.gui;

public interface AbstractSessionNotifier {

	public abstract void addSessionListener(SessionListener l);

	public abstract void removeSessionListener(SessionListener l);

}