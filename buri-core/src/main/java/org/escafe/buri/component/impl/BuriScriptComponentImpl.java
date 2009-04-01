/*
 * 作成日: 2006/04/03
 *
 */
package org.escafe.buri.component.impl;

import java.util.Iterator;
import java.util.Set;

import org.escafe.buri.component.BuriComponent;
import org.escafe.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.escafe.buri.oouo.internal.structure.BuriToolType;

public class BuriScriptComponentImpl implements BuriComponent {

    public String getBuriExecuteSource(String tgtObjName, BuriToolType tool) {
        StringBuffer buffer = new StringBuffer();

        Iterator ite = tool.getExtendedAttribute().iterator();
        while (ite.hasNext()) {
            BuriExtendedAttributeType attri = (BuriExtendedAttributeType) ite.next();
            String scriptName = attri.getName();
            String script = attri.getValue();
            script = convertTextToSource(script);
            buffer.append("    Script script = scriptFactory.getScript(\"").append(scriptName).append("\");\n");
            buffer.append("    String scriptStr = \"").append(script).append("\";\n");
            buffer.append("    script.run(scriptStr,").append(tgtObjName).append(",sysContext.getUserContext());\n\n");
        }

        return buffer.toString();
    }

    public Set appendImportName(BuriToolType tool) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public String setupSource() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public String convertTextToSource(String text) {
        text = text.replaceAll("\\\\", "\\\\\\\\");
        text = text.replaceAll("\"", "\\\\\"");
        text = text.replaceAll("\n", "\\\\n");
        return text;
    }

}
