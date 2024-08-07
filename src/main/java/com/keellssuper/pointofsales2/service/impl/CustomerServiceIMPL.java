package com.keellssuper.pointofsales2.service.impl;

import com.keellssuper.pointofsales2.dto.CustomerDTO;
import com.keellssuper.pointofsales2.dto.paginated.PaginatedResponseCustomerDTO;
import com.keellssuper.pointofsales2.dto.paginated.PaginatedResponseItemDTO;
import com.keellssuper.pointofsales2.dto.request.CustomerUpdateDTO;
import com.keellssuper.pointofsales2.entities.Customer;
import com.keellssuper.pointofsales2.entities.Item;
import com.keellssuper.pointofsales2.exceptions.DuplicateException;
import com.keellssuper.pointofsales2.exceptions.NotFoundException;
import com.keellssuper.pointofsales2.repo.CustomerRepo;
import com.keellssuper.pointofsales2.service.CustomerService;
import com.keellssuper.pointofsales2.util.mappers.CustomerMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        if (!customerRepo.existsById(customer.getCustomerId())) {
            customerRepo.save(customer);
            return customer.getCustomerName() + "  Customer Saved";
        } else {
            throw new DuplicateException("Already Added");
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        if(customerRepo.count()>0){
            List<Customer> customers = customerRepo.findAll();
            List<CustomerDTO> customerDTOS = modelMapper.map(customers,new TypeToken<List<CustomerDTO>>(){}.getType());
            return customerDTOS;
        }
        else {
            throw new NotFoundException("No Customer Found");
        }

    }

    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getReferenceById(customerId);
            CustomerDTO customerDTO = modelMapper.map(customer,CustomerDTO.class);
            return customerDTO;
        }else {
            throw new NotFoundException("Not found customer!..");
        }
    }

    @Override
    public String updateCustomer(CustomerUpdateDTO customerUpdateDTO) {
        if(customerRepo.existsById(customerUpdateDTO.getCustomerId())){
            Customer customer= customerRepo.getReferenceById(customerUpdateDTO.getCustomerId());
            modelMapper.map(customerUpdateDTO,Customer.class);
            customerRepo.save(customer);
            return customerUpdateDTO.getCustomerName();
        }else {
            throw new NotFoundException("No Customer Found.");
        }
    }

    @Override
    public List<CustomerDTO> getCustomerByActiveState(boolean activeState) {
        if(customerRepo.count()>0){
            List<Customer> activeCustomers= customerRepo.findAllByActiveEquals(activeState);
            List<CustomerDTO> customerDTOList = modelMapper.map(activeCustomers,new TypeToken<List<CustomerDTO>>(){}.getType());
            return customerDTOList;
        }
        else{
            throw new NotFoundException("No Customer Found");
        }

    }

    @Override
    public String deleteCustomer(int id) {
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
            return "Customer Deleted Successfully";
        }else {
            throw new NotFoundException("Something went Wrong");
        }

    }

    // Paginated Customer details
    @Override
    public PaginatedResponseCustomerDTO getAllCustomersWithPaginated(int page, int size) {
        Page<Customer> customers = customerRepo.findAll(PageRequest.of(page, size));
        if(customers.getSize()<1){
            throw new NotFoundException("No Customer Found");
        }
        PaginatedResponseCustomerDTO paginatedResponseCustomerDTO = new PaginatedResponseCustomerDTO(
               customerMapper.ListDTOToPage(customers),
               customerRepo.count()
        );
        return paginatedResponseCustomerDTO;
    }

    @Override
    public PaginatedResponseCustomerDTO getCustomerByStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Customer> customers = customerRepo.findAllByActiveEquals(activeStatus,PageRequest.of(page, size));
        if (customers.getSize()<1){
            throw new NotFoundException("No Customer Found");

        }
        PaginatedResponseCustomerDTO paginatedResponseCustomerDTO = new PaginatedResponseCustomerDTO(
                customerMapper.ListDTOToPage(customers),
                customerRepo.countAllByActiveEquals(activeStatus)
        );
        return paginatedResponseCustomerDTO;
    }



}


