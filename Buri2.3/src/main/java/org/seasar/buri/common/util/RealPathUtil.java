/*
 * 作成日: 2005/04/24
 *
 */
package org.seasar.buri.common.util;

import javax.servlet.ServletContext;

/**
 * @author makotan
 *
 */
public class RealPathUtil {
    private boolean servletMode = true;
    private boolean modeSetup = false;
    private String rootPath = null;
    private ServletContext context;
    //private String pathSeparator = "\\";
    
    public String getFileSpa() {
        return System.getProperty("file.separator");
    }
    
    private void setupMode() {
        if(modeSetup) return;
        modeSetup = true;
        Throwable throwable = new Throwable();
        StackTraceElement[] stacks = throwable.getStackTrace();
        for(int i=0;i < stacks.length;i++) {
            String className = stacks[i].getClassName();
            if(className.indexOf("TestCase")>=0) {
                servletMode = false;
            }
        }
    }
    
    public String getAppRootPath() {
        if(rootPath!=null) return rootPath;
        setupMode();
        if(servletMode) {
            rootPath = context.getRealPath("/") + getFileSpa();
        } else {
            rootPath = System.getProperty("user.dir") + getFileSpa();
        }
        return rootPath;
    }
    public ServletContext getContext() {
        return context;
    }
    public void setContext(ServletContext context) {
        this.context = context;
    }
    public boolean isServletMode() {
        return servletMode;
    }
    public void setServletMode(boolean servletMode) {
        this.servletMode = servletMode;
        modeSetup = true;
    }
}
