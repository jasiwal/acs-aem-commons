package com.adobe.acs.commons.indesign.deckdynamo.core.services;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.adobe.acs.commons.indesign.deckdynamo.core.exception.DeckDynamoException;

import javax.jcr.RepositoryException;
import java.io.IOException;
import java.util.List;

public interface DeckDynamoService {


    /**
     * This method will create the InDesign deck based on list of assets and other supporting parameter supplied.
     * <p>
     * TODO Planning to create service account and remove the dependency of resourceResolver from this service
     *
     * @param deckName
     * @param masterAssetPath
     * @param assetResourceList
     * @param templateFolderPath
     * @param resourceResolver
     * @param destinationFolderPath
     * @return
     * @throws IOException
     * @throws RepositoryException
     */
    public String createDeck(String deckName, String masterAssetPath, List<Resource> assetResourceList,
                             String templateFolderPath, String destinationFolderPath, ResourceResolver resourceResolver)
            throws DeckDynamoException;

    /**
     * This method fetch asset list from collection/smart-collection.
     * <p>
     * TODO Planning to create service account and remove the dependency of resourceResolver from this service
     *
     * @param collectionPath
     * @param resourceResolver
     * @return
     */
    public List<Resource> fetchAssetListFromCollection(String collectionPath, ResourceResolver resourceResolver)
            throws DeckDynamoException;

    public List<Resource> fetchAssetListFromQuery(String queryString, ResourceResolver resourceResolver);

    public List<Resource> fetchAssetListFromTags(String tagsString, ResourceResolver resourceResolver);
}
