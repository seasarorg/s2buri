SELECT
	SHIPPING_ITEM.ORDER_DETAIL_ID AS ORDER_DETAIL_ID,
	SHIPPING_ITEM.SHIPPING_ID AS SHIPPING_ID,
	SHIPPING_ITEM.SHIPPING_ITEM_ID AS SHIPPING_ITEM_ID
FROM
	SHIPPING_ITEM,
	BURI_PATH_DATA
/*BEGIN*/
where
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
	/*IF dto.orderDetailId_in != null*/AND ORDER_DETAIL_ID IN/*dto.orderDetailId_in*/(0)/*END*/

	/*IF dto.shippingId != null*/AND SHIPPING_ID = /*dto.shippingId*/0/*END*/
	/*IF dto.shippingId_not != null*/AND SHIPPING_ID != /*dto.shippingId_not*/0/*END*/
	/*IF dto.shippingId_large != null*/AND  /*dto.shippingId_large*/0 < SHIPPING_ID/*END*/
	/*IF dto.shippingId_moreLarge != null*/AND  /*dto.shippingId_moreLarge*/0 <= SHIPPING_ID/*END*/
	/*IF dto.shippingId_from != null*/AND  /*dto.shippingId_from*/0 <= SHIPPING_ID/*END*/
	/*IF dto.shippingId_to != null*/AND SHIPPING_ID <= /*dto.shippingId_to*/0/*END*/
	/*IF dto.shippingId_moreSmall != null*/AND SHIPPING_ID <= /*dto.shippingId_moreSmall*/0/*END*/
	/*IF dto.shippingId_small != null*/AND SHIPPING_ID < /*dto.shippingId_small*/0/*END*/
	/*IF dto.shippingId_isNull != null*/AND SHIPPING_ID IS NULL/*END*/
	/*IF dto.shippingId_isNotNull != null*/AND SHIPPING_ID IS NOT NULL/*END*/
	/*IF dto.shippingId_in != null*/AND SHIPPING_ID IN/*dto.shippingId_in*/(0)/*END*/

	/*IF dto.shippingItemId != null*/AND SHIPPING_ITEM_ID = /*dto.shippingItemId*/0/*END*/
	/*IF dto.shippingItemId_not != null*/AND SHIPPING_ITEM_ID != /*dto.shippingItemId_not*/0/*END*/
	/*IF dto.shippingItemId_large != null*/AND  /*dto.shippingItemId_large*/0 < SHIPPING_ITEM_ID/*END*/
	/*IF dto.shippingItemId_moreLarge != null*/AND  /*dto.shippingItemId_moreLarge*/0 <= SHIPPING_ITEM_ID/*END*/
	/*IF dto.shippingItemId_from != null*/AND  /*dto.shippingItemId_from*/0 <= SHIPPING_ITEM_ID/*END*/
	/*IF dto.shippingItemId_to != null*/AND SHIPPING_ITEM_ID <= /*dto.shippingItemId_to*/0/*END*/
	/*IF dto.shippingItemId_moreSmall != null*/AND SHIPPING_ITEM_ID <= /*dto.shippingItemId_moreSmall*/0/*END*/
	/*IF dto.shippingItemId_small != null*/AND SHIPPING_ITEM_ID < /*dto.shippingItemId_small*/0/*END*/
	/*IF dto.shippingItemId_isNull != null*/AND SHIPPING_ITEM_ID IS NULL/*END*/
	/*IF dto.shippingItemId_isNotNull != null*/AND SHIPPING_ITEM_ID IS NOT NULL/*END*/
	/*IF dto.shippingItemId_in != null*/AND SHIPPING_ITEM_ID IN/*dto.shippingItemId_in*/(0)/*END*/

	AND SHIPPING_ITEM.SHIPPING_ITEM_ID = BURI_PATH_DATA.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA.PATH_NAME IN/*paths*/('buri.path.names')/*END*/
/*END*/
/*$dto.orderList*/
