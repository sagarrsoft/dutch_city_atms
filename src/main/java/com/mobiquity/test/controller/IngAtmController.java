package com.mobiquity.test.controller;

import com.mobiquity.test.model.Atm;
import com.mobiquity.test.service.IngAtmService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
public class IngAtmController {

    @Autowired
    IngAtmService ingAtmService;

    
    @RequestMapping(value = "/atms/all/{city}", method = {RequestMethod.GET})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Atm> getCityAtms(@PathVariable String city) throws IOException {
        return ingAtmService.getCityAtms(city);
    }

    
    @RequestMapping(value = "/atms/{city}", method = {RequestMethod.GET})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Atm> getCityAtmsPagination(@PathVariable String city, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws IOException {
        return ingAtmService.getCityAtmsWithPagination(city, pageNumber, pageSize);
    }

    
    @RequestMapping(value = "/atms/size/{city}", method = {RequestMethod.GET})
    @ResponseBody
    @PreAuthorize("hasAuthority('ADMIN')")
    public Integer getCityAtmsSize(@PathVariable String city) throws IOException {
        return ingAtmService.getCityAtmsSize(city);
    }
}
