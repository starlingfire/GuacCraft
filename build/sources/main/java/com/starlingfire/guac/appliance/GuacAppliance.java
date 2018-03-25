package com.starlingfire.guac.appliance;

import com.starlingfire.guac.feature.VendingMachine;
import com.starlingfire.guac.module.Module;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class GuacAppliance extends Module {

    @Override
    public void addFeatures() {
        registerFeature(new VendingMachine());

    }

    @Override
    public ItemStack getIconStack() {
        return new ItemStack(Blocks.RED_FLOWER);
    }

}
