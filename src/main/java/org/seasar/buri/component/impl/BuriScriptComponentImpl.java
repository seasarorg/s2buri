/*
 * �쐬��: 2006/04/03
 *
 */
package org.seasar.buri.component.impl;

import java.util.Iterator;
import java.util.Set;

import org.seasar.buri.component.BuriComponent;
import org.seasar.buri.oouo.internal.structure.BuriExtendedAttributeType;
import org.seasar.buri.oouo.internal.structure.BuriToolType;

public class BuriScriptComponentImpl implements BuriComponent {

    public String getBuriExecuteSource(String tgtObjName,BuriToolType tool) {
        StringBuffer buffer = new StringBuffer();

        Iterator ite = tool.getExtendedAttribute().iterator();
        while(ite.hasNext()) {
            BuriExtendedAttributeType attri = (BuriExtendedAttributeType)ite.next();
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
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        return null;
    }

    public String setupSource() {
        // TODO �����������ꂽ���\�b�h�E�X�^�u
        return null;
    }

    public String convertTextToSource(String text) {
        text = text.replaceAll("\\\\","\\\\\\\\");
        text = text.replaceAll("\"","\\\\\"");
        text = text.replaceAll("\n","\\\\n");
        return text;
    }

}