select
	*
from
	ShippingItem
/*BEGIN*/
where
	
	/*IF dto.shippingItemID != null*/ shippingItemID = /*dto.shippingItemID*/0/*END*/
	/*IF dto.shippingItemID_not != null*/AND shippingItemID != /*dto.shippingItemID_not*/0/*END*/
	/*IF dto.shippingItemID_large != null*/AND  /*dto.shippingItemID_large*/0 < shippingItemID/*END*/
	/*IF dto.shippingItemID_moreLarge != null*/AND  /*dto.shippingItemID_moreLarge*/0 <= shippingItemID/*END*/
	/*IF dto.shippingItemID_from != null*/AND  /*dto.shippingItemID_from*/0 <= shippingItemID/*END*/
	/*IF dto.shippingItemID_to != null*/AND shippingItemID <= /*dto.shippingItemID_to*/0/*END*/
	/*IF dto.shippingItemID_moreSmall != null*/AND shippingItemID <= /*dto.shippingItemID_moreSmall*/0/*END*/
	/*IF dto.shippingItemID_small != null*/AND shippingItemID < /*dto.shippingItemID_small*/0/*END*/
	/*IF dto.shippingItemID_isNull != null*/AND shippingItemID is null /*END*/
	/*IF dto.shippingItemID_isNotNull != null*/AND shippingItemID is not null/*END*/
	/*IF dto.shippingItemID_in != null*/AND shippingItemID in /*dto.shippingItemID_in*/(0)/*END*/

	
	/*IF dto.orderDetailID != null*/AND orderDetailID = /*dto.orderDetailID*/0/*END*/
	/*IF dto.orderDetailID_not != null*/AND orderDetailID != /*dto.orderDetailID_not*/0/*END*/
	/*IF dto.orderDetailID_large != null*/AND  /*dto.orderDetailID_large*/0 < orderDetailID/*END*/
	/*IF dto.orderDetailID_moreLarge != null*/AND  /*dto.orderDetailID_moreLarge*/0 <= orderDetailID/*END*/
	/*IF dto.orderDetailID_from != null*/AND  /*dto.orderDetailID_from*/0 <= orderDetailID/*END*/
	/*IF dto.orderDetailID_to != null*/AND orderDetailID <= /*dto.orderDetailID_to*/0/*END*/
	/*IF dto.orderDetailID_moreSmall != null*/AND orderDetailID <= /*dto.orderDetailID_moreSmall*/0/*END*/
	/*IF dto.orderDetailID_small != null*/AND orderDetailID < /*dto.orderDetailID_small*/0/*END*/
	/*IF dto.orderDetailID_isNull != null*/AND orderDetailID is null /*END*/
	/*IF dto.orderDetailID_isNotNull != null*/AND orderDetailID is not null/*END*/
	/*IF dto.orderDetailID_in != null*/AND orderDetailID in /*dto.orderDetailID_in*/(0)/*END*/

	
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

	/*IF keys != null*/AND shippingItemID in /*keys*/(0) /*END*/
	
/*END*/
/*$dto.orderList*/
