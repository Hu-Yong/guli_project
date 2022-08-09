package org.beatrace.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.beatrace.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.beatrace.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author beatrace
 * @since 2022-08-08
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
