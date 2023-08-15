package moe.zaun.sanictabs.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.*;
import org.jetbrains.annotations.NotNull;
import moe.zaun.sanictabs.TabService;
import moe.zaun.sanictabs.TabsPopupStep;

import java.awt.Dimension;

public class SwitchTabsAction extends AnAction {

    final TabService tabService = new TabService();

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        var project = e.getProject();
        var files = this.tabService.getOpenFiles(e.getProject());
        var popup = JBPopupFactory.getInstance().createListPopup(new TabsPopupStep(project, files));

        popup.setSize(new Dimension(500, 500));
        popup.showInFocusCenter();

    }
}
