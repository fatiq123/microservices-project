package com.invoice.service.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "INVOICE", schema = "invoice_db")
public class Invoice {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "invoice_id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "product_ids")
    private String productIds;

    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

}
