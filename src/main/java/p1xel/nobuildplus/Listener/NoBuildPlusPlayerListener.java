package p1xel.nobuildplus.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.ChestBoat;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import p1xel.nobuildplus.Hook.HRes;
import p1xel.nobuildplus.Storage.FlagsManager;
import p1xel.nobuildplus.Storage.Settings;
import p1xel.nobuildplus.Storage.Worlds;

public class NoBuildPlusPlayerListener implements Listener {

    // Flag: Use
    // Flag: Container
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (e.getClickedBlock() != null) {
            if (HRes.isInRes(e.getClickedBlock())) {
                return;
            }
        }

        // RIGHT CLICK BLOCK
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {

            // Flag: Use
            if (FlagsManager.getFlagsIsEnabled("use")) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, "use")) {

                        if (!p.hasPermission(Worlds.getPermission(world))) {

                            if (FlagsManager.getFlagsType("use").equalsIgnoreCase("list")) {

                                for (String name : FlagsManager.getFlagsList("use")) {
                                    Material block = Material.matchMaterial(name);
                                    if (block != null) {
                                        if (e.getClickedBlock().getType() == block) {
                                            p.sendMessage(Worlds.getDenyMessage(world));
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }

                                }

                            }
                        }
                    }
                }

            }

            // Flag: button
            if (FlagsManager.getFlagsIsEnabled("button")) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, "button")) {

                        if (!p.hasPermission(Worlds.getPermission(world))) {

                            if (FlagsManager.getFlagsType("button").equalsIgnoreCase("list")) {

                                for (String name : FlagsManager.getFlagsList("button")) {
                                    Material block = Material.matchMaterial(name);
                                    if (block != null) {
                                        if (e.getClickedBlock().getType() == block) {
                                            p.sendMessage(Worlds.getDenyMessage(world));
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }

                                }

                            }
                        }
                    }
                }

            }

            // Flag: door-interact
            if (FlagsManager.getFlagsIsEnabled("door-interact")) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, "door-interact")) {

                        if (!p.hasPermission(Worlds.getPermission(world))) {

                            if (FlagsManager.getFlagsType("door-interact").equalsIgnoreCase("list")) {

                                for (String name : FlagsManager.getFlagsList("door-interact")) {
                                    Material block = Material.matchMaterial(name);
                                    if (block != null) {
                                        if (e.getClickedBlock().getType() == block) {
                                            p.sendMessage(Worlds.getDenyMessage(world));
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }

                                }

                            }
                        }
                    }
                }

            }

            // Flag: lever
            if (e.getClickedBlock().getType() == Material.LEVER) {
                if (FlagsManager.getFlagsIsEnabled("lever")) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, "lever")) {

                            if (!p.hasPermission(Worlds.getPermission(world))) {
                                p.sendMessage(Worlds.getDenyMessage(world));
                                e.setCancelled(true);
                                return;
                            }
                        }
                    }

                }
            }

            // Flag: trapdoor-interact
            if (FlagsManager.getFlagsIsEnabled("trapdoor-interact")) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, "trapdoor-interact")) {

                        if (!p.hasPermission(Worlds.getPermission(world))) {

                            if (FlagsManager.getFlagsType("trapdoor-interact").equalsIgnoreCase("list")) {

                                for (String name : FlagsManager.getFlagsList("trapdoor-interact")) {
                                    Material block = Material.matchMaterial(name);
                                    if (block != null) {
                                        if (e.getClickedBlock().getType() == block) {
                                            p.sendMessage(Worlds.getDenyMessage(world));
                                            e.setCancelled(true);
                                            return;
                                        }
                                    }

                                }

                            }
                        }
                    }
                }
            }

                // Flag: fencegate-interact
                if (FlagsManager.getFlagsIsEnabled("fencegate-interact")) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, "fencegate-interact")) {

                            if (!p.hasPermission(Worlds.getPermission(world))) {

                                if (FlagsManager.getFlagsType("fencegate-interact").equalsIgnoreCase("list")) {

                                    for (String name : FlagsManager.getFlagsList("fencegate-interact")) {
                                        Material block = Material.matchMaterial(name);
                                        if (block != null) {
                                            if (e.getClickedBlock().getType() == block) {
                                                p.sendMessage(Worlds.getDenyMessage(world));
                                                e.setCancelled(true);
                                                return;
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
        } // THE END OF THE "RIGHT CLICK BLOCK"

        // RIGHT CLICK BLOCK OR RIGHT CLICK AIR
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {

            if (e.getItem() != null) {

                // Flag: boat
                for (String key : FlagsManager.getFlagsList("boat")) {

                    if (e.getItem().getType() == Material.matchMaterial(key)) {

                        if (FlagsManager.getFlagsIsEnabled("boat")) {

                            if (Settings.getEnableWorldList().contains(world)) {

                                if (!Worlds.getFlag(world, "boat")) {

                                    if (!p.hasPermission(Worlds.getPermission(world))) {

                                        p.sendMessage(Worlds.getDenyMessage(world));
                                        e.setCancelled(true);
                                        return;

                                    }
                                }
                            }

                        }
                    }
                }

                // Flag: egg-throw
                if (FlagsManager.getFlagsIsEnabled("egg-throw")) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, "egg-throw")) {

                            if (!p.hasPermission(Worlds.getPermission(world))) {
                                if (e.getItem().getType() == Material.EGG) {
                                    p.sendMessage(Worlds.getDenyMessage(world));
                                    e.setCancelled(true);
                                    return;
                                }
                            }
                        }
                    }

                }

                // Flag: snowball-throw
                if (FlagsManager.getFlagsIsEnabled("snowball-throw")) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, "snowball-throw")) {

                            if (!p.hasPermission(Worlds.getPermission(world))) {
                                if (e.getItem().getType() == Material.SNOWBALL) {
                                    p.sendMessage(Worlds.getDenyMessage(world));
                                    e.setCancelled(true);
                                    return;
                                }
                            }
                        }
                    }

                }
            }

        } // THE END OF THE "RIGHT CLICK BLOCK OR RIGHT CLICK AIR"

        // Flag: farmbreak
        if (e.getAction() == Action.PHYSICAL) {
                if (FlagsManager.getFlagsIsEnabled("farmbreak")) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, "farmbreak")) {

                            if (e.getClickedBlock().getType() == Material.matchMaterial("SOIL") || e.getClickedBlock().getType() == Material.matchMaterial("FARMLAND")) {
                                    e.setCancelled(true);
                                    return;
                            }

                    }
                }

            }
        }



        // Flag: Container
        if (FlagsManager.getFlagsIsEnabled("container")) {

            if (!Settings.getEnableWorldList().contains(world)) {
                return;
            }

            if (Worlds.getFlag(world, "container")) {
                return;
            }

            if (p.hasPermission(Worlds.getPermission(world))) {
                return;
            }

            if (FlagsManager.getFlagsType("container").equalsIgnoreCase("list")) {

                for (String name : FlagsManager.getFlagsList("container")) {
                    Material block = Material.matchMaterial(name);
                    if (block != null) {
                        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == block) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                            e.setCancelled(true);
                        }
                    }

                }

            }

        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        // Flag: boat (Use)
                if (FlagsManager.getFlagsIsEnabled("boat")) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, "boat")) {

                            if (!p.hasPermission(Worlds.getPermission(world))) {

                                if (e.getRightClicked() instanceof Boat) {

                                    p.sendMessage(Worlds.getDenyMessage(world));
                                    e.setCancelled(true);
                                    return;
                                }

                                if (FlagsManager.BoatIsIncludingChestBoat()) {
                                    if (e.getRightClicked() instanceof ChestBoat) {
                                        p.sendMessage(Worlds.getDenyMessage(world));
                                        e.setCancelled(true);
                                        return;
                                    }
                                }

                            }
                        }
                    }

                }

    }

    // Flag: move
    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (!FlagsManager.getFlagsIsEnabled("move")) {
            return;
        }

        if (!Settings.getEnableWorldList().contains(world)) {
            return;
        }

        if (Worlds.getFlag(world, "move")) {
            return;
        }

        if (p.hasPermission(Worlds.getPermission(world))) {
            return;
        }

        if (!FlagsManager.getFlagsList("move").contains(p)) {
            p.sendMessage(Worlds.getDenyMessage(world));
            e.setCancelled(true);
        }

    }

    // Flag: Bed
    @EventHandler
    public void onBed(PlayerBedEnterEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (FlagsManager.getFlagsIsEnabled("bed")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "bed")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        p.sendMessage(Worlds.getDenyMessage(world));
                        e.setCancelled(true);

                    }
                }

            }

        }

    }

    // Flag: Chat
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (FlagsManager.getFlagsIsEnabled("chat")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "chat")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        p.sendMessage(Worlds.getDenyMessage(world));
                        e.setCancelled(true);

                    }
                }

            }
        }

    }

    // Flag: Command
    @EventHandler
    public void onCommandExecute(PlayerCommandPreprocessEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (FlagsManager.getFlagsIsEnabled("command")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "command")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (FlagsManager.getFlagsType("command").equalsIgnoreCase("all")) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                            e.setCancelled(true);
                            return;
                        }

                        if (FlagsManager.getFlagsType("command").equalsIgnoreCase("list")) {
                            for (String cmd : FlagsManager.getFlagsList("command")) {
                                if (e.getMessage().equalsIgnoreCase("/" + cmd)) {
                                    p.sendMessage(Worlds.getDenyMessage(world));
                                    e.setCancelled(true);
                                }
                            }
                        }



                    }
                }

            }
        }

    }

    @EventHandler
    public void ArmorStand(PlayerArmorStandManipulateEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (FlagsManager.getFlagsIsEnabled("armorstand")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "armorstand")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        p.sendMessage(Worlds.getDenyMessage(world));
                        e.setCancelled(true);

                    }

                }

            }

        }

    }

    @EventHandler
    public void onBucketUse(PlayerBucketEmptyEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (FlagsManager.getFlagsIsEnabled("bucket-place")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "bucket-place")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        p.sendMessage(Worlds.getDenyMessage(world));
                        e.setCancelled(true);

                    }

                }

            }

        }

    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (FlagsManager.getFlagsIsEnabled("bucket-fill")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "bucket-fill")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        p.sendMessage(Worlds.getDenyMessage(world));
                        e.setCancelled(true);

                    }

                }

            }

        }

    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();
        Item droppedItem = e.getItemDrop();

        // Flag: drop-item (For Player)
        if (FlagsManager.getFlagsIsEnabled("drop-item")) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, "drop-item")) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (FlagsManager.getFlagsType("drop-item").equalsIgnoreCase("all")) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                            e.setCancelled(true);
                            return;
                        }

                        if (FlagsManager.getFlagsType("drop-item").equalsIgnoreCase("list")) {

                            for (String itemName : FlagsManager.getFlagsList("drop-item")) {
                                Material item = Material.matchMaterial(itemName);
                                if (item != null) {
                                    if (droppedItem.getItemStack().getType() == item) {
                                        p.sendMessage(Worlds.getDenyMessage(world));
                                        e.setCancelled(true);
                                    }
                                }
                            }

                        }
                    }
                }
            }

        }

    }


}