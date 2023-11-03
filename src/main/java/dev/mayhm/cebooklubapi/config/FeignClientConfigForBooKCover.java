//package dev.mayhm.cebooklubapi.config;
//
//import feign.codec.Decoder;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.util.StreamUtils;
//
//public class FeignClientConfigForBooKCover {
//
//    private Decoder byteArrayResourceDecoder() {
//        Decoder decoder = (response, type) -> {
//            if (type instanceof Class && ByteArrayResource.class.isAssignableFrom((Class) type)) {
//                return StreamUtils.copyToByteArray(response.body().asInputStream());
//            }
//            return new JacksonDecoder().decode(response, type);
//        };
//
//        return new ResponseEntityDecoder(decoder);
//    }
//}
