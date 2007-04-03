使い方

(1)s2buri-tigerをクラスパスに追加する。

(2)Tigerアノテーションまたは定数アノテーションを使用してBaoを作成する。

(3)bao.diconを以下のように変更する。

<!--
	<component class="org.seasar.buri.bao.impl.rBaoMetadataFactoryImpl">
	</component>
-->

<!-- Tigerアノテーション用BaoMetaDataFactory -->
	<component class="org.seasar.buri.bao.impl.TigerBaoMetadataFactoryImpl">
	</component>

注意：
　Baoインターフェイスに定数アノテーションとTigerアノテーションが存在する場合
　Tigerアノテーションの内容が優先されます。
　定数アノテーションと共存する場合、注意して下さい。

