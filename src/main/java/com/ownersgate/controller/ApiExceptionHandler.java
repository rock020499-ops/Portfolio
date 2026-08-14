package com.ownersgate.controller;

import com.ownersgate.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * REST API 全体で共通の例外処理をまとめるクラス。
 * 例外の種類ごとに、返すHTTPステータスとメッセージを決める。
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * @Valid のチェックに失敗したとき（MethodArgumentNotValidException）。
     * 「どの項目が、なぜダメか」を JSON で返す。ステータスは 400（Bad Request）。
     * 返す形の例:
     *   { "itemName": "アイテム名は必須です", "roomNumber": "部屋は必須です" }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errors;
    }

    /**
     * 指定IDのデータが見つからないとき（NotFoundException）。
     * ステータスは 404（Not Found）。
     * 返す形の例:
     *   { "message": "廃棄アイテムが見つかりません: id=999" }
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return error;
    }
}
