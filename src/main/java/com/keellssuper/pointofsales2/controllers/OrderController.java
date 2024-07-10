package com.keellssuper.pointofsales2.controllers;

import com.keellssuper.pointofsales2.dto.request.RequestOrderSaveDTO;
import com.keellssuper.pointofsales2.service.OrderService;
import com.keellssuper.pointofsales2.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(
            path = {"/save"}
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
        String id = orderService.addOrder(requestOrderSaveDTO);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",2), HttpStatus.CREATED
        );
        return response;
    }
}
