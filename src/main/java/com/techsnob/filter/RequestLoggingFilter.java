package com.techsnob.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class RequestLoggingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final int MAX_PAYLOAD_LENGTH = 10000;

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if ((request instanceof HttpServletRequest)
                && !(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper((HttpServletRequest) request);
        }
        try {
            chain.doFilter(request, response);
        } finally {
            if (request instanceof HttpServletRequest) {
                performRequestAudit((HttpServletRequest) request);
            }
        }
    }

    private void performRequestAudit(HttpServletRequest httpRequest) {
        ContentCachingRequestWrapper wrapper =
                WebUtils.getNativeRequest(httpRequest, ContentCachingRequestWrapper.class);
        String payload = "";
        if (wrapper != null) {
            byte[] requestBuffer = wrapper.getContentAsByteArray();
            if (requestBuffer.length > 0) {
                int length = Math.min(requestBuffer.length, MAX_PAYLOAD_LENGTH);
                try {
                    payload = new String(requestBuffer,
                            0, length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException unex) {
                    payload = "[Unsupported-Encoding]";
                }
            }
        }
        HttpHeaders headers = new ServletServerHttpRequest(httpRequest).getHeaders();
        LOGGER.trace("{}|{}", payload, headers);
    }

}
