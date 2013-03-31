/*
 * ArchE
 * Copyright (c) 2012 Carnegie Mellon University.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following acknowledgments and disclaimers.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * 3. All advertising materials for third-party software mentioning features or
 * use of this software must display the following disclaimer:
 *
 * “Neither Carnegie Mellon University nor its Software Engineering Institute
 * have reviewed or endorsed this software”
 *
 * 4. The names “Carnegie Mellon University,” and/or “Software Engineering
 * Institute" shall not be used to endorse or promote products derived from
 * this software without prior written permission. For written permission,
 * please contact permission@sei.cmu.edu.
 *
 * 5. Redistributions of any form whatsoever must retain the following
 * acknowledgment:
 *
 * Copyright 2012 Carnegie Mellon University.
 *
 * This material is based upon work funded and supported by the United States
 * Department of Defense under Contract No. FA8721-05-C-0003 with Carnegie
 * Mellon University for the operation of the Software Engineering Institute, a
 * federally funded research and development center.
 *
 * NO WARRANTY
 *
 * THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE MATERIAL
 * IS FURNISHED ON AN “AS-IS” BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER
 * INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR
 * MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL.
 * CARNEGIE MELLON UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH
 * RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 */

package edu.cmu.sei.arche.ui.dialog;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.help.WorkbenchHelp;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;
import edu.cmu.sei.arche.export.ExportToSQL;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;

import edu.cmu.sei.arche.undo.ArchitectureMemento;
import edu.cmu.sei.arche.undo.UndoManager;
import edu.cmu.sei.arche.undo.MySQLFileMemento;


/**
 * Dialog box used to show a question to the user and send the answer provided by him to the core.
 * 
 * @author Paulo Merson
 */
public class QuestionToUserDialog extends TitleAreaDialog {

    /** composite corresponding to the top whide band area */
    private Composite        topComposite;

    /** composite corresponding to the area in the middle between white band and buttons */
    private Composite        middleComposite;

    /** composite corresponding to the bottom area that has the buttons */
    private Composite        bottomComposite;

    private Label            scenarioTextLabel;

    private Group            answerGroup;

    private Text             questionText;

    private Button[]         answerButtons;

    private Label[]          answerLabels;

    private Text[]           answerTexts;

    private Button           previousButton;

    private Button           nextButton;

    private QuestionToUserVO questionVO;

    private QuestionParser   parser;

    /** list of questions from current ProjectVO */
    private List             questions;

    /** index of the question being displayed in the list of questions */
    private int              currentQuestionIndex;

    /** local cache of facade */
    private ArchEFacade      facade;

    /** local cache of current ProjectVO */
    private ProjectVO        projectVO;

    /**
     * Constructor for the class. The dialog box is created when user double clicks a question in
     * the question view.
     * 
     * @param parentShell the window from which this dialog is being called.
     * @param questionVO question that the user clicked on.
     */
    public QuestionToUserDialog(Shell parentShell, QuestionToUserVO questionVO) {
        super(parentShell);
        if (questionVO == null) {
            throw new IllegalArgumentException("No question was selected.");
        }
        this.questionVO = questionVO;
        parser = ArchEUIPlugin.getDefault().getQuestionParser();
        projectVO = ArchEUIPlugin.getDefault().getProjectVo();
        questions = projectVO.getQuestions();
        facade = ArchEUIPlugin.getDefault().getArchEFacade();
        currentQuestionIndex = questions.indexOf(questionVO);
    }

