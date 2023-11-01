package com.acid.acid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;

@Entity
@Table(name = "outbound_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutboundItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "item_name")
    private String itemName;
    private String vendor;
    @Column(name = "unit_price")
    private double unitPrice;
    private int qty;
    @Column(name = "date_time")
    private String dateTime;
    @Column(name = "cus_id")
    private long customerId;
}
