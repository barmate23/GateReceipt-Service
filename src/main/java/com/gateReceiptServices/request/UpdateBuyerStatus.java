package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateBuyerStatus {
    private Integer asnHeadId;
    private Integer poHeadId;
    private String modeOfCommunication;
    private boolean isCallBuyer;
}
