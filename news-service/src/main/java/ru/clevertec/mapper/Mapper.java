package ru.clevertec.mapper;

public interface Mapper <F, T>{

    T map(F object);
}
