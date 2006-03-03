/*
 * 作成日: 2005/11/29
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
    
    private CustomerDto 客1;
    private CustomerDto 客2;
    
    private ItemDto 商品1;
    private ItemDto 商品2;
    private ItemDto 商品3;
    private ItemDto 商品4;
    private ItemDto 商品5;
    
    public BuriTest(String arg0) {
        super(arg0);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        include("buritest.dicon");
        buriInvoker = (SimpleBuriInvoker)getComponent("SimpleInvoker");
    }
    
    public void testNomalOrderTx() {
        // 本当はburi2.diconに書くもの
        buriEngine_.getWorkflows().readWorkFlowFromResource("注文管理","orderBao.xpdl");

        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("注文管理.注文",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        

        buriInvoker.invoke("注文管理.出荷詳細",shippingItemDto);
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備完了");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷済み");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
        
        buriInvoker.invoke("注文管理.出荷詳細",shippingItemDto);
        
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備完了");
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷済み");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷終了");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),1);
        BillDto billDto = (BillDto)datas.get(0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.再請求準備");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求終了");
        assertEquals(datas.size(),0);
        
        
        buriInvoker.invoke("注文管理.請求",billDto);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.再請求準備");
        assertEquals(datas.size(),1);
        billDto = (BillDto)datas.get(0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求終了");
        assertEquals(datas.size(),0);

        
        buriInvoker.invoke("注文管理.請求",billDto);

        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷終了");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.注文.終了");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求終了");
        assertEquals(datas.size(),1);
        
    }
    
    public void testCancelSuccessOrderTx() {
        // 本当はburi2.diconに書くもの
        buriEngine_.getWorkflows().readWorkFlowFromResource("注文管理","orderBao.xpdl");
        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("注文管理.注文",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),2);
        System.out.println(datas);
        

        Object status = buriInvoker.invoke("注文管理.注文",orderInfoDto1,"cancel","#cancelStatus");
        assertEquals(status.toString(),"success");
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.キャンセル終了");
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷終了");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.cancel済み");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備完了");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.キャンセル済み");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷準備中");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷済み");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
    }
    public void testCancelNGOrderTx() {
        // 本当はburi2.diconに書くもの
        buriEngine_.getWorkflows().readWorkFlowFromResource("注文管理","orderBao.xpdl");
        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("注文管理.注文",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        assertEquals(datas.size(),1);
        System.out.println(datas);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),2);
        System.out.println(datas);
        
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        

        buriInvoker.invoke("注文管理.出荷詳細",shippingItemDto);
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備完了");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷済み");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        
        
        buriInvoker.invoke("注文管理.出荷詳細",shippingItemDto);
        
        
        pathDao_.getAllBuriPath();
        stateDao_.getAllBuriState();
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備完了");
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.商品待ち");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷済み");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷終了");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求終了");
        assertEquals(datas.size(),0);
        


        Object status = buriInvoker.invoke("注文管理.注文",orderInfoDto1,"cancel","#cancelStatus");
        assertEquals(status.toString(),"NG");
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.キャンセル終了");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷作業中");
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.注文.出荷終了");
        assertEquals(datas.size(),1);

        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.cancel済み");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備完了");
        System.out.println(datas);
        assertEquals(datas.size(),2);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.キャンセル済み");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷準備中");
        System.out.println(datas);
        assertEquals(datas.size(),0);
        
        datas = buriInvoker.getDataListFromPath("注文管理.出荷.出荷済み");
        System.out.println(datas);
        assertEquals(datas.size(),1);
        
    }
    
    public void testTimeoutOrderTx() throws Exception {
        // 本当はburi2.diconに書くもの
        buriEngine_.getWorkflows().readWorkFlowFromResource("注文管理","orderBao.xpdl");
        customerSetup();
        itemSetup();
        List datas = null;
        OrderInfoDto orderInfoDto1 = orderSetup1();

        buriInvoker.invoke("注文管理.注文",orderInfoDto1);
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),2);
        ShippingItemDto shippingItemDto = (ShippingItemDto)datas.get(0);
        
        buriInvoker.invoke("注文管理.出荷詳細",shippingItemDto);
        datas = buriInvoker.getDataListFromPath("注文管理.出荷詳細.商品準備中");
        assertEquals(datas.size(),1);
        shippingItemDto = (ShippingItemDto)datas.get(0);
        
        buriInvoker.invoke("注文管理.出荷詳細",shippingItemDto);
        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),1);
        BillDto billDto = (BillDto)datas.get(0);
        
        buriInvoker.invoke("注文管理.請求",billDto);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.再請求準備");
        assertEquals(datas.size(),1);
        billDto = (BillDto)datas.get(0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求終了");
        assertEquals(datas.size(),0);
        
        stateDao_.getAllBuriState();
        Thread.sleep(10*1000);
        /*
         * 本当は別Threadで動作するサービスなんだけど
         * 別Threadにするとトランザクション制御でThreadが固まるので
         * ここで直接呼びだし(^^;
         * 本当は、こんな呼び出しは書かなくてOK！！
         */ 
        autoInvokeService_.execute();

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求作業");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.再請求準備");
        assertEquals(datas.size(),0);

        datas = buriInvoker.getDataListFromPath("注文管理.請求.請求終了");
        assertEquals(datas.size(),1);
        
    }
    
    
    
    protected OrderInfoDto orderSetup1() {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setCustomerID(客1.getCustomerID());
        dto.setOrderDate(new Date());
        dto.setStatus(new Integer(0));
        
        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setItemID(商品2.getItemID());
        detailDto.setOrderCount(1);
        dto.getOrderDetail().add(detailDto);
        
        detailDto = new OrderDetailDto();
        detailDto.setItemID(商品3.getItemID());
        detailDto.setOrderCount(2);
        dto.getOrderDetail().add(detailDto);
        return dto;
    }
    
    protected OrderInfoDto orderSetup2() {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setCustomerID(客1.getCustomerID());
        dto.setOrderDate(new Date());
        dto.setStatus(new Integer(0));

        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setItemID(商品4.getItemID());
        detailDto.setOrderCount(2);
        dto.getOrderDetail().add(detailDto);
        
        detailDto = new OrderDetailDto();
        detailDto.setItemID(商品5.getItemID());
        detailDto.setOrderCount(1);
        dto.getOrderDetail().add(detailDto);
        return dto;
    }
    
    protected OrderInfoDto orderSetup3() {
        OrderInfoDto dto = new OrderInfoDto();
        dto.setCustomerID(客2.getCustomerID());
        dto.setOrderDate(new Date());
        dto.setStatus(new Integer(0));

        OrderDetailDto detailDto = new OrderDetailDto();
        detailDto.setItemID(商品1.getItemID());
        detailDto.setOrderCount(5);
        dto.getOrderDetail().add(detailDto);
        return dto;
    }
    
    protected void itemSetup() {
        商品1 = new ItemDto();
        商品1.setItemName("PS1");
        商品1.setPrice(19800);
        itemDao_.insert(商品1);
        商品2 = new ItemDto();
        商品2.setItemName("PS2");
        商品2.setPrice(19800);
        itemDao_.insert(商品2);
        商品3 = new ItemDto();
        商品3.setItemName("PS3");
        商品3.setPrice(19800);
        itemDao_.insert(商品3);
        商品4 = new ItemDto();
        商品4.setItemName("PS4");
        商品4.setPrice(19800);
        itemDao_.insert(商品4);
        商品5 = new ItemDto();
        商品5.setItemName("PS5");
        商品5.setPrice(19800);
        itemDao_.insert(商品5);
    }
    protected void customerSetup() {
        客1 = new CustomerDto();
        客1.setCustomerName("客１だよ");
        customerDao_.insert(客1);
        客2 = new CustomerDto();
        客2.setCustomerName("客２じゃ");
        customerDao_.insert(客2);
    }
    
}
