/*
 *  * Copyright © Wynntils - 2018 - 2022.
 */

package com.wynntils.modules.utilities.managers;

import java.util.concurrent.TimeUnit;

import com.wynntils.core.utils.objects.TimedSet;

public class AreaDPSManager {

    private static final TimedSet<Integer> DAMAGE_SET = new TimedSet<>(1, TimeUnit.SECONDS);

    public static void registerDamage(Integer amount) {
        DAMAGE_SET.put(amount);
    }

    public static int getCurrentDPS() {
        if (DAMAGE_SET.isEmpty()) return 0;
        DAMAGE_SET.releaseEntries();

        int sum = 0;
        for (Integer integer : DAMAGE_SET) {
            sum += integer;
        }

        return sum;
    }

}
