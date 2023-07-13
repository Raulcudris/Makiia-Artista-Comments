package com.makiia.crosscutting.domain.model;
import java.time.LocalDate;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EntyRecpostcommentsmaDto {

    private Integer recIdeunikeyRcom;
    private String  recIdentifkeyRcom;
    private String  recProfiltypeRcom;
    private String  recProfilpkeyRcom;
    private String  recProftypecmRcom;
    private Integer recIdeunikeyReus;
    private String  apjIdentifkeyAphp;
    private String  recTreemlevelRcom;
    private String  recTreemkeymsRcom;
    private Integer recTreemcountRcom;
    private String  recMessagorigRcom;
    private String  recMessagtypeRcom;
    private String  recMessagtconRcom;
    private String  recMessagbodyRcom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate recMessagdateRcom;
    private Double  recMessagtimeRcom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate recEditmddateRcom;
    private Double  recEditmdtimeRcom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  recEditdedateRcom;
    private Double  recEditdetimeRcom;
    private Integer recClicpositiRcom;
    private Integer recClicnegatvRcom;
    private Integer recClicklegalRcom;
    private String  recCheckmarckRcom;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  recCheckmdateRcom;
    private Double  recCheckmtimeRcom;
    private Long  recOrdviewkeyRcom;
    private Integer  recIspriorityRcom;    
    private String  recRegisstateRcom;
    @ManyToOne(fetch = FetchType.LAZY)
    private EntyRecmaesusuarimaDto regUsers;

}
