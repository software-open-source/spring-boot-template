package org.software.open.source.spring.template.aop;

import org.software.open.source.common.apis.models.responses.Response;
import org.software.open.source.spring.template.exception.ApplicationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ApplicationResponseAdvice implements ResponseBodyAdvice<Object> {

    @Value("${spring.application.name:}")
    private String applicationName;

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType, @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof Response<?>) {
            @SuppressWarnings("unchecked")
            Response<Object> resp = (Response<Object>) body;
            try {
                resp.setApplication(applicationName);
            } catch (Exception ex) {
                throw new ApplicationException("Missing service name", HttpStatus.BAD_REQUEST);
            }
            return resp;
        }
        return body;
    }
}
