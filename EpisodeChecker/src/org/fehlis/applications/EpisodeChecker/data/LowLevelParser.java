package org.fehlis.applications.EpisodeChecker.data;

import java.io.IOException;
import java.io.Reader;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.fehlis.applications.EpisodeTools.data.Serie;

public class LowLevelParser implements GuideParser
{
	public void parseSerie(Reader in, Serie s) throws IOException
	{
		StringBuffer strbuf = new StringBuffer();;
		char[] buf = new char[80];
		int ret = 0;
		
		while ( ( ret = in.read( buf ) ) > 0 )
		{			
			strbuf.append( buf, 0, ret );
		}
		
		// here we got the whole HTML-code within our Stringbuffer now
		// Next steps:
		// - find the beginning of the series list
		// - parse out the series info
		// - create an Episode object for each found episode and add it to the Serie
		
		int currpos = strbuf.indexOf("_____ ______ ___________  ___________ ___________________________________________");
		
		int entryStartPos = 0; 
		int entryEndPos = 0;
		
		while( ( currpos = strbuf.indexOf( "<a target=", currpos ) ) >= 0 )
		{
			entryStartPos = currpos - 39;
			entryEndPos = strbuf.indexOf( "</a>", entryStartPos );
		
			if ( entryEndPos > entryStartPos )
			{
				String currEntry = strbuf.substring( entryStartPos, entryEndPos );
//				System.out.println( "found possible entry: '" + currEntry + "'" );
				
				DatedEpisode ep = parseEpisode( currEntry );
				
				if ( ep != null && !s.contains( ep ) )
				{
					s.add( ep );
				}
			}
			
			currpos++;
		}
		
//		System.out.println( strbuf.toString() );
	}
	
	private int parseMonth( String smonth ) throws NumberFormatException
	{
		int ret = -1;
		Vector<String> monthnames = new Vector<String>();

		monthnames.add( "Jan" );
		monthnames.add( "Feb" );
		monthnames.add( "Mar" );
		monthnames.add( "Apr" );
		monthnames.add( "May" );
		monthnames.add( "Jun" );
		monthnames.add( "Jul" );
		monthnames.add( "Aug" );
		monthnames.add( "Sep" );
		monthnames.add( "Oct" );
		monthnames.add( "Nov" );
		monthnames.add( "Dec" );
		
		ret = monthnames.indexOf( smonth );
		if ( ret == -1 )
		{
			throw new NumberFormatException();
		}
		
		return ret;
	}
	
	private String parseTitle( String currEntry )
	{
		String ret = null;
		
		int startTagPos = currEntry.indexOf( "<a target=" );
		int startTagEndPos = currEntry.indexOf( ">", startTagPos + 1 );
		
		int endTagPos = currEntry.indexOf( "</a>", startTagEndPos );
		if ( endTagPos == -1 )
		{
			endTagPos = currEntry.length();
		}
		
		ret = currEntry.substring( startTagEndPos + 1, endTagPos );
		
		return ret;
	}
	
	private Date parseDate( String airDate ) throws StringIndexOutOfBoundsException, NumberFormatException
	{
		String day = airDate.substring( 0, 3 );
		String month = airDate.substring( 4, 7 );
		String year = airDate.substring( 8, 11 );
		
		int iYear = Integer.parseInt(year.trim());
		if ( iYear > 70 && iYear < 99 )
		{
			iYear += 1900;
		}
		
		if ( iYear > 0 && iYear < 70 )
		{
			iYear += 2000;
		}
				
		Calendar c = java.util.Calendar.getInstance();
		c.clear();
		c.set(iYear, parseMonth( month.trim() ), Integer.parseInt( day.trim() ) );
		
		return c.getTime();
	}
	
	private DatedEpisode parseEpisode(String currEntry) throws NumberFormatException
	{
		// - create new Episode object
		// - check if given String actually includes valid info for an Episode
		// - if yes, fill in the new infos and return the filled Episode obj
		// - if no, return null
		
		DatedEpisode ep = new DatedAndOwnedEpisode();

//		( (DatedAndOwnedEpisode)ep ).setIsOwned( true );
		
		try
		{
			String num = currEntry.substring( 0, 3 );
//			String num2 = currEntry.substring( 6, 12 );
			
			String seasonNum = currEntry.substring( 6, 8 );
			String epNum	 = currEntry.substring( 9, 12 );
			
//			String prodNum = currEntry.substring( 14, 24 );
			String airDate = currEntry.substring( 26, 37 );

			Date d = parseDate(airDate);
			
			String epTitle = parseTitle( currEntry );
			
			ep.setAbsoluteNum( Integer.parseInt( num.trim() ) );
			ep.setSeasonNr( Integer.parseInt( seasonNum.trim() ) );
			ep.setAirNr( Integer.parseInt( epNum.trim() ) );
			ep.setAirDate( d );
			ep.setTitle( epTitle );
		}
		catch( NumberFormatException e )
		{
			ep = null;
		}
		catch( StringIndexOutOfBoundsException e)
		{
			ep = null;
		}
		
		return ep;
	}
}
