package com.gateReceiptServices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsnScheduleItemResponse {
    Integer itemId;
    String itemCode;
    String itemName;
    String unitOfMeasurement;
    Integer totalRequiredQuantity;
    String month;
    Integer year;
}
