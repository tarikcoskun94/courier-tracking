package com.migrosone.couriertracking.mapper;

import com.migrosone.couriertracking.dto.EmbeddedAuditDTO;
import com.migrosone.couriertracking.entity.EmbeddedAudit;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedAuditMapper {

    public EmbeddedAudit toAudit(EmbeddedAuditDTO dto) {
        if (dto == null) {
            return null;
        }

        EmbeddedAudit embeddedAudit = new EmbeddedAudit();
        embeddedAudit.setCreatorUser(dto.getCreatorUser());
        embeddedAudit.setCreationDate(dto.getCreationDate());
        embeddedAudit.setUpdaterUser(dto.getUpdaterUser());
        embeddedAudit.setUpdateDate(dto.getUpdateDate());

        return embeddedAudit;
    }

    public EmbeddedAuditDTO toDto(EmbeddedAudit embeddedAudit) {
        if (embeddedAudit == null) {
            return null;
        }

        EmbeddedAuditDTO dto = new EmbeddedAuditDTO();
        dto.setCreatorUser(embeddedAudit.getCreatorUser());
        dto.setCreationDate(embeddedAudit.getCreationDate());
        dto.setUpdaterUser(embeddedAudit.getUpdaterUser());
        dto.setUpdateDate(embeddedAudit.getUpdateDate());

        return dto;
    }
}
