package it.droidcon.androidstudioplugin.actions;

import com.intellij.icons.AllIcons;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.android.facet.AndroidFacet;


/**
 * Created by andrealucibello on 28/02/17.
 */
public class HelloWorldAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        createClass(anActionEvent);
    }

    private void createClass(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getProject();
        PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText("HelloWorld.java", StdFileTypes.JAVA, "public class HelloWorld {}");

        new WriteCommandAction.Simple(project, psiFile) {

            @Override
            protected void run() throws Throwable {
                //GET DIRECTORY
                IdeView view = LangDataKeys.IDE_VIEW.getData(anActionEvent.getDataContext());
                VirtualFile imHere = view.getDirectories()[0].getVirtualFile();
                PsiDirectory psiDirectory = PsiManager.getInstance(project).findDirectory(imHere);

                PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
                PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(project);

                //DECLARE METHOD NAME AND METHOD RETURN TYPE
                PsiMethod psiMethod = factory.createMethod("pippo", PsiType.LONG);
                //DECLARE METHOD INPUT PARAMETERS
                PsiParameterList parameterList = factory.createParameterList(new String[]{"a", "b"}, new PsiType[]{PsiType.INT, PsiType.INT});
                //DECLARE METHOD STATEMENT BODY
                PsiCodeBlock block = factory.createCodeBlockFromText("{int c = 5;}", null);
                //DECLARE SINGLE LINES OF CODE
                PsiStatement psiStatement = factory.createStatementFromText("String s = \"Some fake code\";", null);
                PsiStatement psiReturnStatement = factory.createStatementFromText("return a + b + c;", null);
                //ADD SINGLE LINES OF CODE TO THE BODY
                block.add(psiStatement);
                block.add(psiReturnStatement);
                //REPLACE ALL METHOD BODY SOURCE CODE
                psiMethod.getBody().replace(block);
                //REPLACE THE PARAMETER LIST
                psiMethod.getParameterList().replace(parameterList);

                //ADD COMMENTS AND JAVADOC TO YOUR METHOD
                String commentText = "/**\n" +
                        "* Comments and javadoc here \n" +
                        "* @see  \n" +
                        "* @verifies \n" +
                        "*/";
                PsiComment psiComment = factory.createCommentFromText(commentText, null);
                psiMethod.addBefore(psiComment, psiMethod.getFirstChild());

                //ADD THE METHOD TO THE CLASS
                psiJavaFile.getClasses()[0].add(psiMethod);

                //REFORMAT CODE
                CodeStyleManager.getInstance(project).reformat(psiJavaFile, false);
                psiDirectory.add(psiFile);


            }
        }.execute();
    }

    @Override
    public void update(AnActionEvent e) {
        boolean isAvailable = isAvailable(e.getDataContext());
        e.getPresentation().setEnabledAndVisible(isAvailable);

        e.getPresentation().setIcon(AllIcons.Nodes.Class);
    }

    /**
     * This opens the action in appropriate context only.
     *
     * @param dataContext Data context of the event
     * @return whetehr the action is available in the given context
     */
    protected static boolean isAvailable(DataContext dataContext) {
        final Module module = LangDataKeys.MODULE.getData(dataContext);
        final IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
        if (module == null ||
                view == null ||
                view.getDirectories().length == 0 ||
                AndroidFacet.getInstance(module) == null) {
            return false;
        }

        if (!view.getDirectories()[0].getVirtualFile().getPath().contains("src/main/java")) {
            return false;
        }

        return true;
    }


}
