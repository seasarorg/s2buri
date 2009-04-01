package example.org.escafe.buri.signal.test;

import java.util.List;

import org.escafe.buri.annotation.bao.BuriSignalTestBao;
import org.escafe.buri.dto.BuriTestINTDto;
import org.escafe.buri.engine.BuriEngine;
import org.escafe.buri.engine.processor.util.BuriSignal;
import org.seasar.extension.unit.S2TestCase;

public class BuriSignalTest extends S2TestCase {

	String diconPath = "example/org/escafe/buri/signal/test/buriSignalTest.dicon";
	String workFlowName = "wakanagoxpdl/buriSignalTest.xpdl";
	String resourceName = "ぶりシグナルテスト";

	private BuriSignal buriSignal;
	private BuriSignalTestBao buriSignalTestBao;

	@Override
	protected void setUp() throws Exception {
		include(diconPath);
		BuriEngine buriEngine = (BuriEngine) getComponent("BuriSimpleEngine");
		buriEngine.readWorkFlowFromResource(workFlowName, resourceName);
	}

	/**
	 * シグナル単発実行テスト
	 * 
	 * @throws Exception
	 */
	public void testSignalTx() throws Exception {
		BuriTestINTDto testDto = new BuriTestINTDto();
		testDto.setValue("データ1");

		List<BuriTestINTDto> result = null;

		buriSignalTestBao.start(testDto);
		result = buriSignalTestBao.getBox1();
		assertEquals(0, result.size());
		result = buriSignalTestBao.getBox2();
		assertEquals(1, result.size());

		buriSignal.signal("ぶりシグナルテスト.シグナルテスト.箱2", testDto);
		result = buriSignalTestBao.getBox2();
		assertEquals(0, result.size());
		result = buriSignalTestBao.getBox3();
		assertEquals(1, result.size());

	}

	/**
	 * シグナル複数実行テスト
	 * 
	 * @throws Exception
	 */
	public void testSignalManyTx() throws Exception {
		BuriTestINTDto[] dtos = new BuriTestINTDto[10];

		BuriTestINTDto testDto = null;
		for (int i = 0; i < 10; i++) {
			testDto = new BuriTestINTDto();
			testDto.setValue("データ1" + new java.util.Date().toString());
			buriSignalTestBao.start(testDto);
			dtos[i] = testDto;
		}

		List<BuriTestINTDto> result = null;
		result = buriSignalTestBao.getBox1();
		assertEquals(0, result.size());
		result = buriSignalTestBao.getBox2();
		assertEquals(10, result.size());

		for (int i = 0; i < 10; i++) {
			testDto = dtos[i];
			buriSignal.signal("ぶりシグナルテスト.シグナルテスト.箱2", testDto);
		}
		result = buriSignalTestBao.getBox2();
		assertEquals(0, result.size());
		result = buriSignalTestBao.getBox3();
		assertEquals(10, result.size());

	}
}
