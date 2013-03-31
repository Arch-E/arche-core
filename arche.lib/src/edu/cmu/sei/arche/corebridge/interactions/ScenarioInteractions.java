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

import java.util.Iterator;

import jess.Fact;
import jess.FactIDValue;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

/**
 * All interactions with the ArchE Core related to the "scenario" architectural element.
 * 
 * @author Henry Chen
 * @author Neel Mullick
 */
public class ScenarioInteractions extends Interactions {

    /* The string array that keeps the construct of the scenario */
    private String[] scenarioConstruct;

    /**
     * The constructor initializes the construct of the scenario
     */
    public ScenarioInteractions() {
        scenarioConstruct = new String[27];
        scenarioConstruct[0] = "description";
        scenarioConstruct[1] = "quality";
        scenarioConstruct[2] = "stimulusText";
        scenarioConstruct[3] = "stimulusType";
        scenarioConstruct[4] = "stimulusUnit";
        scenarioConstruct[5] = "stimulusValue";
        scenarioConstruct[6] = "sourceText";
        scenarioConstruct[7] = "sourceType";
        scenarioConstruct[8] = "sourceUnit";
        scenarioConstruct[9] = "sourceValue";
        scenarioConstruct[10] = "artifactText";
        scenarioConstruct[11] = "artifactType";
        scenarioConstruct[12] = "artifactUnit";
        scenarioConstruct[13] = "artifactValue";
        scenarioConstruct[14] = "environmentText";
        scenarioConstruct[15] = "environmentType";
        scenarioConstruct[16] = "environmentUnit";
        scenarioConstruct[17] = "environmentValue";
        scenarioConstruct[18] = "responseText";
        scenarioConstruct[19] = "responseType";
        scenarioConstruct[20] = "responseUnit";
        scenarioConstruct[21] = "responseValue";
        scenarioConstruct[22] = "measureText";
        scenarioConstruct[23] = "measureType";
        scenarioConstruct[24] = "measureUnit";
        scenarioConstruct[25] = "measureValue";
        scenarioConstruct[26] = "reasoningFramework";
    }

