package net.velli.vvidgets.widgets.container;

import net.velli.vvidgets.widgets.individual.Widget;
import net.velli.vvidgets.widgets.interfaces.WidgetContainer;

import java.util.ArrayList;
import java.util.List;

public abstract class ContainerWidget extends Widget<ContainerWidget> implements WidgetContainer<ContainerWidget> {
    public List<Widget<?>> widgets = new ArrayList<>();

    @Override
    public List<Widget<?>> getWidgets() {
        return widgets;
    }
}
