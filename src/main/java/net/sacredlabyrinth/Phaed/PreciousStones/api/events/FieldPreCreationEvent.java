package net.sacredlabyrinth.Phaed.PreciousStones.api.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

import net.sacredlabyrinth.Phaed.PreciousStones.api.events.FieldPreCreationEvent;
import net.sacredlabyrinth.Phaed.PreciousStones.field.FieldSettings;

/**
 * @author xtomyserrax
 *
 * This event will be called before a field is created so you can cancel it.
 * getPlayer() will return the Player who placed the Field block.
 * getFieldSettings() will return the settings of the Field trying to be created.
 * setFieldSettings(FieldSettings) will let you change the actual Field Settings.
 * getBlock() will return you the Field block.
 * setCancelled(boolean) will let you cancel the event.
 */

public class FieldPreCreationEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	private FieldSettings fieldSettings;
	private Block block;

	private boolean isCancelled = false;

	public FieldPreCreationEvent(Player player, FieldSettings fieldSettings, Block block) {
		super(player);
		this.fieldSettings = fieldSettings;
		this.block = block;
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
	
	public Block getBlock() {
		return this.block;
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
