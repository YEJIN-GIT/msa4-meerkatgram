package com.msa4meerkatgram.global.Response;

import com.msa4meerkatgram.global.Response.constant.CustomResponseCode;

public record GlobalRes<T>(
    String code
    ,String message
    ,T data
) {
    public static <T> GlobalRes<T> from(CustomResponseCode customResponseCode, T data) {
        return new GlobalRes<T>(customResponseCode.getCode(), customResponseCode.name(), data);
    }

    public static GlobalRes<Void> from(CustomResponseCode customResponseCode) {
        return new GlobalRes<Void>(customResponseCode.getCode(), customResponseCode.name(), null);
    }
     /**
     * 성공 패턴1:데이터를 가지는 메소드
     * @param data
     * @return
     * @param <T>
     */
    public static <T> GlobalRes<T> success(T data) {
        return GlobalRes.<T>from(CustomResponseCode.SUCCESS, data);
    }
    /**
     * 성공 패턴2:데이터가 없는 메소드
     * @param data
     * @return
     * @param <T>
     */
    public static GlobalRes<Void> success() {
        return GlobalRes.<Void>from(CustomResponseCode.SUCCESS);
    }
}