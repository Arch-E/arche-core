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

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.IViewerLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

/**
 * This class is essentially a TableViewer that allows coloring individual cells when the
 * ICellColorProvider is implemented by the label provider. This class is a temporary solution while
 * Eclipse 3.0 does not implement bug 52963 (see also 61956) (see Eclipse bugzilla repository).
 * 
 * @see ICellColorProvider
 * @author Paulo Merson
 */
public class ColoredCellTableViewer extends TableViewer {

    /**
     * Constructor simply calls TableViewer constructor.
     * 
     * @param table table widget associated to the viewer
     */
    public ColoredCellTableViewer(Table table) {
        super(table);
    }

    /**
     * This method was copied from TableViewer (r3.0) and adapted to allow setting the color of an
     * individual cell.
     * 
     * @see org.eclipse.jface.viewers.TableViewer#doUpdateItem(Widget, Object, boolean)
     */
    protected void doUpdateItem(Widget widget, Object element, boolean fullMap) {
        if (widget instanceof TableItem) {
            final TableItem item = (TableItem) widget;

            // remember element we are showing
            if (fullMap) {
                associate(element, item);
            } else {
                item.setData(element);
                mapElement(element, item);
            }

            IBaseLabelProvider prov = getLabelProvider();
            ITableLabelProvider tprov = null;
            ILabelProvider lprov = null;

            if (prov instanceof ITableLabelProvider) {
                tprov = (ITableLabelProvider) prov;
            } else {
                lprov = (ILabelProvider) prov;
            }
            int columnCount = super.getTable().getColumnCount();
            TableItem ti = item;
            // Also enter loop if no columns added. See 1G9WWGZ: JFUIF:WINNT - TableViewer with 0
            // columns does not work
            for (int column = 0; column < columnCount || column == 0; column++) {
                // Similar code in TableTreeViewer.doUpdateItem()
                String text = "";
                Image image = null;
                if (tprov != null) {
                    text = tprov.getColumnText(element, column);
                    image = tprov.getColumnImage(element, column);
                    // this is the piece of code that was added
                    if (prov instanceof ICellColorProvider) {
                        ICellColorProvider cprov = (ICellColorProvider) prov;
                        ti.setForeground(column, cprov.getForeground(element, column));
                        ti.setBackground(column, cprov.getBackground(element, column));
                    }
                } else {
                    if (column == 0) {
                        if (lprov instanceof IViewerLabelProvider) {
                            IViewerLabelProvider itemProvider = (IViewerLabelProvider) lprov;
                            ViewerLabel updateLabel = new ViewerLabel(item.getText(), item
                                    .getImage());

                            itemProvider.updateLabel(updateLabel, element);
                            text = updateLabel.getText();
                            image = updateLabel.getImage();

                        } else {
                            text = lprov.getText(element);
                            image = lprov.getImage(element);
                        }
                    }
                }
                ti.setText(column, text);
                if (ti.getImage(column) != image) {
                    ti.setImage(column, image);
                }
            }
            if (prov instanceof IColorProvider) {
                IColorProvider cprov = (IColorProvider) prov;
                ti.setForeground(cprov.getForeground(element));
                ti.setBackground(cprov.getBackground(element));
            }
            if (prov instanceof IFontProvider) {
                IFontProvider fprov = (IFontProvider) prov;
                ti.setFont(fprov.getFont(element));
            }
        }
    }

}