package com.gateReceiptServices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_PrintQueueDetail")
public class PrintQueueDetail implements Comparable<PrintQueueDetail>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private DeviceMaster deviceMaster;

    @ManyToOne
    private SubModules subModules;

    @ManyToOne
    private BarcodeMaster barcodeMaster;

    @Column(name = "value")
    private String value;

    @Column(name = "printJobStatus")
    private String printJobStatus;

    @Column(name = "timeStamp")
    private Date timeStamp;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "createdOn")
    private Date createdOn;

    @Column(name = "isDeleted")
    private Boolean isDeleted;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "modifiedBy")
    private Integer modifiedBy;

    @Column(name = "modifiedOn")
    private Date modifiedOn;

    @Column(name = "OrganizationId")
    private Integer organizationId;

    @Column(name = "SubOrganizationId")
    private Integer subOrganizationId;

    @Column(name = "freeField1")
    private String freeField1;

    @Column(name = "freeField2")
    private String freeField2;

    @Column(name = "freeField3")
    private Integer freeField3;

    @Column(name = "freeField4")
    private Integer freeField4;


    @Override
    public int compareTo(PrintQueueDetail other) {
        return this.id.compareTo(other.id);
    }

}


