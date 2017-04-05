package it.droidcon.androidstudioplugin.actions;

import com.android.tools.idea.ui.wizard.StudioWizardDialogBuilder;
import com.android.tools.idea.wizard.model.ModelWizard;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import it.droidcon.androidstudioplugin.wizardsteps.DroidWizardModel;
import it.droidcon.androidstudioplugin.wizardsteps.FormStepA;
import it.droidcon.androidstudioplugin.wizardsteps.FormStepB;


public class WizardAction extends AnAction {

    private static final String TAG = NewProjectAction.class.getSimpleName();

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);

        DroidWizardModel wizardModel = new DroidWizardModel(project);
        FormStepA stepA = new FormStepA(wizardModel);
        FormStepB stepB = new FormStepB(wizardModel);
        ModelWizard wizard = new ModelWizard.Builder()
                .addStep(stepA)
                .addStep(stepB)
                .build();
        new StudioWizardDialogBuilder(wizard, "Create New Droidcon Project").build().show();
    }

}