    /**
     * Asserts a scenario fact in the ArchE Core with state = "final" / "notfinal" so that the
     * scenario is either persisted or processes (thought about!) by the ArchE Core.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param scenario The string arrays of all the contents of a scenario.
     * @param stateScenario The string that indicates whether the user is "thinking" (i.e. asking
     *            the core to think and advise on the scenario) or is saving the final version of
     *            the scenario. This will be "final" if the user is finalizing the scenario - any
     *            other value for this parameter will be treated as a scenario to think about.
     */
    public void assertScenario(Rete engineArchE, String[] scenario, String stateScenario) {
        try {
            /* Contructing the Fact object using the parameters */
            Fact fTemp = new Fact("Planner::C_AddScenario", engineArchE);

            for (int i = 0; i < scenarioConstruct.length; i++) {
                if (scenario[i] != null && scenario[i].trim() != "") {
                    //these values are slots that contain enumeration datatypes in the core and are
                    // therefore stored as symbols (RU.ATOM)
                    if (scenarioConstruct[i].equalsIgnoreCase("quality")
                            || scenarioConstruct[i].toLowerCase().equalsIgnoreCase("reasoningFramework")
                            || scenarioConstruct[i].toLowerCase().endsWith("type")
                            || scenarioConstruct[i].toLowerCase().endsWith("unit")) {
                        //the quality slot of the scenario fact contains one of an enumeration and
                        // is therefore stored as a symbol.
                        fTemp.setSlotValue(scenarioConstruct[i], new Value(scenario[i], RU.SYMBOL));
                    } else if (scenarioConstruct[i].toLowerCase().endsWith("value")) {
                        fTemp.setSlotValue(scenarioConstruct[i], new Value(Double
                                .valueOf(scenario[i]).doubleValue(), RU.FLOAT));
                    } else {
                        fTemp.setSlotValue(scenarioConstruct[i], new Value(scenario[i], RU.STRING));
                    }
                }
            }

            //This slot contains the string that indicates whether the user is "thinking" (i.e.
            // asking the core to think and advise on the scenario) or is saving the final version
            // of the scenario. This will be "final" if the user is finalizing the scenario - any
            // other value for this parameter will be treated as a scenario to think about. The
            // state slot is like an enumeration in the Core and therefore is stored as a symbol
            fTemp.setSlotValue("state", new Value(stateScenario, RU.SYMBOL));

            /* Asserting the Fact */
            engineArchE.assertFact(fTemp);

            //call the method in the superclass to execute the commands in the Core that are common
            // to all interactions with the Core.
            runCore(engineArchE);

        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * This method locates the temporary fact that Asserts a scenario fact in the ArchE Core with
     * state != "final" so that the ArchE Core can process the scenario and give the user
     * suggestions / modifications to the scenario. The assumption for this implementation is that
     * at any given time there is only one instance of the C_AddScenario fact in the Core. After
     * this "think-like" processing this instance will be retracted from the Core.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     */
    public String[] thinkScenario(Rete engineArchE) {
        try {

            Fact fTemp = null;
            String[] arrScenario = new String[scenarioConstruct.length];
            for (Iterator iFacts = engineArchE.listFacts(); engineArchE.listFacts().hasNext();) {
                fTemp = (Fact) iFacts.next();
                if (fTemp.getName().toString().equalsIgnoreCase("Planner::C_AddScenario")) {
                    for (int i = 0; i < scenarioConstruct.length; i++) {
                        arrScenario[i] = fTemp.getSlotValue(scenarioConstruct[i])
                                .stringValue(engineArchE.getGlobalContext());
                    }
                    engineArchE.retract(fTemp);
                    break;
                }
            }

            return arrScenario;

        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * Modifies a scenario fact in the ArchE Core. Even though the findFactByFact method exposed by
     * the Rete class is faster than findFactByID (as documented in the API documentation) we have
     * changed the implementation to use the latter because the former implies complete knowledge of
     * the fact structures in the Arche Core. Using the latter allows for modifiability over
     * performance - becasuse the fact structures can change without "finding the fact in the core
     * bridge" ever becoming a problem.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param scenario The string arrays of all the contents of a modified scenario.
     * @param applyToSlotM The Scenario Fact Id which is to be modified.
     */
    public void modifyScenario(Rete engineArchE, String[] scenario, int applyToSlotM) {
        try {
            /* Contructing the new (command) Fact object using the parameters */
            Fact fTemp = new Fact("Planner::C_ChangeScenario", engineArchE);

            for (int i = 0; i < scenarioConstruct.length; i++) {
                if (scenario[i] != null && scenario[i].trim() != "") {
                    //these values are slots that contain enumeration datatypes in the core and are
                    // therefore stored as symbols (RU.ATOM)
                    if (scenarioConstruct[i].equalsIgnoreCase("quality")
                            || scenarioConstruct[i].toLowerCase().equalsIgnoreCase("reasoningFramework")
                            || scenarioConstruct[i].toLowerCase().endsWith("type")
                            || scenarioConstruct[i].toLowerCase().endsWith("unit")) {
                        //the quality slot of the scenario fact contains one of an enumeration and
                        // is therefore stored as a symbol.
                        fTemp.setSlotValue(scenarioConstruct[i], new Value(scenario[i], RU.SYMBOL));
                    } else if (scenarioConstruct[i].toLowerCase().endsWith("value")) {
                        fTemp.setSlotValue(scenarioConstruct[i], new Value(Double
                                .valueOf(scenario[i]).doubleValue(), RU.FLOAT));
                    } else {
                        fTemp.setSlotValue(scenarioConstruct[i], new Value(scenario[i], RU.STRING));
                    }
                }
            }

            //This slot contains the string that indicates whether the user is "thinking" (i.e.
            // asking the core to think and advise on the scenario) or is saving the final version
            // of the scenario. This will be "final" if the user is finalizing the scenario - any
            // other value for this parameter will be treated as a scenario to think about. The
            // state slot is like an enumeration in the Core and therefore is stored as a symbol
            fTemp.setSlotValue("state", new Value("final", RU.SYMBOL));

            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlotM)));

            /* Asserting the Fact */
            engineArchE.assertFact(fTemp);

            //call the method in the superclass to execute the commands in the Core that are common
            // to all interactions with the Core.
            runCore(engineArchE);

        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * Retracts the following - the Scenario Fact and the responsibility mappings of the scenario
     * from the ArchE Core.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param applyToSlotD the fact Id of the Scenario that is to be deleted.
     */
    public void retractScenario(Rete engineArchE, int applyToSlotD) {
        try {
            /* Contructing the Fact object using the initial Scenario VO */
            Fact fTemp = new Fact("Planner::C_DeleteScenario", engineArchE);

            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlotD)));

            /* asserting the Fact to delete the scenario */
            engineArchE.assertFact(fTemp);

            //call the method in the superclass to execute the commands in the Core that are common
            // to all interactions with the Core.
            runCore(engineArchE);

        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
            throw new RuntimeException(je.getMessage(), je);
        }
    }

}