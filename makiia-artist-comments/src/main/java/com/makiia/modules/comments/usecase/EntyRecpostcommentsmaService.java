package com.makiia.modules.comments.usecase;
import com.makiia.crosscutting.domain.model.EntyDeleteDto;
import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaDto;
import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaResponse;
import com.makiia.crosscutting.exceptions.ExceptionBuilder;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.messages.SearchMessages;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;
import com.makiia.crosscutting.persistence.repository.EntyRecpostcommentsmaRepository;
import com.makiia.modules.bus.services.UseCase;
import com.makiia.modules.bus.services.UsecaseServices;
import com.makiia.modules.comments.dataproviders.jpa.JpaEntyRecpostcommentsmaDataProviders;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import javax.annotation.PostConstruct;
import javax.persistence.PersistenceException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Log4j2
@UseCase
public class EntyRecpostcommentsmaService extends UsecaseServices<EntyRecpostcommentsmaDto, JpaEntyRecpostcommentsmaDataProviders>
{
    @Autowired
    private JpaEntyRecpostcommentsmaDataProviders jpaDataProviders;
    @PostConstruct
    public void init(){
        this.ijpaDataProvider = jpaDataProviders;
    }
    public LocalDate localDateNow;
    public LocalDate localDateDefault;
    private Double localTimeDefault;
    private Double localTimeNow;
    private String dateNowWhitTime;
    private String timeNowHourMin;
    private Long ordeView;

    public EntyRecpostcommentsmaResponse saveBefore(EntyRecpostcommentsmaResponse dto) throws EBusinessException {
        try {
            List<EntyRecpostcommentsmaDto> dtoAux = this.ijpaDataProvider.save(dto.getRspData());
            //localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
          localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            dateNowWhitTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
            timeNowHourMin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm"));
            localTimeNow = Double.valueOf(timeNowHourMin);
            localDateDefault = LocalDate.parse(LocalDate.of(0001,01,01).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            localTimeDefault = 00.00;
            ordeView = Long.valueOf(dateNowWhitTime);
            for(EntyRecpostcommentsmaDto dtox : dtoAux){
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
            localTimeDefault = 00.00;
            for (EntyRecpostcommentsmaDto dtox : dtoAux){
                dtox.setRecEditmddateRcom(localDateNow);
                dtox.setRecEditmdtimeRcom(localTimeNow);
                dtox.setRecEditdedateRcom(localDateDefault);
                dtox.setRecEditdetimeRcom(localTimeDefault);
                dtox = this.ijpaDataProvider.update(dtox.getRecIdeunikeyRcom(),dtox);
            }
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


    public String changestatusAll(List<EntyDeleteDto> dto) throws EBusinessException {
        try {
            localDateNow = LocalDate.parse(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            timeNowHourMin = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mms"));
            localTimeNow = Double.valueOf(timeNowHourMin);
            EntyRecpostcommentsmaDto rspto = new EntyRecpostcommentsmaDto();

            for (EntyDeleteDto dtox : dto) {
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
