package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompareFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CompareFrame(MainFrame mf) {
		setTitle("Compare Hardware/Computers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		backBtn.setBounds(175, 229, 89, 23);
		contentPane.add(backBtn);
	}

}
