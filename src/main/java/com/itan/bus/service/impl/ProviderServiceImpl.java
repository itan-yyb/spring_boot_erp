package com.itan.bus.service.impl;

import com.itan.bus.entity.Provider;
import com.itan.bus.mapper.ProviderMapper;
import com.itan.bus.service.ProviderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author itan
 * @since 2020-01-17
 */
@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService {
    @Override
    public boolean save(Provider entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @Override
    public boolean updateById(Provider entity) {
        return super.updateById(entity);
    }

    @Override
    public Provider getById(Serializable id) {
        return super.getById(id);
    }
}
