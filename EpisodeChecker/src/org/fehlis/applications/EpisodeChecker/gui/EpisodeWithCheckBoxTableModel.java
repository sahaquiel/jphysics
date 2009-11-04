package org.fehlis.applications.EpisodeChecker.gui;

import org.fehlis.applications.EpisodeChecker.data.DatedAndOwnedEpisode;
import org.fehlis.applications.EpisodeTools.data.Serie;

public class EpisodeWithCheckBoxTableModel extends EpisodeTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7954865512190888768L;

	public static final int COLUMN_OWNED 	= 3;
	
	public EpisodeWithCheckBoxTableModel(Serie s)
	{
		super(s);
	}

	@Override
	public int getColumnCount() {
		return super.getColumnCount() + 1;
	}

	@Override
	public String getColumnName(int column) {
		
		String ret = null;
		
		if ( column < COLUMN_OWNED )
		{
			ret = super.getColumnName(column);
		}
		else
		{
			switch( column )
			{
				case COLUMN_OWNED:
				{
					ret = "owned?";
				}
			}
		}
		
		return ret;
	}

	@Override
	public Object getValueAt(int row, int column)
	{
		Object ret = null;
		
		if ( column < COLUMN_OWNED )
		{
			ret = super.getValueAt(row, column);
		}
		else
		{
			if ( row <= getSerie().size() )
			{	
				DatedAndOwnedEpisode currEp = (DatedAndOwnedEpisode) getSerie().elementAt( row );
				
				ret = new Boolean( currEp.isOwned() );
			}
		}
		
		return ret;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		if ( columnIndex < COLUMN_OWNED )
			return super.getColumnClass(columnIndex);
		else if ( columnIndex == COLUMN_OWNED )
			return Boolean.class;
		else
			return Object.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		// only the owned-column is editable atm
		return (columnIndex == COLUMN_OWNED);
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex)
	{
		if ( columnIndex == COLUMN_OWNED )
		{
			DatedAndOwnedEpisode currEp = (DatedAndOwnedEpisode) getSerie().elementAt( rowIndex );
			
			Boolean b = (Boolean) value;
			currEp.setIsOwned( b.booleanValue() );
		}
		
	}
}
