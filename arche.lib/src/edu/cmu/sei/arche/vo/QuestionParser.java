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

package edu.cmu.sei.arche.vo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import edu.cmu.sei.arche.corebridge.facade.ArchEFacade;

/**
 * Parse question text from property file Match the patterns in the questions and replace them with
 * data
 * 
 * @author Henry Chen
 */
public class QuestionParser {

    public static final String TYPE_ALERT           = "alert";

    public static final String TYPE_SINGLE_CHOICE   = "singleChoice";

    public static final String TYPE_MULTIPLE_CHOICE = "multipleChoice";

    public static final String TYPE_INPUT_VALUE     = "inputValue";

    public static final String TYPE_YES_NO          = "yesNo";

    /** Resource bundle of the question text for basic questions */
    private ResourceBundle     questionBundle;


    /** questions that are updated according to the change of available reasoning frameworks **/
    private Map lookupDynamicQuestions;    
    
    /** ArchEFacade to read fact slot value */
    private ArchEFacade        facade;

    /** QuestionToUserVO used by the parser */
    private QuestionToUserVO   questionVO;

    /**
     * Constructor that uses the default locale to load the questions properties file. This parser
     * cannot access core facts until the facade property is set.
     */
    public QuestionParser() {
        this(null, Locale.getDefault());
    }

    /**
     * Constructor that uses the default locale to load the questions properties file and the passed
     * facade to access core facts.
     * 
     * @param facade a valid instance of the facade used to access the core
     */
    public QuestionParser(ArchEFacade facade) {
        this(facade, Locale.getDefault());
    }

    /**
     * Constructor that uses the specified locale to load the questions properties file. It will
     * look for Questions.properties in package edu.cmu.sei.arche.vo if uses the default locale. It
     * will look for Questions_fr.properties if using the French locale and so on.
     * 
     * @param facade a valid instance of the facade used to access the core
     */
    public QuestionParser(ArchEFacade facade, Locale locale) {
        this.facade = facade;                
        this.questionBundle = ResourceBundle.getBundle("edu.cmu.sei.arche.core.Questions", locale);
//        this.questionBundle = ResourceBundle.getBundle("edu.cmu.sei.arche.vo.Questions", locale);
        lookupDynamicQuestions = new HashMap(0);
    }

    public void updateDynamicQuestions(InputStream stream) throws IOException {  
        Properties properties = new Properties();
        properties.load(stream);
        for(Enumeration eKeys = properties.keys(); eKeys.hasMoreElements(); ){
        	String key = (String)eKeys.nextElement();
        	if(lookupDynamicQuestions.containsKey(key))
        		lookupDynamicQuestions.remove(key);
        	lookupDynamicQuestions.put(key, properties.get(key));
        }
    }

    public String getDynamicQuestions(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return (String)lookupDynamicQuestions.get(key);
    }
    
//  public void reload(){  
//        this.questionBundle = ResourceBundle.getBundle("edu.cmu.sei.arche.core.Questions", Locale.getDefault());        
//  }

    /**
     * Get question type from properties file. It's not necessary to parse because the type does not
     * have tags to replace.
     * 
     * @return translated category text
     */
    public String getQuestionType(QuestionToUserVO questionVO) {
    	String value = this.getDynamicQuestions(questionVO.getQuestionId() + ".questionType");
    	if(value == null)
    		return questionBundle.getString(questionVO.getQuestionId() + ".questionType");
    	else
    		return value;
    }

    /**
     * Get question category from properties file, parse it replacing the tags and return it.
     * 
     * @param questionId
     * @return translated category text
     */
    //    public String getQuestionCategory(String questionId) {
    //        String categorySource = questionBundle.getString(questionId + ".category");
    //        return parseText(categorySource);
    //    }
    /**
     * Parse question category from the property file and translate it based on the <tag>rules.
     * 
     * @param questionVO
     * @return translated category text
     */
    public String getQuestionCategory(QuestionToUserVO questionVO) {
    	String categorySource = this.getDynamicQuestions(questionVO.getQuestionId() + ".category");
    	if(categorySource == null)
    		categorySource = questionBundle.getString(questionVO.getQuestionId() + ".category");
        return parseText(categorySource);
    }

