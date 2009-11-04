package org.fehlis.applications.EpisodeChecker;

import org.fehlis.applications.EpisodeChecker.gui.MainWin;

public class EpisodeCheckerGUI
{
	public static void main( String[] args )
	{
        try
        {
            javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName() );
        }
        catch( Exception e ) {} 

        MainWin m = new MainWin();
        
        m.pack();
        m.setVisible( true );
	}

}
