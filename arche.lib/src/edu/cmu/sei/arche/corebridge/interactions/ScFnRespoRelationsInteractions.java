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
import jess.Rete;

/**
 * All interactions with the ArchE Core related to relationships between scenarios &
 * responsibilities and functions & responsibilities.
 * 
 * @author Neel Mullick
 */
public class ScFnRespoRelationsInteractions extends Interactions {

    /**
     * Asserts a fact in the ArchE Core that maps to a relationship between a scenario & a
     * responsibility and a function & a responsibility.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is asserted.
     * @param parentSlot The integer factId that is the parent (scenario/fucntion) in the
     *            relationship.
     * @param childSlot The integer factId that is the child (responsibility) in the relationship.
     */
    public void assertScFnRespoRelation(Rete engineArchE, int parentSlot, int childSlot) {
        try {
            /* Contructing the Fact object using the parameters */
            Fact fTemp = new Fact("Planner::C_AddTranslationRelation", engineArchE);
            fTemp.setSlotValue("parent", new FactIDValue(engineArchE.findFactByID(parentSlot)));
            fTemp.setSlotValue("child", new FactIDValue(engineArchE.findFactByID(childSlot)));

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
     * Modifies a fact in the ArchE Core that maps to a relationship between a scenario & a
     * responsibility and a function & a responsibility.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param applyToSlot The initial integer factId that is the parent (scenario/function) in the
     *            relationship. This will be the fact id of the initial translation relation that
     *            will now be retracted from the core and replaced with a new translation relation.
     * @param parentSlotM The modified integer factId that is the parent (scenario/function) in the
     *            relationship.
     * @param childSlotM The modified integer factId that is the child (responsibility) in the
     *            relationship.
     */
    public void modifyScFnRespoRelation(Rete engineArchE, int applyToSlot, int parentSlotM,
            int childSlotM) {
        try {
            /* Contructing the Fact object using the initial Responsibility VO */
            Fact fTemp = new Fact("Planner::C_ChangeTranslationRelation", engineArchE);

            fTemp.setSlotValue("parent", new FactIDValue(engineArchE.findFactByID(parentSlotM)));
            fTemp.setSlotValue("child", new FactIDValue(engineArchE.findFactByID(childSlotM)));
            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlot)));

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
     * Retracts a fact in the ArchE Core that maps to a relationship between a scenario & a
     * responsibility and a function & a responsibility.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param applyToSlot The integer (parent-scenario/function) factId for which the (translation)
     *            relationship is to be deleted.
     */
    public void retractScFnRespoRelation(Rete engineArchE, int applyToSlot) {
        try {
            /* Contructing the Fact object using the initial Responsibility VO */
            Fact fTemp = new Fact("Planner::C_DeleteTranslationRelation", engineArchE);
            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlot)));

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
}