    /**
     * Parse question purpose from the property file and translate it based on the <tag>rules.
     * 
     * @param questionVO
     * @return translated category text
     */
    public String getQuestionPurpose(QuestionToUserVO questionVO) {
        this.questionVO = questionVO;        
    	String value = this.getDynamicQuestions(questionVO.getQuestionId() + ".purpose");
    	if(value == null)
    		value = questionBundle.getString(questionVO.getQuestionId() + ".purpose");
        return parseText(value);
    }

    /**
     * Get question text from properties file, parse it replacing the tags and return it.
     * 
     * @param questionVO
     * @return
     */
    public String getQuestionText(QuestionToUserVO questionVO) {
        this.questionVO = questionVO;
        String srcQuestionText = getQuestionSource(questionVO);
        return parseText(srcQuestionText);
    }

    /**
     * Parse question options from the property file and vo translating it based on the <tag>rules.
     * Different from category, purpose and question-text, the options should be assembled as a list
     * of String objects.
     * 
     * @return list of String objects
     */
    public List getQuestionOptionLabels(QuestionToUserVO questionVO) {
        this.questionVO = questionVO;
        int j = 1;
        List options = new ArrayList();
        while (true) {
            String optionSource = null;
            try {
            	optionSource = this.getDynamicQuestions(questionVO.getQuestionId() + ".optionLabel" + j);
            	if(optionSource == null)
            		optionSource = questionBundle.getString(questionVO.getQuestionId() + ".optionLabel" + j);
            	
                int posStartTag = optionSource.indexOf('<');
                int posEndTag = optionSource.indexOf('>');
                if (posStartTag >= 0) {
                    // there's a tag
                    String beforeTag = optionSource.substring(0, posStartTag);
                    String tag = optionSource.substring(posStartTag, posEndTag + 1);
                    String afterTag = optionSource.substring(posEndTag + 1, optionSource.length());
                    List optionVals = parseToList(tag);
                    for (Iterator it = optionVals.iterator(); it.hasNext();) {
                        String optionVal = (String) it.next();
                        options.add(beforeTag + optionVal + afterTag);
                    }
                } else {
                    // there's no tag to replace
                    options.add(optionSource);
                }
                j++;
            } catch (MissingResourceException e) {
                // no more optionLabelN property, can exit the loop
                break;
            }
        }
        return options;
    }

    /**
     * Get the numColumns property from the properties file. It's not necessary to parse because
     * this property cannot contain tags to replace.
     * 
     * @return numColumns property converted to an integer; returns 1 if the property is not present
     */
    public int getNumColumns(QuestionToUserVO questionVO) {
        String strNumCols = null;
        try {
        	strNumCols = this.getDynamicQuestions(questionVO.getQuestionId() + ".numColumns");
        	if(strNumCols == null)
        		strNumCols = questionBundle.getString(questionVO.getQuestionId() + ".numColumns");
        } catch (MissingResourceException e) {
            strNumCols = "1"; // default value if the property is not present
        }
        return Integer.parseInt(strNumCols);
    }

