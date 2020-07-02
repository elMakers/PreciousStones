package net.sacredlabyrinth.Phaed.PreciousStones.managers;

import net.sacredlabyrinth.Phaed.PreciousStones.PreciousStones;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldSettings;
import net.sacredlabyrinth.Phaed.PreciousStones.helpers.ChatHelper;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author phaed
 */
public class LimitManager {
    private PreciousStones plugin;

    /**
     *
     */
    public LimitManager() {
        plugin = PreciousStones.getInstance();
    }

    /**
     * Whether the player has reached the placing limit for a field
     *
     * @param player
     * @param fs     the field settings of the field you need to get the limit of
     * @return
     */
    public boolean reachedLimit(Player player, FieldSettings fs) {


        List<Integer> limits = fs.getLimits();

        if (limits.isEmpty() && !plugin.getSettingsManager().isUsePermissionBasedLimits()) {
            return false;
        }

        if (plugin.getPermissionsManager().has(player, "preciousstones.bypass.limits")) {
            return false;
        }

        if (plugin.getPermissionsManager().has(player, "preciousstones.bypass.limit." + fs.getTitle().replace(" ", "_").toLowerCase())) {
            return false;
        }

        int limit = getLimit(player, fs);
        int count = plugin.getForceFieldManager().getFieldCount(player.getName(), fs.getTypeEntry()) +
                plugin.getForceFieldManager().getRentedFieldCount(player.getName(), fs.getTypeEntry());

        if (limit == -1) {
            return false;
        }

        if (limit == 0) {
            ChatHelper.send(player, "limitsCannotPlace", fs.getTitle());
            return true;
        }

        if (count >= limit) {
            ChatHelper.send(player, "limitsReached", fs.getTitle(), limit);
            return true;
        }

        int totalCount = plugin.getForceFieldManager().getTotalFieldCount(player.getName());

        if (totalCount >= plugin.getSettingsManager().getGlobalFieldLimit()) {
            ChatHelper.send(player, "limitsReachedGlobal", limit);
            return true;
        }

        return false;
    }

    /**
     * Gets the maximum amount of fields a player can place
     *
     * @param player
     * @param fs     the field settings of the field you need to get the limit of
     * @return the limit, -1 if no limit
     */
    public int getLimit(Player player, FieldSettings fs) {
        //Check if config flag usePermissionBasedLimits set to false
        if (!plugin.getSettingsManager().isUsePermissionBasedLimits()) {
            List<Integer> limits = fs.getLimits();

            if (limits.isEmpty()) {
                return -1;
            }

            List<Integer> playersLimits = new ArrayList<>();

            // get all the counts for all limits the player has

            for (int i = limits.size() - 1; i >= 0; i--) {
                if (plugin.getPermissionsManager().has(player, "preciousstones.limit" + (i + 1))) {
                    playersLimits.add(limits.get(i));
                }
            }

            // return the highest one

            if (!playersLimits.isEmpty()) {
                return Collections.max(playersLimits);
            }

            return -1;

        } else if (plugin.getSettingsManager().isUsePermissionBasedLimits()) {

            //If config flag usePermissionBasedLimits set to true!
            /**
             * Player must have preciousstones.limit.field_title
             * where field_title is the name of a configured field, in lowercase, with spaces replaced with underscores.
             * if not, then there will be no limit
             */


            for (int i = fs.getMaxPerPlayer(); i >= 0; i--) {
                if (plugin.getPermissionsManager().has(player, String.format("preciousstones.limit.%s.%d", fs.getTitle().replace(" ", "_").toLowerCase(), i))) {
                        return i;
                    }
                }

            }


        return -1;
    }
}
