package units;

import java.util.Vector;

public class Density extends PhysicalScalar
{
	public static final double DENSITY_OF_EARTH_ATMOPSPHERE = 1.2;	/* at sea level */ 
	
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( Density.m_units == null )
		{		
			Density.m_units = new Vector<Unit>();
			Density.m_units.add( new Unit( "g/m3", new Double( 1.0 ) ) );
			Density.m_units.add( new Unit( "kg/m3", new Double( 1000.0 ) ) );
			
			System.out.println( "Density: m_units = " + Density.m_units );
		}
	}
	
	public Density(double value, String unitName) throws IllegalUnitException
	{
		super(value, Density.m_units, unitName);
	}
	
	public Density(double value)
	{
		super(value, Density.m_units);
	}
	
	public static Density getDensityFromMassAndVolume( Mass m, Volume v )
	{
		return new Density( m.getValueNormalized() / v.getValueNormalized() );
	}
}