    /**
     * Given an input text with <tag>, return the destination text with <tag>replaced according to
     * the rules.
     * 
     * @param sourceText
     * @return replaced text
     */
    private String parseText(String sourceText) {
        StringBuffer destText = new StringBuffer();
        StringBuffer patternStr = new StringBuffer();

        char c;

        for (int i = 0; i < sourceText.length(); i++) {
            c = sourceText.charAt(i);
            switch (c) {
            case '<':
                // If find the begining of a pattern
                if (patternStr.length() == 0) {
                    // if patternStr is empty, append the char to it
                    patternStr.append(c);
                } else {
                    // if patternStr is not empty, throw error
                    destText.append(patternStr);
                    patternStr.append(c);
                }

                break;

            case '>':
                // If find the end of a pattern, call parse(patternStr) to proceed
                // and append the result to the destQuestionText
                patternStr.append(c);
                // This try block is for test
                try {
                    destText.append(parse(patternStr.toString()));
                } catch (Exception e) {
//                    System.out.println(e.getClass() + "Exception when parse text: "
//                            + e.getMessage());
                    destText.append("[ERROR! " + patternStr + "]");
                }
                patternStr.setLength(0);
                break;

            default:
                if (patternStr.length() == 0) {
                    // If not collecting pattern chars, just add the char to the destText.
                    destText.append(c);
                } else {
                    // If the pattern string is not ended yet, continue to add the char to the
                    // pattern string.
                    patternStr.append(c);
                }

            }

        }

        if (patternStr.length() != 0) {
            // Add the last part to the destQuestionText.
            destText.append(patternStr);
        }
        return destText.toString();

    }

    /**
     * Parse the patterns and replace them with data <1>tag is replaced with text of first parameter ,
     * i.e., QuestionToUserVO.parameters[0] <2+>tag is replaced with text of all parameters from the
     * 2nd to the last in QuestionToUserVO.parameters. The Strings should be separated by comma.
     * <3:desc>third parameter (QuestionToUserVO.parameter[2]) is necessarily a fact id in this
     * case. Retrieve the fact from ArchE Core and replace the tag with the value in slot ��desc�� in
     * that fact. Note that a call to core is necessary otherwise we��ll need to use introspection to
     * translate the slot name into a getter method call to some generic fact VO class. <0:desc>tag
     * zero refers to QuestionToUserVO.parentFactId. Therefore <0:desc>is replaced with the value of
     * slot ��desc�� in that fact. <4+:desc>tag is replaced with the value of slot ��desc�� in the facts
     * whose fact ids are the contents of parameters from the 4th to the last in
     * QuestionToUserVO.parameters. The Strings should be separated by comma.
     * 
     * @param patternStr
     * @return
     */
    private String parse(String patternStr) {
        int paramIndex;
        StringBuffer result = new StringBuffer();
        String[] params = questionVO.getParameters();

        // Index of delimeters
        int idxColumn = patternStr.indexOf(":");
        int idxPlus = patternStr.indexOf("+");

        if (idxColumn == -1) {
            // if tag is <n> or <n+>

            if (idxPlus == -1) {
                // tag is <n>
                paramIndex = Integer.parseInt(patternStr.substring(1, patternStr.length() - 1));
                result.append(params[paramIndex - 1]);

            } else {
                // tag is <n+>
                paramIndex = Integer.parseInt(patternStr.substring(1, patternStr.length() - 2));
                result.append(params[paramIndex - 1]);
                for (int i = paramIndex; i < params.length; i++) {
                    result.append(", " + params[i]);
                }
            }

        } else {
            // if tag is <*:*> or <*+:*>
            int factId;
            String slotName;
            if (idxPlus == -1) {
                // tag is <*:*>
                // Get the first part - parameter index
                paramIndex = Integer.parseInt(patternStr.substring(1, idxColumn));
                if (paramIndex == 0) {
                    // when <0:*>, get parentId
                    factId = questionVO.getParentFactId();
                } else {
                    // when <n:*>, (n!=0), get parameter[n-1] value
                    factId = Integer.parseInt(params[paramIndex - 1]);
                }

                slotName = patternStr.substring(idxColumn + 1, patternStr.length() - 1);
                result.append(facade.getSlotValue(factId, slotName));

            } else {
                // tag is <*+:*>
                // Get the first part - parameter index
                paramIndex = Integer.parseInt(patternStr.substring(1, idxPlus));

                factId = new Integer(params[paramIndex - 1]).intValue();
                slotName = patternStr.substring(idxColumn + 1, patternStr.length() - 1);

                result.append(facade.getSlotValue(factId, slotName));
                for (int i = paramIndex; i < params.length; i++) {
                    factId = Integer.parseInt(params[i]);
                    result.append(", " + facade.getSlotValue(factId, slotName));
                }
            }
        }
        return result.toString();
    }

