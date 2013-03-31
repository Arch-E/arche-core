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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*  
 * $Id: FactOperation.java,v 1.5 2006/04/11 21:11:51 fb Exp $
 * 
 * Copyright (c) 2005, Software Engineering Institute. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0 
 * which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/cpl-v10.html  
 */

/**
 * class description
 * 
 * @author Min
 * @version $Revision: 1.5 $
 */

public class FactOperation {

    /**
     * <code>connection</code> : JDBC connection object
     */
    protected Connection connection = null;

    /**
     * <code>logger</code> Java logger object
     */
    private static Logger logger = Logger.getLogger(FactOperation.class
            .getName());

    /**
     * Create a JDBC connection instance using JDBCConfig property information
     * 
     * @param jdbcConfig
     * @return true if the connection is created successfully.
     */
    public boolean createConnection(JDBCConfig jdbcConfig) {
        String driverName = jdbcConfig.getSQL_Driver();
        String SQL_URL = jdbcConfig.getSQL_URL();
        String SQL_User = jdbcConfig.getSQL_User();
        String SQL_Pwd = jdbcConfig.getSQL_Pwd();
        try {

            Class.forName(driverName);
        } catch (java.lang.ClassNotFoundException e) {
            logger.log(Level.SEVERE, "JDBC Driver not found!");
            return false;
        }
        boolean openIt = true;
        if (connection != null)
        	try {
	        	if (!connection.isClosed())
	        		openIt = false;
	        } catch (SQLException e1) {	
	            logger.log(Level.SEVERE, "Opps couldn't get  "
	                    + Connection.class.getName() + " for the the ");
	        }
        if (openIt){
//	        System.out.println("Have to open the connection");
        	try {
		            connection = DriverManager
		                    .getConnection(SQL_URL, SQL_User, SQL_Pwd);
		            openIt = false;
		        } catch (SQLException e1) {	
		            logger.log(Level.SEVERE, "Opps couldn't get  "
		                    + Connection.class.getName() + " for the the ");
		            return false;	
		        }
        }
        // turn off auto commit and check if the connection is still valid
        // This will check the connection at the MySQL server
    	try {
            connection.setAutoCommit(false);
        } catch (SQLException e1) {	
            // We have no connection at the server
            openIt = true;	
        }
        if (openIt) {
        	// recreate the lost connection again
//        	System.out.println("We recreate the lost connection");
        	try {
	            connection = DriverManager
	                    .getConnection(SQL_URL, SQL_User, SQL_Pwd);
	            connection.setAutoCommit(false);
	        } catch (SQLException e1) {	
	            logger.log(Level.SEVERE, "Opps couldn't get  "
	                    + Connection.class.getName() + " for the the ");
	            return false;	
	        }
        }
        return true;
    }

    public boolean closeConnnection() {
        if (connection == null) {
            return true;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Problem closing the connnection ....");
            return false;
        }

        return true;
    }
    public Connection getConnection() {
        return connection;
    }

}