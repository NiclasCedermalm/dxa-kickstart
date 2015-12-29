package com.example.module.model;

import com.sdl.webapp.common.api.mapping.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.entity.AbstractEntityModel;

import static com.sdl.webapp.common.api.mapping.config.SemanticVocabulary.SDL_CORE;

/**
 * Example2
 *
 * @author nic
 */
@SemanticEntity(entityName = "Example2", vocabulary = SDL_CORE, prefix = "e", public_ = true)
public class Example2 extends AbstractEntityModel {

    @SemanticProperty("e:field1")
    private String field1;

    public String getField1() {
        return field1;
    }
}
