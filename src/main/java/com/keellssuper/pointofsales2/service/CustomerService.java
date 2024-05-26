package com.keellssuper.pointofsales2.service;

import com.keellssuper.pointofsales2.dto.CustomerDTO;
import com.keellssuper.pointofsales2.dto.request.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomer();


    CustomerDTO getCustomerById(int customerId);

    String updateCustomer(CustomerUpdateDTO customerUpdateDTO);

    List<CustomerDTO> getCustomerByActiveState(boolean activeState);

    String deleteCustomer(int id);
}
