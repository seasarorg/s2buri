SELECT
	BURI_TEST_USER.USER_ID AS USER_ID,
	BURI_TEST_USER.USER_NAME AS USER_NAME,
	BURI_TEST_USER.ROLE_NAME AS ROLE_NAME,
	BURI_TEST_USER.PARENT_USER_ID AS PARENT_USER_ID
FROM
	BURI_TEST_USER,
	BURI_PATH_DATA
/*BEGIN*/
WHERE
	/*IF dto.userId != null*/ USER_ID = /*dto.userId*/0/*END*/
	/*IF dto.userId_not != null*/AND USER_ID != /*dto.userId_not*/0/*END*/
	/*IF dto.userId_large != null*/AND  /*dto.userId_large*/0 < USER_ID/*END*/
	/*IF dto.userId_moreLarge != null*/AND  /*dto.userId_moreLarge*/0 <= USER_ID/*END*/
	/*IF dto.userId_from != null*/AND  /*dto.userId_from*/0 <= USER_ID/*END*/
	/*IF dto.userId_to != null*/AND USER_ID <= /*dto.userId_to*/0/*END*/
	/*IF dto.userId_moreSmall != null*/AND USER_ID <= /*dto.userId_moreSmall*/0/*END*/
	/*IF dto.userId_small != null*/AND USER_ID < /*dto.userId_small*/0/*END*/
	/*IF dto.userId_isNull != null*/AND USER_ID is null /*END*/
	/*IF dto.userId_isNotNull != null*/AND USER_ID is not null/*END*/
	/*IF dto.userId_in != null*/AND USER_ID in /*dto.userId_in*/(0)/*END*/

	
	/*IF dto.userName_matchFull != null*/AND USER_NAME Like /*dto.userName_matchFull*/'%TestData%'/*END*/
	/*IF dto.userName_matchFront != null*/AND USER_NAME Like /*dto.userName_matchFront*/'TestData%'/*END*/
	/*IF dto.userName_matchBack != null*/AND USER_NAME Like /*dto.userName_matchBack*/'%TestData'/*END*/
	/*IF dto.userName != null*/AND USER_NAME = /*dto.userName*/'TestData'/*END*/
	/*IF dto.userName_not != null*/AND USER_NAME != /*dto.userName_not*/'TestData'/*END*/
	/*IF dto.userName_large != null*/AND  /*dto.userName_large*/'TestData' < USER_NAME/*END*/
	/*IF dto.userName_moreLarge != null*/AND  /*dto.userName_moreLarge*/'TestData' <= USER_NAME/*END*/
	/*IF dto.userName_from != null*/AND  /*dto.userName_from*/'TestData' <= USER_NAME/*END*/
	/*IF dto.userName_to != null*/AND USER_NAME <= /*dto.userName_to*/'TestData'/*END*/
	/*IF dto.userName_moreSmall != null*/AND USER_NAME <= /*dto.userName_moreSmall*/'TestData'/*END*/
	/*IF dto.userName_small != null*/AND USER_NAME < /*dto.userName_small*/'TestData'/*END*/
	/*IF dto.userName_isNull != null*/AND USER_NAME is null /*END*/
	/*IF dto.userName_isNotNull != null*/AND USER_NAME is not null/*END*/
	/*IF dto.userName_in != null*/AND USER_NAME in /*dto.userName_in*/('TestData')/*END*/

	
	/*IF dto.roleName_matchFull != null*/AND ROLE_NAME Like /*dto.roleName_matchFull*/'%TestData%'/*END*/
	/*IF dto.roleName_matchFront != null*/AND ROLE_NAME Like /*dto.roleName_matchFront*/'TestData%'/*END*/
	/*IF dto.roleName_matchBack != null*/AND ROLE_NAME Like /*dto.roleName_matchBack*/'%TestData'/*END*/
	/*IF dto.roleName != null*/AND ROLE_NAME = /*dto.roleName*/'TestData'/*END*/
	/*IF dto.roleName_not != null*/AND ROLE_NAME != /*dto.roleName_not*/'TestData'/*END*/
	/*IF dto.roleName_large != null*/AND  /*dto.roleName_large*/'TestData' < ROLE_NAME/*END*/
	/*IF dto.roleName_moreLarge != null*/AND  /*dto.roleName_moreLarge*/'TestData' <= ROLE_NAME/*END*/
	/*IF dto.roleName_from != null*/AND  /*dto.roleName_from*/'TestData' <= ROLE_NAME/*END*/
	/*IF dto.roleName_to != null*/AND ROLE_NAME <= /*dto.roleName_to*/'TestData'/*END*/
	/*IF dto.roleName_moreSmall != null*/AND ROLE_NAME <= /*dto.roleName_moreSmall*/'TestData'/*END*/
	/*IF dto.roleName_small != null*/AND ROLE_NAME < /*dto.roleName_small*/'TestData'/*END*/
	/*IF dto.roleName_isNull != null*/AND ROLE_NAME is null /*END*/
	/*IF dto.roleName_isNotNull != null*/AND ROLE_NAME is not null/*END*/
	/*IF dto.roleName_in != null*/AND ROLE_NAME in /*dto.roleName_in*/('TestData')/*END*/

	
	/*IF dto.parentUserId != null*/AND PARENT_USER_ID = /*dto.parentUserId*/int/*END*/
	/*IF dto.parentUserId_not != null*/AND PARENT_USER_ID != /*dto.parentUserId_not*/int/*END*/
	/*IF dto.parentUserId_large != null*/AND  /*dto.parentUserId_large*/int < PARENT_USER_ID/*END*/
	/*IF dto.parentUserId_moreLarge != null*/AND  /*dto.parentUserId_moreLarge*/int <= PARENT_USER_ID/*END*/
	/*IF dto.parentUserId_from != null*/AND  /*dto.parentUserId_from*/int <= PARENT_USER_ID/*END*/
	/*IF dto.parentUserId_to != null*/AND PARENT_USER_ID <= /*dto.parentUserId_to*/int/*END*/
	/*IF dto.parentUserId_moreSmall != null*/AND PARENT_USER_ID <= /*dto.parentUserId_moreSmall*/int/*END*/
	/*IF dto.parentUserId_small != null*/AND PARENT_USER_ID < /*dto.parentUserId_small*/int/*END*/
	/*IF dto.parentUserId_isNull != null*/AND PARENT_USER_ID is null /*END*/
	/*IF dto.parentUserId_isNotNull != null*/AND PARENT_USER_ID is not null/*END*/
	/*IF dto.parentUserId_in != null*/AND PARENT_USER_ID in /*dto.parentUserId_in*/(int)/*END*/

	AND BURI_TEST_USER.USER_ID = BURI_PATH_DATA.PKEY_NUM
	/*IF paths != null*/AND BURI_PATH_DATA.PATH_NAME in /*paths*/('buri.path.names')/*END*/
	
/*END*/
/*$dto.orderList*/
