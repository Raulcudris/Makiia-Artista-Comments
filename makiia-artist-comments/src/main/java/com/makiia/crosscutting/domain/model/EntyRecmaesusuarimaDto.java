package com.makiia.crosscutting.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntyRecmaesusuarimaDto {
    private String recNroregReus;
    private String recNiknamReus;
    private String recNomusuReus;
    private String recImgvisReus;
}
