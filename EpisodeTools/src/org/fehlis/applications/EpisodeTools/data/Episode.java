package org.fehlis.applications.EpisodeTools.data;

public class Episode implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6240620683526607060L;
	private String title;
	private int prodNr;
	private int seasonProdNr;
	private int airNr;
	private int seasonNr;
	
	public int getSeasonNr() {
		return seasonNr;
	}
	public void setSeasonNr(int seasonNr) {
		this.seasonNr = seasonNr;
	}
	public int getAirNr() {
		return airNr;
	}
	public void setAirNr(int airNr) {
		this.airNr = airNr;
	}
	public int getProdNr() {
		return prodNr;
	}
	public void setProdNr(int prodNr) {
		this.prodNr = prodNr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSeasonProdNr() {
		return seasonProdNr;
	}
	public void setSeasonProdNr(int seasonProdNr) {
		this.seasonProdNr = seasonProdNr;
	}
	@Override
	public String toString() {
		return "" + seasonNr + ", " + airNr + ", " + seasonProdNr + ", " + prodNr + ", " + title;
	}
	@Override
	public boolean equals(Object obj)
	{
		boolean ret = false;
		Episode ep = (Episode) obj;
		
		if ( ep.airNr == this.airNr &&
			 ep.prodNr == this.prodNr &&
			 ep.seasonNr == this.seasonNr &&	
			 ep.seasonProdNr == this.seasonProdNr &&
			 ep.title.equals(this.title) )
		{
			ret = true;
		}
			
		return ret;
	}
}
