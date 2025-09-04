package com.gateReceiptServices.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_area")
@Data

@EntityListeners(AuditingEntityListener.class)
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @ManyToOne
    @JoinColumn(name = "StoreId")
    private Store store;

    @Column(name = "ErpAreaId")
    private String erpAreaId;

    @Column(name = "AreaId")
    private String areaId;

    @Column(name = "AreaName")
    private String areaName;

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

