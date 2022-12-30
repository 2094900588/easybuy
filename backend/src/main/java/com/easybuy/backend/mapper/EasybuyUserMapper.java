package com.easybuy.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easybuy.backend.pojo.EasybuyUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EasybuyUserMapper extends BaseMapper<EasybuyUser> {
}
