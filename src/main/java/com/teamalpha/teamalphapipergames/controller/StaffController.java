package com.teamalpha.teamalphapipergames.controller;

import com.teamalpha.teamalphapipergames.model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class StaffController {


    private boolean initialStaffCreated = false;

    // Metod för att skapa initial personal om de inte redan existerar
    public void createInitialStaffIfNotExists() {
        if (initialStaffCreated) {
            System.out.println("Initial staff have already been created.");
            return;
        }

        List<Staff> existingStaff = getAllStaff();

        if (existingStaff == null || existingStaff.isEmpty()) {
            Staff[] initialStaffList = {
                    new Staff(0, "Richard", "Hendricks", "R", "Pied Pipers 123", "68703", "25129", "Italy", "rhendricks@gmail.com"),
                    new Staff(0, "Bertram", "Gilfoyle", "B.G", "Pied Pipers 123", "68703", "25129", "Italy", "gaffuso3@baidu.com"),
                    new Staff(0, "Dinesh", "Chugtai", "D.C", "Pied Pipers 123", "68703", "25129", "Italy", "JennieMGrayson@gmail.com")
            };

            Arrays.stream(initialStaffList).forEach(this::save);

            System.out.println("Initial staff have been added to the database.");

            initialStaffCreated = true;
        } else {
            System.out.println("Initial staff have already been created.");
        }

    }

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // Metod för att spara ny personal
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

    // Metod för att hämta alla personal
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

    //Metod för uppdatering av personaldata
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

    // Metod för borttagning av personal
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

    // Metod för att hämta av personal med staffId
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

    public int getStaffIdFromName(String selectedName, List<Staff> staffList) {
        for (Staff staff : staffList) {
            String fullName = staff.getFirstName() + " " + staff.getLastName();
            if (selectedName.equals(fullName)) {
                return staff.getStaffId();
            }
        }
        return -1;
    }

    // Metod för att skapa en TableView med personaldata
    public TableView<Staff> createStaffTableView(List<Staff> staffList) {
        TableView<Staff> staffTableView = new TableView<>();

        if (staffList == null || staffList.isEmpty()) {
            System.out.println("No staff to show.");
            return staffTableView;
        }

        ObservableList<Staff> data = FXCollections.observableArrayList(staffList);

        List<TableColumn<Staff, ?>> columns = List.of(
                createColumn("Staff ID", "staffId"),
                createColumn("First Name", "firstName"),
                createColumn("Last Name", "lastName"),
                createColumn("Nick Name", "nickname"),
                createColumn("Address", "address"),
                createColumn("Zip Code", "zipCode"),
                createColumn("Postal Address", "postalAddress"),
                createColumn("Country", "country"),
                createColumn("Email", "email")
        );

        staffTableView.getColumns().addAll(columns);
        staffTableView.setItems(data);

        return staffTableView;
    }

    // Metod för att skapa TableColumn
    private TableColumn<Staff, String> createColumn(String columnName, String propertyName) {
        TableColumn<Staff, String> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyName));
        return column;
    }

    public TextField createFormattedTextField(String pattern) {
        // Skapar en TextField för inmatning av text
        TextField textField = new TextField();

        // Skapar en textformatter för att tillämpa mönsterbegränsning på textfältet
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            // Kontrollerar om den nya texten matchar det angivna mönstret eller är tom
            if (newText.matches(pattern) || newText.isEmpty()) {
                return change; // Tillåter ändringen om den matchar mönstret eller är tom
            }
            return null; // Annars hindrar ändringen från att ske i textfältet
        });

        // Tillämpar textformattern på textfältet
        textField.setTextFormatter(textFormatter);

        return textField; // Returnerar det konfigurerade textfältet
    }

    public boolean saveStaff(String firstName, String lastName, String nickname, String address, String zipCode, String postalAddress, String country, String email) {
        if (firstName.isEmpty() || lastName.isEmpty() || nickname.isEmpty() || address.isEmpty() || zipCode.isEmpty() || postalAddress.isEmpty() || country.isEmpty() || email.isEmpty()) {
            // Visa ett felmeddelande för användaren om något av de obligatoriska fälten är tomt
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fel");
            alert.setHeaderText(null);
            alert.setContentText("Vänligen fyll i alla obligatoriska fält (Alla fält måste vara ifyllda)!");
            alert.showAndWait();
            return false; // Returnerar falskt om något obligatoriskt fält är tomt
        } else {
            // Alla obligatoriska fält är ifyllda, skapa ett Staff-objekt och spara det
            Staff newStaff = new Staff(0, firstName, lastName, nickname, address, zipCode, postalAddress, country, email);
            save(newStaff);
            return true; // Returnerar sant om personalen sparades
        }
    }


    // Visa en bekräftelsedialog om att personalen har lagts till
    public void displayAddConfirmation() {
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Add Staff Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("New staff has been successfully added!");
        confirmationAlert.showAndWait();
    }

    public boolean updateStaffInformation(int staffId, String newFirstName, String newLastName, String newNickname, String newAddress, String newZipCode, String newPostalAddress, String newCountry, String newEmail) {
        Staff existingStaff = getStaffById(staffId);

        if (existingStaff != null) {
            // Uppdatera endast de fält där det finns ny information
            if (!newFirstName.isEmpty()) {
                existingStaff.setFirstName(newFirstName);
            }
            if (!newLastName.isEmpty()) {
                existingStaff.setLastName(newLastName);
            }
            if (!newNickname.isEmpty()) {
                existingStaff.setNickname(newNickname);
            }
            if (!newAddress.isEmpty()) {
                existingStaff.setAddress(newAddress);
            }
            if (!newZipCode.isEmpty()) {
                existingStaff.setZipCode(newZipCode);
            }
            if (!newPostalAddress.isEmpty()) {
                existingStaff.setPostalAddress(newPostalAddress);
            }
            if (!newCountry.isEmpty()) {
                existingStaff.setCountry(newCountry);
            }
            if (!newEmail.isEmpty()) {
                existingStaff.setEmail(newEmail);
            }

            return update(existingStaff);
        } else {
            // Personalen hittades inte
            return false;
        }
    }

    // Visa en bekräftelsedialog om att informationen har ändrats
    public void displayEditConfirmation() {
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Update Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Staff information has been successfully updated!");
        confirmationAlert.showAndWait();
    }

    // Visa meddelande om misslyckad uppdatering
    public void displayUpdateFailure() {
        Alert failureAlert = new Alert(Alert.AlertType.ERROR);
        failureAlert.setTitle("Update Failure");
        failureAlert.setHeaderText(null);
        failureAlert.setContentText("Failed to update staff information. Please try again!");
        failureAlert.showAndWait();
    }

    // Visa en bekräftelse för användaren efter att en person har tagits bort
    public static void displayDeleteConfirmation() {
        Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
        confirmationAlert.setTitle("Deletion Confirmation");
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Staff member has been successfully deleted!");
        confirmationAlert.showAndWait();
    }

    // Visa ett meddelande om borttagning misslyckades
    public static void displayDeleteFailure() {
        Alert failureAlert = new Alert(Alert.AlertType.ERROR);
        failureAlert.setTitle("Deletion Failure");
        failureAlert.setHeaderText(null);
        failureAlert.setContentText("Failed to delete staff member. Please try again!");
        failureAlert.showAndWait();
    }

    // En metod för att validera e-postadressen och visa ett felmeddelande om den är ogiltig
    public static void validateEmailField(TextField emailField) {
        emailField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                String email = emailField.getText();
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                if (!email.matches(emailRegex)) {
                    // Visa ett felmeddelande om e-postadressen inte är giltig när användaren försöker lämna fältet
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter a valid email address!");
                    alert.showAndWait();
                }
            }
        });
    }
}