package fr.akirabane.akirabanegames;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.akirabane.akirabanegames.listernes.DamageListeners;
import fr.akirabane.akirabanegames.listernes.PlayerListeners;

public class Main extends JavaPlugin{
	
	private List<Player> players = new ArrayList<>();
	private List<Location> spawn = new ArrayList<>();
	private State currentState;
	
	@SuppressWarnings("static-access")
	@Override
	public void onEnable() {
		saveDefaultConfig();
		setState(currentState.WAITING);
		
		//load spawns
        World world = Bukkit.getWorld("world");
        spawn.add(new Location(world, -61.500, 67, -55.500, -90.0f, 0.0f));
        spawn.add(new Location(world, -55.500, 67, -55.500, 90.0f, 0.0f));
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerListeners(this), this);
		pm.registerEvents(new DamageListeners(this), this);
	}
	
	public void setState(State currentState) {
		this.currentState = currentState;
	}
	
	public boolean isState(State currentState) {
		return this.currentState == currentState;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public List<Location> getSpawns(){
		return spawn;
	}

	public void eliminate(Player player) {
		// TODO Auto-generated method stub
		if(players.contains(player)) players.remove(player);
		try {
			player.kickPlayer(getConfig().getString("strings.messages.main.eliminateMessage").replace("&", "§"));			
		} catch (Exception e) {
			// TODO: handle exception
		}
		checkWin();
	}

	public void checkWin() {
		// TODO Auto-generated method stub
		if(players.size() == 1)
		{
			Player winner = players.get(0);
			try {
				Bukkit.broadcastMessage(winner.getName() + getConfig().getString("strings.messages.main.whoWinMessage").replace("&", "§"));				
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				winner.kickPlayer(getConfig().getString("strings.messages.main.winnerMessage").replace("&", "§"));				
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bukkit.shutdown();
		}
	}

}
