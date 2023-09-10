package com.scinlabs.preauditstepone.Controller;

import com.scinlabs.preauditstepone.Entity.InvoiceEntity;
import com.scinlabs.preauditstepone.Processor.InvoiceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/tracking-ids")

public class InvoiceControllerWithPagination {
    private final InvoiceProcessor invoiceProcessor;
    @Autowired
    public InvoiceControllerWithPagination(InvoiceProcessor invoiceProcessor) {
        this.invoiceProcessor = invoiceProcessor;
    }

    @GetMapping("/all")
//    public List<String> getAllTrackingIdsWithPagination(
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "20")int size
//    ){
//        return invoiceProcessor.getAllTrackingIdsWithPagination(page,size);
//    }
    public Page<InvoiceEntity> getAllInvoices(Pageable pageable){
        Page<InvoiceEntity> invoices = invoiceProcessor.getAllInvoices(pageable);
        return ResponseEntity.ok(invoices).getBody();
    }
}
