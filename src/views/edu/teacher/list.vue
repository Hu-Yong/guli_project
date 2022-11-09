<template>
  <div class="app-container">
    <!-- 表格 -->
    <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="数据加载中"
        border
        fit
        highlight-current-row>

        <el-table-column
            label="序号"
            width="70"
            align="center">
            <template slot-scope="scope">
                {{ (page - 1) * limit + scope.$index + 1 }}
            </template>
        </el-table-column>
        <el-table-column prop="name" label="名称" width="80" />
        <el-table-column label="头衔" width="80">
            <template slot-scope="scope">
                {{ scope.row.level===1?'高级讲师':'首席讲师' }}
            </template>
        </el-table-column>
        <el-table-column prop="intro" label="资历" />
        <el-table-column prop="gmtCreate" label="添加时间" width="160"/>
        <el-table-column prop="sort" label="排序" width="60" />
        <el-table-column label="操作" width="200" align="center">
            <template slot-scope="scope">
                <router-link :to="'/edu/teacher/edit/'+scope.row.id">
                    <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
                </router-link>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
        :current-page="page"
        :page-size="limit"
        :total="total"
        style="padding: 30px 0; text-align: center;"
        layout="total, prev, pager, next, jumper"
        @current-change="fetchData"
    />
  </div>
</template>
<script>
import teacher from '@/api/edu/teacher'
export default{
    data() {
        return {
            listLoading: true, // 是否显示loading信息
            list: null, // 数据列表
            total: 0, // 总记录数
            page: 1, // 页码
            limit: 5, // 每页记录数
            searchObj: {
                // name: '',
                // level: null,
                // begin:'',
                // end:''
            }// 查询条件
        }
    },

    created() {
        this.fetchData()
    },

    methods: {
        fetchData(page = 1) {
            console.log('加载列表')
            this.page = page
            this.listLoading = true
            teacher.getPageList(this.page, this.limit, this.searchObj).then(response => {
                //debugger //设置断点调试
                if (response.success === true) {
                this.list = response.data.rows
                this.total = response.data.total
            }
            this.listLoading = false
            })
        }
    }
}
</script>
