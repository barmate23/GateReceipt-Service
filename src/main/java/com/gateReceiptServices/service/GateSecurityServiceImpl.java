package com.gateReceiptServices.service;

import com.gateReceiptServices.configuration.LoginUser;
import com.gateReceiptServices.model.*;
import com.gateReceiptServices.request.AccompaniedByRequest;
import com.gateReceiptServices.request.UpdateBuyerStatus;
import com.gateReceiptServices.request.UpdateItemRequest;
import com.gateReceiptServices.request.UpdateTransportRequest;
import com.gateReceiptServices.response.BaseResponse;
import com.gateReceiptServices.response.POASNLineResponse;
import com.gateReceiptServices.repository.*;
import com.gateReceiptServices.utils.APIConstants;
import com.gateReceiptServices.utils.GlobalMessages;
import com.gateReceiptServices.utils.ServiceConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.ParseException;

@Service
@Slf4j
public class GateSecurityServiceImpl implements GateSecurityService {
    @Autowired
    AsnHeadRepository asnHeadRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransportRepository transportDetailsRepository;
    @Autowired
    TransportAccompanyRepository transportAccompanyRepository;
    @Autowired
    PurchaseStatusRepository purchaseStatusRepository;

    @Autowired
    PurchaseOrderHeadRepository purchaseOrderHeadRepository;
    @Autowired
    AsnLineRepository asnLineRepository;
    @Autowired
    PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    private LoginUser loginUser;

    private static final String DEFAULT_TIME_FORMAT_PATTERN = "HH:mm";


