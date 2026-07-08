package com.example.examplemod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

@Mod(value = DoubleRegistrationError.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = DoubleRegistrationError.MODID, value = Dist.CLIENT)
public class DoubleRegistrationErrorClient {
    public static final Lazy<KeyMapping> CRASH_MAPPING = Lazy.of(() -> new KeyMapping(
            "key." + DoubleRegistrationError.MODID + ".crash",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "key.categories.misc"
    ));

    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(CRASH_MAPPING.get());
    }

    @SubscribeEvent
    public static void onClientSetup(ClientTickEvent.Post event) {
        if (CRASH_MAPPING.get().consumeClick()) {
            for (Item item : BuiltInRegistries.ITEM) {
                DoubleRegistrationError.LOGGER.info("This should crash {}", item.toString());
            }
        }
    }
}
