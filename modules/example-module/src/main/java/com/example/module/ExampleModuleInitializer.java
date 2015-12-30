package com.example.module;

import com.example.module.model.Example;
import com.example.module.model.LatinQuote;
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

    private static final String AREA_NAME = "Example";

    @PostConstruct
    public void initialize() throws Exception {

        this.registerViewModel("Example", Example.class);
        this.registerViewModel("ExampleEmbedded", LatinQuote.class);
    }

    @Override
    protected String getAreaName() {
        return AREA_NAME;
    }
}
