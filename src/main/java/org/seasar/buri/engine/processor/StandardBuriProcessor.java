/*
 * �쐬��: 2005/08/26
 *
 */
package org.seasar.buri.engine.processor;

import java.util.List;

import org.seasar.framework.container.S2Container;

/**
 * �Ԃ�̕W���I�Ȏ��s�ƃf�[�^�̎擾�Ɋ֌W����API�̃Z�b�g
 * ���[�U�̌����ɂ���ē����ύX����ꍇ�͂���API���g��
 * 
 * invoke �ʏ��Invoke
 * context ���s�̌��ʂƂ��Ēl���擾�������ꍇ�̂��߂�OGNL��
 * ����ɉ����ă��\�b�h�̌��œ��삪�ω�����
 * 
 * Action action�t����Invoke
 * NoUpdate data��Update���Ȃ�Invoke
 * 
 * data��action��XPDL�ł�#��t����#data,#action�݂����Ɉ���
 * ���Ƃ͒ʏ��OGNL��������
 * 
 * 
 * 
 * @author makotan
 *
 */
public interface StandardBuriProcessor {
    void toNextStatus(String path, Object data, Object userData);
    Object toNextStatus(String path, Object data, Object userData,String resultExp);
    void toNextStatusAction(String path, Object data, Object userData,Object action);
    
    Object toNextStatus(String path, Object data, Object userData,BuriProcessorInfo info);


//    List findDataList(String path, Object userData,Object dto);
//    List getDataListFromPathAndUser(String path, Object userData);
//    List getDataIDFromPathAndUser(String path, Object userData);
    List getDataListFromPath(String path, Object userData);
    List getDataIDFromPath(String path, Object userData);
    List getPathFromData(String path, Object data, Object userData);

//    List findDataList(String path, Object userData,Object dto, S2Container container);
//    List getDataListFromPathAndUser(String path, Object userData, S2Container container);
//    List getDataIDFromPathAndUser(String path, Object userData, S2Container container);
    List getDataListFromPath(String path, Object userData, S2Container container);
    List getDataIDFromPath(String path, Object userData, S2Container container);
    List getPathFromData(String path, Object data, Object userData, S2Container container);

    List getDataListFromPathAndUserOnly(String path, Object userData);
    List getDataIDFromPathAndUserOnly(String path, Object userData);

    List getDataListFromPathAndUserOnly(String path, Object userData, S2Container container);
    List getDataIDFromPathAndUserOnly(String path, Object userData, S2Container container);
    long countByPathAndDatas(String path, List datas,Object userData);
    long countByPathAndDatas(String path, List datas,Object userData, S2Container container);

}
