package examples.force.friction;

import forces.CentripedalForce;
import units.Accelleration;
import units.Distance;
import units.Force;
import units.IllegalUnitException;
import units.Mass;
import units.Velocity;

public class Example1
{

	Force normalForce;		/* Fnormal */
	
	public Example1()
	{
		try
		{
			Mass m; 
			Distance r;
			Velocity v;
			Force Fg;
			
			r = new Distance( 3180, "m" );
			v = new Velocity( 123, "m/s" );
			m = new Mass( 2.0 * Math.pow(10,5), "kg" );
			
			Fg = Force.getForceByMassAndAccelleration(m, new Accelleration(9.81, "m/s^2"));
			Fg.setAngle( 270 );
			Fg.setUnit( "N" );
			
			Velocity v2 = new Velocity( v );
			
			Force Fy = new Force( Fg );
			Fy.setAngle( 90 );
			
			System.out.println( "Fg: " + Fg );
			System.out.println( "Fy: " + Fy );
			
	//		Force FyFg = new Force ( Fy.getValue(), Fy.getAngle() );
	//		FyFg.addVector(Fg);	
	//		System.out.println( "Fy+Fg: " + FyFg );
			
			Force Fx = CentripedalForce.getForceByMassVelocityAndRadius(m, v, r);
			Fx.setAngle( 180 );		// not really necessary, since Fx is already x-component of another Force
			
			System.out.println( "Fx: " + Fx );
			
			double tana = Fx.getValue() / Fy.getValue();
			
			System.out.println( "Result: tana = " + tana + " => " + Math.toDegrees( Math.atan( tana ) ) + "°" );
		
		}
		catch( IllegalUnitException e )
		{
			e.printStackTrace();
		}		
	}
	
	public static void main( String args[] )
	{
		new Example1();
	}
}
