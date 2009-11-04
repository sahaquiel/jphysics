package org.fehlis.applications.EpisodeChecker.data;

import java.util.Date;
import java.util.Vector;

import org.fehlis.applications.EpisodeTools.data.Serie;

public class EpisodeFilter
{
	public static Vector<DatedAndOwnedEpisode> getNewEpisodesNotOwned( Serie s )
	{
		Vector<DatedAndOwnedEpisode> ret = new Vector<DatedAndOwnedEpisode>();
		
		for ( int i = 0; i < s.size(); i++ )
		{
			DatedAndOwnedEpisode ep = (DatedAndOwnedEpisode) s.elementAt(i);
			
			if ( isEpisodeAiredButNotOwned(ep) )
			{
				ret.add(ep);
			}
		}
		
		return ret;
	}
	
	public static boolean isEpisodeAiredButNotOwned( DatedAndOwnedEpisode ep )
	{
		Date curr = new Date();
		return ( ep.airDate.before( curr ) && !ep.isOwned() );
	}

	public static boolean isEpisodeNotAired(DatedAndOwnedEpisode ep)
	{	
		Date curr = new Date();
		return ( ep.airDate.after( curr ) );
	}	
}
