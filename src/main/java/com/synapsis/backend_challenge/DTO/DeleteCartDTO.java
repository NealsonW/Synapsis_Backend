package com.synapsis.backend_challenge.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Data
public class DeleteCartDTO {
    private int cartId;
    private int productId;
    private int customerId;
}