    /**
     * Parse the patterns and replace them with data <1>tag is replaced with text of first option,
     * i.e., QuestionToUserVO.options[0] <2+>tag is replaced with text of all parameters from the
     * 2nd to the last in QuestionToUserVO.parameters. The Strings should be separate items in the
     * list. <3:desc>third parameter (QuestionToUserVO.parameter[2]) is necessarily a fact id in
     * this case. Retrieve the fact from ArchE Core and replace the tag with the value in slot
     * ��desc�� in that fact. Note that a call to core is necessary otherwise we��ll need to use
     * introspection to translate the slot name into a getter method call to some generic fact VO
     * class. <0:desc>tag zero refers to QuestionToUserVO.parentFactId. <0:desc>is replaced with the
     * value of slot ��desc�� in that fact. <4+:desc>tag is replaced with the value of slot ��desc�� in
     * the facts whose fact ids are the contents of parameters from the 4th to the last in
     * QuestionToUserVO.parameters. The Strings should be separate items in the list.
     * 
     * @param patternStr
     * @return
     */
    private List parseToList(String patternStr) {
        int index;

        String[] options = questionVO.getOptions();
        List result = new ArrayList();
        // Index of delimeters
        int idxColumn = patternStr.indexOf(":");
        int idxPlus = patternStr.indexOf("+");

        if (idxColumn == -1) {
            // if tag is <n> or <n+>

            if (idxPlus == -1) {
                // tag is <n>
                index = Integer.parseInt(patternStr.substring(1, patternStr.length() - 1));
                result.add(options[index - 1]);

            } else {
                // tag is <n+>
                index = Integer.parseInt(patternStr.substring(1, patternStr.length() - 2));
                for (int i = index - 1; i < options.length; i++) {
                    result.add(options[i]);
                }
            }

        } else {
            // if tag is <*:*> or <*+:*>
            int factId;
            String slotName;
            if (idxPlus == -1) {
                // tag is <*:*>
                // Get the first part - parameter index
                index = Integer.parseInt(patternStr.substring(1, idxColumn));
                if (index == 0) {
                    // when <0:*>, get parentId
                    factId = questionVO.getParentFactId();
                } else {
                    // when <n:*>, (n!=0), get parameter[n-1] value
                    factId = Integer.parseInt(options[index - 1]);
                }

                slotName = patternStr.substring(idxColumn + 1, patternStr.length() - 1);
                result.add(facade.getSlotValue(factId, slotName));

            } else {
                // tag is <*+:*>
                index = Integer.parseInt(patternStr.substring(1, idxPlus));
                slotName = patternStr.substring(idxColumn + 1, patternStr.length() - 1);
                for (int i = index - 1; i < options.length; i++) {
                    factId = Integer.parseInt(options[i]);
                    result.add(facade.getSlotValue(factId, slotName));
                }
            }
        }
        return result;
    }

    /**
     * @return Returns the facade.
     */
    public ArchEFacade getFacade() {
        return facade;
    }

    /**
     * @param facade The facade to set.
     */
    public void setFacade(ArchEFacade facade) {
        this.facade = facade;
    }

    /**
     * get the question source
     * 
     * @return srouce question text
     */
    public String getQuestionSource(QuestionToUserVO questionVO) {
        String questionId = questionVO.getQuestionId();
    	String srcQuestionText = this.getDynamicQuestions(questionId + ".question");
    	if(srcQuestionText == null)
    		srcQuestionText = questionBundle.getString(questionId + ".question");        
        return srcQuestionText;
    }

}