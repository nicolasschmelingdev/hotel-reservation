package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.GenericEntity;
import com.ntconsult.hotelreservation.domain.repository.GenericRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import com.ntconsult.hotelreservation.infrastructure.dto.PageResult;
import com.ntconsult.hotelreservation.infrastructure.dto.Sorter;
import com.ntconsult.hotelreservation.infrastructure.dto.ValidationConstants;
import com.ntconsult.hotelreservation.util.ConvertSort;
import com.ntconsult.hotelreservation.util.ObjectMapperUtils;
import com.ntconsult.hotelreservation.util.Util;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericService<T extends GenericEntity<T>, I, D extends GenericDto, V, R extends GenericRepository<T, I>> {

    private static final String TABLE = "{table}";

    private final R genericRepository;

    private final Class<T> type;

    private final Class<V> typeVo;

    protected GenericService(R genericRepository) {
        this.genericRepository = genericRepository;
        this.type = resolveGenericType(0);
        this.typeVo = resolveGenericType(3);
    }

    @SuppressWarnings("unchecked")
    private <E> Class<E> resolveGenericType(int index) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType parameterizedType) {
            Type type = parameterizedType.getActualTypeArguments()[index];
            if (type instanceof Class<?>) {
                return (Class<E>) type;
            } else {
                throw new IllegalStateException("Generic type argument at index " + index + " is not a class.");
            }
        } else {
            throw new IllegalStateException("Superclass is not parameterized");
        }
    }

    protected void transformInputDto(T registro, D dto) {
    }

    protected void transformOutputDto(T registro, V regOut) {
    }

    protected void beforeInsertByEntity(T registro) {
    }

    protected void afterInsertByEntity(T registro) {
    }

    protected void beforeInsert(T registro, D dto) {
    }

    protected void afterInsert(T registro) {
    }

    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public V create(D regDto) {
        T reg = entityFromInDto(regDto);
        transformInputDto(reg, regDto);
        beforeInsert(reg, regDto);
        save(reg);
        afterInsert(reg);
        return this.entityToOutDto(reg);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void createByEntity(T reg) {
        beforeInsertByEntity(reg);
        save(reg);
        afterInsertByEntity(reg);
    }

    protected void beforeUpdate(T registro, D dto) {
        dto.setId(registro.getId());
    }

    protected void afterUpdate(T registro) {
    }

    protected void beforeUpdateByEntity(T registro, I id) {
    }

    protected void afterUpdateByEntity(T registro) {
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @CachePut(value = "entidades", key = "#root.targetClass.simpleName+'-'+#id", condition = "#root.targetClass.isAnnotationPresent(T(com.finanbank.sap.util.CacheableService))")
    public V update(D regDto, I id) {
        T reg = findByIdEntity(id);
        transformInputDto(reg, regDto);
        beforeUpdate(reg, regDto);
        ObjectMapperUtils.map(regDto, reg);
        save(reg);
        afterUpdate(reg);
        return this.entityToOutDto(reg);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @CachePut(value = "entidades", key = "#root.targetClass.simpleName+'-'+#id", condition = "#root.targetClass.isAnnotationPresent(T(com.finanbank.sap.util.CacheableService))")
    public void updateByEntity(T reg, I id) {
        findByIdEntity(id);
        beforeUpdateByEntity(reg, id);
        save(reg);
        afterUpdateByEntity(reg);
    }

    protected void beforeDelete(T registro) {
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @CacheEvict(value = "entidades", key = "#root.targetClass.simpleName+'-'+#id", condition = "#root.targetClass.isAnnotationPresent(T(com.finanbank.sap.util.CacheableService))")
    public void deleteById(I id) {
        T reg = findByIdEntity(id);
        beforeDelete(reg);
        genericRepository.deleteById(id);
    }

    protected void beforeFind(T reg, V regOut) {
    }

    @Cacheable(value = "entidades", key = "#root.targetClass.simpleName+'-'+#id", condition = "#root.targetClass.isAnnotationPresent(T(com.finanbank.sap.util.CacheableService))")
    public V findByIdDto(I id) {
        T reg = findByIdEntity(id);
        V regOut = this.entityToOutDto(reg);
        transformOutputDto(reg, regOut);
        beforeFind(reg, regOut);
        return regOut;
    }

    public T findByIdEntity(I id) {
        var entityName = Util.retornaMensagem("entity.name." + type.getSimpleName());
        return genericRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                Util.retornaMensagem(ValidationConstants.REGISTER_NOT_FOUND).replace(TABLE, entityName)));
    }

    public T findByIdEntityNullable(I id) {
        return genericRepository.findById(id).orElse(null);
    }

    protected void onLoadToList(T registro, V retorno) {
    }

    public PageResult<V> getIndex(Specification<T> spec, int page, int limit, String sortBy, Sorter.Direction sortDirection) {
        Pageable paging = PageRequest.of(page - 1, limit, ConvertSort.toSort(sortBy, sortDirection));
        Page<T> pagina = genericRepository.findAll(spec, paging);
        List<V> recordValues = pagina.stream().map(this::entityToOutDto).toList();
        return PageResult.page(recordValues, pagina.getTotalElements(), pagina.getPageable().getPageNumber(), pagina.getPageable().getPageSize(), pagina.getTotalPages());
    }

    protected void beforeLoadList(T registro, V reg) {
    }

    public T entityFromInDto(D inDto) {

        return ObjectMapperUtils.map(inDto, type);
    }

    public V entityToOutDto(T registro) {
        V reg = ObjectMapperUtils.map(registro, typeVo);
        beforeLoadList(registro, reg);
        transformOutputDto(registro, reg);
        onLoadToList(registro, reg);
        return reg;
    }

    public R getRepository() {
        return genericRepository;
    }
}
