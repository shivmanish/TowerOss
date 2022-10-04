package com.smarthub.baseapplication.helpers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    @NonNull
    private final int statusCode;

    private Resource(@NonNull Status status, @Nullable T data,
                     @Nullable String message, @NonNull int statusCode) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public static <T> Resource<T> success(@NonNull T data, int statusCode) {
        return new Resource<>(Status.SUCCESS, data, null, statusCode);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data, int statusCode) {
        return new Resource<>(Status.ERROR, data, msg, statusCode);
    }

    public static <T> Resource<T> loading(@Nullable T data, int statusCode) {
        return new Resource<>(Status.LOADING, data, null, statusCode);
    }

    public enum Status {SUCCESS, ERROR, LOADING}
}
