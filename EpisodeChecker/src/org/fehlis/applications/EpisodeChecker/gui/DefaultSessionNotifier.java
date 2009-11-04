package org.fehlis.applications.EpisodeChecker.gui;

import java.util.Vector;

import org.fehlis.applications.EpisodeChecker.data.WebGuide;

public class DefaultSessionNotifier implements AbstractSessionNotifier {
	private Vector<SessionListener> m_listeners;

	public DefaultSessionNotifier()
	{
		m_listeners = new Vector<SessionListener>();
	}
	
	/* (non-Javadoc)
	 * @see org.fehlis.applications.EpisodeChecker.gui.AbstractSessionNotifier#addSessionListener(org.fehlis.applications.EpisodeChecker.gui.SessionListener)
	 */
	public void addSessionListener( SessionListener l )
	{
		m_listeners.add( l );
	}
	
	
	/* (non-Javadoc)
	 * @see org.fehlis.applications.EpisodeChecker.gui.AbstractSessionNotifier#removeSessionListener(org.fehlis.applications.EpisodeChecker.gui.SessionListener)
	 */
	public void removeSessionListener( SessionListener l )
	{
		m_listeners.remove( l );
	}
	
	
	protected void fireGuideAddedEvent( WebGuide w )
	{
		for ( int i = 0; i < m_listeners.size(); i++ )
		{
			m_listeners.elementAt(i).guideAdded( w );
		}
	}
	
	protected void fireGuideDeletedEvent( WebGuide w )
	{
		for ( int i = 0; i < m_listeners.size(); i++ )
		{
			m_listeners.elementAt(i).guideDeleted( w );
		}
	}
}
