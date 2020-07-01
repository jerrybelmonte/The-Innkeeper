package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MainMenuController extends JFrame {
    private static final long serialVersionUID = -4712210072304030800L;
    private JPanel contentPane;
    private static JFrame inputData = null;
    private static JFrame displayReport = null;

    public MainMenuController() {
	WindowListener listener = new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
		e.getWindow().dispose();
		MainMenuController.this.setVisible(true);
	    }

	    @Override
	    public void windowClosed(WindowEvent e) {
		MainMenuController.this.setVisible(true);
	    }
	};
	this.setTitle("Main Menu");
	this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
	this.setLocationRelativeTo(null);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.setContentPane(this.contentPane);
	this.contentPane.setLayout(null);

	JButton inputButton = new JButton("Input Data");
	inputButton.setBounds(130, 45, 185, 40);
	inputButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	inputButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		MainMenuController.this.setVisible(false);

		if (inputData == null) {
		    inputData = new InputDataWindow();
		    inputData.addWindowListener(listener);
		}

		inputData.setVisible(true);
	    }
	});
	this.contentPane.add(inputButton);

	JButton displayButton = new JButton("Display Report");
	displayButton.setBounds(130, 110, 185, 40);
	displayButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	displayButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		MainMenuController.this.setVisible(false);

		if (displayReport == null) {
		    displayReport = new DisplayReportWindow();
		    displayReport.addWindowListener(listener);
		}

		displayReport.setVisible(true);
	    }
	});
	this.contentPane.add(displayButton);

	JButton exitButton = new JButton("Exit");
	exitButton.setBounds(130, 180, 185, 40);
	exitButton.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
	exitButton.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	this.contentPane.add(exitButton);
    }

}
