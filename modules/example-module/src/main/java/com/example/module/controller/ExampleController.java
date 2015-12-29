package com.example.module.controller;

import com.example.module.model.Example;
import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.content.ContentProviderException;
import com.sdl.webapp.common.api.localization.Localization;
import com.sdl.webapp.common.api.model.MvcData;
import com.sdl.webapp.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Example Controller
 *
 * @author nic
 */
@Controller
@RequestMapping("/system/mvc/Example/ExampleController")
public class ExampleController extends BaseController {

    @Autowired
    private WebRequestContext webRequestContext;

    @RequestMapping(method = RequestMethod.GET, value = "DoStuff/{entityId}")
    public String handleGetEntity(HttpServletRequest request, @PathVariable String entityId) throws ContentProviderException {


        Example entity = (Example) this.getEntityFromRequest(request, entityId);

        Localization localization = this.webRequestContext.getLocalization();
        String enrichEntitySetting =localization.getConfiguration("example.enrichEntity");

        if ( enrichEntitySetting != null && enrichEntitySetting.equalsIgnoreCase("true") ) {
            // Enrich entity
            //
            entity.setEnrichedField(entity.getField1() + " : " + entity.getField2());
        }

        request.setAttribute("entity", entity);

        final MvcData mvcData = entity.getMvcData();
        return resolveView(mvcData, "Entity", request);
    }

}
