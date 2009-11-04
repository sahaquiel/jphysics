package units;


public class Unit
{
	public String unitName;
	public double multiplicator;
	
	public Unit( String name )
	{
		unitName = name;
		multiplicator = 1;
	}
	
	public Unit( String name, double mult )
	{
		unitName = name;
		multiplicator = mult;
	}
	
	public boolean equals(Unit cmp)
	{
		return this.unitName.equals(cmp.unitName);
	}
	
	public String toString()
	{
		return "Unitname: " + unitName + ", mult: " + multiplicator;
	}
}
