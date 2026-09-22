package com.transportation.shared.application.result;

import java.util.Optional;
import java.util.function.Function;

public sealed interface Result<T,E> {

    record Success<TT, TE>(TT value) implements Result<TT, TE> {
    }

    record Failure<TT, TE>(TE error) implements Result<TT, TE> {
    }

    static <TT, TE> Result<TT, TE> success(TT value) {
        return new Success<>(value); // Java interpetra q parametro usar
    }

    static <TT, TE> Result<TT, TE> failure(TE error) {
        return new Failure<>(error); // Java interpetra q parametro usar
    }

    default boolean isSuccess() {
        return this instanceof Success;
    }

    default boolean isFailure() {
        return this instanceof Failure;
    }

    default Optional<T> toOptional() {
        return switch (this) {
            case Success<T, E> s -> Optional.of(s.value);
            case Failure<T, E> f -> Optional.empty();
        };
    }
    default T getOrElse(T defaultValue) {
        return switch (this) {
            case Success<T, E> s -> s.value;
            case Failure<T, E> f -> defaultValue;
        };
    }
    default <E2> Result<T, E2> mapError(Function<E, E2> converter) {
        return switch (this) {
            case Success<T, E> s -> Result.success(s.value);
            case Failure<T, E> failure -> Result.failure(converter.apply(failure.error));
        };
    }
    default <T2> Result<T2, E> flatMap(Function<T, Result<T2, E>> converter) {
        return switch (this) {
            case Success<T, E> s -> converter.apply(s.value);
            case Failure<T, E> failure -> Result.failure(failure.error);
        };
    }

    default <T2> Result<T2, E> map(Function<T, T2> converter) {
        return switch (this) {
            case Success<T, E> s -> Result.success(converter.apply(s.value));
            case Failure<T, E> failure -> Result.failure(failure.error);
        };
    }

    default Result<T, E> recover(Function<E, Result<T, E>> converter) {
        return switch (this) {
            case Success<T, E> s -> this;
            case Failure<T, E> failure -> converter.apply(failure.error);
        };
    }
}
