package com.scinlabs.preauditstepone.Processor;

import com.scinlabs.preauditstepone.Entity.ManifestEntity;
import com.scinlabs.preauditstepone.Repository.ManifestRepository;
import org.springframework.stereotype.Service;

@Service
public class ManifestService {

    private final ManifestRepository manifestRepository;

    public ManifestService(ManifestRepository manifestRepository) {
        this.manifestRepository = manifestRepository;
    }

    public ManifestEntity getByManifestId(String manifestId){
        return manifestRepository.findByMfstTrackingId(manifestId);
    }
}
