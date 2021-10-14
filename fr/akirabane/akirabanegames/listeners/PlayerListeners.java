package fr.akirabane.akirabanegames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.akirabane.akirabanegames.Main;
import fr.akirabane.akirabanegames.State;
import fr.akirabane.akirabanegames.tasks.AutoStart;

public class PlayerListeners implements Listener {

	private Main main;
	
	public PlayerListeners(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		Location spawn = new Location(Bukkit.getWorld("world"), -175.500, 82, -62.500, 0.0f, 0.0f);
		player.teleport(spawn);
		
		player.getInventory().clear();
		player.setFoodLevel(20);
		player.setHealth(20);
		
		if(main.isState(State.PLAYING))
		{
			event.setJoinMessage(null);
			try {
				player.kickPlayer(main.getConfig().getString("strings.messages.playerListerners.statePlayingKickMessage").replace("&", "§"));				
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (main.isState(State.FINISH))
		{
			event.setJoinMessage(null);
			try {
				player.kickPlayer(main.getConfig().getString("strings.messages.playerListerners.stateFinishKickMessage").replace("&", "§"));				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		if(!main.getPlayers().contains(player)) main.getPlayers().add(player);
		player.setGameMode(GameMode.ADVENTURE);
		try {
			event.setJoinMessage(player.getName() + " " + main.getConfig().getString("strings.messages.playerListeners.joinMessage").replace("&", "§") + "<" + main.getPlayers().size() + "/" + Bukkit.getMaxPlayers() + ">");			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// >= 2
		if(main.isState(State.WAITING) && main.getPlayers().size() == 2)
		{
			AutoStart start = new AutoStart(main);
			start.runTaskTimer(main, 0, 20);
			main.setState(State.STARTING);
		}
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		if(main.getPlayers().contains(player))
		{
			main.getPlayers().remove(player);
		}
		event.setQuitMessage(null);
		main.checkWin();
	}
}
