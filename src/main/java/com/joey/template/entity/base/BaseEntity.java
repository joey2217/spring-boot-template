package com.joey.template.entity.base;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @author Joey
 */
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @CreationTimestamp
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createTime;

    /**
     * 修改时间
     */
    @UpdateTimestamp
    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}