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
package org.escafe.buri.common.util.template.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

import org.escafe.buri.common.util.template.TextTemplate;
import org.escafe.buri.common.util.template.exception.TemplateRuntimeException;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.FileInputStreamUtil;
import org.seasar.framework.util.InputStreamReaderUtil;
import org.seasar.framework.util.ReaderUtil;
import org.seasar.framework.util.ResourceUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * ソースコードのテンプレートを表すクラスの実装です。
 * @author makotan
 * @author nobeans
 * @author imai78(JavaDoc)
 * @author j5ik2o
 * @since 2005/09/13
 */
public class TextTemplateFreeMakerImpl implements TextTemplate {
	/**
	 * {@code FreeMarker}の実行に必要な設定
	 */
	private Configuration configuration = new Configuration();

	/**
	 * オブジェクトを破棄します。
	 */
	public void destroy() {
		configuration = new Configuration();
	}

	/**
	 * コンストラクタです。
	 */
	public TextTemplateFreeMakerImpl() {
		// configuration.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
	}

	/**
	 * {@inheritDoc}
	 */
	public String processResource(String resourceName, Object data) {
		URL resource = ResourceUtil.getResource(resourceName);
		try {
			return processInputStream(resource.openStream(), data);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String processFile(String fileName, Object data) {
		File file = new File(fileName);
		InputStream is = FileInputStreamUtil.create(file);
		return processInputStream(is, data);
	}

	/**
	 * {@link InputStream}で指定されたテンプレートに対して、変換処理を行います。
	 * 
	 * @param is テンプレートを表す{@link InputStream}
	 * @param data テンプレートの変換処理に参照するコンテキスト
	 * @return 変換処理結果
	 */
	private String processInputStream(InputStream is, Object data) {
		InputStreamReader reader = InputStreamReaderUtil.create(is);
		String templateText = ReaderUtil.readText(reader);
		return process(templateText, data);
	}

	/**
	 * {@inheritDoc}
	 */
	public String process(String templateText, Object data) {
		Template template = null;
		try {
			template =
			    new Template(
			        "name",
			        new StringReader(templateText),
			        configuration);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		// 変換結果を受け取るクラスにStringWriterを使用する
		StringWriter stringWriter = new StringWriter();
		try {
			// テンプレートの変換処理
			template.process(data, stringWriter);
		} catch (TemplateException e) {
			throw new TemplateRuntimeException(e);
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}
		stringWriter.flush();
		String result = stringWriter.toString();
		return result;
	}

	/**
	 * 登録されている{@code FreeMarker}の実行に必要な設定を返します。
	 * 
	 * @return {@code FreeMarker}の実行に必要な設定
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * {@code FreeMarker}の実行に必要な設定を登録します。
	 * 
	 * @param configuration {@code FreeMarker}の実行に必要な設定
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
