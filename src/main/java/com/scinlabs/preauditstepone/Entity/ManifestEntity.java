package com.scinlabs.preauditstepone.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "manifest_test")
public class ManifestEntity {
    @Id
    @Column(name = "mfst_tracking_id")
    private String mfstTrackingId;

    public String getMfstTrackingId() {
        return mfstTrackingId;
    }

    public void setMfstTrackingId(String mfstTrackingId) {
        this.mfstTrackingId = mfstTrackingId;
    }
}
