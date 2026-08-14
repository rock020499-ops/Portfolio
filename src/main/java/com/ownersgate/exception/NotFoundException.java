package com.ownersgate.exception;

/**
 * 指定されたIDのデータが見つからなかったときに投げる例外。
 * これを ApiExceptionHandler が受け止めて 404（Not Found）を返す。
 */
public class NotFoundException extends RuntimeException {

	// コンストラクタ
	// 例外クラスはLombokに対応していないので、手動でコンストラクタを作る必要がある
    public NotFoundException(String message) {
        super(message);
    }
}
