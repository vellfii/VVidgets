package net.velli.vvidgets.widgets.interfaces;

public interface ClickableWidget {
    void onClick(int mouseX, int mouseY);
    void onRelease(int mouseX, int mouseY);

    interface ClickProcessor {
        void onClick(int mouseX, int mouseY);
        void onRelease(int mouseX, int mouseY);
    }
}
