package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class BuildFrame extends JFrame {

	private JPanel contentPane;
	private JTextField outputTF;

	/**
	 * Create the frame.
	 */
	public BuildFrame(MainFrame mf) {
		setTitle("Build a Computer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton backBtn = new JButton("Go Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mf.setVisible(true);
				dispose();
			}
		});
		backBtn.setBounds(162, 364, 89, 23);
		contentPane.add(backBtn);
		
		JLabel cpuLB = new JLabel("CPU:");
		cpuLB.setBounds(53, 46, 49, 14);
		contentPane.add(cpuLB);
		
		JLabel gpuLB = new JLabel("GPU:");
		gpuLB.setBounds(53, 76, 49, 14);
		contentPane.add(gpuLB);
		
		JLabel ramLB = new JLabel("RAM:");
		ramLB.setBounds(55, 106, 49, 14);
		contentPane.add(ramLB);
		
		JLabel ssdLB = new JLabel("SSD:");
		ssdLB.setBounds(55, 136, 49, 14);
		contentPane.add(ssdLB);
		
		JLabel MotherboardLB = new JLabel("Motherboard:");
		MotherboardLB.setBounds(55, 166, 79, 14);
		contentPane.add(MotherboardLB);
		
		JLabel PowerSupplyLB = new JLabel("Power Supply:");
		PowerSupplyLB.setBounds(55, 196, 96, 14);
		contentPane.add(PowerSupplyLB);
		
		JLabel CaseLB = new JLabel("Case:");
		CaseLB.setBounds(55, 226, 49, 14);
		contentPane.add(CaseLB);
		
		JComboBox cpuGB = new JComboBox();
		cpuGB.setBounds(153, 46, 70, 17);
		contentPane.add(cpuGB);
		
		JComboBox gpuCB = new JComboBox();
		gpuCB.setBounds(153, 76, 70, 17);
		contentPane.add(gpuCB);
		
		JComboBox ramCB = new JComboBox();
		ramCB.setBounds(153, 106, 70, 17);
		contentPane.add(ramCB);
		
		JComboBox ssdCB = new JComboBox();
		ssdCB.setBounds(153, 136, 70, 17);
		contentPane.add(ssdCB);
		
		JComboBox MotherboardCB = new JComboBox();
		MotherboardCB.setBounds(153, 166, 70, 17);
		contentPane.add(MotherboardCB);
		
		JComboBox PowerSupplyCB = new JComboBox();
		PowerSupplyCB.setBounds(153, 196, 70, 17);
		contentPane.add(PowerSupplyCB);
		
		JComboBox CaseCB = new JComboBox();
		CaseCB.setBounds(153, 226, 70, 17);
		contentPane.add(CaseCB);
		
		JLabel ramAmountLB = new JLabel("Amount:");
		ramAmountLB.setBounds(271, 106, 57, 14);
		contentPane.add(ramAmountLB);
		
		JLabel ssdAmountLB = new JLabel("Amount:");
		ssdAmountLB.setBounds(271, 136, 57, 14);
		contentPane.add(ssdAmountLB);
		
		JComboBox ramAmountCB = new JComboBox();
		ramAmountCB.setBounds(338, 105, 49, 17);
		contentPane.add(ramAmountCB);
		
		JComboBox ssdAmountCB = new JComboBox();
		ssdAmountCB.setBounds(338, 135, 49, 17);
		contentPane.add(ssdAmountCB);
		
		JLabel TotalLB = new JLabel("Total:");
		TotalLB.setBounds(55, 296, 49, 14);
		contentPane.add(TotalLB);
		
		outputTF = new JTextField();
		outputTF.setEditable(false);
		outputTF.setBounds(153, 295, 96, 20);
		contentPane.add(outputTF);
		outputTF.setColumns(10);
		
		JButton checkoutBTN = new JButton("Checkout");
		checkoutBTN.setBounds(298, 294, 89, 23);
		contentPane.add(checkoutBTN);
	}
}
