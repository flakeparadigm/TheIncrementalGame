package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Player;

public class TIGview extends JFrame {
	
	// GAME INFO
	public static String NAME = "The Incremental Game";
	public static String VERSION = "v0.6";
	public static String CHANGELOG = "New since v0.5 : Secret prestige added";
	
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
	
	// other items
	Player player;
	UserPanel userPanel;
	final String FILENAME = "save.tig";
	
	public TIGview() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowClosingListener());
		this.setLayout(new BoxLayout( this.getContentPane(), BoxLayout.Y_AXIS));
		this.setLocation(X_SCREEN_SIZE / 2 - X_WINDOW_SIZE / 2, Y_SCREEN_SIZE
				/ 2 - Y_WINDOW_SIZE / 2);
		this.setSize(X_WINDOW_SIZE, Y_WINDOW_SIZE);
		this.setTitle(NAME);
		this.getContentPane().setBackground(TIGview.BACKGROUND);
		
		// create user panel
		player = loadPlayer();
		userPanel = new UserPanel(player);
		
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
	
	private Player loadPlayer() {
		try {
			File file = new File(FILENAME);
			if (!file.exists()) {
				throw new IOException();
			}
			
			FileInputStream fis = new FileInputStream(FILENAME);
			ObjectInputStream loaderStream = new ObjectInputStream(fis);
	
			Player newPlayer = (Player) loaderStream.readObject();
	
			loaderStream.close();
			fis.close();
			
			System.out.println("Loaded saved player");
			return newPlayer;
			
		} catch(Exception e) {
			e.printStackTrace();
			return new Player("Default");
		}
	}
	private boolean savePlayer() {		
		try {
			File file = new File(FILENAME);
			try {
				// Try creating the file
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
				JOptionPane.showMessageDialog(
						userPanel,
						"Failed to save. (Could not create save file)\nSorry."
						);
			}
			
			FileOutputStream fos = new FileOutputStream(FILENAME);
			ObjectOutputStream saverStream = new ObjectOutputStream(fos);
	
			saverStream.writeObject(player);
	
			saverStream.close();
			fos.close();
			
			System.out.println("Loaded saved player");
			return true;
			
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private class WindowClosingListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			int shouldSave = JOptionPane.showConfirmDialog(userPanel,
					"Would you like to save your game?", "Save Game?",
					JOptionPane.YES_NO_OPTION);
			
			if (shouldSave == JOptionPane.YES_OPTION) {
				if(savePlayer()) {
					System.out.println("Game saved.");
				} else {
					System.out.println("Game *NOT* saved.");
				}
			}
		}
	}
	
}
