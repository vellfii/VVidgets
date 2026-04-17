package net.velli.vvidgets.widgets.container;

import net.minecraft.client.gui.DrawContext;

public class BasicContainerWidget extends ContainerWidget {

    private int color = 0x99000000;

    @Override
    public BasicContainerWidget getSelf() {
        return this;
    }

    public BasicContainerWidget withColor(int color) {
        this.color = color;
        return getSelf();
    }

    @Override
    protected void onRender(DrawContext context, float delta) {
        context.fill(0, 0, width(), height(), color);
        renderChildren(context);
    }

    @Override
    public void onHover(int mouseX, int mouseY, boolean active) {
        hoverChildren(mouseX, mouseY, active);
    }
}
