package com.starlingfire.guac.feature;

import com.starlingfire.guac.block.BlockVendingMachine;
import com.starlingfire.guac.module.Feature;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
//import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
//import net.minecraftforge.fml.common.registry.GameRegistry;
//import net.minecraftforge.oredict.OreDictionary;
import vazkii.arl.recipe.RecipeHandler;
import vazkii.arl.util.ProxyRegistry;

public class VendingMachine extends Feature{

    public static Block vendingMachine_block;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        vendingMachine_block = new BlockVendingMachine();

        RecipeHandler.addOreDictRecipe(ProxyRegistry.newStack(vendingMachine_block),
                "ISI", "IRB", "IDI",
                'I', ProxyRegistry.newStack(Items.IRON_INGOT, 1),
                'S', ProxyRegistry.newStack(Items.SIGN, 1),
                'R', ProxyRegistry.newStack(Items.REDSTONE, 1),
                'B', ProxyRegistry.newStack(Blocks.STONE_BUTTON, 1),
                'D', ProxyRegistry.newStack(Blocks.DISPENSER, 1)
                );
    }

    /* Don't need this since the vending machine isn't an ore
       Need to add [import net.minecraftforge.fml.common.event.FMLInitializationEvent;] if re-adding

        @Override
        public void init(FMLInitializationEvent event) {
            OreDictionary.registerOre("blockVendingMachine", vendingMachine_block);
        }
    */

    @Override
    public boolean requiresMinecraftRestartToEnable() {
        return true;
    }

    /* Don't need this since there are no incompatible mods (yet?)

        @Override
        public String[] getIncompatibleMods() {
            return new String[] { "actuallyadditions", "mekanism" };
        }
    */
}
