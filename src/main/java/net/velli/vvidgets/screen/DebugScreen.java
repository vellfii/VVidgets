package net.velli.vvidgets.screen;

import net.minecraft.text.Text;
import net.velli.vvidgets.widgets.Widgets;
import net.velli.vvidgets.widgets.container.BasicContainerWidget;
import net.velli.vvidgets.widgets.enums.Alignment;
import net.velli.vvidgets.widgets.individual.ButtonWidget;

public class DebugScreen extends ContainerWidgetScreen {
    public DebugScreen(Text title) {
        super(title);
        addWidgets(
                Widgets.create(BasicContainerWidget::new)
                        .addWidgets(
                                Widgets.create(ButtonWidget::new)
                                        .withDimensions(100, 100, true)
                                        .withAlignment(Alignment.TOP)
                                        .withPosition(0, 10, true)
                        )
                        .withDimensions(300, 300, true)
                        .withAlignment(Alignment.CENTER)
                        .withPosition(0, 0, true)
        );
    }
}
