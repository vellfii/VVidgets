package net.velli.vvidgets.screen;

import net.minecraft.text.Text;
import net.velli.vvidgets.widgets.Widgets;
import net.velli.vvidgets.widgets.container.BasicContainerWidget;
import net.velli.vvidgets.widgets.container.ListContainerWidget;
import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.individual.ButtonWidget;
import net.velli.vvidgets.widgets.individual.Widget;

public class DebugScreen extends ContainerWidgetScreen {
    public DebugScreen(Text title) {
        super(title);
        addWidgets(
                ((ListContainerWidget) Widgets.create(ListContainerWidget::new))
                        .addColumns(1)
                        .addWidgets(
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(50, 20, true)
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
                                        .withDimensions(100, 75, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(75, 50, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                        )
                        .addColumns(1)
                        .addWidgets(
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(50, 20, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(20, 75, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true),
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(35, 50, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                        )
                        .withDimensions(300, 300, true)
                        .withAlignment(Alignment.CENTER)
                        .withPosition(0, 0, true)
        );
    }
}
