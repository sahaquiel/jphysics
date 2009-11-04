package org.fehlis.applications.EpisodeChecker.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

import org.fehlis.applications.EpisodeTools.data.Serie;

public class WebGuide implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8328543006901483652L;
	
	String 	name;		// name of web-based guide
	URL		location;	// the URL of the guide
	Serie 	theSerie;	// the serie that the guide is referring to
	
	
	private WebGuide( String name, URL location )
	{
		this.name = name;
		this.location = location;
	}
	
	public WebGuide( String name, URL location, Serie s )
	{
		this(name, location);
		theSerie = s;
	}
	
	public WebGuide( String name, URL location, String sname )
	{
		this(name,location);
		theSerie = new Serie( sname );
	}

	public Serie getTheSerie() {
		return theSerie;
	}

	public void setTheSerie(Serie theSerie) {
		this.theSerie = theSerie;
	}

	// TODO: this could throw a parseException
	public void parsePage() throws IOException
	{		
		GuideParser guideParser= null;
		
		if ( name.equals( "EpGuides.com" ) )
		{
			guideParser = new LowLevelParser();
		}
		// here we can add more implementations depending on the name of the Guide
//			else if ( name.equals( "Someotherguide.com" ) )
//			{
//				guideParser = new HtmlParser();
//			}
		
		// let the parser run through the webpage. It will fill our Serie-element if successful
		BufferedReader br = new BufferedReader( new InputStreamReader( location.openConnection().getInputStream() ) );
		guideParser.parseSerie( br, theSerie );

		// not sure why the code below doesn't cause the Windows-sockets to be closed right away
		// when using JRE 6. With 5 it works.
		br.close();
		location.openConnection().getInputStream().close();			
	}

	public String toString()
	{
		return theSerie.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vector getNotOwnedEpisodes() {
		return EpisodeFilter.getNewEpisodesNotOwned( theSerie );
	}
}
