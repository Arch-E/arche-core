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

package edu.cmu.sei.arche.designconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.vo.ViewElementTypeVO;
import edu.cmu.sei.arche.vo.DesignFilterVO;
import edu.cmu.sei.arche.vo.ViewRelationTypeVO;

/**
 * DesignReader class is responsible for parsing a design configuration file, creating an instance
 * of DesignFilterVO class and populating it with data from the design configuration file
 * 
 * @author Neel Mullick
 */
public class DesignReader {

    /** the name of the design configruation file */
    private static final String DESIGN_CONFIG_PATH       = "designconfig.xml";

    /** the path to the design configuration file */
    private String              path;

    /** XML DOM document */
    private Document            doc;

    /** contains XML (string) of a configuration file */
    private InputSource         source;

    /**
     * Hashtable that maps DesignFilterVO objects to Boolean objects. It shall contain as keys all
     * DesignFilterVOs that the DesignReader found as available from the designconfig.xml file. The
     * Boolean value is set to true. It is however dertermined by the user preferences set for the
     * eclipse workbench and is true if that design filter is active for and false otherwise.
     */
    private Hashtable           activeDFs;

    /**
     * Represents the &lt;dc/&gt; tag
     */
    private static final String TAG_DC                   = "dc";

    /**
     * Represents the &lt;designFilters/&gt; tag
     */
    private static final String TAG_DFS                  = "designFilters";

    /**
     * Represents the &lt;designFilter/&gt; tag
     */
    private static final String TAG_DF                   = "designFilter";

    /**
     * Represents the "id" attribute
     */
    private static final String ATTRIBUTE_ID             = "id";

    /**
     * Represents the "name" attribute
     */
    private static final String ATTRIBUTE_NAME           = "name";

    /**
     * Represents the &lt;designElementType/&gt; tag
     */
    private static final String TAG_DESIGN_ELEMENT_TYPE  = "designElementType";

    /**
     * Represents the "factType" attribute
     */
    private static final String ATTRIBUTE_FACT_TYPE      = "factType";

    /**
     * Represents the "nameSlotName" attribute
     */
    private static final String ATTRIBUTE_NAME_SLOT_NAME = "nameSlotName";

    /**
     * Represents the &lt;paramSlots/&gt; tag
     */
    private static final String TAG_PARAM_SLOTS          = "paramSlots";

    /**
     * Represents the &lt;paramSlot/&gt; tag
     */
    private static final String TAG_PARAM_SLOT           = "paramSlot";

    /**
     * Represents the &lt;designRelationType/&gt; tag
     */
    private static final String TAG_DESIGN_RELATION_TYPE = "designRelationType";

    /**
     * Represents the "lhsSlotName" attribute
     */
    private static final String ATTRIBUTE_LHS_SLOT_NAME  = "lhsSlotName";

    /**
     * Represents the "rhsSlotName" attribute
     */
    private static final String ATTRIBUTE_RHS_SLOT_NAME  = "rhsSlotName";

    /**
     * @throws SAXException, IOException
     */
    private DesignReader() throws SAXException, IOException {
        DOMParser domParser = new DOMParser();
        path = String.valueOf(this.getClass().getResource(DESIGN_CONFIG_PATH));
        domParser.parse(path);
        doc = domParser.getDocument();
        activeDFs = new Hashtable();
    }

    /**
     * @param source Contains XML (string) of a configuration file
     * @throws SAXException, IOException
     */
    private DesignReader(InputSource source) throws SAXException, IOException {
        DOMParser domParser = new DOMParser();
        domParser.parse(source);
        doc = domParser.getDocument();
    }

    /**
     * Parses a design configuration file
     * 
     * @return activeDFs Hashtable that maps DesignFilterVO objects to Boolean objects.
     * @throws SAXException, IOException, ArchEException
     */
    public static Hashtable parse() throws SAXException, IOException, ArchEException {
        DesignReader p = new DesignReader();
        return p.parseDF();
    }

    /**
     * Parses a design configuration file
     * 
     * @return activeDFs Hashtable that maps DesignFilterVO objects to Boolean objects.
     * @throws ArchEException
     */
    private Hashtable parseDF() throws ArchEException {
        parseDesignFilter(doc, activeDFs);
        return activeDFs;
    }

