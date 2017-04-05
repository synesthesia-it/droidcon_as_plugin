package it.droidcon.androidstudioplugin.wizardsteps;

import com.android.tools.idea.npw.project.NewProjectModel;
import com.intellij.openapi.project.Project;
import it.droidcon.androidstudioplugin.utils.Utils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.jetbrains.annotations.NotNull;


public class DroidWizardModel extends NewProjectModel {

    private static final String TAG = DroidWizardModel.class.getSimpleName();

    private Project myProject;

    private IntegerProperty firstNumber = new SimpleIntegerProperty(0);
    private IntegerProperty secondNumber = new SimpleIntegerProperty(0);


    public DroidWizardModel(@NotNull Project project) {
        myProject = project;
    }

    /**
     * This is where the final step of the wizard is executed.
     */
    @Override
    public void handleFinished() {
        Long result = firstNumber.longValue() + secondNumber.longValue();
        Utils.showDialogMessage(myProject, "Result", " A + B = " + result.toString());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //                                             SETTERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setFirstNumber(Integer number) {
        firstNumber.setValue(number);
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber.setValue(secondNumber);
    }

}
