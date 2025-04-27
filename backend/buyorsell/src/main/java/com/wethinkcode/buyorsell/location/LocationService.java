package com.wethinkcode.buyorsell.location;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepo locationRepo;

    public Location saveAddress(int id,Location location) {
        if (location.getAddressLine1() == null || location.getAddressLine1().isEmpty()) {
            throw new IllegalArgumentException("Address Line 1 cannot be null or empty");
        }
        Location newAddress = new Location(
            location.getAddressLine1(),
            location.getAddressLine2(),
            location.getCountry(),
            location.getPostalCode(),
            location.getCity()
        );
        System.out.println(newAddress);
        return locationRepo.save(newAddress);
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
