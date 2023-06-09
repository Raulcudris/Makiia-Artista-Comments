package com.makiia.crosscutting.domain.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "recmaesusuarima")
public class EntyUserCommentDto {
    private String recIdentifkeyReus;
    private String recImgvisReus;
    private String recNomusuReus;
}
