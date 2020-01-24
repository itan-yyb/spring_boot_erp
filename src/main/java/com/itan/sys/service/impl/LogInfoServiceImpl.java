package com.itan.sys.service.impl;

import com.itan.sys.entity.LogInfo;
import com.itan.sys.mapper.LogInfoMapper;
import com.itan.sys.service.LogInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itan
 * @since 2020-01-07
 */
@Service
@Transactional
public class LogInfoServiceImpl extends ServiceImpl<LogInfoMapper, LogInfo> implements LogInfoService {

}
