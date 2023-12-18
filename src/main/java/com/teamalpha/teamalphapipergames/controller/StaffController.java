package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Staff;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaffController {

  public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

  // CREATE
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
  // READ
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

  // READ 1
  public Staff getStaffById(int id){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      Staff staffToReturn = entityManager.find(Staff.class, id);
      transaction.commit();
      return staffToReturn;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return null;
  }
  // UPDATE
  public boolean update(Staff staff){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.merge(staff);
      transaction.commit();
      return true;
    } catch (Exception e){
      if(transaction != null){
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }
    return false;
  }
  // DELETE
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

  public boolean deleteStaffById(int id) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // Retrieve the Staff entity using EntityManager.find
      Staff staffToDelete = entityManager.find(Staff.class, id);

      // Check if the entity is found
      if (staffToDelete != null) {
        entityManager.remove(staffToDelete);
        transaction.commit();
        return true;
      } else {
        System.out.println("Staff not found with id: " + id);
      }
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
}