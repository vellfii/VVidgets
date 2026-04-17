package net.velli.vvidgets.widgets.individual;

import net.minecraft.client.gui.DrawContext;
import net.velli.vvidgets.Util;
import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.interfaces.WidgetContainer;
import org.joml.Vector2i;

public abstract class Widget<T extends Widget<T>> {

    private final WidgetData data = new WidgetData();

    public abstract T getSelf();
    public WidgetContainer<?> getParent() { return data.parent; }

    public int x() { return Math.round(data.x); }
    public int y() { return Math.round(data.y); }
    public int width() { return Math.round(data.width); }
    public int height() { return Math.round(data.height); }
    public float animSpeed() { return data.animSpeed; }
    public Alignment alignment() { return data.alignment; }
    public boolean hovered() { return data.hovered; }

    public Vector2i alignmentOffset() {
        if (getParent() == null) return new Vector2i(0, 0);
        Alignment alignment = alignment();
        int x = 0, y = 0;
        if (!alignment.id.contains("left")) {
            if (alignment.id.contains("right")) x = getParent().width() - width();
            else x = getParent().width() / 2 - width() / 2;
        }
        if (!alignment.id.contains("top")) {
            if (alignment.id.contains("bottom")) y = getParent().height() - height();
            else y = getParent().height() / 2 - height() / 2;
        }
        return new Vector2i(x, y);
    }

    public T withX(int x, boolean snap) {
        data.targetX = x;
        if (snap) {
            data.x = x;
        }
        return getSelf();

    }
    public T withY(int y, boolean snap) {
        data.targetY = y;
        if (snap) {
            data.y = y;
        }
        return getSelf();
    }
    public T withPosition(int x, int y, boolean snap) {
        withX(x, snap);
        withY(y, snap);
        return getSelf();
    }

    public T withWidth(int width, boolean snap) {
        data.targetWidth = width;
        if (snap) {
            data.width = width;
        }
        return getSelf();
    }
    public T withHeight(int height, boolean snap) {
        data.targetHeight = height;
        if (snap) {
            data.height = height;
        }
        return getSelf();
    }
    public T withDimensions(int width, int height, boolean snap) {
        withWidth(width, snap);
        withHeight(height, snap);
        return getSelf();
    }

    public T withAnimSpeed(float animSpeed) {
        data.animSpeed = animSpeed;
        return getSelf();
    }

    public T withAlignment(Alignment alignment) {
        data.alignment = alignment;
        return getSelf();
    }

    public void setParent(WidgetContainer<?> parent) {
        data.parent = parent;
    }

    private long lastRender = System.currentTimeMillis();

    public void render(DrawContext context) {
        float delta = (float) (System.currentTimeMillis() - lastRender) / 1000;
        data.x = Util.lerp(data.x, data.targetX, data.animSpeed * 8 * delta);
        data.y = Util.lerp(data.y, data.targetY, data.animSpeed * 8 * delta);
        data.width = Util.lerp(data.width, data.targetWidth, data.animSpeed * 8 * delta);
        data.height = Util.lerp(data.height, data.targetHeight, data.animSpeed * 8 * delta);
        onRender(context, delta);
        lastRender = System.currentTimeMillis();
    }

    protected abstract void onRender(DrawContext context, float delta);

    public void hover(int mouseX, int mouseY, boolean active) {
        data.hovered = data.isHovered(mouseX, mouseY) && active;
        onHover(mouseX, mouseY, active);
    }

    protected abstract void onHover(int mouseX, int mouseY, boolean active);
}
