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

package edu.cmu.sei.designview.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;

import edu.cmu.sei.designview.DesignViewPlugin;

/**
 * Abstract prototype of an architectural element.
 * Has a size (width and height), a location (x and y position) and a list of incoming
 * and outgoing connections. Use subclasses to instantiate a specific shape.
 * @see edu.cmu.sei.designview.model.Module
 * @see edu.cmu.sei.designview.model.Interface
 * 
 * @author Hyunwoo Kim
 * @date July 10, 2006
 */

public abstract class ArchElement extends ArchModel {
	
	private static final long serialVersionUID = 1;

	/** 
	 * A static array of property descriptors.
	 * There is one IPropertyDescriptor entry per editable property.
	 * @see #getPropertyDescriptors()
	 * @see #getPropertyValue(Object)
	 * @see #setPropertyValue(Object, Object)
	 */
	/** Property ID to use when the location of this ArchElement is modified. */
	public static final String LOCATION_PROP = "ArchElement.Location";
	/** Property ID to use then the size of this ArchElement is modified. */
	public static final String SIZE_PROP = "ArchElement.Size";
	/** Property ID to use when the list of outgoing connections is modified. */
	public static final String SOURCE_CONNECTIONS_PROP = "ArchElement.SourceConn";
	/** Property ID to use when the list of incoming connections is modified. */
	public static final String TARGET_CONNECTIONS_PROP = "ArchElement.TargetConn";
	/** ID for the Root property value (used for by the corresponding property descriptor).  */
	public static final String ROOT_PROP = "ArchElement.Root";
		
	protected static Image createImage(String name) {
		InputStream stream = DesignViewPlugin.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}

	
	/** Location of this ArchElement. */
	private Point location = new Point(0, 0);
	/** Size of this ArchElement. */
	private Dimension size = new Dimension(50, 50);
	/** List of outgoing Connections. */
	private List sourceConnections = new ArrayList();
	/** List of incoming Connections. */
	private List targetConnections = new ArrayList();

	/**
	 * Add an incoming or outgoing connection to this ArchElement.
	 * @param conn a non-null connection instance
	 * @throws IllegalArgumentException if the connection is null or has not distinct endpoints
	 */
	void addConnection(ArchRelation conn) {
		if (conn == null || conn.getSource() == conn.getTarget()) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.add(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.add(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
		}
	}

	/**
	 * Return a pictogram (small icon) describing this model element.
	 * Children should override this method and return an appropriate Image.
	 * @return a 16x16 Image or null
	 */
	public abstract Image getIcon();

	/**
	 * Return the Location of this ArchElement.
	 * @return a non-null location instance
	 */
	public Point getLocation() {
		return location.getCopy();
	}


	/**
	 * Return the Size of this ArchElement.
	 * @return a non-null Dimension instance
	 */
	public Dimension getSize() {
		return size.getCopy();
	}

	/**
	 * Return a List of outgoing Connections.
	 */
	public List getSourceConnections() {
		return new ArrayList(sourceConnections);
	}

	/**
	 * Return a List of incoming Connections.
	 */
	public List getTargetConnections() {
		return new ArrayList(targetConnections);
	}

	/**
	 * Remove an incoming or outgoing connection from this shape.
	 * @param conn a non-null connection instance
	 * @throws IllegalArgumentException if the parameter is null
	 */
	void removeConnection(ArchRelation conn) {
		if (conn == null) {
			throw new IllegalArgumentException();
		}
		if (conn.getSource() == this) {
			sourceConnections.remove(conn);
			firePropertyChange(SOURCE_CONNECTIONS_PROP, null, conn);
		} else if (conn.getTarget() == this) {
			targetConnections.remove(conn);
			firePropertyChange(TARGET_CONNECTIONS_PROP, null, conn);
		}
	}

	/**
	 * Set the Location of this ArchElement.
	 * @param newLocation a non-null Point instance
	 * @throws IllegalArgumentException if the parameter is null
	 */
	public void setLocation(Point newLocation) {
		if (newLocation == null) {
			throw new IllegalArgumentException();
		}
		location.setLocation(newLocation);
		firePropertyChange(LOCATION_PROP, null, location);
	}

	/**
	 * Set the Size of this ArchElement.
	 * Will not modify the size if newSize is null.
	 * @param newSize a non-null Dimension instance or null
	 */
	public void setSize(Dimension newSize) {
		if (newSize != null) {
			size.setSize(newSize);
			firePropertyChange(SIZE_PROP, null, size);
		}
	}
	
	public abstract void setFactid(String newFactid);

	public abstract void setName(String newName);
	
	public abstract String getFactid();

	public abstract String getName();
}
