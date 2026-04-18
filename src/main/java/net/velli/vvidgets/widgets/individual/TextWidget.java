package net.velli.vvidgets.widgets.individual;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.velli.vvidgets.VVidgets;
import net.velli.vvidgets.VVidgetsUtil;
import net.velli.vvidgets.widgets.enums.Alignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextWidget extends Widget<TextWidget> {
    public List<OrderedText> lines = new ArrayList<>();
    public Alignment alignment = Alignment.LEFT;

    @Override
    public void onRender(DrawContext context, float delta) {
        TextRenderer textRenderer = VVidgets.MC.textRenderer;
        int offsetY = 0;
        List<OrderedText> renderLines = new ArrayList<>();
        for (OrderedText line : lines) {
            Text text = VVidgetsUtil.textFromOrdered(line);
            renderLines.addAll(textRenderer.wrapLines(text, width()));
        }
        for (OrderedText line : renderLines) {
            int offsetX;
            if (Objects.equals(alignment, Alignment.LEFT)) offsetX = 0;
            else if (Objects.equals(alignment, Alignment.RIGHT)) offsetX = width() - textRenderer.getWidth(line);
            else offsetX = width() / 2 - textRenderer.getWidth(line) / 2;
            context.drawText(textRenderer, line, offsetX, offsetY, 0xFFFFFFFF, true);
            offsetY += textRenderer.fontHeight + 1;
        }
    }

    @Override
    protected void onHover(int mouseX, int mouseY, boolean active) {
        TextRenderer textRenderer = VVidgets.MC.textRenderer;
        List<OrderedText> renderLines = new ArrayList<>();
        for (OrderedText line : lines) {
            StringBuilder sb = new StringBuilder();
            line.accept(((index, style, codePoint) -> {
                String s = new String(Character.toChars(codePoint));
                sb.append(s);
                return true;
            }));
            renderLines.addAll(textRenderer.wrapLines(Text.literal(sb.toString()), width()));
        }
        withDimensions(width(), (renderLines.size() * (VVidgets.MC.textRenderer.fontHeight + 1)) - 1, true);
    }

    @Override
    public TextWidget getSelf() {
        return this;
    }

    public List<OrderedText> lines() {
        return lines;
    }

    public TextWidget setLines(List<Text> lines) {
        ArrayList<OrderedText> orderedLines = new ArrayList<>();
        for (Text line : lines) orderedLines.add(line.asOrderedText());
        return setLines(orderedLines);
    }

    public TextWidget setLines(ArrayList<OrderedText> lines) {
        this.lines = lines;
        withDimensions(width(), (lines.size() * (VVidgets.MC.textRenderer.fontHeight + 1)) - 1, true);
        return getSelf();
    }

    public TextWidget setLines(Text... lines) {
        return setLines(List.of(lines));
    }

    public TextWidget setLines(OrderedText... lines) {
        return setLines(new ArrayList<>(List.of(lines)));
    }

    public TextWidget addLine(Text line) {
        for (Text ln : VVidgetsUtil.splitTextNewline(line)) addLine(ln.asOrderedText());
        return getSelf();
    }

    public TextWidget addLine(OrderedText line) {
        lines.add(line);
        return getSelf();
    }

    public TextWidget insertLine(Text line, int index) {
        for (Text ln : VVidgetsUtil.splitTextNewline(line)) {
            insertLine(ln.asOrderedText(), index);
            index += 1;
        }
        return getSelf();
    }

    public TextWidget insertLine(OrderedText line, int index) {
        lines.add(index, line);
        return getSelf();
    }

    public TextWidget removeLine(int index) {
        lines.remove(index);
        return getSelf();
    }

    public TextWidget withTextAlignment(Alignment alignment) {
        if (alignment.id.contains("left")) alignment = Alignment.LEFT;
        else if (alignment.id.contains("right")) alignment = Alignment.RIGHT;
        else alignment = Alignment.CENTER;
        this.alignment = alignment;
        return getSelf();
    }
}
