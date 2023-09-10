package com.scinlabs.preauditstepone.Repository;

import com.scinlabs.preauditstepone.Entity.ManifestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManifestRepository extends JpaRepository<ManifestEntity, String> {

    ManifestEntity findByMfstTrackingId(String manifestId);
}
