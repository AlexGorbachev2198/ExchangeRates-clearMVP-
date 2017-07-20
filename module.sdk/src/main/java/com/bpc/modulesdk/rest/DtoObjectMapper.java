package com.bpc.modulesdk.rest;

import android.util.Log;

import com.bpc.modulesdk.rest.dto.response.CommonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DtoObjectMapper extends ObjectMapper {

    private DtoObjectMapper() {
        configure();
    }

    public static DtoObjectMapper create() {
        return new DtoObjectMapper();
    }


    private void configure() {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //set empty string when get string with null value
       /* SimpleModule simpleModule = new SimpleModule("NonNullModule");
        simpleModule.addDeserializer(String.class, new NonNullStringDeserializer());
        registerModule(simpleModule);*/
    }



    public <T extends CommonResponse> T copyObject(T object) {
        try {
            String json = writeValueAsString(object);
            return (T) readValue(json, object.getClass());
        } catch (Exception e) {
            Log.e("DtoObjectMapper", "Cannot copy object", e);
        }
        return null;
    }


}