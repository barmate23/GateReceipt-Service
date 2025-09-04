package com.gateReceiptServices.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDockInfoResponse {
    private String storeName;
    private String storeId;
    private String dockName;
    private String dockId;
}
