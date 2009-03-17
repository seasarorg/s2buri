/*
 * 作成日: 2006/03/06
 *
 */
package org.escafe.buri.oouo.reader.impl;

import java.util.Stack;

import org.escafe.buri.oouo.reader.OouoClassDef;
import org.escafe.buri.oouo.reader.OouoClassDefFactory;
import org.seasar.framework.container.S2Container;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {
    private S2Container container;
    private OouoClassDefFactory classDefFactory;
    private Object root = null;
    private Object now = null;
    private OouoClassDef classDef;
    private Stack objStack = new Stack();
    private Stack eleStack = new Stack();
    private String parentQName;
    private String nowQName;

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (root == null) {
            root = classDefFactory.getRoot(qName);
            now = root;
            classDef = classDefFactory.create(now.getClass());

            objStack.push(now);
            eleStack.push(parentQName);
            parentQName = qName;
        } else {
            if (classDef.isChildElement(qName)) {
                objStack.push(now);
                eleStack.push(parentQName);
                parentQName = qName;

                Object child = classDef.getChildObject(qName);
                now = child;
                if (now != null) {
                    classDef = classDefFactory.create(now.getClass());
                }
            }
        }
        if (classDef.getElementName().endsWith(qName)) {
            for (int i = 0; i < attributes.getLength(); i++) {
                String tgtName = attributes.getQName(i);
                if (classDef.isAttribute(tgtName)) {
                    classDef.setAttribute(now, tgtName, attributes.getValue(i));
                }
            }
        } else {
            for (int i = 0; i < attributes.getLength(); i++) {
                String tgtName = attributes.getQName(i);
                if (classDef.isChildAttribute(qName, tgtName)) {
                    classDef.setChildAttribute(now, qName, tgtName, attributes.getValue(i));
                }
            }
        }
        nowQName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (parentQName.equalsIgnoreCase(qName)) {
            Object childObj = now;
            parentQName = (String) eleStack.pop();
            now = objStack.pop();
            if (classDef.isCallFinMethod()) {
                classDef.fin(childObj, now);
            }
            classDef = classDefFactory.create(now.getClass());
            if (classDef.isChildElement(qName)) {
                classDef.setChild(now, qName, childObj);
            }
            nowQName = classDef.getElementName();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (nowQName.equals(classDef.getElementName())) {
            String text = new String(ch, start, length);
            if (classDef.isSetTextMethod()) {
                classDef.setText(now, text);
            }
        }
    }

    public OouoClassDefFactory getClassDefFactory() {
        return classDefFactory;
    }

    public void setClassDefFactory(OouoClassDefFactory classDefFactory) {
        this.classDefFactory = classDefFactory;
    }

    public Object getRoot() {
        return root;
    }

    public void setRoot(Object root) {
        this.root = root;
    }
}
