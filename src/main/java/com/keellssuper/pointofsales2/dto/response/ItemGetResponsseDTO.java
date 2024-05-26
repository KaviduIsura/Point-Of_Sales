package com.keellssuper.pointofsales2.dto.response;

import com.keellssuper.pointofsales2.entities.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemGetResponsseDTO {
    private int itemId;
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private double balanceQty;
    private double sellingPrice;
    private double supplierPrice;
    private boolean activeState;

}
