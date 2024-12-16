package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import System_and_Main.HardwareSystem;
import HardwareComponent.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JCheckBox;

public class BuildFrame extends JFrame {

	private JPanel contentPane;
	private JTextField outputTF;
	private JComboBox cpuCB;
	private JComboBox gpuCB;
	private JComboBox ramCB;
	private JComboBox ssdCB;
	private JComboBox MotherboardCB;
	private JComboBox PowerSupplyCB;
	private JComboBox CaseCB;
	private JSpinner ramSpinner;
	private JSpinner ssdSpinner;
	private JCheckBox monitorCheckBox;
	private CPU cpu;
	private GPU gpu;
	private RAM ram;
	private SSD ssd;
	private Motherboard mb;
	private PowerSupply ps;
	private Case Case;

	/**
	 * Create the frame.
	 */
	public BuildFrame(MainFrame mf) {
		setTitle("Build a Computer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{53, 98, 98, 57, 49, 0};
		gbl_contentPane.rowHeights = new int[]{46, 17, 17, 20, 20, 17, 17, 17, 51, 23, 47, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel gpuLB = new JLabel("GPU:");
		GridBagConstraints gbc_gpuLB = new GridBagConstraints();
		gbc_gpuLB.anchor = GridBagConstraints.WEST;
		gbc_gpuLB.insets = new Insets(0, 0, 5, 5);
		gbc_gpuLB.gridx = 1;
		gbc_gpuLB.gridy = 1;
		contentPane.add(gpuLB, gbc_gpuLB);
		
		gpuCB = new JComboBox();
		gpuCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gpu = (GPU) HardwareSystem.findHardwarePart(Integer.parseInt(gpuCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				MotherboardCB.setModel(new DefaultComboBoxModel(HardwareSystem.getMotherboards()));
			}
		});
		gpuCB.setModel(new DefaultComboBoxModel(HardwareSystem.getGPUs()));
		GridBagConstraints gbc_gpuCB = new GridBagConstraints();
		gbc_gpuCB.fill = GridBagConstraints.BOTH;
		gbc_gpuCB.insets = new Insets(0, 0, 5, 5);
		gbc_gpuCB.gridx = 2;
		gbc_gpuCB.gridy = 1;
		contentPane.add(gpuCB, gbc_gpuCB);
		
		JLabel MotherboardLB = new JLabel("Motherboard:");
		GridBagConstraints gbc_MotherboardLB = new GridBagConstraints();
		gbc_MotherboardLB.anchor = GridBagConstraints.WEST;
		gbc_MotherboardLB.insets = new Insets(0, 0, 5, 5);
		gbc_MotherboardLB.gridx = 1;
		gbc_MotherboardLB.gridy = 2;
		contentPane.add(MotherboardLB, gbc_MotherboardLB);
		
		MotherboardCB = new JComboBox();
		MotherboardCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mb = (Motherboard) HardwareSystem.findHardwarePart(Integer.parseInt(MotherboardCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				cpuCB.setModel(new DefaultComboBoxModel(HardwareSystem.getCPUs(mb)));
				ramSpinner.setModel(new SpinnerNumberModel(1, 1, mb.getMemorySlots(), 1));
				ssdSpinner.setModel(new SpinnerNumberModel(1, 1, mb.getStorageSlot().length, 1));
			}
		});
		GridBagConstraints gbc_motherboardCB = new GridBagConstraints();
		gbc_motherboardCB.fill = GridBagConstraints.BOTH;
		gbc_motherboardCB.insets = new Insets(0, 0, 5, 5);
		gbc_motherboardCB.gridx = 2;
		gbc_motherboardCB.gridy = 2;
		contentPane.add(MotherboardCB, gbc_motherboardCB);
		
		JLabel cpuLB = new JLabel("CPU:");
		GridBagConstraints gbc_cpuLB = new GridBagConstraints();
		gbc_cpuLB.anchor = GridBagConstraints.WEST;
		gbc_cpuLB.insets = new Insets(0, 0, 5, 5);
		gbc_cpuLB.gridx = 1;
		gbc_cpuLB.gridy = 3;
		contentPane.add(cpuLB, gbc_cpuLB);
		
		cpuCB = new JComboBox();
		cpuCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpu = (CPU) HardwareSystem.findHardwarePart(Integer.parseInt(cpuCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				int amount=1;
				try {
					ramSpinner.commitEdit();
					amount=(int)ramSpinner.getValue();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ramCB.setModel(new DefaultComboBoxModel(HardwareSystem.getRAMs(mb, cpu, amount)));
			}
		});
		GridBagConstraints gbc_cpuGB = new GridBagConstraints();
		gbc_cpuGB.fill = GridBagConstraints.BOTH;
		gbc_cpuGB.insets = new Insets(0, 0, 5, 5);
		gbc_cpuGB.gridx = 2;
		gbc_cpuGB.gridy = 3;
		contentPane.add(cpuCB, gbc_cpuGB);
		
		JLabel ramLB = new JLabel("RAM:");
		GridBagConstraints gbc_ramLB = new GridBagConstraints();
		gbc_ramLB.anchor = GridBagConstraints.WEST;
		gbc_ramLB.insets = new Insets(0, 0, 5, 5);
		gbc_ramLB.gridx = 1;
		gbc_ramLB.gridy = 4;
		contentPane.add(ramLB, gbc_ramLB);
		
		ramCB = new JComboBox();
		ramCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ram = (RAM)HardwareSystem.findHardwarePart(Integer.parseInt(ramCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				ssdCB.setModel(new DefaultComboBoxModel(HardwareSystem.getSSDs(mb)));
			}
		});
		GridBagConstraints gbc_ramCB = new GridBagConstraints();
		gbc_ramCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ramCB.insets = new Insets(0, 0, 5, 5);
		gbc_ramCB.gridx = 2;
		gbc_ramCB.gridy = 4;
		contentPane.add(ramCB, gbc_ramCB);
		
