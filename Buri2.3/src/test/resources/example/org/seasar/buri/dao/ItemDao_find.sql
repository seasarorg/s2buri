select
		Item.itemID as itemID
	,Item.itemName as itemName
	,Item.price as price
from
	Item
	,BuriPathData
/*BEGIN*/
where
	
	/*IF dto.itemID != null*/ itemID = /*dto.itemID*/0/*END*/
	/*IF dto.itemID_not != null*/AND itemID != /*dto.itemID_not*/0/*END*/
	/*IF dto.itemID_large != null*/AND  /*dto.itemID_large*/0 < itemID/*END*/
	/*IF dto.itemID_moreLarge != null*/AND  /*dto.itemID_moreLarge*/0 <= itemID/*END*/
	/*IF dto.itemID_from != null*/AND  /*dto.itemID_from*/0 <= itemID/*END*/
	/*IF dto.itemID_to != null*/AND itemID <= /*dto.itemID_to*/0/*END*/
	/*IF dto.itemID_moreSmall != null*/AND itemID <= /*dto.itemID_moreSmall*/0/*END*/
	/*IF dto.itemID_small != null*/AND itemID < /*dto.itemID_small*/0/*END*/
	/*IF dto.itemID_isNull != null*/AND itemID is null /*END*/
	/*IF dto.itemID_isNotNull != null*/AND itemID is not null/*END*/
	/*IF dto.itemID_in != null*/AND itemID in /*dto.itemID_in*/(0)/*END*/

	
	/*IF dto.itemName_matchFull != null*/AND itemName Like /*dto.itemName_matchFull*/'%TestData%'/*END*/
	/*IF dto.itemName_matchFront != null*/AND itemName Like /*dto.itemName_matchFront*/'TestData%'/*END*/
	/*IF dto.itemName_matchBack != null*/AND itemName Like /*dto.itemName_matchBack*/'%TestData'/*END*/
	/*IF dto.itemName != null*/AND itemName = /*dto.itemName*/'TestData'/*END*/
	/*IF dto.itemName_not != null*/AND itemName != /*dto.itemName_not*/'TestData'/*END*/
	/*IF dto.itemName_large != null*/AND  /*dto.itemName_large*/'TestData' < itemName/*END*/
	/*IF dto.itemName_moreLarge != null*/AND  /*dto.itemName_moreLarge*/'TestData' <= itemName/*END*/
	/*IF dto.itemName_from != null*/AND  /*dto.itemName_from*/'TestData' <= itemName/*END*/
	/*IF dto.itemName_to != null*/AND itemName <= /*dto.itemName_to*/'TestData'/*END*/
	/*IF dto.itemName_moreSmall != null*/AND itemName <= /*dto.itemName_moreSmall*/'TestData'/*END*/
	/*IF dto.itemName_small != null*/AND itemName < /*dto.itemName_small*/'TestData'/*END*/
	/*IF dto.itemName_isNull != null*/AND itemName is null /*END*/
	/*IF dto.itemName_isNotNull != null*/AND itemName is not null/*END*/
	/*IF dto.itemName_in != null*/AND itemName in /*dto.itemName_in*/('TestData')/*END*/

	
	/*IF dto.price != null*/AND price = /*dto.price*/0/*END*/
	/*IF dto.price_not != null*/AND price != /*dto.price_not*/0/*END*/
	/*IF dto.price_large != null*/AND  /*dto.price_large*/0 < price/*END*/
	/*IF dto.price_moreLarge != null*/AND  /*dto.price_moreLarge*/0 <= price/*END*/
	/*IF dto.price_from != null*/AND  /*dto.price_from*/0 <= price/*END*/
	/*IF dto.price_to != null*/AND price <= /*dto.price_to*/0/*END*/
	/*IF dto.price_moreSmall != null*/AND price <= /*dto.price_moreSmall*/0/*END*/
	/*IF dto.price_small != null*/AND price < /*dto.price_small*/0/*END*/
	/*IF dto.price_isNull != null*/AND price is null /*END*/
	/*IF dto.price_isNotNull != null*/AND price is not null/*END*/
	/*IF dto.price_in != null*/AND price in /*dto.price_in*/(0)/*END*/

	AND Item.itemID = BuriPathData.pkeyNum
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
