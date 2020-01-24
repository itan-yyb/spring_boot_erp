package com.itan.sys.vo;

import com.itan.sys.entity.LogInfo;
import com.itan.sys.entity.Notice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class NoticeVo extends Notice {
    private static final long serialVersionUID=1L;
    private Integer page;
    private Integer limit;
    private Integer[] ids;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
