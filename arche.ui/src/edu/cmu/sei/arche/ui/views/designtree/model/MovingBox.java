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

package edu.cmu.sei.arche.ui.views.designtree.model;

import java.util.ArrayList;
import java.util.List;

public class MovingBox extends Model {
	protected List boxes;
	protected List games;
	protected List books;
	protected List treeNodes;
	
	private static IModelVisitor adder = new Adder();
	private static IModelVisitor remover = new Remover();
	
	public MovingBox() {
		boxes = new ArrayList();
		games = new ArrayList();
		books = new ArrayList();
		treeNodes = new ArrayList();
	}
	
	private static class Adder implements IModelVisitor {

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame)
		 */

		/*
		 * @see ModelVisitorI#visitBook(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitMovingBox(MovingBox)
		 */

		/*
		 * @see ModelVisitorI#visitBoardgame(BoardGame, Object)
		 */
	

		public void visitNode(TreeNode treeNode, Object argument) {
			((TreeNode) argument).addTreeNode(treeNode);
		} 
	}

	private static class Remover implements IModelVisitor {
		
		
		/*
		 * @see ModelVisitorI#visitNode(MovingBox, Object)
		 */
		public void visitNode(TreeNode treeNode, Object argument) {
			((TreeNode) argument).removeTreeNode(treeNode);
		}

	}
	
	public MovingBox(String name) {
		this();
		this.name = name;
	}
	
	public List getBoxes() {
		return boxes;
	}
	
	
	
	public List getBooks() {
		return books;
	}
	
	public void remove(Model toRemove) {
		toRemove.accept(remover, this);
	}
	
	protected void removeBoardGame(BoardGame boardGame) {
		games.remove(boardGame);
		boardGame.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(boardGame);
	}
	
	
	
	public List getTreeNodes() {
		return treeNodes;
	}
	protected void removeTreeNode(TreeNode treeNode) {
		treeNodes.remove(treeNode);
		treeNode.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(treeNode);
	}
	protected void removeBook(Book book) {
		books.remove(book);
	    book.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(book);
	}
	
	protected void removeBox(MovingBox box) {
		boxes.remove(box);
		box.addListener(NullDeltaListener.getSoleInstance());
		fireRemove(box);	
	}

	public void add(Model toAdd) {
		toAdd.accept(adder, this);
	}
	
	public List getGames() {
		return games;
	}
	
	
	/** Answer the total number of items the
	 * receiver contains. */
	public int size() {
		return getBooks().size() + getBoxes().size() + getGames().size() + getTreeNodes().size();
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		//visitor.visitMovingBox(this, passAlongArgument);
	}

}
