package com.schegolevalex.heat_engineering_calculations.controllers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.JacksonJsonProvider;
import com.jayway.jsonpath.spi.json.JsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.jayway.jsonpath.spi.mapper.MappingProvider;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

@Component
public class JsonArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonArg.class);
    }

    //    @Override
//    public Object resolveArgument(MethodParameter parameter,
//                                  ModelAndViewContainer mavContainer,
//                                  NativeWebRequest webRequest,
//                                  WebDataBinderFactory binderFactory)
//            throws Exception {
//        DocumentContext requestParsedBody = getRequestParsedBody(webRequest);
//        String jsonPath = Objects.requireNonNull(Objects.requireNonNull(parameter.getParameterAnnotation(JsonArg.class)).value());
//        Class<?> parameterType = parameter.getParameterType();
//        return requestParsedBody.read(jsonPath, parameterType);
//    }
//
//    private DocumentContext getRequestParsedBody(NativeWebRequest webRequest) {
//        HttpServletRequest servletRequest = Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class));
//        String jsonBody = String.valueOf(servletRequest.getAttribute(JSON_BODY_ATTRIBUTE));
//        if (jsonBody == "null") {
//            try {
//                jsonBody = IOUtils.toString(servletRequest.getInputStream());
//                servletRequest.setAttribute(JSON_BODY_ATTRIBUTE, JsonPath.parse(jsonBody));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return JsonPath.parse(jsonBody);
//    }
    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory)
            throws Exception {
        String body = getRequestBody(webRequest);
        String jsonPath = Objects.requireNonNull(Objects.requireNonNull(parameter.getParameterAnnotation(JsonArg.class)).value());
        Class<?> parameterType = parameter.getParameterType();

        Configuration.setDefaults(new Configuration.Defaults() {

            private final JsonProvider jsonProvider = new JacksonJsonProvider();
            private final MappingProvider mappingProvider = new JacksonMappingProvider();

            @Override
            public JsonProvider jsonProvider() {
                return jsonProvider;
            }

            @Override
            public MappingProvider mappingProvider() {
                return mappingProvider;
            }

            @Override
            public Set<Option> options() {
                return EnumSet.noneOf(Option.class);
            }
        });


        return JsonPath.parse(body).read(jsonPath, parameterType);
    }

    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class));
        String jsonBody = (String) servletRequest.getAttribute(JSON_BODY_ATTRIBUTE);
        if (jsonBody == null) {
            try {
                jsonBody = IOUtils.toString(servletRequest.getInputStream());
                servletRequest.setAttribute(JSON_BODY_ATTRIBUTE, jsonBody);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return jsonBody;
    }
}
