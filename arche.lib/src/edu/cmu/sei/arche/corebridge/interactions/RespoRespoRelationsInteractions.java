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

import java.util.Enumeration;

import jess.Defrule;
import jess.Deftemplate;
import jess.Fact;
import jess.FactIDValue;
import jess.Funcall;
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;

import edu.cmu.sei.arche.corebridge.IRefreshableUI;
import edu.cmu.sei.arche.corebridge.listener.VOUpdate;
import edu.cmu.sei.arche.vo.CoreFact;
import edu.cmu.sei.arche.vo.ParameterTypeVO;
import edu.cmu.sei.arche.vo.ParameterVO;
import edu.cmu.sei.arche.vo.ProjectVO;
import edu.cmu.sei.arche.vo.RelationshipTypeVO;
import edu.cmu.sei.arche.vo.RelationshipVO;

/**
 * All interactions with the ArchE Core related to relationships between responsibilities.
 * 
 * @author Neel Mullick
 */
public class RespoRespoRelationsInteractions extends Interactions {

    /**
     * Asserts a fact in the ArchE Core that maps to a relationship between responsibilities.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param parentSlot The integer factId that is the parent in the relationship.
     * @param childSlot The integer factId that is the child in the relationship.
     * @param typeSlot The string that represents the type of the relationship.
     */
    public void assertRespoRelation(Rete engineArchE, int parentSlot, int childSlot, String typeSlot) {
        try {
            /* Contructing the Fact object using the parameters */
            Fact fTemp = new Fact("Planner::C_AddResponsibilityRelation", engineArchE);
            fTemp.setSlotValue("parent", new FactIDValue(engineArchE.findFactByID(parentSlot)));
            fTemp.setSlotValue("child", new FactIDValue(engineArchE.findFactByID(childSlot)));
            if (typeSlot != null && typeSlot.trim() != "") {
                fTemp.setSlotValue("type", new Value(typeSlot, RU.SYMBOL));
            }

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
     * Asserts a fact in the ArchE Core that maps to a relationship between responsibilities.
     * If needed, it defines the template and the asserting rule for the fact.
     * Typically it's used to assert a relation defined by external reasoning frameworks.
     * Also, it assumes that the modudle ExternalInteraction has already been defined.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param parentSlot The integer factId that is the parent in the relationship.
     * @param childSlot The integer factId that is the child in the relationship.
     * @param typeVO The VO object that represents the type of the relationship.
     */
    public void assertRespoRelation(Rete engineArchE, int parentSlot, int childSlot, RelationshipTypeVO typeVO) {
        try {
        	        			        	
            /* Contructing the Fact object using the parameters */
//            Fact fTemp = new Fact("Planner::C_AddResponsibilityRelation", engineArchE);
//            fTemp.setSlotValue("parent", new FactIDValue(engineArchE.findFactByID(parentSlot)));
//            fTemp.setSlotValue("child", new FactIDValue(engineArchE.findFactByID(childSlot)));
//            if (typeVO != null && typeVO.getName().trim() != "") {
//                fTemp.setSlotValue("type", new Value(typeVO.getName(), RU.SYMBOL));
//            }

	          Fact fTemp = new Fact(typeVO.getId(), engineArchE);
	          fTemp.setSlotValue("source", new Value("User", RU.SYMBOL));
	          fTemp.setSlotValue("parent", new FactIDValue(engineArchE.findFactByID(parentSlot)));
	          fTemp.setSlotValue("child", new FactIDValue(engineArchE.findFactByID(childSlot)));
        	
	          /* Asserting the Fact */
	          Fact jFact = engineArchE.assertFact(fTemp, engineArchE.getGlobalContext());
              engineArchE.assertString("(Planner::C_RequirementsChanged)");
	          
	          
//	          /* Temporary implementation to test the Analyze interaction command for external RFs */
//	          
//	          int factId = jFact.getFactId();
//	          String typeName = jFact.getName();
//	          int source = VOUpdate.strToIntSource(jFact.getSlotValue("source").stringValue(engineArchE.getGlobalContext()));
//
//	          int parent = -1;
//	          int child = -1;
//	          /*
//	           * The parent and the child fact ids can be strings or Fact values depending on whether the
//	           * TranslationRElation fact was read from a persisted file (in which case they will be
//	           * strings) or are asserted by the rules inside of the ArchE Core (in which case they will
//	           * be references to facts therefore returning values of type RU.FACT / RU.EXTERNAL_ADDRESS
//	           */
//	          if ((jFact.getSlotValue("parent").type() == RU.EXTERNAL_ADDRESS)
//	                  || (jFact.getSlotValue("parent").type() == RU.FACT)) {
//	              parent = jFact.getSlotValue("parent").factValue(engineArchE.getGlobalContext()).getFactId();
//	          } else {
//	              parent = VOUpdate.strToIntFactId(jFact.getSlotValue("parent").stringValue(engineArchE.getGlobalContext()));
//	          }
//
//	          if ((jFact.getSlotValue("child").type() == RU.EXTERNAL_ADDRESS)
//	                  || (jFact.getSlotValue("child").type() == RU.FACT)) {
//	              child = jFact.getSlotValue("child").factValue(engineArchE.getGlobalContext()).getFactId();
//	          } else {
//	              child = VOUpdate.strToIntFactId(jFact.getSlotValue("child").stringValue(engineArchE.getGlobalContext()));
//	          }
//	          
//            
//            
//            RelationshipVO vo = new RelationshipVO(factId, jFact.getName().toString(),
//                    typeVO, source, parent, child,ui);
//
//            project.getRelationships().add(vo);
//            

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
     * Asserts a fact that maps to a parameter of a relationship between responsibilities.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param paramName The name of the parameter - maps to a fact is the ArchE Core.
     * @param ownerSlot The integer owner (parent) slot of the fact.
     * @param valueSlot The string value slot of the fact.
     * @param dataType The parameter value can be any one of string, boolean or float. This is a
     *            constant that lets us know which data type value is to be asserted.
     */
    public void assertRespoRelationParameter(Rete engineArchE, String paramName, int ownerSlot,
            String valueSlot, int dataType) {
        try {

            /* The following are the valid values for the parameter - paramName (ArchEv1.0) */
            //ModifiabilityReasoningFrameworks::P_Probability
            /* Contructing a temp Fact object with the correct Fact Name and slots. */
            Fact fTemp = new Fact(paramName, engineArchE);
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
     * Modifies a fact in the ArchE Core that maps to a relationship between responsibilities. Even
     * though the findFactByFact method exposed by the Rete class is faster than findFactByID (as
     * documented in the API documentation) we have changed the implementation to use the latter
     * because the former implies complete knowledge of the fact structures in the Arche Core. Using
     * the latter allows for modifiability over performance - becasuse the fact structures can
     * change without "finding the fact in the core bridge" ever becoming a problem.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param voRshipInitial The Relationship Value Object which is to be modified.
     * @param parentSlotM The modified integer factId that is the parent in the relationship.
     * @param childSlotM The modified integer factId that is the child in the relationship.
     */
    public void modifyRespoRelation(Rete engineArchE, RelationshipVO voRshipInitial,
            int parentSlotM, int childSlotM) {
        try {
            /* Finding the Fact using the FactId */
            Fact fModify = engineArchE.findFactByID(voRshipInitial.getFactId());

            /* Checking the slots that changed and modifying the fact accordingly */
            if (voRshipInitial.getParentFactId() != parentSlotM) {
                engineArchE.modify(fModify, "parent", new FactIDValue(engineArchE
                        .findFactByID(parentSlotM)));
            }
            if (voRshipInitial.getChildFactId() != childSlotM) {
                engineArchE.modify(fModify, "child", new FactIDValue(engineArchE
                        .findFactByID(childSlotM)));
            }
            //For facts (not of type C_) that are modified, source = User. This slot is like an
            // enumeration in the Core and therefore is stored as a symbol
            if (voRshipInitial.getSource() != CoreFact.USER) {
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
     * Modifies a fact that maps to a parameter of a relationship between responsibilities. Even
     * though the findFactByFact method exposed by the Rete class is faster than findFactByID (as
     * documented in the API documentation) we have changed the implementation to use the latter
     * because the former implies complete knowledge of the fact structures in the Arche Core. Using
     * the latter allows for modifiability over performance - becasuse the fact structures can
     * change without "finding the fact in the core bridge" ever becoming a problem.
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
    public void modifyRespoRelationParameter(Rete engineArchE, ParameterVO voParamInitial,
            int ownerSlotI, int ownerSlotM, String valueSlotM, int dataType) {
        try {
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
     * Retracts a responsibility relationship and the parameters associated to the responsibility
     * relationship. Even though a fact to be retracted can be found by ID this method is documented
     * in the Java API for Jess as being very slow. Therefore it is better to construct a fact
     * object within the Core Bridge and then retract it form the ArchE Core. This however, induces
     * the overhead of ensuring that the fact being deleted from the view has a replica in the Core -
     * a consistency concern.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param applyToSlotD the fact Id of the Responsbility Relation (Fact) that is to be deleted.
     */
    public void retractRespoRelation(Rete engineArchE, int applyToSlotD) {
        try {
            /* Contructing the Fact object using the initial Responsibility VO */
            Fact fTemp = new Fact("Planner::C_DeleteResponsibilityRelation", engineArchE);
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
            throw new RuntimeException(je.getMessage(), je);
        }
    }

    /**
     * Retracts a fact that maps to a parameter of a relationship between responsibilities. Even
     * though the findFactByFact method exposed by the Rete class is faster than findFactByID (as
     * documented in the API documentation) we have changed the implementation to use the latter
     * because the former implies complete knowledge of the fact structures in the Arche Core. Using
     * the latter allows for modifiability over performance - becasuse the fact structures can
     * change without "finding the fact in the core bridge" ever becoming a problem.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param factIdD the FactId of the parameter fact to be deleted
     */
    public void retractRespoRelationParameter(Rete engineArchE, int factIdD) {
        try {
            /* Finding the Fact using the FactId */
            Fact fTemp = new Fact(engineArchE.findFactByID(factIdD));

            /* Retracting the parameter Fact */
            engineArchE.retract(fTemp);
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
}