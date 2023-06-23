package com.makiia.modules.comments.usecase;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.makiia.crosscutting.domain.model.EntyCommentUtiliDto;
import com.makiia.crosscutting.domain.model.EntyDeleteDto;
import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaDto;
import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaResponse;
import com.makiia.crosscutting.exceptions.ExceptionBuilder;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.messages.SearchMessages;
import com.makiia.modules.comments.dataproviders.jpa.JpaEntyRecpostcommentsmaDataProviders;
import com.makiia.modules.comments.services.UseCase;
import com.makiia.modules.comments.services.UsecaseServices;

@UseCase
public class EntyRecpostcommentsmaService extends UsecaseServices<EntyRecpostcommentsmaDto, JpaEntyRecpostcommentsmaDataProviders>
{
    @Autowired
    private JpaEntyRecpostcommentsmaDataProviders jpaDataProviders;
    @PostConstruct
    public void init(){
        this.ijpaDataProvider = jpaDataProviders;
    }
    private String localYear;
    public LocalDate localDateNow;
    public LocalDate localDateDefault;
    private Double localTimeDefault;
    private Double localTimeNow;
    private String dateNowWhitTime;
    private String timeNowHourMin;
    private Long ordeView;
    private int year;

    public EntyRecpostcommentsmaResponse saveBefore(EntyRecpostcommentsmaResponse dto) throws EBusinessException {
        try {
            List<EntyRecpostcommentsmaDto> dtoAux = this.ijpaDataProvider.save(dto.getRspData());
            localYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
            localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dateNowWhitTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
            timeNowHourMin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm"));
            localTimeNow = Double.valueOf(timeNowHourMin);
            localDateDefault = LocalDate.parse(LocalDate.of(0001,01,01).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            localTimeDefault = 0.0;
            ordeView = Long.valueOf(dateNowWhitTime);
            year = Integer.parseInt(localYear);
            for(EntyRecpostcommentsmaDto dtox : dtoAux){
                dtox.setRecIdentifkeyRcom(year+""+dtox.getRecIdeunikeyRcom());
                dtox.setRecMessagdateRcom(localDateNow);
                dtox.setRecMessagtimeRcom(localTimeNow );
                dtox.setRecEditmddateRcom(localDateNow);
                dtox.setRecEditmdtimeRcom(localTimeNow);
                dtox.setRecEditdedateRcom(localDateDefault);
                dtox.setRecEditdetimeRcom(localTimeDefault);
                dtox.setRecCheckmarckRcom("1");
                dtox.setRecCheckmdateRcom(localDateNow);
                dtox.setRecCheckmtimeRcom(localTimeNow);
                dtox.setRecRegisstateRcom("1");
                dtox.setRecOrdviewkeyRcom(ordeView);
            }
            dtoAux = this.ijpaDataProvider.save(dtoAux);
            dto.setRspData(dtoAux);
            dto.setRspValue("OK");
            dto.setRspMessage("OK");
            dto.setRspParentKey("NA");
            dto.setRspAppKey("NA");
            return dto;

        }catch (PersistenceException | DataAccessException e){
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    public EntyRecpostcommentsmaResponse updateAll(EntyRecpostcommentsmaResponse dto) throws EBusinessException {
        try {
            List<EntyRecpostcommentsmaDto> dtoAux = dto.getRspData();
            localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            timeNowHourMin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm"));
            localTimeNow = Double.valueOf(timeNowHourMin);
            localDateDefault = LocalDate.parse(LocalDate.of(0001,01,01).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            localTimeDefault = 0.0;
            for (EntyRecpostcommentsmaDto dtox : dtoAux){
                dtox.setRecEditmddateRcom(localDateNow);
                dtox.setRecEditmdtimeRcom(localTimeNow);
                dtox.setRecEditdedateRcom(localDateDefault);
                dtox.setRecEditdetimeRcom(localTimeDefault);
                dtox = this.ijpaDataProvider.update(dtox.getRecIdeunikeyRcom(),dtox);
            }
            dto.setRspValue("OK");
            dto.setRspMessage("OK");
            dto.setRspParentKey("NA");
            dto.setRspAppKey("NA");
            dto.setRspData(dtoAux);           
            return dto;

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }


    public String changestatusAll(List<EntyCommentUtiliDto> dto) throws EBusinessException {
        try {
            localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            timeNowHourMin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mms"));
            localTimeNow = Double.valueOf(timeNowHourMin);
            EntyRecpostcommentsmaDto rspto = new EntyRecpostcommentsmaDto();

            for (EntyCommentUtiliDto dtox : dto) {
                rspto = this.ijpaDataProvider.get(dtox.getRecPKey());
                rspto.setRecEditdedateRcom(localDateNow);
                rspto.setRecEditdetimeRcom(localTimeNow);
                rspto.setRecRegisstateRcom("2");
                this.ijpaDataProvider.update(dtox.getRecPKey(),rspto);
            }
            return "OK";

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }
    public String deleteAll(List<EntyDeleteDto> dto) throws EBusinessException {
        try {

            for (EntyDeleteDto dtox : dto) {
                this.ijpaDataProvider.delete(dtox.getRecPKey());
            }
            return "OK";

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.DELETE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.DELETE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }


}
