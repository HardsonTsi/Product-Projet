package com.hardtech.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

   /* @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
        return args -> List.of("PC", "Mercedes", "Huawei P8", "Iphone 13")
                .forEach(inventoryName -> {
                    Inventory inventory = Inventory.builder()
                            .skuCode(inventoryName)
                            .quantity(new Random().nextInt(0, 200))
                            .build();
                    inventoryRepository.save(inventory);
                });
    }*/


}
