package net.velli.vvidgets.widgets.individual;

import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.interfaces.WidgetContainer;

public class WidgetData {
    public WidgetContainer<?> parent;

    public int targetX = 0;
    public int targetY = 0;
    public int targetWidth = 0;
    public int targetHeight = 0;
    public float x = 0;
    public float y = 0;
    public float width = 0;
    public float height = 0;
    public float animSpeed = 1;

    public Alignment alignment = Alignment.TOPLEFT;

    public boolean hovered = false;

    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + width
                && mouseY >= y && mouseY < y + height;
    }
}
