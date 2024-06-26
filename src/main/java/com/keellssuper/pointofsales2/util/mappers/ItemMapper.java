package com.keellssuper.pointofsales2.util.mappers;

import com.keellssuper.pointofsales2.dto.response.ItemGetResponsseDTO;
import com.keellssuper.pointofsales2.entities.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponsseDTO>entityListToDtoList(List<Item> items);

    //Item ----------------> ResponseDTO

    ItemGetResponsseDTO EntityToDTO( Item item);

   // Page<Item> items   ---------------->   List<ItemGetResponsseDTO>
   List<ItemGetResponsseDTO>ListDTOToPage(Page<Item> items);
}
