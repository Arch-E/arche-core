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

package edu.cmu.sei.arche.rfconfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import edu.cmu.sei.arche.ArchEException;
import edu.cmu.sei.arche.vo.ViewElementTypeVO;
import edu.cmu.sei.arche.vo.ViewRelationTypeVO;
import edu.cmu.sei.arche.vo.ModelElementTypeVO;
import edu.cmu.sei.arche.vo.ModelRelationTypeVO;
import edu.cmu.sei.arche.vo.OperandsVO;
import edu.cmu.sei.arche.vo.ParameterTypeVO;
import edu.cmu.sei.arche.vo.PartOptionsVO;
import edu.cmu.sei.arche.vo.ReasoningFrameworkVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.ScenarioPartMetadataVO;
import edu.cmu.sei.arche.vo.ScenarioTypeVO;

/**
 * ConfigReader class is responsible for parsing an RF configuration file, creating an instance of
 * ReasoningFrameworkVO class and populating it with data from the configuration file
 * 
 * @author Alexander Berendeyev
 */
public class ConfigReader {

    //path to the configuration file
    private String             path                          = "";

    //XML DOM document
    private Document           doc;

    //contains XML (string) of a configuration file
    private InputSource        source;

    /**
     * Represents the &lt;rf/&gt; tag
     */
    public static final String TAG_RF                        = "rf";

    /**
     * Represents the &lt;part/&gt; tag
     */
    public static final String TAG_PART                      = "part";

    /**
     * Represents the &lt;parts/&gt; tag
     */
    public static final String TAG_PARTS                     = "parts";

    /**
     * Represents the &lt;types/&gt; tag
     */
    public static final String TAG_TYPES                     = "types";

    /**
     * Represents the &lt;type/&gt; tag
     */
    public static final String TAG_TYPE                      = "type";

    /**
     * Represents the &lt;units/&gt; tag
     */
    public static final String TAG_UNITS                     = "units";

    /**
     * Represents the &lt;unit/&gt; tag
     */
    public static final String TAG_UNIT                      = "unit";

    /**
     * Represents the &lt;lhs/&gt; tag
     */
    public static final String TAG_LHS                       = "lhs";

    /**
     * Represents the &lt;rhs/&gt; tag
     */
    public static final String TAG_RHS                       = "rhs";

    /**
     * Represents the &lt;direction/&gt; tag
     */
    public static final String TAG_DIRECTION                 = "direction";

    /**
     * Represents the &lt;operands/&gt; tag
     */
    public static final String TAG_OPERANDS                  = "operands";

    /**
     * Represents the &lt;scenarioType/&gt; tag
     */
    public static final String TAG_SCENARIO_TYPE             = "scenarioType";

    /**
     * Represents the &lt;scenarioTypes/&gt; tag
     */
    public static final String TAG_SCENARIO_TYPES            = "scenarioTypes";

    /**
     * Represents the &lt;responsibilityStructure/&gt; tag
     */
    public static final String TAG_RESPONSIBILITY_STRUCTURE  = "responsibilityStructure";
        
    /**
     * Represents the &lt;relationshipType/&gt; tag
     */
    public static final String TAG_RELATIONSHIP_TYPE         = "relationshipType";

    /**
     * Represents the &lt;relationshipTypes/&gt; tag
     */
    public static final String TAG_RELATIONSHIP_TYPES        = "relationshipTypes";

    /**
     * Represents the &lt;parameterTypes/&gt; tag
     */
    public static final String TAG_PARAMETER_TYPES           = "parameterTypes";

    /**
     * Represents the &lt;parameterType/&gt; tag
     */
    public static final String TAG_PARAMETER_TYPE            = "parameterType";

    /**
     * Represents the &lt;parameter/&gt; tag
     */
    public static final String TAG_PARAMETER            = "parameter";
    
    /**
     * Represents the &lt;responsibilityParameters/&gt; tag
     */
    public static final String TAG_RESPONSIBILITY_PARAMETERS = "responsibilityParameters";

    /**
     * Represents the &lt;model/&gt; tag
     */
    public static final String TAG_MODEL                     = "model";

    /**
     * Represents the &lt;modelElementType/&gt; tag
     */
    public static final String TAG_MODEL_ELEMENT_TYPE        = "modelElementType";

    /**
     * Represents the &lt;paramSlots/&gt; tag
     */
    public static final String TAG_PARAM_SLOTS               = "paramSlots";

    /**
     * Represents the &lt;paramSlot/&gt; tag
     */
    public static final String TAG_PARAM_SLOT                = "paramSlot";

    /**
     * Represents the &lt;modelRelationType/&gt; tag
     */
    public static final String TAG_MODEL_RELATION_TYPE       = "modelRelationType";

