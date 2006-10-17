select
		BuriTestINT.testID as testID
	,BuriTestINT.value as value
	,BuriTestINT.versionNo as versionNo
from
	BuriTestINT
	,BuriPathData
/*BEGIN*/
where
	
	/*IF dto.testID != null*/ testID = /*dto.testID*/0/*END*/
	/*IF dto.testID_not != null*/AND testID != /*dto.testID_not*/0/*END*/
	/*IF dto.testID_large != null*/AND  /*dto.testID_large*/0 < testID/*END*/
	/*IF dto.testID_moreLarge != null*/AND  /*dto.testID_moreLarge*/0 <= testID/*END*/
	/*IF dto.testID_from != null*/AND  /*dto.testID_from*/0 <= testID/*END*/
	/*IF dto.testID_to != null*/AND testID <= /*dto.testID_to*/0/*END*/
	/*IF dto.testID_moreSmall != null*/AND testID <= /*dto.testID_moreSmall*/0/*END*/
	/*IF dto.testID_small != null*/AND testID < /*dto.testID_small*/0/*END*/
	/*IF dto.testID_isNull != null*/AND testID is null /*END*/
	/*IF dto.testID_isNotNull != null*/AND testID is not null/*END*/
	/*IF dto.testID_in != null*/AND testID in /*dto.testID_in*/(0)/*END*/

	
	/*IF dto.value_matchFull != null*/AND value Like /*dto.value_matchFull*/'%TestData%'/*END*/
	/*IF dto.value_matchFront != null*/AND value Like /*dto.value_matchFront*/'TestData%'/*END*/
	/*IF dto.value_matchBack != null*/AND value Like /*dto.value_matchBack*/'%TestData'/*END*/
	/*IF dto.value != null*/AND value = /*dto.value*/'TestData'/*END*/
	/*IF dto.value_not != null*/AND value != /*dto.value_not*/'TestData'/*END*/
	/*IF dto.value_large != null*/AND  /*dto.value_large*/'TestData' < value/*END*/
	/*IF dto.value_moreLarge != null*/AND  /*dto.value_moreLarge*/'TestData' <= value/*END*/
	/*IF dto.value_from != null*/AND  /*dto.value_from*/'TestData' <= value/*END*/
	/*IF dto.value_to != null*/AND value <= /*dto.value_to*/'TestData'/*END*/
	/*IF dto.value_moreSmall != null*/AND value <= /*dto.value_moreSmall*/'TestData'/*END*/
	/*IF dto.value_small != null*/AND value < /*dto.value_small*/'TestData'/*END*/
	/*IF dto.value_isNull != null*/AND value is null /*END*/
	/*IF dto.value_isNotNull != null*/AND value is not null/*END*/
	/*IF dto.value_in != null*/AND value in /*dto.value_in*/('TestData')/*END*/

	
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

	AND BuriTestINT.testID = BuriPathData.pkeyNum
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
