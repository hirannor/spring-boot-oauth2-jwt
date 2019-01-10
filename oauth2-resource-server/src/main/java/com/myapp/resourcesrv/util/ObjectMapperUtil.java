package com.myapp.resourcesrv.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * ObjectMapper utility class
 * The main purpose of this util class to deserialize json to java object and provide a base reply.
 *
 * @author mate.karolyi
 */
public class ObjectMapperUtil {

    private static final Logger LOGGER = LogManager.getLogger(ObjectMapperUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void getBaseReply(String success, String message, HttpServletResponse response)
    {
        Map<String, String> map = new HashMap<String, String>();

        map.put("success", success);
        map.put("message", message);

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            objectMapper.writeValue(response.getWriter(), map);
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public static Object deserialize(ServletRequest request, Class c)
    {
        HttpServletRequest req = (HttpServletRequest) request;

        try {
            return new ObjectMapper().readValue(req.getInputStream(), c);
        }
        catch(IOException ex)
        {
            LOGGER.error("Something went wrong during deserialization of json: {}", ex);
            throw new RuntimeException(ex);
        }
    }
}
