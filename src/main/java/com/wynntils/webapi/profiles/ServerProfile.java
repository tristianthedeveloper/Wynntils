/*
 *  * Copyright © Wynntils - 2018 - 2022.
 */

package com.wynntils.webapi.profiles;

import java.util.Set;

import com.wynntils.core.utils.StringUtils;

public class ServerProfile {

    long firstSeen;
    Set<String> players;

    public ServerProfile(long firstSeem, Set<String> players) {
        this.firstSeen = firstSeem; this.players = players;
    }

    public Set<String> getPlayers() {
        return players;
    }

    public long getFirstSeen() {
        return firstSeen;
    }

    public String getUptime() {
        return StringUtils.millisToLongString(System.currentTimeMillis() - firstSeen);
    }

    /**
     * This makes the firstSeem match the user computer time instead of the server time
     *
     * @param serverTime the input server time
     */
    public void matchTime(long serverTime) {
        firstSeen = firstSeen - (System.currentTimeMillis() - serverTime);
    }

}
