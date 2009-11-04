package org.fehlis.applications.EpisodeChecker.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.fehlis.applications.EpisodeChecker.data.WebGuide;
import org.fehlis.applications.EpisodeTools.data.Serie;

@SuppressWarnings("serial")
public class ProviderPanel extends JPanel implements java.awt.event.ActionListener, AbstractSessionNotifier
{
	@Override
	public void setEnabled(boolean enabled)
	{
		super.setEnabled(enabled);
		
		m_table.setEnabled( enabled );
	}

	private Serie m_serie;
	private EpisodeTable m_table;	
	private WebGuide m_guide;
	private JLabel m_provLabel;
	private JCheckBox m_filterBox;
	private JScrollPane m_pane;
	private JButton m_deleteBtn;
	private DefaultSessionNotifier m_notifier;
	
	public ProviderPanel()
	{
		this.setLayout( new BorderLayout() );
		
		JPanel topPanel = new JPanel();
	
		m_notifier = new DefaultSessionNotifier();
		
		m_serie = new Serie();
		m_provLabel = new JLabel("<none>");
		m_filterBox = new JCheckBox();
		m_deleteBtn = new JButton( "remove" );
		
		m_deleteBtn.addActionListener( this );
		
		topPanel.add( new JLabel("Provider Name:") );
		topPanel.add( m_provLabel );
		topPanel.add( new JLabel( "Filter: " ) );
		topPanel.add( m_filterBox );
		topPanel.add( m_deleteBtn );
		
		add( topPanel, BorderLayout.NORTH );
		
		m_table = new EpisodeTable( m_serie );
		
		m_pane = new JScrollPane();
		m_pane.setViewportView( m_table );
		
		setPreferredSize( new Dimension( 300, 100 ) );
		
		add( m_pane, BorderLayout.CENTER );
		
		m_guide = null;
	}
	
	private void updateLabel()
	{
		m_provLabel.setText( m_guide.getName() );
	}
	
	public WebGuide getData()
	{
		return m_guide;
	}
	
	public void setData( WebGuide guide )
	{
		if ( guide != null )
		{
			m_guide = guide;
			m_table.setSerie( m_guide.getTheSerie() );
		}
		
		updateLabel();
	}
	
	public void update()
	{
		if ( m_guide != null )
		{
//			g1.setTheSerie( m_serie );
//			m_guide.getTheSerie().clear();
			
			try {
				m_guide.parsePage();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Unable to parse guide " + m_guide + "!", "warning", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		
			m_table.setSerie( m_guide.getTheSerie() );
	
//			m_pane.scrollRectToVisible( new Rectangle( 200, 100 ) );
//			m_table.setEditingRow( m_table.getRowCount() - 1 );
			
			m_table.changeSelection( m_table.getRowCount() - 1, 0, false, false );
			m_pane.scrollRectToVisible( m_table.getCellRect(m_table.getRowCount() - 1, 0, true) );
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		m_notifier.fireGuideDeletedEvent( m_guide );
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
