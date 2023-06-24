package com.makiia.crosscutting.translate;

import org.springframework.stereotype.Component;

import com.makiia.crosscutting.domain.model.EntyRecmaesusuarimaDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecmaesusuarima;
import com.makiia.crosscutting.utils.GsonUtil;

@Component
public class EntyRecmaesusuarimaSaveResponseTranslate implements Translator<EntyRecmaesusuarima,EntyRecmaesusuarimaDto> {

    @Override
    public EntyRecmaesusuarimaDto translate(EntyRecmaesusuarima input) throws EBusinessException {
    return GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(input), EntyRecmaesusuarimaDto.class);
    }
    
}
