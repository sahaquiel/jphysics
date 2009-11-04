package units;

import java.util.Vector;

public class IllegalUnitException extends Throwable
{
	private String m_errMsg;
	
	public IllegalUnitException( String errMsg )
	{
		m_errMsg = errMsg;
	}
	
	public IllegalUnitException( String unitNameRequested, Vector<String> unitNamesAvailable )
	{
		m_errMsg = "illegal Unit: '" + unitNameRequested + "'! Available: " + unitNamesAvailable;
	}
	
	public String toString()
	{
		return m_errMsg;
	}
}
