/*
  この Java(J2SE1.4) 用祝日判定コードは、下記ウェブサイトで
  公開されているのVBA用のコードを元に、主にJavascript版と
  C言語版を参考にして
  阿蛭 栄一( http://www.age.ne.jp/x/abiru/index.html )が
  編集移植しました。
  << 改編履歴 >>
    Tue Mar 16 2004
        Stringの比較で = や != を使用していたのを、equalsメソッドに変更
        
    更にチューニング他の目的で数カ所変更 by makotan
*/

/*
_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
_/
_/　CopyRight(C) K.Tsunoda(AddinBox) 2001 All Rights Reserved.
_/　( http://www.h3.dion.ne.jp/~sakatsu/index.htm )
_/
_/　　この祝日マクロは『kt関数アドイン』で使用しているものです。
_/　　このロジックは、レスポンスを第一義として、可能な限り少ない
_/　  【条件判定の実行】で結果を出せるように設計してあります。
_/　　この関数では、２００７年施行の改正祝日法(昭和の日)までを
_/　  サポートしています(９月の国民の休日を含む)。
_/
_/　(*1)このマクロを引用するに当たっては、必ずこのコメントも
_/　　　一緒に引用する事とします。
_/　(*2)他サイト上で本マクロを直接引用する事は、ご遠慮願います。
_/　　　【 http://www.h3.dion.ne.jp/~sakatsu/holiday_logic.htm 】
_/　　　へのリンクによる紹介で対応して下さい。
_/　(*3)[ktHolidayName]という関数名そのものは、各自の環境に
_/　　　おける命名規則に沿って変更しても構いません。
_/　
_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
*/