    /**
     * Represents the "id" attribute
     */
    public static final String ATTRIBUTE_ID                  = "id";

    /**
     * Represents the "name" attribute
     */
    public static final String ATTRIBUTE_NAME                = "name";

    /**
     * Represents the "version" attribute
     */
    public static final String ATTRIBUTE_VERSION             = "version";

    /**
     * Represents the "tID" attribute
     */
    public static final String ATTRIBUTE_TYPE_ID             = "tID";

    /**
     * Represents the "desc" attribute
     */
    public static final String ATTRIBUTE_DESCRIPTION         = "desc";

    /**
     * Represents the "recursive" attribute
     */
    public static final String ATTRIBUTE_RECURSIVE           = "recursive";

    /**
     * Represents the "partType" attribute
     */
    public static final String ATTRIBUTE_PART_TYPE           = "partType";

    /**
     * Represents the "dataType" attribute
     */
    public static final String ATTRIBUTE_DATA_TYPE           = "dataType";

    /**
     * Represents the "defaultText" attribute
     */
    public static final String ATTRIBUTE_DEFAULT_TEXT        = "defaultText";

    /**
     * Represents the "defaultValue" attribute
     */
    public static final String ATTRIBUTE_DEFAULT_VALUE       = "defaultValue";

    /**
     * Represents the "defaultTypeId" attribute
     */
    public static final String ATTRIBUTE_DEFAULT_TYPE_ID     = "defaultTypeId";

    /**
     * Represents the "defaultUnitId" attribute
     */
    public static final String ATTRIBUTE_DEFAULT_UNIT_ID     = "defaultUnitId";

    /**
     * Represents the "factType" attribute
     */
    public static final String ATTRIBUTE_FACT_TYPE           = "factType";

    /**
     * Represents the "nameSlotName" attribute
     */
    public static final String ATTRIBUTE_NAME_SLOT_NAME      = "nameSlotName";

    /**
     * Represents the "lhsSlotName" attribute
     */
    public static final String ATTRIBUTE_LHS_SLOT_NAME       = "lhsSlotName";

    /**
     * Represents the "rhsSlotName" attribute
     */
    public static final String ATTRIBUTE_RHS_SLOT_NAME       = "rhsSlotName";

    /**
     * One of the possible values of the &lt;direction&gt; tag
     */
    public static final String BIDIRECTIONAL                 = "bidirectional";

    /**
     * One of the possible values of the &lt;direction&gt; tag
     */
    public static final String UNIDIRECTIONAL                = "unidirectional";

    /**
     * One of the possible values of the dataType attribute
     */
    public static final String DOUBLE                        = "double";

    /**
     * One of the possible values of the dataType attribute
     */
    public static final String INTEGER                        = "integer";
    
    /**
     * One of the possible values of the dataType attribute
     */
    public static final String STRING                        = "string";

    /**
     * One of the possible values of the dataType attribute
     */
    public static final String BOOLEAN                       = "boolean";

    /**
     * One of the possible values of the partType attribute
     */
    public static final String SOURCE_OF_STIMULUS            = "SOURCE_OF_STIMULUS";

    /**
     * One of the possible values of the partType attribute
     */
    public static final String STIMULUS                      = "STIMULUS";

    /**
     * One of the possible values of the partType attribute
     */
    public static final String ENVIRONMENT                   = "ENVIRONMENT";

    /**
     * One of the possible values of the partType attribute
     */
    public static final String ARTIFACT                      = "ARTIFACT";

    /**
     * One of the possible values of the partType attribute
     */
    public static final String RESPONSE                      = "RESPONSE";

    /**
     * One of the possible values of the partType attribute
     */
    public static final String RESPONSE_MEASURE              = "RESPONSE_MEASURE";
    
       
    /**
     * Represents the &lt;view/&gt; tag
     */
    private static final String TAG_VIEW                   = "view";

    /**
     * Represents the &lt;viewElementType/&gt; tag
     */
    private static final String TAG_VIEW_ELEMENT_TYPE    = "viewElementType";
           
    /**
     * Represents the &lt;property/&gt; tag
     */
    private static final String TAG_PROPERTY        = "property";

    /**
     * Represents the &lt;viewRelationType/&gt; tag
     */
    private static final String TAG_VIEW_RELATION_TYPE = "viewRelationType";

    /**
     * Represents the "namePropertyName" attribute
     */
    private static final String ATTRIBUTE_NAME_PROPERTYNAME    = "namePropertyName";

    /**
     * Represents the "lhsProperyName" attribute
     */
    private static final String ATTRIBUTE_LHS_PROPERTYNAME    = "lhsProperyName";

    /**
     * Represents the "rhsProperyName" attribute
     */
    private static final String ATTRIBUTE_RHS_PROPERTYNAME    = "rhsProperyName";
    
