/*
 * �쐬��: 2006/07/17
 *
 */
package org.seasar.buri.bao;

import java.util.List;

import example.org.seasar.buri.dto.ItemDto;

public interface PrivacyInfoManagementBao {
    static Class TARGETDTO = ItemDto.class;
    static String PROCESS = "�l���Ǘ�.�l���Ǘ�";
    static String USERINFO = "buriUser";

    static BuriConvert CONVERTER[] = new BuriConvert[]{
    };

    static String getWaitingIndicationRecognition_ACTIVITY = "�J�����F�҂�";
    List getWaitingIndicationRecognition();
    
    static String getIndicationRecognition_ACTIVITY = "�J�����F";
    List getIndicationRecognition();
    
    static String getWaitingAbandonmentCheck_ACTIVITY = "���j���m�F�҂�";
    List getWaitingAbandonmentCheck();
    
    static String getFinishingAbandonmentCheck_ACTIVITY = "���j���m�F�ς�";
    List getFinishingAbandonmentCheck();
    
    static String getWaitingReturnCheck_ACTIVITY = "���ԋp�m�F�҂�";
    List getWaitingReturnCheck();
    
    static String getFinishingReturnCheck_ACTIVITY = "���ԋp�m�F�ς�";
    List getFinishingReturnCheck();
    
    
    
    static String indicationRequest_ACTIVITY = "�J���˗�";
    void indicationRequest(ItemDto dto);
    
    static String waitingIndicationRecognition_ACTIVITY = "�J�����F�҂�";
    void waitingIndicationRecognition(ItemDto dto);
    
    static String indicationRecognitionAbandonment_ACTIVITY = "�J�����F";
    public static String indicationRecognitionAbandonment_ACTION = "drop";
    void indicationRecognitionAbandonment(ItemDto dto);
    
    static String indicationRecognitionReturn_ACTIVITY = "�J�����F";
    public static String indicationRecognitionReturn_ACTION = "return";
    void indicationRecognitionReturn(ItemDto dto);
    
    static String waitingAbandonmentCheck_ACTIVITY = "���j���m�F�҂�";
    void waitingAbandonmentCheck(ItemDto dto);
    
    static String waitingReturnCheck_ACTIVITY = "���ԋp�m�F�҂�";
    void waitingReturnCheck(ItemDto dto);
    
    

}
