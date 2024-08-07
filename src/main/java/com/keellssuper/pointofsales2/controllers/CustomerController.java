package com.keellssuper.pointofsales2.controllers;

import com.keellssuper.pointofsales2.dto.CustomerDTO;
import com.keellssuper.pointofsales2.dto.paginated.PaginatedResponseCustomerDTO;
import com.keellssuper.pointofsales2.dto.paginated.PaginatedResponseItemDTO;
import com.keellssuper.pointofsales2.dto.request.CustomerUpdateDTO;
import com.keellssuper.pointofsales2.service.CustomerService;
import com.keellssuper.pointofsales2.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse>saveCustomer(@RequestBody CustomerDTO customerDTO){
    String customer= customerService.saveCustomer(customerDTO);
    return new ResponseEntity<StandardResponse>(
            new StandardResponse(201,"Success",customer),
            HttpStatus.CREATED
    );
    }

    @GetMapping(
            path = "/get-all-customers"
    )
    public ResponseEntity<StandardResponse>getAllCustomer(){
        List<CustomerDTO> allCustomers =  customerService.getAllCustomer();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",allCustomers),
                HttpStatus.OK
        );
    }



    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id")int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",customerDTO),
                HttpStatus.OK
        );
    }

    @PutMapping("/Update")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDTO customerUpdateDTO){
        String update = customerService.updateCustomer(customerUpdateDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",update),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/get-all-customers-by-active-status/{status}"
    )
    public ResponseEntity<StandardResponse>getCustomersByActiveState(@PathVariable(value = "status")boolean activeState){
        List<CustomerDTO> activeCustomers = customerService.getCustomerByActiveState(activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",activeCustomers),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "/delete",
            params = "id"
    )
    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam(value = "id")int id){
        String deleteCustomer= customerService.deleteCustomer(id);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",deleteCustomer),
                HttpStatus.OK
        );

    }

    // Pagination
    @GetMapping(
            path = "/get-all-customers-with-paginated",
            params = {"page","size"}
    )
    public ResponseEntity<StandardResponse> getAllCustomersWithPaginated(
            @RequestParam(value = "page")int page,
            @RequestParam(value = "size")int size
    ){
        PaginatedResponseCustomerDTO paginatedResponseCustomerDTO = customerService.getAllCustomersWithPaginated(page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",paginatedResponseCustomerDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "get-all-customers-by-status-with-paginated",
            params = {"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getCustomersByStatus(
        @RequestParam(value = "activeStatus")boolean activeStatus,
        @RequestParam(value = "page")int page,
        @RequestParam(value = "size")int size
    ){
        PaginatedResponseCustomerDTO paginatedResponseCustomerDTO = customerService.getCustomerByStatusWithPaginated(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"success",paginatedResponseCustomerDTO),
                HttpStatus.OK
        );

    }

//    @GetMapping(
//            path = "get-all-items-by-status",
//            params = {"activeStatus","page","size"}
//    )
//    public ResponseEntity<StandardResponse> getItemBuStatus(
//            @RequestParam(value = "activeStatus") boolean activeStatus,
//            @RequestParam(value = "page") int page,
//            @RequestParam(value = "size") int size
//    ){
//        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveStatusWithPaginated(activeStatus,page,size);
//        return new ResponseEntity<StandardResponse>(
//                new StandardResponse(200,"Success",paginatedResponseItemDTO),
//                HttpStatus.OK
//        );
//    }

}


