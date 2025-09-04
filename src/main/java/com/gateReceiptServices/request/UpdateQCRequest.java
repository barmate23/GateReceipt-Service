package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQCRequest {
    private Integer qcCertificateId;
    private Boolean isAccepted;
}
