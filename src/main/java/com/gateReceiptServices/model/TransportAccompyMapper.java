package com.gateReceiptServices.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tbl_transportAccompyMapper")
public class TransportAccompyMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "organizationId")
    private Integer organizationId;

    @Column(name = "subOrganizationId")
    private Integer subOrganizationId;

    @ManyToOne
    @JoinColumn(name = "transportId", referencedColumnName = "id")
    private Transport transport;

    @Column(name = "personName", length = 30)
    private String personName;

    @Column(name = "mobile")
    private String mobile;

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
