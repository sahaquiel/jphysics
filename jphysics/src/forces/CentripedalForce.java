package forces;

import units.Distance;
import units.Force;
import units.IllegalUnitException;
import units.Mass;
import units.Velocity;

public class CentripedalForce
{
	/* centripedal force */
	public static Force getForceByMassVelocityAndRadius( Mass m, Velocity v, Distance r )
	{
		try {
			
			return new Force( ( m.getValueInUnit("kg") * Math.pow( v.getValueInUnit("m/s"), 2 ) ) / r.getValueInUnit("m"), "N" );
			
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
	}
}
