package com.example.examplemod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(DoubleRegistrationError.MODID)
public class DoubleRegistrationError {
    public static final String MODID = "doubleregistrationerror";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DoubleRegistrationError(IEventBus modEventBus, ModContainer modContainer) {
        final DeferredRegister.Items items = DeferredRegister.createItems(MODID);
        final DeferredRegister.Items itemsDupe = DeferredRegister.createItems(MODID);
        items.registerSimpleItem("example_item");
        itemsDupe.registerSimpleItem("example_item");
        items.register(modEventBus);
        itemsDupe.register(modEventBus);
    }
}
