package org.fehlis.applications.EpisodeRenamer;

import org.fehlis.applications.EpisodeRenamer.gui.*;

public class EpisodeRenamer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2834181334083194218L;
	
	@SuppressWarnings("deprecation")
	public static void main( String[] args )
	{
        try
        {
            javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName() );
        }
        catch( Exception e ) {} 
        
		new MainWin2().show();
	}

}
