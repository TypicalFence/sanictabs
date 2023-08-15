package moe.zaun.sanictabs;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.*;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ui.StatusText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

public class TabsPopupStep implements ListPopupStepEx<VirtualFile> {


    private final List<VirtualFile> files;
    private final Project project;
    private VirtualFile selected;


    public TabsPopupStep(Project project, List<VirtualFile> files) {
        this.files = files;
        this.project = project;
    }

    @Override
    public @NotNull List<VirtualFile> getValues() {
        return this.files;
    }

    @Override
    public boolean isSelectable(VirtualFile value) {
        return true;
    }

    @Override
    public @Nullable Icon getIconFor(VirtualFile value) {
        return value.getFileType().getIcon();
    }

    @Override
    public @NlsContexts.ListItem @NotNull String getTextFor(VirtualFile value) {
        return value.getName();
    }

    @Override
    public @Nullable ListSeparator getSeparatorAbove(VirtualFile value) {
        return null;
    }

    @Override
    public int getDefaultOptionIndex() {
        return 0;
    }

    @Override
    public @NlsContexts.PopupTitle @Nullable String getTitle() {
        return "Tabs";
    }

    @Override
    public @Nullable PopupStep<?> onChosen(VirtualFile selectedValue, boolean finalChoice) {
        this.selected = selectedValue;
        return null;
    }

    @Override
    public boolean hasSubstep(VirtualFile selectedValue) {
        return false;
    }

    @Override
    public void canceled() {

    }

    @Override
    public boolean isMnemonicsNavigationEnabled() {
        return false;
    }

    @Override
    public @Nullable MnemonicNavigationFilter<VirtualFile> getMnemonicNavigationFilter() {
        return null;
    }

    @Override
    public boolean isSpeedSearchEnabled() {
        return true;
    }

    @Override
    public @Nullable SpeedSearchFilter<VirtualFile> getSpeedSearchFilter() {
        return new SpeedSearchFilter<VirtualFile>() {
            @Override
            public boolean canBeHidden(VirtualFile value) {
                return true;
            }

            @Override
            public String getIndexedString(VirtualFile value) {
                return value.getPresentableUrl();
            }
        };

    }

    @Override
    public boolean isAutoSelectionEnabled() {
        return false;
    }

    @Override
    public @Nullable Runnable getFinalRunnable() {
        Project project = this.project;
        VirtualFile file = this.selected;

        return new Runnable() {
            @Override
            public void run() {
                TabService.switchToTab(project, file);
            }
        };
    }

    @Override
    public @NlsContexts.Tooltip @Nullable String getTooltipTextFor(VirtualFile value) {
        return value.getPath();
    }

    @Override
    public void setEmptyText(@NotNull StatusText emptyText) {
    }
}
