package org.beatrace.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.beatrace.commonutils.R;
import org.beatrace.eduservice.entity.EduTeacher;
import org.beatrace.eduservice.entity.vo.TeacherQuery;
import org.beatrace.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author beatrace
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;
    // 查询数据

    @GetMapping("findAll")
    public R findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //逻辑删除讲师
    @DeleteMapping("/deleteTeacherById/{id}")
    public R deleteTeacherById(@PathVariable String id){

        boolean flag = teacherService.removeById(id);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询
    @GetMapping("/pageList/{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit
    ) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //分页查询，查完后，会将数据封装在pageParam中
        teacherService.page(pageParam,null);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    //多条件查询讲师带分页
    @ApiOperation(value = "多条件查询讲师带分页")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
    @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.pageQuery(pageParam, teacherQuery);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    //新增讲师
    @ApiOperation(value = "新增讲师")
    @PostMapping("/save")
    public R save(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.save(eduTeacher);
        return flag ? R.ok() : R.error();
    }

    public void test_git() {
        System.out.println("test git");
    }

}

