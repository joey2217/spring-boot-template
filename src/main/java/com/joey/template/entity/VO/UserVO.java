package com.joey.template.entity.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joey
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "响应用户",description = "返回数据用户")
public class UserVO {
    private Long id;
    private String username;
    private String nickname;
    private String role;
    private String avatar;
}
