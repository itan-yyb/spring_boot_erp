package com.itan.bus.service;

import com.itan.bus.entity.Outport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author itan
 * @since 2020-01-21
 */
public interface OutportService extends IService<Outport> {

    void addOutport(Integer id, Integer number, String remark);
}
