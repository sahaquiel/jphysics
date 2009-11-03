package units;

import java.util.Vector;

public class Velocity extends PhysicalScalar
{
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( Velocity.m_units == null )
		{		
			Velocity.m_units = new Vector<Unit>();
			Velocity.m_units.add( new Unit( "m/s", new Double( 1.0 ) ) );
			Velocity.m_units.add( new Unit( "km/s", new Double( 1000.0 ) ) );
			Velocity.m_units.add( new Unit( "km/h", new Double( 1000.0/3600.0 ) ) );
			Velocity.m_units.add( new Unit( "mi/s", new Double( 1609.0 ) ) );
			Velocity.m_units.add( new Unit( "mi/h", new Double( 1609.0/3600.0 ) ) );
			
			System.out.println( "Velocity: m_units = " + Velocity.m_units );
		}
	}
	
	public Velocity(double value, String unitName) throws IllegalUnitException
	{
		super( value, Velocity.m_units, unitName );		
	}
	
	public Velocity(double value)
	{
		super( value, Velocity.m_units );		
	}

	public static Velocity getVelocityFromDistanceAndTime( Distance d, Time t )
	{
		try {
			return new Velocity( d.getValueInUnit( "m" ) / t.getValueInUnit( "s" ), "m/s" );
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}

	public static Velocity getVelocityFromFrequencyAndWavelength( Frequency f, Distance lambda )
	{
		try {
			return new Velocity( f.getValueInUnit( "Hz" ) / lambda.getValueInUnit( "m" ), "m/s" );
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
}
