package com.atguigu.system.controller;

import com.atguigu.common.result.Result;
import com.atguigu.model.system.SysRole;
import com.atguigu.model.vo.SysRoleQueryVo;
import com.atguigu.system.service.SysRoleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    //新增角色
    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public Result save(@RequestBody SysRole role) {
        sysRoleService.save(role);
        return Result.ok();
    }


    //条件分页查询
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result findPageQueryRole(@PathVariable Long page,
                                    @PathVariable Long limit,
                                    SysRoleQueryVo sysRoleQueryVo)
    {
        //创建page对象
        Page<SysRole> pageParam =new Page<>(page,limit);
        //调用service方法
        IPage<SysRole> pageModel= sysRoleService.selectPage(pageParam,sysRoleQueryVo);

        //返回
        return Result.ok(pageModel);

    }



    //查询所有记录
    //http://localhost:8800/admin/system/sysRole/findAll
    @ApiOperation("角色查询接口")
    @GetMapping("/findAll")
    public Result findAllRole(){

        int i=9/0;

        //调用service
        List<SysRole> roleList=sysRoleService.list();
        return Result.ok(roleList);
    }


    @ApiOperation("逻辑删除接口")
    @DeleteMapping("remove/{id}")
    public Result removeRole(@PathVariable Long id) {
        boolean b = sysRoleService.removeById(id);
        if (b) {
            return Result.ok();
        } else {
            return Result.fail();

        }
    }

    //修改-根据id查询
    @ApiOperation("根据id查询")
    @PostMapping("findRoleById/{id}")
    public Result findRoleById(@PathVariable Long id){
        SysRole sysRole=sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("最终修改")
    @PostMapping("update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean isSuccess =sysRoleService.updateById(sysRole);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        sysRoleService.removeByIds(idList);
        return Result.ok();
    }





}


