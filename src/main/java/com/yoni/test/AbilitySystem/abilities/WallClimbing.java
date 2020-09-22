package com.yoni.test.AbilitySystem.abilities;

import com.yoni.test.AbilitySystem.Ability;
import com.yoni.test.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class WallClimbing extends Ability {

    public String getName() {
        return "Wall-Climbing";
    }

    public String getDescription() {
        return "Get near a tower of blocks and start climbing!";
    }

    @EventHandler
    public void spiderMode(PlayerToggleSneakEvent event) {
        final Player player = event.getPlayer();
        final Vector vector = new Vector(0, 0.1, 0);

        new BukkitRunnable() {

            boolean facingAWall = false;
            public void run() {
                facingAWall = player.getLocation()
                        .add(player.getEyeLocation()
                                .getDirection())
                        .getBlock()
                        .getType() != Material.AIR;
                if(player.isSneaking() && !facingAWall) {
                    cancel();
                    return;
                }
                if(!player.isSneaking()) {
                    cancel();
                }
                player.setVelocity(vector);
            };
        }.runTaskTimer(Main.getPlugin(Main.class), 1, 1);
    }
}
