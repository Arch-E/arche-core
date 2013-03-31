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

public class Book extends Model {
	protected static List newBooks = buildBookList();
	protected static int cursor = 0;
	
	public Book(String title, String authorGivenName, String authorSirName) {
		super(title, authorGivenName, authorSirName);
	}
	
	
	
	
	public static Book newBook() {
		Book newBook = (Book)newBooks.get(cursor);
		cursor = ((cursor + 1) % newBooks.size());
		return newBook;
	}
	
	
	protected static List buildBookList() {
		newBooks = new ArrayList();
		Book[] books = new Book[] {
			new Book("Advanced Java: Idioms, Pitfalls, Styles and Programming Tips", "Chris", "Laffra"),
			new Book("Programming Ruby: A Pragmatic Programmer's Guide", "David", "Thomas"),
			new Book("The Pragmatic Programmer", "Andrew", "Hunt"),
			new Book("Java Virtual Machine", "Jon", "Meyer"),
			new Book("Using Netscape IFC", "Arun", "Rao"),
			new Book("Smalltalk-80", "Adele", "Goldberg"),
			new Book("Cold Mountain", "Charles", "Frazier"),
			new Book("Software Development Using Eiffel", "Richard", "Wiener"),
			new Book("Winter's Heart", "Robert", "Jordan"),
			new Book("Ender's Game", "Orson Scott", "Card"),
			new Book("Castle", "David", "Macaulay"),
			new Book("Cranberry Thanksgiving", "Wende", "Devlin"),
			new Book("The Biggest Bear", "Lynd", "Ward"),
			new Book("The Boxcar Children", "Gertrude Chandler", "Warner"),
			new Book("BASIC Fun with Adventure Games", "Susan Drake", "Lipscomb"),
			new Book("Bridge to Terabithia", "Katherine", "Paterson"),
			new Book("One Renegade Cell", "Robert A.", "Weinberg"),
			new Book("Programming Internet Mail", "David", "Wood"),
			new Book("Refactoring", "Martin", "Fowler"),
			new Book("Effective Java", "Joshua", "Bloch"),
			new Book("Cutting-Edge Java Game Programming", "Neil", "Bartlett"),
			new Book("The C Programming Language", "Brian W.", "Kernighan"),
			new Book("The Design and Analysis of Spatial Data Structures", "Hanan", "Samet"),
			new Book("Object-Oriented Programming", "Brad", "Cox"),
			new Book("Python Essential Reference", "David M.", "Beazley"),
			new Book("The Practical SQL Handbook", "Judith S.", "Bowman"),
			new Book("The Design Patterns Smalltalk Companion", "Sherman R.", "Alpert"),
			new Book("Design Patterns", "Erich", "Gamma"),
			new Book("Gig", "John", "Bowe"),
			new Book("You Can't Be Too Careful", "David Pryce", "Jones"),
			new Book("Go for Beginners", "Kaoru", "Iwamoto"),
			new Book("How to Read a Book", "Mortimer J.", "Adler"),
			new Book("The Message", "Eugene H.", "Peterson"),
			new Book("Beyond Bumper Sticker Ethics", "Steve", "Wilkens"),
			new Book("Life Together", "Dietrich", "Bonhoeffer"),
			new Book("Java 2 Exam Cram", "William", "Brogden")
		};
		
		for (int i = 0; i < books.length; i++) {
			newBooks.add(books[i]);
			
		}
		return newBooks;
	}
	/*
	 * @see Model#accept(ModelVisitorI, Object)
	 */
	public void accept(IModelVisitor visitor, Object passAlongArgument) {
		//visitor.visitBook(this, passAlongArgument);
	}

}
