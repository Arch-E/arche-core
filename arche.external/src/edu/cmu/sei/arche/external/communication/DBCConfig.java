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

package edu.cmu.sei.arche.external.communication;

/**
 * This class contains the configuration properties for DB connection to the
 * DBMS.
 * 
 * @author Hyunwoo Kim
 */

public class DBCConfig{

    // the DB connection url
    private String SQL_URL;
    // User id to access the DBMS
    private String SQL_User;
    // Password to access the DBMS
    private String SQL_Pwd;
        
	/**
     * @return Returns the sQL_Pwd.
     */
    public String getSQL_Pwd() {
        return SQL_Pwd;
    }
    /**
     * @param pwd The sQL_Pwd to set.
     */
    public void setSQL_Pwd(String pwd) {
        SQL_Pwd = pwd;
    }
    /**
     * @return Returns the sQL_URL.
     */
    public String getSQL_URL() {
        return SQL_URL;
    }
    /**
     * @param sql_url The sQL_URL to set.
     */
    public void setSQL_URL(String sql_url) {
        SQL_URL = sql_url;
    }
    /**
     * @return Returns the sQL_User.
     */
    public String getSQL_User() {
        return SQL_User;
    }
    /**
     * @param user The sQL_User to set.
     */
    public void setSQL_User(String user) {
        SQL_User = user;
    }
}
