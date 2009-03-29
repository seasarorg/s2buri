SELECT
	ITEM.ITEM_ID AS ITEM_ID,
	ITEM.ITEM_NAME AS ITEM_NAME,
	ITEM.PRICE AS PRICE
FROM
	ITEM,
	BURI_PATH_DATA
/*BEGIN*/
WHERE
	/*IF dto.itemId != null*/ ITEM_ID = /*dto.itemId*/0/*END*/
	/*IF dto.itemId_not != null*/AND ITEM_ID != /*dto.itemId_not*/0/*END*/
	/*IF dto.itemId_large != null*/AND  /*dto.itemId_large*/0 < ITEM_ID/*END*/
	/*IF dto.itemId_moreLarge != null*/AND  /*dto.itemId_moreLarge*/0 <= ITEM_ID/*END*/
	/*IF dto.itemId_from != null*/AND  /*dto.itemId_from*/0 <= itemId/*END*/
	/*IF dto.itemId_to != null*/AND ITEM_ID <= /*dto.itemId_to*/0/*END*/
	/*IF dto.itemId_moreSmall != null*/AND ITEM_ID <= /*dto.itemId_moreSmall*/0/*END*/
	/*IF dto.itemId_small != null*/AND ITEM_ID < /*dto.itemId_small*/0/*END*/
	/*IF dto.itemId_isNull != null*/AND ITEM_ID is null /*END*/
	/*IF dto.itemId_isNotNull != null*/AND ITEM_ID is not null/*END*/
	/*IF dto.itemId_in != null*/AND ITEM_ID in /*dto.itemId_in*/(0)/*END*/
	
	/*IF dto.itemName_matchFull != null*/AND ITEM_NAME Like /*dto.itemName_matchFull*/'%TestData%'/*END*/
	/*IF dto.itemName_matchFront != null*/AND ITEM_NAME Like /*dto.itemName_matchFront*/'TestData%'/*END*/
	/*IF dto.itemName_matchBack != null*/AND ITEM_NAME Like /*dto.itemName_matchBack*/'%TestData'/*END*/
	/*IF dto.itemName != null*/AND ITEM_NAME = /*dto.itemName*/'TestData'/*END*/
	/*IF dto.itemName_not != null*/AND ITEM_NAME != /*dto.itemName_not*/'TestData'/*END*/
	/*IF dto.itemName_large != null*/AND  /*dto.itemName_large*/'TestData' < ITEM_NAME/*END*/
	/*IF dto.itemName_moreLarge != null*/AND  /*dto.itemName_moreLarge*/'TestData' <= ITEM_NAME/*END*/
	/*IF dto.itemName_from != null*/AND  /*dto.itemName_from*/'TestData' <= ITEM_NAME/*END*/
	/*IF dto.itemName_to != null*/AND ITEM_NAME <= /*dto.itemName_to*/'TestData'/*END*/
	/*IF dto.itemName_moreSmall != null*/AND ITEM_NAME <= /*dto.itemName_moreSmall*/'TestData'/*END*/
	/*IF dto.itemName_small != null*/AND ITEM_NAME < /*dto.itemName_small*/'TestData'/*END*/
	/*IF dto.itemName_isNull != null*/AND ITEM_NAME is null /*END*/
	/*IF dto.itemName_isNotNull != null*/AND ITEM_NAME is not null/*END*/
	/*IF dto.itemName_in != null*/AND ITEM_NAME in /*dto.itemName_in*/('TestData')/*END*/

	/*IF dto.price != null*/AND PRICE = /*dto.price*/0/*END*/
	/*IF dto.price_not != null*/AND PRICE != /*dto.price_not*/0/*END*/
	/*IF dto.price_large != null*/AND  /*dto.price_large*/0 < price/*END*/
	/*IF dto.price_moreLarge != null*/AND  /*dto.price_moreLarge*/0 <= price/*END*/
	/*IF dto.price_from != null*/AND  /*dto.price_from*/0 <= price/*END*/
	/*IF dto.price_to != null*/AND PRICE <= /*dto.price_to*/0/*END*/
	/*IF dto.price_moreSmall != null*/AND PRICE <= /*dto.price_moreSmall*/0/*END*/
	/*IF dto.price_small != null*/AND PRICE < /*dto.price_small*/0/*END*/
	/*IF dto.price_isNull != null*/AND PRICE is null /*END*/
	/*IF dto.price_isNotNull != null*/AND PRICE is not null/*END*/
	/*IF dto.price_in != null*/AND PRICE in /*dto.price_in*/(0)/*END*/

	AND ITEM.ITEM_ID = BURI_PATH_DATA.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA.PATH_NAME IN/*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
