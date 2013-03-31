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

package edu.cmu.sei.arche.corebridge.interactions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jess.Fact;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;

/**
 * All miscellaneous interactions with the ArchE Core. Could be related to multiple architectural /
 * design element(s).
 * 
 * @author Neel Mullick
 */
public class MiscellanyInteractions {

    private Document doc;

    private Element  xml;

    /**
     * gets the value (string contents) for a given slot of a fact in the core.
     * 
     * @param engineArchE The centrally located Rete object (Jess instance).
     * @param factId The integer FactId of the fact in the Core.
     * @param slotName The string slot for which the value needs ot be read.
     * @return The String value (contents) of the given slot for the fact found using the Id. I fhte
     *         slot was a multislot a blank string "" is returned. If a fact refernce is in the slot
     *         then the factid is returned.
     */
    public String getValue(Rete engineArchE, int factId, String slotName) {
        try {
            // Contruct fact using the fact found in the core using the factId
            // as argument
            Fact fTemp = new Fact(engineArchE.findFactByID(factId));

            if (fTemp.getSlotValue(slotName).type() == RU.MULTISLOT) {
                return "";
            }

            //return the string value of the contents of the slot of the fact
            // that was found
            return (fTemp.getSlotValue(slotName).type() == RU.FACT) ? String.valueOf(fTemp
                    .getSlotValue(slotName).factValue(engineArchE.getGlobalContext()).getFactId())
                    : fTemp.getSlotValue(slotName).stringValue(engineArchE.getGlobalContext());
        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future,
            // it could be
            // SQLException for example). Uses an unchecked exception because
            // the caller is not
            // expected to know how to deal with JessException in any way
            // different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * Writes the XML file containing all the facts, their slots and values from the core to an XML
     * file
     * 
     * @param engineArchE The centrally located Rete object (Jess instance).
     */
    public void writeFactsXML(Rete engineArchE, String fileNameForXML, String path) {
        Element archE = null;

        try {

            Fact fTemp = null;
            String factId = "";
            ValueVector listVV;

            Element factName = null;
            Element valueName = null;

            doc = new DocumentImpl();
            archE = doc.createElement("ArchE");

            //Retrieve Iterator of all the Facts from the Core.
            for (Iterator iFacts = engineArchE.listFacts(); engineArchE.listFacts().hasNext();) {
                fTemp = (Fact) iFacts.next();

                //Get the Fact Name
                String name[] = new String[2];
                name = fTemp.getName().split("::");
                factName = doc.createElement(name[1]);

                //Get the FactId
                factName.setAttribute("factId", String.valueOf(fTemp.getFactId()));
                factName.setAttribute("module", name[0]);

                try {
                    for (int i = 0; i < fTemp.getDeftemplate().getNSlots(); i++) {

                        //create tag of SlotName
                        switch (fTemp.getDeftemplate().getSlotType(i)) {
                        case RU.MULTISLOT:
                            listVV = fTemp.getSlotValue(fTemp.getDeftemplate().getSlotName(i))
                                    .listValue(engineArchE.getGlobalContext());
                            String multiSlotValues[] = new String[listVV.size()];
                            String strValue = "";
                            for (int j = 0; j < listVV.size(); j++) {
                                Value v = listVV.get(j);

                                if (v.type() == RU.FACT) {
                                    valueName = doc.createElement(fTemp.getDeftemplate()
                                            .getSlotName(i));

                                    valueName.appendChild(doc
                                            .createTextNode(String.valueOf(v
                                                    .factValue(engineArchE.getGlobalContext())
                                                    .getFactId())));
                                } else {

                                    valueName = doc.createElement(fTemp.getDeftemplate()
                                            .getSlotName(i));

                                    valueName.appendChild(doc.createTextNode(v
                                            .stringValue(engineArchE.getGlobalContext())));
                                }
                            }

                            break;
                        default:

                            if (getStringValue(engineArchE, fTemp, i) != null) {

                                valueName = doc
                                        .createElement(fTemp.getDeftemplate().getSlotName(i));

                                valueName.appendChild(doc
                                        .createTextNode(getStringValue(engineArchE, fTemp, i)
                                                .equals("nil") ? "" : getStringValue(engineArchE,
                                                                                     fTemp, i)));
                            }
                            break;
                        }
                        factName.appendChild(valueName);
                    }
                } catch (JessException je) {

                }

                archE.appendChild(factName);
            }
            doc.appendChild(archE);
            createXMLFile(fileNameForXML, path);
        } catch (ArrayIndexOutOfBoundsException ae) {
            doc.appendChild(archE);
            createXMLFile(fileNameForXML, path);
        }
    }

    /**
     * Retrieve all data from facts in the Core and create XML file with them
     * 
     * @param xmlFileName - file name which will be created as a result
     * @param userPath - path information in accordance with user's environment
     */
    private void createXMLFile(String xmlFileName, String userPath) {
        String filePath = userPath;
        OutputFormat format = new OutputFormat(doc, "ISO-8859-1", true);
        FileWriter file;
        try {
            file = new FileWriter(new File(filePath + xmlFileName));
            XMLSerializer serial = new XMLSerializer(file, format);
            serial.asDOMSerializer();
            serial.serialize(doc.getDocumentElement());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Retrieves the string value of a slot of a fact.
     * 
     * @param engineArchE The centrally located Rete object (Jess instance).
     * @param f The fact for which a particular slot's value needs to be retrieved
     * @param i The zero based index of the slot for which the string value needs to be retrieved.
     * @return String the string value of a slot of a fact.
     */
    private String getStringValue(Rete engine, Fact f, int i) throws JessException {
        //If the fact's slot is a reference to a fact then this returns the
        // factId of the referred
        // fact cast as a string. Otherwise the contents of the slot are
        // returned as a string.
        return (getType(f, i) == RU.FACT) ? String.valueOf(f.getSlotValue(
                                                                          f.getDeftemplate()
                                                                                  .getSlotName(i))
                .factValue(engine.getGlobalContext()).getFactId()) : f
                .getSlotValue(f.getDeftemplate().getSlotName(i))
                .stringValue(engine.getGlobalContext());
    }

    /**
     * Retrieves the type of a slot of a fact
     * 
     * @param f The fact for which a particular slot's value needs to be retrieved
     * @param i The zero based index of the slot for which the string value needs to be retrieved.
     * @return int Returns the slot data type (one of the constants in jess.RU) for the slot given
     *         by the zero-based index.
     */
    private int getType(Fact f, int i) throws JessException {
        return f.getSlotValue(f.getDeftemplate().getSlotName(i)).type();
    }

}