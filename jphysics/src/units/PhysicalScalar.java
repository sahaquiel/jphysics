package units;

import java.util.Vector;

public abstract class PhysicalScalar
{
//	protected Unit m_unit;
	protected Vector<Unit> m_units = null;
	protected int m_currUnitIndex;
	
	/* m_value is the actual value, always converted automatically */
	private double m_value;
	
	public Vector<Unit> getUnits() {
		return m_units;
	}
	
	public Vector<String> getUnitNames()
	{
		Vector<String> ret = new Vector<String>(m_units.size());
		
		for ( int i = 0; i < m_units.size(); i++ )
		{
			ret.add( m_units.elementAt( i ).unitName );
		}
		
		return ret;
	}

	protected double getValueNormalized()
	{
		return m_value;
	}
	
	protected void setUnits(Vector<Unit> mUnits) {
		m_units = mUnits;
	}

	public double getValue()
	{
		return m_value / m_units.elementAt(m_currUnitIndex).multiplicator;
	}

	public double getValueInUnit( String unitName ) throws IllegalUnitException
	{
		return m_value / m_units.elementAt(getUnitIndex( unitName )).multiplicator;
	}
	
	protected void setDefaultUnit()
	{
		setCurrUnitIndex(0);
	}
	
	public void setValue(double mValue)
	{
		m_value = mValue * m_units.elementAt(m_currUnitIndex).multiplicator;
	}

	public void setValue(double mValue, String unit) throws IllegalUnitException
	{
		setUnit( unit );
		setValue( mValue );
//		m_value = mValue * m_units.elementAt(m_currUnitIndex).multiplicator;
	}

	public int getCurrUnitIndex() {
		return m_currUnitIndex;
	}

	public void setCurrUnitIndex(int mCurrUnitIndex) {
		m_currUnitIndex = mCurrUnitIndex;
	}

	private int getUnitIndex( String unitName ) throws IllegalUnitException
	{	
		int i, ret = 0;
		
		for ( i = 0; i < m_units.size(); i++ )
		{
			if ( m_units.elementAt( i ).unitName.equals( unitName ) )
			{
				ret = i;
				break;
			}
		}
		
		if ( i == m_units.size() )
		{
			throw new IllegalUnitException( unitName, getUnitNames() );
		}
		
		return ret;
	}	
	
	public void setUnit( String unitName ) throws IllegalUnitException
	{	
		m_currUnitIndex = getUnitIndex( unitName );
		
		if ( m_currUnitIndex == -1 )
		{
			m_currUnitIndex = 0;
			throw new IllegalUnitException( "unknown unit: " + unitName + "! Available: " + getUnitNames() );
		}
	}
	
	public void setUnitIndex( int i ) throws IllegalUnitException
	{
		if ( m_units == null || i < 0 || i >= m_units.size() )
		{
			m_currUnitIndex = 0;
			throw new IllegalUnitException( "unknown unit-index: " + i + "! Available: " + getUnitNames() );
		}
		else
		{
			m_currUnitIndex = i;
		}
	}
	
	protected PhysicalScalar( double value, Vector<Unit> units )
	{
		m_units = units; 
		m_currUnitIndex = 0;
		setValue( value );		
	}	
	
	protected PhysicalScalar( double value, Vector<Unit> units, String unitName ) throws IllegalUnitException
	{
		m_units = units; 
		setUnit( unitName );
		setValue( value );		
	}	
	
	// copy constructor
	protected PhysicalScalar( PhysicalScalar src )
	{
		m_value = src.m_value;
		m_units = src.m_units;
		m_currUnitIndex = src.m_currUnitIndex;
	}

/*	
	protected static void normalizeQuality( PhysicalUnit factor1, PhysicalUnit factor2 )
	{
		// TODO: make sure we introduce a Unit-type, to ensure only
		//       compatible units can be multiplied
		
		if ( factor1.m_quality != factor2.m_quality )
		{
			double mult = factor1.m_quality / factor2.m_quality;
			
			factor2.m_value *= mult;
			factor2.m_quality = factor1.m_quality;
		}
	}
*/
	
	public String toString()
	{
		return getValue() + " " + m_units.elementAt(getCurrUnitIndex()).unitName;
	}
}
