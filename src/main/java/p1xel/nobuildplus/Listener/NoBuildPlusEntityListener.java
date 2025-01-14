package p1xel.nobuildplus.Listener;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import p1xel.nobuildplus.Hook.HRes;
import p1xel.nobuildplus.Storage.FlagsManager;
import p1xel.nobuildplus.Storage.Settings;
import p1xel.nobuildplus.Storage.Worlds;

public class NoBuildPlusEntityListener implements Listener {

    // Flag: Mob Damage
    // Flag: Pvp
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {

        String world = e.getEntity().getWorld().getName();
        Entity p = e.getDamager();
        Entity target = e.getEntity();

        if (HRes.isInRes(p)) {
            return;
        }

        if (HRes.isInRes(target)) {
            return;
        }

        String mobDamage = "mob-damage";
        // Flag: Mob Damage
        if (FlagsManager.getFlagsIsEnabled(mobDamage)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, mobDamage)) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (FlagsManager.getFlagsType(mobDamage).equalsIgnoreCase("all")) {

                            if (p instanceof Player && !(target instanceof Player)) {

                                if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                                e.setCancelled(true);
                                return;

                            }

                        }

                        if (FlagsManager.getFlagsType(mobDamage).equalsIgnoreCase("list")) {

                            if (p instanceof Player && !(target instanceof Player)) {

                                for (String string : FlagsManager.getFlagsList(mobDamage)) {

                                    if (e.getEntityType() == EntityType.valueOf(string)) {

                                        if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
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

        String frame = "frame";

        // Flag: Frame (Damage)
        if (FlagsManager.getFlagsIsEnabled(frame)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, frame)) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (FlagsManager.FrameIsIncludingGlowFrame()) {

                            if (target instanceof GlowItemFrame) {

                                if (p instanceof Player) {
                                    if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                                }
                                e.setCancelled(true);
                                return;

                            }

                        }

                        if (target instanceof ItemFrame) {

                            if (p instanceof Player) {
                                if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                            }
                            e.setCancelled(true);
                            return;

                        }


                    }

                }

            }
        }

        String tntDamage = "tnt-damage";
        // Flag: tnt-damage
        if (e.getDamager().getType() == EntityType.PRIMED_TNT || e.getDamager().getType() == EntityType.MINECART_TNT) {
            if (FlagsManager.getFlagsIsEnabled(tntDamage)) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, tntDamage)) {

                        if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                        e.setCancelled(true);
                        return;

                    }
                }

            }
        }

        String armorstand = "armorstand";

        // Flag: Armor Stand (Damage)
        if (FlagsManager.getFlagsIsEnabled(armorstand)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, armorstand)) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (p instanceof Player) {

                            if (target instanceof ArmorStand || target.getType() == EntityType.ARMOR_STAND) {

                                if (Worlds.isDenyMessageExist(world)) {
                                    p.sendMessage(Worlds.getDenyMessage(world));
                                }
                                e.setCancelled(true);
                                return;

                            }

                        }

                    }
                }
            }

        }

        String pvp = "pvp";
        // Flag: Pvp
        if (FlagsManager.getFlagsIsEnabled(pvp)) {

            if (!Settings.getEnableWorldList().contains(world)) {
                return;
            }

            if (Worlds.getFlag(world, pvp)) {
                return;
            }

            if (p.hasPermission(Worlds.getPermission(world))) {
                return;
            }

            if (p instanceof Player && target instanceof Player) {

                if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                e.setCancelled(true);

            }

        }


    }

    // Flag: Mob Explode
    @EventHandler
    public void onMobExplode(EntityExplodeEvent e) {

        String world = e.getEntity().getWorld().getName();

        if (HRes.isInRes(e.getEntity())) {
            return;
        }

        String mobExplode = "mob-explode";

        if (FlagsManager.getFlagsIsEnabled(mobExplode)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, mobExplode)) {

                    if (FlagsManager.getFlagsType(mobExplode).equalsIgnoreCase("all")) {

                        e.setCancelled(true);
                        return;

                    }

                    if (FlagsManager.getFlagsType(mobExplode).equalsIgnoreCase("list")) {

                        for (String string : FlagsManager.getFlagsList(mobExplode)) {

                            if (e.getEntityType() == EntityType.valueOf(string)) {

                                e.setCancelled(true);
                                return;

                            }

                        }


                    }
                }
            }

        }

        String tnt = "tnt";

        // Flag: Tnt
        if (FlagsManager.getFlagsIsEnabled(tnt)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, tnt)) {

                    if (e.getEntityType() == EntityType.PRIMED_TNT || e.getEntityType() == EntityType.MINECART_TNT) {
                        e.setCancelled(true);
                    }

                }
            }

        }

    }

    // Flag: frame(move)
    // Flag: Armor stand
    // Flag: villager
    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent e) {

        String world = e.getPlayer().getWorld().getName();
        Player p = e.getPlayer();

        if (HRes.isInRes(p)) {
            return;
        }

        if (HRes.isInRes(e.getRightClicked())) {
            return;
        }

        String frame = "frame";

        if (FlagsManager.getFlagsIsEnabled(frame)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, frame)) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (FlagsManager.FrameIsIncludingGlowFrame()) {

                            if (e.getRightClicked().getType() == EntityType.GLOW_ITEM_FRAME) {

                                if (Worlds.isDenyMessageExist(world)) {
                                    p.sendMessage(Worlds.getDenyMessage(world));
                        }
                                e.setCancelled(true);
                                return;

                            }

                        }

                        if (e.getRightClicked().getType() == EntityType.ITEM_FRAME) {

                            if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                            e.setCancelled(true);
                            return;

                        }

                    }
                }

            }

        }

        String ride = "ride";

        // Flag: Ride
        if (FlagsManager.getFlagsIsEnabled(ride)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, ride)) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (FlagsManager.getFlagsType(ride).equalsIgnoreCase("list")) {

                            for (String name : FlagsManager.getFlagsList(ride)) {
                                if (e.getRightClicked().getType() == EntityType.valueOf(name)) {
                                    if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                                    e.setCancelled(true);
                                    return;
                                }

                            }

                        }
                    }
                }
            }

        }

        String armorstand = "armorstand";

        if (FlagsManager.getFlagsIsEnabled(armorstand)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, armorstand)) {

                    if (!p.hasPermission(Worlds.getPermission(world))) {

                        if (e.getRightClicked().getType() == EntityType.ARMOR_STAND || e.getRightClicked() instanceof ArmorStand) {

                            if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                            e.setCancelled(true);
                            return;

                        }

                    }
                }

            }

        }

        if (e.getRightClicked().getType() == EntityType.VILLAGER) {

            String villager = "villager";

            if (FlagsManager.getFlagsIsEnabled(villager)) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, villager)) {

                        if (!p.hasPermission(Worlds.getPermission(world))) {

                            if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                            e.setCancelled(true);

                        }
                    }

                }

            }
        }
    }

    // Flag: Void Teleport
    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        String world = e.getEntity().getWorld().getName();
        Entity entity = e.getEntity();

        String voidtpFlag = "voidtp";
        String falldamageFlag = "fall-damage";

        if (entity instanceof Player) {
            if (HRes.isInRes(entity)) {
                return;
            }
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.VOID) {
            if (FlagsManager.getFlagsIsEnabled(voidtpFlag)) {
                if (Settings.getEnableWorldList().contains(world)) {
                    if (Worlds.getFlag(world, voidtpFlag)) {
                        if (Worlds.isSpawnLocationSet(world)) {
                            if (entity instanceof Player) {
                                Player p = (Player) e.getEntity();
                                p.teleport(Worlds.getSpawnLocation(world));
                                p.setFallDistance(0);
                            }
                        }
                    }
                }
            }
        }

        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            if (entity instanceof Player) {
                if (FlagsManager.getFlagsIsEnabled(falldamageFlag)) {
                    if (Settings.getEnableWorldList().contains(world)) {
                        if (!Worlds.getFlag(world, falldamageFlag)) {
                            Player p = (Player) e.getEntity();
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }


    }





    // Flag: shoot
    @EventHandler
    public void onShoot(EntityShootBowEvent e) {

        String world = e.getEntity().getWorld().getName();
        Entity p = e.getEntity();

        String shootFlag = "shoot";

        if (p instanceof Player) {
            if (HRes.isInRes(p)) {
                return;
            }
        }

        if (FlagsManager.getFlagsIsEnabled(shootFlag)) {

            if (Settings.getEnableWorldList().contains(world)) {

                if (!Worlds.getFlag(world, shootFlag)) {

                    if (p instanceof Player) {

                        if (!p.hasPermission(Worlds.getPermission(world))) {

                            if (e.getBow().getType() == Material.BOW) {
                                if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
                                e.setCancelled(true);
                            }
                            if (FlagsManager.getBoolInFlag(shootFlag, "include-crossbow")) {
                                if (e.getBow().getType() == Material.CROSSBOW) {
                                    if (Worlds.isDenyMessageExist(world)) {
                            p.sendMessage(Worlds.getDenyMessage(world));
                        }
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
    public void onCreatureSpawn(CreatureSpawnEvent e) {

        String world = e.getEntity().getWorld().getName();

        String armorstandFlag = "armorstand";

        if (HRes.isInRes(e.getEntity())) {
            return;
        }

        if (e.getEntityType() == EntityType.ARMOR_STAND) {

            if (FlagsManager.getFlagsIsEnabled(armorstandFlag)) {

                if (Settings.getEnableWorldList().contains(world)) {

                    if (!Worlds.getFlag(world, armorstandFlag)) {

                        // 这个我弄不了给有权限的人放置
                        // 实在没有头绪了
                        e.setCancelled(true);


                    }

                }

            }

        }

    }

    @EventHandler
    public void onEntityInteract(EntityInteractEvent e) {

        Entity entity = e.getEntity();
        String world = entity.getWorld().getName();
        
        String farmbreakFlag = "farmbreak";

        if (HRes.isInRes(entity)) {
            return;
        }

        // Flag: farmbreak

            if (e.getBlock().getType() == Material.matchMaterial("SOIL") || e.getBlock().getType() == Material.matchMaterial("FARMLAND")) {

                if (FlagsManager.getFlagsIsEnabled(farmbreakFlag)) {

                    if (Settings.getEnableWorldList().contains(world)) {

                        if (!Worlds.getFlag(world, farmbreakFlag)) {

                            e.setCancelled(true);

                    }


                }
            }
        }

    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {

        String mobSpawnFlag = "mob-spawn";


        if (FlagsManager.getFlagsIsEnabled(mobSpawnFlag)) {
            Entity entity = e.getEntity();
            String world = entity.getWorld().getName();
            boolean contain = Settings.getEnableWorldList().contains(world);
            if (contain) {

                if (!Worlds.getFlag(world, mobSpawnFlag)) {

                    if (FlagsManager.getFlagsType(mobSpawnFlag).equalsIgnoreCase("list")) {

                        EntityType et = e.getEntityType();

                        for (String mob : FlagsManager.getFlagsList(mobSpawnFlag)) {

                            if (et == EntityType.valueOf(mob)) {

                                e.setCancelled(true);

                            }

                        }

                    }

                }


            }
        }

    }


}
