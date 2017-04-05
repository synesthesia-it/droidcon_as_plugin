package it.droidcon.androidstudioplugin.wizardsteps;


import com.android.tools.idea.ui.validation.ValidatorPanel;
import com.android.tools.idea.ui.wizard.StudioWizardStepPanel;
import com.android.tools.swing.util.FormScalingUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FormStepA extends FormStep {

    private static final String WIZARD_STEP_TITLE = "Create Droidcon Android Project";

    private final StudioWizardStepPanel myRootPanel;
    private final ValidatorPanel myValidatorPanel;
    private JTextField numberA;
    private JPanel myPanel;


    public FormStepA(DroidWizardModel model) {
        super(model, WIZARD_STEP_TITLE);
        myValidatorPanel = new ValidatorPanel(this, myPanel);
        myRootPanel = new StudioWizardStepPanel(myValidatorPanel, "Configure your new project");
        FormScalingUtil.scaleComponentTree(this.getClass(), myRootPanel);
    }



    @Override
    protected void onProceeding() {
        super.onProceeding();
        myModel.setFirstNumber(getIntegerValue(numberA.getText()));
    }



    @NotNull
    @Override
    protected JComponent getComponent() {
        return myRootPanel;
    }

}
