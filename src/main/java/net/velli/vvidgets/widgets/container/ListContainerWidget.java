package net.velli.vvidgets.widgets.container;

import net.minecraft.client.gui.DrawContext;
import net.velli.vvidgets.widgets.Widgets;
import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.individual.Widget;

import java.util.ArrayList;
import java.util.List;

public class ListContainerWidget extends ContainerWidget {

    private int color = 0x99000000;

    public List<BasicContainerWidget> columns = new ArrayList<>();

    int verticalPadding = 5;
    int horizontalPadding = 5;
    int columnPadding = 5;
    int itemPadding = 2;

    public ListContainerWidget() {
        // addColumns(1);
    }

    @Override
    public ListContainerWidget getSelf() {
        return this;
    }

    public ListContainerWidget withColor(int color) {
        this.color = color;
        return getSelf();
    }

    public ListContainerWidget withPadding(int verticalPadding, int horizontalPadding, int columnPadding, int itemPadding) {
        this.verticalPadding = verticalPadding;
        this.horizontalPadding = horizontalPadding;
        this.columnPadding = columnPadding;
        this.itemPadding = itemPadding;
        return getSelf();
    }

    public ListContainerWidget addColumns(int amount) {
        for (int i = 0; i < amount; ++i) {
            BasicContainerWidget newColumn = (BasicContainerWidget) Widgets.create(BasicContainerWidget::new);
            newColumn.withColor(0x00000000);
            super.addWidgets(newColumn);
            columns.add(newColumn);
        }
        return getSelf();
    }

    public void removeColumns(int amount) {
        for (int i = 0; i < amount; ++i) {
            BasicContainerWidget remove = columns.getLast();
            columns.remove(remove);
            removeWidgets(remove);
        }
    }

    @Override
    public ListContainerWidget addWidgets(Widget<?>... widgets) {
        columns.getLast().addWidgets(widgets);
        return getSelf();
    }

    @Override
    public ListContainerWidget removeWidgets(Widget<?>... widgets) {
        for (BasicContainerWidget column : columns) {
            column.removeWidgets(widgets);
        }
        return getSelf();
    }

    @Override
    public ListContainerWidget removeWidget(int index) {
        columns.getLast().removeWidget(index);
        return getSelf();
    }

    @Override
    public List<Widget<?>> getWidgets() {
        return super.getWidgets();
    }

    @Override
    protected void onRender(DrawContext context, float delta) {
        context.fill(0, 0, width(), height(), color);
        renderChildren(context);
    }

    private int fullHeight = 0;

    private void sortWidgets() {
        fullHeight = 0;
        withWidth(horizontalPadding, true);
        for (BasicContainerWidget column : columns) {
            column.withPosition(width(), verticalPadding, true);
            column.withHeight(0, true);

            for (Widget<?> widget : column.getWidgets()) {
                widget.withAlignment(Alignment.TOP);

                widget.withPosition(0, column.height(), true);

                // Stretches column to fit max widget len + adds current height of widget with item padding.
                column.withDimensions(
                        Math.max(widget.width(), column.width()),
                        column.height() + widget.height() + itemPadding,
                        true
                );
            }
            // Removes extra padding from column + adds width of current column and padding.
            column.withHeight(column.height() - itemPadding, true);
            withWidth(width() + column.width() + columnPadding, true);

            // Stretch fullHeight to be max column height + padding.
            fullHeight = Math.max(fullHeight, column.height() + verticalPadding * 2);
        }
    }

    @Override
    protected void onHover(int mouseX, int mouseY, boolean active) {
        hoverChildren(mouseX, mouseY, active);
        sortWidgets();
    }
}
