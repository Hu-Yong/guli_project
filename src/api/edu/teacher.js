import request from '@/utils/request'
const api_name = '/eduservice/edu-teacher/pageTeacherCondition'
export default {
  getPageList(page, limit, teacherQuery) {
    return request({
    url: `${api_name}/${page}/${limit}`,
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
  }
}