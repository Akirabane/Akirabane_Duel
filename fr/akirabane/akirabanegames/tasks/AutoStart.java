package fr.akirabane.akirabanegames.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.akirabane.akirabanegames.Main;
import fr.akirabane.akirabanegames.State;

public class AutoStart extends BukkitRunnable{

	private int timer = 10;
	private Main main;
	
	public AutoStart(Main main) {
		// TODO Auto-generated constructor stub
		this.main = main;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		for(Player pls : main.getPlayers()) {
			pls.setLevel(timer);
		}
		
		if(timer == 10)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.timerMessage").replace("&", "�") + timer + "s");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (timer == 5)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.timerMessage").replace("&", "�") + timer + "s");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (timer == 4)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.timerMessage").replace("&", "�") + timer + "s");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (timer == 3)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.timerMessage").replace("&", "�") + timer + "s");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (timer == 2)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.timerMessage").replace("&", "�") + timer + "s");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (timer == 1)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.timerMessage").replace("&", "�") + timer + "s");				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	
		if(timer == 0)
		{
			try {
				Bukkit.broadcastMessage(main.getConfig().getString("strings.messages.autoStart.gameStart").replace("&", "�"));				
			} catch (Exception e) {
				// TODO: handle exception
			}
			main.setState(State.PLAYING);
			
			for(int i = 0; i < main.getPlayers().size(); i++)
			{	
				Player player = main.getPlayers().get(i);
				Location spawn = main.getSpawns().get(i);
				player.teleport(spawn);
				player.getInventory().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
				player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
				player.getInventory().setItem(1, new ItemStack(Material.BOW));
				player.getInventory().setItem(2, new ItemStack(Material.ARROW, 64));
				player.getInventory().setItem(3, new ItemStack(Material.SPLASH_POTION));
				player.getInventory().setItem(4, new ItemStack(Material.SPLASH_POTION));
				player.getInventory().setItem(5, new ItemStack(Material.SPLASH_POTION));
				player.getInventory().setItem(6, new ItemStack(Material.SPLASH_POTION));
				player.getInventory().setItem(7, new ItemStack(Material.SPLASH_POTION));
				player.getInventory().setItem(8, new ItemStack(Material.SPLASH_POTION));
				player.updateInventory();
			}
			
			GameCycle cycle = new GameCycle(main);
			cycle.runTaskTimer(main, 0, 20);
			
			cancel();
		}
		
		timer--;
	}

}
