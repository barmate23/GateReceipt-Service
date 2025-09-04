package com.gateReceiptServices.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_LicenseLine")
@Data
public class LicenseLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "keyCount")
    private Integer keyCount;

    @ManyToOne
    @JoinColumn(name = "part_number_sub_module_mapper_id")
    private PartNumberSubModuleMapper partNumberSubModuleMapper;

    @Column(name = "organizationId")
    private Integer organizationId;

    @Column(name = "subOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "reasonFileId")
    private Integer reasonFileId;

    @Column(name = "superAdminReason")
    private String superAdminReason;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "isModuleSelected")
    private Boolean isModuleSelected;

    @Column(name = "isApproved")
    private Integer isApproved;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "CreatedOn")
    private Date createdOn;

    @Column(name = "ModifiedBy")
    private Integer modifiedBy;

    @Column(name = "ModifiedOn")
    private Date modifiedOn;


}
