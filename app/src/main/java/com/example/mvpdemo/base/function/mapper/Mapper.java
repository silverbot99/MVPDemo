package com.example.mvpdemo.base.function.mapper;

public interface Mapper<From, To> {
    To map(From value);
}
