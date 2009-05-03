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
package org.escafe.buri.common.util.template.exception;

import org.escafe.buri.exception.BuriException;

import freemarker.template.TemplateException;

/**
 * テンプレートの変換処理に失敗した場合に使用する例外クラスです。
 * 
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2005/09/14
 */
public class TemplateRuntimeException extends BuriException {
	/**
	 * コンストラクタです。
	 * 
	 * @param cause {@link TemplateException}オブジェクト
	 */
	public TemplateRuntimeException(TemplateException cause) {
		super(null, null, cause);
	}

	/** */
	private static final long serialVersionUID = -4556782442510118126L;
}
