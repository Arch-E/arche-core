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

package edu.cmu.sei.arche.export;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*  
 * $Id: DeleteOperation.java 368 2006-11-09 15:19:45Z fb $
 * 
 * Copyright (c) 2005, Software Engineering Institute. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html  
 */

/**
 * This class deletes all the fact from database
 * 
 * @author Hyunwoo Kim
 * @version $Revision: 368 $
 */

public class DeleteOperation extends FactOperation {

    private static DeleteOperation instance = new DeleteOperation();

    /**
     * Get the singleton instance of the class
     * 
     * @return
     */
    public static DeleteOperation getInstance() {
        return instance;
    }

    //Set the default constructor to private to prevent others using it to
    // create new object
    private DeleteOperation() {

    }
   
    public boolean clearFactsRelatedToVersion(String verName) {
    	String sql = "";
    	ResultSet rs = null;
    	try {
           java.sql.Statement stmt = connection.createStatement();
       	   Statement rOrphan = connection.createStatement();
           connection.setAutoCommit(false);
           stmt.executeUpdate("DELETE FROM __versions__ WHERE version_name='" + verName + "';");
           connection.commit();
           // Now check if there have been dependent fact sets in the database
           sql = "SELECT v1.ID FROM __versions__ v1 LEFT JOIN __versions__ v2 ON v1.parent=v2.ID WHERE v2.ID is null AND not v1.parent is null";
           rs = rOrphan.executeQuery(sql);
           while (rs.next()){
	            do {
	            	long id = rs.getLong("ID");
	                stmt.executeUpdate("DELETE FROM __versions__ WHERE ID='" + id + "';");
	            } while (rs.next());
	            rs = rOrphan.executeQuery(sql);
           }
           connection.commit();
           connection.setAutoCommit(true);

           return true;

       } catch (SQLException e) {

           e.printStackTrace();
           return false;
       }

   }   
}
