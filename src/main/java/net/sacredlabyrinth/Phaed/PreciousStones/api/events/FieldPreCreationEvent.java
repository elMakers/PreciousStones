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
 * <p>
 * This event is fired right before a Field is created.
 * <p>
 * getPlayer() will return the Player who placed the Field block.
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

	/**
	 * Utility method to get the Settings of the Field that will be created.
	 *
	 * @return FieldSettings
	 */
	public FieldSettings getFieldSettings() {
		return this.fieldSettings;
	}

	/**
	 * Utility method to set the Settings of the Field that will be created.
	 *
	 * @return void
	 */
	public void setFieldSettings(FieldSettings fieldSettings) {
		this.fieldSettings = fieldSettings;
	}
	
	/**
	 * Utility method to get the Block the Field will be.
	 *
	 * @return Block
	 */
	public Block getBlock() {
		return this.block;
	}

	public boolean isCancelled() {
		return this.isCancelled;
	}

	/**
	 * Cancel the creation of the Field.
	 *
	 * @return void
	 */
	public void setCancelled(boolean paramBoolean) {
		this.isCancelled = paramBoolean;
	}

	public HandlerList getHandlers() {
		return handlers;
	}
}
