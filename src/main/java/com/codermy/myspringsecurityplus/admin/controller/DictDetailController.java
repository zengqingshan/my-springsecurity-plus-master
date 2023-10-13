package com.codermy.myspringsecurityplus.admin.controller;

import com.codermy.myspringsecurityplus.admin.entity.SysDictDetail;
import com.codermy.myspringsecurityplus.admin.entity.SysJob;
import com.codermy.myspringsecurityplus.admin.service.DictDetailService;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.PageTableRequest;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.log.aop.MyLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/dictDetail")
@Api(tags = "系统：字典详情管理")
public class DictDetailController {
    @Autowired
    private DictDetailService detailService;

    @ApiOperation("查询字典详情")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('dict:list')")
    @ResponseBody
    public Result getDictDetailMaps(PageTableRequest pageTableRequest, String dictName){
        pageTableRequest.countOffset();
        return detailService.getDictByName(pageTableRequest.getOffset(),pageTableRequest.getLimit(),dictName);
    }

    @GetMapping("/add")
    @ApiOperation(value = "添加字典详情页面")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String addDictDetail(Model model, SysDictDetail sysDictDetail){
        model.addAttribute("MyDictDetail", sysDictDetail);
        return "/system/dict/dict-detail-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加字典详情")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    @MyLog("添加字典详情")
    public Result saveDictDetail(@RequestBody SysDictDetail sysDictDetail){
        return Result.judge(detailService.insertDictDetail(sysDictDetail),"添加字典详情");
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改字典详情页面")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String editDictDetail(Model model, SysDictDetail sysDictDetail) {
        model.addAttribute("MyDictDetail",detailService.getDictDetailById(sysDictDetail.getId()));
        return "system/dict/dict-detail-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改字典详情")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    @MyLog("修改字典详情")
    public Result updateDictDetail(@RequestBody SysDictDetail sysDictDetail){
        return Result.judge(detailService.updateDictDetail(sysDictDetail),"修改字典详情");
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除字典详情")
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<SysJob> deleteDict(String ids) {
        try {
            detailService.deleteDictDetailByIds(ids);
            return Result.ok().message("删除成功");
        }catch (MyException e){
            return Result.error().message(e.getMsg()).code(e.getCode());
        }
    }
}
