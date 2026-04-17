package net.velli.vvidgets.widgets.interfaces;

import net.minecraft.client.gui.DrawContext;
import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.individual.Widget;
import org.joml.Vector2i;

import java.util.List;

public interface WidgetContainer<T extends WidgetContainer<T>> extends ClickableWidget {

    int x();
    int y();
    int width();
    int height();
    T getSelf();
    boolean hovered();

    List<Widget<?>> getWidgets();

    default T addWidgets(Widget<?>... widgets) {
        for (Widget<?> widget : widgets) {
            widget.setParent(getSelf());
        }
        getWidgets().addAll(List.of(widgets));
        return getSelf();
    }
    default T removeWidgets(Widget<?>... widgets) {
        for (Widget<?> widget : widgets) {
            widget.setParent(null);
            getWidgets().remove(widget);
        }
        return getSelf();
    }
    default T removeWidget(int index) {
        getWidgets().get(index).setParent(null);
        getWidgets().remove(index);
        return getSelf();
    }

    default void renderChildren(DrawContext context) {
        context.enableScissor(0, 0, width(), height());
        for (Widget<?> widget : getWidgets()) {
            renderWidget(widget, context);
        }
        context.disableScissor();
    }

    default void renderWidget(Widget<?> widget, DrawContext context) {
        Vector2i offset = widget.alignmentOffset();
        context.getMatrices().pushMatrix();
        context.getMatrices().translate(widget.x() + offset.x(), widget.y() + offset.y());
        widget.render(context);
        context.getMatrices().popMatrix();
    }

    default void hoverChildren(int mouseX, int mouseY, boolean active) {
        for (Widget<?> widget : getWidgets()) {
            Vector2i offset = widget.alignmentOffset();
            widget.hover(mouseX - offset.x() - x(),
                    mouseY - offset.y() - y(),
                    active && hovered());
        }
    }

    default void onClick(int mouseX, int mouseY) {
        clickChildren(mouseX, mouseY);
    }

    default void clickChildren(int mouseX, int mouseY) {
        for (Widget<?> widget : getWidgets()) {
            Vector2i offset = widget.alignmentOffset();
            if (widget instanceof ClickableWidget cw) {
                cw.onClick(
                        mouseX - offset.x() - x(),
                        mouseY - offset.y() - y()
                );
            }
        }
    }

    default void onRelease(int mouseX, int mouseY) {
        releaseChildren(mouseX, mouseY);
    }

    default void releaseChildren(int mouseX, int mouseY) {
        for (Widget<?> widget : getWidgets()) {
            Vector2i offset = widget.alignmentOffset();
            if (widget instanceof ClickableWidget cw) {
                cw.onRelease(
                        mouseX - offset.x() - x(),
                        mouseY - offset.y() - y()
                );
            }
        }
    }

}
