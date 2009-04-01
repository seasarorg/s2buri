package example.org.escafe.buri.service;

import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.framework.container.S2Container;

/**
 * サービスの抽象クラスです。
 * 
 * @author S2JDBC-Gen
 * @param <ENTITY>
 *            エンティティの型
 */
public abstract class AbstractService<ENTITY> extends S2AbstractService<ENTITY> {
	protected S2Container s2Container;

	public void setS2Container(S2Container s2Container) {
		this.s2Container = s2Container;
	}
}
