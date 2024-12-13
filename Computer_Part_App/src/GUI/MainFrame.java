package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	CompareFrame cf = new CompareFrame(this);
	BuildFrame bf = new BuildFrame(this);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("HardwareCompass");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb1 = new JLabel("Welcome");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb1.setBounds(176, 68, 89, 30);
		contentPane.add(lb1);
		
		JButton btn1 = new JButton("Build");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bf.setVisible(true);
				setVisible(false);
			}
		});
		btn1.setBounds(102, 126, 89, 23);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("Compare");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.setVisible(true);
				setVisible(false);
			}
		});
		btn2.setBounds(230, 126, 89, 23);
		contentPane.add(btn2);
	}
}
