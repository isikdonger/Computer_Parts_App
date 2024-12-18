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
	JButton compareBtn;
	GridBagConstraints gbc_compareBtn;
	JButton backBtn;
	GridBagConstraints gbc_backBtn;
	private JLabel[][] labels = new JLabel[16][3];
	JLabel resLbl;
	private JComboBox comboBox_1 = null;
	private JComboBox comboBox_2 = null;
	private String type;
	private HardwarePart firstObject;
	private HardwarePart secondObject;
	MainFrame mfb; 
	/**
	 * Create the frame.
	 */
	public CompareFrame(MainFrame mf) {
		mfb = mf; 
		setTitle("Compare Hardware/Computers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 763, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{102, 99, 0, 242, 0};
		gbl_contentPane.rowHeights = new int[]{38, 46, 0, 35, 41, 0, 38, 0, 13, 0, 0};
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
		componentComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				type = componentComboBox.getSelectedItem().toString();
				fillFirstComboBox();
				clear();
			}
		});
		componentComboBox.setVisible(false);
		componentComboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		componentComboBox.setModel(new DefaultComboBoxModel(new String[] {"CPU", "GPU", "RAM", "SSD", "Motherboard", "PowerSupply", "Case"}));
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
					componentTypeLbl.setVisible(false); // Hide component-specific controls
			        componentComboBox.setVisible(false);
				}
				else {
					type = componentComboBox.getSelectedItem().toString();
					componentTypeLbl.setVisible(true);
					componentComboBox.setVisible(true);
				}
				fillFirstComboBox();
				clear();
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
				clear();
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
				clear();
			}
		});
		comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.gridx = 3;
		gbc_comboBox_2.gridy = 4;
		contentPane.add(comboBox_2, gbc_comboBox_2);
		
		resLbl = new JLabel("");
		GridBagConstraints gbc_resLbl = new GridBagConstraints();
		gbc_resLbl.gridwidth = 3;
		gbc_resLbl.insets = new Insets(0, 0, 5, 5);
		gbc_resLbl.gridx = 1;
		gbc_resLbl.gridy = 5;
		contentPane.add(resLbl, gbc_resLbl);
		
		for (int i=0;i<16;i++) {
			for (int j=0;j<3;j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setVisible(false);
				GridBagConstraints gbc_Lbl = new GridBagConstraints();
				gbc_Lbl.gridwidth = 3;
				gbc_Lbl.insets = new Insets(0, 0, 5, 5);
				gbc_Lbl.gridx = j;
				gbc_Lbl.gridy = i + 6;
				contentPane.add(labels[i][j], gbc_Lbl);
			}
		}
		
		compareBtn = new JButton("Compare");
		compareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					HardwarePart winner = compare();
					if (winner instanceof Computer) {
						resLbl.setText(((Computer)winner).getModelName());
					}
					else {
						resLbl.setText(((HardwareComponent)winner).getModelName());
					}
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
		gbc_compareBtn = new GridBagConstraints();
		gbc_compareBtn.fill = GridBagConstraints.VERTICAL;
		gbc_compareBtn.insets = new Insets(0, 0, 5, 5);
		gbc_compareBtn.gridx = 1;
		gbc_compareBtn.gridy = 6;
		contentPane.add(compareBtn, gbc_compareBtn);
		
		backBtn = new JButton("Go Back");
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resLbl.setText("");
				dispose();
				mfb.setVisible(true);
			}
		});
		gbc_backBtn = new GridBagConstraints();
		gbc_backBtn.insets = new Insets(0, 0, 5, 0);
		gbc_backBtn.fill = GridBagConstraints.VERTICAL;
		gbc_backBtn.gridx = 3;
		gbc_backBtn.gridy = 6;
		contentPane.add(backBtn, gbc_backBtn);
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	
	public JButton getCompareBtn() {
		return compareBtn;
	}

	public GridBagConstraints getGbc_compareBtn() {
		return gbc_compareBtn;
	}

	public JButton getBackBtn() {
		return backBtn;
	}

	public GridBagConstraints getGbc_backBtn() {
		return gbc_backBtn;
	}

	public JLabel[][] getLabels() {
		return labels;
	}

	public void fillFirstComboBox() {
		String[] arr;
		if (type.equalsIgnoreCase("Computer")) {
			arr = (String[])HardwareSystem.getComputersArray(null);
		}
		else {
			try {
		        // Convert type to method name, e.g., "CPU" -> "getCPUs"
		        String methodName = "get" + type + "s";
		        java.lang.reflect.Method method = HardwareSystem.class.getMethod(methodName);
		        
		        // Invoke the method
		        arr = (String[]) method.invoke(null);
		        
		    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
		        e.printStackTrace();
		        arr = new String[0]; // Empty array if an error occurs
		    }

		    
		}
		// Populate the combo box
	    comboBox_1.setModel(new DefaultComboBoxModel(arr));
	}
	
	private void fillSecondComboBox() {
	    String[] arr;

	    if (type.equalsIgnoreCase("Computer")) {
	        // Fetch related computers excluding the first selected one
	    	arr = (String[])HardwareSystem.getComputersArray((Computer) firstObject);
	    } else {
	        try {
	            // Convert the type to the corresponding method name
	            String methodName = "get" + type + "s"; // E.g., "CPU" -> "getCPUs"
	            java.lang.reflect.Method method = HardwareSystem.class.getMethod(methodName);
	            
	            // Invoke the method with the selected first object
	            arr = (String[]) method.invoke(null);
	        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	            e.printStackTrace();
	            arr = new String[0]; // Empty array if an error occurs
	        }
	    }
	    // Remove the selected item from the first combo box from the second combo box's options
        String selectedItem = comboBox_1.getSelectedItem().toString();
        ArrayList<String> filteredList = new ArrayList<>(Arrays.asList(arr));
        filteredList.remove(selectedItem); // Remove the selected item

        // Convert the list back to an array
        String[] filteredArr = filteredList.toArray(new String[0]);

        // Set the new model for the second combo box
        comboBox_2.setModel(new DefaultComboBoxModel(filteredArr));
	}
	
	private HardwarePart compare() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return HardwareSystem.compare(this, firstObject, secondObject);
	}
	
	private void clear() {
		resLbl.setText("");
		resLbl.setVisible(false);
		for (int i=0;i<16;i++) {
			for (int j=0;j<3;j++) {
				labels[i][j].setText("");
				labels[i][j].setVisible(false);
			}
		}
		gbc_compareBtn.gridy = 6;
		contentPane.add(compareBtn, gbc_compareBtn);
		gbc_backBtn.gridy = 6;
		contentPane.add(backBtn, gbc_backBtn);
	}
	
}
