package com.codermy.myspringsecurityplus.admin.controller;


import com.codermy.myspringsecurityplus.admin.entity.SysDict;
import com.codermy.myspringsecurityplus.admin.entity.SysJob;
import com.codermy.myspringsecurityplus.admin.service.DictService;
import com.codermy.myspringsecurityplus.common.exceptionhandler.MyException;
import com.codermy.myspringsecurityplus.common.utils.PageTableRequest;
import com.codermy.myspringsecurityplus.common.utils.Result;
import com.codermy.myspringsecurityplus.common.utils.UserConstants;
import com.codermy.myspringsecurityplus.log.aop.MyLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/dict")
@Api(tags = "系统：字典管理")
public class DictController {
    @Autowired
    private DictService dictService;


    @GetMapping("/index")
    @PreAuthorize("hasAnyAuthority('dict:list')")
    public String index(){
        return "system/dict/dict";
    }


    @GetMapping
    @ResponseBody
    @ApiOperation(value = "字典列表")
    @PreAuthorize("hasAnyAuthority('dict:list')")
    @MyLog("查询字典列表")
    public Result getDictAll(PageTableRequest pageTableRequest, SysDict sysDict){
        pageTableRequest.countOffset();
        return dictService.getDictPage(pageTableRequest.getOffset(),pageTableRequest.getLimit(), sysDict);
    }


    @GetMapping("/add")
    @ApiOperation(value = "添加字典页面")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public String addDict(Model model){
        model.addAttribute("MyDict",new SysDict());
        return "/system/dict/dict-add";
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加字典")
    @PreAuthorize("hasAnyAuthority('dict:add')")
    @MyLog("添加字典")
    public Result saveDict(@RequestBody SysDict sysDict){
        if (UserConstants.NOT_UNIQUE.equals(dictService.checkDictNameUnique(sysDict))) {
            return Result.error().message("新增字典'" + sysDict.getDictName() + "'失败，字典名称已存在");
        }
        return Result.judge(dictService.insertDict(sysDict),"添加字典");
    }

    @GetMapping(value = "/edit")
    @ApiOperation(value = "修改字典页面")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    public String editDict(Model model, SysDict sysDict) {
        model.addAttribute("MyDict",dictService.getDictById(sysDict.getDictId()));
        return "system/dict/dict-edit";
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "修改字典")
    @PreAuthorize("hasAnyAuthority('dict:edit')")
    @MyLog("修改字典")
    public Result updateDict(@RequestBody SysDict sysDict){
        if (UserConstants.NOT_UNIQUE.equals(dictService.checkDictNameUnique(sysDict))) {
            return Result.error().message("修改字典'" + sysDict.getDictName() + "'失败，字典名称已存在");
        }
        return Result.judge(dictService.updateDict(sysDict),"修改字典");
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除字典")
    @PreAuthorize("hasAnyAuthority('dict:del')")
    public Result<SysJob> deleteDict(String ids) {
        try {
            dictService.deleteDictByIds(ids);
            return Result.ok().message("删除成功");
        }catch (MyException e){
            return Result.error().message(e.getMsg()).code(e.getCode());
        }
    }
}
