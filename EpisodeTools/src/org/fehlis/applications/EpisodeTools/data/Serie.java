package org.fehlis.applications.EpisodeTools.data;


public class Serie extends java.util.Vector<Episode>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2137647951282452626L;
	
	private int num;
	private String name;
	private String renameMask;
	
	public Serie()
	{
		this("none");
	}
	
	public Serie( String name )
	{
		num = 1;
		this.name = name;
		renameMask = "%1$s - %2$02d%3$02d - %6$dACX%4$02d - %5$s";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRenameMask() {
		return renameMask;
	}
	public void setRenameMask(String mask) {
		this.renameMask = mask;
	}	
}
