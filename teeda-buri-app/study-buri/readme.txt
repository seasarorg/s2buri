*=========================================================================
study-buriについて
	作成者	ぶりコミッタ a-conv
	Blog		http://d.hatena.ne.jp/a-conv
*=========================================================================
最初に
*=========================================================================
study-buriはぶりのサンプルを実行し、画面で結果を確認し、ぶりの動作を理解するためのサンプルWebアプリケーションです。

*=========================================================================
作成環境
*=========================================================================
	WindowsXP
	jdk1.5.0_11
	maven2.0.x
	Apache-Tomcat-5.5.x
	eclipse3.2.2 + gef + emf-sdo-xsd

	利用しているeclipseプラグイン
		Seasar2支援
			Seasar-EclipsePlugin-UpdateSite3.2
					http://eclipse.seasar.org/updates/3.2
				・Doltengプラグイン
				・DBLauncherプラグイン

		Seasar2支援
			Seasar-EclipsePlugin-UpdateSite3.1
					http://eclipse.seasar.org/updates/3.1
				・kijimunaプラグイン
				・Maihimeプラグイン
			
		Tomcatコントロール用
			Sysdeo/SQLI Eclipse Tomcat Launcher plugin
					http://www.eclipsetotale.com/tomcatPlugin.html
				・Sysdeo Tomcatプラグイン

		SVN操作用
			Polarion community for Subversion
					http://www.polarion.org/projects/subversive/download/1.1/update-site/
				・subversiveプラグイン

*=========================================================================
動作確認環境
*=========================================================================
	ブラウザ
		IE6
		FireFox

*=========================================================================
使用方法(SVN)
*=========================================================================
	1.Maven2および、Eclipse、使用しているプラグインをインストールして下さい。

	2.Maven2を利用してセットアップを行う。
		2-1.ぶり・サンプル用DB作成(DB作成時はテストを行わない)
			mvn -DdownloadSources=true -Denv=h2 -Dmaven.test.skip=true
	
		2-2.コンパイル(すみません、テストケースがありませんが....)
			mvn -DdownloadSources=true clean verify
	
		2-3.eclipseにmaven2のリポジトリを登録
			mvn -Declipse.workspace=<Eclipseワークスペースへのパス> eclipse:add-maven-repo 

	3.Tomcatプラグインに利用するTomcatの設定を行う
	
	4.study-buriプロジェクトを右クリックして、Tomcatプロジェクト -> コンテキスト定義を更新をクリック
	
	5.study-buriプロジェクトを右クリックして、H2 -> Start H2 Serverをクリック
	
	6.上記設定後、Tomcatを起動し、正常に起動する事を確認して下さい。	

	7.下記URLにアクセスするとトップページが表示されます。
		http://localhost:8080/study-buri/view/index.html
		
	**注意**
	maven2、eclipse,subversionの基本的な利用法については割愛させていただきます。
	ご了承下さい。
