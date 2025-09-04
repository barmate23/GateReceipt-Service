package com.gateReceiptServices.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAsnRequest {
    private Integer id;
    private Integer poHeader;
    private Integer supplier;
    private String asnNumber;
    private Integer statusId;
    private List<UpdateAsnLineRequest> asnLineList;
}
