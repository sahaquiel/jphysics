package org.fehlis.applications.EpisodeChecker.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.fehlis.applications.EpisodeChecker.data.WebGuide;

@SuppressWarnings("serial")
public class AddNewGuideDialog extends JDialog implements java.awt.event.ActionListener
{
	private JButton m_ok;
	private JButton m_cancel;
//	private JTextField m_tf_name;
	private JTextField m_tf_url;
	private JTextField m_tf_serie;
	private JComboBox m_cb_name;

	private WebGuide m_newGuide;
	
	public AddNewGuideDialog( Frame f )
	{
		super( f, true );
		
		this.setTitle( "add new Guide" );
		
		m_ok = new JButton( "OK" );
		m_cancel = new JButton( "Cancel" );
		
		m_ok.addActionListener( this );
		m_cancel.addActionListener( this );
		
		m_cb_name = new JComboBox();
		m_cb_name.addItem( new String( "EpGuides.com") );
		m_tf_url = new JTextField( "http://");
		m_tf_serie = new JTextField( "" );
		
		// bottom frame
		JPanel bframe = new JPanel();		
		bframe.setLayout( new java.awt.FlowLayout() );
		
		bframe.add(m_ok);
		bframe.add(m_cancel);
		
		// middle frame
		JPanel mframe = new JPanel();
		mframe.setLayout( new java.awt.GridLayout(3, 2 ) );
		
		mframe.add( new JLabel("Provider name:") );
		mframe.add( m_cb_name );
		mframe.add( new JLabel("Provider URL:") );
		mframe.add( m_tf_url );
		mframe.add( new JLabel("Serie name:") );
		mframe.add( m_tf_serie );
		
		this.setLayout( new BorderLayout() );
		
		add( mframe, BorderLayout.CENTER );		
		add( bframe, BorderLayout.SOUTH );
		
		this.pack();
		
		this.setLocationRelativeTo( f );
	}

	public WebGuide addNewWebGuide()
	{
		this.setVisible(true);
		
		return m_newGuide;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == m_cancel )
		{
			this.dispose();
		}
		else if ( e.getSource() == m_ok )
		{
			try
			{
				String tmpName = ( ( String ) m_cb_name.getSelectedItem() ).trim();
				URL tmpUrl = new URL( m_tf_url.getText().trim() );
				String tmpSerie = m_tf_serie.getText().trim();
				
				m_newGuide = new WebGuide( tmpName, tmpUrl, tmpSerie );
				this.dispose();
			}
			catch (MalformedURLException e1)
			{
				JOptionPane.showMessageDialog(this, "Input Error", "error", JOptionPane.ERROR_MESSAGE);
			}			
		}
	}
}
