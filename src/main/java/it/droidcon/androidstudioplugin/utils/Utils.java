package it.droidcon.androidstudioplugin.utils;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindowId;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by andrealucibello on 28/02/17.
 */
public class Utils {

    public static final String PLUGIN_DIRECTORY_NAME = "Droidcon-Plugin-Example";

    private static final NotificationGroup TOOLWINDOW_NOTIFICATION = NotificationGroup.toolWindowGroup(
            "Toolwindow",
            ToolWindowId.VCS,
            true
    );

    private static final NotificationGroup STICKY_NOTIFICATION = new NotificationGroup(
            "Sticky",
            NotificationDisplayType.STICKY_BALLOON,
            true
    );

    private static final NotificationGroup BALLOON_NOTIFICATION = new NotificationGroup(
            "Baloon",
            NotificationDisplayType.BALLOON,
            true
    );

    private static void notify(NotificationType type, NotificationGroup group, Project project, String title, String message) {
        group.createNotification(title, message, type, null).notify(project);
    }

    public static void notifySuccess(Project project, String title, String message) {
        notify(NotificationType.INFORMATION, STICKY_NOTIFICATION, project, title, message);
    }

    public static void showDialogMessage(Project project, String title , String message) {
        Messages.showMessageDialog(project, message, title, Messages.getInformationIcon());
    }

    public static void showCustomDialogMessage(Project project, String title , String message) {

        final DialogWrapper wrapper = new DialogWrapper(project, false) {

            {
                init();
            }

            @Override
            protected JComponent createCenterPanel() {
                final JPanel panel = new JPanel(new BorderLayout());
//                final JTextArea textArea = new JTextArea("some string");
//                textArea.setEditable(false);
//                textArea.setRows(40);
//                textArea.setColumns(70);
                JLabel label = new JLabel(message);
                panel.add(label);
//                panel.add(ScrollPaneFactory.createScrollPane(textArea));
                return panel;
            }
        };
        wrapper.centerRelativeToParent();
        wrapper.setTitle(title);
        wrapper.show();
    }



    /**
     * Returns the directory in which all the files relative to the plugin reside.
     *
     * @return the plugin directory if it exists and is writable, null otherwise
     */
    public static File getPluginDirectory() {
        File myPluginDirectory;
        File pluginsDirectory = new File(PathManager.getPluginsPath());
        if (pluginsDirectory.isDirectory()) {
            myPluginDirectory = new File(pluginsDirectory.getPath() + File.separator + PLUGIN_DIRECTORY_NAME);
            if (myPluginDirectory.exists() && myPluginDirectory.isDirectory() && myPluginDirectory.canExecute() && myPluginDirectory.canWrite()) {
                return myPluginDirectory;
            }
        }
        return null;
    }



    /**
     * Gets the directory with the templates.
     *
     * @return template directory if exists and is readable, null otherwise
     */
    public static File getTemplateDirectory() {
        File pluginDirectory = getPluginDirectory();
        if (pluginDirectory == null || !pluginDirectory.exists()) {
            return null;
        }
        File templateDirectory = new File(pluginDirectory, "templates");
        if (templateDirectory == null || !templateDirectory.exists() || !templateDirectory.canRead()) {
            return null;
        }
        return templateDirectory;
    }
}
