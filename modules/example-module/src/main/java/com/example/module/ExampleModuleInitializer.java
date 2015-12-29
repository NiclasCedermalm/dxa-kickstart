package com.example.module;

import com.sdl.webapp.common.impl.AbstractInitializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Example Module Initializer
 *
 * @author nic
 */

@Component
public class ExampleModuleInitializer extends AbstractInitializer {

    // TODO: Why is abstract initializer part of common-impl?? Should be in common-api. Modules should avoid having dependency to common-impl!!!!

    private static final String AREA_NAME = "Example";

    @PostConstruct
    public void initialize() throws Exception {

        /*
        this.registerViewModel("Example", Example.class);
        this.registerViewModel("ExampleEmbedded", LatinQuote.class);
        */
    }

    @Override
    protected String getAreaName() {
        return AREA_NAME;
    }
}
