/**
 * ぶりの機能を使う為のAPIセットのインターフェイスを持つパッケージです。
 * <p>
 * このパッケージにあるインターフェイスは、ぶりのエンジンをWrapしたAPIとして提供されています。
 * {@code Bao}を用いない場合、
 * ぶりのエンジンである{@link BuriSimpleEngineImpl}または
 * {@link BuriStandardEngineImpl}を直接操作しない場合は、
 * このパッケージのAPIセットを使用してぶりを活用することとなります。
 * </p>
 * @author imai78(JavaDoc)
 */
package org.escafe.buri.engine.processor;

import org.escafe.buri.engine.impl.BuriSimpleEngineImpl;
import org.escafe.buri.engine.impl.BuriStandardEngineImpl;
