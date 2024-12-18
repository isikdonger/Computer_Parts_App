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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Visibility;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JLabel popLb;
	
	CompareFrame cf = new CompareFrame(this);
	BuildFrame bf = new BuildFrame(this);
	SearchFrame sf = new SearchFrame(this);
	
	public void clear() {
		textArea.setText("");
	}
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("HardwareCompass");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Welcome");
		title.setBounds(181, 11, 89, 30);
		title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(title);
		
		JButton buildBtn = new JButton("Build");
		buildBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popLb.setVisible(true);
				}
					
			}
		});
		buildBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buildBtn.setBounds(59, 295, 139, 37);
		buildBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bf.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.add(buildBtn);
		
		JButton compareBtn = new JButton("Compare");
		compareBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		compareBtn.setBounds(226, 295, 152, 37);
		compareBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				cf.setVisible(true);
			}
		});
		contentPane.add(compareBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 154, 319, 130);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					clear();
				}
			}
		});
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
		displayComputerBtn.setBounds(59, 51, 139, 37);
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
		displayComponentBtn.setBounds(226, 51, 152, 37);
		contentPane.add(displayComponentBtn);
		
		JButton searchBtn = new JButton("Search or Delete");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sf.setVisible(true);
				setVisible(false);
			}
		});
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchBtn.setBounds(59, 99, 319, 37);
		contentPane.add(searchBtn);
		
		popLb = new JLabel("only for PCs");
		popLb.setBounds(97, 329, 89, 25);
		popLb.setVisible(false);
		contentPane.add(popLb);
	}
}
