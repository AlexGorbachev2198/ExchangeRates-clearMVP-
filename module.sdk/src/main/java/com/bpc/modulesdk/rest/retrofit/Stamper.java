package com.bpc.modulesdk.rest.retrofit;

import com.bpc.modulesdk.rest.dto.request.StampedRequest;

/**
 * Created by Masloed on 27.01.2017.
 */
@Deprecated
class Stamper {
    // TODO: 21.06.2017 выпилить после тестинга проверки подписи серваком
    static <T extends StampedRequest> T prepare(T request) {
       /* byte[]  s = new byte[0];
        try {
            s = DtoObjectMapper.create().writeValueAsBytes(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        SecurityProvider securityProvider = SecurityProviderFactory.getProvider();
        String signature = securityProvider.generateStamp(s);
        request.setStamp(signature);*/
        return request;
    }
}
