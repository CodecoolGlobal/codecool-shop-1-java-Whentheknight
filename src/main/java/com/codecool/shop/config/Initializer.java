package com.codecool.shop.config;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier garrison = new Supplier("Garrison Corp.", "Mr. Garrison's Corporation supplier of futuristic vehicles");
        supplierDataStore.add(garrison);
        Supplier wacky = new Supplier("Wacky Co.", "Supplier of the wild wacky action bike");
        supplierDataStore.add(wacky);
        Supplier southpark = new Supplier("SouthPark Toys Inc.", "Creator of the best seller toys");
        supplierDataStore.add(southpark);
        Supplier cartman = new Supplier("Cartman Ltd.", "Maker of top hit songs in USA");
        supplierDataStore.add(cartman);



        //setting up a new product category

        ProductCategory toy = new ProductCategory("Toy", "Toy maker", "Toys for kids and adults only");
        productCategoryDataStore.add(toy);
        ProductCategory vehicle = new ProductCategory("Vehicle", "Vehicle", "A vehicle that can get you from A to B (or not)");
        productCategoryDataStore.add(vehicle);
        ProductCategory music = new ProductCategory("Music", "Pan flute/Christian rock", "Music for the whole family (if they are christians)");
        productCategoryDataStore.add(music);


        //setting up products and printing it
        productDataStore.add(new Product("STOP touching me ELMO", 29.97f, "USD", "Elmo says inappropriate catchphrases, the doll also has a toothpaste dispenser", toy, southpark));
        productDataStore.add(new Product("Wild Wacky action bike", 150.99f, "USD", "The bike is require the rider to steer the front and rear wheels simultaneously, making it almost impossible to steer in the right direction. It also sports two more wheels, one that is oblong and makes the bike prone to being slow and rocky while in use while the smaller of the two merely keeps it balanced. It also glows in the dark..", vehicle, wacky));
        productDataStore.add(new Product("Alabama Man", 15.99f, "USD", "He is quick, strong, and active. He can bowl, chew tobacco, and drink alcohol. His action button can be used to hit his wife, a figure sold separately..", toy, southpark));
        productDataStore.add(new Product("Chinpokomon booster pack", 42.99f, "USD", "Chinpokomon booster pack contains multiple collectable chinpokomons. Gotta buy 'em all!", toy, southpark));
        productDataStore.add(new Product("'IT' Garrison's futuristic bike", 1499.69f, "USD", "IT can go up to two hundred miles per hour, gets three hundred miles to the gallon, and is an all-around better mode of travel", vehicle, garrison));
        productDataStore.add(new Product("Faith Plus 1", 18.23f, "USD", "Top hits from the christian rock band called Faith+1.", music, cartman));
        productDataStore.add(new Product("Vientos Del Sur", 10.45f, "USD", "Top hits of Peruvian Pan Flute Music.", music, cartman));
    }
}
