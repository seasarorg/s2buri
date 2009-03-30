package org.escafe.buri.s2dao.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.dto.BuriTestINTDto;

@Buri(process = "ぶりシグナルテスト.シグナルテスト", dtoClass = BuriTestINTDto.class)
public interface BuriSignalTestBao {

	@BuriActivity("箱1")
	public void start(BuriTestINTDto dto);

	@BuriActivity("箱2")
	public void startBox2(BuriTestINTDto dto);

	@BuriActivity("箱3")
	public void startBox3(BuriTestINTDto dto);

	@BuriActivity("箱1")
	public List<BuriTestINTDto> getBox1();

	@BuriActivity("箱2")
	public List<BuriTestINTDto> getBox2();

	@BuriActivity("箱3")
	public List<BuriTestINTDto> getBox3();

}
