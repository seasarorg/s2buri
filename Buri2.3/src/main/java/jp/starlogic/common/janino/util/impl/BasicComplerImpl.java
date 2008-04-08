/*
 * 作成日: 2006/05/05
 *
 */
package jp.starlogic.common.janino.util.impl;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import jp.starlogic.common.janino.exception.CompileRuntimeException;
import jp.starlogic.common.janino.exception.ParseRuntimeException;
import jp.starlogic.common.janino.exception.ScanRuntimeException;
import jp.starlogic.common.janino.util.BasicCompileInfo;
import jp.starlogic.common.janino.util.BasicCompler;

import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Location;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.escafe.buri.common.util.template.TextTemplate;
import org.escafe.buri.component.BuriComponentUtil;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.log.Logger;

public class BasicComplerImpl implements BasicCompler {
    private static Logger logger = Logger.getLogger(BasicComplerImpl.class);

    private TextTemplate template;
    private BuriComponentUtil buriComponentUtil;

    public Object Compile(BasicCompileInfo info) {
        String src = createSrc(info);
        Object successObj = compileSrc(src, info);
        return successObj;
    }

    protected String createSrc(BasicCompileInfo info) {
        HashMap<String, Object> context = new HashMap<String, Object>();
        context.put(info.getBaseObjectName(), info.getBaseObject());
        context.put("buriComponentUtil", buriComponentUtil);
        return template.processResource(info.getTemplateFileName(), context);
    }

    protected Object compileSrc(String src, BasicCompileInfo info) {
        Object data;
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("compile " + info.getOutputClassName() + "\n" + src);
            }
            Scanner scanner = new Scanner(null, new StringReader(src));
            Class baseClass = info.getBaseClass();
            Class interfaceClass[] = info.getInterfaceClass();
            ClassLoader parentClassLoader = info.getParentClassLoader();
            data = ClassBodyEvaluator.createFastClassBodyEvaluator(scanner, info.getOutputClassName(), baseClass, interfaceClass, parentClassLoader);
        } catch (CompileException e) {
            compileProcessError(src, info.getBaseObject(), null ,e.getMessage());
            throw new CompileRuntimeException(e);
        } catch (ParseException e) {
            compileProcessError(src, info.getBaseObject(), e.getLocation(), e.getMessage());
            throw new ParseRuntimeException(e);
        } catch (ScanException e) {
            compileProcessError(src, info.getBaseObject(), e.getLocation(), e.getMessage());
            throw new ScanRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return data;
    }

    protected void compileProcessError(String src, Object baseObj, Location location, String message) {
        logger.error(location);
        logger.error(message);
        logger.error(src);
    }

    public BuriComponentUtil getBuriComponentUtil() {
        return buriComponentUtil;
    }

    public void setBuriComponentUtil(BuriComponentUtil buriComponentUtil) {
        this.buriComponentUtil = buriComponentUtil;
    }

    public TextTemplate getTemplate() {
        return template;
    }

    public void setTemplate(TextTemplate template) {
        this.template = template;
    }

}
