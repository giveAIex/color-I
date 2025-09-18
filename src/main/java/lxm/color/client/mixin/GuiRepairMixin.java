package lxm.color.client.mixin;

import net.minecraft.client.gui.screen.ingame.RepairScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RepairScreen.class)
public abstract class GuiRepairMixin {
    @Shadow
    private TextFieldWidget nameField;

    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        // 设置文本框最大长度为100，允许更多字符
        nameField.setMaxLength(100);
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        // 检测Shift+Enter组合键
        if (nameField.isFocused() && keyCode == 257 && (modifiers & 1) != 0) { // 257是Enter键，1是Shift键
            String text = nameField.getText();
            int cursorPos = nameField.getCursorPos();
            
            // 在光标位置插入换行符
            String newText = text.substring(0, cursorPos) + "\n" + text.substring(cursorPos);
            nameField.setText(newText);
            nameField.setCursorPos(cursorPos + 1);
            
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "onRenamed", at = @At("HEAD"), cancellable = true)
    private void onRenamed(CallbackInfo ci) {
        String text = nameField.getText();
        if (text != null && !text.isEmpty()) {
            // 客户端直接处理文本，不进行过滤
            // 这里可以添加任何客户端特定的文本处理逻辑
        }
    }
}