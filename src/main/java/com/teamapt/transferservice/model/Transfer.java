/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice.model;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;


public class Transfer implements Serializable {
    
    @NotBlank
    private int from;
    
    @NotBlank
    private int to;
    
    @NotBlank
    private long amount;
    
    
}
