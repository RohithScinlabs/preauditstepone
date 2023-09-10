package com.scinlabs.preauditstepone.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_test3")
public class InvoiceEntity {
    @Id
    @Column(name = "inv_tracking_id")
    private String trackingId;

    public InvoiceEntity(String trackingId) {
        this.trackingId = trackingId;
    }
    public InvoiceEntity() {
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }
}
