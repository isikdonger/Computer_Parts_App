package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class DeleteFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.

	/**
	 * Create the frame.
	 */
	public DeleteFrame(MainFrame mf) {
		setTitle("Delete");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Go Back");
		btnNewButton.setBounds(167, 213, 89, 23);
		contentPane.add(btnNewButton);
	}

}
