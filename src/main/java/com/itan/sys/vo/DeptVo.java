package com.itan.sys.vo;

import com.itan.sys.entity.Dept;
import com.itan.sys.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeptVo extends Dept {
    private static final long serialVersionUID=1L;
    private Integer page;
    private Integer limit;
    private Integer[] ids;
}
