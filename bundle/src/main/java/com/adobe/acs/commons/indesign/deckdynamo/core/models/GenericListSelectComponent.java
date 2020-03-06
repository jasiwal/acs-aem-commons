package com.adobe.acs.commons.indesign.deckdynamo.core.models;

import com.adobe.acs.commons.genericlists.GenericList;
import com.adobe.acs.commons.mcp.form.SelectComponent;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Custom SelectComponent to Support Select list from GenericList
 */
public class GenericListSelectComponent extends SelectComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericListSelectComponent.class);
    public static final String GENERIC_LIST_PATH = "genericListPath";

    /**
     * Override to support options for select list from Generic Lists
     *
     * @return
     */
    @Override
    public Map<String, String> getOptions() {
        ResourceResolver resourceResolver = getHelper().getRequest().getResourceResolver();
        Map<String, String> options = new LinkedHashMap<>();

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        if (pageManager == null) {
            LOGGER.debug("Page manager is null, hence exiting the process and returning empty map");
            return Collections.emptyMap();
        }

        if (!hasOption(GENERIC_LIST_PATH)) {
            LOGGER.debug("Generic list path is null, hence exiting the process and returning empty map");
            return Collections.emptyMap();
        }
        Optional<String> listPath = getOption(GENERIC_LIST_PATH);
        Page genericListPage = pageManager.getPage(listPath.get());

        if (genericListPage == null) {
            LOGGER.debug("Generic List Page is null, hence exiting the select options process and returning empty map");
            return Collections.emptyMap();
        }

        GenericList itemList = genericListPage.adaptTo(GenericList.class);
        if (itemList == null) {
            return Collections.emptyMap();
        }

        options.put(StringUtils.EMPTY, "Select the Option");
        itemList.getItems().forEach(item -> options.put(item.getValue(), item.getTitle()));

        return options;

    }
}
