package fr.akirabane.akirabanegames.listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.akirabane.akirabanegames.Main;

public class DamageListeners implements Listener {

	private Main main;
	
	public DamageListeners(Main main) {
		this.main = main;
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		Entity victim = event.getEntity();
		if(victim instanceof Player)
		{
			Player player = (Player)victim;
			if(player.getHealth() <= event.getDamage())
			{
				event.setDamage(0);
				main.eliminate(player);
			}
		}
	}
	
	@EventHandler
	public void onPvp(EntityDamageByEntityEvent event) {
		Entity victim = event.getEntity();
		if(victim instanceof Player)
		{
			Player player = (Player)victim;
			Entity damager = event.getDamager();
			Player killer = player;
			
			if(player.getHealth() <= event.getDamage())
			{
				if(damager instanceof Player) killer = (Player)damager;
				
				if(damager instanceof Arrow)
				{
					Arrow arrow = (Arrow)damager;
					if(arrow.getShooter() instanceof Player)
					{
						killer = (Player) arrow.getShooter();
					}
				}
				try {
					killer.sendMessage(main.getConfig().getString("strings.messages.damageListeners.killerSendMessage").replace("&", "§") + victim.getName());					
				} catch (Exception e) {
					// TODO: handle exception
				}
				event.setDamage(0);
				main.eliminate(player);
			}
		}
	}
	
}
