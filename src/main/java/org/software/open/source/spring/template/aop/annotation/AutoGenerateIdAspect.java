package org.software.open.source.spring.template.aop.annotation;

import java.lang.reflect.Field;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.github.f4b6a3.uuid.UuidCreator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class AutoGenerateIdAspect {

    // Áp dụng cho tất cả method save() trong package repository
    @Before("execution(* org.software.open.source.user.io.repositories..*.save*(..))")
    public void beforeSave(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0)
            return;

        for (Object arg : args) {
            if (arg == null)
                continue;
            processEntity(arg);
        }
    }

    private void processEntity(Object entity) throws Exception {
        Class<?> clazz = entity.getClass();

        // Tìm field "id"
        Field idField = findIdField(clazz);
        if (idField == null)
            return;

        idField.setAccessible(true);
        Object currentId = idField.get(entity);

        // Nếu id == null → gán UUIDv7
        if (currentId == null) {
            UUID newId = UuidCreator.getTimeOrderedEpoch();
            idField.set(entity, newId);
            log.info("[AutoGenerateId] ID: {} cho entity: {}", newId, entity.getClass().getSimpleName());
        }
    }

    private Field findIdField(Class<?> clazz) {
        while (clazz != null && clazz != Object.class) {
            try {
                return clazz.getDeclaredField("id");
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }
        return null;
    }
}
