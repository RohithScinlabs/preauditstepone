package com.scinlabs.preauditstepone.Controller;

import com.scinlabs.preauditstepone.Processor.InvoiceProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceProcessor invoiceProcessor;

    @Autowired
    public InvoiceController(InvoiceProcessor invoiceProcessor) {
        this.invoiceProcessor = invoiceProcessor;
    }

    @GetMapping("/trackingIds")
    public void getAllTrackingIds() {
        invoiceProcessor.getAllTrackingIds();
    }
}
