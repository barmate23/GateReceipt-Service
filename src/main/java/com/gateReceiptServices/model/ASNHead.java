package com.gateReceiptServices.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_ASNHead")
public class ASNHead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "requiredOnDate")
    private Date requiredOnDate;

    //add new column
    @Column(name = "actualArrivalDate")
    private Date actualArrivalDate;

    //add new column
    @Column(name = "actualArrivalTime")
    private Time actualArrivalTime;

    @Column(name = "deliveryTime")
    private LocalTime deliveryTime;

    @ManyToOne
    @JoinColumn(name = "purchaseOrderHeadId", referencedColumnName = "id")
    private PurchaseOrderHead purchaseOrderHead;

    @Column(name = "AsnNumber")
    private String asnNumber;

    @ManyToOne
    @JoinColumn(name = "supplierId", referencedColumnName = "id")
    private Supplier supplier;

    @Column(name = "CinBarcodeNumber")
    private String cinBarcodeNumber;

    @Column(name = "CrrBarcodeNumber")
    private String crrBarcodeNumber;

    @ManyToOne
    @JoinColumn(name = "PurchaseStatusId", referencedColumnName = "id")
    private PurchaseStatus purchaseStatus;

    //Add new Column
    @Column(name = "IsCallBuyer")
    private Boolean isCallBuyer;

    //Add new Column
    @Column(name = "IsPrint")
    private Boolean isPrint;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "ModifiedOn")
    private Date modifiedOn;
}
