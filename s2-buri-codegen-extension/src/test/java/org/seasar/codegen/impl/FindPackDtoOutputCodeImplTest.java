/*
 * Copyright 2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.codegen.impl;

import java.io.File;
import java.util.Map;

import org.seasar.codegen.ImportCodeData;
import org.seasar.codegen.OutputCode;
import org.seasar.extension.unit.S2TestCase;

public class FindPackDtoOutputCodeImplTest extends S2TestCase {
    private String PATH = "org/seasar/codegen/impl/FindPackDtoOutputCode.dicon";

    private ImportCodeData importCodeData_;

    private OutputCode outputCode_;

    public FindPackDtoOutputCodeImplTest(String arg0) {
        super(arg0);
    }

    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }

    public void testDtoOutput() {
        // File src = new File("test/é©ìÆê∂ê¨óp.csv");
        File src = new File("test/codegen.csv");
        Map tableData = importCodeData_.readCodeData(src);

        outputCode_.generateCode(new File("testsrc"), tableData);

    }

}
