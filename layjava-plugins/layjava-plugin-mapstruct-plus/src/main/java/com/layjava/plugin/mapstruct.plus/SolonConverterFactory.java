package com.layjava.plugin.mapstruct.plus;

import io.github.linpeilie.AbstractCachedConverterFactory;
import io.github.linpeilie.BaseMapMapper;
import io.github.linpeilie.BaseMapper;
import org.noear.solon.core.AppContext;

import java.util.HashMap;
import java.util.Map;

public class SolonConverterFactory extends AbstractCachedConverterFactory {

    private static final Map<String, BaseMapper> MAPPER_MAP = new HashMap<>();

    private static final Map<String, BaseMapMapper> MAP_MAPPER_MAP = new HashMap<>();

    public SolonConverterFactory(final AppContext context) {
        loadMapper(context);
    }

    private String getMapperKey(Class<?> sourceClass, Class<?> targetClass) {
        return sourceClass.getName() + "->" + targetClass.getName();
    }

    private String getMapMapperKey(Class<?> sourceClass) {
        return sourceClass.getName();
    }

    private void loadMapper(final AppContext context) {
        context.beanForeach(beanWrap -> {
            final Object raw = beanWrap.raw();
            if (raw instanceof BaseMapper) {
                final Class<?> sourceClass = GenericUtils.getInterfaceGenericType(raw.getClass(), 0);
                final Class<?> targetClass = GenericUtils.getInterfaceGenericType(raw.getClass(), 1);
                MAPPER_MAP.put(getMapperKey(sourceClass, targetClass), (BaseMapper) raw);
            } else if (raw instanceof BaseMapMapper) {
                final Class<?> sourceClass = GenericUtils.getInterfaceGenericType(raw.getClass(), 0);
                MAP_MAPPER_MAP.put(getMapMapperKey(sourceClass), (BaseMapMapper) raw);
            }
        });
    }

    @Override
    protected <S, T> BaseMapper findMapper(final Class<S> source, final Class<T> target) {
        return MAPPER_MAP.get(getMapperKey(source, target));
    }

    @Override
    protected <S> BaseMapMapper findMapMapper(final Class<?> source) {
        return MAP_MAPPER_MAP.get(getMapMapperKey(source));
    }
}
