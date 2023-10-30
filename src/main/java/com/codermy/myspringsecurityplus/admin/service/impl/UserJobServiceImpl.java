package com.codermy.myspringsecurityplus.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codermy.myspringsecurityplus.admin.dao.UserJobDao;
import com.codermy.myspringsecurityplus.admin.entity.SysUserJob;
import com.codermy.myspringsecurityplus.admin.service.UserJobService;
import com.codermy.myspringsecurityplus.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author codermy
 * @createTime 2020/7/10
 */
@Service
public class UserJobServiceImpl extends ServiceImpl<UserJobDao, SysUserJob> implements UserJobService {
}
