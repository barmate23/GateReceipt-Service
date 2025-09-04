package com.gateReceiptServices.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_CodeMapper")
public class CodeMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "OrganizationId", length = 6)
    private Integer organizationId;

    @Column(name = "SubOrganizationId", length = 6)
    private Integer subOrganizationId;

    @Column(name = "PoNumber", length = 20)
    private String poNumber;

    @Column(name = "ASNNumber", length = 20)
    private String asnNumber;

    @Column(name = "InvoiceNo", length = 20)
    private Long invoiceNo;

    @Column(name = "CinBarcodeNumber")
    private String cinBarcodeNumber;

    @Column(name = "CrrBarcodeNumber")
    private Integer crrBarcodeNumber;

    @Column(name = "AcceptedRejectedGRRCode", length = 30)
    private String acceptedRejectedGRRCode;

    @Column(name = "IsAcceptedRejected")
    private Boolean isAcceptedRejected;

    @Column(name = "isAsn")
    private Boolean isAsn;

    @Column(name = "GRNNumber", length = 20)
    private Long grnNumber;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @Column(name = "CreatedBy", length = 20)
    private Integer createdBy;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "ModifiedBy", length = 20)
    private Integer modifiedBy;

    @Column(name = "ModifiedOn")
    private Date modifiedOn;
}