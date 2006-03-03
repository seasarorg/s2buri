/*
 * �쐬��: 2005/11/29
 *
 */
package example.org.seasar.buri.test;

import java.util.Date;
import java.util.List;

import jp.starlogic.servicemanager.service.BuriAutoInvokeService;

import org.seasar.buri.dao.BuriPathDao;
import org.seasar.buri.dao.BuriStateDao;
import org.seasar.buri.engine.BuriEngine;
import org.seasar.buri.engine.invoker.SimpleBuriInvoker;
import org.seasar.extension.unit.S2TestCase;

import example.org.seasar.buri.dao.BillDao;
import example.org.seasar.buri.dao.CustomerDao;
import example.org.seasar.buri.dao.ItemDao;
import example.org.seasar.buri.dao.OrderDetailDao;
import example.org.seasar.buri.dao.OrderTitleDao;
import example.org.seasar.buri.dao.ShippingDao;
import example.org.seasar.buri.dao.ShippingItemDao;
import example.org.seasar.buri.dao.util.OrderInfoDao;
import example.org.seasar.buri.dao.util.ShippingSetDao;
import example.org.seasar.buri.dto.BillDto;
import example.org.seasar.buri.dto.CustomerDto;
import example.org.seasar.buri.dto.ItemDto;
import example.org.seasar.buri.dto.OrderDetailDto;
import example.org.seasar.buri.dto.OrderInfoDto;
import example.org.seasar.buri.dto.ShippingItemDto;

public class BuriTest extends S2TestCase {
    private ShippingDao shippingDao_;
    private ShippingItemDao shippingItemDao_;
    private OrderDetailDao orderDetailDao_;
    private OrderTitleDao orderTitleDao_;
    private ItemDao itemDao_;
    private CustomerDao customerDao_;
    private BillDao billDao_;
    private OrderInfoDao orderInfoDao_;
    private ShippingSetDao shippingSetDao_;
    private BuriStateDao stateDao_;
    private BuriPathDao pathDao_;
    private BuriAutoInvokeService autoInvokeService_;

    private BuriEngine buriEngine_;
    
    private SimpleBuriInvoker buriInvoker;
    
    private CustomerDto �q1;
    private CustomerDto �q2;
    
    private ItemDto ���i1;
    private ItemDto ���i2;
    private ItemDto ���i3;
    private ItemDto ���i4;
    private ItemDto ���i5;
    
    public BuriTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include("buritest.dicon");
        buriInvoker = (SimpleBuriInvoker)getComponent("SimpleInvoker");
    }
    
    public void testNomalOrderTx() {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");

        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("�����Ǘ�.����",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        

        buriInvoker.invoke("�����Ǘ�.�o�׏ڍ�",shippingItemDto);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i��������");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׍ς�");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
        
        buriInvoker.invoke("�����Ǘ�.�o�׏ڍ�",shippingItemDto);
        
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i��������");
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׍ς�");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׏I��");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),1);
        BillDto billDto = (BillDto)datas.get(0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�Đ�������");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�����I��");
        assertEquals(datas.size(),0);
        
        
        buriInvoker.invoke("�����Ǘ�.����",billDto);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�Đ�������");
        assertEquals(datas.size(),1);
        billDto = (BillDto)datas.get(0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�����I��");
        assertEquals(datas.size(),0);

        
        buriInvoker.invoke("�����Ǘ�.����",billDto);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׏I��");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�I��");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�����I��");
        assertEquals(datas.size(),1);
        
    }
    
    public void testCancelSuccessOrderTx() {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");
        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("�����Ǘ�.����",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),2);
        System.out.println(datas);
        

        Object status = buriInvoker.invoke("�����Ǘ�.����",orderInfoDto1,"cancel","#cancelStatus");
        assertEquals(status.toString(),"success");
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�L�����Z���I��");
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׏I��");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.cancel�ς�");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i��������");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�L�����Z���ς�");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׏�����");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׍ς�");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
    }
    public void testCancelNGOrderTx() {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");
        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("�����Ǘ�.����",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        

        buriInvoker.invoke("�����Ǘ�.�o�׏ڍ�",shippingItemDto);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i��������");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׍ς�");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
        
        buriInvoker.invoke("�����Ǘ�.�o�׏ڍ�",shippingItemDto);
        
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i��������");
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.���i�҂�");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׍ς�");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׏I��");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�����I��");
        assertEquals(datas.size(),0);
        


        Object status = buriInvoker.invoke("�����Ǘ�.����",orderInfoDto1,"cancel","#cancelStatus");
        assertEquals(status.toString(),"NG");
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�L�����Z���I��");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׍�ƒ�");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�o�׏I��");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.cancel�ς�");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i��������");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�L�����Z���ς�");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׏�����");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o��.�o�׍ς�");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
    }
    
    public void testTimeoutOrderTx() throws Exception {
        // �{����buri2.dicon�ɏ�������
        buriEngine_.getWorkflows().readWorkFlowFromResource("�����Ǘ�","orderBao.xpdl");
        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("�����Ǘ�.����",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),2);
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        
        buriInvoker.invoke("�����Ǘ�.�o�׏ڍ�",shippingItemDto);
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.�o�׏ڍ�.���i������");
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        buriInvoker.invoke("�����Ǘ�.�o�׏ڍ�",shippingItemDto);
        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),1);
        BillDto billDto = (BillDto)datas.get(0);
        
        buriInvoker.invoke("�����Ǘ�.����",billDto);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�Đ�������");
        assertEquals(datas.size(),1);
        billDto = (BillDto)datas.get(0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�����I��");
        assertEquals(datas.size(),0);
        
        stateDao_.getAllBuriState();
        Thread.sleep(10*1000);
        /*
         * �{���͕�Thread�œ��삷��T�[�r�X�Ȃ񂾂���
         * ��Thread�ɂ���ƃg�����U�N�V���������Thread���ł܂�̂�
         * �����Œ��ڌĂт���(^^;
         * �{���́A����ȌĂяo���͏����Ȃ���OK�I�I
         */ 
        autoInvokeService_.execute();

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�������");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�Đ�������");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("�����Ǘ�.����.�����I��");
        assertEquals(datas.size(),1);
        
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
