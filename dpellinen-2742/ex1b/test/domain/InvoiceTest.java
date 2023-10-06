package domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {

    Invoice invoice1;

    @BeforeEach
    void setUp() {
        this.invoice1 = new Invoice(1, new GDate(), new GDate());
    }

    @Test
    void copyConstructor() {
        Invoice invoice2 = new Invoice(this.invoice1);
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
        assertNotSame(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
        assertNotSame(this.invoice1.getDueDate(), invoice2.getDueDate());
    }

    @Test
    void copy() {
        Invoice invoice2 = this.invoice1.copy();
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
        assertNotSame(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
        assertNotSame(this.invoice1.getDueDate(), invoice2.getDueDate());
    }

//    @Test
//    void addLineItem() {
//    }
//
    @Test
    void total() {
        this.invoice1.addLineItem( new LineItem(1.0, "description1") );
        this.invoice1.addLineItem( new LineItem(2.0, "description2") );
        this.invoice1.addLineItem( new LineItem(3.0, "description3") );
        this.invoice1.addLineItem( new LineItem(4.0, "description4") );

        assertEquals(10.0, this.invoice1.total());

        this.invoice1.removeLineItem(0);
        this.invoice1.removeLineItem(0);
        this.invoice1.removeLineItem(0);
        this.invoice1.removeLineItem(0);
        LineItem removedLineItem = this.invoice1.removeLineItem(0);
        assertNull(removedLineItem);
    }

@Test
void removeLineItem() {
    LineItem lineItem1 = new LineItem(1.0, "description1");

    // test Invoice.removeLineItem(int index)
    this.invoice1.addLineItem(lineItem1);
    LineItem removedLineItem = this.invoice1.removeLineItem(0);
    assertEquals(lineItem1, removedLineItem);
    assertNotSame(lineItem1, removedLineItem);

    // test removing from empty ArrayList
    removedLineItem = this.invoice1.removeLineItem(0);
    assertNull(removedLineItem);

    // test Invoice.removeLineItem(LineItem lineItem)
    this.invoice1.addLineItem(lineItem1);
    removedLineItem = this.invoice1.removeLineItem(lineItem1);
    assertEquals(lineItem1, removedLineItem);
    assertNotSame(lineItem1, removedLineItem);

    // test removing from empty ArrayList
    removedLineItem = this.invoice1.removeLineItem(lineItem1);
    assertNull(removedLineItem);
}

}