package examples.velocity;

import units.IllegalUnitException;
import units.Velocity;

public class Example1
{
	public Example1()
	{
/*		
		Time time;
		Velocity velocity = new Velocity( 10 );
		Distance distance = new Distance( 5000 );
		
		time = Time.getTimeFromVelocityAndDistance(velocity, distance);
		
		System.out.println( "Velocity: " + velocity + ", Distance: " + distance + " => Time: " + time );

		velocity.setCurrUnitIndex(1);		
		System.out.println( "Velocity: " + velocity + ", Distance: " + distance + " => Time: " + time );

		velocity.setUnit( "km/h" );
		System.out.println( "Velocity: " + velocity + ", Distance: " + distance + " => Time: " + time );
*/
		
		try {
			Velocity v;			
			v = new Velocity( 10, "m/s" );
			v.setCurrUnitIndex(2);
			v.setValue( 100 );
			System.out.println( "Velocity: " + v );
			
			v.setCurrUnitIndex(1);
			System.out.println( "Velocity: " + v );
		
			v.setUnit( "km/h" );
			System.out.println( "Velocity: " + v );
	
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static void main( String args[] )
	{
		new Example1();
	}
}
