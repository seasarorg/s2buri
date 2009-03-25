SELECT
	FURNITURE_ITEM.FURNITURE_ID AS FURNITURE_ID,
	FURNITURE_ITEM.TYPE AS TYPE,
	FURNITURE_ITEM.NAME AS NAME,
	FURNITURE_ITEM.ACQUISITION AS ACQUISITION,
	FURNITURE_ITEM.ACQUISITION_TYPE AS ACQUISITION_TYPE,
	FURNITURE_ITEM.VERSION_NO AS VERSION_NO
FROM
	FURNITURE_ITEM,
	BURI_PATH_DATA
/*BEGIN*/
WHERE
	/*IF dto.furnitureId != null*/ FURNITURE_ID = /*dto.furnitureId*/int/*END*/
	/*IF dto.furnitureId_not != null*/AND FURNITURE_ID != /*dto.furnitureId_not*/int/*END*/
	/*IF dto.furnitureId_large != null*/AND  /*dto.furnitureId_large*/int < FURNITURE_ID/*END*/
	/*IF dto.furnitureId_moreLarge != null*/AND  /*dto.furnitureId_moreLarge*/int <= FURNITURE_ID/*END*/
	/*IF dto.furnitureId_from != null*/AND  /*dto.furnitureId_from*/int <= furnitureID/*END*/
	/*IF dto.furnitureId_to != null*/AND FURNITURE_ID <= /*dto.furnitureId_to*/int/*END*/
	/*IF dto.furnitureId_moreSmall != null*/AND FURNITURE_ID <= /*dto.furnitureId_moreSmall*/int/*END*/
	/*IF dto.furnitureId_small != null*/AND FURNITURE_ID < /*dto.furnitureId_small*/int/*END*/
	/*IF dto.furnitureId_isNull != null*/AND FURNITURE_ID is null /*END*/
	/*IF dto.furnitureId_isNotNull != null*/AND FURNITURE_ID is not null/*END*/
	/*IF dto.furnitureId_in != null*/AND FURNITURE_ID in /*dto.furnitureId_in*/(int)/*END*/

	
	/*IF dto.type_matchFull != null*/AND TYPE Like /*dto.type_matchFull*/'%TestData%'/*END*/
	/*IF dto.type_matchFront != null*/AND TYPE Like /*dto.type_matchFront*/'TestData%'/*END*/
	/*IF dto.type_matchBack != null*/AND TYPE Like /*dto.type_matchBack*/'%TestData'/*END*/
	/*IF dto.type != null*/AND TYPE = /*dto.type*/'TestData'/*END*/
	/*IF dto.type_not != null*/AND TYPE != /*dto.type_not*/'TestData'/*END*/
	/*IF dto.type_large != null*/AND  /*dto.type_large*/'TestData' < TYPE/*END*/
	/*IF dto.type_moreLarge != null*/AND  /*dto.type_moreLarge*/'TestData' <= TYPE/*END*/
	/*IF dto.type_from != null*/AND  /*dto.type_from*/'TestData' <= TYPE/*END*/
	/*IF dto.type_to != null*/AND TYPE <= /*dto.type_to*/'TestData'/*END*/
	/*IF dto.type_moreSmall != null*/AND TYPE <= /*dto.type_moreSmall*/'TestData'/*END*/
	/*IF dto.type_small != null*/AND TYPE < /*dto.type_small*/'TestData'/*END*/
	/*IF dto.type_isNull != null*/AND TYPE is null /*END*/
	/*IF dto.type_isNotNull != null*/AND TYPE is not null/*END*/
	/*IF dto.type_in != null*/AND TYPE IN /*dto.type_in*/('TestData')/*END*/

	
	/*IF dto.name_matchFull != null*/AND NAME Like /*dto.name_matchFull*/'%TestData%'/*END*/
	/*IF dto.name_matchFront != null*/AND NAME Like /*dto.name_matchFront*/'TestData%'/*END*/
	/*IF dto.name_matchBack != null*/AND NAME Like /*dto.name_matchBack*/'%TestData'/*END*/
	/*IF dto.name != null*/AND NAME = /*dto.name*/'TestData'/*END*/
	/*IF dto.name_not != null*/AND NAME != /*dto.name_not*/'TestData'/*END*/
	/*IF dto.name_large != null*/AND  /*dto.name_large*/'TestData' < NAME/*END*/
	/*IF dto.name_moreLarge != null*/AND  /*dto.name_moreLarge*/'TestData' <= NAME/*END*/
	/*IF dto.name_from != null*/AND  /*dto.name_from*/'TestData' <= name/*END*/
	/*IF dto.name_to != null*/AND NAME <= /*dto.name_to*/'TestData'/*END*/
	/*IF dto.name_moreSmall != null*/AND NAME <= /*dto.name_moreSmall*/'TestData'/*END*/
	/*IF dto.name_small != null*/AND NAME < /*dto.name_small*/'TestData'/*END*/
	/*IF dto.name_isNull != null*/AND NAME IS NULL /*END*/
	/*IF dto.name_isNotNull != null*/AND name IS NOT NULL/*END*/
	/*IF dto.name_in != null*/AND NAME IN /*dto.name_in*/('TestData')/*END*/

	
	/*IF dto.acquisition != null*/AND ACQUISITION = /*dto.acquisition*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_not != null*/AND ACQUISITION != /*dto.acquisition_not*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_large != null*/AND  /*dto.acquisition_large*/'2005-01-01 00:00:00.0000' < ACQUISITION/*END*/
	/*IF dto.acquisition_moreLarge != null*/AND  /*dto.acquisition_moreLarge*/'2005-01-01 00:00:00.0000' <= ACQUISITION/*END*/
	/*IF dto.acquisition_from != null*/AND  /*dto.acquisition_from*/'2005-01-01 00:00:00.0000' <= ACQUISITION/*END*/
	/*IF dto.acquisition_to != null*/AND ACQUISITION <= /*dto.acquisition_to*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_moreSmall != null*/AND ACQUISITION <= /*dto.acquisition_moreSmall*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_small != null*/AND ACQUISITION < /*dto.acquisition_small*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_isNull != null*/AND ACQUISITION is null /*END*/
	/*IF dto.acquisition_isNotNull != null*/AND ACQUISITION is not null/*END*/
	/*IF dto.acquisition_in != null*/AND ACQUISITION in /*dto.acquisition_in*/('2005-01-01 00:00:00.0000')/*END*/

	
	/*IF dto.acquisitionType != null*/AND ACQUISITION_TYPE = /*dto.acquisitionType*/int/*END*/
	/*IF dto.acquisitionType_not != null*/AND ACQUISITION_TYPE != /*dto.acquisitionType_not*/int/*END*/
	/*IF dto.acquisitionType_large != null*/AND  /*dto.acquisitionType_large*/int < ACQUISITION_TYPE/*END*/
	/*IF dto.acquisitionType_moreLarge != null*/AND  /*dto.acquisitionType_moreLarge*/int <= ACQUISITION_TYPE/*END*/
	/*IF dto.acquisitionType_from != null*/AND  /*dto.acquisitionType_from*/int <= ACQUISITION_TYPE/*END*/
	/*IF dto.acquisitionType_to != null*/AND ACQUISITION_TYPE <= /*dto.acquisitionType_to*/int/*END*/
	/*IF dto.acquisitionType_moreSmall != null*/AND ACQUISITION_TYPE <= /*dto.acquisitionType_moreSmall*/int/*END*/
	/*IF dto.acquisitionType_small != null*/AND ACQUISITION_TYPE < /*dto.acquisitionType_small*/int/*END*/
	/*IF dto.acquisitionType_isNull != null*/AND ACQUISITION_TYPE is null /*END*/
	/*IF dto.acquisitionType_isNotNull != null*/AND ACQUISITION_TYPE is not null/*END*/
	/*IF dto.acquisitionType_in != null*/AND ACQUISITION_TYPE in /*dto.acquisitionType_in*/(int)/*END*/

	
	/*IF dto.versionNo != null*/AND VERSION_NO = /*dto.versionNo*/int/*END*/
	/*IF dto.versionNo_not != null*/AND VERSION_NO != /*dto.versionNo_not*/int/*END*/
	/*IF dto.versionNo_large != null*/AND  /*dto.versionNo_large*/int < VERSION_NO/*END*/
	/*IF dto.versionNo_moreLarge != null*/AND  /*dto.versionNo_moreLarge*/int <= VERSION_NO/*END*/
	/*IF dto.versionNo_from != null*/AND  /*dto.versionNo_from*/int <= VERSION_NO/*END*/
	/*IF dto.versionNo_to != null*/AND VERSION_NO <= /*dto.versionNo_to*/int/*END*/
	/*IF dto.versionNo_moreSmall != null*/AND VERSION_NO <= /*dto.versionNo_moreSmall*/int/*END*/
	/*IF dto.versionNo_small != null*/AND VERSION_NO < /*dto.versionNo_small*/int/*END*/
	/*IF dto.versionNo_isNull != null*/AND VERSION_NO is null /*END*/
	/*IF dto.versionNo_isNotNull != null*/AND VERSION_NO is not null/*END*/
	/*IF dto.versionNo_in != null*/AND VERSION_NO in /*dto.versionNo_in*/(int)/*END*/

	AND FURNITURE_ITEM.FURNITURE_ID = BURI_PATH_DATA.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA.PATH_NAME IN /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
