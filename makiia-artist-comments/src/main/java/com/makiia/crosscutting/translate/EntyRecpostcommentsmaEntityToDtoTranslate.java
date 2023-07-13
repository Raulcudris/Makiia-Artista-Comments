package com.makiia.crosscutting.translate;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.makiia.crosscutting.domain.model.EntyRecmaesusuarimaDto;
import com.makiia.crosscutting.domain.model.EntyRecpostcommentsmaDto;
import com.makiia.crosscutting.exceptions.Main.EBusinessException;
import com.makiia.crosscutting.patterns.Translator;
import com.makiia.crosscutting.persistence.entity.EntyRecpostcommentsma;
import com.makiia.crosscutting.utils.GsonUtil;
@Component
public class EntyRecpostcommentsmaEntityToDtoTranslate implements Translator<EntyRecpostcommentsma, EntyRecpostcommentsmaDto> {
    @Override
    public EntyRecpostcommentsmaDto translate(EntyRecpostcommentsma input) throws EBusinessException {

        EntyRecpostcommentsmaDto output = GsonUtil.getGson().fromJson(GsonUtil.getGson().toJson(input), EntyRecpostcommentsmaDto.class);

        if (Objects.nonNull(input.getRegUsers())) {
            output.setRegUsers(EntyRecmaesusuarimaDto.builder()
                .recIdeunikeyReus(input.getRegUsers().getRecIdeunikeyReus()) 
                .recNroregReus(input.getRegUsers().getRecNroregReus()) 
                .recNiknamReus(input.getRegUsers().getRecNiknamReus()) 
                .recNomusuReus(input.getRegUsers().getRecNomusuReus())
                .recImgvisReus(input.getRegUsers().getRecImgvisReus()).build());
        }

        return output;
    }
}
