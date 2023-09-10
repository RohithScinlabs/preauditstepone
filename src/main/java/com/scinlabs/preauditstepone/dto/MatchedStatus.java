package com.scinlabs.preauditstepone.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class MatchedStatus {

    private String manifestId;

    private String status;

    public String getManifestId() {
        return manifestId;
    }

    public void setManifestId(String manifestId) {
        this.manifestId = manifestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
