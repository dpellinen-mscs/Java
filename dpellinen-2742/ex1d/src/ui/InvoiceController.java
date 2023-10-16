package ui;

import domain.DbContext;
import domain.Invoice;
import domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InvoiceController {

    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();
    @FXML
    private TextField invoiceIdTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField invoiceDateTextField;
    @FXML
    private TextField dueDateTextField;
    @FXML
    private ComboBox invoicesComboBox;
    @FXML
    private ListView lineItemsListView;
    @FXML
    private TextField descriptionTextField;
    @FXML
    private TextField amountTextField;
    @FXML
    private TextField totalTextField;


//    public InvoiceController() {
//        this.invoices = DbContext.getInvoices();
//    }
    public InvoiceController() { }
    public void initData (ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    private void displayInvoice (Invoice invoice) {
        this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(Integer.toString(invoice.getStatus()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));
        this.dueDateTextField.setText(invoice.getDueDate().format(formatter));
    }


    @FXML
    protected void initialize() {
        if (this.invoices.size() > 0)
        for (Invoice invoice : this.invoices) {
            invoicesComboBox.getItems().add(invoice.toShortString());
        }
        invoicesComboBox.getSelectionModel().selectFirst();
        Invoice invoice = this.invoices.get(0);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
    }

    @FXML
    private void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Invoice invoice = this.invoices.get(index);
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
        }
    }

    @FXML
    private void lineItemsListViewClicked(MouseEvent mouseEvent) {
        //1) get ibndex of selected invoice
        //2) get selected invoice from this.invoices
        //3) get lineItems from selected invoice
        //4) get index of selected lineItem
        //5) get selected lineItem
        //6) displayLineItem()
        int index = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(index);
        ArrayList<LineItem> lineItems = invoice.getLineItems();

        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        LineItem lineItem = lineItems.get(lineItemIndex);

        displayLineItem(lineItem);
    }

    private void displayLineItems(Invoice invoice) {
        //1) clear lineItemsListView
        this.lineItemsListView.getItems().clear();
        //2) lineItems = invoice.getLineItems()
        ArrayList<LineItem> lineItems = invoice.getLineItems();
        //3) copy each LineItem to lineItemsListView
        for (LineItem lineItem : lineItems) {
            invoicesComboBox.getItems().add(lineItem.toShortString());
        }
        this.descriptionTextField.setText("");
        this.amountTextField.setText("");
        this.lineItemsListView.getSelectionModel().selectFirst();
        if (lineItems.size() > 0) {
            displayLineItem(lineItems.get(0));
        }

        //4) clear description and amount TextFields
        //5) select first item in lineItemsListView

        //6) if lineItems has an item, displayLineItem()
    }
    private void displayLineItem(LineItem lineItem) {
        //set TextFields using lineItem
    }

    @FXML
    private void saveInvoiceButton(ActionEvent actionEvent) {
        // get index of selected invoice
        //get selected invoice
        // copy from form controls to invoice
        // GDate invoiceDate = new GDate (
        //                  Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
        //remove selected item from invoicesComboBox:      myCombo.getItems().remove(name2);
        // add invoice. toShortString to invoicesComboBox
        // select item in invoicesComboBox

        //get selected invoice
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);

        //copy from form controls to invoice
//        invoice.setStatus(Integer.parseInt(this.statusTextField.getText()));
//        GDate invoiceDate = new GDate(
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(0, 4)),
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(5, 7)),
//                Integer.parseInt(this.invoiceDateTextField.getText().substring(8, 10))
//        );
//        invoice.setInvoiceDate(invoiceDate);
//        GDate dueDate = new GDate(
//                Integer.parseInt(this.dueDateTextField.getText().substring(0, 4)),
//                Integer.parseInt(this.dueDateTextField.getText().substring(5, 7)),
//                Integer.parseInt(this.dueDateTextField.getText().substring(8, 10))
//        );
//        invoice.setDueDate(dueDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy/MM/dd");
        LocalDate date;
        date = LocalDate.parse(this.invoiceDateTextField.getText(), formatter);
        invoice.setInvoiceDate(date.atStartOfDay());
        date = LocalDate.parse(this.dueDateTextField.getText(), formatter);
        invoice.setDueDate(date.atStartOfDay());

        //refresh invoicesComboBox selected item
        this.invoicesComboBox.getItems().remove(invoiceIndex);
        this.invoicesComboBox.getItems().add(invoiceIndex, invoice.toShortString());
        this.invoicesComboBox.getSelectionModel().select(invoiceIndex);
    }

    @FXML
    private void saveLineItemButtonClicked(ActionEvent actionEvent) {
         //get index of selected invoice
        // get selected invoice

        //get index of selected LineItem
        // remove selected LineItem from invoice

        //create new LineItem
        //add LineItem to invoice

        //remove selected LineItem from ListView
        //add new item(string) to ListView
        // select current LineItem in ListView
    }

    @FXML
    private void addLineItemButtonClicked(ActionEvent actionEvent) {
        // get index of selected invoice
        // get selected invoice

        //create new LineItem(0.0, "")
        //add new LineItem to invoice

        //add LineItem shortString to ListView
        //select last item in ListView
        //displayLineItem()
        // set focus to description
    }

    @FXML
    private void deleteLineItemButtonClicked(ActionEvent actionEvent) {
        // clear text from amountTextField and descriptionTextField
        // get index of selected invoice
        // get selected invoice
        // if lineItemIndex >= 0
        //      remove lineItem from invoice
        //      remove item from ListView
        //      select last item in ListView
        //      scroll to last item in ListView
        //      get last lineItem in invoice
        //      displayLineItem()

        this.amountTextField.setText("");
        this.descriptionTextField.setText("");

        // get selected invoice and lineItem index
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        if (invoiceIndex >= 0) {
            Invoice invoice = this.invoices.get(invoiceIndex);
            int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();

            if (lineItemIndex >= 0) {
            // remove linItem from invoice and ListView
                invoice.removeLineItem(lineItemIndex);
                this.lineItemsListView.getItems().remove(lineItemIndex);
                this.lineItemsListView.getSelectionModel().selectLast();
                lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
                if (lineItemIndex >= 0) {
                    this.lineItemsListView.scrollTo(lineItemIndex);
                    LineItem lineItem = invoice.getLineItem(lineItemIndex);
                    displayLineItem(lineItem);
                }
                //display total
                this.totalTextField.setText(String.format("0.2f", invoice.total()));
            }
        }

    }
}
