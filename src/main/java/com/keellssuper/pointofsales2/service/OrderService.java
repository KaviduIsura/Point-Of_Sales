package com.keellssuper.pointofsales2.service;

import com.keellssuper.pointofsales2.dto.request.RequestOrderSaveDTO;

public interface OrderService {

    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);
}
