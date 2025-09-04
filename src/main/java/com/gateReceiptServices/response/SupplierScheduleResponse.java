package com.gateReceiptServices.response;

import com.gateReceiptServices.model.ItemSchedule;
import com.gateReceiptServices.model.ItemScheduleSupplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierScheduleResponse {
    private ItemSchedule itemSchedule;
    private List<ItemScheduleSupplier> itemScheduleSupplierList;
}
