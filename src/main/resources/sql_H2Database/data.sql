-- ============================================================
-- 初期データ（起動時に schema.sql の後で実行される）
-- もとは DataSeeder.java にべた書きしていたデータをSQLに移した。
-- id は AUTO_INCREMENT に任せるので指定しない。
-- ============================================================

-- ---------- ログインユーザー ----------
-- password 列は "ownerpass" を BCrypt でハッシュ化した値。
-- 平文はDBのどこにも保存されない（ログイン時はハッシュ同士で照合する）。
INSERT INTO app_user (username, password, role)
VALUES ('owner', '$2a$10$IGFzjjUMKPLqQHI9gAr.BuitNmbcY4wg/liK1ZeYyUT0YdDTBrxVy', 'USER');

-- ---------- 廃棄アイテム（廃棄リスト2の内容） ----------

-- ① 洋室(2) = Room 1
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, 'エアコン（廃棄ダクトが③ベランダにあるため）', '特大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, 'デスクトップPC＋キーボード', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, 'ノートPC', '小', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '金属ラック', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '赤いラック', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '机', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '椅子（組み立て）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '扇風機', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '絨毯', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, 'カーテン', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (1, '照明器具', '中', 2);

-- ② 洋室(3) = Room 2
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (2, 'ベッド（解体済）', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (2, '絨毯', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (2, 'デスクトップPC', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (2, '金属棚', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (2, '金属ラック', '特大', 2);

-- ③ 和室 = Room 3
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '仏壇', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '布団・毛布全部', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '座布団全部', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '各種超大ゴミ（扇風機・掃除機・椅子・その他）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, 'ガス系スプレー（可能なら）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, 'テレビ', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, 'BDレコーダー＋テレビ台', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '物干しラック', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '絨毯', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, 'カーテン×2', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (3, '照明器具', '中', 2);

-- ④ 洋室(4) = Room 4
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, '冷蔵庫', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, 'エアコン＋廃棄ダクト', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, '物干しラック（赤）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, '電話台ラック（白）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, 'ラック（白）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, '絨毯', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, 'カーテン', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (4, '照明器具', '中', 2);

-- ⑤ 洋室(1) = Room 5
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (5, 'タンス（横開き）', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (5, 'タンス（複数段）', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (5, '椅子（大）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (5, '絨毯', '大', 2);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (2, 'ベッド', '特大', 2);

-- ⑥ リビング = Room 6
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '食器棚', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, 'ソファー（2つ分）', '特大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '布団・毛布全部', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '各種超大ゴミ（確認中）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '茶色の棚（ポット・トースター台）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, 'こたつテーブル', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '足場の台（平台）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '絨毯（こたつ下）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (6, '絨毯（食卓机下）', '大', 2);

-- ⑦ キッチン・ダイニング = Room 7
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (7, '白の棚（電子レンジ台）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (7, '食器棚右隣の棚', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (7, 'ゴミ箱（白）', '中', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (7, '絨毯（食器棚下）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (7, '電子レンジ', '大', 2);

-- ⑧ 風呂・洗面所・トイレ = Room 8 … 廃棄物なし
-- ⑨ 玄関 = Room 9 … 廃棄物なし

-- ⑩ バルコニー = Room 10
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (10, 'スカパーアンテナ', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (10, 'すだれ', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (10, '物干し（一部）', '大', 1);
INSERT INTO waste_item (room_number, item_name, size, phase) VALUES (10, '物干し（本体）', '特大', 2);


-- ---------- 自力処分の記録（2026/08 時点までの戦果） ----------

-- 1. 燃えるゴミ
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('燃えるゴミ', '生ゴミ', 20, '袋', '2026-08-12', '10年前の調味料・冷蔵庫の中身など。');
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('燃えるゴミ', '紙屑（シュレッダー）', 80, '袋', '2026-08-12', '個人情報書類をひたすらシュレッダー。腕がもげるかと思った。');
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('燃えるゴミ', 'その他', 90, '袋', '2026-08-12', '服・布類・小物類を一気に処分。');

-- 2. その他不燃ゴミ
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('その他不燃ゴミ', '不燃ゴミ', 25, '袋', '2026-08-12', '陶器・ガラス・小型金属類。');

-- 3. 缶・ペットボトル
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('缶・ペットボトル', '缶・ペットボトル', 13, '袋', '2026-08-12', '');

-- 4. ビン
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('ビン', 'ビン', 7, '袋', '2026-08-12', '');

-- 5. プラゴミ
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('プラゴミ', 'プラ容器・包装', 46, '袋', '2026-08-12', '');

-- 6. 紙資源・古着
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('紙資源・衣類', '本', 18, '束', '2026-08-12', 'コミック、文庫本、参考書、地図等120冊以上');
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('紙資源・衣類', '雑誌', 20, '束', '2026-08-12', '十字縛り。重すぎて腰をやるかと思った');
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('紙資源・衣類', '新聞・チラシ', 25, '束', '2026-08-12', '数年分溜まった分を一斉処分');
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('紙資源・衣類', '段ボール', 15, '束', '2026-08-12', '特大、大、中、小と大きさが様々を束ねるのに苦労');
INSERT INTO self_disposal_log (main_category, category, quantity, unit, disposal_date, note) VALUES ('紙資源・衣類', '衣類・寝具', 15, '袋', '2026-08-12', '数年分溜まった衣類・寝具を一気に処分。');