package jp.ne.dion.h3.sakatsu.addinbox;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class KtHoliday {
    private static HashMap holidayMap = new HashMap();
    
    public void dispose() {
    	holidayMap.clear();
    }
    
    //オリジナルと同じ
    private KtHoliday(){
    }

    private static final  Calendar cstImplementTheLawOfHoliday
        = new GregorianCalendar( 1948, Calendar.JULY, 20 );      // 祝日法施行
    private static final  Calendar cstAkihitoKekkon
        = new GregorianCalendar( 1959, Calendar.APRIL, 10 );     // 明仁親王の結婚の儀
    private static final  Calendar cstShowaTaiso
        = new GregorianCalendar( 1989, Calendar.FEBRUARY, 24 );  // 昭和天皇大喪の礼
    private static final  Calendar cstNorihitoKekkon
        = new GregorianCalendar( 1993, Calendar.JUNE, 9 );       // 徳仁親王の結婚の儀
    private static final  Calendar cstSokuireiseiden
        = new GregorianCalendar( 1990, Calendar.NOVEMBER, 12 );  // 即位礼正殿の儀
    private static final  Calendar cstImplementHoliday
        = new GregorianCalendar( 1973, Calendar.APRIL, 12 );     // 振替休日施行
    
    //改造部分
    public static String getHolidayName( Calendar prmDate )
    {
        Long dateID = new Long(prmDate.getTimeInMillis());
        if(holidayMap.containsKey(dateID)) {
            return holidayMap.get(dateID).toString();
        }
        
        String HolidayName_ret = "";
        Calendar MyDate = Calendar.getInstance();
        MyDate.setTime( prmDate.getTime() );
        
        // ここから
        
        String HolidayName = prvHolidayChk( MyDate );
        if ( HolidayName.equals("") ) {
            if ( MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) {
                // 月曜以外は振替休日判定不要
                // 5/6(火,水)の判定は[ prvHolidayChk ]で処理済
                // 5/6(月)はここで判定する
                if ( MyDate.after( cstImplementHoliday ) || MyDate.equals( cstImplementHoliday ) ) {
                    Calendar YesterDay = (Calendar )MyDate.clone();
                    YesterDay.add( Calendar.DATE, -1 );
                    HolidayName = prvHolidayChk( YesterDay );
                    HolidayName_ret = "";
                    if ( !HolidayName.equals("") ) {
                        HolidayName_ret = "振替休日";
                    } else {
                        HolidayName_ret = "";
                    }
                } else {
                    HolidayName_ret = "";
                }
            } else {
                HolidayName_ret = "";
            }
        } else {
            HolidayName_ret = HolidayName;
        }
        
        //ここまではオリジナルと同じ
//        if(HolidayName_ret.length() == 0) {
//            int dayOfWeek = MyDate.get(Calendar.DAY_OF_WEEK);
//            if( dayOfWeek == Calendar.SUNDAY) {
//                HolidayName_ret = "日";
//            } else if( dayOfWeek == Calendar.SATURDAY ) {
//                HolidayName_ret = "土";
//            }
//        }
        holidayMap.put(dateID,HolidayName_ret);
        return HolidayName_ret;
    }

    //===============================================================
    //ここから後ろもオリジナルと同じ

    private static String prvHolidayChk( Calendar MyDate )
    {
        int NumberOfWeek;
        String Result;
        int MyYear = MyDate.get( Calendar.YEAR );
        int MyMonth = MyDate.get( Calendar.MONTH ) + 1;    // MyMonth:1～12
        int MyDay = MyDate.get( Calendar.DATE );

        if ( MyDate.before( cstImplementTheLawOfHoliday ) ) {
        return "";   // 祝日法施行(1948/7/20 )以前
        } else;

        Result = "";
        switch ( MyMonth ) {
    // １月 //
        case 1:
            if ( MyDay == 1 ) {
                Result = "元日";
            } else {
                if ( MyYear >= 2000 ) {
                    NumberOfWeek = ( (MyDay - 1 ) / 7 ) + 1;
                    if ( ( NumberOfWeek == 2 ) && ( MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) ) {
                        Result = "成人の日";
                    } else;
                } else {
                    if ( MyDay == 15 ) {
                        Result = "成人の日";
                    } else;
                }
            }
            break;
    // ２月 //
        case 2:
            if ( MyDay == 11 ) {
                if ( MyYear >= 1967 ) {
                    Result = "建国記念の日";
                } else;
            } else {
                if ( MyDate.equals( cstShowaTaiso ) ) {
                    Result = "昭和天皇の大喪の礼";
                } else;
            }
            break;
    // ３月 //
        case 3:
            if ( MyDay == prvDayOfSpringEquinox( MyYear ) ) {    // 1948～2150以外は[99]
                Result = "春分の日";                   // が返るので､必ず≠になる
            } else;
            break;
    // ４月 //
        case 4:
            if ( MyDay == 29 ) {
                if (MyYear >= 2007) {
                    Result = "昭和の日";
                } else {
                    if ( MyYear >= 1989 ) {
                        Result = "みどりの日";
                    } else {
                        Result = "天皇誕生日";
                    }
                }
            } else {
                if ( MyDate.equals( cstAkihitoKekkon ) ) {
                    Result = "皇太子明仁親王の結婚の儀";  // ( =1959/4/10 )
                } else;
            }
            break;
    // ５月 //
        case 5:
            switch ( MyDay ) {
              case 3:  // ５月３日
                  Result = "憲法記念日";
                  break;
              case 4:  // ５月４日
                  if (MyYear >= 2007) {
                      Result = "みどりの日";
                  } else {
                      if (MyYear >= 1986) {
                          if (MyDate.get( Calendar.DAY_OF_WEEK ) > Calendar.MONDAY) {
                              // 5/4が日曜日は『只の日曜』､月曜日は『憲法記念日の振替休日』(～2006年)
                              Result = "国民の休日";
                          } else;
                      } else;
                  }
                  break;
              case 5:  // ５月５日
                  Result = "こどもの日";
                  break;
              case 6:  // ５月６日
                  if (MyYear >= 2007) {
                      if( (MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.TUESDAY)
                          || (MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.WEDNESDAY)) {
                          Result = "振替休日";    // [5/3,5/4が日曜]ケースのみ、ここで判定
                      } else;
                  } else;
                  break;
            }
            break;
    // ６月 //
        case 6:
            if ( MyDate.equals( cstNorihitoKekkon ) ) {
                Result = "皇太子徳仁親王の結婚の儀";
            } else;
            break;
    // ７月 //
        case 7:
            if ( MyYear >= 2003 ) {
                NumberOfWeek = ( (MyDay - 1 ) / 7 ) + 1;
                if ( ( NumberOfWeek == 3 ) && ( MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) ) {
                    Result = "海の日";
                } else;
            } else {
                if ( MyYear >= 1996 ) {
                    if ( MyDay == 20 ) {
                        Result = "海の日";
                    } else;
                } else;
            }
            break;
    // ９月 //
        case 9:
            //第３月曜日( 15～21 )と秋分日(22～24 )が重なる事はない
            int MyAutumnEquinox = prvDayOfAutumnEquinox( MyYear );
            if ( MyDay == MyAutumnEquinox ) {        // 1948～2150以外は[99]
                Result = "秋分の日";       // が返るので､必ず≠になる
            } else {
                if ( MyYear >= 2003 ) {
                    NumberOfWeek = ( (MyDay - 1 ) / 7 ) + 1;
                    if ( (NumberOfWeek == 3 ) && ( MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) ) {
                        Result = "敬老の日";
                    } else {
                        if ( MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.TUESDAY ) {
                            if ( MyDay == ( MyAutumnEquinox - 1 ) ) {
                                Result = "国民の休日";
                            } else;
                        } else;
                    }
                } else {
                    if ( MyYear >= 1966 ) {
                        if ( MyDay == 15 ) {
                            Result = "敬老の日";
                        } else;
                    } else;
                }
            }
            break;
    // １０月 //
        case 10:
            if ( MyYear >= 2000 ) {
                NumberOfWeek = ( ( MyDay - 1 ) / 7 ) + 1;
                if ( (NumberOfWeek == 2 ) && ( MyDate.get( Calendar.DAY_OF_WEEK ) == Calendar.MONDAY ) ) {
                    Result = "体育の日";
                } else;
            } else {
                if ( MyYear >= 1966 ) {
                    if ( MyDay == 10 ) {
                        Result = "体育の日";
                    } else;
                } else;
            }
            break;
    // １１月 //
        case 11:
            if ( MyDay == 3 ) {
                Result = "文化の日";
            } else {
                if ( MyDay == 23 ) {
                    Result = "勤労感謝の日";
                } else {
                    if ( MyDate.equals( cstSokuireiseiden ) ) {
                        Result = "即位礼正殿の儀";
                    } else;
                }
            }
            break;
    // １２月 //
        case 12:
            if ( MyDay == 23 ) {
                if ( MyYear >= 1989 ) {
                    Result = "天皇誕生日";
                } else;
            } else;
            break;
        }
        return Result;
    }

    //===================================================================
    // 春分/秋分日の略算式は
    // 『海上保安庁水路部 暦計算研究会編 新こよみ便利帳』
    // で紹介されている式です。
    private static int prvDayOfSpringEquinox( int MyYear )
    {
        int SpringEquinox_ret;
        if ( MyYear <= 1947 ) {
            SpringEquinox_ret = 99;    //祝日法施行前
        } else {
            if ( MyYear <= 1979 ) {
                SpringEquinox_ret = (int)( 20.8357 + 
                ( 0.242194 * ( MyYear - 1980 ) ) - (int)( (MyYear - 1983 ) / 4 ) );
            } else {
                if ( MyYear <= 2099 ) {
                    SpringEquinox_ret = (int)( 20.8431 + 
                    ( 0.242194 * ( MyYear - 1980 ) ) - (int)( (MyYear - 1980 ) / 4 ) );
                } else {
                    if ( MyYear <= 2150 ) {
                        SpringEquinox_ret = (int)( 21.851 + 
                        ( 0.242194 * ( MyYear - 1980 ) ) - (int)( (MyYear - 1980 ) / 4 ) );
                    } else {
                        SpringEquinox_ret = 99;    //2151年以降は略算式が無いので不明
                    }
                }
            }
        }
        return SpringEquinox_ret;
    }

    //=====================================================================
    private static int prvDayOfAutumnEquinox( int MyYear )
    {
        int AutumnEquinox_ret;
        if ( MyYear <= 1947 ) {
            AutumnEquinox_ret = 99;   //祝日法施行前
        } else {
            if ( MyYear <= 1979 ) {
                AutumnEquinox_ret = (int)( 23.2588 + 
                ( 0.242194 * ( MyYear - 1980 ) ) - (int)( (MyYear - 1983 ) / 4 ) );
            } else {
                if ( MyYear <= 2099 ) {
                    AutumnEquinox_ret = (int)( 23.2488 + 
                    ( 0.242194 * ( MyYear - 1980 ) ) - (int)( (MyYear - 1980 ) / 4 ) );
                } else {
                    if ( MyYear <= 2150 ) {
                        AutumnEquinox_ret = (int)( 24.2488 + 
                        ( 0.242194 * ( MyYear - 1980 ) ) - (int)( (MyYear - 1980 ) / 4 ) );
                    } else {
                        AutumnEquinox_ret = 99;    //2151年以降は略算式が無いので不明
                    }
                }
            }
        }
        return AutumnEquinox_ret;
    }

    //余計なコードは一部カット

}
