package com.keellssuper.pointofsales2.dto.paginated;

import com.keellssuper.pointofsales2.dto.response.ItemGetResponsseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    List<ItemGetResponsseDTO> list;
    private long dataCount;

}