    /**
     * Parses the DesignFilter metadata
     * 
     * @param doc DOM representation of the design configuration file
     * @param activeDFs Hashtable that maps DesignFilterVO objects to Boolean objects.
     * @throws ArchEException
     */
    private void parseDesignFilter(Document doc, Hashtable activeDFs) throws ArchEException {
        Attr attribute;
        String id;
        String name;
        boolean dfStatus = true;

        Node dfsNode = doc.getElementsByTagName(TAG_DFS).item(0);
        for (int i = 0; i < dfsNode.getChildNodes().getLength(); i++) {
            Node dfNode = dfsNode.getChildNodes().item(i);
            if (dfNode.getNodeType() == Node.ELEMENT_NODE) {
                if (dfNode.getNodeName() == TAG_DF) {
                    attribute = (Attr) dfNode.getAttributes().getNamedItem(ATTRIBUTE_ID);
                    id = attribute.getNodeValue();
                    attribute = (Attr) dfNode.getAttributes().getNamedItem(ATTRIBUTE_NAME);
                    name = attribute.getNodeValue();
                    DesignFilterVO dfVO = new DesignFilterVO(id, name);
                    parseDesign(dfNode, dfVO);
                    activeDFs.put(dfVO, new Boolean(dfStatus));
                }
            }
        }
    }

    /**
     * Parses a design
     * 
     * @param dfNode &lt;designFilter&gt; node
     * @param dfVo The DesignFilterVo for which the designElements ande designRelations are to be
     *            created.
     * @throws ArchEException
     */
    private void parseDesign(Node dfNode, DesignFilterVO dfVO) throws ArchEException {

        for (int i = 0; i < dfNode.getChildNodes().getLength(); i++) {
            Node childNode = dfNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_DESIGN_ELEMENT_TYPE) {
                    ViewElementTypeVO desElementType = parseDesignElementType(childNode);
                    dfVO.getDesignElementTypes().add(desElementType);
                }
                if (childNode.getNodeName() == TAG_DESIGN_RELATION_TYPE) {
                    ViewRelationTypeVO desRelationType = parseDesignRelationType(childNode);
                    dfVO.getDesignRelationTypes().add(desRelationType);
                }
            }
        }
    }

    /**
     * Parses a Design Element Type
     * 
     * @param node &lt;designElementType&gt;node
     * @return DesignElementTypeVO
     */
    private ViewElementTypeVO parseDesignElementType(Node node) {
        Attr attribute;
        String factType;
        String nameSlotName;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_FACT_TYPE);
        factType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME_SLOT_NAME);
        nameSlotName = attribute.getNodeValue();
        ViewElementTypeVO det = new ViewElementTypeVO(factType, nameSlotName,null,null, null);
        ArrayList paramSlotNames = new ArrayList();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PARAM_SLOTS) {
                parseParamSlots(node.getChildNodes().item(i), paramSlotNames);
            }
        }
        det.setParamSlotNames((String[]) paramSlotNames.toArray(new String[paramSlotNames.size()]));
        String[] paramSlotDisplays = new String[paramSlotNames.size()];
        for(int i=0; i<paramSlotDisplays.length; i++)
        	paramSlotDisplays[i] = "true";        	
        det.setParamSlotDisplays(paramSlotDisplays);        
        return det;
    }

    /**
     * Parses a DEsign Relation Type
     * 
     * @param node &lt;designRelationType&gt;node
     * @return DesignRelationTypeVO
     */
    private ViewRelationTypeVO parseDesignRelationType(Node node) {
        Attr attribute;
        String factType;
        String lhsSlotName;
        String rhsSlotName;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_FACT_TYPE);
        factType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_LHS_SLOT_NAME);
        lhsSlotName = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_RHS_SLOT_NAME);
        rhsSlotName = attribute.getNodeValue();
        ViewRelationTypeVO drt = new ViewRelationTypeVO(factType, lhsSlotName, rhsSlotName,
                null,null,null);
        ArrayList paramSlotNames = new ArrayList();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PARAM_SLOTS) {
                parseParamSlots(node.getChildNodes().item(i), paramSlotNames);
            }
        }
        drt.setParamSlotNames((String[]) paramSlotNames.toArray(new String[paramSlotNames.size()]));
        String[] paramSlotDisplays = new String[paramSlotNames.size()];
        for(int i=0; i<paramSlotDisplays.length; i++)
        	paramSlotDisplays[i] = "true";        	
        drt.setParamSlotDisplays(paramSlotDisplays);
        return drt;
    }

    /**
     * Parses ParamSlots
     * 
     * @param node &lt;paramSlots&gt;node
     * @param list List of paramSlots to be filled
     */
    private void parseParamSlots(Node node, ArrayList list) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PARAM_SLOT) {
                list.add(parseParamSlot(node.getChildNodes().item(i)));
            }
        }
    }

    /**
     * Parses a paramSlot
     * 
     * @param node &lt;paramSlot&gt;node
     */
    private String parseParamSlot(Node node) {
        Attr attribute;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        return attribute.getNodeValue();
    }

}