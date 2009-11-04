package org.fehlis.applications.EpisodeChecker.gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import org.fehlis.applications.EpisodeChecker.data.GuideSession;
import org.fehlis.applications.EpisodeChecker.data.WebGuide;

@SuppressWarnings("serial")
public class ManageGuidesWindow extends JDialog implements ActionListener, AbstractSessionNotifier
{
	JButton m_loadBt;
	JButton m_addBt;
	JButton m_exitBt;
	
	private Frame m_parent;
	private GuideSession m_sess;
	private DefaultSessionNotifier m_notifier;
	
	public ManageGuidesWindow( MainWin f, GuideSession sess )
	{
		super( f, true );
		
		m_parent = f;
		m_sess = sess;
		m_notifier = new DefaultSessionNotifier();
		
		this.setTitle( "Guide management" );
		this.setLayout( new java.awt.FlowLayout() );
		
		m_loadBt = new JButton( "load" );
		m_addBt = new JButton( "add" );
		m_exitBt = new JButton( "exit" );
		
		add(m_loadBt);
		add(m_addBt);
		add(m_exitBt);
		
		m_loadBt.addActionListener( this );
		m_addBt.addActionListener( this );
		m_exitBt.addActionListener( this );
		
		this.pack();		
		this.setLocationRelativeTo( f );
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == m_exitBt )
		{
			this.dispose();
		}
		else if ( e.getSource() == m_addBt )
		{		
			WebGuide newGuide = new AddNewGuideDialog(m_parent).addNewWebGuide();
			
			if ( newGuide != null )
			{
				m_sess.add( newGuide );
				m_notifier.fireGuideAddedEvent( newGuide );
			}
		}
	}

	@Override
	public void addSessionListener(SessionListener l) {
		// TODO Auto-generated method stub
		m_notifier.addSessionListener(l);
	}

	@Override
	public void removeSessionListener(SessionListener l) {
		// TODO Auto-generated method stub
		m_notifier.removeSessionListener(l);
	}

}
