# Owner's Gate

マンション売却に伴う **家財整理** を管理するための、個人用ダッシュボードアプリです。

実際に自宅マンションを売却・整理した経験をもとに、「どの部屋の、どの大型家具を、事前に処分するか／引っ越し当日に処分するか」「自分でゴミを何袋出したか」を一元管理する目的で作成しました。単なる学習用のサンプルではなく、**実体験の課題を解決するために設計** している点が特徴です。

---

## 主な機能

- **ログイン認証**（Spring Security）
- **部屋別 廃棄リスト**
  - 間取り図（CSSで再現）をクリックして部屋ごとに絞り込み
  - アイテムの追加・削除、処分「済 / 未」の切り替え
  - Phase（事前処分 / 引っ越し時処分）の管理
- **自力処分の記録**
  - 6大分類（燃えるゴミ・不燃・缶ペット・ビン・プラ・紙資源）ごとに、処分した量・日付・メモを記録
  - 追加・削除
- **入力バリデーション**（サーバー側 + ブラウザ側の二段構え）

---

## 技術スタック

| 分類 | 使用技術 |
|---|---|
| 言語 / ビルド | Java 17 / Maven |
| フレームワーク | Spring Boot 3.4 |
| セキュリティ | Spring Security（BCrypt によるパスワードハッシュ化 + DB認証） |
| データアクセス | Spring Data JPA |
| データベース | 開発時：H2 Database（ファイルモード）/GitHub、AWSデプロイ時：PostgreSQL |
| 画面 | Thymeleaf + 素の CSS |
| 非同期通信 | REST API + Vanilla JavaScript（fetch） |
| 入力検証 | Bean Validation（`@Valid`） |

---

## アーキテクチャ（3層構造）

役割ごとに層を分け、層をまたぐデータは **DTO と Entity を使い分けて** います。

```
[ブラウザ / fetch]
      │  DTO（画面・APIとやり取りする箱）
      ▼
[Controller]  findAll / findById / create / update / delete
      │  DTO
      ▼
[Service]     selectAll / selectById / register / modify / remove
      │  Entity（DBと対応する箱）        ＋ @Transactional
      ▼
[Repository]  Spring Data JPA（findAll / save / deleteById …）
      │
      ▼
[H2 Database]  schema.sql でテーブル作成 → data.sql で初期データ投入※開発のみ。本番はPostgreSQLにて管理するため初期設定は不要
```

- **Controller ⇔ Service** は DTO でやり取り
- **Service ⇔ Repository** は Entity でやり取り
- **DTO ⇔ Entity の変換は Service の中だけ** で行う
- メソッド名を層ごとに変える（Controller: `findAll` / Service: `selectAll`）ことで、役割を明確化

---

## パッケージ構成

```
com.ownersgate
├── controller/   画面・REST APIの入口（+ 例外ハンドラ）
├── service/      業務ロジック、DTO⇔Entity変換、トランザクション境界
├── repository/   DBアクセス（Spring Data JPA）
├── entity/       DBのテーブルに対応（WasteItem / SelfDisposalLog / AppUser）
├── dto/          画面・APIとやり取りする箱（+ バリデーションルール）
├── config/       Spring Security 設定
└── exception/    独自例外（NotFoundException）
```

---

## データベース設計

起動時に `schema.sql`（テーブル定義）→ `data.sql`（初期データ）を自動実行します。※開発時のみ

| テーブル | 内容 |
|---|---|
| `app_user` | ログインユーザー（パスワードはBCryptハッシュで保存） |
| `waste_item` | 部屋別の廃棄アイテム（部屋番号・サイズ・Phase・完了フラグ） |
| `self_disposal_log` | 自力処分の記録（大分類・小分類・数量・単位・日付・メモ） |

---

## 起動方法　

1.※インターネットで確認する場合（AWS環境へデプロイ済）

1. ブラウザで `http://13.192.78.72:8080` を開く
   ※「接続が保護されていません」と表示された場合は「詳細設定」→「アクセスする」で進んでください
2. 以下でログイン

   | 項目 | 値 |
   |---|---|
   | ユーザー名 | `owner` |
   | パスワード | `ownerpass9999` |

2.※ローカル開発環境で確認する場合

1. IDE（Eclipse 等）で `OwnersGateApplication.java` を実行
2. ブラウザで `http://localhost:8080` を開く
3. 以下でログイン

   | 項目 | 値 |
   |---|---|
   | ユーザー名 | `owner` |
   | パスワード | `ownerpass9999` |

- DBの中身は `http://localhost:8080/h2-console`（JDBC URL: `jdbc:h2:file:./data/owners_gate_db` / ユーザー `sa`）で確認できます。※開発時のみ
- 起動のたびに `schema.sql` / `data.sql` が実行され、初期データに戻ります。※開発時のみ

---

## 設計上の工夫ポイント

- **DTO と Entity の分離**：DBの構造をそのまま外部に晒さず、受け渡し専用のDTOを介する
- **DBの初期化をSQLファイルで管理**：テーブル定義とデータをコードから切り離し、`schema.sql` / `data.sql` で一元管理　※開発時のみ
- **パスワードのハッシュ化 + DB認証**：平文パスワードをソースにもDBにも残さない（BCrypt）
- **バリデーションと例外処理の一元化**：入力チェックはDTOに宣言的に記述し、エラー応答は `@RestControllerAdvice` で共通化（400 / 404）
- **間取り図をCSSで再現**：CSS Grid の `grid-template-areas` で実際の間取りを表現
- **サーバーサイド描画（Thymeleaf）と REST API の併用**：一覧表示はSSR、更新系は fetch で非同期

---

## 今後の課題

- **CSRF の有効化**
（学習用のためSecurityConfigにて.csrf(csrf -> csrf.disable());で無効化の暫定対応
本来はfetch の各リクエストに `[csrfHeader]: csrfToken` を付与し、外部からの侵入を防ぐ）
- **クラウドへのデプロイ**（AWSのEC2へアプリ、RDSへPostgreSQLの環境作成）⇒完了

---

> 本アプリは学習およびポートフォリオを目的とした個人開発です。
