package com.makiia.crosscutting.translate;
import org.springframework.stereotype.Component;

import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;
import com.makiia.crosscutting.utils.GsonUtil;
@Component
public class EntyRecpostcommentsmaDtoToEntityTranslate implements Translator<EntyRecpostcommentsmaDto, EntyRecpostcommentsma> {

    @Override
    public EntyRecpostcommentsma translate(EntyRecpostcommentsmaDto input) throws EBusinessException {
        return GsonUtil.getGson(false)
                .fromJson(GsonUtil.getGson().toJson(input), EntyRecpostcommentsma.class);
    }
}
