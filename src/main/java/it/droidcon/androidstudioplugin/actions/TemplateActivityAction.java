package it.droidcon.androidstudioplugin.actions;

import com.android.tools.idea.npw.project.AndroidProjectPaths;
import com.android.tools.idea.npw.project.AndroidSourceSet;
import com.android.tools.idea.npw.template.ConfigureTemplateParametersStep;
import com.android.tools.idea.npw.template.RenderTemplateModel;
import com.android.tools.idea.npw.template.TemplateHandle;
import com.android.tools.idea.ui.wizard.StudioWizardDialogBuilder;
import com.android.tools.idea.wizard.model.ModelWizard;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiPackage;
import it.droidcon.androidstudioplugin.utils.Utils;
import org.jetbrains.android.facet.AndroidFacet;

import java.io.File;
import java.util.List;

public class TemplateActivityAction extends AnAction {

    private static final String TAG = TemplateActivityAction.class.getSimpleName();

    private static final String KEY_R_ANDROID = "r_android";

    private Project myCurrentProject;
    private DataContext myDataContext;
    private RenderTemplateModel myTemplateModel;
    private AndroidFacet myAndroidFacet;


    @Override
    public void actionPerformed(AnActionEvent e) {
        myCurrentProject = e.getData(DataKeys.PROJECT);
        myDataContext = e.getDataContext();

        File templateDirectory = Utils.getTemplateDirectory();
        if (templateDirectory == null) {
            // :adam: TODO: 2017-03-01 12:17 - notify the user when something goes wrong
//            NotifyUtil.notifyError(myCurrentProject, "Plugin Error", "Could not load the template.");
            return;
        }

        VirtualFile targetFile = CommonDataKeys.VIRTUAL_FILE.getData(myDataContext);
        assert targetFile != null;
        VirtualFile targetDirectory = targetFile;
        if (!targetDirectory.isDirectory()) {
            targetDirectory = targetFile.getParent();
            assert targetDirectory != null;
        }
        String activityDescription = e.getPresentation().getText(); // e.g. "Blank Activity", "Tabbed Activity"

        Module module = LangDataKeys.MODULE.getData(myDataContext);
        assert module != null;
        myAndroidFacet = AndroidFacet.getInstance(module);
        assert myAndroidFacet != null;

        AndroidProjectPaths app = new AndroidProjectPaths(myAndroidFacet);
        AndroidSourceSet ass = new AndroidSourceSet("", app);
        List<AndroidSourceSet> androidSourceSets = AndroidSourceSet.getSourceSets(myAndroidFacet, targetDirectory);


        //GET PACKAGE NAME
        IdeView view = LangDataKeys.IDE_VIEW.getData(e.getDataContext());
        VirtualFile imHere = view.getDirectories()[0].getVirtualFile();
        PsiDirectory psiDirectory = PsiManager.getInstance(myCurrentProject).findDirectory(imHere);
        PsiPackage psiPackage = JavaDirectoryService.getInstance().getPackage(psiDirectory);
        String packageName = psiPackage.getQualifiedName();
        myTemplateModel = new RenderTemplateModel(myCurrentProject, new TemplateHandle(templateDirectory), packageName, ass, "");

        //List<SourceProvider> sourceProviders = AndroidProjectPaths.getSourceProviders(myAndroidFacet, targetDirectory);
        //String initialPackageSuggestion = AndroidPackageUtils.getPackageForPath(myAndroidFacet, androidSourceSets, targetDirectory);

        ModelWizard.Builder wizardBuilder = new ModelWizard.Builder();
        ConfigureTemplateParametersStep step = new ConfigureTemplateParametersStep(
                myTemplateModel,
                "Configure Activity",
                androidSourceSets,
                myAndroidFacet
        );
        wizardBuilder.addStep(step);
        ModelWizard modelWizard = wizardBuilder.build();

        // applied at the end of the process
        modelWizard.addResultListener(new MyResultListener());

        // create and show the dialog
        new StudioWizardDialogBuilder(modelWizard, "New Android Activity")
                .setProject(module.getProject())
                .build()
                .show();
    }



    /**
     * This one dictates the action applied to the generated files after the wizard has finished.
     * Here we move the two method from the fragment file to the modules and component files in injection directory.
     */
    private class MyResultListener implements ModelWizard.ResultListener {

        @Override
        public void onWizardFinished(boolean result) {
            // do something after wizard has finished
        }
    }


}
