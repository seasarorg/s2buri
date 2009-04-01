/*
 * 作成日: 2006/06/14
 *
 */
package org.escafe.buri.engine.processor.impl;

import java.util.ArrayList;
import java.util.List;

import org.escafe.buri.dao.BuriTestUserDao;
import org.escafe.buri.dao.util.BuriUserUtil;
import org.escafe.buri.dto.BuriTestUserDto;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.IdentityInfo;
import org.escafe.buri.engine.ParticipantProvider;
import org.escafe.buri.engine.processor.StandardBuriProcessor;
import org.escafe.buri.exception.select.BuriNotSelectedActivityException;
import org.seasar.extension.unit.S2TestCase;

import example.org.escafe.buri.dao.ItemDao;
import example.org.escafe.buri.dto.ItemDto;
import example.org.escafe.buri.dto.ItemFindDto;

/**
 * {@link StandardBuriProcessor}のテスト。
 * <p>
 * <code>wakanagoxpdl/stdTest.xpdl</code>の<code>test01</code>プロセスを対象とする。
 * </p>
 * @author $Author$
 */
public class StandardBuriProcessor01Test extends S2TestCase {

    private static final String PATH = "buri/dicon/buriStandard.dicon";

    private BuriEngine buriEngine;
    private StandardBuriProcessor processor;
    private BuriTestUserDao userDao;
    private ItemDao itemDao;
    private ParticipantProvider participantProvider;
    private BuriUserUtil buriUserUtil;

    public StandardBuriProcessor01Test(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        include(PATH);
    }

    public void test01Tx() {
        readXlsWriteDb("StdTestData.xls");
        participantProvider = (ParticipantProvider) getComponent("StdTestParticipantProvider");
        buriEngine.readWorkFlowFromResource("wakanagoxpdl/stdTest.xpdl", "stdTest",
            participantProvider);

        BuriTestUserDto 無関係ユーザ = null;
        BuriTestUserDto 下っ端ユーザ = userDao.getBuriTestUser(3);
        BuriTestUserDto 真ん中ユーザ = userDao.getBuriTestUser(2);
        BuriTestUserDto 一番上ユーザ = userDao.getBuriTestUser(1);

        ItemDto 安い商品 = itemDao.getItem(1);
        ItemDto やや高い商品 = itemDao.getItem(2);
        ItemDto とても高い商品 = itemDao.getItem(3);

        processor.toNextStatus("stdTest.Test01", 安い商品, 無関係ユーザ);

        List dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());

        { // 検索をしてみる。
            ItemFindDto findDto = new ItemFindDto();
            findDto.setItemName_matchFront("PS");

            List<String> pathList = new ArrayList<String>();
            pathList.add("stdTest.Test01.受付済み");

            List<Long> userList = new ArrayList<Long>();
            IdentityInfo userId = participantProvider.getUserId(下っ端ユーザ);
            userList.add(new Long(buriUserUtil.convertBuriUserId(userId)));
            dataList = itemDao.findAndUser(findDto, pathList, userList);
            assertEquals(1, dataList.size());

            userList.clear();
            userId = participantProvider.getUserId(真ん中ユーザ);
            userList.add(new Long(buriUserUtil.convertBuriUserId(userId)));
            dataList = itemDao.findAndUser(findDto, pathList, userList);
            assertEquals(0, dataList.size());
        }

        processor.toNextStatus("stdTest.Test01", やや高い商品, 無関係ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());

        processor.toNextStatus("stdTest.Test01", とても高い商品, 無関係ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());

        processor.toNextStatus("stdTest.Test01.受付済み", 安い商品, 下っ端ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());

        processor.toNextStatus("stdTest.Test01.受付済み", やや高い商品, 真ん中ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());

        processor.toNextStatus("stdTest.Test01.受付済み", とても高い商品, 一番上ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());

        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.受付済み", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());

        processor.toNextStatus("stdTest.Test01.処理中", 安い商品, 下っ端ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());

        processor.toNextStatus("stdTest.Test01.処理中", やや高い商品, 真ん中ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(2, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());

        processor.toNextStatus("stdTest.Test01.処理中", とても高い商品, 一番上ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(3, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 真ん中ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.処理中", 一番上ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(0, dataList.size());

        processor.toNextStatus("stdTest.Test01.返信待ち", 安い商品, 下っ端ユーザ);

        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(2, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.終了", 無関係ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());

        // 返信待ちを進められるのは下っ端ユーザだけ
        try {
            processor.toNextStatus("stdTest.Test01.返信待ち", とても高い商品, 一番上ユーザ);
            fail();
        } catch (BuriNotSelectedActivityException e) {
        }
        dataList = processor.getDataListFromPath("stdTest.Test01.返信待ち", 下っ端ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(2, dataList.size());
        dataList = processor.getDataListFromPath("stdTest.Test01.終了", 無関係ユーザ, ItemDto.class);
        System.out.println(dataList);
        assertEquals(1, dataList.size());
    }

}
