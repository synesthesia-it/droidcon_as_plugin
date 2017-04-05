package it.droidcon.androidstudioplugin.actions;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import it.droidcon.androidstudioplugin.utils.Utils;

/**
 * Created by andrealucibello on 28/02/17.
 */
public class ThreadAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {

        ApplicationManager.getApplication().executeOnPooledThread(new Runnable() {
            @Override
            public void run() {
                //DO STUFF ...
                runOnUiThread(anActionEvent);
            }
        });


        /*
        //FOR READ AND WRITE OPERATIONS

        ApplicationManager.getApplication().runReadAction(new Runnable() {
            @Override
            public void run() {
                //...
            }
        });

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run() {
                //...
            }
        });
        */
    }

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setIcon(AllIcons.Toolbar.Unknown);
    }

    private void runOnUiThread(AnActionEvent anActionEvent) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                Project project = anActionEvent.getProject();
                Utils.showDialogMessage(project, "Droidcon examples", "This is the UI thread");
            }
        });
    }
}
