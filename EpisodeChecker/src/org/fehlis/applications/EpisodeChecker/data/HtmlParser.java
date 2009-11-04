package org.fehlis.applications.EpisodeChecker.data;

import java.io.IOException;
import java.io.Reader;

import javax.swing.text.ChangedCharSetException;
import javax.swing.text.html.parser.Parser;
import javax.swing.text.html.parser.TagElement;

import org.fehlis.applications.EpisodeTools.data.Episode;
import org.fehlis.applications.EpisodeTools.data.Serie;

public class HtmlParser extends Parser implements GuideParser
{
	private Serie serieToParse = null;
	private Episode currEp = null; 		// current Episode being parsed
	
	private int currState = INIT;
	private static final int INIT = 0;	
	private static final int FOUND_HEADER = 1;
	private static final int EPISODE_START = 2;
	private static final int EPISODE_DONE = 3;
	
	public HtmlParser() throws IOException
	{
		super(javax.swing.text.html.parser.DTD.getDTD("html.dtd"));
	}

	/* (non-Javadoc)
	 * @see org.fehlis.applications.EpisodeChecker.data.GuideParser#parseSerie(java.io.Reader, org.fehlis.applications.EpisodeRenamer.data.Serie)
	 */
	public void parseSerie(Reader in, Serie s) throws IOException
	{
		serieToParse = s;
		parse(in);
	}

	@Override
	protected void handleText(char[] text) {

		String str = new String( text );
		System.out.println("Text: " + str);
		System.out.println("Pos:  " + getCurrentPos() );
		System.out.println("Line: " + getCurrentLine() );
		
		if ( currState == INIT )
		{		
			if ( str.contains( "_____ ______ ___________ ___________ ___________________________________________" ) )
			{
				currState = FOUND_HEADER;
			}	
			return;
		}
		else if ( currState == FOUND_HEADER )
		{
			if ( str.contains( "." ) )
			{
				String num		= str.substring( 0, 5 );
				String num2		= str.substring( 7, 13 );
				String prod		= str.substring( 15, 26 );				

				if ( true/* valid values */ )
				{
					currEp = new Episode();
//					ep.setSeasonNr();
//					ep.setSeasonProdNr();
//					ep.setAirNr();
//					ep.setProdNr();
					
					currState = EPISODE_START;
				}
			}			
		}
		else if ( currState == EPISODE_START )
		{
			String title = str;
			
			if ( true )
			{
				serieToParse.add( currEp );
				currEp = null;
				
				currState = EPISODE_DONE;
			}
		}				
	}

	@Override
	protected void startTag(TagElement tag) throws ChangedCharSetException
	{
		System.out.println("Start tag: " + tag.getElement().getName());
				
		super.startTag(tag);
	}

	@Override
	protected void handleStartTag(TagElement tag) {
		System.out.println("Handle Start tag: " + tag.getElement().getName());

		super.handleStartTag(tag);
	}

	@Override
	protected void handleEndTag(TagElement tag) {
		System.out.println("Handle End tag: " + tag.getElement().getName());

		super.handleEndTag(tag);
	}
}