    /**
     * Make the dlg box resizeable--necessary in case the question has too many options.
     * 
     * @see org.eclipse.jface.window.Window#setShellStyle(int)
     */
    protected void setShellStyle(int newShellStyle) {
        super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.MAX);
    }

    /**
     * Configures the given shell in preparation for opening this window in it. Sets the title in
     * the title bar.
     * 
     * @param shell
     * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
     */
    protected void configureShell(Shell shell) {
        super.configureShell(shell);
        shell.setText("Interact with ArchE");
    }

    /**
     * Method declared in Window. Configures the top part of the dialog (area in the white band).
     * NOTE: text that is displayed here varies according to the current question, so it's set later
     * when we call createDynamicControls.
     * 
     * @param parent parent Composite to which controls will be added
     */
    protected Control createContents(Composite parent) {
        topComposite = parent;
        Control contents = super.createContents(parent);
        setTitleImage(getImageFromPath("icons/arche-logo48.gif"));
        return contents;
    }

    /**
     * Creates the widgets in the large area between the white band and the buttons. Method declared
     * on Dialog. Note that dynamic contents are set later.
     */
    protected Control createDialogArea(Composite parent) {
        // top level composite
        Font font = parent.getFont();

        // create a composite with standard margins and spacing
        middleComposite = (Composite) super.createDialogArea(parent);
        GridLayout layout = new GridLayout();
        middleComposite.setLayout(layout);
        middleComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
        middleComposite.setFont(font);

        Label questionLabel = new Label(middleComposite, SWT.NONE);
        questionLabel.setFont(font);
        questionLabel.setText("Question:");

        questionText = new Text(middleComposite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
        questionText.setEditable(false);
        GridData questionGridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        questionGridData.horizontalAlignment = GridData.FILL;
        questionGridData.widthHint = 200;
        questionGridData.heightHint = 100;
        questionText.setLayoutData(questionGridData);

        answerGroup = new Group(middleComposite, SWT.FILL);
        answerGroup.setText("Answer");
        GridLayout groupLayout = new GridLayout();
        answerGroup.setLayout(groupLayout);
        GridData groupGridData = new GridData(GridData.FILL_BOTH);
        groupGridData.widthHint = 200;
        groupGridData.heightHint = 150;
        answerGroup.setLayoutData(groupGridData);

        return middleComposite;
    }

    /**
     * Create the button area at the bottom of the dialog. It has the following buttons: [Help]
     * [Previous][Next][Finish][Cancel]
     * 
     * @param parent parent Composite to where the widgest will be added.
     */
    protected void createButtonsForButtonBar(Composite parent) {
        bottomComposite = parent;
        Button helpButton = createButton(parent, IDialogConstants.HELP_ID,
                                         IDialogConstants.HELP_LABEL, false);
        helpButton.addSelectionListener(new SelectionListener() {

            // help button pressed
            public void widgetSelected(SelectionEvent se) {
                WorkbenchHelp.displayHelp("SEI.ArchE.UI." + questionVO.getQuestionId());
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });
        helpButton.setEnabled(false); // Andres 3/14/08

        previousButton = createButton(parent, IDialogConstants.NO_ID, "< Previous", false);
        previousButton.addSelectionListener(new SelectionListener() {

            // previous button pressed
            public void widgetSelected(SelectionEvent se) {
                moveToAdjacentQuestion(-1);
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        nextButton = createButton(parent, IDialogConstants.NO_ID, "Next >", false);
        nextButton.addSelectionListener(new SelectionListener() {

            // next button pressed
            public void widgetSelected(SelectionEvent se) {
                moveToAdjacentQuestion(+1);
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        Button finishButton = createButton(parent, IDialogConstants.FINISH_ID,
                                           IDialogConstants.FINISH_LABEL, true);
        finishButton.addSelectionListener(new SelectionListener() {

            // finish button pressed:
            // For each QuestionToUserVO that has a value in property "answer", assert it to ArchE
            // Core via fa�ade (if there�s an answer it�s because it should go to the fact
            // base�that simple). If the question category is "alert", the answer field should
            // be set to "ACK" just to indicate that the user read the alert.
            public void widgetSelected(SelectionEvent se) {
                assignAnswerToVO();            
                exportCurrentArchitectureForUndo();                
                answerQuestionsInCore();
                close();
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }

            /**
             * We iterate over the list of questions and when one of them is answered, we send the
             * answer to the core. But the core triggers VOUpdate which ends up changing the list of
             * questions! That causes the iterator to 'fail-fast'. So we copy the questions with
             * answer to a separate collection and iterate over it. (An alternative would be every
             * time we send an answer to the core we have to start iterating over a refreshed list
             * possibly making a recursive call to this method. However, if the core does not
             * retract the answered question, it's in an infinite loop.)
             */
            private void answerQuestionsInCore() {
                questions = projectVO.getQuestions();
                List questionsArray = new ArrayList();
                for (Iterator it = questions.iterator(); it.hasNext();) {
                    QuestionToUserVO question = (QuestionToUserVO) it.next();
                    if (question.getAnswer() != null) {
                        questionsArray.add(question);
                    }
                }
                                
                for (Iterator it = questionsArray.iterator(); it.hasNext();) {
                	
                	
                    QuestionToUserVO question = (QuestionToUserVO) it.next();
                    facade.answerQuestion(question);
                }
            }

        });

        Button cancelButton = createButton(parent, IDialogConstants.CANCEL_ID,
                                           IDialogConstants.CANCEL_LABEL, false);
        cancelButton.addSelectionListener(new SelectionListener() {

            // cancel button pressed:
            // For each QuestionToUserVO that has a value in property "answer", reset that value
            // to null to indicate the user did not confirm that answer (no call to the
            // facade is made). Then Close the dialog box.
            public void widgetSelected(SelectionEvent se) {
                for (Iterator it = questions.iterator(); it.hasNext();) {
                    QuestionToUserVO question = (QuestionToUserVO) it.next();
                    if (question.getAnswer() != null) {
                        question.setAnswer(null);
                    }
                }
                close();
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        enableDisableButtons();
        // At this point all widgets were created.
        // Now we set the dynamic contents for the top white band area, middle area with question
        // and answer and bottom area with the buttons.
        createOrSetDynamicWidgets();

    }
    
    /**
     * 	Save the current architecture in a local file to support undoing the last applied tactic     
     */
//    static int undoVersion = 1;
    private void exportCurrentArchitectureForUndo() {
        IProject projectHandle = ArchEUIPlugin.getDefault().getOpenProject();
//        IFolder subdir = projectHandle.getFolder("design");
//        if (subdir.exists()) {
////        	IFile file = subdir.getFile("undo"+undoVersion+".sql");
////        	undoVersion++;
//        	IFile file = subdir.getFile(UndoManager.getInstance().getUndoMySQLVersion());
//        	//IFile file = subdir.getFile("undo.sql");
//        	String temp  = file.getLocation().toFile().getAbsolutePath();
//	    	int first = 0;
//	    	int end = temp.indexOf("\\", first);
//	    	String sqlFilePath = "/" + temp.substring(first, end);
//
//	    	while(true){
//		    	 first = end + 1;
//		    	 end = temp.indexOf("\\", first);
//		    	 if(end == -1){
//		    		 sqlFilePath = sqlFilePath + "/" + temp.substring(first);			    		 
//		    		 break;
//		    	 }
//		    	 sqlFilePath = sqlFilePath + "/" + temp.substring(first, end);
//	    	}
//            
//	    	//ExportToSQL exportToSQL = new ExportToSQL(facade);       
//            //exportToSQL.exportTo(sqlFilePath,false);
////	    	ArchitectureMemento memento = UndoManager.getInstance().createApplyTacticMemento(facade, sqlFilePath);
//        }
    	ArchitectureMemento memento = UndoManager.getInstance().createApplyTacticMemento(facade, projectHandle);
 	}

	/**
     * Code factored out of event handler for previous and next button. Move to previous (if hop is
     * -1) or next (if hop is +1) question in the list of questions. Assumes there's a previous or
     * next question to move to.
     * 
     * @param hop number of questions to shift.
     */
    private void moveToAdjacentQuestion(int hop) {
        assignAnswerToVO();
        currentQuestionIndex += hop;
        questionVO = (QuestionToUserVO) questions.get(currentQuestionIndex);
        enableDisableButtons();
        createOrSetDynamicWidgets();
    }

    /**
     * Set the text of the widgets that should vary according to the current question. Also, create
     * the answer box and it's internal widgets according to the current question.
     * 
     * @param composite
     */
    private void createOrSetDynamicWidgets() {

        //
        // Set top white band of the dialog box
        //
        setTitle(parser.getQuestionCategory(questionVO)); // bold text in the top white band
        setMessage(parser.getQuestionPurpose(questionVO)); // non-bold text in the top white band

        // 
        // Help message is the same in any area of the screen
        //
        WorkbenchHelp.setHelp(topComposite, "SEI.ArchE.UI." + questionVO.getQuestionId());
        WorkbenchHelp.setHelp(middleComposite, "SEI.ArchE.UI." + questionVO.getQuestionId());
        WorkbenchHelp.setHelp(bottomComposite, "SEI.ArchE.UI." + questionVO.getQuestionId());

        String question = parser.getQuestionText(questionVO);
        if (question == null) {
            questionText.setText("COULD NOT PARSE");
        } else {
            questionText.setText(question);
        }
        ((GridLayout) answerGroup.getLayout()).numColumns = parser.getNumColumns(questionVO);

        if (answerButtons != null) {
            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].dispose();
            }
        }
        if (answerTexts != null) {
            for (int i = 0; i < answerTexts.length; i++) {
                answerLabels[i].dispose();
                answerTexts[i].dispose();
            }
        }

        // the answer on screen will not be necessarily empty. It's possible that the user has
        // answered this question, clicked next or previous and now came back to this question. In
        // this case questionVO.getAnswer() contains the answer he gave, which was not submitted
        // to core yet. If getAnswer() is null, then we use the "default" answer, which is
        // an answer suggested by ArchE.
        String answerToUse[] = questionVO.getAnswer();
        if (answerToUse == null) {
            answerToUse = questionVO.getDefaultAnswer();
        }
        String questionType = parser.getQuestionType(questionVO);
        if (questionType.equals(QuestionParser.TYPE_ALERT)) {
            answerGroup.setVisible(false);
            answerGroup.layout(true);
        } else {
            answerGroup.setVisible(true);
            List optionLabels = parser.getQuestionOptionLabels(questionVO);
            String[] optionsInVo = questionVO.getOptions();
            int numInputs = Math.max(optionsInVo.length, optionLabels.size());

            if (questionType.equals(QuestionParser.TYPE_SINGLE_CHOICE)) {
                answerButtons = new Button[numInputs];
                for (int i = 0; i < numInputs; i++) {
                    answerButtons[i] = new Button(answerGroup, SWT.RADIO);
                    answerButtons[i].setText((String) optionLabels.get(i % optionLabels.size()));
                }
                // answerToUse[0] has the index of the default selection (e.g.
                // "1" for selecting the first option).
                if (answerToUse != null && answerToUse.length > 0) {
                    int defaultOption = Integer.parseInt(answerToUse[0]);
                    answerButtons[defaultOption - 1].setSelection(true);
                }
                answerGroup.layout(true);
                answerButtons[0].setFocus();
            } else if (questionType.equals(QuestionParser.TYPE_MULTIPLE_CHOICE)) {
                answerButtons = new Button[numInputs];
                for (int i = 0; i < numInputs; i++) {
                    answerButtons[i] = new Button(answerGroup, SWT.CHECK);
                    answerButtons[i].setText((String) optionLabels.get(i % optionLabels.size()));
                }
                // answerToUse[0] has a space-separated list of the indexes of
                // the default selections (e.g. "1 4 5" for selecting the 1st, 4th and 5th options).
                if (answerToUse != null && answerToUse.length > 0) {
                    String[] defaultChecksStr = answerToUse[0].split("\\s");
                    for (int i = 0; i < defaultChecksStr.length; i++) {
                        if (defaultChecksStr[i].trim().length() > 0) {
                            int defaultCheckedOption = Integer.parseInt(defaultChecksStr[i]);
                            answerButtons[defaultCheckedOption - 1].setSelection(true);
                        }
                    }
                }
                answerGroup.layout(true);
                answerButtons[0].setFocus();
            } else if (questionType.equals(QuestionParser.TYPE_INPUT_VALUE)) {
                // For input text boxes, we need two columns for each pair or label and input box
                ((GridLayout) answerGroup.getLayout()).numColumns = parser
                        .getNumColumns(questionVO) * 2;
                //                answerPanel.setLayout(groupLayout);
                answerLabels = new Label[numInputs];
                answerTexts = new Text[numInputs];
                for (int i = 0; i < numInputs; i++) {
                    // Create the label. If QuestionToUserVO.options has more elements than
                    // ".optionLabelN" in the bundle, then we repeat the existing ".optionLabelN"
                    // cyclically.
                    answerLabels[i] = new Label(answerGroup, SWT.LEFT);
                    answerLabels[i].setText((String) optionLabels.get(i % optionLabels.size()));
                    answerTexts[i] = new Text(answerGroup, SWT.SINGLE | SWT.BORDER);
                    answerTexts[i].setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
                    answerTexts[i].addFocusListener(new FocusListener() {

                        // mark all text when input text box gains focus
                        public void focusGained(FocusEvent e) {
                            Text text = (Text) e.widget;
                            text.setSelection(0, text.getText().length());
                        }

                        public void focusLost(FocusEvent e) {
                            // nothing to do
                        }

                    });
                }
                // answerToUse[i] has the default answer for the ith input text box.
                if (answerToUse != null) {
                    for (int i = 0; i < answerToUse.length; i++) {
                        answerTexts[i].setText(answerToUse[i]);
                    }
                }
                answerGroup.layout(true);
                answerTexts[0].setFocus();
            } else if (questionType.equals(QuestionParser.TYPE_YES_NO)) {
                answerButtons = new Button[2];
                answerButtons[0] = new Button(answerGroup, SWT.RADIO);
                answerButtons[0].setText("Yes");
                answerButtons[1] = new Button(answerGroup, SWT.RADIO);
                answerButtons[1].setText("No");
                // answerToUse[0] contains "yes" or "no"
                if (answerToUse != null && answerToUse.length > 0) {
                    if (answerToUse[0].equalsIgnoreCase("yes")) {
                        answerButtons[0].setSelection(true);
                    } else if (answerToUse[0].equalsIgnoreCase("no")) {
                        answerButtons[1].setSelection(true);
                    }
                }
                answerGroup.layout(true);
                answerButtons[0].setFocus();
            }
        }
    }

    /**
     * Enable or disable the previous and next buttons according to the position of the current
     * question in the list of questions.
     */
    private void enableDisableButtons() {
        if (currentQuestionIndex == 0) {
            previousButton.setEnabled(false);
        } else {
            previousButton.setEnabled(true);
        }
        if (currentQuestionIndex == (questions.size() - 1)) {
            nextButton.setEnabled(false);
        } else {
            nextButton.setEnabled(true);
        }
    }

    /**
     * Creates an Image object from an ImageDescriptor created based on the image file specified by
     * the relative path.
     * 
     * @param relativePath path inside the plugin (typically "icons/imageName.gif")
     * @return Image object
     */
    private Image getImageFromPath(String relativePath) {
        URL baseUrlPlugin = ArchEUIPlugin.getDefault().getDescriptor().getInstallURL();
        URL imgUrl = null;
        try {
            imgUrl = new URL(baseUrlPlugin, relativePath);
        } catch (MalformedURLException ex) {
            return null;
        }
        return ImageDescriptor.createFromURL(imgUrl).createImage();
    }

    /**
     * User has clicked previous, next or finish. We have to store the answer to the VO for now (not
     * sending to the core). After this, if has clicked finish it will be sent to core.
     */
    private void assignAnswerToVO() {
        String answers[] = null;
        String questionType = parser.getQuestionType(questionVO);

        if (questionType.equals(QuestionParser.TYPE_INPUT_VALUE)) {
            answers = new String[answerTexts.length];
            for (int i = 0; i < answerTexts.length; i++) {
                answers[i] = answerTexts[i].getText();
            }
        } else {
            answers = new String[1];
            if (questionType.equals(QuestionParser.TYPE_ALERT)) {
                answers[0] = "ACK";
            } else if (questionType.equals(QuestionParser.TYPE_SINGLE_CHOICE)) {
                for (int i = 0; i < answerButtons.length; i++) {
                    if (answerButtons[i].getSelection()) {
                        // found the selected one
                        answers[0] = "" + (i + 1);
                    }
                }
            } else if (questionType.equals(QuestionParser.TYPE_MULTIPLE_CHOICE)) {
                StringBuffer answer = new StringBuffer();
                for (int i = 0; i < answerButtons.length; i++) {
                    if (answerButtons[i].getSelection()) {
                        // found a selected one
                        answer.append((i + 1) + " ");
                    }
                }
                answers[0] = answer.toString().trim();
            } else if (questionType.equals(QuestionParser.TYPE_YES_NO)) {
                if (answerButtons[0].getSelection()) {
                    answers[0] = "yes";
                } else {
                    answers[0] = "no";
                }
            }
        }
        questionVO.setAnswer(answers);
    }

}