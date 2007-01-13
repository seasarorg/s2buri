/*
 * 作成日: 2006/02/07
 *
 */
package jp.starlogic.common.janino;

import java.io.IOException;
import java.io.StringReader;

import jp.starlogic.common.janino.exception.CompileRuntimeException;
import jp.starlogic.common.janino.exception.ParseRuntimeException;
import jp.starlogic.common.janino.exception.ScanRuntimeException;

import org.codehaus.janino.ClassBodyEvaluator;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.seasar.framework.exception.IORuntimeException;

public class ClassBodyEvaluatorUtil {

    public static Object evaluate(String classBody) {
        return evaluate(classBody,null);
    }
    public static Object evaluate(String classBody,Class extendedType) {
        return evaluate(classBody,extendedType,new Class[0]);
    }
    public static Object evaluate(String classBody,Class extendedType,Class implementedTypes) {
        return evaluate(classBody,extendedType,new Class[]{implementedTypes});
    }
    public static Object evaluate(String classBody,Class extendedType,Class[] implementedTypes) {
        Object data = null;
        try {
            Scanner scanner = new Scanner(null, new StringReader(classBody));
            data = ClassBodyEvaluator.createFastClassBodyEvaluator(scanner,extendedType.getName()+"$ClassBodyEvaluatorUtil$",extendedType,implementedTypes,null);
        } catch (CompileException e) {
            throw new CompileRuntimeException(e);
        } catch (ParseException e) {
            throw new ParseRuntimeException(e);
        } catch (ScanException e) {
            throw new ScanRuntimeException(e);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return data;
    }
}
