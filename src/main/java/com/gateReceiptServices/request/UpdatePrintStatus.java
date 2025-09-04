package com.gateReceiptServices.request;

import lombok.Data;

@Data
public class UpdatePrintStatus {
    private Integer printStatusOf;
    private Integer transportId;
    private Integer asnHeadId;
    private Integer poHeadId;
}
