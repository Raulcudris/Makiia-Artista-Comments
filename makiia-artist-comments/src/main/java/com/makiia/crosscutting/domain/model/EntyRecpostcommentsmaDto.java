package com.makiia.crosscutting.domain.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EntyRecpostcommentsmaDto {

    private Integer  recIdeunikeyRcom;
    private String   recIdentifkeyRcom;
    private String  recProfiltypeRcom;
    private String  recProfilpkeyRcom;
    private String  recProftypecmRcom;
    private String  recIdentifkeyReus;
    private String  apjIdentifkeyAphp;
    private String  recTreemlevelRcom;
    private String  recTreemkeymsRcom;
    private Integer recTreemcountRcom;
    private String  recMessagorigRcom;
    private String  recMessagtypeRcom;
    private String  recMessagtconRcom;
    private String  recMessagbodyRcom;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMddHHmm")
    private LocalDateTime recMessagdateRcom;
    private Double  recMessagtimeRcom;
    @JsonFormat(pattern="yyyyMMdd")
    private LocalDate recEditmddateRcom;
    private Double  recEditmdtimeRcom;
    @JsonFormat(pattern="yyyyMMdd")
    private LocalDate  recEditdedateRcom;
    private Double  recEditdetimeRcom;
    private Integer recClicpositiRcom;
    private Integer recClicnegatvRcom;
    private Integer recClicklegalRcom;
    private String  recCheckmarckRcom;
    @JsonFormat(pattern="yyyyMMdd")
    private LocalDate    recCheckmdateRcom;
    private Double  recCheckmtimeRcom;
    private Double  recOrdviewkeyRcom;
    private String  recRegisstateRcom;

}
