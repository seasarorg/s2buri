/*
 * 作成日: 2006/06/28
 *
 */
package org.escafe.buri.engine.service.impl;

import java.util.List;

import jp.starlogic.servicemanager.abst.AbstractGetRunService;

import org.escafe.buri.engine.processor.util.BuriTimerInvoker;
import org.escafe.buri.entity.BuriPathDataEntity;
import org.escafe.buri.service.BuriPathDataEntityService;

/**
 * タイマーによる制御を行うクラスです。
 * <p>
 * 指定されたタイマーに応じて{@link BuriTimerInvoker}を実行する為のクラスです。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @since 2006/06/28
 */
public class BuriTimerService extends AbstractGetRunService {
	/**
	 * {@link BuriTimerInvoker}
	 */
	private BuriTimerInvoker timerInvoker;

	/**
	 * ぶり固有のビュー{@code BuriPathDataEntityService}のService
	 */
	private BuriPathDataEntityService buriPathDataEntityService;

	/*
	 * @see jp.starlogic.servicemanager.OneService#canService()
	 */
	public boolean canService() {
		return true;
	}

	/*
	 * @see jp.starlogic.servicemanager.OneService#execute()
	 */
	public void execute() {
		List<BuriPathDataEntity> timerOverList =
		    buriPathDataEntityService.getTimeOrverState();
		for (BuriPathDataEntity callDto : timerOverList) {
			timerInvoker.invoke(callDto);
		}
	}

	/**
	 * ぶり固有のビュー{@code BuriPathDataEntity}のServiceを返します。
	 * 
	 * @return ぶり固有のビュー{@code BuriPathDataEntity}のService
	 */
	public BuriPathDataEntityService getBuriPathDataEntityService() {
		return buriPathDataEntityService;
	}

	/**
	 * ぶり固有のビュー{@code BuriPathDataEntity}のServiceを登録します。
	 * 
	 * @param dataDao
	 *            ぶり固有のビュー{@code BuriPathDataEntity}のService
	 */
	public void setBuriPathDataEntityService(
	        BuriPathDataEntityService buriPathDataEntityService) {
		this.buriPathDataEntityService = buriPathDataEntityService;
	}

	/**
	 * {@link BuriTimerInvoker}を返します。
	 * 
	 * @return {@link BuriTimerInvoker}
	 */
	public BuriTimerInvoker getTimerInvoker() {
		return timerInvoker;
	}

	/**
	 * {@link BuriTimerInvoker}を登録します。
	 * 
	 * @param timerInvoker
	 *            {@link BuriTimerInvoker}
	 */
	public void setTimerInvoker(BuriTimerInvoker timerInvoker) {
		this.timerInvoker = timerInvoker;
	}
}
