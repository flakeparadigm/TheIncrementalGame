package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Player;

public class TIGview extends JFrame {
	
	// GAME INFO
	public static String NAME = "The Incremental Game";
	public static String VERSION = "v0.5";
	public static String CHANGELOG = "New since v0.4 : Secret prestige added";
	
	// Window settings
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private final int X_SCREEN_SIZE = ((int) tk.getScreenSize().getWidth());
	private final int Y_SCREEN_SIZE = ((int) tk.getScreenSize().getHeight());
	private final int X_WINDOW_SIZE = 500;
	private final int Y_WINDOW_SIZE = 300;
	public static final Color BACKGROUND = new Color(0xCCCCCC);
	
	// Static GUI Elements
	private JLabel TITLE = new JLabel(NAME + " " + VERSION);
	public static JLabel SUBTITLE = new JLabel("Welcome to " + NAME);
	private JLabel VERSIONLOG = new JLabel(CHANGELOG);
	
	public TIGview() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout( this.getContentPane(), BoxLayout.Y_AXIS));
		this.setLocation(X_SCREEN_SIZE / 2 - X_WINDOW_SIZE / 2, Y_SCREEN_SIZE
				/ 2 - Y_WINDOW_SIZE / 2);
		this.setSize(X_WINDOW_SIZE, Y_WINDOW_SIZE);
		this.setTitle(NAME);
		this.getContentPane().setBackground(TIGview.BACKGROUND);
		
		// create user panel
		Player player = new Player("Default");
		UserPanel userPanel = new UserPanel(player);
		
		// set other element info
		TITLE.setAlignmentX(Component.CENTER_ALIGNMENT);
		TITLE.setFont(new Font("Times New Roman", Font.BOLD, 32) );
		
		SUBTITLE.setAlignmentX(Component.CENTER_ALIGNMENT);
		SUBTITLE.setFont(new Font("Times New Roman", Font.PLAIN, 18) );
		
		VERSIONLOG.setAlignmentX(Component.CENTER_ALIGNMENT);
		VERSIONLOG.setFont(new Font("Times New Roman", Font.PLAIN, 14) );
		
		this.add(TITLE);
		this.add(SUBTITLE);
		
		this.add(userPanel);
		
		this.add(VERSIONLOG);
	}
	
}
