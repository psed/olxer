/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.olxer.persistence;

import com.mycompany.olxer.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author user
 */
public class PersistenceHelper {

    public static void init() {
        EntityManagerFactory factory = null;
        EntityManager manager = null;
        try {
            factory = Persistence.createEntityManagerFactory("pu_local");
            manager = factory.createEntityManager();

            manager.getTransaction().begin();
            List<Customer> resultList = manager.createNamedQuery("Customer.findAll").getResultList();
            for (Customer resultList1 : resultList) {
                System.out.println(resultList1);
            }
            //TODO: Add several blogPosts entities to database using EntityManager
            //TODO: Read all entities from database using EntityManager
        } finally {
            if (manager != null) {
                manager.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }

}
