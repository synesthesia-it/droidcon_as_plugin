package it.droidcon.androidstudioplugin.wizardsteps;

import com.android.tools.idea.wizard.model.ModelWizardStep;
import org.jetbrains.annotations.NotNull;


public abstract class FormStep  extends ModelWizardStep<DroidWizardModel> {

    protected DroidWizardModel myModel;

    protected FormStep(@NotNull DroidWizardModel model, @NotNull String title) {
        super(model, title);
        myModel = model;
    }

    public Integer getIntegerValue(String s) {
        Integer result;
        try {
            result = Integer.valueOf(s);
        } catch(NumberFormatException e) {
            // :adam: TODO: 2017-03-06 11:03 - log the error
            result = new Integer(0);
        }
        return result;
    }
}
