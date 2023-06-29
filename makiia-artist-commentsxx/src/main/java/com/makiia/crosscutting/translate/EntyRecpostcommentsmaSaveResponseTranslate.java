package com.makiia.crosscutting.translate;

import org.springframework.stereotype.Component;

import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;
import com.makiia.crosscutting.utils.GsonUtil;
@Component
public class EntyRecpostcommentsmaSaveResponseTranslate implements Translator<EntyRecpostcommentsma, EntyRecpostcommentsmaDto> {
    @Override
    public EntyRecpostcommentsmaDto translate(EntyRecpostcommentsma input) throws EBusinessException {
        return GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(input), EntyRecpostcommentsmaDto.class);
    }
}
