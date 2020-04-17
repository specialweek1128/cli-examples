# Spring Boot コマンドラインアプリ

データベース接続設定をコマンドパラメータで指定できるSpring Bootアプリケーションのテンプレートです。

## Usage

```
usage: java -jar cli-examples.jar -k <key> [option]
 -?,--help               ヘルプメッセージを表示します。
 -d,--dbName <arg>       データベース名を指定してください。この値デフォルト値は"postgres"です。
 -h,--host <arg>         DBのホスト名を指定してください。この値デフォルト値は"localhost"です。
 -k,--key <arg>          データ検索時のキーを指定してください。この値は必須です。
 -p,--port <arg>         DBのポート番号を指定してください。この値デフォルト値は"5432"です。
 -P,--dbPassword <arg>   DBのパスワードを指定してください。この値デフォルト値は"postgres"です。
 -U,--dbUserId <arg>     DBのユーザIDを指定してください。この値デフォルト値は"postgres"です。
```

## Version

| ライブラリ | バージョン |
| --- | --- |
| Java | 13 |
| Spring Boot | 2.2.5 |
| Apache CLI | 1.4 |
