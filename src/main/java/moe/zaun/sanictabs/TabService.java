package moe.zaun.sanictabs;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.impl.EditorHistoryManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.ArrayUtil;

import java.util.LinkedList;
import java.util.List;

public class TabService {
        public List<VirtualFile> getOpenFiles(Project project) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            return getOpenFiles(fileEditorManager, ArrayUtil.reverseArray(EditorHistoryManager.getInstance(project).getFiles()));
        }

        public static void switchToTab(Project project, VirtualFile file) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            fileEditorManager.openFile(file, true);
        }

        private List<VirtualFile> getOpenFiles(FileEditorManager fileEditorManager, VirtualFile[] recentFiles) {
            List<VirtualFile> openFiles = new LinkedList<>();
            int editorTabLimit = UISettings.getInstance().EDITOR_TAB_LIMIT;
            for (VirtualFile file : recentFiles) {
                if (openFiles.size() <= editorTabLimit && fileEditorManager.isFileOpen(file) && !openFiles.contains(file)) {
                    openFiles.add(file);
                }
            }
            return openFiles;
        }
}
