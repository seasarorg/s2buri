select
		OrderTitle.orderTitleID as orderTitleID
	,OrderTitle.orderDate as orderDate
	,OrderTitle.customerID as customerID
	,OrderTitle.status as status
from
	OrderTitle
	,BuriPathDataUser
/*BEGIN*/
where
	
	/*IF dto.orderTitleID != null*/ orderTitleID = /*dto.orderTitleID*/0/*END*/
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

	
	/*IF dto.orderDate != null*/AND orderDate = /*dto.orderDate*/Timestamp/*END*/
	/*IF dto.orderDate_not != null*/AND orderDate != /*dto.orderDate_not*/Timestamp/*END*/
	/*IF dto.orderDate_large != null*/AND  /*dto.orderDate_large*/Timestamp < orderDate/*END*/
	/*IF dto.orderDate_moreLarge != null*/AND  /*dto.orderDate_moreLarge*/Timestamp <= orderDate/*END*/
	/*IF dto.orderDate_from != null*/AND  /*dto.orderDate_from*/Timestamp <= orderDate/*END*/
	/*IF dto.orderDate_to != null*/AND orderDate <= /*dto.orderDate_to*/Timestamp/*END*/
	/*IF dto.orderDate_moreSmall != null*/AND orderDate <= /*dto.orderDate_moreSmall*/Timestamp/*END*/
	/*IF dto.orderDate_small != null*/AND orderDate < /*dto.orderDate_small*/Timestamp/*END*/
	/*IF dto.orderDate_isNull != null*/AND orderDate is null /*END*/
	/*IF dto.orderDate_isNotNull != null*/AND orderDate is not null/*END*/
	/*IF dto.orderDate_in != null*/AND orderDate in /*dto.orderDate_in*/(Timestamp)/*END*/

	
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

	
	/*IF dto.status != null*/AND status = /*dto.status*/Integer/*END*/
	/*IF dto.status_not != null*/AND status != /*dto.status_not*/Integer/*END*/
	/*IF dto.status_large != null*/AND  /*dto.status_large*/Integer < status/*END*/
	/*IF dto.status_moreLarge != null*/AND  /*dto.status_moreLarge*/Integer <= status/*END*/
	/*IF dto.status_from != null*/AND  /*dto.status_from*/Integer <= status/*END*/
	/*IF dto.status_to != null*/AND status <= /*dto.status_to*/Integer/*END*/
	/*IF dto.status_moreSmall != null*/AND status <= /*dto.status_moreSmall*/Integer/*END*/
	/*IF dto.status_small != null*/AND status < /*dto.status_small*/Integer/*END*/
	/*IF dto.status_isNull != null*/AND status is null /*END*/
	/*IF dto.status_isNotNull != null*/AND status is not null/*END*/
	/*IF dto.status_in != null*/AND status in /*dto.status_in*/(Integer)/*END*/

	AND OrderTitle.orderTitleID = BuriPathDataUser.pkeyNum
	/*IF paths != null*/AND BuriPathDataUser.PathName in /*paths*/('buri.path.names')/*END*/
	/*IF userIDs != null*/AND BuriPathDataUser.BuriUserID in /*userIDs*/(1)/*END*/
	
/*END*/
/*$dto.orderList*/
