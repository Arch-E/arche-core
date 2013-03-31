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

/**
 * All interactions with the ArchE Core related to the "function" architectural element.
 * 
 * @author Paulo Merson
 */
public class FunctionInteractions extends Interactions {

    /**
     * Asserts a function fact in the ArchE Core.
     * 
     * @param engineArchE The centrally located Rete object (Jess instance).
     * @param descriptionSlot The string description slot of the fact.
     */
    public void assertFunction(Rete engineArchE, String idSlot, String descriptionSlot) {
        try {
            // Contructing the command fact object using the arguments
            Fact fTemp = new Fact("Planner::C_AddFunction", engineArchE);
            if (idSlot != null && idSlot.trim() != "") {
                fTemp.setSlotValue("functionID", new Value(idSlot, RU.STRING));
            }
            if (descriptionSlot != null && descriptionSlot.trim() != "") {
                fTemp.setSlotValue("description", new Value(descriptionSlot, RU.STRING));
            }
            // slot state is ignored

            // Asserting the command fact
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
     * Modifies a function fact (indirectly) by asserting a command fact. (Internally, the core Jess
     * rule will delete the old function and insert a new one with the changed data slots).
     * 
     * @param engineArchE The centrally located Rete object (Jess instance).
     * @param applyToSlot The fact id of the function to change
     * @param idSlot The modified string ID of the fact
     * @param descriptionSlot The modified string description of the fact.
     */
    public void modifyFunction(Rete engineArchE, int applyToSlot, String idSlot,
            String descriptionSlot) {
        try {
            // Contructing the command fact object used to perform the operation
            Fact fTemp = new Fact("Planner::C_ChangeFunction", engineArchE);

            fTemp.setSlotValue("functionID", new Value(idSlot, RU.STRING));
            fTemp.setSlotValue("description", new Value(descriptionSlot, RU.STRING));
            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlot)));

            // slot state is ignored

            // Asserting the command fact
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
     * Retracts the function fact (indirectly) by asserting a command fact.
     * 
     * @param engineArchE The centrally located Rete object using which the fact is retracted.
     * @param applyToSlotD the fact Id of the function to be deleted.
     * @throws je Either a Nested Jess Exception OR a JessException
     * @throws e Exception
     */
    public void retractFunction(Rete engineArchE, int applyToSlot) {
        try {
            // Contructing the command fact object used to perform the operation
            Fact fTemp = new Fact("Planner::C_DeleteFunction", engineArchE);
            fTemp.setSlotValue("applyTo", new FactIDValue(engineArchE.findFactByID(applyToSlot)));

            // asserting the Fact to delete the function
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