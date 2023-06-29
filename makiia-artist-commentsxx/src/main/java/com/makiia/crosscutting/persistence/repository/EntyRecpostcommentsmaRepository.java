package com.makiia.crosscutting.persistence.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;

public interface EntyRecpostcommentsmaRepository extends JpaRepository<EntyRecpostcommentsma,Integer>
{
        String FILTER_RECUNIKEYREMC_QUERY = "select c from EntyRecpostcommentsma c  where c.recIdeunikeyRcom  = ?1  and c.recRegisstateRcom = 1 order by c.recOrdviewkeyRcom DESC";
        @Query(value = FILTER_RECUNIKEYREMC_QUERY)
        Page<EntyRecpostcommentsma> findByRecIdeunikeyRcom(Integer filter, Pageable pageable);
        
        String FILTER_RECPROFILPKEYRCOM_QUERY = "select c from EntyRecpostcommentsma c where  UPPER(c.recProfilpkeyRcom) like concat('%',upper(?1),'%') and c.recRegisstateRcom = 1 order by c.recOrdviewkeyRcom DESC";
        @Query(value = FILTER_RECPROFILPKEYRCOM_QUERY)
        Page<EntyRecpostcommentsma> findByRecProfilpkeyRcom(String filter, Pageable pageable);

}