		JLabel ramAmountLB = new JLabel("Amount:");
		GridBagConstraints gbc_ramAmountLB = new GridBagConstraints();
		gbc_ramAmountLB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ramAmountLB.insets = new Insets(0, 0, 5, 5);
		gbc_ramAmountLB.gridx = 3;
		gbc_ramAmountLB.gridy = 4;
		contentPane.add(ramAmountLB, gbc_ramAmountLB);
		
		ramSpinner = new JSpinner();
		ramSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		ramSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int amount = (int) ramSpinner.getValue(); // Get the current value
                ramCB.setModel(new DefaultComboBoxModel(HardwareSystem.getRAMs(mb, cpu, amount)));
            }
        });
		GridBagConstraints gbc_ramSpinner = new GridBagConstraints();
		gbc_ramSpinner.anchor = GridBagConstraints.NORTH;
		gbc_ramSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_ramSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_ramSpinner.gridx = 4;
		gbc_ramSpinner.gridy = 4;
		contentPane.add(ramSpinner, gbc_ramSpinner);
		
		JLabel ssdLB = new JLabel("SSD:");
		GridBagConstraints gbc_ssdLB = new GridBagConstraints();
		gbc_ssdLB.anchor = GridBagConstraints.WEST;
		gbc_ssdLB.insets = new Insets(0, 0, 5, 5);
		gbc_ssdLB.gridx = 1;
		gbc_ssdLB.gridy = 5;
		contentPane.add(ssdLB, gbc_ssdLB);
		
		ssdCB = new JComboBox();
		ssdCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ssd = (SSD)HardwareSystem.findHardwarePart(Integer.parseInt(ssdCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				CaseCB.setModel(new DefaultComboBoxModel(HardwareSystem.getCases()));
			}
		});
		GridBagConstraints gbc_ssdCB = new GridBagConstraints();
		gbc_ssdCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssdCB.insets = new Insets(0, 0, 5, 5);
		gbc_ssdCB.gridx = 2;
		gbc_ssdCB.gridy = 5;
		contentPane.add(ssdCB, gbc_ssdCB);
		
		JLabel ssdAmountLB = new JLabel("Amount:");
		GridBagConstraints gbc_ssdAmountLB = new GridBagConstraints();
		gbc_ssdAmountLB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssdAmountLB.insets = new Insets(0, 0, 5, 5);
		gbc_ssdAmountLB.gridx = 3;
		gbc_ssdAmountLB.gridy = 5;
		contentPane.add(ssdAmountLB, gbc_ssdAmountLB);
		
		ssdSpinner = new JSpinner();
		ssdSpinner.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		GridBagConstraints gbc_ssdSpinner = new GridBagConstraints();
		gbc_ssdSpinner.anchor = GridBagConstraints.NORTH;
		gbc_ssdSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssdSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_ssdSpinner.gridx = 4;
		gbc_ssdSpinner.gridy = 5;
		contentPane.add(ssdSpinner, gbc_ssdSpinner);
		
		JLabel CaseLB = new JLabel("Case:");
		GridBagConstraints gbc_CaseLB = new GridBagConstraints();
		gbc_CaseLB.anchor = GridBagConstraints.WEST;
		gbc_CaseLB.insets = new Insets(0, 0, 5, 5);
		gbc_CaseLB.gridx = 1;
		gbc_CaseLB.gridy = 6;
		contentPane.add(CaseLB, gbc_CaseLB);
		
		CaseCB = new JComboBox();
		CaseCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Case = (Case)HardwareSystem.findHardwarePart(Integer.parseInt(CaseCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
				PowerSupplyCB.setModel(new DefaultComboBoxModel(HardwareSystem.getPowerSupplys(gpu, Case)));
			}
		});
		GridBagConstraints gbc_caseCB = new GridBagConstraints();
		gbc_caseCB.fill = GridBagConstraints.BOTH;
		gbc_caseCB.insets = new Insets(0, 0, 5, 5);
		gbc_caseCB.gridx = 2;
		gbc_caseCB.gridy = 6;
		contentPane.add(CaseCB, gbc_caseCB);
		
		JLabel PowerSupplyLB = new JLabel("Power Supply:");
		GridBagConstraints gbc_PowerSupplyLB = new GridBagConstraints();
		gbc_PowerSupplyLB.fill = GridBagConstraints.HORIZONTAL;
		gbc_PowerSupplyLB.insets = new Insets(0, 0, 5, 5);
		gbc_PowerSupplyLB.gridx = 1;
		gbc_PowerSupplyLB.gridy = 7;
		contentPane.add(PowerSupplyLB, gbc_PowerSupplyLB);
		
		PowerSupplyCB = new JComboBox();
		PowerSupplyCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ps = (PowerSupply)HardwareSystem.findHardwarePart(Integer.parseInt(PowerSupplyCB.getSelectedItem().toString().replaceAll("\\D*(\\d+).*", "$1")));
			}
		});
		GridBagConstraints gbc_powerSupplyCB = new GridBagConstraints();
		gbc_powerSupplyCB.fill = GridBagConstraints.BOTH;
		gbc_powerSupplyCB.insets = new Insets(0, 0, 5, 5);
		gbc_powerSupplyCB.gridx = 2;
		gbc_powerSupplyCB.gridy = 7;
		contentPane.add(PowerSupplyCB, gbc_powerSupplyCB);
		
		monitorCheckBox = new JCheckBox("Do you want a monitor: ");
		GridBagConstraints gbc_monitorCheckBox = new GridBagConstraints();
		gbc_monitorCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_monitorCheckBox.gridx = 2;
		gbc_monitorCheckBox.gridy = 8;
		contentPane.add(monitorCheckBox, gbc_monitorCheckBox);
		
		JLabel TotalLB = new JLabel("Total:");
		GridBagConstraints gbc_TotalLB = new GridBagConstraints();
		gbc_TotalLB.anchor = GridBagConstraints.WEST;
		gbc_TotalLB.insets = new Insets(0, 0, 5, 5);
		gbc_TotalLB.gridx = 1;
		gbc_TotalLB.gridy = 9;
		contentPane.add(TotalLB, gbc_TotalLB);
		
		JButton backBtn = new JButton("Go Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mf.setVisible(true);
				dispose();
			}
		});
		
		JButton checkoutBTN = new JButton("Checkout");
		checkoutBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputTF.setText(Double.toString(buildComputer()));
			}
		});
		
		outputTF = new JTextField();
		outputTF.setEditable(false);
		GridBagConstraints gbc_outputTF = new GridBagConstraints();
		gbc_outputTF.anchor = GridBagConstraints.WEST;
		gbc_outputTF.insets = new Insets(0, 0, 5, 5);
		gbc_outputTF.gridx = 2;
		gbc_outputTF.gridy = 9;
		contentPane.add(outputTF, gbc_outputTF);
		outputTF.setColumns(10);
		GridBagConstraints gbc_checkoutBTN = new GridBagConstraints();
		gbc_checkoutBTN.anchor = GridBagConstraints.EAST;
		gbc_checkoutBTN.fill = GridBagConstraints.VERTICAL;
		gbc_checkoutBTN.insets = new Insets(0, 0, 5, 0);
		gbc_checkoutBTN.gridwidth = 2;
		gbc_checkoutBTN.gridx = 3;
		gbc_checkoutBTN.gridy = 9;
		contentPane.add(checkoutBTN, gbc_checkoutBTN);
		GridBagConstraints gbc_backBtn = new GridBagConstraints();
		gbc_backBtn.fill = GridBagConstraints.BOTH;
		gbc_backBtn.insets = new Insets(0, 0, 0, 5);
		gbc_backBtn.gridx = 2;
		gbc_backBtn.gridy = 11;
		contentPane.add(backBtn, gbc_backBtn);
	}
	
	public double buildComputer() {
		try {
			ramSpinner.commitEdit();
			ssdSpinner.commitEdit();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0.0;
		}
		return HardwareSystem.buildComputer(cpu, gpu, ram, (int)ramSpinner.getValue(), ssd, (int)ssdSpinner.getValue(), mb, ps, Case, monitorCheckBox.isSelected());
	}

	public JComboBox getCpuCB() {
		return cpuCB;
	}

	public JComboBox getGpuCB() {
		return gpuCB;
	}

	public JComboBox getRamCB() {
		return ramCB;
	}

	public JComboBox getSsdCB() {
		return ssdCB;
	}

	public JComboBox getMotherboardCB() {
		return MotherboardCB;
	}

	public JComboBox getPowerSupplyCB() {
		return PowerSupplyCB;
	}

	public JComboBox getCaseCB() {
		return CaseCB;
	}

	public JSpinner getRamSpinner() {
		return ramSpinner;
	}

	public JSpinner getSsdSpinner() {
		return ssdSpinner;
	}
}
