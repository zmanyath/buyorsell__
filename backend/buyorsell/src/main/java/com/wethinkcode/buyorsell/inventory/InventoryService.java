package com.wethinkcode.buyorsell.inventory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private InventoryRepo inventoryRepo;

    @Autowired
    public InventoryService(InventoryRepo inventoryRepo) {
        super();
        this.inventoryRepo = inventoryRepo;
    }

    public Inventory addToInventory(Inventory inventory) {
        return inventoryRepo.save(inventory);
    }

    public Inventory getProductInventory(int id) {
        return inventoryRepo.getById(id);
    }

    public List<Inventory> listInventories() {
        List<Inventory> inventories = new ArrayList<Inventory>();
        for(Inventory inventory : inventoryRepo.findAll()) {
            inventories.add(inventory);
        }
        return inventories;
    }

    public void removeInventory(int id) {
        inventoryRepo.deleteById(id);
    }
    
}