/*
 *  * Copyright © Wynntils - 2022.
 */

package com.wynntils.modules.map;

import java.util.Collection;

import org.lwjgl.input.Keyboard;

import com.wynntils.McIf;
import com.wynntils.Reference;
import com.wynntils.core.framework.enums.Priority;
import com.wynntils.core.framework.instances.KeyHolder;
import com.wynntils.core.framework.instances.Module;
import com.wynntils.core.framework.interfaces.annotations.ModuleInfo;
import com.wynntils.core.utils.Utils;
import com.wynntils.modules.map.commands.CommandDetection;
import com.wynntils.modules.map.commands.CommandLocate;
import com.wynntils.modules.map.commands.CommandLootRun;
import com.wynntils.modules.map.configs.MapConfig;
import com.wynntils.modules.map.events.ClientEvents;
import com.wynntils.modules.map.instances.MapProfile;
import com.wynntils.modules.map.managers.LootRunManager;
import com.wynntils.modules.map.overlays.MiniMapOverlay;
import com.wynntils.modules.map.overlays.OverlayEvents;
import com.wynntils.modules.map.overlays.ui.GuildWorldMapUI;
import com.wynntils.modules.map.overlays.ui.MainWorldMapUI;
import com.wynntils.modules.map.overlays.ui.WaypointCreationMenu;
import com.wynntils.modules.map.overlays.ui.WorldMapUI;
import com.wynntils.webapi.WebManager;
import com.wynntils.webapi.WebReader;

import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraftforge.client.settings.KeyConflictContext;

@ModuleInfo(name = "map", displayName = "Map")
public class MapModule extends Module {

    private static MapModule module;
    private MapProfile mainMap;

    private KeyHolder mapKey;
    private KeyHolder guildMapKey;

    @Override
    public void onEnable() {
        module = this;

        WebReader webApi = WebManager.getApiUrls();
        mainMap = new MapProfile(webApi == null ? null : webApi.get("MainMap"), "main-map");
        mainMap.updateMap();

        LootRunManager.setup();

        registerEvents(new ClientEvents());
        registerEvents(new OverlayEvents());

        registerSettings(MapConfig.class);
        registerSettings(MapConfig.Textures.class);
        registerSettings(MapConfig.Waypoints.class);
        registerSettings(MapConfig.WorldMap.class);
        registerSettings(MapConfig.LootRun.class);
        registerSettings(MapConfig.Telemetry.class);

        registerOverlay(new MiniMapOverlay(), Priority.LOWEST);

        registerCommand(new CommandLootRun());
        registerCommand(new CommandLocate());
        registerCommand(new CommandDetection());

        registerKeyBinding("New Waypoint", Keyboard.KEY_B, "Wynntils", KeyConflictContext.IN_GAME, true, () -> {
            if (Reference.onWorld)
                McIf.mc().displayGuiScreen(new WaypointCreationMenu(null));
        });

        mapKey = registerKeyBinding("Open Map", Keyboard.KEY_M, "Wynntils", KeyConflictContext.IN_GAME, true, () -> {
            if (Reference.onWorld) {
                if (WebManager.getApiUrls() == null) {
                    WebManager.tryReloadApiUrls(true);
                } else {
                    openMap(false);
                }
            }
        });

        guildMapKey = registerKeyBinding("Open Guild Map", Keyboard.KEY_L, "Wynntils", KeyConflictContext.IN_GAME, true, () -> {
            if (Reference.onWorld) {
                if (WebManager.getApiUrls() == null) {
                    WebManager.tryReloadApiUrls(true);
                } else {
                    openMap(true);
                }
            }
        });
    }

    private void openMap(boolean guildMap) {
        //Check if in war using scoreboard
        boolean inWar = isPlayerInWar();

        WorldMapUI map;

        if (inWar) {
            if (guildMap)
                map = new GuildWorldMapUI(MapConfig.WorldMap.INSTANCE.mapDefaultX, MapConfig.WorldMap.INSTANCE.mapDefaultZ);
            else
                map = new MainWorldMapUI(MapConfig.WorldMap.INSTANCE.mapDefaultX, MapConfig.WorldMap.INSTANCE.mapDefaultZ);
        }
        else {
            if (guildMap)
                map = new GuildWorldMapUI();
            else
                map = new MainWorldMapUI();
        }

        Utils.displayGuiScreen(map);
    }

    private boolean isPlayerInWar() {
        boolean inWar = false;

        ScoreObjective objective = McIf.world().getScoreboard().getObjectiveInDisplaySlot(1);
        if (objective != null) {
            Collection<Score> objectiveList = objective.getScoreboard().getSortedScores(objective);
            for (Score score : objectiveList) {
                if (score.getPlayerName().equals("§4§lWar:"))
                {
                    inWar = true;
                    break;
                }
            }
        }
        return inWar;
    }

    public static MapModule getModule() {
        return module;
    }

    public MapProfile getMainMap() {
        return mainMap;
    }

    public KeyHolder getMapKey() { return mapKey; }

    public KeyHolder getGuildMapKey() {
        return guildMapKey;
    }

}
