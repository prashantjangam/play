package com.teamfive.play.converter;

import com.teamfive.play.domain.Base;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.util.Date;

@Component
public class AuditConverter<T extends Base> implements Serializable {

    /**
     * Prepare for create.
     */
    public void prepareForCreate(T entity) {
        entity.setCreatedAt(new Date());
    }

    /**
     * Prepare for update.
     */
    public void prepareForUpdate(T entity) {
       entity.setUpdatedAt(new Date());
    }
}
