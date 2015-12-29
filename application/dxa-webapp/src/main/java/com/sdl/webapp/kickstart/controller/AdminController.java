package com.sdl.webapp.kickstart.controller;

import com.sdl.webapp.common.api.WebRequestContext;
import com.sdl.webapp.common.api.localization.Localization;
import com.sdl.webapp.common.api.localization.LocalizationResolver;
import com.sdl.webapp.common.markup.Markup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {
    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private WebRequestContext webRequestContext;

    @Autowired
    private LocalizationResolver localizationResolver;

    @Autowired
    private Markup markup;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/refresh")
    public String handleRefresh() {
        final Localization localization = webRequestContext.getLocalization();
        LOG.trace("handleRefresh: localization {}", localization);
        localizationResolver.refreshLocalization(localization);

        return "redirect:" + localization.getPath();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{locPath}/admin/refresh")
    public String handleRefreshLoc(@PathVariable String locPath) {
        return handleRefresh();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/adf-claims")
    public String displayAdfClaims(HttpServletRequest request) {
        request.setAttribute("markup", this.markup);
        return "/Admin/adf-claims";
    }

}
