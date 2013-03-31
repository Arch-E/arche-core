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

import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Image;

import edu.cmu.sei.designview.DesignViewPlugin;

/**
 * Abstract prototype of an architectural relation between
 * two distinct architectural elements.
 * 
 * @author Hyunwoo Kim
 * @date July 10, 2006
 */
public abstract class ArchRelation extends ArchModel {

	
	private static final long serialVersionUID = 1;
		
	
	/** True, if the connection is bidirected. */
	private boolean isBidirected;
	
	/** True, if the connection is attached to its endpoints. */ 
	private boolean isConnected;
	/** Line drawing style for this connection. */
	private int lineStyle = Graphics.LINE_SOLID;
	/** Connection's source endpoint. */
	private ArchElement source;
	/** Connection's target endpoint. */
	private ArchElement target;

	/** 
	 * Create a (solid) connection between two distinct shapes.
	 * @param source a source endpoint for this connection (non null)
	 * @param target a target endpoint for this connection (non null)
	 * @throws IllegalArgumentException if any of the parameters are null or source == target
	 * @see #setLineStyle(int) 
	 */
	public ArchRelation(ArchElement source, ArchElement target) {
		reconnect(source, target);
		isBidirected = false;
	}

	/** 
	 * Disconnect this connection from the shapes it is attached to.
	 */
	public void disconnect() {
		if (isConnected) {
			source.removeConnection(this);
			target.removeConnection(this);
			isConnected = false;
		}
	}

	/**
	 * Returns the line drawing style of this connection.
	 * @return an int value (Graphics.LINE_DASH or Graphics.LINE_SOLID)
	 */
	public int getLineStyle() {
		return lineStyle;
	}

	/**
	 * Returns the source endpoint of this connection.
	 * @return a non-null ArchElement instance
	 */
	public ArchElement getSource() {
		return source;
	}

	/**
	 * Returns the target endpoint of this connection.
	 * @return a non-null ArchElement instance
	 */
	public ArchElement getTarget() {
		return target;
	}

	/** 
	 * Reconnect this connection. 
	 * The connection will reconnect with the ArchElements it was previously attached to.
	 */  
	public void reconnect() {
		if (!isConnected) {
			source.addConnection(this);
			target.addConnection(this);
			isConnected = true;
		}
	}

	/**
	 * Reconnect to a different source and/or target ArchElement.
	 * The connection will disconnect from its current attachments and reconnect to 
	 * the new source and target. 
	 * @param newSource a new source endpoint for this connection (non null)
	 * @param newTarget a new target endpoint for this connection (non null)
	 * @throws IllegalArgumentException if any of the paramers are null or newSource == newTarget
	 */
	public void reconnect(ArchElement newSource, ArchElement newTarget) {
		if (newSource == null || newTarget == null || newSource == newTarget) {
			throw new IllegalArgumentException();
		}
		disconnect();
		this.source = newSource;
		this.target = newTarget;
		reconnect();
	}

	/**
	 * Set the line drawing style of this connection.
	 * @param lineStyle one of following values: Graphics.LINE_DASH or Graphics.LINE_SOLID
	 * @see Graphics#LINE_DASH
	 * @see Graphics#LINE_SOLID
	 * @throws IllegalArgumentException if lineStyle does not have one of the above values
	 */
	public void setLineStyle(int lineStyle) {
		if (lineStyle != Graphics.LINE_DASH && lineStyle != Graphics.LINE_SOLID && lineStyle != Graphics.LINE_DOT  ) {
			throw new IllegalArgumentException();
		}
		this.lineStyle = lineStyle;
	}

	protected static Image createImage(String name) {
		InputStream stream = DesignViewPlugin.class.getResourceAsStream(name);
		Image image = new Image(null, stream);
		try {
			stream.close();
		} catch (IOException ioe) {
		}
		return image;
	}
	
	public void setBidirected(boolean value){
		isBidirected = value;
		//isBidirected = true; // Quick fix to turn all the dependencies bidirected
	}
	
	public boolean isBidirected(){
		return isBidirected;
	}
		
	
	/**
	 * Return a pictogram (small icon) describing this model element.
	 * Children should override this method and return an appropriate Image.
	 * @return a 16x16 Image or null
	 */
	public abstract Image getIcon();
	
	public abstract void setFactid(String newFactid);

	public abstract void setParent(String newParent);
	
	public abstract void setChild(String newChild);
	
	public abstract String getFactid();

	public abstract String getParent();
	
	public abstract String getChild();
	
}
