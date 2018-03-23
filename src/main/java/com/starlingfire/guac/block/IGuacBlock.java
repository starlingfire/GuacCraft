package com.starlingfire.guac.block;

import com.starlingfire.guac.GuacCraft;
import vazkii.arl.interf.IModBlock;

public interface IGuacBlock extends IModBlock{

    @Override
    default String getModNamespace() {
        return GuacCraft.MODID;
    }
}
