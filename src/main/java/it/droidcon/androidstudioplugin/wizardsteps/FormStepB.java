package it.droidcon.androidstudioplugin.wizardsteps;


import com.android.tools.idea.ui.properties.BindingsManager;
import com.android.tools.idea.ui.validation.ValidatorPanel;
import com.android.tools.idea.ui.wizard.StudioWizardStepPanel;
import com.android.tools.swing.util.FormScalingUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class FormStepB extends FormStep {

    private static final String WIZARD_STEP_TITLE = "Create Droidcon Android Project";

    private final StudioWizardStepPanel myRootPanel;

    private final ValidatorPanel myValidatorPanel;

    private JTextField numberB;

    private JPanel myPanel;

    private BindingsManager myBindings = new BindingsManager();

    private DroidWizardModel myModel;


    public FormStepB(DroidWizardModel model) {
        super(model, WIZARD_STEP_TITLE);
        myModel = model;
        myValidatorPanel = new ValidatorPanel(this, myPanel);
        myRootPanel = new StudioWizardStepPanel(myValidatorPanel, "Configure your new project");
        FormScalingUtil.scaleComponentTree(this.getClass(), myRootPanel);
    }

    @Override
    protected void onProceeding() {
        super.onProceeding();
        myModel.setSecondNumber(getIntegerValue(numberB.getText()));
    }


    @NotNull
    @Override
    protected JComponent getComponent() {
        return myRootPanel;
    }
}
