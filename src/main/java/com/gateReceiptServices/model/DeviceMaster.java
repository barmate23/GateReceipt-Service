package com.gateReceiptServices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tbl_DeviceMaster")
public class DeviceMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "deviceIp")
    private String deviceIp;

    @Column(name = "deviceName")
    private String deviceName;

    @Column(name = "deviceBrandName")
    private String deviceBrandName;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private SubModules role;

    @Column(name = "devicePort")
    private String devicePort;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "subOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "createdOn")
    private Date createdOn;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    @Column(name = "modifiedOn")
    private Date modifiedOn;

    @Column(name = "freeField1")
    private String freeField1;

    @Column(name = "freeField2")
    private String freeField2;

    @Column(name = "freeField3")
    private Integer freeField3;

    @Column(name = "freeField4")
    private Integer freeField4;
}

