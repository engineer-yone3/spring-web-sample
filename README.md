## Springboot サンプルアプリケーション

### ■はじめに
このリポジトリは、SpringbootでWeb画面によるCRUD処理、ダミーデータ作成、CSSによるアニメーションなど </br>
開発業務でほぼ必須とされるテクニックを用いて作成したものです。</br>
初めてJavaに触れる方、Web開発業務を行ったことの無い方は是非参考にしてみてください。

### ■使っているFW・ライブラリ等

- Springboot(3.2.2)
  - thymeleaf
  - web
  - validation
  - jdbc
  - shell
- datafaker(2.0.0)
- MyBatis(3.0.3)
- flyway(10.16.0)

他、細かい設定等はbuild.gradleを見てください。

### ■使い方

▼事前に必要なツール類
- docker
- JDK17(このアプリケーションはホストOS上にJVMがあることを前提としています)

1. mainブランチをgit checkoutします。
2. docker-compose.ymlの内容に従ってコンテナを作成します。</br>
```
docker-compose up -d
```
3. vscodeやintelliJ IDEA等のIDEでSpringBootアプリケーションの実行構成を作成、実行します。
4. Springboot起動に成功するとflywayがDBマイグレーションしてくれます。
5. Springboot起動後、shellが対話モードになります。ここで</br>
```
run-seeder
```
と実行するとSeederプログラムが実行され、reviewsテーブルにダミーデータが投入されます(デフォルトは100件)