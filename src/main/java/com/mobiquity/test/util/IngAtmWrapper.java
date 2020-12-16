package com.mobiquity.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiquity.test.model.Atm;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;


@Service
public class IngAtmWrapper {

    private static final Logger LOG = Logger.getLogger(IngAtmWrapper.class);
    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    public Atm[] getATMs(String Url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.getForEntity(Url, String.class);
        String data = result.getBody().substring(result.getBody().indexOf('['));
        Atm[] atms = objectMapper.readValue(data, Atm[].class);

        if (atms.length == 0) {
            LOG.warn("No result found");
        }
        return atms;
    }
}
