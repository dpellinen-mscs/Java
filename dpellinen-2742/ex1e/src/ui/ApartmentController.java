package ui;

import domain.Apartment;
import domain.DbContext;
import domain.Invoice;
import domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {
//    private ArrayList<Apartment> apartments= new ArrayList<Apartment>();
 //   private ArrayList<Person> people = new ArrayList<Person>();
//    @FXML
//    private ComboBox aptComboBox;
    @FXML
    private ComboBox<Apartment> aptComboBox;
    @FXML
    private Button saveAptButton;
    @FXML
    private Button viewInvoicesButton;
//    @FXML
//    private ComboBox adminComboBox;
    @FXML
    private ComboBox<Person> adminComboBox;                 //ex1e
//    @FXML
//    private ComboBox tenantComboBox;
    @FXML
    private ComboBox<Person> tenantComboBox;                //ex1e
    @FXML
    private TextField aptNumTextField;
    @FXML
    private TextField squareFeetTextField;
    @FXML
    private TextField bathroomsTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField updatedTextField;
    public ApartmentController() {
//        this.apartments = DbContext.getApartments();
//        //this.people = DbContext.getPeople();
//        this.apartments = DbContext.getApartments();
    }

    @FXML
    protected void initialize() {
        ArrayList<Apartment> apartments = DbContext.getApartments();        //ex1e
        for (Apartment apartment : apartments) {
            aptComboBox.getItems().add(apartment);
        }
        this.aptComboBox.getSelectionModel().selectFirst();
//        Apartment apt = aptComboBox.get(0);

        ArrayList<Person> people = DbContext.getPeople();

        for (Person person : people) {
//          adminComboBox.getItems().add(person.toShortString());
            adminComboBox.getItems().add(person);
        }

        for (Person person : people) {
//            tenantComboBox.getItems().add(person);
            this.tenantComboBox.getItems().add(person);
        }
       this.displayApartment(this.aptComboBox.getSelectionModel().getSelectedItem());
    }
    private void displayApartment (Apartment apartment) {
        this.aptNumTextField.setText(apartment.getApartmentNum());
        this.squareFeetTextField.setText(Integer.toString(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(Integer.toString(apartment.getBathrooms()));
        this.priceTextField.setText(String.format("0.2f", apartment.getPrice()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated().format(formatter));

        int selectedIndex = -1;
        //for (int i = 0; i < this.people.size(); i++) {                            //ex1e
        for (int i = 0; i < this.adminComboBox.getItems().size(); i++) {
           // Person person = this.people.get(i);
             Person person = this.adminComboBox.getItems().get(i);
            if (person.equals(apartment.getAdministrator())) {
                selectedIndex = i;
            }
        }
        this.adminComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
//        for (int i = 0; i < this.people.size(); i++) {
//            Person person = this.people.get(i);
//            if (person.equals(apartment.getTenant())) {
//            }
//            selectedIndex = i;
//        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);
    }



    @FXML
    private void saveAptButtonClicked(ActionEvent actionEvent) {
        //Get Selected Apartment
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();

        //Update apartment fields
        apartment.setApartmentNum(aptNumTextField.getText());
        apartment.setSquareFeet(Integer.parseInt(squareFeetTextField.getText()));
        apartment.setBathrooms(Integer.parseInt(bathroomsTextField.getText()));
        apartment.setPrice(Double.parseDouble(priceTextField.getText()));
        apartment.setUpdated(LocalDateTime.now());

//        int selectedAdminIndex = this.adminComboBox.getSelectionModel().getSelectedIndex();
//        apartment.setAdministrator(people.get(selectedAdminIndex));
//        int selectedTenantIndex = this.tenantComboBox.getSelectionModel().getSelectedIndex();
//        apartment.setTenant(people.get(selectedTenantIndex));
        apartment.setAdministrator(
                this.adminComboBox.getSelectionModel().getSelectedItem());
        apartment.setTenant(
                this.adminComboBox.getSelectionModel().getSelectedItem());

       // Update aptComboBox
        int selectedAptIndex = this.aptComboBox.getSelectionModel().getSelectedIndex();
        this.aptComboBox.getItems().remove(selectedAptIndex);
        this.aptComboBox.getItems().add(selectedAptIndex, apartment);
        this.aptComboBox.getSelectionModel().select(selectedAptIndex);
    }


    @FXML
    private void viewInvoicesButtonClicked(ActionEvent actionEvent) {
//        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//          Apartment apartment = (apartments.get(selectedIndex));
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();
        if (apartment != null) {
            ArrayList<Invoice> invoices = apartment .getInvoices();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvoiceView.fxml"));
                InvoiceController invoiceController = new InvoiceController();
                invoiceController.initData(invoices);
                fxmlLoader.setController(invoiceController);
//            Parent root1 = (Parent) fxmlLoader.load();
                Pane pane = (Pane) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Invoice");
                stage.setScene(new Scene(pane, 380, 400));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void aptComboBoxItemSelected(ActionEvent actionEvent) {
//        int selectedIndex = aptComboBox.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            displayApartment(aptComboBox.getSelectionModel().getSelectedItem());
//        }
        Apartment apartment = aptComboBox.getSelectionModel().getSelectedItem();
        if (apartment != null)
            displayApartment(apartment);
    }
}
