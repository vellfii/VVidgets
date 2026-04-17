package net.velli.vvidgets.widgets.container;

import net.minecraft.client.gui.DrawContext;

public class ListContainerWidget extends ContainerWidget {
    @Override
    public ContainerWidget getSelf() {
        return this;
    }

    @Override
    protected void onRender(DrawContext context, float delta) {
        renderChildren(context);
    }

    @Override
    protected void onHover(int mouseX, int mouseY, boolean active) {
        hoverChildren(mouseX, mouseY, active);
    }
}
