/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name ="transactions")
@EntityListeners(AuditingEntityListener.class)
public class Transaction implements Serializable { 
    @Id
    private UUID reference = UUID.randomUUID();
    
    @NotNull
    private float amount;
    
    @NotNull
    private int acc_nr;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public UUID getReference() {
        return reference;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getAcc_nr() {
        return acc_nr;
    }

    public void setAcc_nr(int acc_nr) {
        this.acc_nr = acc_nr;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    
    
    
}
