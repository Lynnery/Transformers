package com.yoni.test.AbilitySystem.abilities;

import com.yoni.test.AbilitySystem.Ability;
import com.yoni.test.Main;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CobwebShooting extends Ability {

    public String getName() {
        return "Cobweb Shooting";
    }

    public String getDescription() {
        return "Shoot... COBWEB!!! OMG!!!";
    }

    @EventHandler
    public void cobwebShooting(PlayerInteractEvent event) {
        if(event.getAction().name().contains("LEFT")) {
            final Player player = event.getPlayer();
            new BukkitRunnable() {
                int timer = 0;
                public void run() {
                    if(timer >= 8) {
                        cancel();
                    }
                    timer++;
                    Vector direction = player.getEyeLocation().getDirection();
                    FallingBlock cobweb = player.getWorld().spawnFallingBlock(player.getEyeLocation(), Material.COBWEB, (byte) 0);
                    cobweb.setVelocity(direction.multiply(1.3));
                };
            }.runTaskTimer(Main.getInstance(), 2, 2);
        }
    }
}
