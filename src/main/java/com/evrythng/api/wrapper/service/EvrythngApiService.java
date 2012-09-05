package com.evrythng.api.wrapper.service;

import com.evrythng.api.wrapper.Configuration;
import com.evrythng.api.wrapper.util.LoggerConfigurator;
import com.evrythng.thng.resource.model.core.ResourceModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

/**
 * API service which facilitates provides helper
 * methods for performing remote method calls as well as deserializing the
 * corresponding JSON responses.
 *
 * @author
 */
public abstract class EvrythngApiService<T extends ResourceModel> {

    private Configuration config; 
    private final Logger log = Logger.getLogger(LoggerConfigurator.class);
    
    /**
     * ObjectMapper singleton.
     */
    private static ObjectMapper MAPPER = null;

    public EvrythngApiService(Configuration config) {
        this.config = config;
    }

    /**
     * This method calls POST on a resource, i.e., it creates a resource.
     *
     * @param <T>    Type to be mapped for response
     * @param path   path to the resource
     * @param object resource to be POSTed
     * @param cl     Class of the POSTed resource
     *
     * @return The response mapper to the given type
     */
    protected <T> T post(String path, T object, Class<T> cl) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost request = new HttpPost(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Content-Type", Configuration.CONTENT_TYPE);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addKey(request);

        try {
            logRequest(request.getMethod(), request.getURI());

            request.setEntity(new StringEntity(MAPPER.writeValueAsString(object)));
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return MAPPER.readValue(entity.getContent(), cl);

        } catch (IllegalStateException e) {
            log.error("Error while POSTting a resource", e);
        } catch (IOException e) {
            log.error("Error while POSTting a resource", e);
        }
        return null;
    }

    /**
     * This method calls PUT on a resource, i.e., it updates an existing
     * resource.
     *
     * @param <T>    Type to be mapped for response
     * @param path   path to the resource
     * @param object resource to be PUT
     * @param cl     Class of the PUT resource
     *
     * @return The response mapper to the given type
     */
    protected <T> T put(String path, T object, Class<T> cl) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPut request = new HttpPut(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Content-Type", Configuration.CONTENT_TYPE);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addKey(request);

        try {
            logRequest(request.getMethod(), request.getURI());

            request.setEntity(new StringEntity(MAPPER.writeValueAsString(object)));
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return MAPPER.readValue(entity.getContent(), cl);

        } catch (IllegalStateException e) {
            log.error("Error while PUTting a resource", e);
        } catch (IOException e) {
            log.error("Error while PUTting a resource", e);
        }
        return null;
    }

    /**
     * This method calls GET on a resource, i.e., it retrieves a resource.
     *
     * @param <T>    Type to be mapped for response
     * @param path   path to the resource
     * @param object resource to be POSTed
     * @param cl     Class of the POSTed resource
     *
     * @return The response mapper to the given type
     */
    protected <T> T get(String path, Class<T> cl) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet request = new HttpGet(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addKey(request);

        try {
            logRequest(request.getMethod(), request.getURI());

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            return MAPPER.readValue(entity.getContent(), cl);

        } catch (IllegalStateException e) {
            log.error("Error while GETting a resource", e);
        } catch (IOException e) {
            log.error("Error while GETting a resource", e);
        }
        return null;
    }

    /**
     *
     * /**
     * This method calls DELETE on a resource, i.e., it removes a resource.
     *
     * @param path path to the resource
     *
     * @return The HttpResponse
     */
    protected HttpResponse delete(String path) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpDelete request = new HttpDelete(Configuration.BASE_DOMAIN_URL + path);
        request.addHeader("Accept", Configuration.ACCEPT_TYPE);
        addKey(request);

        try {
            logRequest(request.getMethod(), request.getURI());
            return httpclient.execute(request);

        } catch (IllegalStateException e) {
            log.error("Error while DELETting a resource", e);
        } catch (IOException e) {
            log.error("Error while DELETting a resource", e);
        }
        return null;
    }

    /**
     * Adds an API key to a request.
     *
     * @param request
     * @param key
     *
     * @return
     */
    protected HttpRequest addKey(HttpRequest request) {
        if (config.getApiKey() != null) {
            request.addHeader("X-Evrythng-Token", config.getApiKey());
        }
        return request;
    }

    protected void printResponse(HttpResponse response) {
        String inputLine;
        HttpEntity entity = response.getEntity();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        } catch (IOException e) {
            log.error("Error while printing response", e);
        }
    }

    private void logRequest(String method, URI url) {
        log.debug("Calling" + method + " on: " + url.toASCIIString());
    }


    /**
     * Use Jackson to deserialize a JSON object to a native class
     * representation.
     *
     * @param <T>       Native class type.
     * @param typeToken Native class type wrapper.
     * @param response  Serialized JSON object.
     *
     * @return Deserialized native instance.
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    protected <T> T unmarshall(InputStream response, TypeReference<T> typeToken) throws JsonParseException, JsonMappingException, IOException {
        return (T) EvrythngApiService.getObjectMapper().readValue(response, typeToken);
    }

    /**
     * Use Jackson to deserialize a JSON string to a native class
     * representation.
     *
     * @param <T>       Native class type.
     * @param typeToken Native class type wrapper.
     * @param reponse   Serialized JSON string.
     *
     * @return Deserialized native instance.
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @SuppressWarnings("unchecked")
    protected <T> T unmarshall(String response, Class<?> type) throws JsonParseException, JsonMappingException, IOException {
        return (T) EvrythngApiService.getObjectMapper().readValue(response, type);
    }

    /**
     * Create am {@link ObjectMapper} and register all of the custom types
     * needed
     * in order to properly deserialize complex evrythng-specific types.
     *
     * @return Assembled Jackson mapper instance.
     */
    private static ObjectMapper getObjectMapper() {
        if (MAPPER == null) {
            MAPPER = createObjectMapper();
        }
        return MAPPER;
    }

    private static ObjectMapper createObjectMapper() {

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule evrythngModule = new SimpleModule("evrythng", new Version(3, 0, 0, "SNAPSHOT"));

        final DateFormat dateFormat = new SimpleDateFormat("SSSSSS"); // milliseconds
        evrythngModule.addDeserializer(Calendar.class, new JsonDeserializer<Calendar>() {
            @Override
            public Calendar deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
                try {
                    Calendar c = Calendar.getInstance();
                    c.setTime(dateFormat.parse(arg0.getText()));
                    return c;
                } catch (ParseException e) {
                    return null;
                }
            }
        });

        mapper.registerModule(evrythngModule);
        return mapper;
    }
}