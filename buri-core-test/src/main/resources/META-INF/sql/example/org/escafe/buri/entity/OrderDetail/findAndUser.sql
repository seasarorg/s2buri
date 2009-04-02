SELECT
	ORDER_DETAIL.ORDER_DETAIL_ID AS ORDER_DETAIL_ID,
	ORDER_DETAIL.ORDER_COUNT AS ORDER_COUNT,
	ORDER_DETAIL.ITEM_ID AS ITEM_ID,
	ORDER_DETAIL.ORDER_TITLE_ID AS ORDER_TITLE_ID
FROM
	ORDER_DETAIL,
	BURI_PATH_DATA_USER
/*BEGIN*/
WHERE
	/*IF dto.orderDetailId != null*/ ORDER_DETAIL_ID = /*dto.orderDetailId*/0/*END*/
	/*IF dto.orderDetailId_not != null*/AND ORDER_DETAIL_ID != /*dto.orderDetailId_not*/0/*END*/
	/*IF dto.orderDetailId_large != null*/AND  /*dto.orderDetailId_large*/0 < ORDER_DETAIL_ID/*END*/
	/*IF dto.orderDetailId_moreLarge != null*/AND  /*dto.orderDetailId_moreLarge*/0 <= ORDER_DETAIL_ID/*END*/
	/*IF dto.orderDetailId_from != null*/AND  /*dto.orderDetailId_from*/0 <= ORDER_DETAIL_ID/*END*/
	/*IF dto.orderDetailId_to != null*/AND ORDER_DETAIL_ID <= /*dto.orderDetailId_to*/0/*END*/
	/*IF dto.orderDetailId_moreSmall != null*/AND ORDER_DETAIL_ID <= /*dto.orderDetailId_moreSmall*/0/*END*/
	/*IF dto.orderDetailId_small != null*/AND ORDER_DETAIL_ID < /*dto.orderDetailId_small*/0/*END*/
	/*IF dto.orderDetailId_isNull != null*/AND ORDER_DETAIL_ID IS NULL/*END*/
	/*IF dto.orderDetailId_isNotNull != null*/AND ORDER_DETAIL_ID IS NOT NULL/*END*/
	/*IF dto.orderDetailId_in != null*/AND ORDER_DETAIL_ID IN /*dto.orderDetailId_in*/(0)/*END*/
	
	/*IF dto.orderCount != null*/AND ORDER_COUNT = /*dto.orderCount*/int/*END*/
	/*IF dto.orderCount_not != null*/AND ORDER_COUNT != /*dto.orderCount_not*/int/*END*/
	/*IF dto.orderCount_large != null*/AND  /*dto.orderCount_large*/int < ORDER_COUNT/*END*/
	/*IF dto.orderCount_moreLarge != null*/AND  /*dto.orderCount_moreLarge*/int <= ORDER_COUNT/*END*/
	/*IF dto.orderCount_from != null*/AND  /*dto.orderCount_from*/int <= orderCount/*END*/
	/*IF dto.orderCount_to != null*/AND ORDER_COUNT <= /*dto.orderCount_to*/int/*END*/
	/*IF dto.orderCount_moreSmall != null*/AND ORDER_COUNT <= /*dto.orderCount_moreSmall*/int/*END*/
	/*IF dto.orderCount_small != null*/AND ORDER_COUNT < /*dto.orderCount_small*/int/*END*/
	/*IF dto.orderCount_isNull != null*/AND ORDER_COUNT IS NULL/*END*/
	/*IF dto.orderCount_isNotNull != null*/AND ORDER_COUNT IS NOT NULL/*END*/
	/*IF dto.orderCount_in != null*/AND ORDER_COUNT IN /*dto.orderCount_in*/(int)/*END*/

	/*IF dto.itemId != null*/AND ITEM_ID = /*dto.itemId*/0/*END*/
	/*IF dto.itemId_not != null*/AND ITEM_ID != /*dto.itemId_not*/0/*END*/
	/*IF dto.itemId_large != null*/AND  /*dto.itemId_large*/0 < ITEM_ID/*END*/
	/*IF dto.itemId_moreLarge != null*/AND  /*dto.itemId_moreLarge*/0 <= ITEM_ID/*END*/
	/*IF dto.itemId_from != null*/AND  /*dto.itemId_from*/0 <= ITEM_ID/*END*/
	/*IF dto.itemId_to != null*/AND ITEM_ID <= /*dto.itemId_to*/0/*END*/
	/*IF dto.itemId_moreSmall != null*/AND ITEM_ID <= /*dto.itemId_moreSmall*/0/*END*/
	/*IF dto.itemId_small != null*/AND ITEM_ID < /*dto.itemId_small*/0/*END*/
	/*IF dto.itemId_isNull != null*/AND ITEM_ID IS NULL/*END*/
	/*IF dto.itemId_isNotNull != null*/AND ITEM_ID IS NOT NULL/*END*/
	/*IF dto.itemId_in != null*/AND ITEM_ID IN /*dto.itemId_in*/(0)/*END*/

	/*IF dto.orderTitleId != null*/AND ORDER_TITLE_ID = /*dto.orderTitleId*/0/*END*/
	/*IF dto.orderTitleId_not != null*/AND ORDER_TITLE_ID != /*dto.orderTitleId_not*/0/*END*/
	/*IF dto.orderTitleId_large != null*/AND  /*dto.orderTitleId_large*/0 < ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_moreLarge != null*/AND  /*dto.orderTitleId_moreLarge*/0 <= ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_from != null*/AND  /*dto.orderTitleId_from*/0 <= ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_to != null*/AND ORDER_TITLE_ID <= /*dto.orderTitleId_to*/0/*END*/
	/*IF dto.orderTitleId_moreSmall != null*/AND ORDER_TITLE_ID <= /*dto.orderTitleId_moreSmall*/0/*END*/
	/*IF dto.orderTitleId_small != null*/AND ORDER_TITLE_ID < /*dto.orderTitleId_small*/0/*END*/
	/*IF dto.orderTitleId_isNull != null*/AND ORDER_TITLE_ID IS NULL /*END*/
	/*IF dto.orderTitleId_isNotNull != null*/AND ORDER_TITLE_ID IS NOT NULL/*END*/
	/*IF dto.orderTitleId_in != null*/AND ORDER_TITLE_ID IN /*dto.orderTitleId_in*/(0)/*END*/

	AND ORDER_DETAIL.ORDER_TITLE_ID = BURI_PATH_DATA_USER.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA_USER.PATH_NAME IN /*paths*/('buri.path.names')/*END*/
	/*IF userIDs != null*/AND BURI_PATH_DATA_USER.BURI_USER_ID IN /*userIDs*/(1)/*END*/
	
/*END*/
/*$dto.orderList*/
