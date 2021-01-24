package com.snnlab.springclouddataflowbatch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  SnnLabInfoDTO implements Serializable {

    private String labId;
    private BigDecimal labAmount;
    private String labCurrency;
}
