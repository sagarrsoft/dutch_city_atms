/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobiquity.test.exception;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No ATMs availble for this city.")
public class AtmsNotFound extends RuntimeException {

    private static final Logger LOG = Logger.getLogger(AtmsNotFound.class);

    @Override
    public String getMessage() {
        LOG.error("No ATMs availble for this city.");
        return "No ATMs availble for this city.";
    }

}
