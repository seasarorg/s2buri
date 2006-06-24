/*
 * �쐬��: 2005/11/29
 *
 */
package example.org.seasar.buri.test;

import java.util.Date;
import java.util.List;

import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.extension.unit.S2TestCase;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.impl.ComponentDefImpl;

import example.org.seasar.buri.bao.BillBao;
import example.org.seasar.buri.bao.OrderBao;
import example.org.seasar.buri.bao.ShippingBao;
import example.org.seasar.buri.bao.ShippingItemBao;
import example.org.seasar.buri.dao.CustomerDao;
import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dto.BillDto;
import example.org.seasar.buri.dto.CustomerDto;
import example.org.seasar.buri.dto.ItemDto;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingItemDto;

public class BaoTest extends S2TestCase {
    private ItemDao itemDao_;
    private CustomerDao customerDao_;
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao_;
    
    private BillBao billBao_;
    private OrderBao orderBao_;
    private ShippingBao shippingBao_;
    private ShippingItemBao shippingItemBao_;
    private BuriEngine buriEngine_;

    private CustomerDto �q1;
    private CustomerDto �q2;
    
    private ItemDto ���i1;
    private ItemDto ���i2;
    private ItemDto ���i3;
    private ItemDto ���i4;
    private ItemDto ���i5;
    
