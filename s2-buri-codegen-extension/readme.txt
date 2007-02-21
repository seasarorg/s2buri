s2-buri-codegen-extension 20070221版について

■s2-buri-codegen-extensionとは

s2dao-codegen(0.2.0-SNAPSHOT：リビジョン181)を独自に拡張してぶりの状態問い合わせ機能を追加したものです
s2dao-codegenに関してはこちらからどうぞ
http://s2dao-codegen.sandbox.seasar.org/

■使い方は
・s2dao-codegenが動作する環境を構築
	とりあえず一度動かしてエラーがないかなどを確認してください
・s2-buri-codegen-extensionのdiconとfmフォルダをs2dao-codegenの同名フォルダに上書き
・s2dao-codegenと同じように実行
これでs2dao-codegenが本来出力するファイルとぶり対応の拡張済みのDaoとSQLが完成しています
出力したファイルをプロジェクトのjavaやresourceフォルダ上にコピーして使ってください

■Daoの独自拡張メソッド
public String findBuri_ARGS = "dto,paths";
public List findBuri(BillFindDto dto,List paths);

public String findBuriAndUser_ARGS = "dto,paths,userIDs";
public List findBuriAndUser(BillFindDto dto,List paths,List userIDs);

■用語
pathsとは
・文字列表記のパスのリストを渡します

userIDsとは
・ぶり内部のユーザIDをリストで渡します

StandardBuriProcessor01Testのtest01Txに利用例としてサンプルがあります。

■実行環境について
これらのSQLの実行にはぶりのテーブルが必要です
ぶりの実行を伴わない場合はぶりのjarは不要です
