package com.keellssuper.pointofsales2.entities;

import com.keellssuper.pointofsales2.entities.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Item {
    @Id
    @Column(name = "item_id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @Column(name = "item_name",length = 255, nullable = false)
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "measuring_units", length = 100, nullable = false)
    private MeasuringUnitType measuringUnitType;

    @Column(name = "balance_qty",length = 255, nullable = false)
    private double balanceQty;

    @Column(name = "selling_price",length = 255, nullable = false)
    private double sellingPrice;

    @Column(name = "supplier_price",length = 255, nullable = false)
    private double supplierPrice;

    @Column(name = "active_state",columnDefinition = "TINYINT default 0")
    private boolean activeState;
}
