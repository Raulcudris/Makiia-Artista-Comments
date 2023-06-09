package com.makiia.modules.comments.dataproviders.jpa;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaDto;
import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaResponse;
import com.makiia.crosscutting.domain.model.EntyUserCommentDto;
import com.makiia.crosscutting.domain.model.PaginationResponse;
import com.makiia.crosscutting.exceptions.DataProvider;
import com.makiia.crosscutting.exceptions.ExceptionBuilder;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.messages.SearchMessages;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;
import com.makiia.crosscutting.persistence.repository.EntyRecpostcommentsmaRepository;
import com.makiia.modules.comments.dataproviders.IjpaEntyRecpostcommentsmaDataProviders;

@DataProvider
public class JpaEntyRecpostcommentsmaDataProviders implements IjpaEntyRecpostcommentsmaDataProviders {

    @Autowired
    private EntyRecpostcommentsmaRepository repository;
    @Autowired
    @Qualifier("entyRecpostcommentsmaSaveResponseTranslate")
    private Translator<EntyRecpostcommentsma, EntyRecpostcommentsmaDto>saveResponseTranslate;
    @Autowired
    @Qualifier("entyRecpostcommentsmaDtoToEntityTranslate")
    private Translator<EntyRecpostcommentsmaDto, EntyRecpostcommentsma>dtoToEntityTranslate;

