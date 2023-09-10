package com.scinlabs.preauditstepone.Repository;

import com.scinlabs.preauditstepone.Entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {
}
