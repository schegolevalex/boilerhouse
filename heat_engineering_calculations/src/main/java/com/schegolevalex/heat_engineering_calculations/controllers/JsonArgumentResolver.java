package com.schegolevalex.heat_engineering_calculations.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

public class JsonArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String JSON_BODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonArg.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory)
            throws Exception {
        DocumentContext requestParsedBody = getRequestParsedBody(webRequest);
        String jsonPath = Objects.requireNonNull(Objects.requireNonNull(parameter.getParameterAnnotation(JsonArg.class)).value());
        Class<?> parameterType = parameter.getParameterType();
        return requestParsedBody.read(jsonPath, parameterType);
    }

    private DocumentContext getRequestParsedBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = Objects.requireNonNull(webRequest.getNativeRequest(HttpServletRequest.class));
        String jsonBody = (String) servletRequest.getAttribute(JSON_BODY_ATTRIBUTE);
        if (jsonBody == null) {
            try {
                jsonBody = IOUtils.toString(servletRequest.getInputStream());
                servletRequest.setAttribute(JSON_BODY_ATTRIBUTE, JsonPath.parse(jsonBody));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return JsonPath.parse(jsonBody);
    }
}
