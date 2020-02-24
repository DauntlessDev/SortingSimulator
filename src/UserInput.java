import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

public class UserInput extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	JButton btnEnter = new JButton("ENTER");


	/**
	 * Create the frame.
	 */
	public UserInput() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 76);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblUserInput = new JLabel("User Input:");
		lblUserInput.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblUserInput.setBounds(37, 13, 109, 32);
		contentPane.add(lblUserInput);
		
		textField = new JTextField();
		textField.setBounds(158, 17, 57, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnEnter.setBounds(229, 16, 92, 32);
		contentPane.add(btnEnter);

		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				Main.list[Main.counter] = Integer.parseInt(textField.getText());
				if(Integer.parseInt(textField.getText()) < 1) {

					JOptionPane.showMessageDialog(null, "Error! Please input a valid positive number"
							, "Invalid Input",
									JOptionPane.WARNING_MESSAGE);
				}else {

					Main.counter++;


					if (Main.counter < Main.len) {

						JOptionPane.showMessageDialog(null, "Please input " +  String.valueOf(Main.len - Main.counter) + " more to be done. "
						, "Input",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				

				
				
				
				if (Main.counter == Main.len) {

					Main.shuffle.setEnabled(true);
					Main.speed.setEnabled(true);
					Main.size.setEnabled(true);
					Main.sort.setEnabled(true);
					Main.btnUserInput.setEnabled(true);
					Main.alg.setEnabled(true);
					Main.reset();
					JOptionPane.showMessageDialog(null, "All done! Please proceed to sorting!"
							, "DONE",
									JOptionPane.INFORMATION_MESSAGE);
							

							
							Main.sorting = false;
							Main.shuffled = true;

							dispose();
				}
				} catch(Exception e1) {
				
					JOptionPane.showMessageDialog(null, "Error! Please input a valid number"
							, "Invalid Input",
									JOptionPane.WARNING_MESSAGE);
				}
				

				
			}
		});
		
		
		
	}
	

	
	public void createListInput() {
		Main.list = new int[Main.len];
		Main.counter = 0;
	}

}
