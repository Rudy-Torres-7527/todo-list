package com.progress.todoList.mapper;

public interface BaseMapper<M, D> {

    M toModel(D dto);
    D toDTO(M model);

}