    /**
     * Represents the "type" attribute
     */
    private static final String ATTRIBUTE_TYPE  = "type";    

    /**
     * Represents the "display" attribute
     */
    private static final String ATTRIBUTE_DISPLAY  = "display";
    
    protected ReasoningFrameworkVO rf = null;

    /**
     * @param path Path to the RF configuration file
     * @throws SAXException, IOException
     */
    private ConfigReader(String path) throws SAXException, IOException {
        this.path = path;
        DOMParser domParser = new DOMParser();
        domParser.parse(path);
        doc = domParser.getDocument();
    }

    /**
     * @param source Contains XML (string) of a configuration file
     * @throws SAXException, IOException
     */
    private ConfigReader(InputSource source) throws SAXException, IOException {
        DOMParser domParser = new DOMParser();
        domParser.parse(source);
        doc = domParser.getDocument();
    }

    /**
     * Parses an RF configuration file
     * 
     * @param path Path to the RF configuration file
     * @return ReasoningFrameworkVO
     * @throws SAXException, IOException, ArchEException
     */
    public static ReasoningFrameworkVO parse(String path) throws SAXException, IOException,
            ArchEException {
        ConfigReader p = new ConfigReader(path);
        return p.parse();
    }

    /**
     * Reserved for future use
     * 
     * @param path Path to the RF configuration file
     * @param doc DOM representation of the RF configuration file
     * @return ReasoningFrameworkVO
     * @throws SAXException, IOException, ArchEException
     */
    public static ReasoningFrameworkVO parse(String path, Document doc) throws SAXException,
            IOException, ArchEException {
        ConfigReader p = new ConfigReader(path);
        doc = p.doc;
        return p.parse();
    }

    /**
     * Tries to parse an RF configuration file
     * 
     * @param source Contains XML (string) of a configuration file
     * @return ReasoningFrameworkVO
     * @throws SAXException, IOException, ArchEException
     */
    public static ReasoningFrameworkVO tryParse(InputSource source) throws SAXException,
            IOException, ArchEException {
        ConfigReader p = new ConfigReader(source);
        return p.parse();
    }

    /**
     * Parses an RF configuration file
     * 
     * @return ReasoningFrameworkVO
     * @throws ArchEException
     */
    private ReasoningFrameworkVO parse() throws ArchEException {
        ReasoningFrameworkVO rf = new ReasoningFrameworkVO();
        parseRF(doc, rf);
        return rf;
    }

    /**
     * Parses the RF metadata
     * 
     * @param doc DOM representation of the RF configuration file
     * @param rf Instance of the ReasoningFrameworkVO class
     * @throws ArchEException
     */
    private void parseRF(Document doc, ReasoningFrameworkVO rf) throws ArchEException {
        Attr attribute;
        String id;
        String name;
        String version;
        Node rfNode = doc.getElementsByTagName(TAG_RF).item(0);
        attribute = (Attr) rfNode.getAttributes().getNamedItem(ATTRIBUTE_ID);
        id = attribute.getNodeValue();
        attribute = (Attr) rfNode.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        name = attribute.getNodeValue();
        attribute = (Attr) rfNode.getAttributes().getNamedItem(ATTRIBUTE_VERSION);
        version = attribute.getNodeValue();
        rf.setId(id);
        rf.setName(name);
        rf.setVersion(version);
        this.rf = rf;
        print(id);
        print(name);
        print(version);
        parseScenarioTypes(rfNode, rf.getScenarioTypes());
        parseResponsibilityStructure(rfNode, rf);
        parseModel(rfNode, rf);
        parseView(rfNode, rf);
    }
    
    private void parseResponsibilityStructure(Node node, ReasoningFrameworkVO rf) throws ArchEException {
        Node responsibilityStructureNode = node.getOwnerDocument().getElementsByTagName(TAG_RESPONSIBILITY_STRUCTURE).item(0);        	
        if(responsibilityStructureNode == null) return;
        for (int i = 0; i < responsibilityStructureNode.getChildNodes().getLength(); i++) {
            Node childNode = responsibilityStructureNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_PARAMETER_TYPES) {
                    parseParameterTypes(childNode, rf.getParamTypes());
                }
            }
        }
        
        Hashtable ht = CreateHashtable(rf.getParamTypes());

        for (int i = 0; i < responsibilityStructureNode.getChildNodes().getLength(); i++) {
            Node childNode = responsibilityStructureNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_RELATIONSHIP_TYPES) {
                    parseRelationshipTypes(childNode, rf.getRelationshipTypes(), ht);
                }
                if (childNode.getNodeName() == TAG_RESPONSIBILITY_PARAMETERS) {
                    parseResponsibilityParameters(childNode, rf.getRespParamTypes(), ht);    	
                }
            }
        }
        
