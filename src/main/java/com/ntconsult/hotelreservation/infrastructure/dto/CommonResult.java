package com.ntconsult.hotelreservation.infrastructure.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.ntconsult.hotelreservation.util.Util;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = -7759049455606088964L;

    @JsonProperty("code")
    private int code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private T data;

    @JsonProperty("anterior")
    private T anterior;


    public static <T> CommonResult<T> success(String msg, @Nullable T data, @Nullable T anterior) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        result.setAnterior(anterior);
        return result;
    }

    public static <T> CommonResult<T> success(String msg, T data) {
        return success(msg, data, null);
    }

    public static <T> CommonResult<T> success(String msg) {
        return success(msg, null, null);
    }


    public static <T> CommonResult<T> success(T data) {
        return success(Util.retornaMensagem("obtidos.sucesso"), data, null);
    }

    public static <T> CommonResult<T> success() {
        return success(Util.retornaMensagem("operacao.sucesso"), null, null);
    }

    public static <T> CommonResult<T> error(String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> exception(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommonResult<?> that = (CommonResult<?>) o;
        return code == that.code && Objects.equals(msg, that.msg) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, data);
    }
}
