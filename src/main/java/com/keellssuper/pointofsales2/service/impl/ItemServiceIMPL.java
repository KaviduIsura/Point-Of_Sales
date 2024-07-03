package com.keellssuper.pointofsales2.service.impl;

import com.keellssuper.pointofsales2.dto.paginated.PaginatedResponseItemDTO;
import com.keellssuper.pointofsales2.dto.request.CustomerUpdateDTO;
import com.keellssuper.pointofsales2.dto.request.ItemSaveRequestDTO;
import com.keellssuper.pointofsales2.dto.request.ItemUpdateRequestDTO;
import com.keellssuper.pointofsales2.dto.response.ItemGetResponsseDTO;
import com.keellssuper.pointofsales2.entities.Customer;
import com.keellssuper.pointofsales2.entities.Item;
import com.keellssuper.pointofsales2.exceptions.DuplicateException;
import com.keellssuper.pointofsales2.exceptions.NotFoundException;
import com.keellssuper.pointofsales2.repo.ItemRepo;
import com.keellssuper.pointofsales2.service.ItemService;
import com.keellssuper.pointofsales2.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        if (!itemRepo.existsById(item.getItemId())) {
            itemRepo.save(item);
            return item.getItemId() + " Successfully Added";
        } else {
            throw new DuplicateException("Item is already Added");
        }

    }

    @Override
    public List<ItemGetResponsseDTO> getAllItems() {
        List<Item> items = itemRepo.findAll();
        if (items.size() > 0) {
            List<ItemGetResponsseDTO> itemGetResponsseDTOS = modelMapper.map(items, new TypeToken<List<ItemGetResponsseDTO>>() {
            }.getType());
            return itemGetResponsseDTOS;
        } else {
            throw new NotFoundException("No Item Found");
        }

    }

    @Override
    public ItemGetResponsseDTO getItemById(int itemId) {
        Item item = itemRepo.getReferenceById(itemId);
        if (itemRepo.existsById(itemId)) {
            ItemGetResponsseDTO itemGetResponsseDTO = modelMapper.map(item, ItemGetResponsseDTO.class);
            return itemGetResponsseDTO;
        } else {
            throw new NotFoundException("No Item Found");
        }
    }

    @Override
    public ItemGetResponsseDTO getItemByNameAndActiveState(String itemName) {
        boolean b = true;
        Item item = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName, b);
        ItemGetResponsseDTO itemGetResponsseDTO = modelMapper.map(item, ItemGetResponsseDTO.class);
        return itemGetResponsseDTO;

    }

    @Override
    public String deleteItem(int itemId) {
        if (itemRepo.existsById(itemId)) {
            itemRepo.deleteById(itemId);
            return "Item deleted !";
        } else {
            throw new NotFoundException("No Item Found");
        }

    }

    @Override
    public ItemGetResponsseDTO getItemByNameAndActiveStateByMapStruct(String name) {

        boolean b = false;
        Item item = itemRepo.findAllByItemNameEqualsAndActiveStateEquals(name, b);
        ItemGetResponsseDTO itemGetResponsseDTO = itemMapper.EntityToDTO(item);
        return itemGetResponsseDTO;
    }

    @Override
    public String updateItem(ItemUpdateRequestDTO itemUpdateRequestDTO) {
        if (itemRepo.existsById(itemUpdateRequestDTO.getItemId())) {
            Item item = itemRepo.getReferenceById(itemUpdateRequestDTO.getItemId());
            modelMapper.map(item, ItemUpdateRequestDTO.class);
            itemRepo.save(item);
            return itemUpdateRequestDTO.getItemName();
        } else {
            throw new NotFoundException("No Item Found");
        }


    }

    // Paginated Item details
    @Override
    public PaginatedResponseItemDTO getAllItemsWithPaginated(int page, int size) {
        Page<Item> items = itemRepo.findAll(PageRequest.of(page, size));
        if (items.getSize() < 1) {
            throw new NotFoundException("No Item Found");
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.ListDTOToPage(items),
                itemRepo.count()
        );

        return paginatedResponseItemDTO;
    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));
        if (items.getSize() < 1) {
            throw new NotFoundException("No Item Found");

        }
        PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                itemMapper.ListDTOToPage(items),
                itemRepo.countAllByActiveStateEquals(activeStatus)

        );

        return paginatedResponseItemDTO;
    }


}
