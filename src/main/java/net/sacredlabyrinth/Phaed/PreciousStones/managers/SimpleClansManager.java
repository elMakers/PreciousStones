package net.sacredlabyrinth.Phaed.PreciousStones.managers;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.Field;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * @author phaed
 */
@SuppressWarnings("deprecation")
public final class SimpleClansManager {
    private PreciousStones plugin;
    private SimpleClans simpleClans;

    /**
     *
     */
    public SimpleClansManager() {
        plugin = PreciousStones.getInstance();
        getSimpleClans();
    }

    private void getSimpleClans() {
        if (simpleClans == null) {
            Plugin test = Bukkit.getServer().getPluginManager().getPlugin("SimpleClans");

            if (test != null) {
                this.simpleClans = (SimpleClans) test;
            }
        }
    }

    /**
     * Whether SimpleClans was loaded
     *
     * @return
     */
    public boolean hasSimpleClans() {
        if (plugin.getSettingsManager().isDisableSimpleClanHook()) {
            return false;
        }

        return simpleClans != null;
    }

    /**
     * Whether SimpleClans was loaded
     *
     * @return
     */
    public boolean hasSimpleClans2() {
        if (plugin.getSettingsManager().isDisableSimpleClanHook()) {
            return false;
        }

        return false;
    }

    /**
     * Announce to players clan
     *
     * @param playerName
     * @param message
     */
    public void clanAnnounce(String playerName, String message) {
        if (hasSimpleClans()) {
        	OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
	        	if (player != null) {
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp = simpleClans.getClanManager().getClanPlayer(player);
	
	            if (cp != null) {
	                cp.getClan().clanAnnounce("PreciousStones", message);
	            }
            }
        }
    }

    /**
     * Adds a message to the player's clan's BB
     *
     * @param player
     * @param message
     */
    public void addBB(Player player, String message) {
        if (hasSimpleClans()) {
            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp = simpleClans.getClanManager().getClanPlayer(player);

            if (cp != null) {
                cp.getClan().addBb("[PreciousStones]", message);
            }
        }
    }

    /**
     * Check whether any of a player's clan members are online
     *
     * @param playerName
     * @return
     */
    public boolean isAnyOnline(String playerName) {
        if (hasSimpleClans()) {
        	OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        	if (player != null) {
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp = simpleClans.getClanManager().getClanPlayer(player);
	
	            if (cp != null) {
	                if (cp.getClan().isAnyOnline()) {
	                    return true;
	                }
	            }
            }
        }

        return false;
    }

    /**
     * Check if
     *
     * @param field
     * @param offenderName
     * @return
     */
    public boolean inWar(Field field, String offenderName) {
        if (hasSimpleClans()) {
        	OfflinePlayer p = Bukkit.getOfflinePlayer(offenderName);
        	OfflinePlayer pOwner = Bukkit.getOfflinePlayer(field.getOwner());
        	if (p != null && pOwner != null) {
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp = simpleClans.getClanManager().getClanPlayer(p);
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cpOwner = simpleClans.getClanManager().getClanPlayer(pOwner);
	
	            if (cp != null && cpOwner != null) {
	                List<net.sacredlabyrinth.phaed.simpleclans.Clan> warringClans = cp.getClan().getWarringClans();
	
	                String ownerClan = cpOwner.getTag();
	
	                for (net.sacredlabyrinth.phaed.simpleclans.Clan warring : warringClans) {
	                    if (ownerClan.equals(warring.getTag())) {
	                        return true;
	                    }
	                }
	            }
            }
        }

        return false;
    }

    /**
     * @param owner
     * @param playerName
     * @return
     */
    public boolean isAllyOwner(String owner, String playerName) {
        if (hasSimpleClans()) {
            Player player = plugin.getServer().getPlayerExact(playerName);

            if (player != null) {
            	OfflinePlayer pOwner = Bukkit.getOfflinePlayer(owner);
            	if (pOwner != null) {
	                net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cpOwner = simpleClans.getClanManager().getClanPlayer(pOwner);
	
	                if (cpOwner != null) {
	                    return cpOwner.isAlly(player);
	                }
                }
            }
        }
        return false;
    }

    /**
     * Check if two players are clanmates
     *
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public boolean isClanMate(String playerOne, String playerTwo) {
        if (hasSimpleClans()) {
        	OfflinePlayer p1 = Bukkit.getOfflinePlayer(playerOne);
        	OfflinePlayer p2 = Bukkit.getOfflinePlayer(playerTwo);
        	if (p1 != null && p2 != null) {
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp1 = simpleClans.getClanManager().getClanPlayer(p1);
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp2 = simpleClans.getClanManager().getClanPlayer(p2);
	
	            if (cp1 != null && cp2 != null) {
	                if (cp1.getClan().equals(cp2.getClan())) {
	                    return true;
	                }
	            }
            }
        }

        return false;
    }

    /**
     * Check if a player is in a clan
     *
     * @param playerName
     * @param clanName
     * @return
     */
    public boolean isInClan(String playerName, String clanName) {
        if (hasSimpleClans()) {
        	OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        	if (player != null) {
	            net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp = simpleClans.getClanManager().getClanPlayer(player);
	
	            if (cp != null) {
	                if (cp.getTag().equals(clanName)) {
	                    return true;
	                }
	            }
            }
        }

        return false;
    }

    /**
     * Gets a clan by the target provided
     *
     * @param target
     * @param isPlayer
     * @return the clan's tag
     */
    public String getClan(String target, boolean isPlayer) {
        if (hasSimpleClans()) {
            if (isPlayer) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(target);
                net.sacredlabyrinth.phaed.simpleclans.ClanPlayer cp = simpleClans.getClanManager().getClanPlayer(player);

                if (cp != null) {
                    return cp.getTag();
                }
            } else {
               net.sacredlabyrinth.phaed.simpleclans.Clan c = simpleClans.getClanManager().getClan(target);
               if (c != null) {
                   return c.getTag();
               }
            }
        }

        return null;
    }

    /**
     * Gets a clan by a player name.
     *
     * @param target
     * @return the clan's tag
     */
    @Deprecated
    public String getClan(String target) {
        return getClan(target, true);
    }

}
