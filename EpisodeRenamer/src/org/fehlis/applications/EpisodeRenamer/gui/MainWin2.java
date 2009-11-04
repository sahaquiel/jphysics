package org.fehlis.applications.EpisodeRenamer.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fehlis.applications.EpisodeRenamer.data.EpisodeSettings;
import org.fehlis.applications.EpisodeRenamer.data.FileListModel;
import org.fehlis.applications.EpisodeTools.data.Episode;
import org.fehlis.applications.EpisodeTools.data.Serie;

public class MainWin2 extends MainFrame implements ActionListener, DocumentListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1217124364854985967L;
	private Serie theSeries = null;
	private Episode theEpisode = null;
	private FileListModel data = null;
    private File selectedDir = null;
    private File lastSelectedEpDir = null;
    private File lastSelectedSaveDir = null;
    private boolean updateInProgress = false;
 
    public MainWin2()
    {
    	preInit();
    	postInit();
    }
    
	private void preInit()
	{
		theSeries = new Serie();
		theEpisode = new Episode();
		data = new FileListModel();
		
		theSeries.setName( "Family Guy" );
		theSeries.setNum( 1 );
		
		theEpisode.setAirNr( 1 );
		theEpisode.setProdNr( 1 );
		theEpisode.setSeasonNr( 1 );
		theEpisode.setSeasonProdNr( 1 );
		theEpisode.setTitle( "dummy" );
		
		getJButtonRename().setEnabled( false );
	}
	
	private void postInit()
	{
		getJList().setModel( data );
		
		syncGUIFromData();
		getJButtonSelect().addActionListener( this );
		getJButtonRename().addActionListener( this );
		getJButtonSaveMask().addActionListener( this );
		getJButtonLoadMask().addActionListener( this );
		getJTextField_AirNum().getDocument().addDocumentListener( this );
		getJTextField_EpisodeTitle().getDocument().addDocumentListener( this );
		getJTextField_ProdNum().getDocument().addDocumentListener( this );
		getJTextField_SeasonNum().getDocument().addDocumentListener( this );
		getJTextField_SeasonProdNum().getDocument().addDocumentListener( this );
		getJTextField_SeriesName().getDocument().addDocumentListener( this );
		getJTextField_RenameMask().getDocument().addDocumentListener( this );
	}
	
	private void syncGUIFromData()
	{
		updateInProgress = true;
		
		getJTextField_AirNum().			setText( "" + theEpisode.getAirNr() );
		getJTextField_EpisodeTitle().	setText( theEpisode.getTitle() );
		getJTextField_ProdNum().		setText( "" + theEpisode.getProdNr() );
		getJTextField_SeasonNum().		setText( "" + theEpisode.getSeasonNr() );
		getJTextField_SeasonProdNum().	setText( "" + theEpisode.getSeasonProdNr() );
		getJTextField_SeriesName().		setText( theSeries.getName() );
		getJTextField_RenameMask().		setText( theSeries.getRenameMask() );
		
		updateFileName();
		
		updateInProgress = false;
	}
	
	private int getVerifiedInt( JTextField tf )
	{
		int ret = 0;
		
		try
		{
			ret = Integer.parseInt( tf.getText() );
			tf.setForeground( Color.BLACK );
		}
		catch( NumberFormatException e)
		{
			tf.setForeground( Color.RED );
		}
		
		return ret;
	}
		
	private void syncDataFromGUI()
	{
		theEpisode.setAirNr( getVerifiedInt( getJTextField_AirNum() ) );
		theEpisode.setProdNr( getVerifiedInt( getJTextField_ProdNum() ) );
		theEpisode.setSeasonNr( getVerifiedInt( getJTextField_SeasonNum() ) );
		theEpisode.setSeasonProdNr( getVerifiedInt( getJTextField_SeasonProdNum() ) );
		theEpisode.setTitle( getJTextField_EpisodeTitle().getText() );
		theSeries.setName( getJTextField_SeriesName().getText() );
		theSeries.setRenameMask( getJTextField_RenameMask().getText() );
	}
	
	private void renameSelectedFile()
	{
		File f = (File) getJList().getSelectedValue();
		String extension = f.getName().substring( f.getName().lastIndexOf("."), f.getName().length() );
		
		String newName = f.getParent() + File.separator + generateFileName() + extension;

		System.out.println( newName );
		f.renameTo( new File( newName ) );
		
        updateFileList( selectedDir );
	}
	
	private void updateFileName()
	{
		System.out.println( "generated Filename: '" + generateFileName() + "'" );
		getJTextField_GeneratedName().	setText( generateFileName() );
	}
	
	private void updateFileList( java.io.File dir )
	{
		data.updateListData( dir.listFiles() );
//		getJList().revalidate();
		System.out.println( "items in List: " + getJList().getModel().getSize() );
	}
	
	private String generateFileName()
	{
		return String.format( theSeries.getRenameMask(), theSeries.getName(), theEpisode.getSeasonNr(), theEpisode.getAirNr(), theEpisode.getProdNr(), theEpisode.getTitle(), theEpisode.getSeasonProdNr() );
/*		
		return theSeries.getName() + " - "
		+ String.format( "%1$02d", theEpisode.getSeasonNr() ) + theEpisode.getAirNr() + " - "
		+ theEpisode.getSeasonNr() + "ACX" + theEpisode.getProdNr() + " - "		
		+ theEpisode.getTitle();
*/
	}

	public void actionPerformed(ActionEvent arg0) {
		Object o = arg0.getSource();
		
		if( o.equals( getJButtonSelect() ) )
		{
			DirectoryChooser chooser = new DirectoryChooser( lastSelectedEpDir );
			
			if ( chooser.showOpenDialog( this ) == javax.swing.JFileChooser.APPROVE_OPTION )
			{
					selectedDir = chooser.getSelectedFile();
					System.out.println("You chose to open this file: " + selectedDir.getName() );
			        updateFileList( selectedDir );
			        
			        getJButtonRename().setEnabled( true );
			        
			        lastSelectedEpDir = selectedDir;
			}			
		}
		else if ( o.equals( getJButtonLoadMask() ) )
		{
			EpisodeSettings currMask = new EpisodeSettings( theSeries );
			
			FileChooser chooser = new FileChooser( lastSelectedSaveDir );
			chooser.setDialogType( JFileChooser.OPEN_DIALOG );
			
			if ( chooser.showOpenDialog( this ) == javax.swing.JFileChooser.APPROVE_OPTION )
			{
					File selectedFile = chooser.getSelectedFile();
					System.out.println("You chose to save to this file: " + selectedFile.getAbsolutePath() );
					
					try
					{
						currMask.loadFromFile( selectedFile );
						syncGUIFromData();
						lastSelectedSaveDir = selectedFile.getParentFile();
					}
					catch( IOException e )
					{
						e.printStackTrace();
					}
			}			
		}
		else if ( o.equals( getJButtonSaveMask() ) )
		{
			EpisodeSettings currMask = new EpisodeSettings( theSeries );
			
			FileChooser chooser = new FileChooser( lastSelectedSaveDir );
			chooser.setDialogType( JFileChooser.SAVE_DIALOG );
			
			if ( chooser.showOpenDialog( this ) == javax.swing.JFileChooser.APPROVE_OPTION )
			{
					File selectedFile = chooser.getSelectedFile();
					System.out.println("You chose to save to this file: " + selectedFile.getAbsolutePath() );
					
					try
					{
						currMask.writeToFile( selectedFile );
						lastSelectedSaveDir = selectedFile.getParentFile();						
					}
					catch( IOException e )
					{
						e.printStackTrace();
					}
			}			
		}
		else if ( o.equals( getJButtonRename() ) )
		{
			updateData();
			renameSelectedFile();
		}
		else if ( o.equals( getJTextField_AirNum() ) )
		{
			updateData();
		}
	}	

	private void updateData()
	{
		if ( !updateInProgress )
		{
			syncDataFromGUI();
			updateFileName();
		}
	}
	
	public void insertUpdate(DocumentEvent arg0) {
		System.out.println( "insertUpdate()" );
		updateData();
	}

	public void removeUpdate(DocumentEvent arg0) {
		System.out.println( "removeUpdate()" );
		updateData();
	}

	public void changedUpdate(DocumentEvent arg0) {
		System.out.println( "changedUpdate()" );
		updateData();
	}

}
