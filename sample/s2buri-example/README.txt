サンプル動作までの作業手順


動作環境
- Eclipse 3.1.1
- Sysdeo Tomcat Plugin 3.1.0
にて動作を確認しています


作業手順
1. プロジェクト直下のbuild.xmlを使用して「ant prepare-db」を実行します
2. WEB-INF/src/j2ee.dicon内のDerbyのjdbc-urlを環境に合わせて修正します
3. Sysdeo Tomcat PluginにてTomcatを起動します
4. http://localhost:8080/s2buri-example/ にアクセスしてください

DBに既に登録されている顧客番号は('TEST001','TEST002','TEST003')なので、
それらの顧客番号を使用して検針結果の登録から開始してください。