//        parseParameterTypes(node, rf.getParamTypes());
//        parseResponsibilityParameters(node, rf.getRespParamTypes(), ht);    	
//        parseRelationshipTypes(node, rf.getRelationshipTypes(), ht);
    }
    
    private void parseView(Node node, ReasoningFrameworkVO rf) throws ArchEException {
        Node viewNode = node.getOwnerDocument().getElementsByTagName(TAG_VIEW).item(0);
        for (int i = 0; i < viewNode.getChildNodes().getLength(); i++) {
            Node childNode = viewNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_VIEW_ELEMENT_TYPE) {
                    rf.getViewElementTypes().add(parseViewElementType(childNode));
                }
                if (childNode.getNodeName() == TAG_VIEW_RELATION_TYPE) {
                    rf.getViewRelationTypes().add(parseViewRelationType(childNode));
                }
            }
        }
    }

    private ViewRelationTypeVO parseViewRelationType(Node node) {
        Attr attribute;
        String typeID;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        typeID = attribute.getNodeValue();
        
        String lhsPropertyName;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_LHS_PROPERTYNAME);
        lhsPropertyName = attribute.getNodeValue();

        String rhsPropertyName;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_RHS_PROPERTYNAME);
        rhsPropertyName = attribute.getNodeValue();
        
        // Create a fact type for this relation
        String factType = null;
        String lower = typeID.toLowerCase();
        if(lower.startsWith("design."))
        	factType = "Design::" + typeID.substring(typeID.indexOf(".")+1, typeID.length());
        else
        	factType = rf.getId() + "::" + typeID;         
        
        ViewRelationTypeVO vrt = new ViewRelationTypeVO(factType,
        			lhsPropertyName,rhsPropertyName,null,null,null);
        
        List paramSlotNames = new ArrayList();
        List paramSlotTypes = new ArrayList();
        List paramSlotDisplays = new ArrayList();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PROPERTY) {
                parseProperty(node.getChildNodes().item(i), paramSlotNames,paramSlotTypes,paramSlotDisplays);
            }
        }
        vrt.setParamSlotNames((String[]) paramSlotNames.toArray(new String[paramSlotNames.size()]));
        vrt.setParamSlotTypes((String[]) paramSlotTypes.toArray(new String[paramSlotTypes.size()]));
        vrt.setParamSlotDisplays((String[]) paramSlotDisplays.toArray(new String[paramSlotDisplays.size()]));
        return vrt;
    }
    
    private void parseProperty(Node node, List paramSlotNames, List paramSlotTypes, List paramSlotDisplays) {
        Attr attribute;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        paramSlotNames.add(attribute.getNodeValue());
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE);
        paramSlotTypes.add(attribute.getNodeValue());
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DISPLAY);
        paramSlotDisplays.add(attribute.getNodeValue());
    }

	private ViewElementTypeVO parseViewElementType(Node node) {
        Attr attribute;
        String typeID;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        typeID = attribute.getNodeValue();
        
        String nameProperty;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME_PROPERTYNAME);
        nameProperty = attribute.getNodeValue();
        
        // Create a fact type for this relation
        String factType = null;
        String lower = typeID.toLowerCase();
        if(lower.startsWith("design."))
        	factType = "Design::" + typeID.substring(typeID.indexOf(".")+1, typeID.length());
        else
        	factType = rf.getId() + "::" + typeID;         
        ViewElementTypeVO vet = new ViewElementTypeVO(factType,nameProperty,null,null,null);
        
        List paramSlotNames = new ArrayList();
        List paramSlotTypes = new ArrayList();
        List paramSlotDisplays = new ArrayList();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PROPERTY) {
                parseProperty(node.getChildNodes().item(i), paramSlotNames,paramSlotTypes,paramSlotDisplays);
            }
        }
        vet.setParamSlotNames((String[]) paramSlotNames.toArray(new String[paramSlotNames.size()]));
        vet.setParamSlotTypes((String[]) paramSlotTypes.toArray(new String[paramSlotTypes.size()]));
        vet.setParamSlotDisplays((String[]) paramSlotDisplays.toArray(new String[paramSlotDisplays.size()]));
        return vet;
	}

	/**
     * Helper method that creates a Hashtable for lookup of parameter types by ID
     * 
     * @param paramTypes List of parameter types
     * @return Hashtable
     */
    private Hashtable CreateHashtable(ArrayList paramTypes) {
        Hashtable ht = new Hashtable();
        for (int i = 0; i < paramTypes.size(); i++) {
            ht.put(((ParameterTypeVO) paramTypes.get(i)).getId(), paramTypes.get(i));
        }
        return ht;
    }

    /**
     * Parses parameters associated with responsibilities
     * 
     * @param node &lt;responsibilityParameters&gt; node
     * @param pt Contains responsibility parameter types
     * @param ht Hashtable for lookup of parameter types by ID
     */
    private void parseResponsibilityParameters(Node responsibilityParametersNode, ArrayList pt, Hashtable ht) {
        Attr attribute;
        String id;
//        print("---parseResponsibilityParameters----");
//        Node responsibilityParametersNode = node.getOwnerDocument()
//                .getElementsByTagName(TAG_RESPONSIBILITY_PARAMETERS).item(0);
        for (int i = 0; i < responsibilityParametersNode.getChildNodes().getLength(); i++) {
            Node childNode = responsibilityParametersNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE 
            			&& childNode.getNodeName() == TAG_PARAMETER) {
                attribute = (Attr) childNode.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
                id = attribute.getNodeValue();
                String fullId = rf.getId() + "::" + id;
                print(fullId);
                ParameterTypeVO ptVO = (ParameterTypeVO) ht.get(fullId);
                //throw an exception if there is no corresponding parameter type
                if (ptVO == null) {
                    throw new NullPointerException(
                            "Invalid Parameter Type ID associated with Responsibilities.");
                }
                pt.add(ptVO);
            }
        }
        print("---End parseResponsibilityParameters----");
    }

    /**
     * Parses relationship types
     * 
     * @param node &lt;relationshipTypes&gt;node
     * @param rt Contains relationship types
     * @param ht Hashtable for lookup of parameter types by ID
     * @throws ArchEException
     */
    private void parseRelationshipTypes(Node relationshipTypesNode, ArrayList rt, Hashtable ht)
            throws ArchEException {
//        Node relationshipTypesNode = node.getOwnerDocument()
//                .getElementsByTagName(TAG_RELATIONSHIP_TYPES).item(0);
        for (int i = 0; i < relationshipTypesNode.getChildNodes().getLength(); i++) {
            if (relationshipTypesNode.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
            		&& relationshipTypesNode.getChildNodes().item(i).getNodeName() == TAG_RELATIONSHIP_TYPE) {
            	rt.add(parseRelationshipType(relationshipTypesNode.getChildNodes().item(i),ht));
            }
        }    	
    }

    /**
     * Parses a single relationship type
     * 
     * @param node &lt;relationshipType&gt; node
     * @param ht Hashtable for lookup of parameter types by ID
     * @return RelationshipTypeVO
     * @throws ArchEException
     */
    private RelationshipTypeVO parseRelationshipType(Node node, Hashtable ht) throws ArchEException {
        Attr attribute;
        String id;
        String name;
        String desc;
        String recursive;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        id = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        name = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DESCRIPTION);
        desc = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_RECURSIVE);
        recursive = attribute.getNodeValue();
        Boolean isRecursive = Boolean.valueOf(recursive);
        
        String fullFactId = rf.getId() + "::" + id;
        RelationshipTypeVO rt = new RelationshipTypeVO(fullFactId, name, desc, isRecursive.booleanValue());
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node childNode = node.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_OPERANDS) {
                    parseOperands(childNode, rt.getAllowedOperands());
                }
                if (childNode.getNodeName() == TAG_PARAMETER) {
                    attribute = (Attr) childNode.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
                    id = attribute.getNodeValue();
                    String fullId = rf.getId() + "::" + id;
                    ParameterTypeVO pt = (ParameterTypeVO) ht.get(fullId);
                    //throw an exception if null
                    if (pt == null) {
                        throw new NullPointerException("Invalid Parameter Type ID(" + id
                                + ")associated with the relationship type.");
                    }
                    rt.getParamTypes().add(pt);
                }
            }
        }
        print("---parseRelationshipType----");
        print(id);
        print(name);
        print(desc);
        print(recursive);
        print(Boolean.toString(isRecursive.booleanValue()));
        print("---End parseRelationshipType----");
        return rt;
    }

    /**
     * Parses available operands of a relatinoship type
     * 
     * @param node &lt;operands&gt;node
     * @param operands Container for operands
     * @throws ArchEException
     */
    private void parseOperands(Node node, List operands) throws ArchEException {
        String[] elements = new String[] { OperandsVO.SCENARIO, OperandsVO.RESPONSIBILITY,
                OperandsVO.PARAMETER, OperandsVO.FUNCTION};
        OperandsVO op = new OperandsVO();
        String lhs = "";
        String rhs = "";
        String direction = "";
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node childNode = node.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_LHS) {
                    lhs = childNode.getFirstChild().getNodeValue();
                    if (Arrays.asList(elements).indexOf(lhs) < 0) {
                        throw new ArchEException("Invalid LHS.");
                    }
                    op.setLhs(lhs);
                }
                if (childNode.getNodeName() == TAG_RHS) {
                    rhs = childNode.getFirstChild().getNodeValue();
                    if (Arrays.asList(elements).indexOf(rhs) < 0) {
                        throw new ArchEException("Invalid RHS.");
                    }
                    op.setRhs(rhs);
                }
                if (childNode.getNodeName() == TAG_DIRECTION) {
                    direction = childNode.getFirstChild().getNodeValue();
                    if (direction.equalsIgnoreCase(BIDIRECTIONAL)) {
                        op.setBidirectional(true);
                    } else if (direction.equalsIgnoreCase(UNIDIRECTIONAL)) {
                        op.setBidirectional(false);
                    } else {
                        //Throw exception
                        throw new ArchEException("Invalid direction.");
                    }
                }
            }
        }
        operands.add(op);
        print(lhs);
        print(rhs);
        print(direction);
    }

    /**
     * Parses parameter types
     * 
     * @param node &lt;parameterTypes&gt;node
     * @param pt Container for parameter types
     * @throws ArchEException
     */
    private void parseParameterTypes(Node parameterTypesNode, ArrayList pt) throws ArchEException {
//        Node parameterTypesNode = node.getOwnerDocument().getElementsByTagName(TAG_PARAMETER_TYPES)
//                .item(0);
        for (int i = 0; i < parameterTypesNode.getChildNodes().getLength(); i++) {
            if (parameterTypesNode.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
            		&& parameterTypesNode.getChildNodes().item(i).getNodeName() == TAG_PARAMETER_TYPE) {
                pt.add(parseParameterType(parameterTypesNode.getChildNodes().item(i)));
            }
        }
    }

    /**
     * Parses a single parameter type
     * 
     * @param node &lt;parameterType&gt; node
     * @return ParameterTypeVO
     * @throws ArchEException
     */
    private ParameterTypeVO parseParameterType(Node node) throws ArchEException {
        Attr attribute;
        String id;
        String name;
        String desc;
        String dataType;
        String defaultValue;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        id = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        name = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DESCRIPTION);
        desc = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DATA_TYPE);
        dataType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DEFAULT_VALUE);
        defaultValue = attribute.getNodeValue();

        int dataTypeId;
        if (dataType.equalsIgnoreCase(DOUBLE)) {
            dataTypeId = ParameterTypeVO.DOUBLE;
        } else if (dataType.equalsIgnoreCase(INTEGER)) {
            dataTypeId = ParameterTypeVO.INTEGER;
        } else if (dataType.equalsIgnoreCase(STRING)) {
            dataTypeId = ParameterTypeVO.STRING;
        } else if (dataType.equalsIgnoreCase(BOOLEAN)) {
            dataTypeId = ParameterTypeVO.BOOLEAN;
        } else {
            throw new ArchEException("Invalid parameter data type.");
        }
        
        String fullFactId = rf.getId() + "::" + id;
        ParameterTypeVO paramType = new ParameterTypeVO(fullFactId, name, desc, dataTypeId, defaultValue);
        print("---parseParameterType----");
        print(id);
        print(name);
        print(desc);
        print(dataType);
        print("" + dataTypeId);
        print(defaultValue);
        print("---End parseParameterType----");
        return paramType;
    }

    /**
     * Parses scenario types
     * 
     * @param node &lt;scenarioTypes&gt;node
     * @param pt Container for scenario types
     * @throws ArchEException
     */
    private void parseScenarioTypes(Node node, ArrayList st) throws ArchEException {
        Node scenarioTypesNode = node.getOwnerDocument().getElementsByTagName(TAG_SCENARIO_TYPES)
                .item(0);
        for (int i = 0; i < scenarioTypesNode.getChildNodes().getLength(); i++) {
            if (scenarioTypesNode.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                st.add(parseScenarioType(scenarioTypesNode.getChildNodes().item(i)));
            }
        }
    }

    /**
     * Parses a single scenario type
     * 
     * @param node &lt;scenarioType&gt; node
     * @return ScenarioTypeVO
     * @throws ArchEException
     */
    private ScenarioTypeVO parseScenarioType(Node node) throws ArchEException {
        Attr attribute;
        String id;
        String name;
        String desc;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        id = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME);
        name = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DESCRIPTION);
        desc = attribute.getNodeValue();
        print("-------");
        print(id);
        print(name);
        print(desc);
        print("-------");
        ScenarioTypeVO st = new ScenarioTypeVO(id, name, desc);
        st.setRFID(rf.getId());
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PARTS) {
                parseScenarioTypeParts(node.getChildNodes().item(i), st.getPartsMetadata());
            }
        }
        return st;
    }

    /**
     * Parses scenario type parts
     * 
     * @param node &lt;scenarioTypes&gt;node
     * @param parts Container for scenario type parts
     * @throws ArchEException
     */
    private void parseScenarioTypeParts(Node node, ScenarioPartMetadataVO[] parts)
            throws ArchEException {
        int partIndex = 0;
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PART) {
                /* part number checking */
                if (partIndex >= 6) {
                    throw new ArchEException("Too many parts");
                }
                parts[partIndex] = parseScenarioTypePart(node.getChildNodes().item(i));
                partIndex++;
            }
        }
    }

    /**
     * Parses a single scenario type part
     * 
     * @param node &lt;part&gt; node
     * @return ScenarioPartMetadataVO
     * @throws ArchEException
     */
    private ScenarioPartMetadataVO parseScenarioTypePart(Node node) throws ArchEException {
        Attr attribute;
        String partType;
        String defaultText;
        String defaultValue;
        String defaultTypeId;
        String defaultUnitId;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_PART_TYPE);
        partType = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DEFAULT_TEXT);
        defaultText = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DEFAULT_VALUE);
        defaultValue = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DEFAULT_TYPE_ID);
        defaultTypeId = attribute.getNodeValue();
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_DEFAULT_UNIT_ID);
        defaultUnitId = attribute.getNodeValue();
        // partType conversion
        int partTypeId;
        if (partType.equalsIgnoreCase(STIMULUS)) {
            partTypeId = ScenarioPartMetadataVO.STIMULUS;
        } else if (partType.equalsIgnoreCase(SOURCE_OF_STIMULUS)) {
            partTypeId = ScenarioPartMetadataVO.SOURCE_OF_STIMULUS;
        } else if (partType.equalsIgnoreCase(ENVIRONMENT)) {
            partTypeId = ScenarioPartMetadataVO.ENVIRONMENT;
        } else if (partType.equalsIgnoreCase(ARTIFACT)) {
            partTypeId = ScenarioPartMetadataVO.ARTIFACT;
        } else if (partType.equalsIgnoreCase(RESPONSE)) {
            partTypeId = ScenarioPartMetadataVO.RESPONSE;
        } else if (partType.equalsIgnoreCase(RESPONSE_MEASURE)) {
            partTypeId = ScenarioPartMetadataVO.RESPONSE_MEASURE;
        } else {
            throw new ArchEException("Invalid part type.");
        }
        print("---Type Part----");
        print(partType);
        print("" + partTypeId);
        print(defaultText);
        print(defaultValue);
        print(defaultTypeId);
        print(defaultUnitId);
        print("---End Type Part----");
        ScenarioPartMetadataVO part = new ScenarioPartMetadataVO(partTypeId, defaultText,
                defaultValue);
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_TYPES) {
                parsePartTypes(node.getChildNodes().item(i), part.getTypes());
            }
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_UNITS) {
                parsePartUnits(node.getChildNodes().item(i), part.getUnits());
            }
        }
        if (defaultTypeId != null && defaultTypeId.length() > 0) {
            PartOptionsVO defaultType = getDefaultOption(part.getTypes(), defaultTypeId);
            if (defaultType == null) {
                throw new NullPointerException("Invalid default type ID.");
            }
            part.setDefaultType(defaultType);
        }
        if (defaultUnitId != null && defaultUnitId.length() > 0) {
            PartOptionsVO defaultUnit = getDefaultOption(part.getUnits(), defaultUnitId);
            if (defaultUnit == null) {
                throw new NullPointerException("Invalid default unit ID.");
            }
            part.setDefaultUnit(defaultUnit);
        }
        return part;
    }

    /**
     * Helper method to identify a default part type or unit
     * 
     * @param options List of types or units
     * @param id ID of the default type or unit
     * @return PartOptionsVO
     */
    private PartOptionsVO getDefaultOption(ArrayList options, String id) {
        for (int i = 0; i < options.size(); i++) {
            PartOptionsVO option = (PartOptionsVO) options.get(i);
            if (option.getId().equalsIgnoreCase(id)) {
                return option;
            }
        }
        return null;
    }

    /**
     * Parses available types of a scenario type
     * 
     * @param node &lt;types&gt;node
     * @param types Container for types
     */
    private void parsePartTypes(Node node, ArrayList types) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_TYPE) {
                Node tNode = node.getChildNodes().item(i);
                Attr attribute;
                String id;
                String name;
                attribute = (Attr) tNode.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
                id = attribute.getNodeValue();
                attribute = (Attr) tNode.getAttributes().getNamedItem(ATTRIBUTE_NAME);
                name = attribute.getNodeValue();
                print("---Type----");
                print(id);
                print(name);
                print("---End Type----");
                PartOptionsVO option = new PartOptionsVO(id, name);
                types.add(option);
            }
        }
    }

    /**
     * Parses available units of a scenario type
     * 
     * @param node &lt;units&gt;node
     * @param units Container for units
     */
    private void parsePartUnits(Node node, ArrayList units) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_UNIT) {
                Node tNode = node.getChildNodes().item(i);
                Attr attribute;
                String id;
                String name;
                attribute = (Attr) tNode.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
                id = attribute.getNodeValue();
                attribute = (Attr) tNode.getAttributes().getNamedItem(ATTRIBUTE_NAME);
                name = attribute.getNodeValue();
                print("---Unit----");
                print(id);
                print(name);
                print("---End Unit----");
                PartOptionsVO option = new PartOptionsVO(id, name);
                units.add(option);
            }
        }
    }

    /**
     * Parses a model
     * 
     * @param node &lt;rf&gt; node
     * @param rf Reasoning framework the model belongs to
     * @throws ArchEException
     */
    private void parseModel(Node node, ReasoningFrameworkVO rf) throws ArchEException {
        Node modelNode = node.getOwnerDocument().getElementsByTagName(TAG_MODEL).item(0);
        for (int i = 0; i < modelNode.getChildNodes().getLength(); i++) {
            Node childNode = modelNode.getChildNodes().item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getNodeName() == TAG_MODEL_ELEMENT_TYPE) {
                    rf.getModelElementTypes().add(parseModelElementType(childNode));
                }
                if (childNode.getNodeName() == TAG_MODEL_RELATION_TYPE) {
                    rf.getModelRelationTypes().add(parseModelRelationType(childNode));
                }
            }
        }
    }

    /**
     * Parses a Model Element Type
     * 
     * @param node &lt;modelElementType&gt;node
     * @return ModelElementTypeVO
     */
    private ModelElementTypeVO parseModelElementType(Node node) {
        Attr attribute;
        String typeID;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        typeID = attribute.getNodeValue();
        
        String nameProperty;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_NAME_PROPERTYNAME);
        nameProperty = attribute.getNodeValue();
        
        // Create a fact type for this relation
        String factType = rf.getId() + "::" + typeID;         
        ModelElementTypeVO met = new ModelElementTypeVO(factType,nameProperty,null,null,null);
        
        List paramSlotNames = new ArrayList();
        List paramSlotTypes = new ArrayList();
        List paramSlotDisplays = new ArrayList();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PROPERTY) {
                parseProperty(node.getChildNodes().item(i), paramSlotNames,paramSlotTypes,paramSlotDisplays);
            }
        }
        met.setParamSlotNames((String[]) paramSlotNames.toArray(new String[paramSlotNames.size()]));
        met.setParamSlotTypes((String[]) paramSlotTypes.toArray(new String[paramSlotTypes.size()]));
        met.setParamSlotDisplays((String[]) paramSlotDisplays.toArray(new String[paramSlotDisplays.size()]));
        return met;    	
    }

    /**
     * Parses a Model Relation Type
     * 
     * @param node &lt;modelRelationType&gt;node
     * @return ModelRelationTypeVO
     */
    private ModelRelationTypeVO parseModelRelationType(Node node) {
        Attr attribute;
        String typeID;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_TYPE_ID);
        typeID = attribute.getNodeValue();
        
        String lhsPropertyName;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_LHS_PROPERTYNAME);
        lhsPropertyName = attribute.getNodeValue();

        String rhsPropertyName;
        attribute = (Attr) node.getAttributes().getNamedItem(ATTRIBUTE_RHS_PROPERTYNAME);
        rhsPropertyName = attribute.getNodeValue();
        
        // Create a fact type for this relation
        String factType = rf.getId() + "::" + typeID;         
        ModelRelationTypeVO mrt = new ModelRelationTypeVO(factType,
        			lhsPropertyName,rhsPropertyName,null,null,null);
        
        List paramSlotNames = new ArrayList();
        List paramSlotTypes = new ArrayList();
        List paramSlotDisplays = new ArrayList();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE
                    && node.getChildNodes().item(i).getNodeName() == TAG_PROPERTY) {
                parseProperty(node.getChildNodes().item(i), paramSlotNames,paramSlotTypes,paramSlotDisplays);
            }
        }
        mrt.setParamSlotNames((String[]) paramSlotNames.toArray(new String[paramSlotNames.size()]));
        mrt.setParamSlotTypes((String[]) paramSlotTypes.toArray(new String[paramSlotTypes.size()]));
        mrt.setParamSlotDisplays((String[]) paramSlotDisplays.toArray(new String[paramSlotDisplays.size()]));
        return mrt;
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

    /* For Debug */
    protected static void print(String text) {
        //System.out.println(text);
    }
}