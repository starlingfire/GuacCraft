/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Quark Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Quark
 *
 * Quark is Open Source and distributed under the
 * CC-BY-NC-SA 3.0 License: https://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB
 *
 * File Created @ [20/06/2016, 11:48:13 (GMT)]
 */
package com.starlingfire.guac.block;

import com.starlingfire.guac.GuacCraft;
import com.starlingfire.guac.tile.TileVendingMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.arl.block.BlockMod;

import javax.annotation.Nullable;

public class BlockVendingMachine extends BlockMod implements IGuacBlock {

    /*
        protected static final AxisAlignedBB SOUTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.1875D);
        protected static final AxisAlignedBB NORTH_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.8125D, 1.0D, 1.0D, 1.0D);
        protected static final AxisAlignedBB WEST_AABB = new AxisAlignedBB(0.8125D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        protected static final AxisAlignedBB EAST_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.1875D, 1.0D, 1.0D);
    */

    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool BASE = PropertyBool.create("base");

    protected static final AxisAlignedBB VENDING_MACHINE_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);

    public BlockVendingMachine() {
        super("vending_machine_block", Material.IRON);
        setHardness(5.0F);
        setResistance(10.0F);
        setSoundType(SoundType.METAL);
        setCreativeTab(CreativeTabs.DECORATIONS);
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, FACING, BASE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int meta = state.getValue(FACING).getIndex();
        if (state.getValue(BASE)) {
            meta |= 8;
        }
        return meta;
    }

    @Override
    @SuppressWarnings("deprecation")
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getFront(meta & 7);
        if (facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH;
        }
        boolean isBase = (meta & 8) > 0;
        return getDefaultState().withProperty(FACING, facing).withProperty(BASE, isBase);
    }

    /* Not used - can remove

        @Nullable
        public TileEntity createNewTileEntity(World world, int metadata) {
            return new TileVendingMachine(!getStateFromMeta(metadata).getValue(BASE));
        }
    */

    @SuppressWarnings("deprecation")
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return VENDING_MACHINE_AABB;
    }

    @Override
    @SuppressWarnings("deprecation")
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return VENDING_MACHINE_AABB;
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        Block blockBelow = world.getBlockState(pos.down()).getBlock();
        //if (blockBelow == this) {
        if (blockBelow == this.getDefaultState().withProperty(BASE, true)){
            return false;
        }
        Block blockAbove = world.getBlockState(pos.up(2)).getBlock();
        return blockAbove != this && super.canPlaceBlockAt(world, pos) && world.getBlockState(pos.up()).getBlock().isReplaceable(world, pos.up());
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        world.setBlockState(pos.up(), this.getDefaultState().withProperty(BASE, false).withProperty(FACING, state.getValue(FACING)), 2);
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        EnumFacing facing = EnumFacing.getDirectionFromEntityLiving(pos, placer).getOpposite();
        if(facing.getAxis() == EnumFacing.Axis.Y) {
            facing = EnumFacing.NORTH;
        }
        return getDefaultState().withProperty(FACING, facing).withProperty(BASE, true);
    }


    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        super.breakBlock(world, pos, state);
        if (world.getBlockState(pos.up()).getBlock() == this) {
            world.setBlockToAir(pos.up());
        } else if (world.getBlockState(pos.down()).getBlock() == this) {
            world.setBlockToAir(pos.down());
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState iBlockState) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState iBlockState) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote)
            playerIn.sendMessage(new TextComponentString("You used the vending machine!"));

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
        return EnumBlockRenderType.MODEL;
    }

    @Nullable
    public TileVendingMachine getTileVendingMachine(World world, BlockPos pos) {
        TileVendingMachine tileVendingMachine = (TileVendingMachine) world.getTileEntity(pos);
        return tileVendingMachine != null ? tileVendingMachine.getParent() : null;
    }
}