    public BaoTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include("baotest.dicon");
    }
    
    public void testNomalOrderTx() {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");

        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();
        
        orderBao_.order(orderInfoDto1);
        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = shippingBao_.getNowWaiting();
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = shippingItemBao_.getItemWaiting();
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        

        shippingItemBao_.endShipping(shippingItemDto);
        
        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),1);

        datas = shippingItemBao_.getItemWaiting();
        System.out.println(datas);
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        datas = shippingItemBao_.getEndShipping();
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = shippingBao_.getNowWaiting();
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = shippingBao_.getEndShipping();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
        
        shippingItemBao_.endShipping(shippingItemDto);
        
        
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        datas = shippingItemBao_.getItemWaiting();
        assertEquals(datas.size(),0);
        
        datas = shippingItemBao_.getEndShipping();
        assertEquals(datas.size(),2);
        
        datas = shippingBao_.getNowWaiting();
        assertEquals(datas.size(),0);
        
        datas = shippingBao_.getEndShipping();
        assertEquals(datas.size(),1);

        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),0);

        datas = orderBao_.getEndShipping();
        assertEquals(datas.size(),1);

        datas = billBao_.getBillWaiting();
        assertEquals(datas.size(),1);
        BillDto billDto = (BillDto)datas.get(0);

        datas = billBao_.getReBill();
        assertEquals(datas.size(),0);
        
        datas = billBao_.getEndBill();
        assertEquals(datas.size(),0);
        
        
        billBao_.bill(billDto);

        datas = billBao_.getBillWaiting();
        assertEquals(datas.size(),0);

        datas = billBao_.getReBill();
        assertEquals(datas.size(),1);
        billDto = (BillDto)datas.get(0);

        datas = billBao_.getEndBill();
        assertEquals(datas.size(),0);

        
        billBao_.checkPayment(billDto);

        datas = orderBao_.getEndShipping();
        assertEquals(datas.size(),0);

        datas = orderBao_.getOrderEnd();
        assertEquals(datas.size(),1);

        datas = billBao_.getBillWaiting();
        assertEquals(datas.size(),0);

        datas = billBao_.getEndBill();
        assertEquals(datas.size(),1);
        
    }
    
    public void testCancelSuccessOrderTx() {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");

        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        orderBao_.order(orderInfoDto1);

        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = shippingBao_.getNowWaiting();
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = shippingItemBao_.getItemWaiting();
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        Object status = orderBao_.cancel(orderInfoDto1.getOrderTitleID());
        assertEquals(status.toString(),"success");
        
        datas = orderBao_.getOrderCancelEnd();
        assertEquals(datas.size(),1);
        
        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),0);
        
        datas = orderBao_.getEndShipping();
        assertEquals(datas.size(),0);

        datas = shippingItemBao_.getCancel();
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        datas = shippingItemBao_.getItemWaiting();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = shippingItemBao_.getEndShipping();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = shippingBao_.getShippingCancel();
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = shippingBao_.getNowWaiting();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = shippingBao_.getEndShipping();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
    }    
    
    public void testInvokeExceptionOrderTx() {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");

        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        orderBao_.order(orderInfoDto1);
        
        try{
            orderBao_.order(orderInfoDto1);
            fail();
        } catch(Exception ex) {
            
        }

        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = shippingBao_.getNowWaiting();
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = shippingItemBao_.getItemWaiting();
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        Object status = orderBao_.cancel(orderInfoDto1.getOrderTitleID());
        assertEquals(status.toString(),"success");
        
        datas = orderBao_.getOrderCancelEnd();
        assertEquals(datas.size(),1);
        
        datas = orderBao_.getUnderWork();
        assertEquals(datas.size(),0);
        
        datas = orderBao_.getEndShipping();
        assertEquals(datas.size(),0);

        datas = shippingItemBao_.getCancel();
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        datas = shippingItemBao_.getItemWaiting();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = shippingItemBao_.getEndShipping();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = shippingBao_.getShippingCancel();
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = shippingBao_.getNowWaiting();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = shippingBao_.getEndShipping();
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
    }    
    
    protected OrderInfoDto orderSetup1() {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setCustomerID(�q1.getCustomerID());
        dto.setOrderDate(new Date());
        dto.setStatus(new Integer(0));
        
        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setItemID(���i2.getItemID());
        detailDto.setOrderCount(1);
        dto.getOrderDetail().add(detailDto);
        
        detailDto = new OrderDetailDto();
        detailDto.setItemID(���i3.getItemID());
        detailDto.setOrderCount(2);
        dto.getOrderDetail().add(detailDto);
        return dto;
    }
    
    protected OrderInfoDto orderSetup2() {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setCustomerID(�q1.getCustomerID());
        dto.setOrderDate(new Date());
        dto.setStatus(new Integer(0));

        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setItemID(���i4.getItemID());
        detailDto.setOrderCount(2);
        dto.getOrderDetail().add(detailDto);
        
        detailDto = new OrderDetailDto();
        detailDto.setItemID(���i5.getItemID());
        detailDto.setOrderCount(1);
        dto.getOrderDetail().add(detailDto);
        return dto;
    }
    
    protected OrderInfoDto orderSetup3() {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setCustomerID(�q2.getCustomerID());
        dto.setOrderDate(new Date());
        dto.setStatus(new Integer(0));

        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setItemID(���i1.getItemID());
        detailDto.setOrderCount(5);
        dto.getOrderDetail().add(detailDto);
        return dto;
    }
    
    protected void itemSetup() {
        ���i1 = new ItemDto();
        ���i1.setItemName("PS1");
        ���i1.setPrice(19800);
        itemDao_.insert(���i1);
        ���i2 = new ItemDto();
        ���i2.setItemName("PS2");
        ���i2.setPrice(19800);
        itemDao_.insert(���i2);
        ���i3 = new ItemDto();
        ���i3.setItemName("PS3");
        ���i3.setPrice(19800);
        itemDao_.insert(���i3);
        ���i4 = new ItemDto();
        ���i4.setItemName("PS4");
        ���i4.setPrice(19800);
        itemDao_.insert(���i4);
        ���i5 = new ItemDto();
        ���i5.setItemName("PS5");
        ���i5.setPrice(19800);
        itemDao_.insert(���i5);
    }
    protected void customerSetup() {
        �q1 = new CustomerDto();
        �q1.setCustomerName("�q�P����");
        customerDao_.insert(�q1);
        �q2 = new CustomerDto();
        �q2.setCustomerName("�q�Q����");
        customerDao_.insert(�q2);
    }
    
}
