package com.joey.template.controller;

import com.joey.template.entity.VO.RoleVO;
import com.joey.template.entity.enums.RoleEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author joey
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private List<RoleVO> roleVOList = new ArrayList<>();

    @GetMapping("/list")
    public ResponseEntity<List<RoleVO>> list() {
        if (roleVOList.isEmpty()) {
            roleVOList = Arrays.stream(RoleEnum.values()).map(roleEnum -> new RoleVO(roleEnum.getName(), roleEnum.getDesc())).toList();
        }
        return ResponseEntity.ok(roleVOList);
    }
}