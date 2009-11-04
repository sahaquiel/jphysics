package org.fehlis.applications.EpisodeChecker.gui;

import org.fehlis.applications.EpisodeChecker.data.WebGuide;

public interface SessionListener
{
	public void guideAdded( WebGuide g );
	public void guideAddedAt( int i);
	
	public void guideDeleted( WebGuide g );
	public void guideDeletedAt( int i );
}
