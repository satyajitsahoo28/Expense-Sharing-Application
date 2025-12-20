package com.expensesharingapplication.controller;

import com.expensesharingapplication.dtos.request.SettlementRequest;
import com.expensesharingapplication.dtos.response.GroupSettlementResponse;
import com.expensesharingapplication.dtos.response.SettlementResponse;
import com.expensesharingapplication.service.SettlementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("settlements")
public class SettlementController {

    private final SettlementService settlementService;


    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    @PostMapping("/settle-up")
    public ResponseEntity<SettlementResponse> settleUp(@RequestBody SettlementRequest settlementRequest) {
        SettlementResponse settlementResponse = settlementService.settleUp(settlementRequest);
        return ResponseEntity.ok(settlementResponse);
    }

    @GetMapping("/by-id/{settlementId}")
    public ResponseEntity<SettlementResponse> getSettlementById(@PathVariable String settlementId) {
        SettlementResponse settlementResponse = settlementService.findById(settlementId);
        return ResponseEntity.ok(settlementResponse);
    }

    @GetMapping("/settlements-in-group/{groupId}")
    public ResponseEntity<GroupSettlementResponse> getSettlementsInGroup(@PathVariable String groupId) {
        return ResponseEntity.ok(settlementService.settleByGroup(groupId));
    }
}
