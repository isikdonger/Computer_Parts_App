package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System_and_Main.HardwareSystem;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JLabel modelLb;
	private JTextField modelTf;
	private JTextArea textArea;
	private JButton btnNewButton;
	private JButton deleteBtn;
	private Object o;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SearchFrame(MainFrame mf) {
		
		
		setTitle("Search Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		modelLb = new JLabel("Enter Model Number:");
		modelLb.setBounds(60, 29, 143, 14);
		contentPane.add(modelLb);
		
		modelTf = new JTextField();
		modelTf.setBounds(213, 26, 154, 20);
		contentPane.add(modelTf);
		modelTf.setColumns(10);
		
		JButton searchBtn = new JButton("search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!modelTf.getText().isEmpty()) {
					o = HardwareSystem.findHardwarePart(Integer.parseInt(modelTf.getText()));
					if (o == null)
						textArea.setText("Could Not Found Any Component/Computer!");
					else textArea.setText(o.toString());
				}
			}
		});
		searchBtn.setBounds(60, 65, 78, 23);
		contentPane.add(searchBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 99, 311, 119);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnNewButton = new JButton("Go Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mf.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(157, 229, 89, 23);
		contentPane.add(btnNewButton);
		
		deleteBtn = new JButton("delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!modelTf.getText().isEmpty()) {
					o = HardwareSystem.findHardwarePart(Integer.parseInt(modelTf.getText()));
					if (HardwareSystem.removeHardwarePart(o)) {
						textArea.setText("Successfuly Deleted!");
					}
					else textArea.setText("Could Not Found Any Component/Computer to delete!");
				}
			}
		});
		deleteBtn.setBounds(289, 65, 78, 23);
		contentPane.add(deleteBtn);
	}
}
