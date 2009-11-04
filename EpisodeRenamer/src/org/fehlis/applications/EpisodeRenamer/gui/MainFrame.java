package org.fehlis.applications.EpisodeRenamer.gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9036097657977508516L;
	private JPanel jContentPane = null;
	private JPanel jPanel = null;
	private JButton jButtonSelect = null;
	private JButton jButtonRename = null;
	private JScrollPane jScrollPane = null;
	private JList jList = null;
	private JPanel jPanel1 = null;
	private JLabel jLabel = null;
	private JTextField jTextField_SeriesName = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField_SeasonNum = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField_AirNum = null;
	private JLabel jLabel3 = null;
	private JTextField jTextField_SeasonProdNum = null;
	private JLabel jLabel4 = null;
	private JTextField jTextField_ProdNum = null;
	private JLabel jLabel5 = null;
	private JTextField jTextField_EpisodeTitle = null;
	private JPanel jPanel2 = null;
	private JPanel jPanel3 = null;
	private JLabel jLabel6 = null;
	private JTextField jTextField_GeneratedName = null;
	private JLabel jLabel7 = null;
	private JTextField jTextField_RenameMask = null;
	private JButton jButtonSaveMask = null;
	private JButton jButtonLoadMask = null;

	/**
	 * This is the default constructor
	 */
	public MainFrame() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(496, 328);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setContentPane(getJContentPane());
		this.setTitle("Episode Renamer");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getJPanel1(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getJPanel2(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.add(getJButtonSelect(), null);
			jPanel.add(getJButtonRename(), null);
			jPanel.add(getJButtonLoadMask(), null);
			jPanel.add(getJButtonSaveMask(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jButtonSelect	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getJButtonSelect() {
		if (jButtonSelect == null) {
			jButtonSelect = new JButton();
			jButtonSelect.setText("Select Dir");
		}
		return jButtonSelect;
	}

	/**
	 * This method initializes jButtonRename	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getJButtonRename() {
		if (jButtonRename == null) {
			jButtonRename = new JButton();
			jButtonRename.setText("Rename Selected");
		}
		return jButtonRename;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	protected JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	protected JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setFont(new java.awt.Font("Lucida Console", java.awt.Font.BOLD, 12));
			jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
			jList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
				public void valueChanged(javax.swing.event.ListSelectionEvent e) {
					System.out.println("valueChanged()"); // TODO Auto-generated Event stub valueChanged()
				}
			});
		}
		return jList;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints13.gridx = 1;
			gridBagConstraints13.gridy = 6;
			gridBagConstraints13.ipadx = 184;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints12.ipadx = 5;
			gridBagConstraints12.gridy = 6;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 5;
			gridBagConstraints11.ipadx = 91;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints10.ipadx = 5;
			gridBagConstraints10.gridy = 5;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.gridx = 1;
			gridBagConstraints9.gridy = 4;
			gridBagConstraints9.ipadx = 177;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints8.ipadx = 5;
			gridBagConstraints8.gridy = 4;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints7.gridx = 1;
			gridBagConstraints7.gridy = 3;
			gridBagConstraints7.ipadx = 177;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints6.ipadx = 5;
			gridBagConstraints6.gridy = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.ipadx = 177;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints4.ipadx = 5;
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.ipadx = 177;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.fill = java.awt.GridBagConstraints.NONE;
			gridBagConstraints2.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints2.ipadx = 5;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridx = 1;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.ipadx = 124;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
			gridBagConstraints.ipadx = 5;
			gridBagConstraints.gridy = 0;
			jLabel7 = new JLabel();
			jLabel7.setText("Rename Mask");
			jLabel5 = new JLabel();
			jLabel5.setText("Episode Title");
			jLabel4 = new JLabel();
			jLabel4.setText("Prod #");
			jLabel3 = new JLabel();
			jLabel3.setText("Season Prod #");
			jLabel2 = new JLabel();
			jLabel2.setText("Air #");
			jLabel1 = new JLabel();
			jLabel1.setText("Season #");
			jLabel = new JLabel();
			jLabel.setText("Series Name");
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GridBagLayout());
			jPanel1.add(jLabel, gridBagConstraints);
			jPanel1.add(getJTextField_SeriesName(), gridBagConstraints1);
			jPanel1.add(jLabel1, gridBagConstraints2);
			jPanel1.add(getJTextField_SeasonNum(), gridBagConstraints3);
			jPanel1.add(jLabel2, gridBagConstraints4);
			jPanel1.add(getJTextField_AirNum(), gridBagConstraints5);
			jPanel1.add(jLabel3, gridBagConstraints6);
			jPanel1.add(getJTextField_SeasonProdNum(), gridBagConstraints7);
			jPanel1.add(jLabel4, gridBagConstraints8);
			jPanel1.add(getJTextField_ProdNum(), gridBagConstraints9);
			jPanel1.add(jLabel5, gridBagConstraints10);
			jPanel1.add(getJTextField_EpisodeTitle(), gridBagConstraints11);
			jPanel1.add(jLabel7, gridBagConstraints12);
			jPanel1.add(getJTextField_RenameMask(), gridBagConstraints13);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_SeriesName() {
		if (jTextField_SeriesName == null) {
			jTextField_SeriesName = new JTextField();
		}
		return jTextField_SeriesName;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_SeasonNum() {
		if (jTextField_SeasonNum == null) {
			jTextField_SeasonNum = new JTextField();
		}
		return jTextField_SeasonNum;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_AirNum() {
		if (jTextField_AirNum == null) {
			jTextField_AirNum = new JTextField();
		}
		return jTextField_AirNum;
	}

	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_SeasonProdNum() {
		if (jTextField_SeasonProdNum == null) {
			jTextField_SeasonProdNum = new JTextField();
		}
		return jTextField_SeasonProdNum;
	}

	/**
	 * This method initializes jTextField4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_ProdNum() {
		if (jTextField_ProdNum == null) {
			jTextField_ProdNum = new JTextField();
		}
		return jTextField_ProdNum;
	}

	/**
	 * This method initializes jTextField5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_EpisodeTitle() {
		if (jTextField_EpisodeTitle == null) {
			jTextField_EpisodeTitle = new JTextField();
		}
		return jTextField_EpisodeTitle;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			gridLayout1.setColumns(1);
			jPanel2 = new JPanel();
			jPanel2.setLayout(gridLayout1);
			jPanel2.add(getJPanel3(), null);
			jPanel2.add(getJPanel(), null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes jPanel3	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.fill = java.awt.GridBagConstraints.HORIZONTAL;
			gridBagConstraints15.gridx = 1;
			gridBagConstraints15.gridy = 0;
			gridBagConstraints15.ipadx = 0;
			gridBagConstraints15.ipady = 0;
			gridBagConstraints15.weightx = 1.0;
			gridBagConstraints15.insets = new java.awt.Insets(0,0,0,1);
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.gridx = 0;
			gridBagConstraints14.ipadx = 10;
			gridBagConstraints14.gridy = 0;
			jLabel6 = new JLabel();
			jLabel6.setText("Generated Name:");
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GridBagLayout());
			jPanel3.add(jLabel6, gridBagConstraints14);
			jPanel3.add(getJTextField_GeneratedName(), gridBagConstraints15);
		}
		return jPanel3;
	}

	/**
	 * This method initializes jTextField_GeneratedName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_GeneratedName() {
		if (jTextField_GeneratedName == null) {
			jTextField_GeneratedName = new JTextField();
			jTextField_GeneratedName.setEditable(false);
		}
		return jTextField_GeneratedName;
	}

	/**
	 * This method initializes jTextField_RenameMask	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	protected JTextField getJTextField_RenameMask() {
		if (jTextField_RenameMask == null) {
			jTextField_RenameMask = new JTextField();
		}
		return jTextField_RenameMask;
	}

	/**
	 * This method initializes jButtonSaveMask	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getJButtonSaveMask() {
		if (jButtonSaveMask == null) {
			jButtonSaveMask = new JButton();
			jButtonSaveMask.setText("Save Mask");
		}
		return jButtonSaveMask;
	}

	/**
	 * This method initializes jButtonLoadMask	
	 * 	
	 * @return javax.swing.JButton	
	 */
	protected JButton getJButtonLoadMask() {
		if (jButtonLoadMask == null) {
			jButtonLoadMask = new JButton();
			jButtonLoadMask.setText("Load Mask");
		}
		return jButtonLoadMask;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
