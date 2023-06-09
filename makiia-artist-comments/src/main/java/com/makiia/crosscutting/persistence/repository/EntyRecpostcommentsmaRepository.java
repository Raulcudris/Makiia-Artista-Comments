package com.makiia.crosscutting.persistence.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.makiia.crosscutting.domain.model.EntyUserCommentDto;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;

public interface EntyRecpostcommentsmaRepository extends JpaRepository<EntyRecpostcommentsma,Integer>
{
        String FILTER_USUARIO_RECUNIKEYREMC_QUERY = "select c from EntyRecpostcommentsma c  where c.recIdeunikeyRcom  = ?1  and c.recRegisstateRcom = 1 order by c.recOrdviewkeyRcom DESC";
        @Query(value = FILTER_USUARIO_RECUNIKEYREMC_QUERY)
        Page<EntyRecpostcommentsma> findByRecIdeunikeyRcom(Integer parameter, Pageable pageable);
        
        String FILTER_USUARIO_RECNROREGREMC_QUERY = "select c from EntyRecpostcommentsma c where  UPPER(c.recProfilpkeyRcom) like concat('%',upper(?1),'%') and c.recRegisstateRcom = 1 order by c.recOrdviewkeyRcom DESC";
        @Query(value = FILTER_USUARIO_RECNROREGREMC_QUERY)
        Page<EntyRecpostcommentsma> findByRecIdentifkeyRcom(String filter, Pageable pageable);
         
        String FILTER_USUARIO_COMENNTS_QUERY = "select c from EntyUserCommentDto c where UPPER(c.recIdentifkeyReus) = UPPER(?1)";
        @Query(value = FILTER_USUARIO_COMENNTS_QUERY)
        List<EntyUserCommentDto> findByRecIdentifkeyReus(String recIdentifkeyReus);

}