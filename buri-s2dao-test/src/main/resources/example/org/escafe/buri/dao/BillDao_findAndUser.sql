SELECT
	BILL.BILL_ID AS BILL_ID,
	BILL.SHIPPING_ID AS SHIPPING_ID,
	BILL.ORDER_TITLE_ID AS ORDER_TITLE_ID,
	BILL.CUSTOMER_ID AS CUSTOMER_ID
FROM
	BILL,
	BURI_PATH_DATA_USER
/*BEGIN*/
WHERE
	/*IF dto.billId != null*/ BILL_ID = /*dto.billId*/0/*END*/
	/*IF dto.billId_not != null*/AND BILL_ID != /*dto.billId_not*/0/*END*/
	/*IF dto.billId_large != null*/AND  /*dto.billId_large*/0 < BILL_ID/*END*/
	/*IF dto.billId_moreLarge != null*/AND  /*dto.billId_moreLarge*/0 <= BILL_ID/*END*/
	/*IF dto.billId_from != null*/AND  /*dto.billId_from*/0 <= BILL_ID/*END*/
	/*IF dto.billId_to != null*/AND BILL_ID <= /*dto.billId_to*/0/*END*/
	/*IF dto.billId_moreSmall != null*/AND BILL_ID <= /*dto.billId_moreSmall*/0/*END*/
	/*IF dto.billId_small != null*/AND BILL_ID < /*dto.billId_small*/0/*END*/
	/*IF dto.billId_isNull != null*/AND BILL_ID IS NULL/*END*/
	/*IF dto.billId_isNotNull != null*/AND BILL_ID IS NOT NULL/*END*/
	/*IF dto.billId_in != null*/AND BILL_ID IN/*dto.billId_in*/(0)/*END*/
	
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

	/*IF dto.orderTitleId != null*/AND ORDER_TITLE_ID = /*dto.orderTitleId*/0/*END*/
	/*IF dto.orderTitleId_not != null*/AND ORDER_TITLE_ID != /*dto.orderTitleId_not*/0/*END*/
	/*IF dto.orderTitleId_large != null*/AND  /*dto.orderTitleId_large*/0 < ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_moreLarge != null*/AND  /*dto.orderTitleId_moreLarge*/0 <= ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_from != null*/AND  /*dto.orderTitleId_from*/0 <= ORDER_TITLE_ID/*END*/
	/*IF dto.orderTitleId_to != null*/AND ORDER_TITLE_ID <= /*dto.orderTitleId_to*/0/*END*/
	/*IF dto.orderTitleId_moreSmall != null*/AND ORDER_TITLE_ID <= /*dto.orderTitleId_moreSmall*/0/*END*/
	/*IF dto.orderTitleId_small != null*/AND ORDER_TITLE_ID < /*dto.orderTitleId_small*/0/*END*/
	/*IF dto.orderTitleId_isNull != null*/AND ORDER_TITLE_ID IS NULL/*END*/
	/*IF dto.orderTitleId_isNotNull != null*/AND ORDER_TITLE_ID IS NOT NULL/*END*/
	/*IF dto.orderTitleId_in != null*/AND ORDER_TITLE_ID IN/*dto.orderTitleId_in*/(0)/*END*/

	
	/*IF dto.customerId != null*/AND CUSTOMER_ID = /*dto.customerId*/0/*END*/
	/*IF dto.customerId_not != null*/AND CUSTOMER_ID != /*dto.customerId_not*/0/*END*/
	/*IF dto.customerId_large != null*/AND  /*dto.customerId_large*/0 < CUSTOMER_ID/*END*/
	/*IF dto.customerId_moreLarge != null*/AND  /*dto.customerId_moreLarge*/0 <= CUSTOMER_ID/*END*/
	/*IF dto.customerId_from != null*/AND  /*dto.customerId_from*/0 <= CUSTOMER_ID/*END*/
	/*IF dto.customerId_to != null*/AND CUSTOMER_ID <= /*dto.customerId_to*/0/*END*/
	/*IF dto.customerId_moreSmall != null*/AND CUSTOMER_ID <= /*dto.customerId_moreSmall*/0/*END*/
	/*IF dto.customerId_small != null*/AND CUSTOMER_ID < /*dto.customerId_small*/0/*END*/
	/*IF dto.customerId_isNull != null*/AND CUSTOMER_ID IS NULL/*END*/
	/*IF dto.customerId_isNotNull != null*/AND CUSTOMER_ID IS NOT NULL/*END*/
	/*IF dto.customerId_in != null*/AND CUSTOMER_ID IN/*dto.customerId_in*/(0)/*END*/

	AND BILL.BILL_ID = BURI_PATH_DATA_USER.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA_USER.PATH_NAME IN/*paths*/('buri.path.names')/*END*/
	/*IF userIds != null*/AND BURI_PATH_DATA_USER.BRUI_USER_ID IN/*userIds*/(1)/*END*/
	
/*END*/
/*$dto.orderList*/
