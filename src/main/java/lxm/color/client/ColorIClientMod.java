package lxm.color.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ColorIClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 客户端初始化逻辑
        System.out.println("Color I Client Mod initialized!");
    }
}