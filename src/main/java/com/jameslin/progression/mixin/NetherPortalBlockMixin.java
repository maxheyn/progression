package com.jameslin.progression.mixin;

import com.jameslin.progression.ProgressionConfig;
import net.minecraft.block.Block;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.io.FileNotFoundException;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockMixin extends Block {

    /* Generic constructor */
    public NetherPortalBlockMixin(Settings settings) {
        super(settings);
    }

    /* Redirect the onEntityCollision() method from the EndPortalBlock class to our custom method below. */
    @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setInNetherPortal(Lnet/minecraft/util/math/BlockPos;)V"))
    private void portalControl(Entity entity, BlockPos pos) {
        /* Check to see if the dimension is enabled or disabled */
        boolean dimensionIsDisabled = true;
        try { dimensionIsDisabled = ProgressionConfig.getBoolValueFromEntry("DisableNetherDimension"); }
        catch(FileNotFoundException e) { e.printStackTrace(); }

        if (dimensionIsDisabled) {
            /* If the dimension is disabled... */
            if (entity instanceof PlayerEntity) {
                try {
                    /* Create and send the message to send to players if the dimension is disabled. */
                    Text playerMsg = new LiteralText(ProgressionConfig.getStringValueFromEntry("NetherDisabledMessage"))
                            .formatted(Formatting.RED, Formatting.BOLD);
                    ((PlayerEntity) entity).sendMessage(playerMsg, true);
                }
                catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            /* If the dimension is enabled... */
            entity.setInNetherPortal(pos);
        }
    }
}
