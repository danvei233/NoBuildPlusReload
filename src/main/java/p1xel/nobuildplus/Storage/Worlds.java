package p1xel.nobuildplus.Storage;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import p1xel.nobuildplus.NoBuildPlus;

import java.io.File;
import java.io.IOException;

public class Worlds {
    public wflags = new File();
    public wflagc = new YamlConfiguration();
    public static void createWorldsFile() {

        File file = new File(NoBuildPlus.getInstance().getDataFolder(), "worlds.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        reload();
    }

    public static FileConfiguration get() {
        return wflagc;
    }
public static FileConfiguration reload() {
        wflags = new File(NoBuildPlus.getInstance().getDataFolder(), "worlds.yml");
        wflagc.loadConfiguration(wflags);
                return wflagc;
    }
    public static void set(String path, Object value) {
        
        wflagc.set(path,value);
        try {
            wflagc.save(wflags);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    public static boolean isDenyMessageExist(String world) {
        return get().get(world + ".deny-message") != null;
    }

    public static String getDenyMessage(String world) {
        return ChatColor.translateAlternateColorCodes('&', get().getString(world + ".deny-message"));
    }

    public static String getPermission(String world) {
        return get().getString(world + ".permission");
    }

    public static boolean isSpawnLocationSet(String world) {
        if (get().get(world + ".spawn-loc") != null) {
            return true;
        }
        return false;
    }

    public static Location getSpawnLocation(String world) {
        return (Location) get().get(world + ".spawn-loc");
    }

    public static void setSpawnLocation(String world, Location loc) {
        set(world + ".spawn-loc", loc);
    }

    public static void createWorld(String world) {

        for (String flagname : FlagsManager.getFlags()) {
            set(world + ".flags." + flagname, Settings.getDefaultFlag(flagname));
        }

        set(world + ".permission", Settings.getPermission());
        set(world + ".deny-message", Settings.getDenyMessageString());

    }

    public static void removeWorld(String world) {

        set(world, null);

    }

    public static void setFlag(String world, String flag, boolean bool) {
        set(world + ".flags." + flag, bool);
    }

    public static boolean getFlag(String world, String flag) {
        return get().getBoolean(world + ".flags." + flag);
    }



}
