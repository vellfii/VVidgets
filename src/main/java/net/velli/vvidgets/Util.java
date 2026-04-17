package net.velli.vvidgets.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.*;

import java.util.ArrayList;
import java.util.List;

public class Util {
    private static List<Text> newlineResult = new ArrayList<>();
    private static MutableText newlineCurrentStrand = Text.empty();

    public static List<OrderedText> wrapLines(StringVisitable text, int width) {
        return MinecraftClient.getInstance().textRenderer.wrapLines(text, width);
    }

    public static List<Text> splitTextNewline(Text text) {
        if (!text.copyContentOnly().getString().isEmpty()) text = Text.empty().append(text);
        newlineResult = new ArrayList<>();
        List<Text> siblings = new ArrayList<>(text.getSiblings());
        newlineCurrentStrand = Text.empty();
        for (Text sibling : siblings) {
            newlineWrapLogic(sibling, sibling.copyContentOnly().getString());
            for (Text siblingin : sibling.getSiblings()) {
                newlineWrapLogic(siblingin, siblingin.getString());
            }
        }
        newlineResult.add(newlineCurrentStrand);
        return newlineResult;
    }

    private static void newlineWrapLogic(Text text, String partsString) {
        String[] parts = partsString.split("\n", -1);
        Style style = text.getStyle();
        List<String> partsList = new ArrayList<>(List.of(parts));
        for (String part : partsList) {
            if (partsList.indexOf(part) != 0) {
                newlineResult.add(newlineCurrentStrand);
                newlineCurrentStrand = Text.empty();
            }
            newlineCurrentStrand.append(Text.literal(part).setStyle(style));
        }
    }

    public static float lerp(float a, float b, float t) {
        if (a == b) return b;
        t = Math.max(t, 0);
        t = Math.min(t, 1);
        return a + t * (b - a);
    }

    public static Text textFromOrdered(OrderedText orderedText) {
        StyleCharacterVisitor styles = new StyleCharacterVisitor();
        orderedText.accept(styles);
        if (styles.oldStyle != null) {
            styles.text.append(Text.literal(styles.sb.toString()).setStyle(styles.oldStyle));
        } else {
            styles.text.append(Text.literal(styles.sb.toString()));
        }
        return styles.text;
    }

    private static class StyleCharacterVisitor implements CharacterVisitor {

        public Style oldStyle;
        public StringBuilder sb = new StringBuilder();
        public MutableText text = Text.empty();

        @Override
        public boolean accept(int index, Style style, int codePoint) {
            String s = new String(Character.toChars(codePoint));
            if (oldStyle == style) {
                sb.append(s);
            } else if (oldStyle != null) {
                text.append(Text.literal(sb.toString()).setStyle(oldStyle));
                sb.setLength(0);
                sb.append(s);
            } else {
                text.append(Text.literal(s).setStyle(style));
            }
            oldStyle = style;

            return true;
        }
    }
}
