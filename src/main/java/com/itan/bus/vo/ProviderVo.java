package com.itan.bus.vo;

import com.itan.bus.entity.Customer;
import com.itan.bus.entity.Provider;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderVo extends Provider {
    private static final long serialVersionUID=1L;
    private Integer page;
    private Integer limit;
    private Integer[] ids;
}
