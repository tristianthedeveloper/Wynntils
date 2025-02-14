/*
 *  * Copyright © Wynntils - 2018 - 2022.
 */

package com.wynntils.modules.utilities.managers;

import java.util.HashMap;
import java.util.Map;

import com.wynntils.webapi.WebManager;
import com.wynntils.webapi.profiles.ServerProfile;

public class ServerListManager {

    private static Map<String, ServerProfile> availableServers = new HashMap<>();

    public static synchronized void updateServers() {
        WebManager.getServerList((list) -> availableServers = list);
    }

    public static ServerProfile getServer(String id) {
        return availableServers.get(id);
    }

    public static String getUptime(String id) {
        if (!availableServers.containsKey(id)) return "Latest";
        else return availableServers.get(id).getUptime();
    }

    public static String getUptimeHours(String id) {
        if (!availableServers.containsKey(id)) return "-";
        long milliseconds = System.currentTimeMillis() - availableServers.get(id).getFirstSeen();
        return String.valueOf((milliseconds / (1000*60*60)) % 24);
    }

    public static String getUptimeMinutes(String id) {
        if (!availableServers.containsKey(id)) return "-";
        long milliseconds = System.currentTimeMillis() - availableServers.get(id).getFirstSeen();
        return String.valueOf(milliseconds / (1000*60) % 60);
    }

}
