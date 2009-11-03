package units;

import java.util.Vector;

public class Mass extends PhysicalScalar
{
	protected static Vector<Unit> m_units = null;

	static
	{
		if ( Mass.m_units == null )
		{		
			Mass.m_units = new Vector<Unit>();
			Mass.m_units.add( new Unit( "g", new Double( 1.0 ) ) );
			Mass.m_units.add( new Unit( "kg", new Double( 1000.0 ) ) );
			Mass.m_units.add( new Unit( "t", new Double( 1000.0 * 1000.0 ) ) );
			
			System.out.println( "Mass: m_units = " + Mass.m_units );
		}
	}
	
	public Mass(double value)
	{
		super(value, Mass.m_units);
	}
	
	public Mass(double value, String unit) throws IllegalUnitException
	{
		super(value, Mass.m_units, unit);
	}
	
	/* 
	 * creates a Mass-object from a PhysicalUnit-object.
	 * only the value is taken, the unit is the default-unit
	 * of Mass
	 */
	
	public static Mass getMassFromVolumeAndDensity( Volume v, Density d )
	{
		System.err.println( "getMassFromVolumeAndDensity(): v = " + v.getValueNormalized() + ", d = " + d.getValueNormalized() );
		
		return new Mass( v.getValueNormalized() * d.getValueNormalized() ); 
	}
}
