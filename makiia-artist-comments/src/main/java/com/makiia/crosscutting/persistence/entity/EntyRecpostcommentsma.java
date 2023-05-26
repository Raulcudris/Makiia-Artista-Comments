package com.makiia.crosscutting.persistence.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recpostcommentsma")
public class EntyRecpostcommentsma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rec_ideunikey_rcom")
    private Integer  recIdeunikeyRcom;

    @Basic(optional = false)
    @Column(name = "rec_identifkey_rcom")
    private String recIdentifkeyRcom;

    @Basic(optional = false)
    @Column(name = "rec_profiltype_rcom")
    private String recProfiltypeRcom;

    @Basic(optional = false)
    @Column(name = "rec_profilpkey_rcom")
    private String  recProfilpkeyRcom;

    @Basic(optional = false)
    @Column(name = "rec_proftypecm_rcom")
    private String  recProftypecmRcom;

    @Basic(optional = false)
    @Column(name = "rec_identifkey_reus")
    private String  recIdentifkeyReus;

    @Basic(optional = false)
    @Column(name = "apj_identifkey_aphp")
    private String  apjIdentifkeyAphp;

    @Basic(optional = false)
    @Column(name = "rec_treemlevel_rcom")
    private String  recTreemlevelRcom;

    @Basic(optional = false)
    @Column(name = "rec_treemkeyms_rcom")
    private String  recTreemkeymsRcom;

    @Basic(optional = false)
    @Column(name = "rec_treemcount_rcom")
    private Integer  recTreemcountRcom;

    @Basic(optional = false)
    @Column(name = "rec_messagorig_rcom")
    private String  recMessagorigRcom;

    @Basic(optional = false)
    @Column(name = "rec_messagtype_rcom")
    private String  recMessagtypeRcom;

    @Basic(optional = false)
    @Column(name = "rec_messagtcon_rcom")
    private String  recMessagtconRcom;

    @Basic(optional = false)
    @Column(name = "rec_messagbody_rcom")
    private String recMessagbodyRcom;

    @Basic(optional = false)
    @Column(name = "rec_messagdate_rcom")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate recMessagdateRcom;

    @Basic(optional = false)
    @Column(name = "rec_messagtime_rcom")
    private Double  recMessagtimeRcom;

    @Basic(optional = false)
    @Column(name = "rec_editmddate_rcom")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate recEditmddateRcom;

    @Basic(optional = false)
    @Column(name = "rec_editmdtime_rcom")
    private Double  recEditmdtimeRcom;

    @Basic(optional = false)
    @Column(name = "rec_editdedate_rcom")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  recEditdedateRcom;

    @Basic(optional = false)
    @Column(name = "rec_editdetime_rcom")
    private Double  recEditdetimeRcom;

    @Basic(optional = false)
    @Column(name = "rec_clicpositi_rcom")
    private Integer  recClicpositiRcom;

    @Basic(optional = false)
    @Column(name = "rec_clicnegatv_rcom")
    private Integer  recClicnegatvRcom;

    @Basic(optional = false)
    @Column(name = "rec_clicklegal_rcom")
    private Integer  recClicklegalRcom;

    @Basic(optional = false)
    @Column(name = "rec_checkmarck_rcom")
    private String  recCheckmarckRcom;

    @Basic(optional = false)
    @Column(name = "rec_checkmdate_rcom")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate  recCheckmdateRcom;

    @Basic(optional = false)
    @Column(name = "rec_checkmtime_rcom")
    private Double  recCheckmtimeRcom;

    @Basic(optional = false)
    @Column(name = "rec_ordviewkey_rcom")
    private Long  recOrdviewkeyRcom;

    @Basic(optional = false)
    @Column(name = "rec_regisstate_rcom")
    private String  recRegisstateRcom;

}
