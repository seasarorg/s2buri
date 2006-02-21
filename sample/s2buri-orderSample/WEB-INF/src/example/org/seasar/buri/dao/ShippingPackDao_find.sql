select
	Shipping.shippingID
	,Shipping.shippingDate
	,Shipping.orderTitleID
	,Shipping.customerID
	,customerDto.customerID AS customerID_0
	,customerDto.customerName AS customerName_0
	,orderTitleDto.orderTitleID AS orderTitleID_1
	,orderTitleDto.orderDate AS orderDate_1
	,orderTitleDto.customerID AS customerID_1
	,orderTitleDto.status AS status_1
from
	Shipping
	LEFT OUTER JOIN Customer customerDto ON Shipping.CustomerID = customerDto.CustomerID
	LEFT OUTER JOIN OrderTitle orderTitleDto ON Shipping.OrderTitleID = orderTitleDto.OrderTitleID
/*BEGIN*/
where

	/*IF dto.shippingID != null*/ Shipping.ShippingID = /*dto.shippingID*/0/*END*/
	/*IF dto.shippingID_not != null*/AND Shipping.ShippingID != /*dto.shippingID_not*/0/*END*/
	/*IF dto.shippingID_large != null*/AND  /*dto.shippingID_large*/0 < Shipping.ShippingID/*END*/
	/*IF dto.shippingID_moreLarge != null*/AND  /*dto.shippingID_moreLarge*/0 <= Shipping.ShippingID/*END*/
	/*IF dto.shippingID_from != null*/AND  /*dto.shippingID_from*/0 <= Shipping.ShippingID/*END*/
	/*IF dto.shippingID_to != null*/AND Shipping.ShippingID <= /*dto.shippingID_to*/0/*END*/
	/*IF dto.shippingID_moreSmall != null*/AND Shipping.ShippingID <= /*dto.shippingID_moreSmall*/0/*END*/
	/*IF dto.shippingID_small != null*/AND Shipping.ShippingID < /*dto.shippingID_small*/0/*END*/
	/*IF dto.shippingID_isNull != null*/AND Shipping.ShippingID is null /*END*/
	/*IF dto.shippingID_isNotNull != null*/AND Shipping.ShippingID is not null/*END*/
	/*IF dto.shippingID_in != null*/AND Shipping.ShippingID in /*dto.shippingID_in*/(0)/*END*/
	/*IF dto.shippingDate != null*/AND Shipping.ShippingDate = /*dto.shippingDate*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.shippingDate_not != null*/AND Shipping.ShippingDate != /*dto.shippingDate_not*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.shippingDate_large != null*/AND  /*dto.shippingDate_large*/'2005-01-01 00:00:00.0000' < Shipping.ShippingDate/*END*/
	/*IF dto.shippingDate_moreLarge != null*/AND  /*dto.shippingDate_moreLarge*/'2005-01-01 00:00:00.0000' <= Shipping.ShippingDate/*END*/
	/*IF dto.shippingDate_from != null*/AND  /*dto.shippingDate_from*/'2005-01-01 00:00:00.0000' <= Shipping.ShippingDate/*END*/
	/*IF dto.shippingDate_to != null*/AND Shipping.ShippingDate <= /*dto.shippingDate_to*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.shippingDate_moreSmall != null*/AND Shipping.ShippingDate <= /*dto.shippingDate_moreSmall*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.shippingDate_small != null*/AND Shipping.ShippingDate < /*dto.shippingDate_small*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.shippingDate_isNull != null*/AND Shipping.ShippingDate is null /*END*/
	/*IF dto.shippingDate_isNotNull != null*/AND Shipping.ShippingDate is not null/*END*/
	/*IF dto.shippingDate_in != null*/AND Shipping.ShippingDate in /*dto.shippingDate_in*/('2005-01-01 00:00:00.0000')/*END*/
	/*IF dto.orderTitleID != null*/AND Shipping.OrderTitleID = /*dto.orderTitleID*/0/*END*/
	/*IF dto.orderTitleID_not != null*/AND Shipping.OrderTitleID != /*dto.orderTitleID_not*/0/*END*/
	/*IF dto.orderTitleID_large != null*/AND  /*dto.orderTitleID_large*/0 < Shipping.OrderTitleID/*END*/
	/*IF dto.orderTitleID_moreLarge != null*/AND  /*dto.orderTitleID_moreLarge*/0 <= Shipping.OrderTitleID/*END*/
	/*IF dto.orderTitleID_from != null*/AND  /*dto.orderTitleID_from*/0 <= Shipping.OrderTitleID/*END*/
	/*IF dto.orderTitleID_to != null*/AND Shipping.OrderTitleID <= /*dto.orderTitleID_to*/0/*END*/
	/*IF dto.orderTitleID_moreSmall != null*/AND Shipping.OrderTitleID <= /*dto.orderTitleID_moreSmall*/0/*END*/
	/*IF dto.orderTitleID_small != null*/AND Shipping.OrderTitleID < /*dto.orderTitleID_small*/0/*END*/
	/*IF dto.orderTitleID_isNull != null*/AND Shipping.OrderTitleID is null /*END*/
	/*IF dto.orderTitleID_isNotNull != null*/AND Shipping.OrderTitleID is not null/*END*/
	/*IF dto.orderTitleID_in != null*/AND Shipping.OrderTitleID in /*dto.orderTitleID_in*/(0)/*END*/
	/*IF dto.customerID != null*/AND Shipping.CustomerID = /*dto.customerID*/0/*END*/
	/*IF dto.customerID_not != null*/AND Shipping.CustomerID != /*dto.customerID_not*/0/*END*/
	/*IF dto.customerID_large != null*/AND  /*dto.customerID_large*/0 < Shipping.CustomerID/*END*/
	/*IF dto.customerID_moreLarge != null*/AND  /*dto.customerID_moreLarge*/0 <= Shipping.CustomerID/*END*/
	/*IF dto.customerID_from != null*/AND  /*dto.customerID_from*/0 <= Shipping.CustomerID/*END*/
	/*IF dto.customerID_to != null*/AND Shipping.CustomerID <= /*dto.customerID_to*/0/*END*/
	/*IF dto.customerID_moreSmall != null*/AND Shipping.CustomerID <= /*dto.customerID_moreSmall*/0/*END*/
	/*IF dto.customerID_small != null*/AND Shipping.CustomerID < /*dto.customerID_small*/0/*END*/
	/*IF dto.customerID_isNull != null*/AND Shipping.CustomerID is null /*END*/
	/*IF dto.customerID_isNotNull != null*/AND Shipping.CustomerID is not null/*END*/
	/*IF dto.customerID_in != null*/AND Shipping.CustomerID in /*dto.customerID_in*/(0)/*END*/

	/*IF dto.customerDto_CustomerID != null*/AND customerDto.CustomerID = /*dto.customerDto_CustomerID*/0/*END*/
	/*IF dto.customerDto_CustomerID_not != null*/AND customerDto.CustomerID != /*dto.customerDto_CustomerID_not*/0/*END*/
	/*IF dto.customerDto_CustomerID_large != null*/AND  /*dto.customerDto_CustomerID_large*/0 < customerDto.CustomerID/*END*/
	/*IF dto.customerDto_CustomerID_moreLarge != null*/AND  /*dto.customerDto_CustomerID_moreLarge*/0 <= customerDto.CustomerID/*END*/
	/*IF dto.customerDto_CustomerID_from != null*/AND  /*dto.customerDto_CustomerID_from*/0 <= customerDto.CustomerID/*END*/
	/*IF dto.customerDto_CustomerID_to != null*/AND customerDto.CustomerID <= /*dto.customerDto_CustomerID_to*/0/*END*/
	/*IF dto.customerDto_CustomerID_moreSmall != null*/AND customerDto.CustomerID <= /*dto.customerDto_CustomerID_moreSmall*/0/*END*/
	/*IF dto.customerDto_CustomerID_small != null*/AND customerDto.CustomerID < /*dto.customerDto_CustomerID_small*/0/*END*/
	/*IF dto.customerDto_CustomerID_isNull != null*/AND customerDto.CustomerID is null /*END*/
	/*IF dto.customerDto_CustomerID_isNotNull != null*/AND customerDto.CustomerID is not null/*END*/
	/*IF dto.customerDto_CustomerID_in != null*/AND customerDto.CustomerID in /*dto.customerDto_CustomerID_in*/(0)/*END*/
	/*IF dto.customerDto_CustomerName_matchFull != null*/AND customerDto.CustomerName Like /*dto.customerDto_CustomerName_matchFull*/'%TestData%'/*END*/
	/*IF dto.customerDto_CustomerName_matchFront != null*/AND customerDto.CustomerName Like /*dto.customerDto_CustomerName_matchFront*/'TestData%'/*END*/
	/*IF dto.customerDto_CustomerName_matchBack != null*/AND customerDto.CustomerName Like /*dto.customerDto_CustomerName_matchBack*/'%TestData'/*END*/
	/*IF dto.customerDto_CustomerName != null*/AND customerDto.CustomerName = /*dto.customerDto_CustomerName*/'TestData'/*END*/
	/*IF dto.customerDto_CustomerName_not != null*/AND customerDto.CustomerName != /*dto.customerDto_CustomerName_not*/'TestData'/*END*/
	/*IF dto.customerDto_CustomerName_large != null*/AND  /*dto.customerDto_CustomerName_large*/'TestData' < customerDto.CustomerName/*END*/
	/*IF dto.customerDto_CustomerName_moreLarge != null*/AND  /*dto.customerDto_CustomerName_moreLarge*/'TestData' <= customerDto.CustomerName/*END*/
	/*IF dto.customerDto_CustomerName_from != null*/AND  /*dto.customerDto_CustomerName_from*/'TestData' <= customerDto.CustomerName/*END*/
	/*IF dto.customerDto_CustomerName_to != null*/AND customerDto.CustomerName <= /*dto.customerDto_CustomerName_to*/'TestData'/*END*/
	/*IF dto.customerDto_CustomerName_moreSmall != null*/AND customerDto.CustomerName <= /*dto.customerDto_CustomerName_moreSmall*/'TestData'/*END*/
	/*IF dto.customerDto_CustomerName_small != null*/AND customerDto.CustomerName < /*dto.customerDto_CustomerName_small*/'TestData'/*END*/
	/*IF dto.customerDto_CustomerName_isNull != null*/AND customerDto.CustomerName is null /*END*/
	/*IF dto.customerDto_CustomerName_isNotNull != null*/AND customerDto.CustomerName is not null/*END*/
	/*IF dto.customerDto_CustomerName_in != null*/AND customerDto.CustomerName in /*dto.customerDto_CustomerName_in*/('TestData')/*END*/

	/*IF dto.orderTitleDto_OrderTitleID != null*/AND orderTitleDto.OrderTitleID = /*dto.orderTitleDto_OrderTitleID*/0/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_not != null*/AND orderTitleDto.OrderTitleID != /*dto.orderTitleDto_OrderTitleID_not*/0/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_large != null*/AND  /*dto.orderTitleDto_OrderTitleID_large*/0 < orderTitleDto.OrderTitleID/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_moreLarge != null*/AND  /*dto.orderTitleDto_OrderTitleID_moreLarge*/0 <= orderTitleDto.OrderTitleID/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_from != null*/AND  /*dto.orderTitleDto_OrderTitleID_from*/0 <= orderTitleDto.OrderTitleID/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_to != null*/AND orderTitleDto.OrderTitleID <= /*dto.orderTitleDto_OrderTitleID_to*/0/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_moreSmall != null*/AND orderTitleDto.OrderTitleID <= /*dto.orderTitleDto_OrderTitleID_moreSmall*/0/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_small != null*/AND orderTitleDto.OrderTitleID < /*dto.orderTitleDto_OrderTitleID_small*/0/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_isNull != null*/AND orderTitleDto.OrderTitleID is null /*END*/
	/*IF dto.orderTitleDto_OrderTitleID_isNotNull != null*/AND orderTitleDto.OrderTitleID is not null/*END*/
	/*IF dto.orderTitleDto_OrderTitleID_in != null*/AND orderTitleDto.OrderTitleID in /*dto.orderTitleDto_OrderTitleID_in*/(0)/*END*/
	/*IF dto.orderTitleDto_OrderDate != null*/AND orderTitleDto.OrderDate = /*dto.orderTitleDto_OrderDate*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.orderTitleDto_OrderDate_not != null*/AND orderTitleDto.OrderDate != /*dto.orderTitleDto_OrderDate_not*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.orderTitleDto_OrderDate_large != null*/AND  /*dto.orderTitleDto_OrderDate_large*/'2005-01-01 00:00:00.0000' < orderTitleDto.OrderDate/*END*/
	/*IF dto.orderTitleDto_OrderDate_moreLarge != null*/AND  /*dto.orderTitleDto_OrderDate_moreLarge*/'2005-01-01 00:00:00.0000' <= orderTitleDto.OrderDate/*END*/
	/*IF dto.orderTitleDto_OrderDate_from != null*/AND  /*dto.orderTitleDto_OrderDate_from*/'2005-01-01 00:00:00.0000' <= orderTitleDto.OrderDate/*END*/
	/*IF dto.orderTitleDto_OrderDate_to != null*/AND orderTitleDto.OrderDate <= /*dto.orderTitleDto_OrderDate_to*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.orderTitleDto_OrderDate_moreSmall != null*/AND orderTitleDto.OrderDate <= /*dto.orderTitleDto_OrderDate_moreSmall*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.orderTitleDto_OrderDate_small != null*/AND orderTitleDto.OrderDate < /*dto.orderTitleDto_OrderDate_small*/'2005-01-01 00:00:00.0000'/*END*/
	/*IF dto.orderTitleDto_OrderDate_isNull != null*/AND orderTitleDto.OrderDate is null /*END*/
	/*IF dto.orderTitleDto_OrderDate_isNotNull != null*/AND orderTitleDto.OrderDate is not null/*END*/
	/*IF dto.orderTitleDto_OrderDate_in != null*/AND orderTitleDto.OrderDate in /*dto.orderTitleDto_OrderDate_in*/('2005-01-01 00:00:00.0000')/*END*/
	/*IF dto.orderTitleDto_CustomerID != null*/AND orderTitleDto.CustomerID = /*dto.orderTitleDto_CustomerID*/0/*END*/
	/*IF dto.orderTitleDto_CustomerID_not != null*/AND orderTitleDto.CustomerID != /*dto.orderTitleDto_CustomerID_not*/0/*END*/
	/*IF dto.orderTitleDto_CustomerID_large != null*/AND  /*dto.orderTitleDto_CustomerID_large*/0 < orderTitleDto.CustomerID/*END*/
	/*IF dto.orderTitleDto_CustomerID_moreLarge != null*/AND  /*dto.orderTitleDto_CustomerID_moreLarge*/0 <= orderTitleDto.CustomerID/*END*/
	/*IF dto.orderTitleDto_CustomerID_from != null*/AND  /*dto.orderTitleDto_CustomerID_from*/0 <= orderTitleDto.CustomerID/*END*/
	/*IF dto.orderTitleDto_CustomerID_to != null*/AND orderTitleDto.CustomerID <= /*dto.orderTitleDto_CustomerID_to*/0/*END*/
	/*IF dto.orderTitleDto_CustomerID_moreSmall != null*/AND orderTitleDto.CustomerID <= /*dto.orderTitleDto_CustomerID_moreSmall*/0/*END*/
	/*IF dto.orderTitleDto_CustomerID_small != null*/AND orderTitleDto.CustomerID < /*dto.orderTitleDto_CustomerID_small*/0/*END*/
	/*IF dto.orderTitleDto_CustomerID_isNull != null*/AND orderTitleDto.CustomerID is null /*END*/
	/*IF dto.orderTitleDto_CustomerID_isNotNull != null*/AND orderTitleDto.CustomerID is not null/*END*/
	/*IF dto.orderTitleDto_CustomerID_in != null*/AND orderTitleDto.CustomerID in /*dto.orderTitleDto_CustomerID_in*/(0)/*END*/
	/*IF dto.orderTitleDto_Status != null*/AND orderTitleDto.Status = /*dto.orderTitleDto_Status*/Integer/*END*/
	/*IF dto.orderTitleDto_Status_not != null*/AND orderTitleDto.Status != /*dto.orderTitleDto_Status_not*/Integer/*END*/
	/*IF dto.orderTitleDto_Status_large != null*/AND  /*dto.orderTitleDto_Status_large*/Integer < orderTitleDto.Status/*END*/
	/*IF dto.orderTitleDto_Status_moreLarge != null*/AND  /*dto.orderTitleDto_Status_moreLarge*/Integer <= orderTitleDto.Status/*END*/
	/*IF dto.orderTitleDto_Status_from != null*/AND  /*dto.orderTitleDto_Status_from*/Integer <= orderTitleDto.Status/*END*/
	/*IF dto.orderTitleDto_Status_to != null*/AND orderTitleDto.Status <= /*dto.orderTitleDto_Status_to*/Integer/*END*/
	/*IF dto.orderTitleDto_Status_moreSmall != null*/AND orderTitleDto.Status <= /*dto.orderTitleDto_Status_moreSmall*/Integer/*END*/
	/*IF dto.orderTitleDto_Status_small != null*/AND orderTitleDto.Status < /*dto.orderTitleDto_Status_small*/Integer/*END*/
	/*IF dto.orderTitleDto_Status_isNull != null*/AND orderTitleDto.Status is null /*END*/
	/*IF dto.orderTitleDto_Status_isNotNull != null*/AND orderTitleDto.Status is not null/*END*/
	/*IF dto.orderTitleDto_Status_in != null*/AND orderTitleDto.Status in /*dto.orderTitleDto_Status_in*/(Integer)/*END*/

	/*IF keys != null*/AND Shipping.shippingID in /*keys*/(0) /*END*/
	
/*END*/
/*$dto.orderList*/
