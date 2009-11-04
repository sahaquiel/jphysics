package examples.mass;

import units.Density;
import units.IllegalUnitException;
import units.Mass;
import units.Volume;

public class Example1
{
	public Example1()
	{
		try
		{
			Mass mass;
			Volume volume = new Volume( 3.44 );
			Density density = new Density( 0.5 );
			
			mass = Mass.getMassFromVolumeAndDensity(volume, density);
			
			System.out.println( "Volume: " + volume + ", Density: " + density + " => Mass: " + mass );
	
			/* ---- */
			
			density.setValue( 0.25 );
			
			mass = Mass.getMassFromVolumeAndDensity(volume, density);
			
			System.out.println( "Volume: " + volume + ", Density: " + density + " => Mass: " + mass );
	
			/* ---- */
			
			mass.setValue( 2 );
			
			volume = Volume.getVolumeFromMassAndDensity(mass, density);
			
			System.out.println( "Mass: " + mass + ", Density: " + density + " => Volume: " + volume );
			
			mass.setUnit( "kg" );
			System.out.println( "Mass: " + mass + ", Density: " + density + " => Volume: " + volume );
			
			mass.setValue( 10, "kg" );
			mass.setUnit( "g" );
			System.out.println( "Mass: " + mass + ", Density: " + density + " => Volume: " + volume );	
			
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
