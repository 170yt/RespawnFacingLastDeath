package respawnfacinglastdeath;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public final class Main extends JavaPlugin implements Listener {

    private static Location deathLoc;
    private static Location loc;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        deathLoc = event.getEntity().getLocation();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        this.runTask(event);
    }

    private void runTask(PlayerRespawnEvent event) {
        BukkitTask task = Bukkit.getScheduler().runTaskLater(this, () -> {
            Vector vec = deathLoc.toVector().subtract(event.getPlayer().getLocation().toVector());
            loc = event.getPlayer().getEyeLocation().setDirection(vec);
            event.getPlayer().teleport(loc);
        }, 1L);
    }
}
