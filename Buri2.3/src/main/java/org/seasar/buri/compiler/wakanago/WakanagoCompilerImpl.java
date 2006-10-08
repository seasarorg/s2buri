/*
 * �쐬��: 2006/03/26
 *
 */
package org.seasar.buri.compiler.wakanago;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.seasar.buri.compiler.BuriCompiler;
import org.seasar.buri.compiler.CompilePackage;
import org.seasar.buri.compiler.CompileProcess;
import org.seasar.buri.engine.ParticipantProvider;
import org.seasar.buri.oouo.internal.structure.BuriPackageType;
import org.seasar.buri.oouo.internal.structure.BuriWorkflowProcessType;
import org.seasar.buri.oouo.reader.OouoReader;
import org.seasar.buri.util.packages.BuriExePackages;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.log.Logger;

public class WakanagoCompilerImpl implements BuriCompiler {
    private static Logger logger = Logger.getLogger(WakanagoCompilerImpl.class);
    
    private OouoReader oouoReader;
    private S2Container container;
    
    private CompileProcess compileProcess;
    private CompilePackage compilePackage;
    
    public BuriExePackages CompileInputStream(InputStream workFlowIs,ParticipantProvider provider) {
        logger.info("�ǂݍ��݊J�n");
        Object data = oouoReader.readInputStream(workFlowIs);
        assert data instanceof BuriPackageType;
        logger.info("�R���p�C���J�n");
        BuriExePackages result = Compile((BuriPackageType)data,provider);
        logger.info("�R���p�C���I��");
        return result;
    }
    
    public BuriExePackages CompileResource(String src,ParticipantProvider provider) {
        logger.info(src + "�ǂݍ��݊J�n");
        Object data = oouoReader.readResource(src);
        assert data instanceof BuriPackageType;
        logger.info(src + "�R���p�C���J�n");
        BuriExePackages result = Compile((BuriPackageType)data,provider);
        logger.info(src + "�R���p�C���I��");
        return result;
    }

    public BuriExePackages CompileFile(String src,ParticipantProvider provider) {
        logger.info(src + "�ǂݍ��݊J�n");
        Object data = oouoReader.readFile(src);
        assert data instanceof BuriPackageType;
        logger.info(src + "�R���p�C���J�n");
        BuriExePackages result = Compile((BuriPackageType)data,provider);
        logger.info(src + "�R���p�C���I��");
        return result;
    }
    
    public BuriExePackages CompileObject(BuriPackageType buriPackage,ParticipantProvider provider) {
        logger.info("�R���p�C���J�n");
        BuriExePackages result = Compile(buriPackage,provider);
        logger.info("�R���p�C���I��");
        return result;
    }
    
    protected BuriExePackages Compile(BuriPackageType buriPackage,ParticipantProvider provider) {
        BuriExePackages result = compilePackage.compile(buriPackage);
        result.setParticipantProvider(provider);
        Map processMap = buriPackage.getProcessById();
        Iterator ite = processMap.keySet().iterator();
        while(ite.hasNext()) {
            String procId = (String)ite.next();
            BuriWorkflowProcessType process = (BuriWorkflowProcessType)processMap.get(procId);
            compileProcess.compile(result,process,provider);
        }
        
        return result;
    }

    public CompilePackage getCompilePackage() {
        return compilePackage;
    }

    public void setCompilePackage(CompilePackage compilePackage) {
        this.compilePackage = compilePackage;
    }

    public CompileProcess getCompileProcess() {
        return compileProcess;
    }

    public void setCompileProcess(CompileProcess compileProcess) {
        this.compileProcess = compileProcess;
    }

    public S2Container getContainer() {
        return container;
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    public OouoReader getOouoReader() {
        return oouoReader;
    }

    public void setOouoReader(OouoReader oouoReader) {
        this.oouoReader = oouoReader;
    }
    

}
