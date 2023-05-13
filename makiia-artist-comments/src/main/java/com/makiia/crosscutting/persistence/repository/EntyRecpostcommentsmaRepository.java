package com.makiia.crosscutting.persistence.repository;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface EntyRecpostcommentsmaRepository extends JpaRepository<EntyRecpostcommentsma,Integer>
{
        String FILTER_USUARIO_RECUNIKEYREMC_QUERY = "select c from EntyRecpostcommentsma c  where c.recIdeunikeyRcom  = ?1";
        @Query(value = FILTER_USUARIO_RECUNIKEYREMC_QUERY)
        Page<EntyRecpostcommentsma> findByRecIdeunikeyRcom(Integer parameter, Pageable pageable);
        String FILTER_USUARIO_RECNROREGREMC_QUERY = "select c from EntyRecpostcommentsma c where UPPER(c.recIdentifkeyRcom) like concat('%',upper(?1),'%')";
        @Query(value = FILTER_USUARIO_RECNROREGREMC_QUERY)
        Page<EntyRecpostcommentsma> findByRecIdentifkeyRcom(String filter, Pageable pageable);

}
