package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import System_and_Main.HardwareSystem;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
		setBounds(100, 100, 450, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Welcome");
		title.setBounds(177, 10, 89, 30);
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(title);
		
		JButton buildBtn = new JButton("Build");
		buildBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buildBtn.setBounds(59, 279, 139, 37);
		buildBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bf.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(buildBtn);
		
		JButton compareBtn = new JButton("Compare");
		compareBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		compareBtn.setBounds(226, 279, 152, 37);
		compareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cf.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(compareBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 120, 319, 130);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton displayComputerBtn = new JButton("Display Computers");
		displayComputerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = HardwareSystem.displayComputers();
				if (output == "") {
					textArea.setText("There are no computers.");
				}
				else {
					textArea.setText(output);	
				}
			}
		});
		displayComputerBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displayComputerBtn.setBounds(59, 61, 139, 37);
		contentPane.add(displayComputerBtn);
		
		JButton displayComponentBtn = new JButton("Display Components");
		displayComponentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = HardwareSystem.displayComponents();
				if (output == "") {
					textArea.setText("There are no components.");
				}
				else {
					textArea.setText(output);	
				}
			}
		});
		displayComponentBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		displayComponentBtn.setBounds(226, 61, 152, 37);
		contentPane.add(displayComponentBtn);
	}
}
