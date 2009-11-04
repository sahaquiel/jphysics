package org.fehlis.applications.EpisodeRenamer.data;

import java.util.Properties;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

import org.fehlis.applications.EpisodeTools.data.Serie;

public class EpisodeSettings extends Properties
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1750499334057296872L;
	
	private final static String m_seriesNameKey 	= "SeriesName";
//	private final static String m_seasonNrKey		= "SeasonNr";
//	private final static String m_airNrKey			= "AirNr";
//	private final static String m_seasonProdNrKey 	= "SeasonProdNr";
//	private final static String m_ProdNrKey			= "ProdNr";
//	private final static String m_episodeTitleKey	= "EpisodeTitle";
	private final static String m_renameMaskKey		= "RenameMask";
	
	private Serie m_serie;
	
	public EpisodeSettings( Serie s )
	{
		m_serie = s;
	}
		
	public void writeToFile( File f ) throws IOException
	{
		put( m_seriesNameKey, m_serie.getName() );
		put( m_renameMaskKey, m_serie.getRenameMask() );
		
		store( new FileOutputStream( f ), "no comment" );
	}
	
	public void loadFromFile( File f ) throws IOException
	{
		load( new FileInputStream( f ) );
		m_serie.setName( (String) get( m_seriesNameKey ) );
		m_serie.setRenameMask( (String) get( m_renameMaskKey ) );
	}
}
