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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*  
 * $Id: SaveOperation.java 368 2006-11-09 15:19:45Z fb $
 * 
 * Copyright (c) 2005, Software Engineering Institute. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html  
 */

/**
 * This class establishes the database connection using jdbc, and saves, clears
 * and restores different type of facts into/from database.
 * 
 * @author Hyunwoo Kim
 * @version $Revision: 368 $
 */

public class ImportOperation extends FactOperation {

    private static ImportOperation instance = new ImportOperation();
    private Statement stmt = null;
    private FileReader sqlfile = null;


    /**
     * Get the singleton instance of the class
     * 
     * @return
     */
    public static ImportOperation getInstance() {
        return instance;
    }

    //Set the default constructor to private to prevent others using it to
    // create new object
    private ImportOperation() {

    }

    /**
     * check the version conflict
     * 
     * @param verName :
     *            the version
     */

    public boolean checkVersion(String verName) {
        int count;
    	try {
            if (stmt == null)
            	stmt = connection.createStatement();

            //connection.
            String tablename = "__versions__";
            ResultSet rs = stmt.executeQuery("SELECT count(*) FROM "
                    + tablename + " WHERE version_name = \'" + verName + "\'");
            if (rs.next()) {
            	count =rs.getInt(1);
                if (count > 0)
                    return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
    /**
     * create a new version record
     * 
     * @param verName :
     *            the version
     */
    
    public long newVersion(String factSetName, String verName, String storageType) {
    	try {
            if (stmt == null)
            	stmt = connection.createStatement();

            //connection.
            String tablename = "__versions__";
            stmt.executeUpdate("INSERT INTO " + tablename + " (version_name, storage_type, factSet) VALUES(\'"
                    + verName + "', '" + storageType + "', '" + factSetName + "');");
		    ResultSet key = stmt.getGeneratedKeys();
		    if(key.next()) {
		    	return key.getInt(1);
		    }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    /**
     * Writes all the data of all the facts collected into their tables
     * 
     */
   public void writeRecords(long versionID) throws IOException {
	    if(sqlfile == null)
	    	return;
	    
	    insertRecordFromFile(versionID,sqlfile);
   }
   
	/**
    * Inserts records from a given SQL file
    * 
    * @return true if insert was successfully
    */
   public boolean insertRecordFromFile(long versionID, FileReader file) throws IOException {
       boolean stmtOk = true;    	
       String sql = "";
       
       BufferedReader in = new BufferedReader(file);      
       String ret = null;                
       while(true){
           ret = in.readLine();
           if(ret != null){
	           ret = ret.replaceAll("'####'", Long.toString(versionID));
	           sql += ret;        	
	           if(ret.endsWith(";")){
	        	   // execute this insert sql statement
	               try {
	                   stmt.executeUpdate(sql);
	               } catch (SQLException e) {
	            	   stmtOk = false;
	               }
	               if (!stmtOk){
	            	   try {
	                       stmt = connection.createStatement();
	                       stmt.executeUpdate(sql);
	                   } catch (SQLException e) {
	                	   System.out.println("SQL: " + sql);
	                   		e.printStackTrace();
	                       return false;
	                   }
	               }
	               sql = "";
	           }
           }
           else
        	   break;
       }
       return true;
   }
   
   
   /**
    * Creates a SQL file to export design
    * 
    */
   public void openSQLFile(String sqlFilePath) throws IOException {	   
    	   if(sqlfile != null){
    		   sqlfile.close();
    		   sqlfile = null;
    	   }
    	   sqlfile = new FileReader(sqlFilePath);    	  
   }

   /**
    * Close a SQL file if it exists
    * 
    */
   public void closeSQLFile() throws IOException {
	   if(sqlfile != null){
		   sqlfile.close();
		   sqlfile = null;
	   }
   }   
   /**
    * Starts the transaction for inserting the records in the database
    * 
    * @return true if request was successfully
    */
   public boolean startTransaction(){
		boolean stmtOk = true;
		String sql = "START TRANSACTION;";
	  try {
		if (stmt == null)
			stmt = connection.createStatement();
		stmt.executeUpdate(sql);
	  } catch (SQLException e) {
	  	stmtOk = false;
	  }
	  if (!stmtOk)
	  	try {
	          stmt = connection.createStatement();
	          stmt.executeUpdate(sql);
	      } catch (SQLException e) {
	          // 
			e.printStackTrace();
			return false;
	  }
	  return true;
   }

   /**
    * Stops the transaction and commits all changes to the database
    * 
    * @return true if request was successfully
    */
   public boolean stopTransaction(){
		boolean stmtOk = true;
		String sql = "COMMIT;";
	  try {
		if (stmt == null)
			stmt = connection.createStatement();
		stmt.executeUpdate(sql);
//		Thread.sleep(5000);
	  } catch (SQLException e) {
	  	stmtOk = false;
//	  } catch (InterruptedException e) {
//	 	stmtOk = false;
      }
	  if (!stmtOk)
	  	try {
	          stmt = connection.createStatement();
	          stmt.executeUpdate(sql);
//   			  Thread.sleep(5000);
	      } catch (SQLException e) {
			e.printStackTrace();
			return false;
//	    } catch (InterruptedException e) {
//		    e.printStackTrace();
//			return false;
		}
	      
	  return true;
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
