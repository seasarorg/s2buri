/*
 * ì¬“ú: 2005/09/15
 *
 */
package org.seasar.buri.common.util.template.impl;

import java.util.HashMap;


import org.seasar.buri.common.util.template.TextTemplate;
import org.seasar.extension.unit.S2TestCase;

public class TextTemplateFreeMarkerTest extends S2TestCase {
    private TextTemplate template_;
    private String PATH = "../../dicon/template.dicon";

    public TextTemplateFreeMarkerTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }
    
    public void testTextTemplate01() {
        String templateStr = "${name}‚Å‚Â`\n${val?string(\"#,##0\")}";
        HashMap data = new HashMap();
        data.put("name","makotan");
        data.put("val",new Long(100000));
        String result = template_.process(templateStr,data);
        
        assertEquals(result,"makotan‚Å‚Â`\n100,000");
        
    }

}
