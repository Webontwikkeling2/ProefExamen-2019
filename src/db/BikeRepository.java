package db;

import domain.Bike;
import domain.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BikeRepository {
    private Map<String, Bike> bikes = new HashMap<String, Bike>();

    public BikeRepository() {
        Bike bianchi = new Bike("1", "Bianchi", Category.ROAD,
                "Bianchi Aria 2018 - Shimano Ultegra 11sp Compact 50/34", 2999);
        Bike trek = new Bike("2", "Trek", Category.CITY, "Trek L400 Lowstep", 949);
        Bike breezer = new Bike("3", "Breezer", Category.CITY,
                "Breezer Downtown EX City Bike", 399);
        Bike bnb = new Bike("4", "BNB", Category.CITY,
                "ALU-Deep H38 7V Wit/Zeeblauw", 385);
        Bike oxford = new Bike("5", "Oxford", Category.CITY, "Buster 21V", 629);
        Bike ridley = new Bike("6", "Ridley", Category.ROAD,
                "Ridley Noah Ultegra Road Bike - Performance Exclusive", 2199);
        Bike lapierre = new Bike("7", "Lapierre", Category.ROAD,
                "Xelius SL Ultimate MC 700", 2799);
        add(bianchi);
        add(trek);
        add(breezer);
        add(bnb);
        add(oxford);
        add(ridley);
        add(lapierre);
    }

    public void add(Bike bike) {
        if (bike == null)
            throw new IllegalArgumentException("No bike to add");
        if (bikes.containsKey(bike.getItemId()))
            throw new IllegalArgumentException("Bike already exists");
        bikes.put(bike.getItemId(), bike);
    }

    public Bike get(String itemId) {
        if (itemId == null)
            throw new IllegalArgumentException("No id given");
        return bikes.get(itemId);
    }

    public List<Bike> getAll() {
        return new ArrayList<Bike>(bikes.values());
    }

    public List<Bike> getAllFromCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException("No valid category");
        ArrayList<Bike> result = new ArrayList<Bike>();
        for (Bike bike : getAll()) {
            if (bike.getCategory().equals(category))
                result.add(bike);
        }
        return result;
    }

}
