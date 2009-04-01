/*
 * 作成日: 2005/09/14
 *
 */
package org.escafe.buri.common.util.template.exception;

import org.escafe.buri.exception.BuriException;

import freemarker.template.TemplateException;

public class TemplateRuntimeException extends BuriException {

    public TemplateRuntimeException(TemplateException cause) {
        super(null, null, cause);
    }

    /**
     * 
     */
    private static final long serialVersionUID = -4556782442510118126L;

}
