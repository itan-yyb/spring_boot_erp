package com.itan.bus.vo;

import com.itan.bus.entity.Inport;
import com.itan.bus.entity.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class InportVo extends Inport {
    private static final long serialVersionUID=1L;
    private Integer page;
    private Integer limit;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
