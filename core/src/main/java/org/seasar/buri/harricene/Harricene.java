/*
 * ì¬“ú: 2005/11/23
 *
 */
package org.seasar.buri.harricene;

import java.util.List;
import java.util.Map;

public interface Harricene extends Map {
    void readFile(String filename);
    void readFile(String filename,List sheets);
}
