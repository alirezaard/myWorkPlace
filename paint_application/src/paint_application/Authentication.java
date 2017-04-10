package paint_application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.text.PasswordView;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class Authentication {

	private JFrame frame;
	private JTextField userTxt;
	private JTextField passTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentication window = new Authentication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 366, 243);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnLogin = new JButton("LOGIN...");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int myUserID = CheckPassword.isEquals(userTxt.getText(),
							passTxt.getText());
					if (myUserID > 0) {
						PaintApplication.main(myUserID);
						frame.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null,
								"you Can't Access this part.");
					}
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(128, 153, 89, 23);
		frame.getContentPane().add(btnLogin);

		userTxt = new JTextField();
		userTxt.setBounds(125, 31, 202, 20);
		frame.getContentPane().add(userTxt);
		userTxt.setColumns(10);

		passTxt = new JTextField();
		passTxt.setColumns(10);
		passTxt.setBounds(125, 90, 202, 20);
		frame.getContentPane().add(passTxt);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 34, 71, 17);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 93, 71, 17);
		frame.getContentPane().add(lblPassword);
	}
}
