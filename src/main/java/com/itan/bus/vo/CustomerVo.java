package com.itan.bus.vo;

import com.itan.bus.entity.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerVo extends Customer {
    private static final long serialVersionUID=1L;
    private Integer page;
    private Integer limit;
    private Integer[] ids;
}
