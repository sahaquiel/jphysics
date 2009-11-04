package org.fehlis.applications.EpisodeRenamer.gui;

import java.io.File;

import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7578729243769255961L;

	class FileFilter extends javax.swing.filechooser.FileFilter
	{

		@Override
		public boolean accept(File arg0) {
			return arg0.toString().endsWith(".eps");
		}

		@Override
		public String getDescription() {
			return ".eps files only";
		}
		
	}
	
    private FileFilter filter;
    
    public FileChooser( File currDir )
    {
    	super();
    	
    	if ( currDir != null && currDir.exists() && currDir.isDirectory() )
    	{
    		this.setCurrentDirectory( currDir );
    	}
    	
    	filter = new FileFilter();

    	setFileFilter(filter);
//    	addChoosableFileFilter(filter);
//    	setFileSelectionMode( JFileChooser.FILES_ONLY );
    }
}
