package com.ankur.inventory.controller;

import com.ankur.inventory.domain.*;
import com.ankur.inventory.domain.Info;
import com.ankur.inventory.service.InventoryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static java.net.HttpURLConnection.*;


@RestController
@RequestMapping("/inventory/services")
@Api(value = "/com/ankur/inventory/services", tags = ("Inventory Management"))
@CrossOrigin(allowedHeaders = "*",maxAge = 3600)
public class InventoryController {

    private static final String CLIENT_ID = "client-id";
    private InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }


    @RequestMapping(value = "findByName", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "findByName", notes = "Finds an inventory item by name", nickname = "findByName")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryFindByNameResponse.class)})
    public ResponseEntity<?> postWithRequestBody(@RequestHeader(value = CLIENT_ID) String clientId,
                                                 @Valid @RequestBody InventoryFindByNameRequest request) {
        return inventoryService.findByName(request);
    }



    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "delete", notes = "Removes an item from the Inventory", nickname = "delete")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryDeleteItemResponse.class)})
    public ResponseEntity<?> deleteWithParamVariable(@RequestHeader(value = CLIENT_ID) String clientId,
                                                     @ApiParam(value = "My Id", required = true, example = "3") @PathVariable("id") final int id) {
        return inventoryService.remove(id);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "update", notes = "Updates an existing item from the Inventory", nickname = "update")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryUpdateItemResponse.class)})
    public ResponseEntity<?> putWithRequestBody(@RequestHeader(value = CLIENT_ID) String clientId,
                                                @Valid @RequestBody InventoryUpdateItemRequest request) {
        return inventoryService.update(request);
    }


    @RequestMapping(value = "add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "add", notes = "Adds a new item in the Inventory", nickname = "add")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryAddItemResponse.class)})
    public ResponseEntity<?> postWithRequestBody(@RequestHeader(value = CLIENT_ID) String clientId,
                                                 @Valid @RequestBody InventoryAddItemRequest request) {
        return inventoryService.add(request);
    }


    @RequestMapping(value = "findById", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ApiOperation(value = "findById", notes = "Find a single inventory item by id", nickname = "findById")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryFindByIdResponse.class)})
    public ResponseEntity<?> postWithRequestBody(@RequestHeader(value = CLIENT_ID) String clientId,
                                                 @Valid @RequestBody InventoryFindByIdRequest request) {
        return inventoryService.findById(request);
    }

    @RequestMapping(value = "listall", method = {RequestMethod.GET}, produces = "application/json")
    @ApiOperation(value = "listall", notes = "Get all inventory items", nickname = "listall")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Missing / invalid parameter", response = ServiceErrorResponse.class),
            @ApiResponse(code = 200, message = "Success", response = InventoryListAllResponse.class)})
    public ResponseEntity<?> getWithoutAnyRequestParamOrPathVariable(@RequestHeader(value = CLIENT_ID) String clientId
    ) {
        return inventoryService.listAll();
    }


}

