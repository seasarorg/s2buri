/*
 * ì¬“ú: 2005/06/30
 *
 */
package org.seasar.buri.dao.util;

import org.seasar.buri.engine.BuriPath;

public interface BuriPathDaoUtil {
    BuriPath getBuriPath(BuriPath path);
    BuriPath getBuriPath(long buriPathID);
    
    void setup(BuriPath path);
}
