package units;

import java.util.Vector;

public class Time extends PhysicalScalar {

	protected static Vector<Unit> m_units = null;

	static
	{		
		Time.m_units = new Vector<Unit>();
		Time.m_units.add( new Unit( "s", new Double( 1.0 ) ) );
		Time.m_units.add( new Unit( "min", new Double( 60 ) ) );
		Time.m_units.add( new Unit( "h", new Double( 60*60 ) ) );
		Time.m_units.add( new Unit( "d", new Double( 60*60*24 ) ) );
		Time.m_units.add( new Unit( "w", new Double( 60*60*24*7 ) ) );
		Time.m_units.add( new Unit( "ms", new Double( 1.0 / 1000.0 ) ) );
		Time.m_units.add( new Unit( "us", new Double( 1.0 / 1000000.0 ) ) );
		Time.m_units.add( new Unit( "ns", new Double( 1.0 / 1000000000.0 ) ) );
		
		System.out.println( "Time: m_units = " + Time.m_units );
	}
	
	public Time(double value, String unit) throws IllegalUnitException
	{
		super( value, Time.m_units, unit );
	}
	
	public Time(double value)
	{
		super( value, Time.m_units );
	}	

	public static Time getTimeFromVelocityAndDistance( Velocity v, Distance d )
	{
		return new Time( d.getValueNormalized() / v.getValueNormalized() ); 
	}
}
