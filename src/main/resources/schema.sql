-- ============================================================
-- テーブル定義（起動時にこのSQLが実行される）
-- ここで作るテーブル名・カラム名は、Entity のフィールド名を
-- スネークケースに直したものと一致させている。
--   例) WasteItem.roomNumber -> waste_item.room_number
-- ============================================================

-- 何度起動しても作り直せるように、まず既存のテーブルを消す
DROP TABLE IF EXISTS waste_item;
DROP TABLE IF EXISTS self_disposal_log;
DROP TABLE IF EXISTS app_user;

-- ログインユーザー（パスワードはハッシュ化して保存する）
CREATE TABLE app_user (
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(50)  NOT NULL
);

-- 部屋別の廃棄アイテム
CREATE TABLE waste_item (
    id           BIGSERIAL PRIMARY KEY,
    room_number  INT,
    item_name    VARCHAR(255),
    size         VARCHAR(50),
    phase        INT,
    is_completed BOOLEAN DEFAULT FALSE
);

-- 自力処分の記録
CREATE TABLE self_disposal_log (
    id            BIGSERIAL PRIMARY KEY,
    main_category VARCHAR(100),
    category      VARCHAR(100),
    quantity      INT,
    unit          VARCHAR(20),
    disposal_date DATE,
    note          VARCHAR(500)
);
