package com.gateReceiptServices.model;

import javax.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "tbl_SubModules")
@Data
public class SubModules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "moduleId")
    private Modules module;

    @Column(name = "subModuleCode", length = 15)
    private String subModuleCode;

    @Column(name = "subModuleName", length = 100)
    private String subModuleName;

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
