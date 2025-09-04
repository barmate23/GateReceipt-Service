package com.gateReceiptServices.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Data
@Table(name = "tbl_PurchaseOrderHead")
public class PurchaseOrderHead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "CinBarcodeNumber")
    private String cinBarcodeNumber;

    @Column(name = "PurchaseOrderID")
    private Integer purchaseOrderId;

    @Column(name = "PurchaseOrderNumber")
    private String purchaseOrderNumber;

    @Column(name = "PurchaseOrderDate")
    private Date purchaseOrderDate;

    @Column(name = "SupplierId")
    private Integer supplierId;

    @Column(name = "DeliveryType")
    private String deliveryType;

    @Column(name = "SupplierName")
    private String supplierName;

    @Column(name = "DeliverByDate")
    private Date deliverByDate;

    @Column(name = "StartDate")
    private Date startDate;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "MobileNumber")
    private String mobileNumber;

    @ManyToOne
    @JoinColumn(name = "StatusId")
    private PurchaseStatus status;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    //Add new Column
    @Column(name = "IsCallBuyer")
    private Boolean isCallBuyer;

    //Add new Column
    @Column(name = "IsPrint")
    private Boolean isPrint;

    //add new column
    @Column(name = "actualArrivalDate")
    private Date actualArrivalDate;

    //add new column
    @Column(name = "actualArrivalTime")
    private Time actualArrivalTime;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @Column(name = "CreatedOn")
    private Timestamp createdOn;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "ModifiedOn")
    private Timestamp modifiedOn;

}
