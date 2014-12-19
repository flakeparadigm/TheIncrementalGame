package controller;

import model.Player;
import view.UserPanel;

import java.lang.Runnable;

public class AutoIncrementer extends Thread {
	
	// outside things
	private Player player;
	private UserPanel panel;
	
	// status
	private boolean running = false;
	
	public AutoIncrementer(Player player, UserPanel panel) {
		super();
		
		this.player = player;
		this.panel = panel;
	}
	
	private void increment() {
		player.autoScore();
		panel.updateStats();
	}

	@Override
	public void run() {
		running = true;
		
		while( running ) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(running)
				increment();
		}
		
	}
	
	public void halt() {
		running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
}
