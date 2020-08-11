package io.github.slazurin.slvanishapi;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import de.myzelyam.api.vanish.VanishAPI;


public class SLVanishApi extends JavaPlugin {
    private boolean superVanishEnabled = false;
    @Override
    public void onEnable() {
        this.superVanishEnabled = false;
        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish") || Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
            this.superVanishEnabled = true;
        }
    }
    
    public List<Player> getVisiblePlayers() {
        List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
        for (Player p : onlinePlayers) {
            if (this.isVanished(p)) {
                onlinePlayers.remove(p);
            }
        }
        return onlinePlayers;
    }
    
    public boolean isVanished(Player player) {
        if (this.superVanishEnabled) {
            return VanishAPI.isInvisible(player);
        }
        
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }
}
