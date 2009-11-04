package org.fehlis.applications.EpisodeChecker.data;

public class DatedAndOwnedEpisode extends DatedEpisode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4682793008888311640L;
	private boolean m_owned;

	public DatedAndOwnedEpisode()
	{
		super();
		m_owned = false;
	}
	
	public boolean isOwned() {
		return m_owned;
	}

	public void setIsOwned(boolean m_owned) {
		this.m_owned = m_owned;
	}

	@Override
	public boolean equals(Object obj)
	{
		return super.equals(obj);
	}
}
