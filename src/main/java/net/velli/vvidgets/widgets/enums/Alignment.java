package net.velli.vvidgets.widgets.enums;

public enum Alignment {
    TOPLEFT("topleft"), TOP("top"), TOPRIGHT("topright"),
    LEFT("left"), CENTER(""), RIGHT("right"),
    BOTLEFT("bottomleft"), BOTTOM("bottom"), BOTRIGHT("bottomright");

    public final String id;

    Alignment(String id) {
        this.id = id;
    }

}
