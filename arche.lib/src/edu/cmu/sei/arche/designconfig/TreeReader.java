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
import edu.cmu.sei.arche.vo.TreeElementTypeVO;
import edu.cmu.sei.arche.vo.TreeVO;

/**
 * DesignReader class is responsible for parsing a design configuration file, creating an instance
 * of DesignFilterVO class and populating it with data from the design configuration file
 * 
 * @author Neel Mullick
 */
public class TreeReader {

    /** the name of the design configruation file */
    private static final String TREE_CONFIG_PATH       = "treeconfig.xml";

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
    private Hashtable           activeTrees;

    /**
     * Represents the &lt;dc/&gt; tag
     */
    private static final String TAG_TC                   = "tc";

    /**
     * Represents the &lt;trees/&gt; tag
     */
    private static final String TAG_TREES                = "trees";
    
    /**
     * Represents the &lt;tree/&gt; tag
     */
    private static final String TAG_TREE                  = "tree";

    /**
     * Represents the &lt;designFilter/&gt; tag
     */
    private static final String TAG_TREE_ELEMENT          = "treeElementType";

    /**
     * Represents the "id" attribute
     */
    private static final String ATTRIBUTE_ID              = "id";

    /**
     * Represents the "name" attribute
     */
    private static final String ATTRIBUTE_NAME            = "name";

  

    /**
     * Represents the "factType" attribute
     */
    private static final String ATTRIBUTE_FACT_TYPE       = "factType";

    /**
     * Represents the "factType" attribute
     */
    private static final String ATTRIBUTE_ELEMENT_FACT_TYPE       = "factType";
    
    /**
     * Represents the "childFactType" attribute
     */
    private static final String ATTRIBUTE_CHlLD_FACT_TYPE      = "childFactType";
   
    /**
     * Represents the "parentFactType" attribute
     */
    private static final String ATTRIBUTE_PARENT_FACT_TYPE      = "parentFactType";
    
    /**
     * Represents the "relationFactType" attribute
     */
    private static final String ATTRIBUTE_RELATION_FACT_TYPE      = "relationFactType";
    
    /**
     * Represents the "parentSlotName" attribute
     */
    private static final String ATTRIBUTE_PARENT_SLOT_NAME      = "parentSlotName";
    /**
     * Represents the "childSlotName" attribute
     */
    private static final String ATTRIBUTE_CHILD_SLOT_NAME      = "childSlotName";
    
    private static final String ATTRIBUTE_SLOT_NAME_TO_DISPLAY       = "slotNameToDisplay";
    /**
     * @throws SAXException, IOException
     */
    private TreeReader() throws SAXException, IOException {
        DOMParser domParser = new DOMParser();
        path = String.valueOf(this.getClass().getResource(TREE_CONFIG_PATH));
        domParser.parse(path);
        doc = domParser.getDocument();
        activeTrees = new Hashtable();
    }

    /**
     * @param source Contains XML (string) of a configuration file
     * @throws SAXException, IOException
     */
    private TreeReader(InputSource source) throws SAXException, IOException {
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
       TreeReader p = new TreeReader();
        return p.parseTR();
    }

    /**
     * Parses a tree configuration file
     * 
     * @return activeDFs Hashtable that maps DesignFilterVO objects to Boolean objects.
     * @throws ArchEException
     */
    private Hashtable parseTR() throws ArchEException {
        parseTreeFilter(doc, activeTrees);
        return activeTrees;
    }

    /**
     * Parses the TreeFilter metadata
     * 
     * @param doc DOM representation of the tree configuration file
     * @param activeDFs Hashtable that maps Tree VO objects to Boolean objects.
     * @throws ArchEException
     */
    private void parseTreeFilter(Document doc, Hashtable activeTrees) throws ArchEException {
        Attr attribute;
        String id;
        String name;
        String factType;
        boolean trStatus = true;

        Node dfsNode = doc.getElementsByTagName(TAG_TREES).item(0);
        for (int i = 0; i < dfsNode.getChildNodes().getLength(); i++) {
            Node trNode = dfsNode.getChildNodes().item(i);
            if (trNode.getNodeType() == Node.ELEMENT_NODE) {
                if (trNode.getNodeName() == TAG_TREE) {
                    attribute = (Attr) trNode.getAttributes().getNamedItem(ATTRIBUTE_ID);
                    id = attribute.getNodeValue();
                    attribute = (Attr) trNode.getAttributes().getNamedItem(ATTRIBUTE_NAME);
                    name = attribute.getNodeValue();
                    attribute = (Attr) trNode.getAttributes().getNamedItem(ATTRIBUTE_FACT_TYPE);
                    factType = attribute.getNodeValue();
                    TreeVO trVO = new TreeVO(id, name, factType);
                    parseTree(trNode, trVO);
                    activeTrees.put(trVO, new Boolean(trStatus));
                }
            }
        }
    }

    /**
     * Parses a design
     * 
     * @param trNode &lt;Tree&gt; node
     * @param trVo The TreeVo for which the 
     *          
     * @throws ArchEException
     */
    private void parseTree(Node trNode, TreeVO trVO) throws ArchEException {

        for (int i = 0; i < trNode.getChildNodes().getLength(); i++) {
            Node childNode = trNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_TREE_ELEMENT) {
                    TreeElementTypeVO treeElementType = parseTreeElementType(childNode);
                    trVO.getTreeElementTypes().add(treeElementType);
                }
                
            }
        }
    }

    /**
     * Parses a Tree Element Type
     * 
     * @param node &lt;TreeElementType&gt;node
     * @return TreeElementTypeVO
     */
    private TreeElementTypeVO parseTreeElementType(Node node) {
        Attr attribute;
        String factType;
        String parentFactType;
        String parentSlotName;
        String childFactType;
        String childSlotName;
        String relationFactType;
        String slotNameToDisplay;
        String name;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_ELEMENT_FACT_TYPE);
        factType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_PARENT_FACT_TYPE);
        parentFactType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_PARENT_SLOT_NAME);
        parentSlotName = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_CHlLD_FACT_TYPE);
        childFactType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_CHILD_SLOT_NAME);
        childSlotName = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_RELATION_FACT_TYPE);
        relationFactType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        name = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_SLOT_NAME_TO_DISPLAY);
        slotNameToDisplay = attribute.getNodeValue();
        TreeElementTypeVO tet = new  TreeElementTypeVO(factType,parentFactType, parentSlotName,
                childFactType, childSlotName, relationFactType, name, slotNameToDisplay);
       
        return tet;
    }

  

}