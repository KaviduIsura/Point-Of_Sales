package com.keellssuper.pointofsales2.dto.paginated;

import com.keellssuper.pointofsales2.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseCustomerDTO {
    List<CustomerDTO> list;
    private long dataCount;
}
