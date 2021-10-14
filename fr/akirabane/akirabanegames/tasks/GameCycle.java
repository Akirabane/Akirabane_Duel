package fr.akirabane.akirabanegames.tasks;

import org.bukkit.scheduler.BukkitRunnable;

import fr.akirabane.akirabanegames.Main;

public class GameCycle extends BukkitRunnable{

	@SuppressWarnings("unused")
	private Main main;
	private int timer = 300;
	
	public GameCycle(Main main) {
		this.main = main;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(timer == 0)
		{
			cancel();
		}
		timer--;
	}

}
