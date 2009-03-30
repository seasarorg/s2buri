select
		FurnitureItem.furnitureID as furnitureID
	,FurnitureItem.type as type
	,FurnitureItem.name as name
	,FurnitureItem.acquisition as acquisition
	,FurnitureItem.acquisitionType as acquisitionType
	,FurnitureItem.versionNo as versionNo
from
	FurnitureItem
	,BuriPathData
/*BEGIN*/
where
	
	/*IF dto.furnitureID != null*/ furnitureID = /*dto.furnitureID*/int/*END*/
	/*IF dto.furnitureID_not != null*/AND furnitureID != /*dto.furnitureID_not*/int/*END*/
	/*IF dto.furnitureID_large != null*/AND  /*dto.furnitureID_large*/int < furnitureID/*END*/
	/*IF dto.furnitureID_moreLarge != null*/AND  /*dto.furnitureID_moreLarge*/int <= furnitureID/*END*/
	/*IF dto.furnitureID_from != null*/AND  /*dto.furnitureID_from*/int <= furnitureID/*END*/
	/*IF dto.furnitureID_to != null*/AND furnitureID <= /*dto.furnitureID_to*/int/*END*/
	/*IF dto.furnitureID_moreSmall != null*/AND furnitureID <= /*dto.furnitureID_moreSmall*/int/*END*/
	/*IF dto.furnitureID_small != null*/AND furnitureID < /*dto.furnitureID_small*/int/*END*/
	/*IF dto.furnitureID_isNull != null*/AND furnitureID is null /*END*/
	/*IF dto.furnitureID_isNotNull != null*/AND furnitureID is not null/*END*/
	/*IF dto.furnitureID_in != null*/AND furnitureID in /*dto.furnitureID_in*/(int)/*END*/

	
	/*IF dto.type_matchFull != null*/AND type Like /*dto.type_matchFull*/'%TestData%'/*END*/
	/*IF dto.type_matchFront != null*/AND type Like /*dto.type_matchFront*/'TestData%'/*END*/
	/*IF dto.type_matchBack != null*/AND type Like /*dto.type_matchBack*/'%TestData'/*END*/
	/*IF dto.type != null*/AND type = /*dto.type*/'TestData'/*END*/
	/*IF dto.type_not != null*/AND type != /*dto.type_not*/'TestData'/*END*/
	/*IF dto.type_large != null*/AND  /*dto.type_large*/'TestData' < type/*END*/
	/*IF dto.type_moreLarge != null*/AND  /*dto.type_moreLarge*/'TestData' <= type/*END*/
	/*IF dto.type_from != null*/AND  /*dto.type_from*/'TestData' <= type/*END*/
	/*IF dto.type_to != null*/AND type <= /*dto.type_to*/'TestData'/*END*/
	/*IF dto.type_moreSmall != null*/AND type <= /*dto.type_moreSmall*/'TestData'/*END*/
	/*IF dto.type_small != null*/AND type < /*dto.type_small*/'TestData'/*END*/
	/*IF dto.type_isNull != null*/AND type is null /*END*/
	/*IF dto.type_isNotNull != null*/AND type is not null/*END*/
	/*IF dto.type_in != null*/AND type in /*dto.type_in*/('TestData')/*END*/

	
	/*IF dto.name_matchFull != null*/AND name Like /*dto.name_matchFull*/'%TestData%'/*END*/
	/*IF dto.name_matchFront != null*/AND name Like /*dto.name_matchFront*/'TestData%'/*END*/
	/*IF dto.name_matchBack != null*/AND name Like /*dto.name_matchBack*/'%TestData'/*END*/
	/*IF dto.name != null*/AND name = /*dto.name*/'TestData'/*END*/
	/*IF dto.name_not != null*/AND name != /*dto.name_not*/'TestData'/*END*/
	/*IF dto.name_large != null*/AND  /*dto.name_large*/'TestData' < name/*END*/
	/*IF dto.name_moreLarge != null*/AND  /*dto.name_moreLarge*/'TestData' <= name/*END*/
	/*IF dto.name_from != null*/AND  /*dto.name_from*/'TestData' <= name/*END*/
	/*IF dto.name_to != null*/AND name <= /*dto.name_to*/'TestData'/*END*/
	/*IF dto.name_moreSmall != null*/AND name <= /*dto.name_moreSmall*/'TestData'/*END*/
	/*IF dto.name_small != null*/AND name < /*dto.name_small*/'TestData'/*END*/
	/*IF dto.name_isNull != null*/AND name is null /*END*/
	/*IF dto.name_isNotNull != null*/AND name is not null/*END*/
	/*IF dto.name_in != null*/AND name in /*dto.name_in*/('TestData')/*END*/

	
	/*IF dto.acquisition != null*/AND acquisition = /*dto.acquisition*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_not != null*/AND acquisition != /*dto.acquisition_not*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_large != null*/AND  /*dto.acquisition_large*/'2005-01-01 00:00:00.0000' < acquisition/*END*/
	/*IF dto.acquisition_moreLarge != null*/AND  /*dto.acquisition_moreLarge*/'2005-01-01 00:00:00.0000' <= acquisition/*END*/
	/*IF dto.acquisition_from != null*/AND  /*dto.acquisition_from*/'2005-01-01 00:00:00.0000' <= acquisition/*END*/
	/*IF dto.acquisition_to != null*/AND acquisition <= /*dto.acquisition_to*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_moreSmall != null*/AND acquisition <= /*dto.acquisition_moreSmall*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_small != null*/AND acquisition < /*dto.acquisition_small*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.acquisition_isNull != null*/AND acquisition is null /*END*/
	/*IF dto.acquisition_isNotNull != null*/AND acquisition is not null/*END*/
	/*IF dto.acquisition_in != null*/AND acquisition in /*dto.acquisition_in*/('2005-01-01 00:00:00.0000')/*END*/

	
	/*IF dto.acquisitionType != null*/AND acquisitionType = /*dto.acquisitionType*/int/*END*/
	/*IF dto.acquisitionType_not != null*/AND acquisitionType != /*dto.acquisitionType_not*/int/*END*/
	/*IF dto.acquisitionType_large != null*/AND  /*dto.acquisitionType_large*/int < acquisitionType/*END*/
	/*IF dto.acquisitionType_moreLarge != null*/AND  /*dto.acquisitionType_moreLarge*/int <= acquisitionType/*END*/
	/*IF dto.acquisitionType_from != null*/AND  /*dto.acquisitionType_from*/int <= acquisitionType/*END*/
	/*IF dto.acquisitionType_to != null*/AND acquisitionType <= /*dto.acquisitionType_to*/int/*END*/
	/*IF dto.acquisitionType_moreSmall != null*/AND acquisitionType <= /*dto.acquisitionType_moreSmall*/int/*END*/
	/*IF dto.acquisitionType_small != null*/AND acquisitionType < /*dto.acquisitionType_small*/int/*END*/
	/*IF dto.acquisitionType_isNull != null*/AND acquisitionType is null /*END*/
	/*IF dto.acquisitionType_isNotNull != null*/AND acquisitionType is not null/*END*/
	/*IF dto.acquisitionType_in != null*/AND acquisitionType in /*dto.acquisitionType_in*/(int)/*END*/

	
	/*IF dto.versionNo != null*/AND versionNo = /*dto.versionNo*/int/*END*/
	/*IF dto.versionNo_not != null*/AND versionNo != /*dto.versionNo_not*/int/*END*/
	/*IF dto.versionNo_large != null*/AND  /*dto.versionNo_large*/int < versionNo/*END*/
	/*IF dto.versionNo_moreLarge != null*/AND  /*dto.versionNo_moreLarge*/int <= versionNo/*END*/
	/*IF dto.versionNo_from != null*/AND  /*dto.versionNo_from*/int <= versionNo/*END*/
	/*IF dto.versionNo_to != null*/AND versionNo <= /*dto.versionNo_to*/int/*END*/
	/*IF dto.versionNo_moreSmall != null*/AND versionNo <= /*dto.versionNo_moreSmall*/int/*END*/
	/*IF dto.versionNo_small != null*/AND versionNo < /*dto.versionNo_small*/int/*END*/
	/*IF dto.versionNo_isNull != null*/AND versionNo is null /*END*/
	/*IF dto.versionNo_isNotNull != null*/AND versionNo is not null/*END*/
	/*IF dto.versionNo_in != null*/AND versionNo in /*dto.versionNo_in*/(int)/*END*/

	AND FurnitureItem.furnitureID = BuriPathData.pkeyNum
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
