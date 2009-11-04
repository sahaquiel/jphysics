package units;

import java.util.Vector;

public class Force extends PhysicalVector
{
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( Force.m_units == null )
		{		
			Force.m_units = new Vector<Unit>();
			Force.m_units.add( new Unit( "N", new Double( 1000.0 ) ) );	/* kg * m / s^2 */
			Force.m_units.add( new Unit( "gm/s^2", new Double( 1.0 ) ) );	/* g * m / s^2 */
			
			System.out.println( "Force: m_units = " + Force.m_units );
		}
	}
	
	public Force(double value, String unitName) throws IllegalUnitException
	{
		super( value, Force.m_units, unitName );		
	}
	
	public Force(double value, double angle)
	{
		super( value, angle, Force.m_units );		
	}
	
	public Force(double value)
	{
		super( value, Force.m_units );		
	}

	// copy constructor
	public Force( Force src )
	{
		super( src );
	}
	
	public static Force getForceByMassAndAccelleration( Mass m, Accelleration a )
	{
		try
		{
			return new Force( m.getValueInUnit( "kg" ) * a.getValueInUnit( "m/s^2" ), "N" );
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}

}
