package net.velli.vvidgets.command;

import com.mojang.brigadier.Command;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;
import net.velli.vvidgets.VVidgets;
import net.velli.vvidgets.screen.DebugScreen;
import net.velli.vvidgets.screen.ScreenHandler;

public class DebugScreenCmd {
    public static void init() {
        registerCommand(context -> {
            ScreenHandler.openScreen(new DebugScreen(Text.literal("debug")));
            return 1;
        });
    }


    private static void registerCommand(Command<FabricClientCommandSource> command) {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("debugscreen").executes(command));
        });
    }
}
