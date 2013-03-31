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

package edu.cmu.sei.arche.ui.views.designtree.ui;

import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public class NoArticleSorter extends ViewerSorter {	/*
	 * @see ViewerSorter#compare(Viewer, Object, Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		int cat1 = category(e1);
		int cat2 = category(e2);
		if (cat1 != cat2) return cat1 - cat2;
		String name1, name2;
		if (viewer == null || !(viewer instanceof ContentViewer)) {
			name1 = e1.toString();
			name2 = e2.toString();
		} else {
			IBaseLabelProvider prov = ((ContentViewer)viewer).getLabelProvider();
			if (prov instanceof ILabelProvider) {
				ILabelProvider lprov = (ILabelProvider)prov;
				name1 = lprov.getText(e1);
				name2 = lprov.getText(e2);
			} else {
				name1 = e1.toString();
				name2 = e2.toString();
			}
		}
		if(name1 == null) name1 = "";
		if(name2 == null) name2 = "";
		name1 = stripArticles(name1);
		name2 = stripArticles(name2);
		return collator.compare(name1, name2);
	}
	
	protected String stripArticles(String name) {
		String test = name.toLowerCase();
		if(test.startsWith("the ") && test.length() > 3) {
			return name.substring(4);
		} else if(test.startsWith("a ") && test.length() > 1) {
			return name.substring(2);
		} else if(test.startsWith("el ") && test.length() > 2) {
			return name.substring(3);
		} else if(test.startsWith("la ") && test.length() > 2) {
			return name.substring(3);
		}
		return name;	
	}

}
