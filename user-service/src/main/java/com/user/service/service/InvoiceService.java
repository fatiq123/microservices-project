package com.user.service.service;

import com.user.service.client.InvoiceServiceFeignClient;
import com.user.service.dto.InvoiceDTO;
import com.user.service.exception.InvoiceException;
import com.user.service.exception.UserException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceServiceFeignClient invoiceServiceFeignClient;

    @Retryable(retryFor = {Exception.class}, maxAttempts = 4, backoff = @Backoff(delay = 3000))
    public List<InvoiceDTO> callInvoiceServiceAndGetInvoiceDTOList(String userId) {

        List<InvoiceDTO> invoiceResponse;

        try {
            invoiceResponse = invoiceServiceFeignClient.getInvoices(userId);
        } catch (InvoiceException invoiceException) {
            throw invoiceException;
        } catch (FeignException.ServiceUnavailable ex) {
            throw new UserException("Downstream service unavailable", List.of("invoice-service is down"), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new UserException("Internal server error", List.of("Something is wrong ar backend side."), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return invoiceResponse;
    }

}
