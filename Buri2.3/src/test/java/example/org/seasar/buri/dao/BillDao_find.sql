select
		Bill.billID as billID
	,Bill.shippingID as shippingID
	,Bill.orderTitleID as orderTitleID
	,Bill.customerID as customerID
from
	Bill
	,BuriPathData
/*BEGIN*/
where
	
	/*IF dto.billID != null*/ billID = /*dto.billID*/0/*END*/
	/*IF dto.billID_not != null*/AND billID != /*dto.billID_not*/0/*END*/
	/*IF dto.billID_large != null*/AND  /*dto.billID_large*/0 < billID/*END*/
	/*IF dto.billID_moreLarge != null*/AND  /*dto.billID_moreLarge*/0 <= billID/*END*/
	/*IF dto.billID_from != null*/AND  /*dto.billID_from*/0 <= billID/*END*/
	/*IF dto.billID_to != null*/AND billID <= /*dto.billID_to*/0/*END*/
	/*IF dto.billID_moreSmall != null*/AND billID <= /*dto.billID_moreSmall*/0/*END*/
	/*IF dto.billID_small != null*/AND billID < /*dto.billID_small*/0/*END*/
	/*IF dto.billID_isNull != null*/AND billID is null /*END*/
	/*IF dto.billID_isNotNull != null*/AND billID is not null/*END*/
	/*IF dto.billID_in != null*/AND billID in /*dto.billID_in*/(0)/*END*/

	
	/*IF dto.shippingID != null*/AND shippingID = /*dto.shippingID*/0/*END*/
	/*IF dto.shippingID_not != null*/AND shippingID != /*dto.shippingID_not*/0/*END*/
	/*IF dto.shippingID_large != null*/AND  /*dto.shippingID_large*/0 < shippingID/*END*/
	/*IF dto.shippingID_moreLarge != null*/AND  /*dto.shippingID_moreLarge*/0 <= shippingID/*END*/
	/*IF dto.shippingID_from != null*/AND  /*dto.shippingID_from*/0 <= shippingID/*END*/
	/*IF dto.shippingID_to != null*/AND shippingID <= /*dto.shippingID_to*/0/*END*/
	/*IF dto.shippingID_moreSmall != null*/AND shippingID <= /*dto.shippingID_moreSmall*/0/*END*/
	/*IF dto.shippingID_small != null*/AND shippingID < /*dto.shippingID_small*/0/*END*/
	/*IF dto.shippingID_isNull != null*/AND shippingID is null /*END*/
	/*IF dto.shippingID_isNotNull != null*/AND shippingID is not null/*END*/
	/*IF dto.shippingID_in != null*/AND shippingID in /*dto.shippingID_in*/(0)/*END*/

	
	/*IF dto.orderTitleID != null*/AND orderTitleID = /*dto.orderTitleID*/0/*END*/
	/*IF dto.orderTitleID_not != null*/AND orderTitleID != /*dto.orderTitleID_not*/0/*END*/
	/*IF dto.orderTitleID_large != null*/AND  /*dto.orderTitleID_large*/0 < orderTitleID/*END*/
	/*IF dto.orderTitleID_moreLarge != null*/AND  /*dto.orderTitleID_moreLarge*/0 <= orderTitleID/*END*/
	/*IF dto.orderTitleID_from != null*/AND  /*dto.orderTitleID_from*/0 <= orderTitleID/*END*/
	/*IF dto.orderTitleID_to != null*/AND orderTitleID <= /*dto.orderTitleID_to*/0/*END*/
	/*IF dto.orderTitleID_moreSmall != null*/AND orderTitleID <= /*dto.orderTitleID_moreSmall*/0/*END*/
	/*IF dto.orderTitleID_small != null*/AND orderTitleID < /*dto.orderTitleID_small*/0/*END*/
	/*IF dto.orderTitleID_isNull != null*/AND orderTitleID is null /*END*/
	/*IF dto.orderTitleID_isNotNull != null*/AND orderTitleID is not null/*END*/
	/*IF dto.orderTitleID_in != null*/AND orderTitleID in /*dto.orderTitleID_in*/(0)/*END*/

	
	/*IF dto.customerID != null*/AND customerID = /*dto.customerID*/0/*END*/
	/*IF dto.customerID_not != null*/AND customerID != /*dto.customerID_not*/0/*END*/
	/*IF dto.customerID_large != null*/AND  /*dto.customerID_large*/0 < customerID/*END*/
	/*IF dto.customerID_moreLarge != null*/AND  /*dto.customerID_moreLarge*/0 <= customerID/*END*/
	/*IF dto.customerID_from != null*/AND  /*dto.customerID_from*/0 <= customerID/*END*/
	/*IF dto.customerID_to != null*/AND customerID <= /*dto.customerID_to*/0/*END*/
	/*IF dto.customerID_moreSmall != null*/AND customerID <= /*dto.customerID_moreSmall*/0/*END*/
	/*IF dto.customerID_small != null*/AND customerID < /*dto.customerID_small*/0/*END*/
	/*IF dto.customerID_isNull != null*/AND customerID is null /*END*/
	/*IF dto.customerID_isNotNull != null*/AND customerID is not null/*END*/
	/*IF dto.customerID_in != null*/AND customerID in /*dto.customerID_in*/(0)/*END*/

	AND Bill.billID = BuriPathData.pkeyNum
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
