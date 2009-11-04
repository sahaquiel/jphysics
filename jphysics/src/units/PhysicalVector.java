package units;

import java.util.Vector;

public abstract class PhysicalVector extends PhysicalScalar
{
	protected double m_angle;
	
	protected PhysicalVector(double scalarValue, Vector<Unit> units)
	{
		super(scalarValue, units);
		
		m_angle = 0;
	}

	protected PhysicalVector(double scalarValue, double angle, Vector<Unit> units)
	{
		super(scalarValue, units);
		
		m_angle = angle;
	}

	protected PhysicalVector(double scalarValue, Vector<Unit> units, String unit) throws IllegalUnitException
	{
		super(scalarValue, units, unit);
		
		m_angle = 0;
	}
	
	// copy constructor
	protected PhysicalVector( PhysicalVector src )
	{
		super( src );
		
		this.m_angle = src.m_angle;
	}

	public double getXValue()
	{
		return Math.cos( Math.toRadians(m_angle) ) * getValue();
	}
	
	public double getYValue()
	{
		return Math.sin( Math.toRadians(m_angle) ) * getValue();
	}
	
	public double getAngle() {
		return m_angle;
	}

	public void setAngle(double mAngle) {
		m_angle = mAngle;
	}

	/**
	 * Adds the given PhysicalVector to the current Object.
	 * @param v
	 */
	public void addVector( PhysicalVector v )
	{
		// == Step 1: get the resulting value 
		// get x and y values of both vectors and add them
		double xsum = getXValue() + v.getXValue();
		double ysum = getYValue() + v.getYValue();
		
		// get the scalar value of the vector via pythagoras
		double newValue = Math.sqrt( Math.pow(xsum, 2.0) + Math.pow( ysum, 2.0 ) );
		
		// == Step 2: get the resulting angle
		// calculate sinus-value from cathede (x-value) and hypotenuse (value from above)
		double newTan = xsum / ysum;
		// calculate angle from sinus with asinus (result is in radians)
		double newAngle = Math.atan( newTan );
		
		// set value and angle of current object to result
		setValue( newValue );
		setAngle( Math.toDegrees( newAngle ) );
		
	}
	
	public String toString()
	{
		return super.toString() + " ( " + getXValue() + ", " + getYValue() + ", " + getAngle() + "° )";
	}
}
