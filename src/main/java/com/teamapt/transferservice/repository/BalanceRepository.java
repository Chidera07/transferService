/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.teamapt.transferservice.model.Balance;
import org.springframework.stereotype.Repository;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer>{
    
}
