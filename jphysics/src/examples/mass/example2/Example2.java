package examples.mass.example2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import units.Density;
import units.IllegalUnitException;
import units.Mass;
import units.PhysicalScalar;
import units.Volume;

public class Example2 extends JFrame implements ActionListener
{
	Mass m;
	Density d;
	Volume v;
	
	JLabel lb_m;
	JTextField tf_m;
	JComboBox cb_m;
	
	JLabel lb_d;
	JTextField tf_d;
	JComboBox cb_d;

	JLabel lb_v;
	JTextField tf_v;
	JComboBox cb_v;
	
	JButton bt_calc_m;
	JButton bt_calc_d;
	JButton bt_calc_v;
	
	
	public Example2()
	{
		m = new Mass( 1 );
		d = new Density( 2 );
		v = new Volume( 3 );
		
		lb_m = new JLabel( "mass: " );
		tf_m = new JTextField( "" );
		cb_m = new JComboBox( m.getUnitNames() );
		
		lb_d = new JLabel( "density: " );
		tf_d = new JTextField( "" );
		cb_d = new JComboBox( d.getUnitNames() );

		lb_v = new JLabel( "volume: " );
		tf_v = new JTextField( "" );
		cb_v = new JComboBox( v.getUnitNames() );
		
		cb_m.addActionListener(this);
		cb_d.addActionListener(this);
		cb_v.addActionListener(this);
		
		bt_calc_m = new JButton( "Calc!" );
		bt_calc_m.addActionListener(this);

		bt_calc_d = new JButton( "Calc!" );
		bt_calc_d.addActionListener(this);

		bt_calc_v = new JButton( "Calc!" );
		bt_calc_v.addActionListener(this);
		
		setLayout( new GridLayout(3,4) );
		
		add( lb_m );
		add( tf_m );
		add( cb_m );
		add( bt_calc_m );

		add( lb_d );
		add( tf_d );
		add( cb_d );
		add( bt_calc_d );

		add( lb_v );
		add( tf_v );
		add( cb_v );
		add( bt_calc_v );
		
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE  );
		
		pack();
		setVisible( true );
		
	}
	
	private void refreshValuesFromGui()
	{
		try
		{
			m.setValue( Double.parseDouble( tf_m.getText() ), (String) cb_m.getSelectedItem() );
		}
		catch ( NumberFormatException e ) {}
		catch ( IllegalUnitException e ) {}		

		try
		{
			d.setValue( Double.parseDouble( tf_d.getText() ), (String) cb_d.getSelectedItem() );
		}
		catch ( NumberFormatException e ) {}
		catch ( IllegalUnitException e ) {}		

		try
		{
			v.setValue( Double.parseDouble( tf_v.getText() ), (String) cb_v.getSelectedItem() );
		}
		catch ( NumberFormatException e ) {}
		catch ( IllegalUnitException e ) {}		
	}
	
	private void recalc( PhysicalScalar val )
	{
		try
		{
			if ( val == m )
			{
				m = Mass.getMassFromVolumeAndDensity(v, d);
				tf_m.setText( m.getValueInUnit( (String) cb_m.getSelectedItem() ) + "" );
			}
			else if ( val == d )
			{
				d = Density.getDensityFromMassAndVolume(m, v);
				tf_d.setText( d.getValueInUnit( (String) cb_d.getSelectedItem() ) + "" );
			}
			else if ( val == v )
			{
				v = Volume.getVolumeFromMassAndDensity(m, d);
				tf_v.setText( v.getValueInUnit( (String) cb_v.getSelectedItem() ) + "" );
			}
		}
		catch( IllegalUnitException e1 )
		{
			e1.printStackTrace();
		}				
	}
	
	
	public static void main( String[] args )
	{
		new Example2();
	}


	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			refreshValuesFromGui();
			
			if ( e.getSource() == bt_calc_m )
			{
				recalc( m );
			}
			else if ( e.getSource() == bt_calc_d )
			{
				recalc( d );
			}
			else if ( e.getSource() == bt_calc_v )
			{
				recalc( v );
			}
			
			else if ( e.getSource() == cb_m )
			{
				tf_m.setText( m.getValueInUnit( (String) cb_m.getSelectedItem() ) + "" );
			}
			else if ( e.getSource() == cb_d )
			{
				tf_d.setText( d.getValueInUnit( (String) cb_d.getSelectedItem() ) + "" );
			}
			else if ( e.getSource() == cb_v )
			{
				tf_v.setText( v.getValueInUnit( (String) cb_v.getSelectedItem() ) + "" );
			}
			
		}
		catch( IllegalUnitException e1 )
		{
			e1.printStackTrace();
		}				
	}

}
