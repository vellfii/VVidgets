package net.velli.vvidgets.widgets.interfaces;

import net.velli.vvidgets.widgets.individual.Widget;

public interface ClickableWidget {
    void onClick(int mouseX, int mouseY);
    void onRelease(int mouseX, int mouseY);

    interface ClickProcessor {
        /**
        Runs on ANY press, not just ones where the widget is hovered.
         */
        void onClick(Widget<?> widget, int mouseX, int mouseY);
        /**
         Runs on ANY release, not just ones where the widget is hovered.
         */
        void onRelease(Widget<?> widget, int mouseX, int mouseY);
    }
}
