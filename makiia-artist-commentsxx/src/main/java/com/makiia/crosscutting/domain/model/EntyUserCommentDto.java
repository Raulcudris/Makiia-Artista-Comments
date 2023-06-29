package com.makiia.crosscutting.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntyUserCommentDto {
    private String imgUser;
    private String nomUsuer;
}
