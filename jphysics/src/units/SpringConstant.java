package units;

import java.util.Vector;

public class SpringConstant extends PhysicalScalar
{
	protected static Vector<Unit> m_units = null;
	
	static
	{
		if ( SpringConstant.m_units == null )
		{		
			SpringConstant.m_units = new Vector<Unit>();
			SpringConstant.m_units.add( new Unit( "N/m", new Double( 1.0 ) ) );
			
			System.out.println( "SpringConstant: m_units = " + SpringConstant.m_units );
		}
	}
	
	public SpringConstant(double value, String unitName) throws IllegalUnitException
	{
		super(value, SpringConstant.m_units, unitName);
	}
	
	public SpringConstant(double value)
	{
		super(value, SpringConstant.m_units);
	}
	
	public static SpringConstant getSpringConstantFromMassAndVolume( Mass m, Volume v )
	{
		return new SpringConstant( m.getValueNormalized() / v.getValueNormalized() );
	}
}
