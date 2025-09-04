package com.gateReceiptServices.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_Store")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "StoreId")
    private String storeId;

    @Column(name = "ERPStoreId")
    private String erpStoreId;

    @Column(name = "StoreName")
    private String storeName;

    @Column(name = "StoreAddress")
    private String storeAddress;

    @Column(name = "StoreManagerName")
    private String storeManagerName;

    @Column(name = "ContactNumber")
    private String contactNumber;

    @Column(name = "EmailId")
    private String emailId;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "FreeField1")
    private Integer freeField1;

    @Column(name = "FreeField2")
    private String freeField2;

    @Column(name = "FreeField3")
    private Float freeField3;

    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    @CreatedBy
    @Column(name = "CreatedBy")
    private Integer createdBy;


    @CreatedDate
    @Column(name = "CreatedOn")
    private Date createdOn;

    @LastModifiedBy
    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @LastModifiedDate
    @Column(name = "ModifiedOn")
    private Date modifiedOn;

}


