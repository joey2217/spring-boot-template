package com.joey.template.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Joey
 */
@Data
@Schema(name = "登录参数",description = "登录参数")
public class LoginParam {
    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 16, message = "账号为4~16位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码为6~16位")
    private String password;
}
