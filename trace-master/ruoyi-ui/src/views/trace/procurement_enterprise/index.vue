<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="采购企业编号" prop="procurementCompanyNumber">
          <el-input
            v-model="queryParams.procurementCompanyNumber"
            placeholder="请输入采购企业编号"
            clearable   
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
    
        <el-form-item label="采购人编号" prop="purchasingPersonNumber">
          <el-input
            v-model="queryParams.purchasingPersonNumber"
            placeholder="请输入采购人编号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="采购合同编号" prop="procurementContractNumber">
          <el-input
            v-model="queryParams.procurementContractNumber"
            placeholder="请输入采购合同编号"
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
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="warehouseList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="采购企业编号" align="center" prop="procurementCompanyNumber" />
        <el-table-column label="采购人编号" align="center" prop="purchasingPersonNumber" />
        <el-table-column label="采购合同编号" align="center" prop="procurementContractNumber" />
        <el-table-column label="应到货日期" align="center" prop="expectedDeliveryDate" />
        <el-table-column label="状态标志" align="center" prop="statusFlag" />
        <el-table-column label="备注" align="center" prop="procurementRemark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        />
  
      <!-- 添加或修改warehouse对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="采购企业编号" prop="procurementCompanyNumber">
            <el-input v-model="form.mprocurementCompanyNumber" placeholder="请输入采购企业编号" />
          </el-form-item>
          <el-form-item label="采购人编号" prop="purchasingPersonNumber">
            <el-input v-model="form.purchasingPersonNumber" placeholder="请输入采购人编号" />
          </el-form-item>
          <el-form-item label="采购合同编号" prop="procurementContractNumber">
            <el-input v-model="form.procurementContractNumber" placeholder="请输入采购合同编号" />
          </el-form-item>
          <el-form-item label="应到货日期" prop="expectedDeliveryDate">
            <el-input v-model="form.expectedDeliveryDate" placeholder="请输入应到货日期" />
          </el-form-item>
          <el-form-item label="状态标志" prop="statusFlag">
            <el-input v-model="form.statusFlag" placeholder="请输入状态标志" />
          </el-form-item>
          <el-form-item label="备注" prop="procurementRemark">
            <el-input v-model="form.procurementRemark" placeholder="请输入备注" />
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
  
  
  export default {
    name: "procurement_enterprise",
    data() {
      return {
        // 遮罩层
        loading: true,
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
        // procurement_enterprise表格数据
        procurement_enterpriseList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          procurementCompanyNumber :null,
          purchasingPersonNumber:null,
          procurementContractNumber:null,
          expectedDeliveryDate:null,
          statusFlag:null,
          procurementRemark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            procurementCompanyNumber: [
            { required: true, message: "采购企业不能为空", trigger: "blur" }
          ],
          purchasingPersonNumber: [
            { required: true, message: "采购人不能为空", trigger: "blur" }
          ],
          procurementContractNumber: [
            { required: true, message: "采购合同不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
        getList() {
        this.loading = true;
        this.loading = false;
        this.warehouseList=[{ 
            procurementCompanyNumber: "001",
            purchasingPersonNumber:"001",
            procurementContractNumber:"001",
            expectedDeliveryDate:"2024/5/11",
            statusFlag:"111",
          procurementRemark:null
          },{
            procurementCompanyNumber :"002",
            purchasingPersonNumber:"002",
            procurementContractNumber:"002",
            expectedDeliveryDate:"2024/6/14",
            statusFlag:"222",
            procurementRemark:null
          }]
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          procurementCompanyNumber :null,
          purchasingPersonNumber:null,
          procurementContractNumber:null,
          expectedDeliveryDate:null,
          statusFlag:null,
          procurementRemark:null,
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
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "采购企业管理";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
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
  