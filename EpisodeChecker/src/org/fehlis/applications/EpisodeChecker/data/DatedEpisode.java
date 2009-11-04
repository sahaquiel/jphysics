package org.fehlis.applications.EpisodeChecker.data;

import java.util.Date;

public class DatedEpisode extends org.fehlis.applications.EpisodeTools.data.Episode
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163262016712504954L;
	int absoluteNum;
	Date airDate = null;
	
	public DatedEpisode()
	{
		super();
		
		absoluteNum = 0;
		airDate = new Date();
	}

	public Date getAirDate() {
		return airDate;
	}

	public void setAirDate(Date airDate) {
		this.airDate = airDate;
	}
	
	public String toString()
	{
		return absoluteNum + ", " + airDate.toString() + ", " + super.toString();
	}

	public int getAbsoluteNum() {
		return absoluteNum;
	}

	public void setAbsoluteNum(int absoluteNum) {
		this.absoluteNum = absoluteNum;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean ret = false;
		
		if ( super.equals( obj ) )
		{
			DatedEpisode ep = (DatedEpisode) obj;
			if ( ep.absoluteNum == this.absoluteNum &&
				 ep.airDate.equals( this.airDate )
				)
			{
				ret = true;
			}
		}
		return ret;
	}	
}
