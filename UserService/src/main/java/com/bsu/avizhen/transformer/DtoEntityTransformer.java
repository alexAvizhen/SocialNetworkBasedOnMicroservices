package com.bsu.avizhen.transformer;

public interface DtoEntityTransformer<E, D> {
    E transformDtoToEntity(D p);

    D transformEntityToDto(E e);

    Class getEntityClass();

}
