package com.keellssuper.pointofsales2.controllers;

import com.keellssuper.pointofsales2.dto.paginated.PaginatedResponseItemDTO;
import com.keellssuper.pointofsales2.dto.request.ItemSaveRequestDTO;
import com.keellssuper.pointofsales2.dto.request.ItemUpdateRequestDTO;
import com.keellssuper.pointofsales2.dto.response.ItemGetResponsseDTO;
import com.keellssuper.pointofsales2.entities.Item;
import com.keellssuper.pointofsales2.service.ItemService;
import com.keellssuper.pointofsales2.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(
            path = "/save"
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequest) {
        String saveItem = itemService.saveItem(itemSaveRequest);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", saveItem),
                HttpStatus.OK
        );

    }

    @GetMapping(
            name = "/get-all-items"
    )
    public ResponseEntity<StandardResponse> getAllItems() {
        List<ItemGetResponsseDTO> itemGetResponsseDTOS = itemService.getAllItems();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemGetResponsseDTOS),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "/get-by-id",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getItemById(@RequestParam(value = "id") int itemId) {
        ItemGetResponsseDTO itemGetResponsseDTO = itemService.getItemById(itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemGetResponsseDTO),
                HttpStatus.OK
        );

    }

    @GetMapping(
            path = "/get-by-name-and-active-state",
            params = "name"
    )
    public ResponseEntity<StandardResponse> getItemByNameAndActiveState(@RequestParam(value = "name") String itemName) {
        ItemGetResponsseDTO itemGetResponsseDTO = itemService.getItemByNameAndActiveState(itemName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemGetResponsseDTO),
                HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "/delete",
            params = "id"
    )
    public ResponseEntity<StandardResponse> deleteItem(@RequestParam(value = "id") int itemId) {
        String deleteItem = itemService.deleteItem(itemId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", deleteItem),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/get-name-with-mapstruct",
            params = "name"
    )
    public ResponseEntity<StandardResponse> getItemByNameAndStatusByMapstruct(@RequestParam(value = "name") String name) {
        ItemGetResponsseDTO itemGetResponsseDTO = itemService.getItemByNameAndActiveStateByMapStruct(name);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", itemGetResponsseDTO),
                HttpStatus.OK
        );
    }

    @PutMapping(
            path = "/update"
    )
    public ResponseEntity<StandardResponse> updateItem(@RequestBody ItemUpdateRequestDTO itemUpdateRequestDTO) {
        String updateItem = itemService.updateItem(itemUpdateRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", updateItem),
                HttpStatus.OK
        );
    }

    // Pagination
    @GetMapping(
            path = "get-all-items-with-paginated",
            params = {"page", "size"}
    )
    public ResponseEntity<StandardResponse> getItemsWithPaginated(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsWithPaginated(page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "get-all-items-by-status",
            params = {"activeStatus", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getItemBuStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getItemByActiveStatusWithPaginated(activeStatus, page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }
}
