package com.example.module.model;

import com.sdl.webapp.common.api.mapping.annotations.SemanticEntity;
import com.sdl.webapp.common.api.mapping.annotations.SemanticProperty;
import com.sdl.webapp.common.api.model.entity.AbstractEntityModel;
import com.sdl.webapp.common.api.model.entity.Image;
import org.joda.time.DateTime;

import java.util.List;

import static com.sdl.webapp.common.api.mapping.config.SemanticVocabulary.SDL_CORE;

/**
 * Example model
 *
 * @author nic
 */
@SemanticEntity(entityName = "Example", vocabulary = SDL_CORE, prefix = "e", public_ = true)
public class Example extends AbstractEntityModel {

    @SemanticProperty("e:field1")
    private String field1;

    @SemanticProperty("e:field2")
    private int field2;

    @SemanticProperty("e:dateField")
    private DateTime dateField;

    @SemanticProperty("e:imageField")
    private Image imageField;

    @SemanticProperty("e:linkField")
    private String linkField;

    @SemanticProperty("e:embeddedField")
    private List<LatinQuote> latinQuotes;

    //@SemanticProperty("e:modelLinkField")
    private Example2 example2;


    private String enrichedField;


    public String getField1() {
        return field1;
    }

    public int getField2() {
        return field2;
    }

    public DateTime getDateField() {
        return dateField;
    }

    public Image getImageField() {
        return imageField;
    }

    public String getLinkField() {
        return linkField;
    }

    public List<LatinQuote> getLatinQuotes() {
        return latinQuotes;
    }

    public Example2 getExample2() {
        return example2;
    }

    public String getEnrichedField() {
        return enrichedField;
    }

    public void setEnrichedField(String enrichedField) {
        this.enrichedField = enrichedField;
    }
}
