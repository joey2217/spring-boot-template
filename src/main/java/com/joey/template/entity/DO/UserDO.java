package com.joey.template.entity.DO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joey.template.entity.base.BaseEntity;
import com.joey.template.entity.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

/**
 * @author Joey
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class UserDO extends BaseEntity implements Serializable {
    /**
     * 用户名 手机号或邮箱
     */
    @Column(unique = true)
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * EnumType:  ORDINAL 枚举序数  默认选项（int）。eg:TEACHER 数据库存储的是 0
     *            STRING：枚举名称       (String)。eg:TEACHER 数据库存储的是 "TEACHER"
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private RoleEnum role;

    /**
     * TODO
     * 头像URl
     */
    private String avatar;
}
