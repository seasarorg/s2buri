select
		BuriTestUser.userID as userID
	,BuriTestUser.userName as userName
	,BuriTestUser.roleName as roleName
	,BuriTestUser.parentUserID as parentUserID
from
	BuriTestUser
	,BuriPathDataUser
/*BEGIN*/
where
	
	/*IF dto.userID != null*/ userID = /*dto.userID*/0/*END*/
	/*IF dto.userID_not != null*/AND userID != /*dto.userID_not*/0/*END*/
	/*IF dto.userID_large != null*/AND  /*dto.userID_large*/0 < userID/*END*/
	/*IF dto.userID_moreLarge != null*/AND  /*dto.userID_moreLarge*/0 <= userID/*END*/
	/*IF dto.userID_from != null*/AND  /*dto.userID_from*/0 <= userID/*END*/
	/*IF dto.userID_to != null*/AND userID <= /*dto.userID_to*/0/*END*/
	/*IF dto.userID_moreSmall != null*/AND userID <= /*dto.userID_moreSmall*/0/*END*/
	/*IF dto.userID_small != null*/AND userID < /*dto.userID_small*/0/*END*/
	/*IF dto.userID_isNull != null*/AND userID is null /*END*/
	/*IF dto.userID_isNotNull != null*/AND userID is not null/*END*/
	/*IF dto.userID_in != null*/AND userID in /*dto.userID_in*/(0)/*END*/

	
	/*IF dto.userName_matchFull != null*/AND userName Like /*dto.userName_matchFull*/'%TestData%'/*END*/
	/*IF dto.userName_matchFront != null*/AND userName Like /*dto.userName_matchFront*/'TestData%'/*END*/
	/*IF dto.userName_matchBack != null*/AND userName Like /*dto.userName_matchBack*/'%TestData'/*END*/
	/*IF dto.userName != null*/AND userName = /*dto.userName*/'TestData'/*END*/
	/*IF dto.userName_not != null*/AND userName != /*dto.userName_not*/'TestData'/*END*/
	/*IF dto.userName_large != null*/AND  /*dto.userName_large*/'TestData' < userName/*END*/
	/*IF dto.userName_moreLarge != null*/AND  /*dto.userName_moreLarge*/'TestData' <= userName/*END*/
	/*IF dto.userName_from != null*/AND  /*dto.userName_from*/'TestData' <= userName/*END*/
	/*IF dto.userName_to != null*/AND userName <= /*dto.userName_to*/'TestData'/*END*/
	/*IF dto.userName_moreSmall != null*/AND userName <= /*dto.userName_moreSmall*/'TestData'/*END*/
	/*IF dto.userName_small != null*/AND userName < /*dto.userName_small*/'TestData'/*END*/
	/*IF dto.userName_isNull != null*/AND userName is null /*END*/
	/*IF dto.userName_isNotNull != null*/AND userName is not null/*END*/
	/*IF dto.userName_in != null*/AND userName in /*dto.userName_in*/('TestData')/*END*/

	
	/*IF dto.roleName_matchFull != null*/AND roleName Like /*dto.roleName_matchFull*/'%TestData%'/*END*/
	/*IF dto.roleName_matchFront != null*/AND roleName Like /*dto.roleName_matchFront*/'TestData%'/*END*/
	/*IF dto.roleName_matchBack != null*/AND roleName Like /*dto.roleName_matchBack*/'%TestData'/*END*/
	/*IF dto.roleName != null*/AND roleName = /*dto.roleName*/'TestData'/*END*/
	/*IF dto.roleName_not != null*/AND roleName != /*dto.roleName_not*/'TestData'/*END*/
	/*IF dto.roleName_large != null*/AND  /*dto.roleName_large*/'TestData' < roleName/*END*/
	/*IF dto.roleName_moreLarge != null*/AND  /*dto.roleName_moreLarge*/'TestData' <= roleName/*END*/
	/*IF dto.roleName_from != null*/AND  /*dto.roleName_from*/'TestData' <= roleName/*END*/
	/*IF dto.roleName_to != null*/AND roleName <= /*dto.roleName_to*/'TestData'/*END*/
	/*IF dto.roleName_moreSmall != null*/AND roleName <= /*dto.roleName_moreSmall*/'TestData'/*END*/
	/*IF dto.roleName_small != null*/AND roleName < /*dto.roleName_small*/'TestData'/*END*/
	/*IF dto.roleName_isNull != null*/AND roleName is null /*END*/
	/*IF dto.roleName_isNotNull != null*/AND roleName is not null/*END*/
	/*IF dto.roleName_in != null*/AND roleName in /*dto.roleName_in*/('TestData')/*END*/

	
	/*IF dto.parentUserID != null*/AND parentUserID = /*dto.parentUserID*/int/*END*/
	/*IF dto.parentUserID_not != null*/AND parentUserID != /*dto.parentUserID_not*/int/*END*/
	/*IF dto.parentUserID_large != null*/AND  /*dto.parentUserID_large*/int < parentUserID/*END*/
	/*IF dto.parentUserID_moreLarge != null*/AND  /*dto.parentUserID_moreLarge*/int <= parentUserID/*END*/
	/*IF dto.parentUserID_from != null*/AND  /*dto.parentUserID_from*/int <= parentUserID/*END*/
	/*IF dto.parentUserID_to != null*/AND parentUserID <= /*dto.parentUserID_to*/int/*END*/
	/*IF dto.parentUserID_moreSmall != null*/AND parentUserID <= /*dto.parentUserID_moreSmall*/int/*END*/
	/*IF dto.parentUserID_small != null*/AND parentUserID < /*dto.parentUserID_small*/int/*END*/
	/*IF dto.parentUserID_isNull != null*/AND parentUserID is null /*END*/
	/*IF dto.parentUserID_isNotNull != null*/AND parentUserID is not null/*END*/
	/*IF dto.parentUserID_in != null*/AND parentUserID in /*dto.parentUserID_in*/(int)/*END*/

	AND BuriTestUser.userID = BuriPathDataUser.pkeyNum
	/*IF paths != null*/AND BuriPathDataUser.PathName in /*paths*/('buri.path.names')/*END*/
	/*IF userIDs != null*/AND BuriPathDataUser.BuriUserID in /*userIDs*/(1)/*END*/
	
/*END*/
/*$dto.orderList*/
