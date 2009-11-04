package org.fehlis.applications.EpisodeChecker.data;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class Settings
{
	private static Settings m_instance = null;
	
	private Properties m_props;
	
	private Rectangle winLocation;
	
	private Settings()
	{
		m_props = new Properties();
		
		try
		{
			File f = new File( System.getProperty("user.home") + File.separatorChar + "EpisodeChecker.properties" );
			
			m_props.load( new BufferedInputStream( new FileInputStream( f ) ) );
			syncPropertiesToMembers();
		}
		catch( java.io.IOException e )
		{
			createNewPreferences();
			try
			{
			save();
			}
			catch( java.io.IOException e2 )
			{}
		}		
	}
	
	private void createNewPreferences()
	{
		winLocation = new Rectangle( new Point( 100, 50 ), new Dimension( 300, 200 ) );
	}

	private void syncPropertiesToMembers()
	{
		try
		{
			Rectangle r = new Rectangle();
			
			r.x = Integer.parseInt( (String) m_props.get( "MainWindowLocation.rectangle.x" ) );
			r.y = Integer.parseInt( (String) m_props.get( "MainWindowLocation.rectangle.y" ) );
			r.width = Integer.parseInt( (String) m_props.get( "MainWindowLocation.rectangle.width" ) );
			r.height = Integer.parseInt( (String) m_props.get( "MainWindowLocation.rectangle.height" ) );

			winLocation = r;
		}
		catch( NumberFormatException e )
		{
			// on error we create default prefs again
			createNewPreferences();
		}

	}
	
	private void syncMembersToProperties()
	{
		m_props.put( "MainWindowLocation.rectangle.x", "" + winLocation.x );		
		m_props.put( "MainWindowLocation.rectangle.y", "" + winLocation.y );		
		m_props.put( "MainWindowLocation.rectangle.width", "" + winLocation.width );		
		m_props.put( "MainWindowLocation.rectangle.height", "" + winLocation.height );		
	}
	
	public static Settings getInstance()
	{
		if ( m_instance == null )
		{
			m_instance = new Settings();
		}
		
		return m_instance;
	}

//	public Properties getProperties() {
//		return m_props;
//	}

	public void save() throws java.io.IOException
	{
		save( "" );
	}

	public void save( String comment ) throws java.io.IOException
	{
		File f = new File( System.getProperty("user.home") + File.separatorChar + "EpisodeChecker.properties" );

		// first sync the properties to the current values of the member-variables
		syncMembersToProperties();
		
		// save the properties in the file
		m_props.store( new BufferedOutputStream( new FileOutputStream( f ) ), comment );
	}

	public Rectangle getWinLocation() {
		return winLocation;
	}

	public void setWinLocation(Rectangle winLocation) {
		this.winLocation = winLocation;
	}
}
