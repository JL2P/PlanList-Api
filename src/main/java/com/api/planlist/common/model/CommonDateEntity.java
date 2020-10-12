package com.api.planlist.common.model;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonDateEntity { // ��¥ �ʵ� ��� ó��
    @CreatedDate // Entity ������ �ڵ����� ��¥����
    private LocalDateTime created;
    @LastModifiedDate // Entity ������ �ڵ����� ��¥����
    private LocalDateTime modified;
}