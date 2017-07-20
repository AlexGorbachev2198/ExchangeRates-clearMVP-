package com.bpc.modulesdk.net;

import com.bpc.modulesdk.security.SecurityProvider;
import com.bpc.modulesdk.security.SecurityProviderFactory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

class StampSignInterceptor implements Interceptor {

    private static final String STAMP_FIELD = "\"stamp\"";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String INITIAL_STAMP = "00000000000000000000";

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    private static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        RequestBody body = request.body();
        if (body != null) {
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            if (isPlaintext(buffer)) {
                Charset charset = identifyCharset(body);
                String bodyString = buffer.readString(charset);
                if (bodyString.indexOf(STAMP_FIELD) > 0) {
                    request = createStampedRequest(request, body, bodyString);
                }
            }
        }
        return chain.proceed(request);
    }

    private Request createStampedRequest(Request originalRequest, RequestBody body, String bodyString) {
        bodyString = createStampedBodyString(bodyString);
        RequestBody stampedBody = RequestBody.create(body.contentType(), bodyString);
        Request.Builder stampedRequestBuilder = originalRequest.newBuilder();
        return stampedRequestBuilder
                .post(stampedBody)
                .headers(originalRequest.headers())
                .build();
    }

    private String createStampedBodyString(String bodyString) {
        SecurityProvider securityProvider = SecurityProviderFactory.getProvider();
        String signature = securityProvider.generateStamp(bodyString.getBytes());

        int i1 = bodyString.indexOf(STAMP_FIELD);
        int i2 = bodyString.indexOf(INITIAL_STAMP, i1 + STAMP_FIELD.length() + 1);
        int i3 = bodyString.indexOf("\"", i2 + 1);

        StringBuilder sb = new StringBuilder(bodyString.length() + signature.length())
                .append(bodyString.substring(0, i2))
                .append(signature)
                .append(bodyString.substring(i3));

        return sb.toString();
    }

    private Charset identifyCharset(RequestBody body) {
        Charset charset = UTF8;
        MediaType contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }
        return charset;
    }
}