package com.gateReceiptServices.request;

import lombok.Data;

@Data
public class UpdateAsnStatusReq {
    Integer asnId;
    Integer reasonId;
    Integer status;
}
