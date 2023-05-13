package com.makiia.crosscutting.domain.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.sql.Date;

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
    private String  recIdentifkeyReus;
    private String  apjIdentifkeyAphp;
    private String  recTreemlevelRcom;
    private String  recTreemkeymsRcom;
    private Integer recTreemcountRcom;
    private Double  recMessagorigRcom;
    private String  recMessagtypeRcom;
    private String  recMessagtconRcom;
    private String  recMessagbodyRcom;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date    recMessagdateRcom;
    private Double  recMessagtimeRcom;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date    recEditmddateRcom;
    private Double  recEditmdtimeRcom;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date    recEditdedateRcom;
    private Double  recEditdetimeRcom;
    private Integer recClicpositiRcom;
    private Integer recClicnegatvRcom;
    private Integer recClicklegalRcom;
    private String  recCheckmarckRcom;
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date    recCheckmdateRcom;
    private Double  recCheckmtimeRcom;
    private Double  recOrdviewkeyRcom;
    private String  recRegisstateRcom;

}
