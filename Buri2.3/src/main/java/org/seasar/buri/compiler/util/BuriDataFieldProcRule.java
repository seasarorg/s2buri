/*
 * �쐬��: 2006/07/04
 *
 */
package org.seasar.buri.compiler.util;

import org.seasar.buri.oouo.internal.structure.BuriDataFieldType;

public interface BuriDataFieldProcRule {

    String getKeyName();
    boolean getRequiredRule(BuriDataFieldType src);
    
    // �����O�ɌĂт����Btrue��Ԃ�����process�̑ΏۂƂ��Ĉ���
    boolean fstCheckProcess(BuriDataFieldType src);
    
    //����������Ƃ���B���炩�̗��R�ōēx�Ăт����K�v������ꍇ��true��Ԃ�
    //���ׂĂ�false�������珈���I���ƌ��Ȃ�
    boolean process(BuriDataFieldType src);
    
    //��ʂ�̏������I������猋�ʂƈꏏ�ɌĂт����B�ēxprocess������K�v�������true��Ԃ�
    boolean afterCheck(BuriDataFieldType src,boolean totalResult);
    
    //�Ō�Ƀ`�F�b�N���Ė�肪����Η�O�𔭐��������肷��Ƃ���
    void finishCheck(BuriDataFieldType src);

}
