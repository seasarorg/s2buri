SELECT
	ORDER_TITLE.ORDER_TITLE_ID AS ORDER_TITLE_ID,
	ORDER_TITLE.ORDER_DATE AS ORDER_DATE,
	ORDER_TITLE.CUSTOMER_ID AS CUSTOMER_ID,
	ORDER_TITLE.STATUS AS STATUS
FROM
	ORDER_TITLE,
	BURI_PATH_DATA
/*BEGIN*/
WHERE
	/*IF dto.orderTitleId != null*/ ORDER_TITLE_ID = /*dto.orderTitleId*/0/*END*/
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
	
	/*IF dto.orderDate != null*/AND ORDER_DATE = /*dto.orderDate*/Timestamp/*END*/
	/*IF dto.orderDate_not != null*/AND ORDER_DATE != /*dto.orderDate_not*/Timestamp/*END*/
	/*IF dto.orderDate_large != null*/AND  /*dto.orderDate_large*/Timestamp < ORDER_DATE/*END*/
	/*IF dto.orderDate_moreLarge != null*/AND  /*dto.orderDate_moreLarge*/Timestamp <= ORDER_DATE/*END*/
	/*IF dto.orderDate_from != null*/AND  /*dto.orderDate_from*/Timestamp <= ORDER_DATE/*END*/
	/*IF dto.orderDate_to != null*/AND ORDER_DATE <= /*dto.orderDate_to*/Timestamp/*END*/
	/*IF dto.orderDate_moreSmall != null*/AND ORDER_DATE <= /*dto.orderDate_moreSmall*/Timestamp/*END*/
	/*IF dto.orderDate_small != null*/AND ORDER_DATE < /*dto.orderDate_small*/Timestamp/*END*/
	/*IF dto.orderDate_isNull != null*/AND ORDER_DATE is null /*END*/
	/*IF dto.orderDate_isNotNull != null*/AND ORDER_DATE is not null/*END*/
	/*IF dto.orderDate_in != null*/AND ORDER_DATE in /*dto.orderDate_in*/(Timestamp)/*END*/

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

	/*IF dto.status != null*/AND STATUS = /*dto.status*/Integer/*END*/
	/*IF dto.status_not != null*/AND STATUS != /*dto.status_not*/Integer/*END*/
	/*IF dto.status_large != null*/AND  /*dto.status_large*/Integer < STATUS/*END*/
	/*IF dto.status_moreLarge != null*/AND  /*dto.status_moreLarge*/Integer <= STATUS/*END*/
	/*IF dto.status_from != null*/AND  /*dto.status_from*/Integer <= STATUS/*END*/
	/*IF dto.status_to != null*/AND STATUS <= /*dto.status_to*/Integer/*END*/
	/*IF dto.status_moreSmall != null*/AND STATUS <= /*dto.status_moreSmall*/Integer/*END*/
	/*IF dto.status_small != null*/AND STATUS < /*dto.status_small*/Integer/*END*/
	/*IF dto.status_isNull != null*/AND STATUS is null /*END*/
	/*IF dto.status_isNotNull != null*/AND STATUS is not null/*END*/
	/*IF dto.status_in != null*/AND STATUS in /*dto.status_in*/(Integer)/*END*/

	AND ORDER_TITLE.orderTitleId = BURI_PATH_DATA.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA.PATH_NAME in /*paths*/('buri.path.names')/*END*/
/*END*/
/*$dto.orderList*/
