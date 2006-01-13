/*
 * ì¬“ú: 2005/10/02
 *
 */
package org.seasar.buri.xpdl.util.impl;

import org.seasar.buri.engine.BuriPath;
import org.seasar.buri.xpdl.util.TagSelect;



public abstract class AbstractTagSelect implements TagSelect {

    private BuriPath path;

    public BuriPath getPath() {
        return path;
    }

    public void setPath(BuriPath path) {
        this.path = path;
    }
        
}
