package com.adobe.acs.commons.indesign.deckdynamo.core.models;

import com.adobe.acs.commons.indesign.deckdynamo.core.osgiconfigurations.DeckDynamoConfigurationService;
import com.adobe.acs.commons.indesign.deckdynamo.core.utils.DeckDynamoUtils;
import com.adobe.acs.commons.mcp.form.SelectComponent;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This custom select is used to populate the available collection for the current user.
 */
public class CollectionSelectComponent extends SelectComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionSelectComponent.class);

    @Override
    public Map<String, String> getOptions() {
        Map<String, String> options = new LinkedHashMap<>();
        SlingHttpServletRequest request = getHelper().getRequest();
        ResourceResolver resourceResolver = request.getResourceResolver();
        DeckDynamoConfigurationService configurationService =
                getHelper().getService(DeckDynamoConfigurationService.class);

        if (null == configurationService) {
            LOGGER.debug("Configuration service is null, hence exiting the process and returning empty map");
            return Collections.emptyMap();
        }

        Map<String, String> collectionMap =
                DeckDynamoUtils.getCollectionsListForLoggedInUser(configurationService.getCollectionQuery(),
                        resourceResolver);
        options.put(StringUtils.EMPTY, "Select the Collection");
        collectionMap.forEach((key, value) -> options.put(value, key));

        return options;
    }
}
