package org.fehlis.applications.EpisodeChecker.gui;

import java.awt.Component;
import java.util.Date;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.fehlis.applications.EpisodeChecker.data.DatedAndOwnedEpisode;
import org.fehlis.applications.EpisodeChecker.data.EpisodeFilter;

public class EpisodeDateFilterTableCellRenderer implements TableCellRenderer 

//extends DefaultTableCellRenderer
{	
	private boolean filterEnabled;
	private Date m_filterDate;

	private JLabel m_label;
	private JCheckBox m_cb;
	
	public EpisodeDateFilterTableCellRenderer()
	{
		super();
		filterEnabled = false;
		m_filterDate = new Date();
		
		preparePossibleComponents();
	}
	
	private void preparePossibleComponents()
	{
		m_label = new JLabel();		
		m_label.setOpaque( true );
		
		m_cb = new JCheckBox();
		m_cb.setOpaque( true );
	}
	
	public boolean isFilterEnabled() {
		return filterEnabled;
	}

	public void setFilterEnabled(boolean filterEnabled) {
		this.filterEnabled = filterEnabled;
	}	
	
    public Component getTableCellRendererComponent (JTable table,
                                                        Object value,
                                                        boolean isSelected,
                                                        boolean hasFocus,
                                                        int row, int column)
    {
    	Component comp = null;

    	// get the real column index of the model - since it might've been moved in the GUI
    	int realColumn = table.convertColumnIndexToModel( column ); 
    	
    	// get the real row index of the mode - since it might've been moved by a sorter
    	int realRow = table.convertRowIndexToModel( row );
    	
    	switch( realColumn )
    	{
    		case EpisodeTableModel.COLUMN_AIRDATE:
    		{
    			m_label.setText( value.toString() );
    			
	    		comp = m_label;
	    		
	    		break;
    		}
    		case EpisodeWithCheckBoxTableModel.COLUMN_OWNED:
    		{
    			Boolean b = (Boolean)value;
    			m_cb.setSelected( b.booleanValue() );
    			
	    		comp = m_cb;
	    		
	    		break;
    		}
    		case EpisodeTableModel.COLUMN_AIRNUM:
    		{
    			m_label.setText( value.toString() );
    			
	    		comp = m_label;
	    			    		
	    		break;
    		}
    		case EpisodeTableModel.COLUMN_TITLE:
    		{
    			m_label.setText( (String) value );
    					    	
	    		comp = m_label;
	    		
	    		break;
    		}
	    	default:
	    	{
	    		m_label.setText( "<unknown column!>" );
	    		
	    		comp = m_label;
	    	}
    	}
    	
    	if ( filterEnabled )
		{
    		// get object representation
    		DatedAndOwnedEpisode ep = (DatedAndOwnedEpisode) ( ( EpisodeWithCheckBoxTableModel ) table.getModel() ).getEpisodeObjectForRow( realRow );
    		
    		if ( EpisodeFilter.isEpisodeAiredButNotOwned( ep ) )
    		{
    			comp.setBackground( java.awt.Color.yellow );
    		}
    		else if ( EpisodeFilter.isEpisodeNotAired( ep ) )
    		{
				comp.setBackground( java.awt.Color.green );
    		}
    		else
			{
				comp.setBackground( java.awt.Color.lightGray );
			}
/*    		
			Date odate = (Date) table.getModel().getValueAt( realRow, 0 );
    		
			if ( m_filterDate.after( odate ) )
			{
				comp.setBackground( java.awt.Color.lightGray );
			}
			else
			{
				comp.setBackground( java.awt.Color.green );
			}
*/
			
		}
    	
    	if ( isSelected )
    	{
    		comp.setBackground( java.awt.Color.blue );
    	}
    	
    	return comp;       
    }
}