    @Override
    public BaseResponse<POASNLineResponse> getAsnPODetails(String asnNumber, String purchaseOrderNumber) {
        long startTime = System.currentTimeMillis();
        log.info(APIConstants.GET_ASN_PO_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET ASN PO ITEM DETAILS STARTED ");
        BaseResponse<POASNLineResponse> baseResponse = new BaseResponse<>();
        try {
            POASNLineResponse poasnLineResponse = new POASNLineResponse();
            User currentUser = userRepository.findById(loginUser.getUserId()).get();
            if (!StringUtils.isEmpty(asnNumber)) {
                log.info(APIConstants.GET_ASN_PO_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), "ASN Number: " + asnNumber);
                ASNHead asnHead = null;
                if (currentUser.getModuleUserLicenceKey().getLicenceLine().getPartNumberSubModuleMapper().getSubModule().getSubModuleCode().equals("SEOF")) {
                    asnHead = asnHeadRepository.findByAsnNumberAndSubOrganizationIdAndIsDeletedAndPurchaseStatusStatusName(asnNumber, loginUser.getSubOrgId(), false, "InPipeline");
                } else if (currentUser.getModuleUserLicenceKey().getLicenceLine().getPartNumberSubModuleMapper().getSubModule().getSubModuleCode().equals("MACL")) {
                    asnHead = asnHeadRepository.findByAsnNumberAndSubOrganizationIdAndIsDeletedAndPurchaseStatusStatusName(asnNumber, loginUser.getSubOrgId(), false, APIConstants.RECEIVED);
                }
                if(asnHead != null) {
                    List<ASNLine> asnLineList = asnLineRepository.findBySubOrganizationIdAndAsnHeadIdIdAndIsDeleted(loginUser.getSubOrgId(), asnHead.getId(), false);
                    User buyer = userRepository.findById(asnHead.getCreatedBy()).get();
                    poasnLineResponse.setBuyer(buyer);
                    User supplier = userRepository.findByIsDeletedAndSupplierIdAndSubOrganizationId(false, asnHead.getPurchaseOrderHead().getSupplierId(), loginUser.getSubOrgId());
                    poasnLineResponse.setSupplierUserName(supplier.getFirstName() + " " + supplier.getLastName());
                    poasnLineResponse.setAsnHead(asnHead);
                    poasnLineResponse.setSupplier(asnHead.getSupplier());
                    poasnLineResponse.setAsnLineList(asnLineList);
                    poasnLineResponse.setUserName(currentUser.getFirstName() + " " + currentUser.getLastName());
                }
            } else if (!StringUtils.isEmpty(purchaseOrderNumber)) {
                log.info(APIConstants.GET_ASN_PO_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), "PO Number: " + purchaseOrderNumber);
                PurchaseOrderHead purchaseOrderHead = null;
                if (currentUser.getModuleUserLicenceKey().getLicenceLine().getPartNumberSubModuleMapper().getSubModule().getSubModuleCode().equals("SEOF")) {
                    purchaseOrderHead = purchaseOrderHeadRepository.findBySubOrganizationIdAndPurchaseOrderNumberAndIsDeletedAndStatusStatusName(loginUser.getSubOrgId(), purchaseOrderNumber, false, "InPipeline");
                } else if (currentUser.getModuleUserLicenceKey().getLicenceLine().getPartNumberSubModuleMapper().getSubModule().getSubModuleCode().equals("MACL")) {
                    purchaseOrderHead = purchaseOrderHeadRepository.findBySubOrganizationIdAndPurchaseOrderNumberAndIsDeletedAndStatusStatusName(loginUser.getSubOrgId(), purchaseOrderNumber,  false, APIConstants.RECEIVED);
                }
                if(purchaseOrderHead != null){
                    List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findBySubOrganizationIdAndIsDeletedAndPurchaseOrderHeadId(loginUser.getSubOrgId(), false, purchaseOrderHead.getId());
                    User supplier = userRepository.findByIsDeletedAndSupplierIdAndSubOrganizationId(false, purchaseOrderHead.getSupplierId(), loginUser.getSubOrgId());
                    User buyer = userRepository.findById(purchaseOrderHead.getCreatedBy()).get();
                    poasnLineResponse.setSupplierUserName(supplier.getFirstName() + " " + supplier.getLastName());
                    poasnLineResponse.setPurchaseOrderHead(purchaseOrderHead);
                    poasnLineResponse.setSupplier(supplierRepository.findByIsDeletedAndId(false, purchaseOrderHead.getSupplierId()));
                    poasnLineResponse.setBuyer(buyer);
                    poasnLineResponse.setPurchaseOrderLineList(purchaseOrderLineList);
                    poasnLineResponse.setUserName(currentUser.getFirstName() + " " + currentUser.getLastName());
                }
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100001);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(Arrays.asList(poasnLineResponse));
            log.info(APIConstants.GET_ASN_PO_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED GET ASN PO ITEM DETAILS ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100002);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.GET_ASN_PO_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING ASN PO ITEM DETAILS LIST END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.GET_ASN_PO_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET ASN PO ITEM DETAILS LIST END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<Transport> getTransportDetails(Integer asnHeadId, Integer poHeadId) {
        log.info(APIConstants.GET_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET TRANSPORT DETAILS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<Transport> baseResponse = new BaseResponse<>();
        try {
            Transport transport = null;
            if (asnHeadId != null && asnHeadId > 0) {
                log.info(APIConstants.GET_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET ASN HEAD :: " + asnHeadId);
                transport = transportDetailsRepository.findByIsDeletedAndSubOrganizationIdAndAsnHeadId(false, loginUser.getSubOrgId(), asnHeadId);
            } else if (poHeadId != null && poHeadId > 0) {
                log.info(APIConstants.GET_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), "PO HEAD ID :: " + poHeadId);
                transport = transportDetailsRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(false, loginUser.getSubOrgId(), poHeadId);
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100003);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(Arrays.asList(transport));
            log.info(APIConstants.GET_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED GET TRANSPORT DETAILS ");
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.GET_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING TRANSPORT DETAILS EXECUTED TIME :: " + (endTime - startTime));
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100004);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setData(null);
            baseResponse.setStatus(errorMessage.getStatus());
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.error(APIConstants.GET_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET TRANSPORT DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<String> updateItemDetails(List<UpdateItemRequest> updateItemRequestList) {
        log.info(APIConstants.UPDATE_ITEM_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE ITEM DETAILS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.UPDATE_ITEM_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATING ITEM DETAILS LIST ");
            for (UpdateItemRequest updateItemRequest : updateItemRequestList) {
                if (updateItemRequest.getAsnLineId() != null && updateItemRequest.getAsnLineId() > 0) {
                    ASNLine asnLine = asnLineRepository.findByIdAndSubOrganizationIdAndIsDeleted(updateItemRequest.getAsnLineId(), loginUser.getSubOrgId(), false);
                    asnLine.setInvoiceQuantity(updateItemRequest.getInvoiceQuantity());
                    asnLine.setModifiedBy(loginUser.getUserId());
                    asnLine.setModifiedOn(new Date());
                    ASNHead asnHead = asnLine.getAsnHeadId();
                    asnHead.setActualArrivalDate(new Date());
                    asnHead.setActualArrivalTime(new Time(System.currentTimeMillis()));
                    asnHead.setPurchaseStatus(purchaseStatusRepository.findByStatusNameAndStatusType(APIConstants.RECEIVED, "ASN Head"));
                    asnHeadRepository.save(asnHead);
                    asnLineRepository.save(asnLine);
                } else if (updateItemRequest.getPoLineId() != null && updateItemRequest.getPoLineId() > 0) {
                    PurchaseOrderLine purchaseOrderLine = purchaseOrderLineRepository.findBySubOrganizationIdAndIsDeletedAndId(loginUser.getSubOrgId(), false, updateItemRequest.getPoLineId());
                    purchaseOrderLine.setInvoiceQuantity(updateItemRequest.getInvoiceQuantity());
                    purchaseOrderLine.setModifiedBy(loginUser.getUserId());
                    purchaseOrderLine.setModifiedOn(new Timestamp(System.currentTimeMillis()));
                    PurchaseOrderHead purchaseOrderHead = purchaseOrderLine.getPurchaseOrderHead();
                    purchaseOrderHead.setActualArrivalDate(new Date());
                    purchaseOrderHead.setActualArrivalTime(new Time(System.currentTimeMillis()));
                    purchaseOrderHead.setStatus(purchaseStatusRepository.findByStatusNameAndStatusType(APIConstants.RECEIVED, "PO Head"));
                    purchaseOrderHeadRepository.save(purchaseOrderHead);
                    purchaseOrderLineRepository.save(purchaseOrderLine);
                }
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100005);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_ITEM_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED UPDATED ITEM DETAILS ");
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.UPDATE_ITEM_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE UPDATING ITEM DETAILS EXECUTED TIME :: " + (endTime - startTime));
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100006);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_ITEM_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), "UPDATE ITEM DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<String> updateTransport(UpdateTransportRequest updateTransport) {
        log.info(APIConstants.UPDATE_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE TRANSPORT DETAILS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.UPDATE_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE TRANSPORT DETAILS  ");
            baseResponse = updateTransDet(updateTransport);

        } catch (Exception e) {
            log.info(APIConstants.UPDATE_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " FAILED TO UPDATE TRANSPORT DETAILS ");
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100008);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_TRANSPORT_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), "UPDATED TRANSPORT DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<String> updateBuyerStatus(UpdateBuyerStatus updateBuyerStatus) {
        log.info(APIConstants.UPDATE_BUYER_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE CALL BUYER STATUS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            CallBuyerAdt callBuyerAdt = new CallBuyerAdt();
            if (updateBuyerStatus.getAsnHeadId() != null && updateBuyerStatus.getAsnHeadId() > 0) {
                log.info(APIConstants.UPDATE_BUYER_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE CALL BUYER STATUS ASN HEAD ID : " + updateBuyerStatus.getAsnHeadId());
                ASNHead asnHead = asnHeadRepository.findByIdAndSubOrganizationIdAndIsDeleted(updateBuyerStatus.getAsnHeadId(), loginUser.getSubOrgId(), false);
                asnHead.setIsCallBuyer(true);
                asnHead.setModifiedBy(loginUser.getUserId());
                asnHead.setModifiedOn(new Date());
                callBuyerAdt.setAsnHead(asnHead);
                asnHeadRepository.save(asnHead);
            } else if (updateBuyerStatus.getPoHeadId() != null && updateBuyerStatus.getPoHeadId() > 0) {
                log.info(APIConstants.UPDATE_BUYER_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE CALL BUYER STATUS PO HEAD ID : " + updateBuyerStatus.getPoHeadId());
                PurchaseOrderHead purchaseOrderHead = purchaseOrderHeadRepository.findByIdAndIsDeleted(updateBuyerStatus.getPoHeadId(), false);
                purchaseOrderHead.setIsCallBuyer(true);
                purchaseOrderHead.setModifiedBy(loginUser.getUserId());
                purchaseOrderHead.setModifiedOn(new Timestamp(System.currentTimeMillis()));
                callBuyerAdt.setPurchaseOrderHead(purchaseOrderHead);
                purchaseOrderHeadRepository.save(purchaseOrderHead);
            }
            callBuyerAdt.setIsDeleted(false);
            callBuyerAdt.setCreatedBy(loginUser.getUserId());
            callBuyerAdt.setCreatedOn(new Date());
            callBuyerAdt.setModifiedBy(loginUser.getUserId());
            callBuyerAdt.setModifiedOn(new Date());
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100009);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_BUYER_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY UPDATED CALL BUYER STATUS ");
        } catch (Exception e) {
            log.info(APIConstants.UPDATE_BUYER_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " FAILED TO UPDATE TRANSPORT DETAILS ");
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100010);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setStatus(500);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_BUYER_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), "UPDATED TRANSPORT DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    private BaseResponse<String> updateTransDet(UpdateTransportRequest updateTransport) throws ParseException {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        Transport transportDetails = transportDetailsRepository.findByIdAndSubOrganizationIdAndIsDeleted(updateTransport.getTransporterId(), loginUser.getSubOrgId(), false);
        transportDetails.setOrganizationId(loginUser.getOrgId());
        transportDetails.setSubOrganizationId(loginUser.getSubOrgId());
        transportDetails.setShipMode(updateTransport.getShipMode());
        transportDetails.setShipThrough(updateTransport.getShipThrough());
        transportDetails.setContactPerson(updateTransport.getContactName());
        transportDetails.setTransporter(updateTransport.getTransporterName());
        transportDetails.setMobile(updateTransport.getMobileNo());
        transportDetails.setLandline(updateTransport.getLandlineNo());
        transportDetails.setLorryReceipt(updateTransport.getLorryReceiptNumber());
        transportDetails.setDispatchDate(stringToDate(updateTransport.getDispatchDate()));
        transportDetails.setDispatchTime(stringToSqlTime(updateTransport.getDispatchTime()));
        transportDetails.setExpectedArrivalDate(stringToDate(updateTransport.getExpectedArrivalDate()));
        transportDetails.setExpectedArrivalTime(stringToSqlTime(updateTransport.getExpectedArrivalTime()));
        transportDetails.setVehicleType(updateTransport.getVehicleType());
        transportDetails.setVehicleNumber(updateTransport.getVehicleNumber());
        transportDetails.setVehicleWeight(updateTransport.getVehicleWeight());
        transportDetails.setPucCertificateNumber(updateTransport.getPucCertificateNumber());
        transportDetails.setPucCertificateValidTill(stringToDate(updateTransport.getPucCertificateNumberValidTill()));
        transportDetails.setPucCenter(updateTransport.getPucCenter());
        transportDetails.setPucCenterId(updateTransport.getPucCenterId());
        transportDetails.setDriver(updateTransport.getDriver());
        transportDetails.setLicenseNumber(updateTransport.getLicenceNumber());
        transportDetails.setLicenseNumberValidTill(stringToDate(updateTransport.getLicenceNumberValidTill()));
        transportDetails.setLicenseType(updateTransport.getLicenceType());
        transportDetails.setModifiedBy(loginUser.getUserId());
        transportDetails.setModifiedOn(new Date());
        transportDetailsRepository.save(transportDetails);
        List<TransportAccompyMapper> transportAccompyMapperList = new ArrayList<>();
        for (AccompaniedByRequest accompaniedByRequest : updateTransport.getAccompaniedByList()) {
            TransportAccompyMapper transportAccompyMapper = new TransportAccompyMapper();
            transportAccompyMapper.setOrganizationId(loginUser.getOrgId());
            transportAccompyMapper.setSubOrganizationId(loginUser.getSubOrgId());
            transportAccompyMapper.setTransport(transportDetails);
            transportAccompyMapper.setPersonName(accompaniedByRequest.getPersonName());
            transportAccompyMapper.setMobile(accompaniedByRequest.getMobileNumber());
            transportAccompyMapper.setIsDeleted(false);
            transportAccompyMapper.setCreatedBy(loginUser.getUserId());
            transportAccompyMapper.setCreatedOn(new Date());
            transportAccompyMapperList.add(transportAccompyMapper);
        }
        transportAccompanyRepository.saveAll(transportAccompyMapperList);
        ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100007);
        baseResponse.setCode(errorMessage.getCode());
        baseResponse.setMessage(errorMessage.getMessage());
        baseResponse.setStatus(errorMessage.getStatus());
        baseResponse.setData(null);
        log.info("LogId:{} - GateSecurityService - updateTransport - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY UPDATED TRANSPORT DETAILS ");
        return baseResponse;
    }

    public static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle parsing errors
        }
    }

    public static Time stringToSqlTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT_PATTERN);
        LocalTime localTime = LocalTime.parse(timeString, formatter);
        return Time.valueOf(localTime);
    }


}
