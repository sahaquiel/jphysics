package org.fehlis.applications.EpisodeChecker.gui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.fehlis.applications.EpisodeChecker.data.GuideSession;
import org.fehlis.applications.EpisodeChecker.data.Settings;
import org.fehlis.applications.EpisodeChecker.data.WebGuide;
import org.fehlis.applications.EpisodeTools.data.Serie;

public class MainWin extends JFrame implements java.awt.event.ActionListener, java.awt.event.WindowListener, SessionListener
{
//	javax.swing.AbstractAction actAddProvider = new javax.swing.AbstractAction();

	/**
	 * 
	 */
	private static final long serialVersionUID = 4162703777238342792L;
	
	GuideSession m_guides;
	JTabbedPane m_guidePane;
	JComboBox m_provNames;

	JButton m_loadBt, m_saveBt;
	JButton m_checkForNotOwned;
	JButton m_guideMgmt;
	
//	Properties m_props;

	public MainWin()
	{
		Rectangle winLocation = Settings.getInstance().getWinLocation();
		
		m_guides = getLastSavedSession();

		if ( m_guides == null )
		{
			m_guides = createDefaultSession();			
		}		

//		this.setPreferredSize(  )
		
		this.setTitle( "Episode Checker" );
		
		this.addWindowListener( this );
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE  );
		
		setLayout( new java.awt.BorderLayout() );
		
		m_guidePane = new JTabbedPane();
		
		add( m_guidePane, BorderLayout.CENTER );
		
		JPanel topPanel = new JPanel();
		add( topPanel, BorderLayout.NORTH );
		
		m_loadBt = new JButton( "load from the web" );
		m_saveBt = new JButton( "save current session" );
		m_checkForNotOwned = new JButton( "check for not owned Episodes" );
		m_guideMgmt = new JButton( "Guide Management" );
		topPanel.add( m_loadBt );
		topPanel.add( m_saveBt );
		topPanel.add( m_checkForNotOwned );
		topPanel.add( m_guideMgmt );
		
		m_loadBt.setActionCommand( "CmdLoadAllGuideData" );
		m_saveBt.setActionCommand( "CmdSaveAllGuideData" );
		m_checkForNotOwned.setActionCommand( "CmdCheckForNotOwned" );
		m_guideMgmt.setActionCommand( "CmdShowGuideManagement" );		

		m_loadBt.addActionListener( this );
		m_saveBt.addActionListener( this );
		m_checkForNotOwned.addActionListener( this );
		m_guideMgmt.addActionListener( this );

		for ( int i = 0; i < m_guides.size(); i++ )
		{
			addGuideTab( m_guides.elementAt( i ) );
		}
		
		this.setBounds(winLocation);
		this.setPreferredSize( winLocation.getSize() );
	}
	
	// TODO should probably move this to a more generally accessible position
	private void setIsBusy( boolean b )
	{
		if ( b == true )
		{
//			this.setEnabled( false );
			this.setCursor( Cursor.getPredefinedCursor( Cursor.WAIT_CURSOR ) );
		}
		else
		{
//			this.setEnabled( true );
			this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );
		}
	}


	private class UpdateThread extends Thread
	{
		ProviderPanel m_pp;
		
		public UpdateThread( ProviderPanel pp )
		{
			m_pp = pp;
		}
		
		public void run()
		{
			m_pp.update();
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String action = (String)e.getActionCommand();
		
		if ( action != null )
		{
			if ( action.equals( "CmdLoadAllGuideData" ) )
			{
				setIsBusy( true );
				
				UpdateThread[] threads = new UpdateThread[m_guidePane.getComponentCount()];
				
				for ( int i = 0; i < m_guidePane.getComponentCount(); i++ )
				{
					ProviderPanel pp = (ProviderPanel) m_guidePane.getComponent( i );
	
					threads[i] = new UpdateThread( pp );

					threads[i].start();
					
//					pp.update();
				}
				
				for ( int i = 0; i < threads.length; i++ )
				{
					try {
						threads[i].join();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						i--;
						continue;
					}
				}
				
				setIsBusy( false );
			}
			else if ( action.equals( "CmdSaveAllGuideData" ) )
			{
				setIsBusy( true );
				saveCurrentSession( m_guides );
				setIsBusy( false );
			}
			else if ( action.equals( "CmdCheckForNotOwned" ) )
			{
				setIsBusy( true );
				checkForNewEpisodes( m_guides );
				setIsBusy( false );
			}
			else if ( action.equals( "CmdShowGuideManagement" ) )
			{
				ManageGuidesWindow w = new ManageGuidesWindow(this, m_guides);
				w.addSessionListener( this );
				w.setVisible(true);
			}
		}		
	}

	public void windowActivated(WindowEvent e) {
		
	}

	public void windowClosed(WindowEvent e) {
		// TODO perhaps ask if really want to exit?
		try {
			Settings.getInstance().setWinLocation( this.getBounds() );
			Settings.getInstance().save( "saved automatically on user exit" );
			
			saveCurrentSession( m_guides );
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void checkForNewEpisodes(GuideSession session)
	{
		for ( int i = 0; i < session.size(); i++ )
		{
			WebGuide g = session.elementAt( i );
			
			Vector v = g.getNotOwnedEpisodes();
			
			System.out.println( "Not owned episodes for " + g.getTheSerie().getName() );
			for ( int l = 0; l < v.size(); l++ )
			{
				System.out.println( v.elementAt(l).toString() );
			}
			
		}
	}

	private void saveCurrentSession( GuideSession session )
	{
		FileOutputStream f_out;
		try {
		// Write to disk with FileOutputStream
			f_out = new FileOutputStream( "lastsession.wgs" );
	
			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
	
			// Write object out to disk
			obj_out.writeObject ( session );
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private GuideSession createDefaultSession()
	{
		GuideSession ret = new GuideSession();
		
		try {
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/BostonLegal/"), "Boston Legal" ) );			
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/Bleach/"), "Bleach" ) );
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/House/"), "House" ) );
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/AvatarTheLastAirbender/"), "Avatar" ) );
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/BattlestarGalactica/"), "Battlestar Galactica" ) );
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/FamilyGuy/"), "Family Guy" ) );			
			ret.add( new WebGuide( "EpGuides.com", new URL("http://epguides.com/Smallville/"), "Smallville" ) );			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	private GuideSession getLastSavedSession()
	{
		GuideSession ret = null;
		
		FileInputStream f_in;
		try {
		// Write to disk with FileOutputStream
			f_in = new FileInputStream("lastsession.wgs");
		
			// Write object with ObjectOutputStream
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
		
			// Write object out to disk
			ret = (GuideSession) obj_in.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	private void addGuideTab( WebGuide g )
	{
		ProviderPanel pp = new ProviderPanel();
		pp.setData( g );
		pp.addSessionListener( this );
		m_guidePane.add( g.getTheSerie().getName(), pp );
//		m_guidePane.in
	}
	
	private void removeGuideTab( WebGuide g )
	{
		for ( int i = 0; i < m_guidePane.getComponentCount(); i++ )
		{
			WebGuide gg = ( ( ProviderPanel) m_guidePane.getComponentAt( i ) ).getData();

			if ( g == gg )
			{
				m_guidePane.remove( i );
				m_guides.remove( g );
				break;
			}
		}
	}
	
	@Override
	public void guideAdded( WebGuide g )
	{
		addGuideTab( g );
	}

	@Override
	public void guideAddedAt(int i)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void guideDeletedAt(int i)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void guideDeleted(WebGuide g)
	{
		removeGuideTab( g );
	}

}
