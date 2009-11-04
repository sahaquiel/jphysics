package org.fehlis.applications.EpisodeRenamer.gui;

import java.io.File;

import javax.swing.JFileChooser;

public class DirectoryChooser extends JFileChooser
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7578729243769255961L;

	class DirectoryFilter extends javax.swing.filechooser.FileFilter
	{

		@Override
		public boolean accept(File arg0) {
			return arg0.isDirectory();
		}

		@Override
		public String getDescription() {
			return "directories only";
		}
		
	}
	
    private DirectoryFilter filter;
    
    public DirectoryChooser( File currDir )
    {
    	super();

    	if ( currDir != null && currDir.exists() && currDir.isDirectory() )
    	{
    		this.setCurrentDirectory( currDir );
    	}

    	filter = new DirectoryFilter();
    	
    	setFileFilter(filter);
    	addChoosableFileFilter(filter);
    	setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
    }
}
