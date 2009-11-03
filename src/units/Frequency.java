package units;

import java.util.Vector;

public class Frequency extends PhysicalScalar
{
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( Frequency.m_units == null )
		{		
			Frequency.m_units = new Vector<Unit>();
			Frequency.m_units.add( new Unit( "Hz", new Double( 1.0 ) ) );		/* 1/s */
			Frequency.m_units.add( new Unit( "1/s", new Double( 1.0 ) ) );		/* 1/s */
			Frequency.m_units.add( new Unit( "kHz", new Double( 1000.0 ) ) );
			Frequency.m_units.add( new Unit( "MHz", new Double( 1000000.0 ) ) );
			Frequency.m_units.add( new Unit( "GHz", new Double( 1000000000.0 ) ) );
			
			System.out.println( "Frequency: m_units = " + Frequency.m_units );
		}
	}
	
	public Frequency(double value, String unitName) throws IllegalUnitException
	{
		super(value, Frequency.m_units, unitName);
	}
	
	public Frequency(double value)
	{
		super(value, Frequency.m_units);
	}
	
	public static Frequency getFrequencyFromSpringConstantAndMass( SpringConstant k, Mass m )
	{
		try {
			
			return new Frequency( ( 1/2*Math.PI ) * Math.sqrt( k.getValueInUnit( "N/m" ) / m.getValueInUnit( "kg" ) ), "Hz" );
			
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
	public static Frequency getFrequencyFromVelocityAndWavelength( Velocity v, Distance lambda )
	{
		try {
			
			return new Frequency( v.getValueInUnit( "m/s" ) / lambda.getValueInUnit( "m" ), "Hz" );
			
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
	
}
