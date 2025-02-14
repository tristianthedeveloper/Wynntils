/*
 *  * Copyright © Wynntils - 2018 - 2022.
 */

package com.wynntils.modules.utilities.instances;

import static net.minecraft.util.text.TextFormatting.GRAY;
import static net.minecraft.util.text.TextFormatting.GREEN;
import static net.minecraft.util.text.TextFormatting.RED;

import com.wynntils.webapi.profiles.item.IdentificationOrderer;
import com.wynntils.webapi.profiles.item.enums.IdentificationModifier;
import com.wynntils.webapi.profiles.item.objects.IdentificationContainer;

/**
 * Used for holding current identifications
 *
 * Ex: +35% Health Regen ->
 *  currentAmount = 35
 *  modifier = IdentificationModifier.PERCENTAGE
 *
 */
public class IdentificationHolder {

    int currentAmount;
    IdentificationModifier modifier;

    public IdentificationHolder(int currentAmount, IdentificationModifier modifier) {
        this.currentAmount = currentAmount;
        this.modifier = modifier;
    }

    public IdentificationModifier getModifier() {
        return modifier;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void sumAmount(int amount) {
        currentAmount+=amount;
    }

    public String getAsLore(String idName) {
        String name = GRAY + IdentificationContainer.getAsLongName(idName);

        String idAmount;
        if (IdentificationOrderer.INSTANCE.isInverted(idName))
            idAmount = (currentAmount > 0 ? RED + "+" + currentAmount + modifier.getInGame(idName) : GREEN.toString() + currentAmount + modifier.getInGame(idName));
        else
            idAmount = (currentAmount > 0 ? GREEN + "+" + currentAmount + modifier.getInGame(idName) : RED.toString() + currentAmount + modifier.getInGame(idName));

        return name + " " + idAmount;
    }

}
