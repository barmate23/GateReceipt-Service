package com.gateReceiptServices.response;

import com.gateReceiptServices.model.ASNHead;
import com.gateReceiptServices.model.ASNLine;
import com.gateReceiptServices.model.Organization;
import com.gateReceiptServices.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ASNDetResponse {
    private ASNHead asnHead;
    private List<ASNLine> asnLineList;
    private Organization organization;
    private User buyer;
}
