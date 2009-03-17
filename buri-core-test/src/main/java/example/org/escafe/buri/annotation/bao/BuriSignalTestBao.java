package example.org.escafe.buri.annotation.bao;

import java.util.List;

import org.escafe.buri.annotation.Buri;
import org.escafe.buri.annotation.BuriActivity;
import org.escafe.buri.entity.BuriTestINT;

@Buri(process = "ぶりシグナルテスト.シグナルテスト", dtoClass = BuriTestINT.class)
public interface BuriSignalTestBao {
	@BuriActivity("箱1")
	public void start(BuriTestINT dto);

	@BuriActivity("箱2")
	public void startBox2(BuriTestINT dto);

	@BuriActivity("箱3")
	public void startBox3(BuriTestINT dto);

	@BuriActivity("箱1")
	public List<BuriTestINT> getBox1();

	@BuriActivity("箱2")
	public List<BuriTestINT> getBox2();

	@BuriActivity("箱3")
	public List<BuriTestINT> getBox3();
}
