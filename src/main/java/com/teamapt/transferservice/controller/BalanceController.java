/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.controller;

import com.teamapt.transferservice.model.Balance;
import com.teamapt.transferservice.repository.BalanceRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {
    
    @Autowired
    BalanceRepository balanceRepository;
    
    @GetMapping("/")
    public List<Balance> getAllBalances(){
        return balanceRepository.findAll();
    }
    
    @PostMapping("/")
    public Balance addBalance(@Valid @RequestBody Balance balance){
        return balanceRepository.save(balance);
    }
}
