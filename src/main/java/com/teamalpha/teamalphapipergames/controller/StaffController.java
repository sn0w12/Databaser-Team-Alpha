
package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Staff;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class StaffController {

    public void createInitialStaffIfNotExists() {
        List<Staff> existingStaff = getAllStaff();

        if (existingStaff == null || existingStaff.isEmpty()) {
            Staff richardHendricks = new Staff(0, "Richard", "Hendricks", "R", "Pied Pipers 123", "68703", "25129", "Italy", "rhendricks@gmail.com");
            Staff bertramGilfoyle = new Staff(0, "Bertram", "Gilfoyle", "B.G", "Pied Pipers 123", "68703", "25129", "Italy", "gaffuso3@baidu.com");
            Staff dineshChugtai = new Staff(0, "Dinesh", "Chugtai", "D.C", "Pied Pipers 123", "68703", "25129", "Italy", "JennieMGrayson@gmail.com");

            save(richardHendricks);
            save(bertramGilfoyle);
            save(dineshChugtai);

            System.out.println("Initial employees have been added to the database.");
        } else {
            System.out.println("Employees already exist in the database.");
        }
    }
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    public boolean save(Staff staff) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(staff);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public List<Staff> getAllStaff() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        List<Staff> staffList = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            staffList = entityManager.createQuery("FROM Staff", Staff.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return staffList;
    }

    public boolean update(Staff staff) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(staff);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public boolean delete(int staffId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Staff staff = entityManager.find(Staff.class, staffId);
            entityManager.remove(staff);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    public static void main(String[] args) {
        StaffController staffController = new StaffController();

        // Skapa initiala anställda om de inte redan finns
        staffController.createInitialStaffIfNotExists();

        // Skapa en ny Staff-post
        Staff newStaff = new Staff();
        newStaff.setFirstName("John");
        newStaff.setLastName("Doe");
        newStaff.setNickname("JD");
        // Fyll i andra attribut för newStaff...

        // Spara den nya Staff-posten i databasen
        boolean saved = staffController.save(newStaff);

        if (saved) {
            System.out.println("Ny Staff-post sparad i databasen!");
        } else {
            System.out.println("Det gick inte att spara ny Staff-post i databasen.");
        }
        // Hämta alla poster från personaltabellen och skriv ut dem i konsolen
        List<Staff> staffList = staffController.getAllStaff();
        if (staffList != null) {
            for (Staff staff : staffList) {
                System.out.println(staff.getFirstName() + " " + staff.getLastName());
            }
        } else {
            System.out.println("Det gick inte att hämta data från personaltabellen.");
        }
    }
}

