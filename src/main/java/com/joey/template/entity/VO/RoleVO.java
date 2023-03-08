package com.joey.template.entity.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joey
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "响应角色",description = "返回数据角色")
public class RoleVO {
    private String name;
    private String desc;
}
