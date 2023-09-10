package com.scinlabs.preauditstepone.Repository;

import com.scinlabs.preauditstepone.Entity.InvoiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepositoryWithPagination extends JpaRepository<InvoiceEntity,String> {
    Page<InvoiceEntity> findAll(Pageable pageable);
}
