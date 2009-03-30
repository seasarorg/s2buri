SELECT
	BURI_TEST_INT.TEST_ID AS TEST_ID,
	BURI_TEST_INT.VALUE AS VALUE,
	BURI_TEST_INT.VERSION_NO AS VERSION_NO
FROM
	BURI_TEST_INT,
	BURI_PATH_DATA_USER
/*BEGIN*/
WHERE
	/*IF dto.testId != null*/ TEST_ID = /*dto.testId*/0/*END*/
	/*IF dto.testId_not != null*/AND TEST_ID != /*dto.testId_not*/0/*END*/
	/*IF dto.testId_large != null*/AND  /*dto.testId_large*/0 < TEST_ID/*END*/
	/*IF dto.testId_moreLarge != null*/AND  /*dto.testId_moreLarge*/0 <= TEST_ID/*END*/
	/*IF dto.testId_from != null*/AND  /*dto.testId_from*/0 <= TEST_ID/*END*/
	/*IF dto.testId_to != null*/AND TEST_ID <= /*dto.testId_to*/0/*END*/
	/*IF dto.testId_moreSmall != null*/AND TEST_ID <= /*dto.testId_moreSmall*/0/*END*/
	/*IF dto.testId_small != null*/AND TEST_ID < /*dto.testId_small*/0/*END*/
	/*IF dto.testId_isNull != null*/AND TEST_ID IS NULL/*END*/
	/*IF dto.testId_isNotNull != null*/AND TEST_ID IS NOT NULL/*END*/
	/*IF dto.testId_in != null*/AND TEST_ID IN/*dto.testId_in*/(0)/*END*/
	
	/*IF dto.value_matchFull != null*/AND VALUE Like /*dto.value_matchFull*/'%TestData%'/*END*/
	/*IF dto.value_matchFront != null*/AND VALUE Like /*dto.value_matchFront*/'TestData%'/*END*/
	/*IF dto.value_matchBack != null*/AND VALUE Like /*dto.value_matchBack*/'%TestData'/*END*/
	/*IF dto.value != null*/AND VALUE = /*dto.value*/'TestData'/*END*/
	/*IF dto.value_not != null*/AND VALUE != /*dto.value_not*/'TestData'/*END*/
	/*IF dto.value_large != null*/AND  /*dto.value_large*/'TestData' < VALUE/*END*/
	/*IF dto.value_moreLarge != null*/AND  /*dto.value_moreLarge*/'TestData' <= VALUE/*END*/
	/*IF dto.value_from != null*/AND  /*dto.value_from*/'TestData' <= VALUE/*END*/
	/*IF dto.value_to != null*/AND VALUE <= /*dto.value_to*/'TestData'/*END*/
	/*IF dto.value_moreSmall != null*/AND VALUE <= /*dto.value_moreSmall*/'TestData'/*END*/
	/*IF dto.value_small != null*/AND VALUE < /*dto.value_small*/'TestData'/*END*/
	/*IF dto.value_isNull != null*/AND VALUE IS NULL/*END*/
	/*IF dto.value_isNotNull != null*/AND VALUE IS NOT NULL/*END*/
	/*IF dto.value_in != null*/AND VALUE IN/*dto.value_in*/('TestData')/*END*/

	/*IF dto.versionNo != null*/AND VERSION_NO = /*dto.versionNo*/int/*END*/
	/*IF dto.versionNo_not != null*/AND VERSION_NO != /*dto.versionNo_not*/int/*END*/
	/*IF dto.versionNo_large != null*/AND  /*dto.versionNo_large*/int < VERSION_NO/*END*/
	/*IF dto.versionNo_moreLarge != null*/AND  /*dto.versionNo_moreLarge*/int <= VERSION_NO/*END*/
	/*IF dto.versionNo_from != null*/AND  /*dto.versionNo_from*/int <= VERSION_NO/*END*/
	/*IF dto.versionNo_to != null*/AND VERSION_NO <= /*dto.versionNo_to*/int/*END*/
	/*IF dto.versionNo_moreSmall != null*/AND VERSION_NO <= /*dto.versionNo_moreSmall*/int/*END*/
	/*IF dto.versionNo_small != null*/AND VERSION_NO < /*dto.versionNo_small*/int/*END*/
	/*IF dto.versionNo_isNull != null*/AND VERSION_NO IS NULL/*END*/
	/*IF dto.versionNo_isNotNull != null*/AND VERSION_NO IS NOT NULL/*END*/
	/*IF dto.versionNo_in != null*/AND VERSION_NO IN/*dto.versionNo_in*/(int)/*END*/

	AND BURI_TEST_INT.testId = BURI_PATH_DATA_USER.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA_USER.PATH_NAME IN/*paths*/('buri.path.names')/*END*/
	/*IF userIDs != null*/AND BURI_PATH_DATA_USER.BURI_USER_ID IN/*userIDs*/(1)/*END*/
/*END*/
/*$dto.orderList*/
