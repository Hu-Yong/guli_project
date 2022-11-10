import request from '@/utils/request'
const api_name = '/eduservice/edu-teacher'
export default {
  getPageList(page, limit, teacherQuery) {
    return request({
      url: `${api_name}/pageTeacherCondition/${page}/${limit}`,
      method: 'post',
      // headers: {
      //     'Content-Type': 'application/json'
      //   },
      data: teacherQuery
    })
  },

  addTeacher(teacher) {
    return request({
      url: `/eduservice/edu-teacher/save`,
      method: 'post',
      // headers: {
      //     'Content-Type': 'application/json'
      //   },
    data: teacher
    })
  },
  removeById(teacherId) {
    return request({
      url: `${api_name}/deleteTeacherById/${teacherId}`,
      method: 'delete'
    })
  },
  getById(id) {
    return request({
      url: `${api_name}/getById/${id}`,
      method: 'get'
    })
  },
  updateById(teacher) {
    return request({
      url: `${api_name}/updateById`,
      method: 'post',
      data: teacher
    })
  }
}