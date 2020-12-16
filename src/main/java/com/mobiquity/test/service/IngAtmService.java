package com.mobiquity.test.service;

import com.mobiquity.test.exception.AtmsNotFound;
import com.mobiquity.test.model.Atm;
import com.mobiquity.test.util.IngAtmWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class IngAtmService {

    @Autowired
    IngAtmWrapper atMsWrapper;

    public List<Atm> getCityAtms(String city) throws IOException {
        List<Atm> cityATMs = getAtms(city);
        return cityATMs;
    }

	
	  public Integer getCityAtmsSize(String city) throws IOException { return
	  getAtms(city).size(); }
	  
	  public List<Atm> getCityAtmsWithPagination(String city, int pageNumber, int
	  pageSize) throws IOException { return getAtms(city).stream().sorted()
	  .skip((long) (pageNumber * pageSize)) .limit((long)
	  (pageSize)).collect(Collectors.toList()); }
	 
    private List<Atm> getAtms(String city) throws IOException {
        List<Atm> cityATMs = Arrays.stream(atMsWrapper.getATMs("https://www.ing.nl/api/locator/atms/")).filter(atm -> atm.getAddress().getCity().equals(city)).sorted().collect(Collectors.toList());
        if (cityATMs.size() == 0) {
            throw new AtmsNotFound();
        }
        return cityATMs;
    }
}
