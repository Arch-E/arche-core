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

import java.io.Serializable;
import java.io.Writer;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * A simple Writer, suitable for constructing a PrintWriter, which uses an SWT Text widget as its
 * output. This class is a convenient way to write a GUI in which Jess prints its output to a text
 * widget.
 * 
 * @author Paulo Merson (copied from jess.awt.TextAreaWriter)
 */

public class SwtTextWriter extends Writer implements Serializable {

    private StringBuffer     m_str;

    private Text             console;

    private int              m_size  = 0;

    private static final int MAXSIZE = 30000;

    /**
     * Call this with an already constructed Text widget, which you can put wherever you like.
     * 
     * @param area The text area
     */
    public SwtTextWriter(Text console) {
        m_str = new StringBuffer(100);
        this.console = console;
        m_size = console.getText().length();
    }

    public synchronized void clear() {
        console.setText("");
        m_size = 0;
    }

    /**
     * Does nothing
     */
    public void close() {
    }

    /**
     * Flushes pending output to the Text widget.
     */
    public synchronized void flush() {
        int len = m_str.length();
        Display.getDefault().syncExec(new Runnable() {

            public void run() {
                if (!console.isDisposed()) {
                    console.append(m_str.toString());
                }
            }
        });
        m_str.setLength(0);
    }

    /**
     * Writes a portion of an array of characters to the Text widget. No output is actually done
     * until flush() is called.
     * 
     * @param b The array of characters
     * @param off The first character in the array to write
     * @param len The number of characters form the array to write
     * @see #flush
     */
    public synchronized void write(char b[], int off, int len) {
        String str = new String(b);
        if (str.indexOf("EOF") == -1) {
            // Jess sends a token "EOF" aftear each prompt command. This if statement avoids echoing
            // the "EOF" string.
            m_str.append(b, off, len);
        }
    }

}

