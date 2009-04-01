/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.escafe.buri.engine.processor.util;

import org.escafe.buri.engine.service.impl.BuriTimerService;
import org.escafe.buri.entity.BuriPathDataEntity;

/**
 * 指定時刻になった時点でエンジンを起動する為のAPIです。
 * <p>
 * このAPIは{@link BuriTimerService}から実行されます。
 * </p>
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 */
public interface BuriTimerInvoker {
	/**
	 * {@link BuriPathDataEntity}に基づいてぶりのエンジンを実行します。
	 * 
	 * @param callDto
	 *            実行対象の{@code BuriPathDataUser}のDto
	 */
	void invoke(BuriPathDataEntity callDto);
}
