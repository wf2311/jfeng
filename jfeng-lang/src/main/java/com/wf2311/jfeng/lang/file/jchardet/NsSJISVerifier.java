/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is mozilla.org code.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1998
 * the Initial Developer. All Rights Reserved.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

/* 
 * DO NOT EDIT THIS DOCUMENT MANUALLY !!!
 * THIS FILE IS AUTOMATICALLY GENERATED BY THE TOOLS UNDER
 *    AutoDetect/tools/
 */

package com.wf2311.jfeng.lang.file.jchardet;

public class NsSJISVerifier extends NsVerifier {

    static int[] cclass;
    static int[] states;
    static int stFactor;
    static String charset;

    public int[] cclass() {
        return cclass;
    }

    public int[] states() {
        return states;
    }

    public int stFactor() {
        return stFactor;
    }

    public String charset() {
        return charset;
    }

    public NsSJISVerifier() {

        cclass = new int[256 / 8];

        cclass[0] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (0)));
        cclass[1] = ((((((0) << 4) | (0)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[2] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[3] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((0) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[4] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[5] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[6] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[7] = ((((((1) << 4) | (1)) << 8) | (((1) << 4) | (1))) << 16) | (((((1) << 4) | (1)) << 8) | (((1) << 4) | (1)));
        cclass[8] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[9] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[10] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[11] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[12] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[13] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[14] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[15] = ((((((1) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[16] = ((((((3) << 4) | (3)) << 8) | (((3) << 4) | (3))) << 16) | (((((3) << 4) | (3)) << 8) | (((3) << 4) | (3)));
        cclass[17] = ((((((3) << 4) | (3)) << 8) | (((3) << 4) | (3))) << 16) | (((((3) << 4) | (3)) << 8) | (((3) << 4) | (3)));
        cclass[18] = ((((((3) << 4) | (3)) << 8) | (((3) << 4) | (3))) << 16) | (((((3) << 4) | (3)) << 8) | (((3) << 4) | (3)));
        cclass[19] = ((((((3) << 4) | (3)) << 8) | (((3) << 4) | (3))) << 16) | (((((3) << 4) | (3)) << 8) | (((3) << 4) | (3)));
        cclass[20] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (4)));
        cclass[21] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[22] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[23] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[24] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[25] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[26] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[27] = ((((((2) << 4) | (2)) << 8) | (((2) << 4) | (2))) << 16) | (((((2) << 4) | (2)) << 8) | (((2) << 4) | (2)));
        cclass[28] = ((((((3) << 4) | (3)) << 8) | (((3) << 4) | (3))) << 16) | (((((3) << 4) | (3)) << 8) | (((3) << 4) | (3)));
        cclass[29] = ((((((4) << 4) | (4)) << 8) | (((4) << 4) | (5))) << 16) | (((((5) << 4) | (3)) << 8) | (((3) << 4) | (3)));
        cclass[30] = ((((((4) << 4) | (4)) << 8) | (((4) << 4) | (4))) << 16) | (((((4) << 4) | (4)) << 8) | (((4) << 4) | (4)));
        cclass[31] = ((((((0) << 4) | (0)) << 8) | (((0) << 4) | (4))) << 16) | (((((4) << 4) | (4)) << 8) | (((4) << 4) | (4)));


        states = new int[3];

        states[0] = ((((((eError) << 4) | (eError)) << 8) | (((eError) << 4) | (eError))) << 16) | (((((3) << 4) | (eStart)) << 8) | (((eStart) << 4) | (eError)));
        states[1] = ((((((eItsMe) << 4) | (eItsMe)) << 8) | (((eItsMe) << 4) | (eItsMe))) << 16) | (((((eError) << 4) | (eError)) << 8) | (((eError) << 4) | (eError)));
        states[2] = ((((((eStart) << 4) | (eStart)) << 8) | (((eStart) << 4) | (eStart))) << 16) | (((((eError) << 4) | (eError)) << 8) | (((eItsMe) << 4) | (eItsMe)));


        charset = "Shift_JIS";
        stFactor = 6;

    }

    public boolean isUCS2() {
        return false;
    }


}
