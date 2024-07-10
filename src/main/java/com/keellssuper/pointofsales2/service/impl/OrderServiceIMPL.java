package com.keellssuper.pointofsales2.service.impl;

import com.keellssuper.pointofsales2.dto.CustomerDTO;
import com.keellssuper.pointofsales2.dto.request.RequestOrderSaveDTO;
import com.keellssuper.pointofsales2.entities.Customer;
import com.keellssuper.pointofsales2.entities.Order;
import com.keellssuper.pointofsales2.entities.OrderDetails;
import com.keellssuper.pointofsales2.repo.CustomerRepo;
import com.keellssuper.pointofsales2.repo.ItemRepo;
import com.keellssuper.pointofsales2.repo.OrderDetailsRepo;
import com.keellssuper.pointofsales2.repo.OrderRepo;
import com.keellssuper.pointofsales2.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
          customerRepo.getById(requestOrderSaveDTO.getCustomers()),
          requestOrderSaveDTO.getDate(),
          requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);
        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.map(requestOrderSaveDTO.getOrderDetails(),new TypeToken<List<OrderDetails>>(){}.getType());

            for(int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }

            if(orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }
}
