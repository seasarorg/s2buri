/*
 * 作成日: 2006/07/04
 *
 */
package org.seasar.buri.compiler.util;

import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;

public interface BuriDataFieldProcRule {

    String getKeyName();
    boolean getRequiredRule(BuriDataFieldType src);
    
    // 処理前に呼びだす。trueを返したらprocessの対象として扱う
    boolean fstCheckProcess(BuriDataFieldType src);
    
    //処理をするところ。何らかの理由で再度呼びだす必要がある場合はtrueを返す
    //すべてがfalseだったら処理終了と見なす
    boolean process(BuriDataFieldType src);
    
    //一通りの処理が終わったら結果と一緒に呼びだす。再度processをする必要があればtrueを返す
    boolean afterCheck(BuriDataFieldType src,boolean totalResult);
    
    //最後にチェックして問題があれば例外を発生させたりするところ
    void finishCheck(BuriDataFieldType src);

}