    @Override
    public EntyRecpostcommentsmaResponse getAll() throws EBusinessException {
        try {
            List<EntyRecpostcommentsma> responsesComments = (List<EntyRecpostcommentsma>) repository.findAll();
        
            int currentPage=0;
            int totalPageSize=responsesComments.size();
            Pageable pageable = PageRequest.of(currentPage, totalPageSize);
            //Pageable paginacion
            Page<EntyRecpostcommentsma> responsePage = null;
            EntyUserCommentDto responseUserComment = null;
            responsePage = repository.findAll(pageable);
                
            List<EntyRecpostcommentsma> ListPage = responsePage.getContent();            
            List<EntyRecpostcommentsmaDto> content  = ListPage.stream().map(p ->mapToDto(p)).collect(Collectors.toList());        
            EntyRecpostcommentsmaResponse response = new EntyRecpostcommentsmaResponse();
            
      
            response.setRspMessage(response.getRspMessage());
            response.setRspValue(response.getRspValue());

            currentPage = currentPage + 1;
            String nextPageUrl = "LocalHost";
            String previousPageUrl = "LocalHost";
            response.setRspPagination(headResponse(currentPage, content.size(), responsePage.getTotalElements(), responsePage.getTotalPages(), ResponsePage.hasNext(), ResponsePage.hasPrevious(), nextPageUrl, previousPageUrl));
            response.setRspData(content);
            return response;

        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }


    @Override
    public EntyRecpostcommentsmaResponse getAll(int currentPage , int totalPageSize , int parameter, String filter) throws EBusinessException {
        try {
            currentPage = currentPage - 1;
            Pageable pageable = PageRequest.of(currentPage, totalPageSize);
            Page<EntyRecpostcommentsma> ResponsePage = null;
            if (parameter == 0) {
                ResponsePage = repository.findByRecIdentifkeyRcom(filter, pageable);
            }else {
                ResponsePage = repository.findByRecIdeunikeyRcom(parameter,pageable);
            }

            List<EntyRecpostcommentsma> ListPage = ResponsePage.getContent();
            List<EntyRecpostcommentsmaDto> content  = ListPage.stream().map(p ->mapToDto(p)).collect(Collectors.toList());

            EntyRecpostcommentsmaResponse response = new EntyRecpostcommentsmaResponse();
            response.setRspMessage(response.getRspMessage());
            response.setRspValue(response.getRspValue());

            currentPage = currentPage + 1;
            String nextPageUrl = "LocalHost";
            String previousPageUrl = "LocalHost";
            response.setRspPagination(headResponse(currentPage, content.size(), ResponsePage.getTotalElements(), ResponsePage.getTotalPages(), ResponsePage.hasNext(), ResponsePage.hasPrevious(), nextPageUrl, previousPageUrl));
            response.setRspData(content);
            return response;


        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }
    @Override
    public EntyRecpostcommentsmaDto get(Integer id) throws EBusinessException {
        try {
            return saveResponseTranslate.translate(repository.findById(id).get());
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.SEARCH_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.SEARCH_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    @Override
    public EntyRecpostcommentsmaDto save(EntyRecpostcommentsmaDto dto) throws EBusinessException {
        try {
            return saveResponseTranslate.translate(repository.save(dtoToEntityTranslate.translate(dto)));
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }


    @Override
    public List<EntyRecpostcommentsmaDto> save(List<EntyRecpostcommentsmaDto> dtos) throws EBusinessException {
        try {
            List<EntyRecpostcommentsma> entities = new ArrayList<>();

            for (EntyRecpostcommentsmaDto dto : dtos) {
                entities.add(dtoToEntityTranslate.translate(dto));
            }
            dtos = new ArrayList<>();
            for (EntyRecpostcommentsma entity : repository.saveAll(entities)) {
                dtos.add(saveResponseTranslate.translate(entity));
            }
            return dtos;
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.CREATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.CREATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }


    @Override
    public EntyRecpostcommentsmaDto update(Integer id, EntyRecpostcommentsmaDto dto) throws EBusinessException {
        try {
            EntyRecpostcommentsma entity = dtoToEntityTranslate.translate(dto);
            EntyRecpostcommentsma old = repository.findById(id).get();

            old.setRecIdeunikeyRcom(
                    Objects.nonNull(dto.getRecIdeunikeyRcom())&& !entity.getRecIdeunikeyRcom().equals(0)
                            ? entity.getRecIdeunikeyRcom()
                            :old.getRecIdeunikeyRcom());

            old.setRecIdentifkeyRcom(
                    Objects.nonNull(dto.getRecIdentifkeyRcom())&& !entity.getRecIdentifkeyRcom().isEmpty()
                            ? entity.getRecIdentifkeyRcom()
                            :old.getRecIdentifkeyRcom());

            old.setRecProfiltypeRcom(
                    Objects.nonNull(dto.getRecProfiltypeRcom())&& !entity.getRecProfiltypeRcom().isEmpty()
                            ? entity.getRecProfiltypeRcom()
                            :old.getRecProfiltypeRcom());

            old.setRecProfilpkeyRcom(
                    Objects.nonNull(dto.getRecProfilpkeyRcom())&& !entity.getRecProfilpkeyRcom().isEmpty()
                            ? entity.getRecProfilpkeyRcom()
                            :old.getRecProfilpkeyRcom());

            old.setRecProftypecmRcom(
                    Objects.nonNull(dto.getRecProftypecmRcom())&& !entity.getRecProftypecmRcom().isEmpty()
                            ? entity.getRecProftypecmRcom()
                            :old.getRecProftypecmRcom());

            old.setRecIdentifkeyReus(
                    Objects.nonNull(dto.getRecIdentifkeyReus())&& !entity.getRecIdentifkeyReus().isEmpty()
                            ? entity.getRecIdentifkeyReus()
                            :old.getRecIdentifkeyReus());

            old.setRecIdentifkeyReus(
                    Objects.nonNull(dto.getRecIdentifkeyReus())&& !entity.getRecIdentifkeyReus().isEmpty()
                            ? entity.getRecIdentifkeyReus()
                            :old.getRecIdentifkeyReus());

            old.setApjIdentifkeyAphp(
                    Objects.nonNull(dto.getApjIdentifkeyAphp())&& !entity.getApjIdentifkeyAphp().isEmpty()
                    ? entity.getApjIdentifkeyAphp()
                    :old.getApjIdentifkeyAphp());

            old.setRecTreemlevelRcom(
                    Objects.nonNull(dto.getRecTreemlevelRcom())&& !entity.getRecTreemlevelRcom().isEmpty()
                            ? entity.getRecTreemlevelRcom()
                            :old.getRecTreemlevelRcom());

            old.setRecTreemkeymsRcom(
                    Objects.nonNull(dto.getRecTreemkeymsRcom())&& !entity.getRecTreemkeymsRcom().isEmpty()
                            ? entity.getRecTreemkeymsRcom()
                            :old.getRecTreemkeymsRcom());

            old.setRecTreemcountRcom(
                    Objects.nonNull(dto.getRecTreemcountRcom())&& !entity.getRecTreemcountRcom().equals(0)
                            ? entity.getRecTreemcountRcom()
                            :old.getRecTreemcountRcom());

            old.setRecMessagorigRcom(
                    Objects.nonNull(dto.getRecMessagorigRcom())&& !entity.getRecMessagorigRcom().isEmpty()
                            ? entity.getRecMessagorigRcom()
                            :old.getRecMessagorigRcom());

            old.setRecMessagtypeRcom(
                    Objects.nonNull(dto.getRecMessagtypeRcom())&& !entity.getRecMessagtypeRcom().isEmpty()
                            ? entity.getRecMessagtypeRcom()
                            :old.getRecMessagtypeRcom());

            old.setRecMessagtconRcom(
                    Objects.nonNull(dto.getRecMessagtconRcom())&& !entity.getRecMessagtconRcom().isEmpty()
                            ? entity.getRecMessagtconRcom()
                            :old.getRecMessagtconRcom());

            old.setRecMessagbodyRcom(
                    Objects.nonNull(dto.getRecMessagbodyRcom())&& !entity.getRecMessagbodyRcom().isEmpty()
                            ? entity.getRecMessagbodyRcom()
                            :old.getRecMessagbodyRcom());

            old.setRecMessagdateRcom(
                    Objects.nonNull(dto.getRecMessagdateRcom())&& !entity.getRecMessagdateRcom().equals(0)
                            ? entity.getRecMessagdateRcom()
                            :old.getRecMessagdateRcom());

            old.setRecMessagtimeRcom(
                    Objects.nonNull(dto.getRecMessagtimeRcom())&& !entity.getRecMessagtimeRcom().equals(0)
                            ? entity.getRecMessagtimeRcom()
                            :old.getRecMessagtimeRcom());

            old.setRecEditmddateRcom(
                    Objects.nonNull(dto.getRecEditmddateRcom())&& !entity.getRecEditmddateRcom().equals(0)
                            ? entity.getRecEditmddateRcom()
                            :old.getRecEditmddateRcom());

            old.setRecEditmdtimeRcom(
                    Objects.nonNull(dto.getRecEditmdtimeRcom())&& !entity.getRecEditmdtimeRcom().equals(0)
                            ? entity.getRecEditmdtimeRcom()
                            :old.getRecEditmdtimeRcom());

            old.setRecEditdedateRcom(
                    Objects.nonNull(dto.getRecEditdedateRcom())&& !entity.getRecEditdedateRcom().equals(0)
                            ? entity.getRecEditdedateRcom()
                            :old.getRecEditdedateRcom());

            old.setRecEditdetimeRcom(
                    Objects.nonNull(dto.getRecEditdetimeRcom())&& !entity.getRecEditdetimeRcom().equals(0)
                            ? entity.getRecEditdetimeRcom()
                            :old.getRecEditdetimeRcom());

            old.setRecClicpositiRcom(
                    Objects.nonNull(dto.getRecClicpositiRcom())&& !entity.getRecClicpositiRcom().equals(0)
                            ? entity.getRecClicpositiRcom()
                            :old.getRecClicpositiRcom());

            old.setRecClicnegatvRcom(
                    Objects.nonNull(dto.getRecClicnegatvRcom())&& !entity.getRecClicnegatvRcom().equals(0)
                            ? entity.getRecClicnegatvRcom()
                            :old.getRecClicnegatvRcom());

            old.setRecClicklegalRcom(
                    Objects.nonNull(dto.getRecClicklegalRcom())&& !entity.getRecClicklegalRcom().equals(0)
                            ? entity.getRecClicklegalRcom()
                            :old.getRecClicklegalRcom());

            old.setRecCheckmarckRcom(
                    Objects.nonNull(dto.getRecCheckmarckRcom())&& !entity.getRecCheckmarckRcom().isEmpty()
                            ? entity.getRecCheckmarckRcom()
                            :old.getRecCheckmarckRcom());

            old.setRecCheckmdateRcom(
                    Objects.nonNull(dto.getRecCheckmdateRcom())&& !entity.getRecCheckmdateRcom().equals(0)
                            ? entity.getRecCheckmdateRcom()
                            :old.getRecCheckmdateRcom());

            old.setRecCheckmtimeRcom(
                    Objects.nonNull(dto.getRecCheckmtimeRcom())&& !entity.getRecCheckmtimeRcom().equals(0)
                            ? entity.getRecCheckmtimeRcom()
                            :old.getRecCheckmtimeRcom());

            old.setRecOrdviewkeyRcom(
                    Objects.nonNull(dto.getRecOrdviewkeyRcom())&& !entity.getRecOrdviewkeyRcom().equals(0)
                            ? entity.getRecOrdviewkeyRcom()
                            :old.getRecOrdviewkeyRcom());

            old.setRecRegisstateRcom(
                    Objects.nonNull(dto.getRecRegisstateRcom())&& !entity.getRecRegisstateRcom().isEmpty()
                            ? entity.getRecRegisstateRcom()
                            :old.getRecRegisstateRcom());

            return saveResponseTranslate.translate(repository.save(old));
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.UPDATE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.UPDATE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();

        }
    }

    @Override
    public void delete(Integer id) throws EBusinessException {
        try {
            repository.delete(repository.findById(id).get());
        } catch (PersistenceException | DataAccessException e) {
            throw ExceptionBuilder.builder()
                    .withMessage(SearchMessages.DELETE_ERROR_DESCRIPTION)
                    .withCode(SearchMessages.DELETE_ERROR_ID)
                    .withParentException(e)
                    .buildBusinessException();
        }
    }

    private EntyRecpostcommentsmaDto mapToDto(EntyRecpostcommentsma entyRecpostcommentsma){
        EntyRecpostcommentsmaDto dto = new EntyRecpostcommentsmaDto();
   

        dto.setRecIdeunikeyRcom(entyRecpostcommentsma.getRecIdeunikeyRcom());
        dto.setRecIdentifkeyRcom(entyRecpostcommentsma.getRecIdentifkeyRcom());
        dto.setRecProfiltypeRcom(entyRecpostcommentsma.getRecProfiltypeRcom());
        dto.setRecProfilpkeyRcom(entyRecpostcommentsma.getRecProfilpkeyRcom());
        dto.setRecProftypecmRcom(entyRecpostcommentsma.getRecProftypecmRcom());
        dto.setRecIdentifkeyReus(entyRecpostcommentsma.getRecIdentifkeyReus());    
        dto.setApjIdentifkeyAphp(entyRecpostcommentsma.getApjIdentifkeyAphp());
        dto.setRecTreemlevelRcom(entyRecpostcommentsma.getRecTreemlevelRcom());
        dto.setRecTreemkeymsRcom(entyRecpostcommentsma.getRecTreemkeymsRcom());
        dto.setRecTreemcountRcom(entyRecpostcommentsma.getRecTreemcountRcom());
        dto.setRecMessagorigRcom(entyRecpostcommentsma.getRecMessagorigRcom());
        dto.setRecMessagtypeRcom(entyRecpostcommentsma.getRecMessagtypeRcom());
        dto.setRecMessagtconRcom(entyRecpostcommentsma.getRecMessagtconRcom());
        dto.setRecMessagbodyRcom(entyRecpostcommentsma.getRecMessagbodyRcom());
        dto.setRecMessagdateRcom(entyRecpostcommentsma.getRecMessagdateRcom());
        dto.setRecMessagtimeRcom(entyRecpostcommentsma.getRecMessagtimeRcom());
        dto.setRecEditmddateRcom(entyRecpostcommentsma.getRecEditmddateRcom());
        dto.setRecEditmdtimeRcom(entyRecpostcommentsma.getRecEditmdtimeRcom());
        dto.setRecEditdedateRcom(entyRecpostcommentsma.getRecEditdedateRcom());
        dto.setRecEditdetimeRcom(entyRecpostcommentsma.getRecEditdetimeRcom());
        dto.setRecClicpositiRcom(entyRecpostcommentsma.getRecClicpositiRcom());
        dto.setRecClicnegatvRcom(entyRecpostcommentsma.getRecClicnegatvRcom());
        dto.setRecClicklegalRcom(entyRecpostcommentsma.getRecClicklegalRcom());
        dto.setRecCheckmarckRcom(entyRecpostcommentsma.getRecCheckmarckRcom());
        dto.setRecCheckmdateRcom(entyRecpostcommentsma.getRecCheckmdateRcom());
        dto.setRecCheckmtimeRcom(entyRecpostcommentsma.getRecCheckmtimeRcom());
        dto.setRecOrdviewkeyRcom(entyRecpostcommentsma.getRecOrdviewkeyRcom());
        dto.setRecRegisstateRcom(entyRecpostcommentsma.getRecRegisstateRcom());
        return  dto;
    }

    public static PaginationResponse headResponse(int currentPage    , int totalPageSize ,
                                                  long totalResults  , int totalPages,
                                                  boolean hasNextPage, boolean hasPreviousPage,
                                                  String nextpageUrl , String previousPageUrl )
    {
        return PaginationResponse.builder()
                .currentPage(currentPage)
                .totalPageSize(totalPageSize)
                .totalResults(totalResults)
                .totalPages(totalPages)
                .hasNextPage(hasNextPage)
                .hasPreviousPage(hasPreviousPage)
                .nextPageUrl(nextpageUrl)
                .previousPageUrl(previousPageUrl)
                .build();

    }
}
