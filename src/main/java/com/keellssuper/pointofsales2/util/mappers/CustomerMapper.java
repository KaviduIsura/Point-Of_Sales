package com.keellssuper.pointofsales2.util.mappers;

import com.keellssuper.pointofsales2.dto.CustomerDTO;
import com.keellssuper.pointofsales2.dto.response.ItemGetResponsseDTO;
import com.keellssuper.pointofsales2.entities.Customer;
import com.keellssuper.pointofsales2.entities.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    List<CustomerDTO> ListDTOToPage(Page<Customer> customers);
}
