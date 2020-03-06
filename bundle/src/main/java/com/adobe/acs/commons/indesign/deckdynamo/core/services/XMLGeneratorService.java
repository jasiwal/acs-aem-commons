package com.adobe.acs.commons.indesign.deckdynamo.core.services;

import com.adobe.acs.commons.indesign.deckdynamo.core.exception.DeckDynamoException;
import com.adobe.acs.commons.indesign.deckdynamo.core.pojos.XMLResourceIterator;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.io.InputStream;
import java.util.List;

/**
 * This service will be used to parse xml.
 */
public interface XMLGeneratorService {

    String generateInddXML(InputStream xmlInputStream, List<XMLResourceIterator> assetResourceIterList,
                           Resource masterResource, Resource deckResource, ResourceResolver resourceResolver, List<String> inddImageList) throws DeckDynamoException;
}
