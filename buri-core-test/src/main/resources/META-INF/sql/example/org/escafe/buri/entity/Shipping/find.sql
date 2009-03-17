select
	Shipping.shippingID as shippingID
	,Shipping.shippingDate as shippingDate
	,Shipping.orderTitleID as orderTitleID
	,Shipping.customerID as customerID
from
	Shipping
	,BuriPathData
/*BEGIN*/
where
	
	/*IF dto.shippingID != null*/ shippingID = /*dto.shippingID*/0/*END*/
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

	
	/*IF dto.shippingDate != null*/AND shippingDate = /*dto.shippingDate*/Timestamp/*END*/
	/*IF dto.shippingDate_not != null*/AND shippingDate != /*dto.shippingDate_not*/Timestamp/*END*/
	/*IF dto.shippingDate_large != null*/AND  /*dto.shippingDate_large*/Timestamp < shippingDate/*END*/
	/*IF dto.shippingDate_moreLarge != null*/AND  /*dto.shippingDate_moreLarge*/Timestamp <= shippingDate/*END*/
	/*IF dto.shippingDate_from != null*/AND  /*dto.shippingDate_from*/Timestamp <= shippingDate/*END*/
	/*IF dto.shippingDate_to != null*/AND shippingDate <= /*dto.shippingDate_to*/Timestamp/*END*/
	/*IF dto.shippingDate_moreSmall != null*/AND shippingDate <= /*dto.shippingDate_moreSmall*/Timestamp/*END*/
	/*IF dto.shippingDate_small != null*/AND shippingDate < /*dto.shippingDate_small*/Timestamp/*END*/
	/*IF dto.shippingDate_isNull != null*/AND shippingDate is null /*END*/
	/*IF dto.shippingDate_isNotNull != null*/AND shippingDate is not null/*END*/
	/*IF dto.shippingDate_in != null*/AND shippingDate in /*dto.shippingDate_in*/(Timestamp)/*END*/

	
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

	AND Shipping.shippingID = BuriPathData.pkeyNum
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
