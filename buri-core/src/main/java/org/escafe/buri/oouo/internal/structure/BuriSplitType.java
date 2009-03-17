/*
 * 作成日: 2006/03/20
 *
 */
package org.escafe.buri.oouo.internal.structure;

import java.util.ArrayList;
import java.util.List;

public class BuriSplitType {
    private String type;
    private List transitionRef = new ArrayList();
    private boolean isXOR = true;

    public static final String OOUOTHIS = "Split";

    public String getType() {
        return type;
    }

    public static final String setType_ATTRI = "Type";

    public void setType(String type) {
        this.type = type;
    }

    public static final String addTransitionRef_ELEMENT = "TransitionRef";
    public static final String addTransitionRef_ATTRI = "Id";

    public void addTransitionRef(String ref) {
        transitionRef.add(ref);
    }

    public static final String setupEnd_OOUOFIN = "";

    public void setupEnd() {
        if (type.endsWith("AND")) {
            isXOR = false;
        }
    }

    public boolean isXor() {
        return isXOR;
    }

    public boolean isAnd() {
        return (!isXOR);
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("[");
        buff.append("/type=").append(type);
        buff.append("/transitionRef=").append(transitionRef);
        buff.append("]");
        return buff.toString();
    }

}
