/*
 * 作成日: 2005/06/09
 *
 */
package org.escafe.buri.common.util;

import java.util.Stack;

/**
 * @author makotan
 *
 */
public class StackUtil {
    public static void putAllStack(Stack dstStack,Stack srcStack) {
        while( ! srcStack.empty()) {
            Object data = srcStack.pop();
            dstStack.push(data);
        }
    }
}
