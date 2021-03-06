package dev.latvian.kubejs.integration.gamestages;

import dev.latvian.kubejs.player.AttachPlayerDataEvent;
import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * @author LatvianModder
 */
public class GameStagesIntegration
{
	public static void init()
	{
		MinecraftForge.EVENT_BUS.register(GameStagesIntegration.class);
	}

	// Just ignore when it says that it is not an Event
	@SubscribeEvent
	public static void attachPlayerData(AttachPlayerDataEvent event)
	{
		event.add("gamestages", new GameStagesPlayerData(event.getParent()));
	}

	@SubscribeEvent
	public static void gameStageAdded(GameStageEvent.Added e)
	{
		new GameStageEventJS(e).post("gamestage.added", e.getStageName());
	}

	@SubscribeEvent
	public static void gameStageRemoved(GameStageEvent.Removed e)
	{
		new GameStageEventJS(e).post("gamestage.removed", e.getStageName());
	}
}