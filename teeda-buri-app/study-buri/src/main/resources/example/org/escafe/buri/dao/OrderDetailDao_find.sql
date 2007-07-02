select
		OrderDetail.orderDetailID as orderDetailID
	,OrderDetail.orderCount as orderCount
	,OrderDetail.itemID as itemID
	,OrderDetail.orderTitleID as orderTitleID
from
	OrderDetail
	,BuriPathData
/*BEGIN*/
where
	
	/*IF dto.orderDetailID != null*/ orderDetailID = /*dto.orderDetailID*/0/*END*/
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

	
	/*IF dto.orderCount != null*/AND orderCount = /*dto.orderCount*/int/*END*/
	/*IF dto.orderCount_not != null*/AND orderCount != /*dto.orderCount_not*/int/*END*/
	/*IF dto.orderCount_large != null*/AND  /*dto.orderCount_large*/int < orderCount/*END*/
	/*IF dto.orderCount_moreLarge != null*/AND  /*dto.orderCount_moreLarge*/int <= orderCount/*END*/
	/*IF dto.orderCount_from != null*/AND  /*dto.orderCount_from*/int <= orderCount/*END*/
	/*IF dto.orderCount_to != null*/AND orderCount <= /*dto.orderCount_to*/int/*END*/
	/*IF dto.orderCount_moreSmall != null*/AND orderCount <= /*dto.orderCount_moreSmall*/int/*END*/
	/*IF dto.orderCount_small != null*/AND orderCount < /*dto.orderCount_small*/int/*END*/
	/*IF dto.orderCount_isNull != null*/AND orderCount is null /*END*/
	/*IF dto.orderCount_isNotNull != null*/AND orderCount is not null/*END*/
	/*IF dto.orderCount_in != null*/AND orderCount in /*dto.orderCount_in*/(int)/*END*/

	
	/*IF dto.itemID != null*/AND itemID = /*dto.itemID*/0/*END*/
	/*IF dto.itemID_not != null*/AND itemID != /*dto.itemID_not*/0/*END*/
	/*IF dto.itemID_large != null*/AND  /*dto.itemID_large*/0 < itemID/*END*/
	/*IF dto.itemID_moreLarge != null*/AND  /*dto.itemID_moreLarge*/0 <= itemID/*END*/
	/*IF dto.itemID_from != null*/AND  /*dto.itemID_from*/0 <= itemID/*END*/
	/*IF dto.itemID_to != null*/AND itemID <= /*dto.itemID_to*/0/*END*/
	/*IF dto.itemID_moreSmall != null*/AND itemID <= /*dto.itemID_moreSmall*/0/*END*/
	/*IF dto.itemID_small != null*/AND itemID < /*dto.itemID_small*/0/*END*/
	/*IF dto.itemID_isNull != null*/AND itemID is null /*END*/
	/*IF dto.itemID_isNotNull != null*/AND itemID is not null/*END*/
	/*IF dto.itemID_in != null*/AND itemID in /*dto.itemID_in*/(0)/*END*/

	
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

	AND OrderDetail.orderDetailID = BuriPathData.pkeyNum
	/*IF paths != null*/AND BuriPathData.PathName in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
