package com.gateReceiptServices.response;

import lombok.Data;

import java.util.List;

@Data
public class DockItem {
    private String dockId;
    private List<DockItemResponse> itemResponse;
}
