package com.bsu.avizhen.factory;

import com.bsu.avizhen.transformer.DtoEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DtoEntityTransformerFactory {

    private final List<DtoEntityTransformer> dtoEntityTransformers;

    @Autowired
    private DtoEntityTransformerFactory(List<DtoEntityTransformer> dtoEntityTransformers) {
        this.dtoEntityTransformers = dtoEntityTransformers;
    }

    public DtoEntityTransformer getDtoEntityTransformer(Class entityClass) {
        for (DtoEntityTransformer dtoEntityTransformer : dtoEntityTransformers) {
            if (dtoEntityTransformer.getEntityClass().equals(entityClass)) {
                return dtoEntityTransformer;
            }
        }
        throw new RuntimeException("Dto to entity transformer not found");
    }


}
