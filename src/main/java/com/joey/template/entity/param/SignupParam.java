package com.joey.template.entity.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Joey
 */
@Data
@Schema(name = "注册参数",description = "注册参数")
public class SignupParam {
    @NotBlank(message = "账号不能为空")
    @Size(min = 4, max = 16, message = "账号为4~16位")
    private String username;

    private String nickname;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码为6~16位")
    private String password;
}
