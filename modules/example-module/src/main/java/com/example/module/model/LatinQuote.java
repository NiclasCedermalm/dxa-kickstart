package com.example.module.model;

import com.sdl.webapp.common.api.mapping.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.entity.AbstractEntityModel;

import static com.sdl.webapp.common.api.mapping.config.SemanticVocabulary.SDL_CORE;

/**
 * Latin Quote - example of a model class for an embedded schema
 *
 * @author nic
 */
@SemanticEntity(entityName = "ExampleEmbedded", vocabulary = SDL_CORE, prefix = "es", public_ = true)
public class LatinQuote extends AbstractEntityModel {

    @SemanticProperty("es:subField1")
    private String latinQuote;

    @SemanticProperty("es:subField2")
    private String translation;

    public String getLatinQuote() {
        return latinQuote;
    }

    public String getTranslation() {
        return translation;
    }
}
