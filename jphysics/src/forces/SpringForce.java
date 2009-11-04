package forces;

import units.Distance;
import units.Force;
import units.IllegalUnitException;
import units.SpringConstant;

public class SpringForce
{
	/* spring force */
	public static Force getForceByDisplacementAndConstant( Distance k, SpringConstant x )
	{
		try {
			
			return new Force( k.getValueInUnit( "m" ) * (-1) * x.getValueInUnit( "N/m" ), "N" );
			
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
}
