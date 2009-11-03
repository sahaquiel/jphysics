package units;

import java.util.Vector;

public class Accelleration extends PhysicalScalar
{
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( Accelleration.m_units == null )
		{		
			Accelleration.m_units = new Vector<Unit>();
			Accelleration.m_units.add( new Unit( "m/s^2", new Double( 1.0 ) ) );
			Accelleration.m_units.add( new Unit( "m/h^2", new Double( 1.0 / ( 3600.0 * 3600.0 ) ) ) );
			
			System.out.println( "Accelleration: m_units = " + Accelleration.m_units );
		}
	}
	
	public Accelleration(double value, String unitName) throws IllegalUnitException
	{
		super( value, Accelleration.m_units, unitName );		
	}
	
	public Accelleration(double value)
	{
		super(value, Accelleration.m_units);
	}
}
