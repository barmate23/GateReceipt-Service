package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveQCCertificateRequest {
    Integer poHeadId;
    Integer asnHeadId;
    Integer itemId;
    Integer qcCertificateId;
    Integer qcNumber;
    String qcDate;
}
