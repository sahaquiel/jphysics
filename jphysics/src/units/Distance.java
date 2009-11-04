package units;

import java.util.Vector;

public class Distance extends PhysicalScalar {
	
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( Distance.m_units == null )
		{		
			Distance.m_units = new Vector<Unit>();
			Distance.m_units.add( new Unit( "m", new Double( 1.0 ) ) );
			Distance.m_units.add( new Unit( "km", new Double( 1000.0 ) ) );
			Distance.m_units.add( new Unit( "cm", new Double( 1.0/10.0 ) ) );
			Distance.m_units.add( new Unit( "dm", new Double( 1.0/100.0 ) ) );
			Distance.m_units.add( new Unit( "mm", new Double( 1.0/1000.0 ) ) );
			Distance.m_units.add( new Unit( "um", new Double( 1.0/1000000.0 ) ) );
			Distance.m_units.add( new Unit( "nm", new Double( 1.0/1000000000.0 ) ) );
			Distance.m_units.add( new Unit( "mi", new Double( 1609.0 ) ) );		/* miles */
			
			System.out.println( "Distance: m_units = " + Distance.m_units );
		}
	}

	public static Distance getDistanceFromTimeAndVelocity( Time t, Velocity v )
	{
		try
		{
			return new Distance( t.getValueInUnit( "s" ) * v.getValueInUnit( "m/s" ) );
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	public Distance(double value)
	{
		super(value, Distance.m_units);
	}
	
	public Distance(double value, String unit ) throws IllegalUnitException
	{
		super(value, Distance.m_units, unit);
	}
}
