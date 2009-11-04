package org.fehlis.applications.EpisodeChecker;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.fehlis.applications.EpisodeChecker.data.WebGuide;

public class EpisodeChecker
{
	public static void main( String[] args )
	{
        try {
        	
            URL loc1 = new URL("http://epguides.com/BostonLegal/");
        	URL loc2 = new URL("http://epguides.com/Bleach/");
        	        	
    		WebGuide g1 = new WebGuide( "EpGuides.com", loc1, "Boston Legal" );
    		WebGuide g2 = new WebGuide( "EpGuides.com", loc2, "Bleach" );
    		
    		g1.parsePage();
    		g2.parsePage();
    		
    		for ( int i = 0; i < g1.getTheSerie().size(); i++ )
    		{
    			System.out.println( g1.getTheSerie().elementAt(i) );
    		}
    		
    		for ( int i = 0; i < g2.getTheSerie().size(); i++ )
    		{
    			System.out.println( g2.getTheSerie().elementAt(i) );
    		}
    		
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
