package examples.velocity.example2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import units.Distance;
import units.IllegalUnitException;
import units.PhysicalScalar;
import units.Time;
import units.Velocity;

public class Example2 extends JFrame implements ActionListener
{
	Velocity v;
	Distance d;
	Time t;
	
	JLabel lb_v;
	JTextField tf_v;
	JComboBox cb_v;
	
	JLabel lb_d;
	JTextField tf_d;
	JComboBox cb_d;

	JLabel lb_t;
	JTextField tf_t;
	JComboBox cb_t;
	
	JButton bt_calc_v;
	JButton bt_calc_d;
	JButton bt_calc_t;
	
	public Example2()
	{
		v = new Velocity( 1 );
		d = new Distance( 2 );
		t = new Time( 3 );
		
		lb_v = new JLabel( "velocity: " );
		tf_v = new JTextField( "" );
		cb_v = new JComboBox( v.getUnitNames() );
		
		lb_d = new JLabel( "distance: " );
		tf_d = new JTextField( "" );
		cb_d = new JComboBox( d.getUnitNames() );

		lb_t = new JLabel( "time: " );
		tf_t = new JTextField( "" );
		cb_t = new JComboBox( t.getUnitNames() );
		
		cb_v.addActionListener(this);
		cb_d.addActionListener(this);
		cb_t.addActionListener(this);
		
		bt_calc_v = new JButton( "Calc!" );
		bt_calc_v.addActionListener(this);

		bt_calc_d = new JButton( "Calc!" );
		bt_calc_d.addActionListener(this);

		bt_calc_t = new JButton( "Calc!" );
		bt_calc_t.addActionListener(this);
		
		setLayout( new GridLayout(3,4) );
		
		add( lb_v );
		add( tf_v );
		add( cb_v );
		add( bt_calc_v );

		add( lb_d );
		add( tf_d );
		add( cb_d );
		add( bt_calc_d );

		add( lb_t );
		add( tf_t );
		add( cb_t );
		add( bt_calc_t );
		
		
		setDefaultCloseOperation( DISPOSE_ON_CLOSE  );
		
		pack();
		setVisible( true );
		
	}
	
	private void refreshValuesFromGui()
	{
		try
		{
			v.setValue( Double.parseDouble( tf_v.getText() ) );
		}
		catch ( NumberFormatException e ) {}

		try
		{
			d.setValue( Double.parseDouble( tf_d.getText() ) );
		}
		catch ( NumberFormatException e ) {}

		try
		{
			t.setValue( Double.parseDouble( tf_t.getText() ) );
		}
		catch ( NumberFormatException e ) {}
	}
	
	private void recalc( PhysicalScalar val )
	{
		try
		{
			if ( val == v )
			{
				v = Velocity.getVelocityFromDistanceAndTime(d, t);
				v.setUnit( (String) cb_v.getSelectedItem() );
				tf_v.setText( v.getValue() + "" );
			}
			else if ( val == d )
			{
				d = Distance.getDistanceFromTimeAndVelocity(t, v);
				d.setUnit( (String) cb_d.getSelectedItem() );
				tf_d.setText( d.getValue() + "" );
			}
			else if ( val == t )
			{
				t = Time.getTimeFromVelocityAndDistance(v, d);
				t.setUnit( (String) cb_t.getSelectedItem() );
				tf_t.setText( t.getValue() + "" );
			}
		
		} catch (IllegalUnitException e1) {
			// TODO Auto-generated catch block
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
		try {
			
			refreshValuesFromGui();
			
			if ( e.getSource() == bt_calc_v )
			{
				recalc( v );
			}
			else if ( e.getSource() == bt_calc_d )
			{
				recalc( d );
			}
			else if ( e.getSource() == bt_calc_t )
			{
				recalc( t );
			}
			
			else if ( e.getSource() == cb_v )
			{
					v.setUnit( (String) cb_v.getSelectedItem() );
				tf_v.setText( v.getValue() + "" );
			}
			else if ( e.getSource() == cb_d )
			{
				d.setUnit( (String) cb_d.getSelectedItem() );
				tf_d.setText( d.getValue() + "" );
			}
			else if ( e.getSource() == cb_t )
			{
				t.setUnit( (String) cb_t.getSelectedItem() );
				tf_t.setText( t.getValue() + "" );
			}
			
		} catch (IllegalUnitException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
