package net.velli.vvidgets.widgets.individual;

import net.minecraft.client.gui.DrawContext;
import net.velli.vvidgets.widgets.interfaces.ClickableWidget;

public class ButtonWidget extends Widget<ButtonWidget> implements ClickableWidget {
    @Override
    public ButtonWidget getSelf() {
        return this;
    }

    @Override
    protected void onRender(DrawContext context, float delta) {
        if (hovered()) {
            context.fill(0, 0, width(), height(), 0x99222222);
            context.drawStrokedRectangle(0, 0, width(), height(), 0xFFFFFFFF);
        } else {
            context.fill(0, 0, width(), height(), 0x99000000);
        }
    }

    @Override
    protected void onHover(int mouseX, int mouseY, boolean active) {

    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        if (hovered()) {
            System.out.println("clicked on button");
        } else {
            System.out.println("clicked off button");
        }
    }

    @Override
    public void onRelease(int mouseX, int mouseY) {
        if (hovered()) {
            System.out.println("released on button");
        } else {
            System.out.println("released off button");
        }
    }
}
