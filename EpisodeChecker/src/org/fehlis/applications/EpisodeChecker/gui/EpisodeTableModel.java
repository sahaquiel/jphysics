package org.fehlis.applications.EpisodeChecker.gui;

import java.util.Date;

import javax.swing.table.AbstractTableModel;

import org.fehlis.applications.EpisodeChecker.data.DatedEpisode;
import org.fehlis.applications.EpisodeTools.data.Serie;

public class EpisodeTableModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8802147281659620546L;

	private Serie m_serie;
	
/*	
	private enum columnIndexes
	{
		DATE (0),
		TITLE(1);
		
		int idxValue = -1;
		
		private columnIndexes( int i )
		{
			this.idxValue = i;
		}
	};
*/
	public static final int COLUMN_AIRDATE	= 0;
	public static final int COLUMN_TITLE 	= 1;
	public static final int COLUMN_AIRNUM 	= 2;
	
	public EpisodeTableModel( Serie s )
	{
		m_serie = s;
	}
	
	public void setSerie( Serie s )
	{
		m_serie = s;
		this.fireTableDataChanged();
	}

	public Serie getSerie()
	{
		return m_serie;
	}

	public int getColumnCount()
	{
		return 3;
	}

	public int getRowCount()
	{
		return m_serie.size();
	}

	public DatedEpisode getEpisodeObjectForRow( int row )
	{
		return (DatedEpisode) m_serie.elementAt(row);
	}
	
	public Object getValueAt(int row, int column)
	{
		Object ret = null;
		
		if ( row <= m_serie.size() )
		{	
			DatedEpisode ep = getEpisodeObjectForRow( row );
			
			switch( column )
			{
			case COLUMN_AIRDATE:
				ret = ep.getAirDate();
				break;
			case COLUMN_TITLE:
				ret = ep.getTitle();
				break;
			case COLUMN_AIRNUM:
				ret = ep.getAbsoluteNum();
				break;
			}
		}
		
		return ret;
	}

	public String getColumnName(int column)
	{
		String ret = "";
		
//		return super.getColumnName(column);
		switch( column )
		{
		case COLUMN_AIRDATE:
			ret = "Air Date";
			break;
		case COLUMN_TITLE:
			ret = "Episode Name";
			break;
		case COLUMN_AIRNUM:
			ret = "Episode Number";
			break;
		}

		return ret;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		Class<?> ret;
//		return super.getColumnClass(columnIndex);
		switch( columnIndex )
		{
		case COLUMN_AIRDATE:
			ret = Date.class;
			break;
		case COLUMN_TITLE:
			ret = String.class;
			break;
		case COLUMN_AIRNUM:
			ret = Integer.class;
			break;
		default:
			ret = Object.class;
		}
		
		return ret;
	}

}
