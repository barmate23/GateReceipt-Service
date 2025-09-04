package com.gateReceiptServices.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_PartNumberSubModuleMapper")
@Data
public class PartNumberSubModuleMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subModuleId")
    private SubModules subModule;

    @Column(name = "defaultKeyCount")
    private Integer defaultKeyCount;

    @Column(name = "licenseCategory")
    private Integer licenseCategory;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "createdOn")
    private Date createdOn;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    @Column(name = "modifiedOn")
    private Date modifiedOn;
}
