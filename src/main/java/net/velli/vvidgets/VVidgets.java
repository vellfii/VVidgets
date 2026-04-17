package net.velli.vvidgets;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.velli.vvidgets.command.DebugScreenCmd;
import net.velli.vvidgets.screen.ScreenHandler;

public class VVidgets implements ClientModInitializer {

    public static final MinecraftClient MC = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        DebugScreenCmd.init();
        ScreenHandler.init();
    }
}
