package net.sacredlabyrinth.Phaed.PreciousStones.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import net.sacredlabyrinth.Phaed.PreciousStones.api.events.FieldPreCreationEvent;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldSettings;

/**
 * @author xtomyserrax
 */

public class FieldPreCreationEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	private FieldSettings fieldSettings;

	private boolean isCancelled = false;

	public FieldPreCreationEvent(Player player, FieldSettings fieldSettings) {
		super(player); // Player who placed the field isnt always the owner. (Check flag NO_OWNER)
		this.fieldSettings = fieldSettings; // You wont get a Field because it hasnt been created yet.
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public FieldSettings getFieldSettings() {
		return this.fieldSettings;
	}

	public void setFieldSettings(FieldSettings fieldSettings) {
		this.fieldSettings = fieldSettings;
	}

	public boolean isCancelled() {
		return this.isCancelled;
	}

	public void setCancelled(boolean paramBoolean) {
		this.isCancelled = paramBoolean;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
}