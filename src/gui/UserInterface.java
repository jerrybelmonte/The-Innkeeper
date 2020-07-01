package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class UserInterface extends JFrame {
    private static final long serialVersionUID = 2592710042211323255L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    static final int maxAttempts = 3;
    static int numberOfAttempts = 0;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    UserInterface frame = new UserInterface();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    public UserInterface() {
	this.setTitle("Login");
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(this.contentPane);
	this.contentPane.setLayout(null);

	JLabel textLabel = new JLabel("Username:");
	textLabel.setBounds(60, 78, 83, 20);
	textLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(textLabel);

	this.textField = new JTextField();
	this.textField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.textField.setToolTipText("Use \"John\" for the username");
	this.textField.setBounds(145, 70, 183, 36);
	this.textField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(this.textField);
	this.textField.setColumns(10);

	JLabel valueLabel = new JLabel("Password:");
	valueLabel.setBounds(65, 123, 78, 20);
	valueLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(valueLabel);

	this.passwordField = new JPasswordField();
	this.passwordField.setHorizontalAlignment(SwingConstants.TRAILING);
	this.passwordField.setToolTipText("Hint: p@ssw0rd");
	this.passwordField.setBounds(145, 115, 183, 36);
	this.passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
	this.contentPane.add(this.passwordField);

	JButton loginButton = new JButton("Login");
	loginButton.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {

		boolean correctUsername = false;
		boolean correctPassword = false;

		String username = UserInterface.this.textField.getText();
		String password = new String(UserInterface.this.passwordField.getPassword());

		correctUsername = username.equals("John");
		correctPassword = password.equals("p@ssw0rd");

		if (correctUsername && correctPassword) {
		    JOptionPane.showMessageDialog(null, "Welcome to INNKEEPER system!", "Successful Login",
			    JOptionPane.PLAIN_MESSAGE);
		    JFrame mainMenu = new MainMenuController();
		    mainMenu.setVisible(true);
		    UserInterface.this.dispose();
		} // end if
		else if (numberOfAttempts < maxAttempts) {
		    String message = "Wrong username or password!\n";
		    message += "Number of attempts left: " + (maxAttempts - numberOfAttempts++);

		    JOptionPane.showMessageDialog(null, message, "Failed Login Attempt", JOptionPane.WARNING_MESSAGE);
		} // end else if
		else {
		    JOptionPane.showMessageDialog(null, "You've used up all of your attempts! Goodbye!",
			    "Failed Credential Verification", JOptionPane.ERROR_MESSAGE);
		    System.exit(0);
		} // end else
	    }
	});
	loginButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
	loginButton.setBounds(164, 190, 120, 36);
	this.contentPane.add(loginButton);
    }

}
