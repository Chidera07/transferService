/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.controller;

import com.teamapt.transferservice.exception.TransactionError;
import com.teamapt.transferservice.model.Balance;
import com.teamapt.transferservice.model.Transaction;
import com.teamapt.transferservice.model.Transfer;
import com.teamapt.transferservice.repository.BalanceRepository;
import com.teamapt.transferservice.repository.TransactionRepository;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TransferController {
    @Autowired
    BalanceRepository balanceRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    //Make a Transfer
    @PostMapping("/transfer")
    public void transfer(@Valid @RequestBody Transfer transfer){
       List<Transaction> transactions = transactionRepository.findAll();
       if (transactions.size()>0){
           long maxTime=Long.MIN_VALUE;
           Transaction ltrans=null;
           for (Transaction t:transactions){
               if (t.getCreatedAt().getTime()>maxTime) ltrans = t;
           }
           Date lastDate  = ltrans.getCreatedAt();
           log.info("{}", System.currentTimeMillis()-lastDate.getTime());
           if ((System.currentTimeMillis()-lastDate.getTime()) < 60*1000 && transfer.getFrom()==ltrans.getAcc_nr() && transfer.getAmount()==ltrans.getAmount()){
               
               throw new TransactionError("A duplicate transaction is suspected, there must be at least a minute interval between transactions with the same properties");
           }
       }

       Balance balanceF = balanceRepository.getOne(transfer.getFrom());
       
       //Check for suficient balance
       if (balanceF.getBalance()-transfer.getAmount()<0) throw new TransactionError("Insufficient Balance");
       
       balanceF.setBalance(balanceF.getBalance()-transfer.getAmount());

       Balance balanceT = balanceRepository.getOne(transfer.getTo());
       balanceT.setBalance(balanceT.getBalance()+transfer.getAmount());
       
       
       Transaction transaction = new Transaction();
       transaction.setAcc_nr(transfer.getFrom());
       transaction.setAmount(transfer.getAmount());
       
       System.out.println(transaction+" "+balanceF+" "+balanceT);
       transactionRepository.save(transaction);
       balanceRepository.save(balanceF);
       balanceRepository.save(balanceT);
           
    }
}
