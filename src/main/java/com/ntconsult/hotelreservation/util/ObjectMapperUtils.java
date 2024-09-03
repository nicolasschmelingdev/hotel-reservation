package com.ntconsult.hotelreservation.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class ObjectMapperUtils {

    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ObjectMapperUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Maps an entity to a specified class.
     *
     * @param <D>      Type of the destination object
     * @param <T>      Type of the source object
     * @param entity   The source object to be mapped
     * @param outClass The class of the destination object
     * @return The mapped object of the destination type
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        if (entity == null) {
            return null;
        }
        return modelMapper.map(entity, outClass);
    }

    /**
     * Maps a collection of entities to a list of a specified class.
     *
     * @param <D>        Type of the destination objects
     * @param <T>        Type of the source objects
     * @param entityList The collection of source objects to be mapped
     * @param outClass   The class of the destination objects
     * @return A list of mapped objects of the destination type
     */
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass) {
        if (entityList == null || entityList.isEmpty()) {
            return List.of();
        }
        return entityList.stream()
                .map(entity -> map(entity, outClass))
                .collect(Collectors.toList());
    }

    /**
     * Maps properties from a source object to an existing destination object.
     *
     * @param <S>         Type of the source object
     * @param <D>         Type of the destination object
     * @param source      The source object containing the properties to map
     * @param destination The destination object where properties will be mapped to
     * @return The destination object with the mapped properties
     */
    public static <S, D> D map(final S source, D destination) {
        if (source == null || destination == null) {
            return destination;
        }
        modelMapper.map(source, destination);
        return destination;
    }
}
