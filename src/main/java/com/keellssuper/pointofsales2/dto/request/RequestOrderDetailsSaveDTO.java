package com.keellssuper.pointofsales2.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestOrderDetailsSaveDTO {

    private String itemName;
    private double qty;
    private double amount;
    private int items;
    private int orders;
}
