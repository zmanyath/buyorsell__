package com.wethinkcode.buyorsell.location;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public Location saveAddress(Location location) {
        return locationRepo.save(location);
    }

    public List<Location> getLocations() {
        List<Location> locations = new ArrayList<Location>();
		for(Location location : locationRepo.findAll()) {
			locations.add(location);
		}
		
		return locations;
    }

    public void deleteAddress(int id) {
        locationRepo.deleteById(id);
    }

}
