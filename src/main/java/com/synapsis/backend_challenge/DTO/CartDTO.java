package com.synapsis.backend_challenge.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class CartDTO {

    private int productId;
    private int customerId;
    private int quantity;
    
}
