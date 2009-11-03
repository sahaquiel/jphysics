package examples.force.spring;

import units.Distance;
import units.Force;
import units.IllegalUnitException;

public class Example1
{
	public Example1() throws IllegalUnitException
	{
		Force Fs;
		
		Distance x = new Distance( 2, "cm" );
		
		
	}
	
	public static void main( String args[] )
	{
		try {
			new Example1();
		} catch (IllegalUnitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
