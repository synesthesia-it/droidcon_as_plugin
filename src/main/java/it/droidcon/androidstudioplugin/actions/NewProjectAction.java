package it.droidcon.androidstudioplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import it.droidcon.androidstudioplugin.utils.Utils;

/**
 * Created by andrealucibello on 28/02/17.
 */
public class NewProjectAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();

        //Utils.notifySuccess(project, "Droidcon examples", "Got it!!");
        //Utils.showDialogMessage(project, "Droidcon examples", "Got it!!");
        Utils.showDialogMessage(project, "Droidcon examples", "Hi, welcome to " + project.getName());


    }
}
