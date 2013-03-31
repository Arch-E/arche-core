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

package edu.cmu.sei.arche.ui.views;

import java.io.IOException;
import java.io.PrintWriter;

import jess.Jesp;
import jess.JessException;
import jess.Rete;
import jess.awt.TextReader;

import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import edu.cmu.sei.arche.ArchEUIPlugin;
import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;

/**
 * A console to the Jess rule engine. It displays stdout and stderr messages coming from the Rete
 * object of the currently opened project. It also allows the user to submit commands interatively.
 * 
 * @author Paulo Merson
 */
public class JessConsole extends ViewPart implements IPropertyChangeListener {

    private TextViewer             viewer;

    private Text                   consoleText;

    private Text                   promptText;

    private Color                  consoleColor;

    private Button                 clearButton;

    private SwtTextWriter          tw;

    private Rete                   core;

    private JessConsoleKeyListener keyListener;

    /**
     * Called by Eclipse to create the view and initialize it.
     */
    public void createPartControl(Composite parent) {

        Composite composite = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
                | GridData.HORIZONTAL_ALIGN_FILL));

        consoleText = new Text(composite, SWT.V_SCROLL | SWT.H_SCROLL);
        consoleText.setLayoutData(new GridData(GridData.FILL_BOTH));
        consoleText.setEditable(false);
        consoleText.setText("Jess> ");
        consoleText.setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));

        consoleText.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        consoleColor = createColor(Display.getDefault(), "jess_console_color");
        consoleText.setForeground(consoleColor);

        Composite bottomLine = new Composite(composite, SWT.NULL);
        GridLayout layout2 = new GridLayout();
        layout2.marginHeight = 0;
        layout2.marginWidth = 0;
        layout2.numColumns = 2;
        bottomLine.setLayout(layout2);
        bottomLine.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

        promptText = new Text(bottomLine, SWT.SINGLE | SWT.BORDER);
        promptText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        clearButton = new Button(bottomLine, SWT.PUSH);
        clearButton.setText("Clear");
        clearButton.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent se) {
                consoleText.setText("");
                promptText.setFocus();
            }

            public void widgetDefaultSelected(SelectionEvent se) {
            }
        });

        ArchEUIPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(this);

        hookUpToJess();
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        promptText.setFocus();
    }

    /**
     * If no project is open, clear the console and display a message indicating that. If a project
     * is open, clear the console and associate this console to the Rete object. Output from Rete
     * will go to consoleText and commands typed in consolePrompt will be sent to Rete.
     */
    public void hookUpToJess() {
        ArchEFacade facade = ArchEUIPlugin.getDefault().getArchEFacade();
        if (facade == null) {
            promptText.setEditable(false);
            return;
        }
        // Console has just been activated OR console was activated and a project was opened.
        promptText.setEditable(true);

        tw = new SwtTextWriter(consoleText);
        core = facade.getEngineArchE();
//        core.setAppObject(ArchEUIPlugin.getDefault());

//        core.setClassLoader(ArchEUIPlugin.getDefault().getClass().getClassLoader().getSystemClassLoader());
//       
//        Global glob = new Global();
//        try{
//            core.importClass("org.xmlBlaster.util.Global");
//        	
//        }
//        catch(JessException e)
//        {	
//        	int i =0;
//        }
        	
        
        if (keyListener != null) {
            promptText.removeKeyListener(keyListener);
        }
        keyListener = new JessConsoleKeyListener();
        promptText.addKeyListener(keyListener);

        core.addOutputRouter("t", tw);
        core.addOutputRouter("WSTDOUT", tw);
        core.addOutputRouter("WSTDERR", tw);
        // Print out where we are
        try {
        core.executeCommand("(get-focus-stack)");
        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * Utility method that returns a color instance based on data from a preference field.
     */
    private Color createColor(Display display, String preference) {
        RGB rgb = PreferenceConverter.getColor(ArchEUIPlugin.getDefault().getPreferenceStore(),
                                               preference);
        return new Color(display, rgb);
    }

    /**
     * A property in the ArchE user preferences has changed. Here we pay heed to the jess console
     * color property.
     * 
     * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
     */
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getProperty().equals("jess_console_color")) {
            Color newColor = createColor(Display.getDefault(), "jess_console_color");
            consoleColor.dispose();
            consoleColor = newColor;
            consoleText.setForeground(consoleColor);
        }
    }

    /**
     * @see org.eclipse.ui.IWorkbenchPart#dispose()
     */
    public void dispose() {
        ArchEUIPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(this);
        super.dispose();
    }

    /**
     * Listen to key pressed on the cmd prompt.
     */
    private class JessConsoleKeyListener implements KeyListener {

        /**
         * @see org.eclipse.swt.events.KeyListener#keyPressed(org.eclipse.swt.events.KeyEvent)
         */
        public void keyPressed(KeyEvent e) {
            // nothing to do
        }

        /**
         * @see org.eclipse.swt.events.KeyListener#keyReleased(org.eclipse.swt.events.KeyEvent)
         */
        public void keyReleased(KeyEvent e) {
            String typed = promptText.getText();
        	if (promptText.getCharCount() == 0) {
	        	switch (e.keyCode) {
	        	case SWT.F2:
	        		typed = "(agenda (get-focus))";
	        		e.character = '\r';
	        		break;
	        	case SWT.F3:
	        		typed = "(facts *)";
	        		e.character = '\r';
	        		break;
	        	case SWT.F4:
	        		typed = "(watch rules facts focus)";
	        		e.character = '\r';
	        		break;
	        	case SWT.F5:
	        		typed = "(E_Connect)";
	        		e.character = '\r';
	        		break;
	        	case SWT.F6:
	        		typed = "(E_AutoDetect enable)";
	        		e.character = '\r';
	        		break;
	        	case SWT.F7:
	        		typed = "(E_AutoDetect disable)";
	        		e.character = '\r';
	        		break;
	        	case SWT.F8:
	        		typed = "(rules ExternalInteraction)";
	        		e.character = '\r';
	        		break;
	        	case SWT.F9:
	        		typed = "(facts ExternalInteraction)";
	        		e.character = '\r';
	        		break;
	        	}
        	} else if ((promptText.getCharCount() == 2) && (promptText.getText(0,0).equalsIgnoreCase("T"))) {
        		typed = "(assert (Test::RunTestCase Case" + e.character + "))\n(focus TestDesign)\n(run)";
        		e.character = '\r';
	        	}
        		
        	if (e.character == '\r') {
                try {
                    tw.write("\nJess> " + typed);
                    tw.write('\n');
                    tw.flush();
                } catch (IOException e1) {
                    PrintWriter pw = new PrintWriter(tw);
                    e1.printStackTrace(pw);
                    tw.flush();
                }
                TextReader tr = new TextReader(true);
                tr.appendText(typed); // + "\n");
                try {
                    Jesp jesp = new Jesp(tr, core);
                    jesp.parse(true);
                    ArchEUIPlugin.getDefault().refreshViews();
                } catch (JessException e1) {
                    PrintWriter pw = new PrintWriter(tw);
                    e1.printStackTrace(pw);
                    tw.flush();
                }
                promptText.setText("");
            }
        }

    }
}