SELECT
	SHIPPING.SHIPPING_ID AS SHIPPING_ID,
	SHIPPING.SHIPPING_DATE AS SHIPPING_DATE,
	SHIPPING.ORDER_TITLE_ID AS ORDER_TITLE_ID,
	SHIPPING.CUSTOMER_ID, AS CUSTOMER_ID
FROM
	SHIPPING,
	BURI_PATH_DATA
/*BEGIN*/
WHERE
	/*IF dto.shippingId != null*/ SHIPPING_ID = /*dto.shippingId*/0/*END*/
	/*IF dto.shippingId_not != null*/AND SHIPPING_ID != /*dto.shippingId_not*/0/*END*/
	/*IF dto.shippingId_large != null*/AND  /*dto.shippingId_large*/0 < SHIPPING_ID/*END*/
	/*IF dto.shippingId_moreLarge != null*/AND  /*dto.shippingId_moreLarge*/0 <= SHIPPING_ID/*END*/
	/*IF dto.shippingId_from != null*/AND  /*dto.shippingId_from*/0 <= SHIPPING_ID/*END*/
	/*IF dto.shippingId_to != null*/AND SHIPPING_ID <= /*dto.shippingId_to*/0/*END*/
	/*IF dto.shippingId_moreSmall != null*/AND SHIPPING_ID <= /*dto.shippingId_moreSmall*/0/*END*/
	/*IF dto.shippingId_small != null*/AND SHIPPING_ID < /*dto.shippingId_small*/0/*END*/
	/*IF dto.shippingId_isNull != null*/AND SHIPPING_ID is null /*END*/
	/*IF dto.shippingId_isNotNull != null*/AND SHIPPING_ID is not null/*END*/
	/*IF dto.shippingId_in != null*/AND SHIPPING_ID in /*dto.shippingId_in*/(0)/*END*/

	
	/*IF dto.shippingDate != null*/AND SHIPPING_DATE = /*dto.shippingDate*/Timestamp/*END*/
	/*IF dto.shippingDate_not != null*/AND SHIPPING_DATE != /*dto.shippingDate_not*/Timestamp/*END*/
	/*IF dto.shippingDate_large != null*/AND  /*dto.shippingDate_large*/Timestamp < SHIPPING_DATE/*END*/
	/*IF dto.shippingDate_moreLarge != null*/AND  /*dto.shippingDate_moreLarge*/Timestamp <= SHIPPING_DATE/*END*/
	/*IF dto.shippingDate_from != null*/AND  /*dto.shippingDate_from*/Timestamp <= SHIPPING_DATE/*END*/
	/*IF dto.shippingDate_to != null*/AND SHIPPING_DATE <= /*dto.shippingDate_to*/Timestamp/*END*/
	/*IF dto.shippingDate_moreSmall != null*/AND SHIPPING_DATE <= /*dto.shippingDate_moreSmall*/Timestamp/*END*/
	/*IF dto.shippingDate_small != null*/AND SHIPPING_DATE < /*dto.shippingDate_small*/Timestamp/*END*/
	/*IF dto.shippingDate_isNull != null*/AND SHIPPING_DATE is null /*END*/
	/*IF dto.shippingDate_isNotNull != null*/AND SHIPPING_DATE is not null/*END*/
	/*IF dto.shippingDate_in != null*/AND SHIPPING_DATE in /*dto.shippingDate_in*/(Timestamp)/*END*/

	
	/*IF dto.orderTitleId != null*/AND ORDER_TITLE_ID = /*dto.orderTitleId*/0/*END*/
	/*IF dto.orderTitleId_not != null*/AND ORDER_TITLE_ID != /*dto.orderTitleId_not*/0/*END*/
	/*IF dto.orderTitleId_large != null*/AND  /*dto.orderTitleId_large*/0 < ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_moreLarge != null*/AND  /*dto.orderTitleId_moreLarge*/0 <= ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_from != null*/AND  /*dto.orderTitleId_from*/0 <= ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_to != null*/AND ORDER_TITLE_ID <= /*dto.orderTitleId_to*/0/*END*/
	/*IF dto.orderTitleId_moreSmall != null*/AND ORDER_TITLE_ID <= /*dto.orderTitleId_moreSmall*/0/*END*/
	/*IF dto.orderTitleId_small != null*/AND ORDER_TITLE_ID < /*dto.orderTitleId_small*/0/*END*/
	/*IF dto.orderTitleId_isNull != null*/AND ORDER_TITLE_ID is null /*END*/
	/*IF dto.orderTitleId_isNotNull != null*/AND ORDER_TITLE_ID is not null/*END*/
	/*IF dto.orderTitleId_in != null*/AND ORDER_TITLE_ID in /*dto.orderTitleId_in*/(0)/*END*/

	
	/*IF dto.customerId != null*/AND CUSTOMER_ID = /*dto.customerId*/0/*END*/
	/*IF dto.customerId_not != null*/AND CUSTOMER_ID != /*dto.customerId_not*/0/*END*/
	/*IF dto.customerId_large != null*/AND  /*dto.customerId_large*/0 < CUSTOMER_ID/*END*/
	/*IF dto.customerId_moreLarge != null*/AND  /*dto.customerId_moreLarge*/0 <= CUSTOMER_ID/*END*/
	/*IF dto.customerId_from != null*/AND  /*dto.customerId_from*/0 <= CUSTOMER_ID/*END*/
	/*IF dto.customerId_to != null*/AND CUSTOMER_ID <= /*dto.customerId_to*/0/*END*/
	/*IF dto.customerId_moreSmall != null*/AND CUSTOMER_ID <= /*dto.customerId_moreSmall*/0/*END*/
	/*IF dto.customerId_small != null*/AND CUSTOMER_ID < /*dto.customerId_small*/0/*END*/
	/*IF dto.customerId_isNull != null*/AND CUSTOMER_ID is null /*END*/
	/*IF dto.customerId_isNotNull != null*/AND CUSTOMER_ID is not null/*END*/
	/*IF dto.customerId_in != null*/AND CUSTOMER_ID in /*dto.customerId_in*/(0)/*END*/

	AND SHIPPING.SHIPPING_ID = BURI_PATH_DATA.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA.PATH_NAME in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
