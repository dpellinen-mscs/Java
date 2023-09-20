package domain;

import java.util.ArrayList;

public class Invoice {
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    /**
     *
     * @param status
     * @param invoiceDate
     * @param dueDate
     */
    public Invoice(int status, GDate invoiceDate, GDate dueDate) {
        // TODO - implement Invoice.Invoice
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param invoice
     */
    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate = new GDate(invoice.invoiceDate);
        this.dueDate = new GDate(invoice.dueDate);
    }

    public Invoice copy() {
        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate);
        invoice.invoiceId = this.invoiceId;
        return invoice;
    }

    /**
     *
     * @param lineItem
     */
    public void addLineItem(LineItem lineItem) {
    this.lineItems.add(lineItem);
    }

    /**
     *
     * @param lineItemId
     */
//    public LineItem removeLineItem(int lineItemId) {
//        // TODO - implement Invoice.removeLineItem
//        throw new UnsupportedOperationException();
//    }

    /**
     *
     * @param index
     */
    public LineItem removeLineItem(int index) {
        LineItem lineItem = null;
        if (index >= 0 && index < this.lineItems.size()) {
            lineItem = this.lineItems.get(index);
            this.lineItems.remove(index);
        }
        return lineItem;
    }
//    // test Invoice.removeLineItem(LineItem lineItem)
//        this.invoice1.addLineItem(lineItem1);
//    removedLineItem = this.invoice1.removeLineItem(lineItem1);
//    assertEquals(lineItem1, removedLineItem);
//    assertFalse(lineItem1 == removedLineItem);
//    // test removing from empty ArrayList
//    removedLineItem = this.invoice1.removeLineItem(lineItem1);
//    assertEquals(null, removedLineItem);
//}

    public double total() {
        // TODO - implement Invoice.total
        throw new UnsupportedOperationException();
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public GDate getInvoiceDate() {
        return invoiceDate;
    }

    public GDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
