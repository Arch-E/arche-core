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

/**
 * Describes a pair of types of operands that are associated with a given relationship type.
 * <p>
 * Example: if relationship type "Preceds" can exist between two responsibilities, then in the
 * "Precedes" RelationshipTypeVO instance of "Precedes", field allowedOperands will contain an
 * OperandsVO object than contains RESPONSIBILITY as the value for lhs and rhs.
 * <p>
 * This is not a fact in the fact base. It comes from the RF configuration file.
 * 
 * @author Paulo Merson
 */
public class OperandsVO {

    /**
     * Type of element at the left hand side of the relationship. Use constant values FUNCTION,
     * PARAMETER, RESPONSIBILITY.
     */
    private String             lhs;

    /**
     * Element at the right hand side of the relationship. Use constant values FUNCTION, PARAMETER,
     * RESPONSIBILITY.
     */
    private String             rhs;

    /** Is relationship bidirectional */
    private boolean            bidirectional;

    //
    // Constants
    //
    public static final String FUNCTION       = "ArchEFunction";

    public static final String PARAMETER      = "ArchEParameter";

    public static final String RESPONSIBILITY = "ArchEResponsibility";

    public static final String SCENARIO       = "ArchEScenario";

    public OperandsVO() {
    }

    /**
     * @param lhs
     * @param rhs
     * @param bidirectional
     */
    public OperandsVO(String lhs, String rhs, boolean bidirectional) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.bidirectional = bidirectional;
    }

    /**
     * @return Returns the lhs.
     */
    public String getLhs() {
        return lhs;
    }

    /**
     * @param lhs The lhs to set.
     */
    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    /**
     * @return Returns the rhs.
     */
    public String getRhs() {
        return rhs;
    }

    /**
     * @param rhs The rhs to set.
     */
    public void setRhs(String rhs) {
        this.rhs = rhs;
    }

    /**
     * @return Returns the bidirectional.
     */
    public boolean isBidirectional() {
        return bidirectional;
    }

    /**
     * @param bidirectional The bidirectional to set.
     */
    public void setBidirectional(boolean bidirectional) {
        this.bidirectional = bidirectional;
    }

}