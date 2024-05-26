package com.keellssuper.pointofsales2.service;

import com.keellssuper.pointofsales2.dto.request.ItemSaveRequestDTO;
import com.keellssuper.pointofsales2.dto.request.ItemUpdateRequestDTO;
import com.keellssuper.pointofsales2.dto.response.ItemGetResponsseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequest);

    List<ItemGetResponsseDTO> getAllItems();

    ItemGetResponsseDTO getItemById(int itemId);

    ItemGetResponsseDTO getItemByNameAndActiveState(String itemName);

    String deleteItem(int itemId);


    ItemGetResponsseDTO getItemByNameAndActiveStateByMapStruct(String name);


    String updateItem(ItemUpdateRequestDTO itemUpdateRequestDTO);
}
