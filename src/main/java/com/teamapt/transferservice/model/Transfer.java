/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;


public class Transfer implements Serializable {
    
    @NotNull
    private int from;
    
    @NotNull
    private int to;
    
    @NotNull
    private long amount;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
    
    
}
