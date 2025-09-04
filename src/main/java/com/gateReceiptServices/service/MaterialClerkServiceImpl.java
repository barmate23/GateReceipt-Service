package com.gateReceiptServices.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gateReceiptServices.configuration.LoginUser;
import com.gateReceiptServices.model.*;
import com.gateReceiptServices.repository.*;
import com.gateReceiptServices.request.*;
import com.gateReceiptServices.response.*;
import com.gateReceiptServices.utils.APIConstants;
import com.gateReceiptServices.utils.BarcodeGenerator;
import com.gateReceiptServices.utils.GlobalMessages;
import com.gateReceiptServices.utils.ServiceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MaterialClerkServiceImpl implements MaterialClerkService {
    @Autowired
    AsnHeadRepository asnHeadRepository;
    @Autowired
    InvoiceHeadRepository invoiceHeadRepository;
    @Autowired
    GatePassRepository gatePassRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    CodeMapperRepository codeMapperRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    QCCertificateRepository qcCertificateRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    BarcodeGenerator barcodeGenerator;
    @Autowired
    TransportRepository transportDetailsRepository;
    @Autowired
    TransportAccompanyRepository transportAccompanyRepository;
    @Autowired
    OrganizationRepository organizationRepository;
    @Autowired
    SerBatNumRepository serBatNumRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    StoreDockMapperRepository storeDockMapperRepository;
    @Autowired
    PurchaseOrderHeadRepository purchaseOrderHeadRepository;
    @Autowired
    AsnLineRepository asnLineRepository;
    @Autowired
    PrintQueueDetailsRepository printQueueDetailsRepository;
    @Autowired
    DeviceMasterRepository deviceMasterRepository;
    @Autowired
    SubModuleRepository subModuleRepository;
    @Autowired
    BarcodeMasterRepository barcodeMasterRepository;
    @Autowired
    PurchaseOrderLineRepository purchaseOrderLineRepository;

    @Autowired
    private LoginUser loginUser;


    @Override
    public BaseResponse<InvoiceHead> getInvoiceDetails(Integer asnHeadId, Integer poHeadId) {
        log.info(APIConstants.INVOICE_DET_LOG, loginUser.getLogId(), loginUser.getUserId(), "  GET INVOICE DETAILS STARTED  ");
        long startTime = System.currentTimeMillis();
        BaseResponse<InvoiceHead> baseResponse = new BaseResponse<>();
        try {
            List<InvoiceHead> invoiceHeadList = new ArrayList<>();

            if (asnHeadId != null && asnHeadId > 0) {
                log.info(APIConstants.INVOICE_DET_LOG, loginUser.getLogId(), loginUser.getUserId(), "ASN HEAD ID :: " + asnHeadId);
                invoiceHeadList = invoiceHeadRepository.findBySubOrganizationIdAndAsnHeadIdAndIsDeleted(loginUser.getSubOrgId(), asnHeadId, false);
            }
            if (poHeadId != null && poHeadId > 0) {
                log.info(APIConstants.INVOICE_DET_LOG, loginUser.getLogId(), loginUser.getUserId(), "PO HEAD ID :: " + poHeadId);
                invoiceHeadList = invoiceHeadRepository.findBySubOrganizationIdAndPurchaseOrderHeadIdAndIsDeleted(loginUser.getSubOrgId(), poHeadId, false);
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100013);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(invoiceHeadList);
            log.info(APIConstants.INVOICE_DET_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED GET INVOICE DETAILS ");

        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100014);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setStatus(500);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.INVOICE_DET_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING INVOICE DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.INVOICE_DET_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET INVOICE DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<QCCertificate> getQcCertificate(Integer asnHeadId, Integer poHeadId) {
        log.info(APIConstants.QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET QC CERTIFICATE STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<QCCertificate> baseResponse = new BaseResponse<>();
        try {
            log.info(loginUser.getLogId() + " GET ->  GET DATE FOR ITEM ");
            List<QCCertificate> qcCertificateList = new ArrayList<>();
            if (asnHeadId != null && asnHeadId > 0) {
                log.info(APIConstants.QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), APIConstants.GET_ASN_HEAD_ID + asnHeadId);
                qcCertificateList = qcCertificateRepository.findBySubOrganizationIdAndAsnLineAsnHeadIdIdAndIsDeleted(loginUser.getSubOrgId(), asnHeadId, false);
            }
            if (poHeadId != null && poHeadId > 0) {
                log.info(APIConstants.QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), APIConstants.GET_PO_HEAD_ID + poHeadId);
                qcCertificateList = qcCertificateRepository.findBySubOrganizationIdAndPurchaseOrderLinePurchaseOrderHeadIdAndIsDeleted(loginUser.getSubOrgId(), poHeadId, false);
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100015);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(qcCertificateList);
            log.info(APIConstants.QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED GET QC CERTIFICATE ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100016);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING GET QC CERTIFICATE END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET QC CERTIFICATE END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<Document> getDocuments(Integer asnHeadId, Integer poHeadId) {
        log.info(APIConstants.DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET DOCUMENTS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<Document> baseResponse = new BaseResponse<>();
        try {
            List<Document> documentList = new ArrayList<>();
            if (asnHeadId != null && asnHeadId > 0) {
                log.info(APIConstants.DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), APIConstants.GET_ASN_HEAD_ID + asnHeadId);
                documentList = documentRepository.findByIsDeletedAndSubOrganizationIdAndAsnHeadId(false, loginUser.getSubOrgId(), asnHeadId);
            }
            if (poHeadId != null && poHeadId > 0) {
                log.info(APIConstants.DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), APIConstants.GET_PO_HEAD_ID + poHeadId);
                documentList = documentRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(false, loginUser.getSubOrgId(), poHeadId);
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100017);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(documentList);
            log.info(APIConstants.DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED GET DOCUMENTS ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100018);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING GET DOCUMENTS END EXECUTED TIME :: " + (endTime - startTime));

        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET DOCUMENTS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<MaterialClerkReceiptResponse> getMaterialClerkReceipt(Integer asnHeadId, Integer poHeadId) {
        log.info(APIConstants.MATERIAL_CLERK_RECEIPT_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET MATERIAL CLERK RECEIPT STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<MaterialClerkReceiptResponse> baseResponse = new BaseResponse<>();
        try {
            MaterialClerkReceiptResponse materialClerkReceipt = new MaterialClerkReceiptResponse();
            List<Document> documentList = null;
            List<InvoiceHead> invoiceHeadList = null;
            List<QCCertificate> qcCertificateList = null;
            Transport transport = null;
            if (asnHeadId != null && asnHeadId > 0) {
                log.info(APIConstants.MATERIAL_CLERK_RECEIPT_LOG, loginUser.getLogId(), loginUser.getUserId(), APIConstants.GET_ASN_HEAD_ID + asnHeadId);
                ASNHead asnHead = asnHeadRepository.findByIdAndSubOrganizationIdAndIsDeleted(asnHeadId, loginUser.getSubOrgId(), false);
                documentList = documentRepository.findByIsDeletedAndSubOrganizationIdAndAsnHeadIdAndIsAccepted(false, loginUser.getSubOrgId(), asnHeadId, true);
                List<ASNLine> asnLineList = asnLineRepository.findBySubOrganizationIdAndAsnHeadIdIdAndIsDeletedAndIsAccepted(loginUser.getSubOrgId(), asnHead.getId(), false, true);
                invoiceHeadList = invoiceHeadRepository.findBySubOrganizationIdAndAsnHeadIdAndIsDeletedAndIsAccepted(loginUser.getSubOrgId(), asnHeadId, false, true);
                qcCertificateList = qcCertificateRepository.findBySubOrganizationIdAndAsnLineAsnHeadIdIdAndIsDeletedAndIsAccepted(loginUser.getSubOrgId(), asnHeadId, false, true);
                transport = transportDetailsRepository.findByIsDeletedAndSubOrganizationIdAndAsnHeadId(false, loginUser.getSubOrgId(), asnHeadId);
                User buyer = userRepository.findById(asnHead.getCreatedBy()).get();
                User supplier = userRepository.findByIsDeletedAndSupplierIdAndSubOrganizationId(false, asnHead.getSupplier().getId(), loginUser.getSubOrgId());

                materialClerkReceipt.setDocumentList(documentList);
                materialClerkReceipt.setAsnHead(asnHead);
                materialClerkReceipt.setBuyer(buyer);
                materialClerkReceipt.setSupplierName(supplier.getFirstName() + " " + supplier.getLastName());
                materialClerkReceipt.setAsnLineList(asnLineList);
                materialClerkReceipt.setInvoiceHeadList(invoiceHeadList);
                materialClerkReceipt.setQcCertificateList(qcCertificateList);
                materialClerkReceipt.setTransportId(transport.getId());
            } else if (poHeadId != null && poHeadId > 0) {
                log.info(APIConstants.MATERIAL_CLERK_RECEIPT_LOG, loginUser.getLogId(), loginUser.getUserId(), APIConstants.GET_PO_HEAD_ID + poHeadId);
                PurchaseOrderHead purchaseOrderHead = purchaseOrderHeadRepository.findBySubOrganizationIdAndIdAndIsDeleted(loginUser.getSubOrgId(), poHeadId, false);
                documentList = documentRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadIdAndIsAccepted(false, loginUser.getSubOrgId(), poHeadId, true);
                List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findBySubOrganizationIdAndIsDeletedAndPurchaseOrderHeadIdAndIsAccepted(loginUser.getSubOrgId(), false, purchaseOrderHead.getId(), true);
                invoiceHeadList = invoiceHeadRepository.findBySubOrganizationIdAndPurchaseOrderHeadIdAndIsDeletedAndIsAccepted(loginUser.getSubOrgId(), poHeadId, false, true);
                qcCertificateList = qcCertificateRepository.findBySubOrganizationIdAndPurchaseOrderLinePurchaseOrderHeadIdAndIsDeletedAndIsAccepted(loginUser.getSubOrgId(), poHeadId, false, true);
                transport = transportDetailsRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(false, loginUser.getSubOrgId(), poHeadId);
                materialClerkReceipt.setDocumentList(documentList);
                materialClerkReceipt.setPoHead(purchaseOrderHead);
                materialClerkReceipt.setPoLineList(purchaseOrderLineList);
                materialClerkReceipt.setInvoiceHeadList(invoiceHeadList);
                User supplier = userRepository.findByIsDeletedAndSupplierIdAndSubOrganizationId(false, purchaseOrderHead.getSupplierId(), loginUser.getSubOrgId());
                materialClerkReceipt.setSupplierName(supplier.getFirstName() + " " + supplier.getLastName());
                materialClerkReceipt.setQcCertificateList(qcCertificateList);
                materialClerkReceipt.setTransportId(transport.getId());
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100019);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(Arrays.asList(materialClerkReceipt));
            log.info(APIConstants.MATERIAL_CLERK_RECEIPT_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED GET MATERIAL CLERK RECEIPT ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100020);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.MATERIAL_CLERK_RECEIPT_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING GET MATERIAL CLERK RECEIPT END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.MATERIAL_CLERK_RECEIPT_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET MATERIAL CLERK RECEIPT END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<CinLabelResponse> generateCinLabel(Integer asnHeadId, Integer poHeadId, Integer printerId) {
        log.info(APIConstants.GENERATE_CIN_LABEL_LOG, loginUser.getLogId(), loginUser.getUserId(), " GENERATE CIN LABEL STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<CinLabelResponse> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.GENERATE_CIN_LABEL_LOG, loginUser.getLogId(), loginUser.getUserId(), " GENERATE CIN LABEL ");
            CinLabelResponse cinLabelResponse = new CinLabelResponse();
            CodeMapper codeMapper = new CodeMapper();
            CinResponse cinResponse = null;
            codeMapper.setOrganizationId(loginUser.getOrgId());
            codeMapper.setSubOrganizationId(loginUser.getSubOrgId());
            String cinBarcode = barcodeGenerator.getCinNumber();
            if (asnHeadId != null && asnHeadId > 0) {
                ASNHead asnHead = asnHeadRepository.findByIdAndSubOrganizationIdAndIsDeleted(asnHeadId, loginUser.getSubOrgId(), false);
                List<CodeMapper> codeMappers = codeMapperRepository.findBySubOrganizationIdAndAsnNumber(loginUser.getSubOrgId(), asnHead.getAsnNumber());
                if(!codeMappers.isEmpty()) {
                    ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100035);
                    baseResponse.setCode(errorMessage.getCode());
                    baseResponse.setMessage(errorMessage.getMessage());
                    baseResponse.setStatus(errorMessage.getStatus());
                    baseResponse.setLogId(loginUser.getLogId());
                    baseResponse.setData(Arrays.asList(cinLabelResponse));
                    return baseResponse;
                }
                asnHead.setCinBarcodeNumber(cinBarcode);
                asnHeadRepository.save(asnHead);
                codeMapper.setCinBarcodeNumber(cinBarcode);
                codeMapper.setAsnNumber(asnHead.getAsnNumber());
                codeMapper.setIsAsn(true);
                List<ASNLine> asnLineList = asnLineRepository.findBySubOrganizationIdAndAsnHeadIdIdAndIsDeletedAndIsAccepted(loginUser.getSubOrgId(), asnHeadId, false, true);
                cinResponse = getCinResponseForAsn(asnHead, asnLineList);
            } else if (poHeadId != null && poHeadId > 0) {

                PurchaseOrderHead purchaseOrderHead = purchaseOrderHeadRepository.findBySubOrganizationIdAndIdAndIsDeleted(loginUser.getSubOrgId(), poHeadId, false);
                List<CodeMapper> codeMappers = codeMapperRepository.findBySubOrganizationIdAndPoNumber(loginUser.getSubOrgId(), purchaseOrderHead.getPurchaseOrderNumber());

                if(!codeMappers.isEmpty()) {
                    ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100035);
                    baseResponse.setCode(errorMessage.getCode());
                    baseResponse.setMessage(errorMessage.getMessage());
                    baseResponse.setStatus(errorMessage.getStatus());
                    baseResponse.setLogId(loginUser.getLogId());
                    baseResponse.setData(Arrays.asList(cinLabelResponse));
                    return baseResponse;
                }
                purchaseOrderHead.setCinBarcodeNumber(cinBarcode);
                purchaseOrderHeadRepository.save(purchaseOrderHead);
                codeMapper.setCinBarcodeNumber(purchaseOrderHead.getCinBarcodeNumber());
                codeMapper.setPoNumber(purchaseOrderHead.getPurchaseOrderNumber());
                codeMapper.setIsAsn(false);
                List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findBySubOrganizationIdAndIsDeletedAndPurchaseOrderHeadIdAndIsAccepted(loginUser.getSubOrgId(), false, poHeadId, true);
                cinResponse = getCinResponseForPo(purchaseOrderHead, purchaseOrderLineList);
            }
            cinLabelResponse.setCinResponses(cinResponse);
            cinLabelResponse.setCinLabel(BarcodeGenerator.generateBarcode(String.valueOf(cinBarcode)));
            codeMapper.setIsDeleted(false);
            codeMapper.setCreatedOn(new Date());
            codeMapper.setCreatedBy(loginUser.getUserId());
            codeMapper.setModifiedOn(new Date());
            codeMapper.setModifiedBy(loginUser.getUserId());

            savePrintQueueDetails(cinResponse, printerId);
            codeMapperRepository.save(codeMapper);
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100021);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setLogId(loginUser.getLogId());
            baseResponse.setData(Arrays.asList(cinLabelResponse));
            log.info(APIConstants.GENERATE_CIN_LABEL_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED CIN LABEL GENERATE ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100022);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.GENERATE_CIN_LABEL_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE GENERATING CIN LABEL END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.error(APIConstants.GENERATE_CIN_LABEL_LOG, loginUser.getLogId(), loginUser.getUserId(), " GENERATE CIN LABEL END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    private CinResponse getCinResponseForAsn(ASNHead asnHead, List<ASNLine> asnLineList) {
        CinResponse cinResponse = new CinResponse();
        cinResponse.setCinNumber(asnHead.getCinBarcodeNumber());
        Transport transport = transportDetailsRepository.findByIsDeletedAndSubOrganizationIdAndAsnHeadId(false, loginUser.getSubOrgId(), asnHead.getId());
        List<InvoiceHead> invoiceHead = invoiceHeadRepository.findBySubOrganizationIdAndAsnHeadIdAndIsDeleted(loginUser.getSubOrgId(), asnHead.getId(), false);
        cinResponse.setTransporter(transport.getTransporter());
        cinResponse.setInvoiceDate(invoiceHead.get(0).getInvoiceDate());
        cinResponse.setInvoiceNumber(invoiceHead.get(0).getInvoiceNumber());
        cinResponse.setVehicleNumber(transport.getVehicleNumber());
        cinResponse.setOrganizationName(loginUser.getOrgName());
        List<DockItem> dockItemList = new ArrayList<>();
        Map<Integer, List<ASNLine>> itemDockMap = asnLineList.stream().collect(Collectors.groupingBy(asnLine -> asnLine.getItem().getDockId().getId()));
        for (Map.Entry<Integer, List<ASNLine>> entry : itemDockMap.entrySet()) {
            DockItem dockItem = new DockItem();
            List<DockItemResponse> dockItemResponseList = new ArrayList<>();
            List<ASNLine> asnLines = entry.getValue();
            dockItem.setDockId(asnLines.get(0).getItem().getDockId().getDockId());
            for (ASNLine asnLine : asnLines) {
                DockItemResponse dockItemResponse = new DockItemResponse();
                dockItemResponse.setItemId(asnLine.getItem().getItemId());
                dockItemResponse.setItemQuantity(asnLine.getInvoiceQuantity());
                dockItemResponseList.add(dockItemResponse);
            }
            dockItem.setItemResponse(dockItemResponseList);
            dockItemList.add(dockItem);
        }
        cinResponse.setDockItemList(dockItemList);
        return cinResponse;
    }

    private CinResponse getCinResponseForPo(PurchaseOrderHead purchaseOrderHead, List<PurchaseOrderLine> purchaseOrderLineList) {
        CinResponse cinResponse = new CinResponse();
        cinResponse.setCinNumber(purchaseOrderHead.getCinBarcodeNumber());
        Transport transport = transportDetailsRepository.findByIsDeletedAndSubOrganizationIdAndPurchaseOrderHeadId(false, loginUser.getSubOrgId(), purchaseOrderHead.getId());
        List<InvoiceHead> invoiceHead = invoiceHeadRepository.findBySubOrganizationIdAndPurchaseOrderHeadIdAndIsDeleted(loginUser.getSubOrgId(), purchaseOrderHead.getId(), false);
        cinResponse.setTransporter(transport.getTransporter());
        cinResponse.setInvoiceDate(invoiceHead.get(0).getInvoiceDate());
        cinResponse.setInvoiceNumber(invoiceHead.get(0).getInvoiceNumber());
        cinResponse.setVehicleNumber(transport.getVehicleNumber());
        cinResponse.setOrganizationName(loginUser.getOrgName());
        List<DockItem> dockItemList = new ArrayList<>();
        Map<Integer, List<PurchaseOrderLine>> itemDockMap = purchaseOrderLineList.stream().collect(Collectors.groupingBy(purchaseOrderLine -> purchaseOrderLine.getItem().getDockId().getId()));
        for (Map.Entry<Integer, List<PurchaseOrderLine>> entry: itemDockMap.entrySet()) {
            DockItem dockItem = new DockItem();
            List<DockItemResponse> dockItemResponseList = new ArrayList<>();
            List<PurchaseOrderLine> purchaseOrderLines = entry.getValue();
            dockItem.setDockId(purchaseOrderLines.get(0).getItem().getDockId().getDockId());
            for (PurchaseOrderLine purchaseOrderLine : purchaseOrderLines) {
                DockItemResponse dockItemResponse = new DockItemResponse();
                dockItemResponse.setItemId(purchaseOrderLine.getItem().getItemId());
                dockItemResponse.setItemQuantity(purchaseOrderLine.getInvoiceQuantity());
                dockItemResponseList.add(dockItemResponse);
            }
            dockItemList.add(dockItem);
        }
        cinResponse.setDockItemList(dockItemList);
        return cinResponse;
    }

    private void savePrintQueueDetails(CinResponse cinResponse, Integer printerId) {
        PrintQueueDetail printQueueDetail = new PrintQueueDetail();

        DeviceMaster deviceMaster = deviceMasterRepository.findById(printerId).get();
        SubModules subModules = subModuleRepository.findBySubModuleCode("MACL");
        BarcodeMaster barcodeMaster = barcodeMasterRepository.findByLabelFor("CIN");
        String responseJson = convertEntityToJson(cinResponse);

        printQueueDetail.setSubOrganizationId(loginUser.getSubOrgId());
        printQueueDetail.setOrganizationId(loginUser.getOrgId());
        printQueueDetail.setBarcodeMaster(barcodeMaster);
        printQueueDetail.setDeviceMaster(deviceMaster);
        printQueueDetail.setIsDeleted(false);
        printQueueDetail.setValue(responseJson);
        printQueueDetail.setSubModules(subModules);
        printQueueDetail.setPrintJobStatus(APIConstants.QUEUED);
        printQueueDetail.setCreatedBy(loginUser.getUserId());
        printQueueDetail.setCreatedOn(new Date());
        printQueueDetailsRepository.save(printQueueDetail);
    }

    @Override
    public BaseResponse<GatePassResponse> generateGatePass(Integer trnsportId) {
        log.error(APIConstants.GENERATE_GATE_PASS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GENERATE GATE PASS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<GatePassResponse> baseResponse = new BaseResponse<>();
        try {
            Transport transport = transportDetailsRepository.findByIdAndSubOrganizationIdAndIsDeleted(trnsportId, loginUser.getSubOrgId(), false);

            GatePassGenerate gatePassGenerate = new GatePassGenerate();
            gatePassGenerate.setOrganizationId(loginUser.getOrgId());
            gatePassGenerate.setSubOrganizationId(loginUser.getSubOrgId());
            gatePassGenerate.setTransport(transport);
            gatePassGenerate.setTimeIn(new Time(System.currentTimeMillis()));
            gatePassGenerate.setRegistrationNumber(BarcodeGenerator.getRandomNumber());
            gatePassGenerate.setIsDeleted(false);
            gatePassGenerate.setCreatedBy(loginUser.getUserId());
            gatePassGenerate.setCreatedOn(new Date());
            gatePassGenerate.setModifiedBy(loginUser.getUserId());
            gatePassGenerate.setModifiedOn(new Date());
            gatePassRepository.save(gatePassGenerate);

            User supplier = userRepository.findByIsDeletedAndIdAndSubOrganizationId(false, transport.getCreatedBy(), loginUser.getSubOrgId());
            User currentUser = userRepository.findByIsDeletedAndIdAndSubOrganizationId(false, loginUser.getUserId(), loginUser.getSubOrgId());
            Organization organization = organizationRepository.findByIdAndIsDeleted(loginUser.getSubOrgId(), false);
            GatePassResponse gatePassResponse = generateGatePass(gatePassGenerate, supplier, currentUser, organization);

            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100023);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(Arrays.asList(gatePassResponse));
            log.info(APIConstants.GENERATE_GATE_PASS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY GENERATED GATE PASS ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100024);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.GENERATE_GATE_PASS_LOG, loginUser.getLogId(), loginUser.getUserId(), " FAILED TO GENERATE GATE PASS TIME : " + (endTime - startTime));
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.error(APIConstants.GENERATE_GATE_PASS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GENERATE GATE PASS END : " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<String> updatePrintStatus(UpdatePrintStatus updatePrintStatus) {
        log.error(APIConstants.UPDATE_PRINT_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE PRINT STATUS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            if (updatePrintStatus.getPrintStatusOf() == 1) {
                if (updatePrintStatus.getAsnHeadId() != null && updatePrintStatus.getAsnHeadId() > 0) {
                    ASNHead asnHead = asnHeadRepository.findByIdAndSubOrganizationIdAndIsDeleted(updatePrintStatus.getAsnHeadId(), loginUser.getSubOrgId(), false);
                    asnHead.setIsPrint(true);
                    asnHeadRepository.save(asnHead);
                } else if (updatePrintStatus.getPoHeadId() != null && updatePrintStatus.getPoHeadId() > 0) {
                    PurchaseOrderHead purchaseOrderHead = purchaseOrderHeadRepository.findBySubOrganizationIdAndIdAndIsDeleted(loginUser.getSubOrgId(), updatePrintStatus.getPoHeadId(), false);
                    purchaseOrderHead.setIsPrint(true);
                    purchaseOrderHeadRepository.save(purchaseOrderHead);

                }
            } else if (updatePrintStatus.getPrintStatusOf() == 2 && checkTransportId(updatePrintStatus)) {
                GatePassGenerate gatePassGenerate = gatePassRepository.findByIsDeletedAndSubOrganizationIdAndTransportId(false, loginUser.getSubOrgId(), updatePrintStatus.getTransportId());
                gatePassGenerate.setIsPrint(true);
                gatePassRepository.save(gatePassGenerate);
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100033);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_PRINT_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY UPDATED PRINT STATUS ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100034);
            log.info(APIConstants.UPDATE_PRINT_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " Failed TO UPDATE PRINT STATUS");
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_PRINT_STATUS_LOG, loginUser.getLogId(), loginUser.getUserId(), " GET -> GET DATE FOR ITEM End --- " + (endTime - startTime));
        return baseResponse;
    }

    private static boolean checkTransportId(UpdatePrintStatus updatePrintStatus) {
        return updatePrintStatus.getTransportId() != null && updatePrintStatus.getTransportId() > 0;
    }

    private GatePassResponse generateGatePass(GatePassGenerate gatePassGenerate, User supplierUser, User currentUser, Organization organization) {
        GatePassResponse gatePassResponse = new GatePassResponse();
        //Organization
        gatePassResponse.setOrganizationName(organization.getOrganizationName());
        gatePassResponse.setOrgAddress(organization.getAddress1());
        gatePassResponse.setOrgState(organization.getState());
        gatePassResponse.setOrgCity(organization.getCity());
        gatePassResponse.setOrgPostCode(organization.getPostCode());
        gatePassResponse.setOrgCountry(organization.getCountry());

        //transporter
        gatePassResponse.setTransporterFullName(gatePassGenerate.getTransport().getDriver());
        gatePassResponse.setDate(String.valueOf(gatePassGenerate.getTransport().getDispatchDate()));
        gatePassResponse.setVehicleNumber(gatePassGenerate.getTransport().getVehicleNumber());
        Supplier supplier = supplierRepository.findByIsDeletedAndId(false, supplierUser.getSupplierId());
        gatePassResponse.setComingFrom(supplier.getCity());
        gatePassResponse.setRegistrationNumber(gatePassGenerate.getRegistrationNumber());
        gatePassResponse.setRepresenting(supplier.getSupplierName());
        gatePassResponse.setTimeIn(String.valueOf(gatePassGenerate.getTimeIn()));
        gatePassResponse.setTransporterAddress(supplier.getAddress1() + ", " + supplier.getCity());

        List<StoreDockInfoResponse> storeDockInfoResponseList = new ArrayList<>();
        List<AccompanyDetailsResponse> accompanyDetailsResponseList = new ArrayList<>();
        Set<String> processedDockStorePairs = new HashSet<>();

        if (gatePassGenerate.getTransport().getAsnHead() != null) {
            List<ASNLine> asnLineList = asnLineRepository.findByAsnHeadIdIdAndSubOrganizationIdAndIsDeleted(
                    gatePassGenerate.getTransport().getAsnHead().getId(), loginUser.getSubOrgId(), false);
            processASNLines(asnLineList, loginUser.getSubOrgId(), processedDockStorePairs, storeDockInfoResponseList);
        } else if (gatePassGenerate.getTransport().getPurchaseOrderHead() != null) {
            List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderLineRepository.findBySubOrganizationIdAndIsDeletedAndPurchaseOrderHeadId(
                    loginUser.getSubOrgId(), false, gatePassGenerate.getTransport().getPurchaseOrderHead().getId());
            processPurchaseOrderLines(purchaseOrderLineList, loginUser.getSubOrgId(), processedDockStorePairs, storeDockInfoResponseList);
        }

        List<TransportAccompyMapper> transportAccompyMapperList = transportAccompanyRepository.findBySubOrganizationIdAndIsDeletedAndTransportId(loginUser.getSubOrgId(), false, gatePassGenerate.getTransport().getId());
        transportAccompyMapperList.forEach(transportAccompyMapper -> {
            AccompanyDetailsResponse accompanyDetailsResponse = new AccompanyDetailsResponse();
            accompanyDetailsResponse.setPersonName(transportAccompyMapper.getPersonName());
            accompanyDetailsResponse.setMobileNumber(transportAccompyMapper.getMobile());
            accompanyDetailsResponseList.add(accompanyDetailsResponse);
        });
        gatePassResponse.setAccompanyDetailsResponseList(accompanyDetailsResponseList);
        gatePassResponse.setStoreDockInfoResponseList(storeDockInfoResponseList);
        gatePassResponse.setCurrentUserName(currentUser.getFirstName() + " " + currentUser.getLastName());
        byte[] barcode = BarcodeGenerator.generateBarcode(gatePassGenerate.getRegistrationNumber());
        gatePassResponse.setRegistrationNumberBarcode(barcode);

        return gatePassResponse;
    }

    private void processASNLines(List<ASNLine> asnLineList, Integer subOrgId, Set<String> processedDockStorePairs, List<StoreDockInfoResponse> storeDockInfoResponseList) {
        for (ASNLine asnLine : asnLineList) {
            List<Location> locationList = locationRepository.findBySubOrganizationIdAndIsDeletedAndItemId(subOrgId, false, asnLine.getItem().getId());

            Set<Integer> storeIds = locationList.stream()
                    .map(location -> location.getZone().getArea().getStore().getId())
                    .collect(Collectors.toSet());

            List<StoreDockMapper> storeDockMapperList = storeDockMapperRepository.findByIsDeletedAndSubOrganizationIdAndDockIdAndStoreIdIn(
                    false, subOrgId, asnLine.getItem().getDockId().getId(), storeIds);

            for (StoreDockMapper storeDockMapper : storeDockMapperList) {
                String dockStorePair = asnLine.getItem().getDockId().getId() + "-" + storeDockMapper.getStore().getId();
                if (!processedDockStorePairs.contains(dockStorePair)) {
                    StoreDockInfoResponse storeDockInfoResponse = new StoreDockInfoResponse();
                    storeDockInfoResponse.setDockName(asnLine.getItem().getDockId().getDockName());
                    storeDockInfoResponse.setDockId(asnLine.getItem().getDockId().getDockId());
                    storeDockInfoResponse.setStoreName(storeDockMapper.getStore().getStoreName());
                    storeDockInfoResponse.setStoreId(storeDockMapper.getStore().getStoreId());
                    storeDockInfoResponseList.add(storeDockInfoResponse);
                    processedDockStorePairs.add(dockStorePair);
                }
            }
        }
    }

    private void processPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLineList, Integer subOrgId, Set<String> processedDockStorePairs, List<StoreDockInfoResponse> storeDockInfoResponseList) {
        for (PurchaseOrderLine poLine : purchaseOrderLineList) {
            List<Location> locationList = locationRepository.findBySubOrganizationIdAndIsDeletedAndItemId(subOrgId, false, poLine.getItem().getId());

            Set<Integer> storeIds = locationList.stream()
                    .map(location -> location.getZone().getArea().getStore().getId())
                    .collect(Collectors.toSet());

            List<StoreDockMapper> storeDockMapperList = storeDockMapperRepository.findByIsDeletedAndSubOrganizationIdAndDockIdAndStoreIdIn(
                    false, subOrgId, poLine.getItem().getDockId().getId(), storeIds);

            for (StoreDockMapper storeDockMapper : storeDockMapperList) {
                String dockStorePair = poLine.getItem().getDockId().getId() + "-" + storeDockMapper.getStore().getId();
                if (!processedDockStorePairs.contains(dockStorePair)) {
                    StoreDockInfoResponse storeDockInfoResponse = new StoreDockInfoResponse();
                    storeDockInfoResponse.setDockName(poLine.getItem().getDockId().getDockName());
                    storeDockInfoResponse.setDockId(poLine.getItem().getDockId().getDockId());
                    storeDockInfoResponse.setStoreName(storeDockMapper.getStore().getStoreName());
                    storeDockInfoResponse.setStoreId(storeDockMapper.getStore().getStoreId());
                    storeDockInfoResponseList.add(storeDockInfoResponse);
                    processedDockStorePairs.add(dockStorePair);
                }
            }
        }
    }
    @Override
    public BaseResponse<POASNLineResponse> updateInvoiceDetails(UpdateInvoiceRequest updateInvoiceRequest) {
        log.info("LogId:{} - MaterialClerkService - updateInvoiceDetails - UserId:{} - {}", loginUser.getLogId(), loginUser.getUserId(), " UPDATE INVOICE DETAILS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<POASNLineResponse> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.UPDATE_INVOICE_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE INVOICE DETAILS STARTED " + updateInvoiceRequest.getInvoiceId());
            InvoiceHead invoiceHead = invoiceHeadRepository.findBySubOrganizationIdAndIdAndIsDeleted(loginUser.getSubOrgId(), updateInvoiceRequest.getInvoiceId(), false);
            invoiceHead.setIsAccepted(true);
            invoiceHead.setModifiedBy(loginUser.getUserId());
            invoiceHead.setModifiedOn(new Date());
            invoiceHeadRepository.save(invoiceHead);

            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100025);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_INVOICE_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED INVOICE DETAILS UPDATE ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100026);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.UPDATE_INVOICE_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE FETCHING UPDATE INVOICE DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_INVOICE_DETAILS_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE INVOICE DETAILS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse<String> updateQcCertificate(List<UpdateQCRequest> updateQCRequestList) {
        log.info(APIConstants.UPDATE_QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE QC CERTIFICATE STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.UPDATE_QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATING QC Certificate LIST");
            for (UpdateQCRequest updateQCRequest : updateQCRequestList) {
                QCCertificate qcCertificate = qcCertificateRepository.findBySubOrganizationIdAndIdAndIsDeleted(loginUser.getSubOrgId(), updateQCRequest.getQcCertificateId(), false);
                qcCertificate.setIsAccepted(true);
                qcCertificate.setModifiedBy(loginUser.getUserId());
                qcCertificate.setModifiedOn(new Date());
                qcCertificateRepository.save(qcCertificate);
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100027);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED QC CERTIFICATE UPDATE ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100028);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.UPDATE_QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE UPDATING QC CERTIFICATE END EXECUTED TIME :: " + (endTime - startTime));
        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_QC_CERTIFICATE_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE QC CERTIFICATE END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse updateDocuments(List<UpdateDocumentRequest> updateDocumentRequestList) {
        log.info(APIConstants.UPDATE_DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE DOCUMENTS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<POASNLineResponse> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.UPDATE_DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATING DOCUMENTS LIST ");
            for (UpdateDocumentRequest updateDocumentRequest : updateDocumentRequestList) {
                Document document = documentRepository.findByIsDeletedAndSubOrganizationIdAndId(false, loginUser.getSubOrgId(), updateDocumentRequest.getDocumentId());
                document.setIsAccepted(true);
                document.setModifiedBy(loginUser.getUserId());
                document.setModifiedOn(new Date());
                documentRepository.save(document);
            }

            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100029);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY FETCHED DOCUMENTS UPDATE ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100030);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.UPDATE_DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE UPDATING DOCUMENTS END EXECUTED TIME :: " + (endTime - startTime));

        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_DOCUMENT_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE DOCUMENTS END EXECUTED TIME :: " + (endTime - startTime));
        return baseResponse;
    }

    @Override
    public BaseResponse updateAsnPoItemDetails(List<UpdateItemRequest> updateItemRequestList) {
        log.info(APIConstants.UPDATE_ASN_PO_ITEM_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATE ASN PO ITEM DETAILS STARTED ");
        long startTime = System.currentTimeMillis();
        BaseResponse<POASNLineResponse> baseResponse = new BaseResponse<>();
        try {
            log.info(APIConstants.UPDATE_ASN_PO_ITEM_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATING ASN PO ITEM DETAILS ");
            for (UpdateItemRequest updateItemRequest : updateItemRequestList) {
                if (updateItemRequest.getAsnLineId() != null && updateItemRequest.getAsnLineId() > 0) {
                    ASNLine asnLine = asnLineRepository.findByIdAndSubOrganizationIdAndIsDeleted(updateItemRequest.getAsnLineId(), loginUser.getSubOrgId(), false);
                    asnLine.setIsAccepted(true);
                    asnLine.setModifiedBy(loginUser.getUserId());
                    asnLine.setModifiedOn(new Date());
                    asnLineRepository.save(asnLine);
                } else if (updateItemRequest.getPoLineId() != null && updateItemRequest.getPoLineId() > 0) {
                    PurchaseOrderLine purchaseOrderLine = purchaseOrderLineRepository.findBySubOrganizationIdAndIsDeletedAndId(loginUser.getSubOrgId(), false, updateItemRequest.getPoLineId());
                    purchaseOrderLine.setIsAccepted(true);
                    purchaseOrderLine.setModifiedBy(loginUser.getUserId());
                    purchaseOrderLine.setModifiedOn(new Timestamp(System.currentTimeMillis()));
                    purchaseOrderLineRepository.save(purchaseOrderLine);
                }
            }
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100031);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            log.info(APIConstants.UPDATE_ASN_PO_ITEM_LOG, loginUser.getLogId(), loginUser.getUserId(), " SUCCESSFULLY UPDATE ASN PO ITEM DETAILS ");
        } catch (Exception e) {
            ResponseMessage errorMessage = GlobalMessages.getResponseMessages(ServiceConstants.ASN_PO_GATR100032);
            baseResponse.setCode(errorMessage.getCode());
            baseResponse.setMessage(errorMessage.getMessage());
            baseResponse.setStatus(errorMessage.getStatus());
            baseResponse.setData(null);
            e.printStackTrace();
            long endTime = System.currentTimeMillis();
            log.error(APIConstants.UPDATE_ASN_PO_ITEM_LOG, loginUser.getLogId(), loginUser.getUserId(), " ERROR OCCURS AT WHILE UPDATING ASN PO ITEM DETAILS END EXECUTED TIME :: " + (endTime - startTime));

        }
        long endTime = System.currentTimeMillis();
        log.info(APIConstants.UPDATE_ASN_PO_ITEM_LOG, loginUser.getLogId(), loginUser.getUserId(), " UPDATING ASN PO ITEM DETAILS END :: " + (startTime - endTime));
        return baseResponse;
    }

    public String convertEntityToJson(CinResponse cinResponse) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(cinResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
