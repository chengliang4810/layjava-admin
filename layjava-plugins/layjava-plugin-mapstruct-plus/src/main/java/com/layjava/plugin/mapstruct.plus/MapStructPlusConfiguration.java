package com.layjava.plugin.mapstruct.plus;

import io.github.linpeilie.Converter;
import io.github.linpeilie.ConverterFactory;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Condition;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.AppContext;

@Configuration
public class MapStructPlusConfiguration {

    @Bean
    @Condition(onMissingBean = Converter.class)
    public Converter converter(final ConverterFactory converterFactory) {
        return new Converter(converterFactory);
    }

    @Bean
    @Condition(onMissingBean = ConverterFactory.class)
    public ConverterFactory converterFactory(final AppContext aopContext) {
        return new SolonConverterFactory(aopContext);
    }

}
