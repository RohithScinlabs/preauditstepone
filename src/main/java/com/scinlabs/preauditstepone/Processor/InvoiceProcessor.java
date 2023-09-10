package com.scinlabs.preauditstepone.Processor;

import com.scinlabs.preauditstepone.Entity.InvoiceEntity;
import com.scinlabs.preauditstepone.Entity.ManifestEntity;
import com.scinlabs.preauditstepone.Repository.InvoiceRepository;
import com.scinlabs.preauditstepone.Repository.InvoiceRepositoryWithPagination;
import com.scinlabs.preauditstepone.dto.MatchedStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceProcessor {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceRepositoryWithPagination invoiceRepositoryWithPagination;
    private final ManifestService manifestService;

    @Autowired
    public InvoiceProcessor(InvoiceRepository invoiceRepository, InvoiceRepositoryWithPagination invoiceRepositoryWithPagination, ManifestService manifestService) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceRepositoryWithPagination = invoiceRepositoryWithPagination;
        this.manifestService = manifestService;
    }

    public Page<InvoiceEntity> getAllInvoices(Pageable pageable){
        return invoiceRepositoryWithPagination.findAll(pageable);
    }

    public void getAllTrackingIds() {
        int pageNumber = 0;
        List<MatchedStatus> batchToSend = new ArrayList<>();
        List<InvoiceEntity> batchToInsert = new ArrayList<>();
        while (true) {
            Page<InvoiceEntity> invoices = invoiceRepositoryWithPagination.findAll(PageRequest.of(pageNumber, 1000));
            System.out.println("Current Page "+ pageNumber);
            if (invoices.isEmpty()) {
                break;
            }
            pageNumber++;

            invoices.forEach(invoiceEntity -> {
                        ManifestEntity byManifestId = manifestService.getByManifestId(invoiceEntity.getTrackingId());
                        String matchValue = "";
                        if (byManifestId == null) {
                            matchValue = "Not Match";
                        } else {
                            matchValue = "Match";
                        }
                        MatchedStatus matchingDTO = new MatchedStatus();
                        matchingDTO.setManifestId(invoiceEntity.getTrackingId());
                        matchingDTO.setStatus(matchValue);
                        batchToSend.add(matchingDTO);
                        batchToInsert.add(invoiceEntity);

                    }
            );

            if (batchToSend.size() >= 1000){
                sendBatch(batchToSend);
                batchToSend.clear();
            }
            if (batchToInsert.size() >= 1000) {
                bulkInsertInvoices(batchToInsert); // Bulk insert 1000 records at a time
                batchToInsert.clear();
            }
        }
        if(!batchToSend.isEmpty()){
            sendBatch(batchToSend);
        }
        if (!batchToInsert.isEmpty()) {
            bulkInsertInvoices(batchToInsert); // Bulk insert any remaining records
        }
    }

    private void sendBatch(List<MatchedStatus> batch){
        RestTemplate restTemplate = new RestTemplate();
        URI uri = null;
        try{
            uri = new URI("http://localhost:" + 8082 + "/matched-status/add");
        } catch (URISyntaxException e){
            throw new RuntimeException(e);
        }
        ResponseEntity<String> result = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                new HttpEntity<>(batch),
                String.class
        );
        System.out.println(result.getBody());
    }
    private void bulkInsertInvoices(List<InvoiceEntity> batch) {
        invoiceRepository.saveAll(batch); // Bulk insert the batch
    }
}

