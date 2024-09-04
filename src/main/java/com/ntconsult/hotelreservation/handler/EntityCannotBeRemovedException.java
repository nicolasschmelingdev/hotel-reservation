package com.ntconsult.hotelreservation.handler;

/**
 * Exceção para entidade que não pode ser excluída.
 *
 * @author Rodolpho Couto
 * @since 2.1
 */
public class EntityCannotBeRemovedException extends RuntimeException {

    private final transient Object entity;

    public <T> EntityCannotBeRemovedException(Object entity) {
        this.entity = entity;
    }

    public Object getEntity() {
        return entity;
    }
}
