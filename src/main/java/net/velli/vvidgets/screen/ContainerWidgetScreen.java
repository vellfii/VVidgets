package net.velli.vvidgets.screen;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.velli.vvidgets.widgets.individual.Widget;
import net.velli.vvidgets.widgets.interfaces.WidgetContainer;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerWidgetScreen extends Screen implements WidgetContainer<ContainerWidgetScreen> {
    private final List<Widget<?>> widgets = new ArrayList<>();

    protected ContainerWidgetScreen(Text title) {
        super(title);
    }

    @Override
    public int x() {
        return 0;
    }

    @Override
    public int y() {
        return 0;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public ContainerWidgetScreen getSelf() {
        return this;
    }

    @Override
    public boolean hovered() {
        return true;
    }

    @Override
    public List<Widget<?>> getWidgets() {
        return widgets;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        hoverChildren(mouseX, mouseY, true);
        renderChildren(context);
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        onClick((int) click.x(), (int) click.y());
        return super.mouseClicked(click, doubled);
    }

    @Override
    public boolean mouseReleased(Click click) {
        onRelease((int) click.x(), (int) click.y());
        return super.mouseReleased(click);
    }
}
