package org.fehlis.applications.EpisodeChecker.gui;

import javax.swing.JTable;

import org.fehlis.applications.EpisodeTools.data.Serie;

@SuppressWarnings("serial")
public class EpisodeTable extends JTable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1451520578501946109L;
	private Serie m_serie;
	private EpisodeDateFilterTableCellRenderer m_renderer;	
	private EpisodeDateFilterTableCellEditor m_editor;	
	
	public EpisodeTable()
	{
		this( new Serie() );
	}
	
	
	public EpisodeTable( Serie s )
	{		
		m_serie = s;
		m_renderer = new EpisodeDateFilterTableCellRenderer();
		m_editor = new EpisodeDateFilterTableCellEditor();
		this.setModel( new EpisodeWithCheckBoxTableModel( m_serie ) );
		this.setFilterEnabled( true );
		
		for ( int i = 0; i < this.getColumnCount(); i++ )
		{			
			this.setDefaultRenderer( this.getColumnClass( i ) , m_renderer);
//			this.setDefaultEditor( this.getColumnClass( i ), m_editor );
		}
		
		this.setAutoCreateRowSorter(true);
	}
	
	
	public void setSerie( Serie s )
	{
		( (EpisodeTableModel) this.getModel() ).setSerie( s );
		
		this.doLayout();
	}
	
	public boolean isFilterEnabled() {
		return m_renderer.isFilterEnabled();
	}

	public void setFilterEnabled(boolean filterEnabled) {
		m_renderer.setFilterEnabled(filterEnabled);
	}	
		
}
