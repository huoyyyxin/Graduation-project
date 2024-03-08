<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="编号" prop="jobName">
          <el-input
            v-model="queryParams.jobName"
            placeholder="请输入编号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="密码" prop="jobRemarks">
          <el-input
            v-model="queryParams.jobRemarks"
            placeholder="请输入密码"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['trace:job:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['trace:reset:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['trace:reset:remove']"
          >删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="jobList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="员工编号" align="center" prop="jobId" />
        <el-table-column label="员工姓名" align="center" prop="jobName" />
        <el-table-column label="所拥有权限" align="center" prop="authority" />
        <el-table-column label="备注" align="center" prop="PersonnelPermissionRemarks" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['trace:job:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['trace:job:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改job对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="员工编号" prop="jobId">
            <el-input v-model="form.jobId" placeholder="请输入编号" />
          </el-form-item>
          <el-form-item label="员工姓名" prop="jobName">
            <el-input v-model="form.jobName" placeholder="请输入员工姓名" />
          </el-form-item>
          <el-form-item label="所拥有权限" prop="authority">
            <el-input v-model="form.authority" placeholder="请输入所拥有权限" />
          </el-form-item>
          <el-form-item label="备注" prop="PersonnelPermissionRemarks">
            <el-input v-model="form.PersonnelPermissionRemarks" placeholder="请输入备注" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listJob, getJob, delJob, addJob, updateJob } from "@/api/trace/job";
  
  export default {
    name: "Job",
    data() {
      return {
        // 遮罩层
        loading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // job表格数据
        jobList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          jobName: null,
          authority: null,
          PersonnelPermissionRemarks:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          jobName: [
            { required: true, message: "名称不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询job列表 */
      getList() {
        this.loading = true;
        // listJob(this.queryParams).then(response => {
        //   this.jobList = response.rows;
        //   this.total = response.total;
        // });
          this.loading = false;
        this.jobList=[{
          jobName:'哈哈哈',
          authority:'1',
          jobId:'1',
          PersonnelPermissionRemarks:"79/8"
        },
        {
          jobName:'偶哈呦',
          authority:'1',
          jobId:'2',
          PersonnelPermissionRemarks:"456"
        }
        ]

      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          jobId: null,
          jobName: null,
          authority: null,
          PersonnelPermissionRemarks:null,
          createTime: null,
          updateTime: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.jobId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "员工编号管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const jobId = row.authority || this.ids
          this.open = true;
      },
      /** 提交按钮 */
      submitForm() {
                this.open = false;
      },
      /** 删除按钮操作 */
      handleDelete(row) {
      },
      /** 导出按钮操作 */
      handleExport() {
      }
    }
  };
  </script>
  