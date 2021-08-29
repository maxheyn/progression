package com.jameslin.progression.mixin;

import com.jameslin.progression.ProgressionConfig;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.FileNotFoundException;

@Mixin(EndPortalBlock.class)
public abstract class EndPortalBlockMixin extends BlockWithEntity {

    /* Generic constructor */
    protected EndPortalBlockMixin(Settings settings)  {
        super(settings);
    }

    /* Redirect the onEntityCollision() method from the EndPortalBlock class to our custom method below. */
    @Redirect(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;moveToWorld(Lnet/minecraft/server/world/ServerWorld;)Lnet/minecraft/entity/Entity;"))
    private Entity portalControl(Entity entity, ServerWorld serverWorld) {
        /* Check to see if the dimension is enabled or disabled */
        boolean dimensionIsDisabled = true;
        try { dimensionIsDisabled = ProgressionConfig.getBoolValueFromEntry("DisableEndDimension"); }
        catch(FileNotFoundException e) { e.printStackTrace(); }

        if (dimensionIsDisabled) {
            /* If the dimension is disabled... */
            if (entity instanceof PlayerEntity) {
                try {
                    /* Create and send the message to send to players if the dimension is disabled. */
                    Text playerMsg = new LiteralText(ProgressionConfig.getStringValueFromEntry("EndDisabledMessage"))
                            .formatted(Formatting.RED, Formatting.BOLD);
                    ((PlayerEntity) entity).sendMessage(playerMsg, true);
                }
                catch(FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            /* If the dimension is enabled... */
            entity.moveToWorld(serverWorld);
        }
        return entity;
    }
}
