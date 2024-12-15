package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Computer.Computer;
import HardwareComponent.HardwareComponent;
import Interface.HardwarePart;
import System_and_Main.HardwareSystem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class CompareFrame extends JFrame {

	private JPanel contentPane;
	private String type;
	private JComboBox comboBox_1 = null;
	private JComboBox comboBox_2 = null;
	private HardwarePart firstObject;
	private HardwarePart secondObject;

	/**
	 * Create the frame.
	 */
	public CompareFrame(MainFrame mf) {
		setTitle("Compare Hardware/Computers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{102, 99, 0, 242, 0};
		gbl_contentPane.rowHeights = new int[]{38, 46, 0, 35, 41, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel typeLbl = new JLabel("Select a type:");
		typeLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_typeLbl = new GridBagConstraints();
		gbc_typeLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_typeLbl.gridx = 1;
		gbc_typeLbl.gridy = 0;
		contentPane.add(typeLbl, gbc_typeLbl);
		
		JLabel componentTypeLbl = new JLabel("Select a component type:     ");
		componentTypeLbl.setVisible(false);
		componentTypeLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_componentTypeLbl = new GridBagConstraints();
		gbc_componentTypeLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_componentTypeLbl.insets = new Insets(0, 0, 5, 5);
		gbc_componentTypeLbl.gridx = 1;
		gbc_componentTypeLbl.gridy = 1;
		contentPane.add(componentTypeLbl, gbc_componentTypeLbl);
		
		JComboBox componentComboBox = new JComboBox();
		componentComboBox.setVisible(false);
		componentComboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		componentComboBox.setModel(new DefaultComboBoxModel(new String[] {"Cpu", "Gpu", "Ram", "Ssd", "Motherboard", "PowerSupply", "Case"}));
		GridBagConstraints gbc_componentComboBox = new GridBagConstraints();
		gbc_componentComboBox.anchor = GridBagConstraints.EAST;
		gbc_componentComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_componentComboBox.gridx = 3;
		gbc_componentComboBox.gridy = 1;
		contentPane.add(componentComboBox, gbc_componentComboBox);
		
		JComboBox typeBox = new JComboBox();
		typeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (typeBox.getSelectedItem().toString().equalsIgnoreCase("Computer")) {
					type = "Computer";
				}
				else {
					type = "HardwareComponent";
					componentTypeLbl.setVisible(true);
					componentComboBox.setVisible(true);
				}
				fillFirstComboBox();
			}
		});
		typeBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		typeBox.setModel(new DefaultComboBoxModel(new String[] {"HardwareComponent", "Computer"}));
		GridBagConstraints gbc_typeBox = new GridBagConstraints();
		gbc_typeBox.anchor = GridBagConstraints.EAST;
		gbc_typeBox.fill = GridBagConstraints.VERTICAL;
		gbc_typeBox.insets = new Insets(0, 0, 5, 0);
		gbc_typeBox.gridx = 3;
		gbc_typeBox.gridy = 0;
		contentPane.add(typeBox, gbc_typeBox);
		
		JLabel obj1Lbl = new JLabel("First Object");
		obj1Lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_obj1Lbl = new GridBagConstraints();
		gbc_obj1Lbl.insets = new Insets(0, 0, 5, 5);
		gbc_obj1Lbl.gridx = 1;
		gbc_obj1Lbl.gridy = 3;
		contentPane.add(obj1Lbl, gbc_obj1Lbl);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstObject = HardwareSystem.findHardwarePart(Integer.parseInt(comboBox_1.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				fillSecondComboBox();
			}
		});
		
		JLabel obj2Lbl = new JLabel("Second Object ");
		obj2Lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_obj2Lbl = new GridBagConstraints();
		gbc_obj2Lbl.insets = new Insets(0, 0, 5, 0);
		gbc_obj2Lbl.gridx = 3;
		gbc_obj2Lbl.gridy = 3;
		contentPane.add(obj2Lbl, gbc_obj2Lbl);
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 4;
		contentPane.add(comboBox_1, gbc_comboBox_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondObject = HardwareSystem.findHardwarePart(Integer.parseInt(comboBox_2.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
			}
		});
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 4;
		contentPane.add(comboBox_2, gbc_comboBox_2);
		
		JButton compareBtn = new JButton("Compare");
		compareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					compare();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		compareBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_compareBtn = new GridBagConstraints();
		gbc_compareBtn.fill = GridBagConstraints.VERTICAL;
		gbc_compareBtn.insets = new Insets(0, 0, 5, 5);
		gbc_compareBtn.gridx = 1;
		gbc_compareBtn.gridy = 6;
		contentPane.add(compareBtn, gbc_compareBtn);
		
		JButton backBtn = new JButton("Go Back");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mf.setVisible(true);
				dispose();
			}
		});
		
		GridBagConstraints gbc_backBtn = new GridBagConstraints();
		gbc_backBtn.insets = new Insets(0, 0, 5, 0);
		gbc_backBtn.fill = GridBagConstraints.VERTICAL;
		gbc_backBtn.gridx = 3;
		gbc_backBtn.gridy = 6;
		contentPane.add(backBtn, gbc_backBtn);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}

	private void fillFirstComboBox() {
		String[] arr;
		if (type.equalsIgnoreCase("Computer")) {
			arr = (String[])HardwareSystem.getComputersArray(null);
		}
		else {
			arr = (String[])HardwareSystem.getHardwareComponentsArray(null);
		}
		comboBox_1.setModel(new DefaultComboBoxModel(arr));
	}
	
	private void fillSecondComboBox() {
		String[] arr;
		if (type.equalsIgnoreCase("Computer")) {
			arr = HardwareSystem.getComputersArray((Computer)firstObject);
		}
		else {
			arr = HardwareSystem.getHardwareComponentsArray((HardwareComponent)firstObject);
		}
		comboBox_2.setModel(new DefaultComboBoxModel(arr));
	}
	
	private void compare() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		// HardwareSystem.compare(this, firstObject, secondObject);
	}
}
