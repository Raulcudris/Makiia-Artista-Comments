package com.makiia.crosscutting.utils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makiia.crosscutting.domain.constants.Constants;

import lombok.Builder;
import lombok.experimental.UtilityClass;

@UtilityClass
@Builder
public final class JsonUtil {
    private static final Gson GSON_IDENTITY = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setDateFormat(Constants.ISO_DATE_TIME_FORMAT).setPrettyPrinting().create();
    @Builder.Default
    private static final Gson GSON_UNDERSCORES = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(Constants.ISO_DATE_TIME_FORMAT).setPrettyPrinting().create();

    public static <T extends Object> T fromJsonIdentity(final Object json, final Class<T> classOfT) {
        return GSON_IDENTITY.fromJson(GSON_IDENTITY.toJson(json), classOfT);
    }


}
