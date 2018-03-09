/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.controller;

import com.teamapt.transferservice.model.Transfer;
import com.teamapt.transferservice.repository.BalanceRepository;
import com.teamapt.transferservice.repository.TransactionRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/transfer")
public class TransferController {
    @Autowired
    BalanceRepository balanceRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
    //Make a Transfer
    @PostMapping("/api/transfer")
    public void transfer(@Valid @RequestBody Transfer transfer){
//       Date lastDate  = transactionRepository.find
    }
}
