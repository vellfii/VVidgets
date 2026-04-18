package net.velli.vvidgets.screen;

import net.minecraft.text.Text;
import net.velli.vvidgets.widgets.Widgets;
import net.velli.vvidgets.widgets.container.BasicContainerWidget;
import net.velli.vvidgets.widgets.container.ListContainerWidget;
import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.individual.ButtonWidget;
import net.velli.vvidgets.widgets.individual.TextWidget;
import net.velli.vvidgets.widgets.individual.Widget;

public class DebugScreen extends ContainerWidgetScreen {
    public DebugScreen(Text title) {
        super(title);
        addWidgets(
                ((ListContainerWidget) Widgets.create(ListContainerWidget::new))
                        .addWidgets(
                                Widgets.create(TextWidget::new)
                                        .withDimensions(100, 10, true)
                                        .setLines(Text.literal("\nhi there\n"))
                                        .withTextAlignment(Alignment.CENTER),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 20, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                                        .withProcessor(new ClickProcessor() {
                                            @Override
                                            public void onClick(Widget<?> widget, int mouseX, int mouseY) {
                                                System.out.println(widget.hovered());
                                            }

                                            @Override
                                            public void onRelease(Widget<?> widget, int mouseX, int mouseY) {
                                                System.out.println(widget.hovered());
                                            }
                                        }),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 75, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 50, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                        )
                        .addColumns(1)
                        .addWidgets(
                                Widgets.create(TextWidget::new)
                                        .withDimensions(100, 10, true)
                                        .setLines(Text.literal("these are titles for a menu and this one wraps"))
                                        .withTextAlignment(Alignment.CENTER),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 20, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 75, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 50, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                        )
                        .addColumns(1)
                        .addWidgets(
                                Widgets.create(TextWidget::new)
                                        .withDimensions(100, 10, true)
                                        .setLines(Text.literal("here i added another column, just for fun! yay!"))
                                        .withTextAlignment(Alignment.CENTER),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 20, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 75, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 50, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                        )
                        .withDimensions(300, 300, true)
                        .withAlignment(Alignment.CENTER)
                        .withPosition(0, 0, true)

        );
    }
}
