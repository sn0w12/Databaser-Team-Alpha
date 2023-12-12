package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Staff;
import com.teamalpha.teamalphapipergames.model.Team;
import com.teamalpha.teamalphapipergames.model.Game;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StaffController {

  public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

  // CREATE
  public boolean save(Object staff) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      entityManager.persist(staff);
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
  // READ
  public List<Staff> getAll(boolean printOut) {
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    List<Staff> staffListToReturn = new ArrayList<>();
    List<String> staffNamesToReturn = new ArrayList<>();

    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      TypedQuery<Staff> resultList = entityManager.createQuery("FROM Staff", Staff.class);
      staffListToReturn.addAll(resultList.getResultList());

      for (Staff staff : staffListToReturn) {
        staffNamesToReturn.add(staff.getFirstName() + " " + staff.getLastName());
      }

      transaction.commit();

      if (printOut) {
        for (Staff staff : staffListToReturn) {
          System.out.println(staff.getId() + ". " + staff.getFirstName() + " " + staff.getLastName());
        }
      }

      return staffListToReturn;
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    } finally {
      entityManager.close();
    }

    return Collections.emptyList();
  }


  // OLD
//  public List<Staff> getAll(boolean printOut){
//    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
//    EntityTransaction transaction = null;
//    List<Staff> staffListToReturn = new ArrayList<>();
//    try {
//      transaction = entityManager.getTransaction();
//      transaction.begin();
//      TypedQuery<Staff> resultList = entityManager.createQuery("FROM Staff", Staff.class);
//      staffListToReturn.addAll(resultList.getResultList());
//      transaction.commit();
//      if(printOut){
//        for (Staff staff :
//            staffListToReturn) {
//          System.out.println(staff.getId() + ". " + staff.getFirstName() + " " + staff.getLastName());
//        }
//      }
//      return staffListToReturn;
//    } catch (Exception e){
//      if(transaction != null){
//        transaction.rollback();
//      }
//      e.printStackTrace();
//    } finally {
//      entityManager.close();
//    }
//    return null;
//  }
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
  public boolean updateStaff(Staff staff){
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
  public boolean deleteStaff(Staff staff){
    EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
    EntityTransaction transaction = null;
    try {
      transaction = entityManager.getTransaction();
      transaction.begin();
      // If the entity is attached then remove customer, else merge(attach/update) entity and then remove
      entityManager.remove(entityManager.contains(staff) ? staff :entityManager.merge(staff));
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
