package units;

import java.util.Vector;

public class Volume extends PhysicalScalar
{
	protected static Vector<Unit> m_units = null;

	static
	{
		if ( Volume.m_units == null )
		{		
			Volume.m_units = new Vector<Unit>();
			Volume.m_units.add( new Unit( "m3", new Double( 1.0 ) ) );
			
			System.out.println( "Volume: m_units = " + Volume.m_units );
		}
	}
	
	public Volume(double value)
	{
		super(value, Volume.m_units );
	}
	
	public Volume(double value, String unitName) throws IllegalUnitException
	{
		super(value, Volume.m_units, unitName);
	}
	
	public static Volume getVolumeFromMassAndDensity( Mass m, Density d )
	{
		return new Volume( m.getValueNormalized() / d.getValueNormalized() ); 
	}
}
