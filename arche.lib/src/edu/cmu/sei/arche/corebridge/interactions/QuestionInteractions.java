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
import jess.JessException;
import jess.RU;
import jess.Rete;
import jess.Value;
import jess.ValueVector;
import edu.cmu.sei.arche.export.ExportToSQL;
import edu.cmu.sei.arche.vo.QuestionParser;
import edu.cmu.sei.arche.vo.QuestionToUserVO;

/**
 * All interactions with the ArchE Core related to "Questions to user"
 * 
 * @author Paulo Merson
 */
public class QuestionInteractions extends Interactions {

    /**
     * Class that gives access to resource bundle and is able to parser static text and replace it
     * with tags.
     */
    private QuestionParser questionParser;

    /**
     * Constructor that receives the question parser used to access question properties.
     */
    public QuestionInteractions(QuestionParser questionParser) {
        this.questionParser = questionParser;
    }

    /**
     * Constructor that instantiates a new parser for use in this class. It works as long as we
     * don't access properties in the properties file that require replacing tags with data in the
     * core, because this local parser doesn't have a facade and cannot point to the proper fact
     * base.
     */
    public QuestionInteractions() {
        this.questionParser = new QuestionParser();
    }

    /**
     * Modifies a MAIN::AskQuestion fact in the core. Different from other facts, in this case there
     * is only one slot to change: answer. Moreover, the new value for that slot is already in the
     * passed VO object.
     * 
     * @param engineArchE The centrally located Rete object (Jess instance).
     * @param voWithAnswer object containing the question and the answer.
     */
    public void modifyQuestion(Rete engineArchE, QuestionToUserVO voWithAnswer) {
        try {
            String answer[] = voWithAnswer.getAnswer();
            if (answer == null || answer.length == 0) {
                // no answer was provided--there's nothing to do
                return;
            }

            Fact originalFact = engineArchE.findFactByID(voWithAnswer.getFactId());
            if (originalFact == null) {
                // it's possible that the question was retracted by the core because another
                // question was answered. In this case, we don't do anything.
                return;
            }
//            Fact fModify = new Fact(originalFact);

            ValueVector list = new ValueVector();

            String questionType = questionParser.getQuestionType(voWithAnswer);

            if (questionType.equals(QuestionParser.TYPE_YES_NO)) {
                // the answer in the VO contains "yes" or "no". It's stored in the fact as
                // symbols (RU.ATOM) with the same name.
                Value v = new Value(answer[0], RU.SYMBOL);
                list.add(v);
            } else {
                // for single choice, multiple choice, input value and alert the value is string
                for (int i = 0; i < answer.length; ++i) {
                    Value v = new Value(answer[i], RU.STRING);
                    list.add(v);
                }
            }
            
            Value answerSlotValue = new Value(list, RU.LIST);
//            engineArchE.executeCommand("(assert (xxx1 Here " + originalFact.getFactId() + " " + fModify.getFactId() + "))");
            engineArchE.modify(originalFact, "answer", answerSlotValue);
//            engineArchE.executeCommand("(assert (xxx2 Here " + originalFact.getFactId() + " " + fModify.getFactId() + "))");
            engineArchE.modify(originalFact, "answerAvailable", new Value("true", RU.SYMBOL));
//            engineArchE.executeCommand("(assert (xxx3 Here " + originalFact.getFactId() + " " + fModify.getFactId() + "))");
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