<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" :min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
      </el-select>
    </el-form-item>
    <el-form-item label="讲师资历">
      <el-input v-model="teacher.career"/>
    </el-form-item>
    <el-form-item label="讲师简介">
      <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
    </el-form-item>
    <el-form-item>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script>
import teacherApi from '@/api/edu/teacher'
export default {
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      saveBtnDisabled: false // 保存按钮是否禁用,
    }
  },
  created() {
    debugger
    console.log('created')
    if (this.$route.params && this.$route.params.id) {
      const id = this.$route.params.id
      this.fetchDataById(id)
    }
  },
  methods: {
    saveOrUpdate() {
      this.saveBtnDisabled = true
      debugger
      console.log(this.teacher)
      if (!this.teacher.id) {
        this.saveData()
      } else {
        this.updateData()
      }
    },
    // 保存
    saveData() {
      //debugger
      console.log(this.teacher)
      this.saveBtnDisabled = true
      teacherApi.addTeacher(this.teacher).then(response => {
          this.$message({
              type: 'success',
              message: '添加成功'
          })
          this.$router.push({
            path: '/edu/teacher/list'
          })
        }).catch((response) => {
          this.$message({
            type: 'error',
            message: '保存失败'
          })
        })
    },
    // 根据id查询记录
    fetchDataById(id) {
      teacherApi.getById(id).then(response => {
        //debugger
        this.teacher = response.data.item
      }).catch((response) => {
        this.$message({
          type: 'error',
          message: '获取数据失败'
        })
      })
    },
   // 根据id更新记录
    updateData() {
      debugger
      this.saveBtnDisabled = true
      teacherApi.updateById(this.teacher).then(response => {
        return this.$message({
          type: 'success',
          message: '修改成功!'
        })
      }).then(resposne => {
        this.$router.push({ path: '/edu/teacher' })
      }).catch((response) => {
        // console.log(response)
        this.$message({
          type: 'error',
          message: '保存失败'
        })
      })
    }
  },
}
</script>
