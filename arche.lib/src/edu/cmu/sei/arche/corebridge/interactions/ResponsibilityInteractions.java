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

import jess.Fact;
import jess.FactIDValue;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

import edu.cmu.sei.arche.vo.CoreFact;
import edu.cmu.sei.arche.vo.ParameterTypeVO;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ResponsibilityVO;

/**
 * All interactions with the ArchE Core related to the "responsibility" architectural element.
 * 
 * @author Neel Mullick
 */
public class ResponsibilityInteractions extends Interactions {

    /**
     * Asserts a responsibility fact in the ArchE Core.
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param nameSlot TODO
     * @param descriptionSlot The string description slot of the fact.
     */
    public void assertResponsiblity(Rete engineArchE, String nameSlot, String descriptionSlot) {
        try {
            /* Contructing the Fact object using the parameters */
            Fact fTemp = new Fact("MAIN::Responsibilities", engineArchE);
            if (nameSlot != null && nameSlot.trim() != "") {
                fTemp.setSlotValue("name", new Value(nameSlot, RU.STRING));
            }
            if (descriptionSlot != null && descriptionSlot.trim() != "") {
                fTemp.setSlotValue("description", new Value(descriptionSlot, RU.STRING));
            }
            //For facts (not of type C_) that are asserted source = User. This slot is like an
            // enumeration in the Core and therefore is stored as a symbol
            fTemp.setSlotValue("source", new Value("User", RU.SYMBOL));

            fTemp.setSlotValue("status", new Value(0, RU.INTEGER));
            
            /* Asserting the Fact */
            engineArchE.assertFact(fTemp);
            engineArchE.assertString("(Planner::C_RequirementsChanged)");

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
     * Asserts a "parameter of a responsibility" fact in the ArchE Core.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param paramName The name of the parameter - maps to a fact is the ArchE Core.
     * @param ownerSlot The integer owner (parent) slot of the fact.
     * @param valueSlot The string value slot of the fact.
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     */
    public void assertResponsiblityParameter(Rete engineArchE, String paramName, int ownerSlot,
            String valueSlot, int dataType) {
        try {

            /* Contructing the Fact object using the parameters */
            Fact fTemp = new Fact(paramName, engineArchE);

            /* The following are the valid values for the parameter - paramName (ArchEv1.0) */
            //SchedulingTheory::P_Scheduler
            //SchedulingTheory::P_Priority
            //SchedulingTheory::P_Processors
            //SchedulingTheory::P_ArrivalPeriod
            //PerformanceReasoningFrameworks::P_ExecutionTime
            //ModifiabilityReasoningFrameworks::P_CostOfChange
            fTemp.setSlotValue("owner", new FactIDValue(engineArchE.findFactByID(ownerSlot)));

            switch (dataType) {
            case ParameterTypeVO.BOOLEAN:
                fTemp.setSlotValue("value", new Value(valueSlot.toUpperCase(), RU.SYMBOL));
                break;
            case ParameterTypeVO.DOUBLE:
                fTemp.setSlotValue("value", new Value(Double.valueOf(valueSlot).doubleValue(),
                        RU.FLOAT));
                break;
            case ParameterTypeVO.STRING:
                fTemp.setSlotValue("value", new Value(valueSlot, RU.STRING));
                break;
            case ParameterTypeVO.INTEGER:
                fTemp.setSlotValue("value", new Value(Integer.valueOf(valueSlot).intValue(),
                        RU.INTEGER));
                break;
            }

            //For facts (not of type C_) that are asserted source = User. This slot is like an
            // enumeration in the Core and therefore is stored as a symbol
            fTemp.setSlotValue("source", new Value("User", RU.SYMBOL));

            /* Asserting the Fact */
            engineArchE.assertFact(fTemp);
            engineArchE.assertString("(Planner::C_RequirementsChanged)");

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
     * Modifies a responsibility fact in the ArchE Core. Even though the findFactByFact method
     * exposed by the Rete class is faster than findFactByID (as documented in the API
     * documentation) we have changed the implementation to use the latter because the former
     * implies complete knowledge of the fact structures in the Arche Core. Using the latter allows
     * for modifiability over performance - becasuse the fact structures can change without "finding
     * the fact in the core bridge" ever becoming a problem.
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param voRInitial The Responsibility Value Object which is to be modified.
     * @param nameSlot TODO
     * @param descriptionSlot The modified string description slot of the fact.
     */
    public void modifyResponsibility(Rete engineArchE, ResponsibilityVO voRInitial,
            String nameSlot, String descriptionSlot) {
        try {
            /* Finding the Fact using the FactId */
            Fact fModify = engineArchE.findFactByID(voRInitial.getFactId());

            /* Checking the slots that changed and modifying the fact accordingly */
            if (!(voRInitial.getName().equalsIgnoreCase(nameSlot))) {
                engineArchE.modify(fModify, "name", new Value(nameSlot, RU.STRING));
            }

            /* Checking the slots that changed and modifying the fact accordingly */
            if (descriptionSlot != null) {
	            if (!(voRInitial.getDescription().equalsIgnoreCase(descriptionSlot))) {
	                engineArchE.modify(fModify, "description", new Value(descriptionSlot, RU.STRING));
	            }
            }
            //For facts (not of type C_) that are modified, source = User. This slot is like an
            // enumeration in the Core and therefore is stored as a symbol
            if (voRInitial.getSource() != CoreFact.USER) {
                engineArchE.modify(fModify, "source", new Value("User", RU.SYMBOL));
            }

            engineArchE.assertString("(Planner::C_RequirementsChanged)");
            
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
     * Modifies a "parameter of a responsibility" fact in the ArchE Core. Even though the
     * findFactByFact method exposed by the Rete class is faster than findFactByID (as documented in
     * the API documentation) we have changed the implementation to use the latter because the
     * former implies complete knowledge of the fact structures in the Arche Core. Using the latter
     * allows for modifiability over performance - becasuse the fact structures can change without
     * "finding the fact in the core bridge" ever becoming a problem.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param voParamInitial the original Parameter Value Object
     * @param ownerSlotI The initial integer owner (parent) slot of the fact.
     * @param ownerSlotM The modified integer owner (parent) slot of the fact.
     * @param valueSlotM The modified string value slot of the fact. If the user has left the cell
     *            corresponding ot this value blank, pass 0 (zero) for this parameter
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     */
    public void modifyResponsiblityParameter(Rete engineArchE, ParameterVO voParamInitial,
            int ownerSlotI, int ownerSlotM, String valueSlotM, int dataType) {
        try {
            //System.out.println("[Modify Responsibility Parameter]");
            //System.out.println("  ParameterID: " + voParamInitial.getFactId());
            //System.out.println("  ownerSlotI : " + ownerSlotI);
            //System.out.println("  ownerSlotM : " + ownerSlotM);
            //System.out.println("  valueSlotM : " + valueSlotM);
            //System.out.println("  dataType   : " + dataType);
            /* Finding the Fact using the FactId */
            Fact fModify = engineArchE.findFactByID(voParamInitial.getFactId());

            /* Checking the slots that changed and modifying the fact accordingly */
            if (ownerSlotI != ownerSlotM) {
                engineArchE.modify(fModify, "owner", new FactIDValue(engineArchE
                        .findFactByID(ownerSlotM)));
            }

            if (!(voParamInitial.getValue().equalsIgnoreCase(valueSlotM))) {
                switch (dataType) {
                case ParameterTypeVO.BOOLEAN:
                    engineArchE.modify(fModify, "value", new Value(valueSlotM.toUpperCase(),
                            RU.SYMBOL));
                    break;
                case ParameterTypeVO.DOUBLE:
                    engineArchE.modify(fModify, "value", new Value(Double.valueOf(valueSlotM)
                            .doubleValue(), RU.FLOAT));
                    break;
                case ParameterTypeVO.STRING:
                    engineArchE.modify(fModify, "value", new Value(valueSlotM, RU.STRING));
                    break;
                case ParameterTypeVO.INTEGER:
                    engineArchE.modify(fModify, "value", new Value(Integer.valueOf(valueSlotM)
                    		.intValue(), RU.INTEGER));
                    break;
                }
            }

            //For facts (not of type C_) that are modified, source = User. This slot is like an
            // enumeration in the Core and therefore is stored as a symbol
            if (voParamInitial.getSource() != CoreFact.USER) {
            	engineArchE.modify(fModify, "source", new Value("User", RU.SYMBOL));
            }

            engineArchE.assertString("(Planner::C_RequirementsChanged)");
            
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
     * Retracts the following - the Responsibility Fact - the Parameters of the responsibility - the
     * relationships of the responsibility - the parameters of the responsibility relationshipsa
     * responsibility and its associated parameters and relationships from the ArchE Core.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param applyToSlotD the fact Id of the Responsbility that is to be deleted.
     */
    public void retractResponsibility(Rete engineArchE, int applyToSlotD) {
        try {
            /* Contructing the Fact object using the initial Responsibility VO */
            Fact fTemp = new Fact("Planner::C_DeleteResponsibility", engineArchE);
            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlotD)));

            /* asserting the Fact to delete the responsibility */
            engineArchE.assertFact(fTemp);

            //call the method in the superclass to execute the commands in the Core that are common
            // to all interactions with the Core.
            runCore(engineArchE);

        } catch (JessException je) {
            // Convert JessException to preserve encapsulation (in the future, it could be
            // SQLException for example). Uses an unchecked exception because the caller is not
            // expected to know how to deal with JessException in any way different it would deal
            // with another RuntimeException (i.e. NPE).
        	je.printStackTrace();
            throw new RuntimeException(je.getMessage(), je);
        }
    }
}