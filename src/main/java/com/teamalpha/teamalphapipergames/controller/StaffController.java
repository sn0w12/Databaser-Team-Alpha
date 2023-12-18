
package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Staff;
import javax.persistence.*;
import java.util.List;

public class StaffController {

    private boolean initialStaffCreated = false;

    public void createInitialStaffIfNotExists() {
        if (!initialStaffCreated) {
            List<Staff> existingStaff = getAllStaff();

            if (existingStaff == null || existingStaff.isEmpty()) {
                Staff richardHendricks = new Staff(0, "Richard", "Hendricks", "R", "Pied Pipers 123", "68703", "25129", "Italy", "rhendricks@gmail.com");
                Staff bertramGilfoyle = new Staff(0, "Bertram", "Gilfoyle", "B.G", "Pied Pipers 123", "68703", "25129", "Italy", "gaffuso3@baidu.com");
                Staff dineshChugtai = new Staff(0, "Dinesh", "Chugtai", "D.C", "Pied Pipers 123", "68703", "25129", "Italy", "JennieMGrayson@gmail.com");

                save(richardHendricks);
                save(bertramGilfoyle);
                save(dineshChugtai);

                System.out.println("Initial staff have been added to the database.");

                initialStaffCreated = true;
                // Uppdatera flaggan efter att initial personal skapats
            } else {
                System.out.println("Initial staff have already been created.");
            }
        }
    }


    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    public void save(Staff staff) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(staff);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
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

    public Staff getStaffById(int staffId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        Staff staff = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            staff = entityManager.find(Staff.class, staffId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return staff;
    }
}

