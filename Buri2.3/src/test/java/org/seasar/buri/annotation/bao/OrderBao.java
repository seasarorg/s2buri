/*
 * 作成日: 2006/01/03
 *
 */
package org.seasar.buri.annotation.bao;

import java.util.List;

import org.seasar.buri.annotation.Buri;
import org.seasar.buri.annotation.BuriAction;
import org.seasar.buri.annotation.BuriActivity;
import org.seasar.buri.annotation.BuriConversionRule;
import org.seasar.buri.annotation.BuriConverter;
import org.seasar.buri.annotation.BuriResult;

import example.org.seasar.buri.dto.OrderInfoDto;

@Buri(process = "注文管理.注文", dtoClass = OrderInfoDto.class)
@BuriConverter( { @BuriConversionRule(convertClass = Long.class, ognl = "OrderInfoDao.getOrderInfo(#data)") })
public interface OrderBao {

    @BuriActivity("出荷作業中")
    public List getUnderWork();

    @BuriActivity("出荷終了")
    public List getEndShipping();

    @BuriActivity("終了")
    public List getOrderEnd();

    @BuriActivity("キャンセル終了")
    public List getOrderCancelEnd();

    @BuriActivity("注文")
    public void order(OrderInfoDto dto);

    @BuriActivity("出荷作業中")
    public void endShipping(long orderID);

    @BuriActivity("出荷終了")
    public void endBill(long orderID);

    @BuriActivity( { "出荷作業中", "出荷終了" })
    @BuriAction("cancel")
    @BuriResult("#cancelStatus")
    public String cancel(long orderID);

}
