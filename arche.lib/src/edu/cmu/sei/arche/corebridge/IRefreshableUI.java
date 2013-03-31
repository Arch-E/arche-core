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

package edu.cmu.sei.arche.corebridge;

import java.io.IOException;
import java.io.InputStream;

/**
 * One class in the user interface layer should implement this interface so that the data elements
 * can properly indicate when views have to be refreshed.
 * 
 * @author Paulo Merson
 */
public interface IRefreshableUI {

    /**
     * Take some action to register the fact that an object of the given class was created, deleted
     * or modified and views that display that kind of object should be refreshed. The method only
     * flag that one or more views need to be refreshed (by setting bits in a bitmap for example),
     * but it doesn't refresh the views. In the VO classes, this method must be called in the
     * constructor and in each setter method that changes a value that corresponds to a slot in the
     * core.
     * 
     * @param clazz an oject of this class has changed.
     */
    public void flagViewsToRefresh(Class clazz);

    /**
     * Take the proper actions to refresh all views that were flagged by calling
     * flagViewsToRefresh(). Then reset the data element (e.g. a bitmap) used to indicate a view is
     * flagged to be refreshed.
     */
    public void refreshViews();
    
    /**
     * Take some action to restart ArchE in order to refresh all the previously-defined templates
     */
    public void restartArchE();
    
    public void refreshQuestions(InputStream stream) throws IOException;    
}