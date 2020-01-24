package com.itan.sys.vo;

import com.itan.sys.entity.Permission;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PermissionVo extends Permission {
    private static final long serialVersionUID=1L;
    private Integer page;
    private Integer limit;
}
