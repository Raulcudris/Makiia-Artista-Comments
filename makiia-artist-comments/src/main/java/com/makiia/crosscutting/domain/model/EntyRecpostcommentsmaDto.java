package com.makiia.crosscutting.domain.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private String  recRegisstateRcom;

}
