package com.codermy.myspringsecurityplus.security.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.codermy.myspringsecurityplus.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author CodePoorFish
 * @createTime 2023/10/11
 */
@Slf4j
@Component
public class MybatisColumnsHandler implements MetaObjectHandler {
    //设置数据新增时候的，字段自动赋值规则
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
//        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
//        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
//        this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getCurrentUsername());
//        this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getCurrentUsername());
        setFieldValByName("createTime", new Date(), metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);
        setFieldValByName("createBy", SecurityUtils.getCurrentUsername(), metaObject);
        setFieldValByName("updateBy", SecurityUtils.getCurrentUsername(), metaObject);
    }

    //设置数据修改update时候的，字段自动赋值规则
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
//        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
//        this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getCurrentUsername());
        setFieldValByName("updateTime", new Date(), metaObject);
        setFieldValByName("updateBy", SecurityUtils.getCurrentUsername(), metaObject);
    }
}
