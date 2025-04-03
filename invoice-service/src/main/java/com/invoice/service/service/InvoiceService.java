package com.invoice.service.service;

import com.invoice.service.dto.InvoiceDTO;
import com.invoice.service.entities.Invoice;
import com.invoice.service.exception.InvoiceException;
import com.invoice.service.repos.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public List<InvoiceDTO> getInvoiceList(String userId) {

        // we are just throwing exception if userId is missing in GET Request
        // obviously we wil be requiring userId to process GET Request
        if (StringUtils.isEmpty(userId)) {
            throw new InvoiceException(HttpStatus.BAD_REQUEST, "Bad Request", List.of("userId is missing in request."));
        }

        List<Invoice> invoices = invoiceRepository.findAllByUserId(userId);

        return invoices.stream()
                .map(this::populateInvoiceDTO)
                .toList();

    }


    // convert Invoice Entity to Invoice DTO
    private InvoiceDTO populateInvoiceDTO(Invoice invoice) {

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceId(invoice.getId());
        invoiceDTO.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDTO.setProductIds(invoice.getProductIds());
        invoiceDTO.setUpdatedTime(invoice.getUpdatedTime().toString());

        return invoiceDTO;
    }

}
