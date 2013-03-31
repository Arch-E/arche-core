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

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/** 
 * This class loads the properties of XmlBlaster connection from the XML file.
 * 
 * @author Hyunwoo Kim
 */
public class XmlBlasterPropertyLoader extends DefaultHandler {

    private XmlBlasterConfig config = null;
    
    private int curElementType = -1;
    
    private static final int ELEMENT_HOSTNAME = 1;
    private static final int ELEMENT_PORT = 2;
    private static final int ELEMENT_SESSIONNAME = 3;
    private static final int ELEMENT_PASSWORD= 4;
    
    public XmlBlasterPropertyLoader(XmlBlasterConfig config) {
        super();
        this.config = config;
    }
        
    public void startElement( String uri, String localName, String qName,
		      Attributes attribs ) {
        if (qName.equals("HostName")) {
            curElementType = ELEMENT_HOSTNAME;
        } else if (qName.equals("Port")) {
            curElementType = ELEMENT_PORT;
        } else if (qName.equals("SessionName")) {
            curElementType = ELEMENT_SESSIONNAME;
        } else if (qName.equals("Password")) {
            curElementType = ELEMENT_PASSWORD;
        }
    }
    
    public void characters( char[] data, int start, int length ) {
        switch ( curElementType ) {
        case ELEMENT_HOSTNAME:
            config.setHostName(new String(data, start, length));
            break;
        case ELEMENT_PORT:
            config.setPort(new String(data, start, length));
            break;
        case ELEMENT_SESSIONNAME:
            config.setSessionName(new String(data, start, length));
            break;
        case ELEMENT_PASSWORD:
            config.setPassword(new String(data, start, length));
            break;
        }
    } 
    
    public void endElement( String uri, String localName, String qName ) {
        curElementType = -1;
    }
	
	
}
