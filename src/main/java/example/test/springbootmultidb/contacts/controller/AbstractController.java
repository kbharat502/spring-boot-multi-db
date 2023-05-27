package example.test.springbootmultidb.contacts.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;

public class AbstractController {
    private static final Logger LOG = LogManager.getLogger(AbstractController.class);

    protected void buildLocationHeader(HttpServletRequest request, HttpHeaders headers, String path) {
        StringBuffer requestURL = request.getRequestURL();
        LOG.debug("Request URI: {}", requestURL.toString());

        String contextPath = request.getContextPath();
        LOG.debug("Context Path: {}", contextPath);

        String domainPath = requestURL.toString().substring(0,
                (requestURL.toString().indexOf(contextPath) + (contextPath.length())));
        LOG.debug("Domain Path: {}", domainPath);

        headers.add("Location", (String.join("", domainPath, path)));
    }
}
