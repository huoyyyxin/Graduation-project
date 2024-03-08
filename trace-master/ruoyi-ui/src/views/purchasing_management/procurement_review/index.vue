<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
        <el-form-item label="采购审核单编号" prop="procurementReviewFormNumber">
          <el-input
            v-model="queryParams.procurementReviewFormNumber"
            placeholder="请输入采购审核单编号"
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
  
      <el-table v-loading="loading" :data="procurement_reviewList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="采购审核单编号" align="center" prop="procurementReviewFormNumber" />
        <el-table-column width = "150" label="采购企业编号" align="center" prop="procurementCompanyNumber" />
        <el-table-column width = "150" label="采购人编号" align="center" prop="purchasingPersonNumber" />
        <el-table-column width = "150" label="采购合同编号" align="center" prop="procurementContractNumber" />
        <el-table-column width = "150" label="所需原材料" align="center" prop="requiredRawMaterials" />
        <el-table-column width = "150" label="已到货数量" align="center" prop="receivedQuantity" />
        <el-table-column width = "150" label="不合格数量" align="center" prop="unqualifiedquantity" />
        <el-table-column width = "150" label="所需数量" align="center" prop="requiredQuantity" />
        <el-table-column width = "150" label="备注" align="center" prop="remark" />
        <el-table-column fixed = "right" width = "150" label="操作" align="center" class-name="small-padding fixed-width">
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
          <el-form-item label="采购审核单编号" prop="procurementReviewFormNumber">
            <el-input v-model="form.procurementReviewFormNumber" placeholder="请输入采购审核单编号" />
          </el-form-item>
          <el-form-item label="采购企业编号" prop="procurementCompanyNumber">
            <el-input v-model="form.procurementCompanyNumber" placeholder="请输入采购企业编号" />
          </el-form-item>
          <el-form-item label="采购人编号" prop="purchasingPersonNumber">
            <el-input v-model="form.purchasingPersonNumber" placeholder="请输入采购人编号" />
          </el-form-item>
          <el-form-item label="采购合同编号" prop="procurementContractNumber">
            <el-input v-model="form.procurementContractNumber" placeholder="请输入采购合同编号" />
          </el-form-item>
          <el-form-item label="所需原材料" prop="requiredRawMaterials">
            <el-input v-model="form.requiredRawMaterials" placeholder="请输入所需原材料" />
          </el-form-item>
          <el-form-item label="已到货数量" prop="receivedQuantity">
            <el-input v-model="form.receivedQuantity" placeholder="请输入已到货数量" />
          </el-form-item>
          <el-form-item label="不合格数量" prop="unqualifiedquantity">
            <el-input v-model="form.unqualifiedquantity" placeholder="请输入不合格数量" />
          </el-form-item>
          <el-form-item label="所需数量" prop="requiredQuantity">
            <el-input v-model="form.requiredQuantity" placeholder="请输入不合格数量" />
          </el-form-item>
          <el-form-item label="备注" prop="remark ">
            <el-input v-model="form.remark " placeholder="请输入备注" />
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
    name: "procurement_review",
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
        // warehouse表格数据
        procurement_reviewList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          procurementReviewFormNumber: null,
          procurementCompanyNumber: null,
          purchasingPersonNumber:null,
          procurementContractNumber:null,
          requiredRawMaterials:null,
          receivedQuantity:null,
          unqualifiedquantity:null,
          requiredQuantity:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            procurementReviewFormNumber: [
            { required: true, message: "采购企业审核单编号不能为空", trigger: "blur" }
          ],
          procurementCompanyNumber: [
            { required: true, message: "采购企业编号不能为空", trigger: "blur" }
          ],
          purchasingPersonNumber: [
            { required: true, message: "采购人编号不能为空", trigger: "blur" }
          ],
          procurementContractNumber: [
            { required: true, message: "采购合同编号不能为空", trigger: "blur" }
          ],
          receivedQuantity: [
            { required: true, message: "已到货数量不能为空", trigger: "blur" }
          ],
          unqualifiedquantity: [
            { required: true, message: "不合格数量不能为空", trigger: "blur" }
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
        this.procurement_reviewList=[{ 
            procurementReviewFormNumber: "null",
          procurementCompanyNumber: "null",
          purchasingPersonNumber:"null",
          procurementContractNumber:"null",
          requiredRawMaterials:"null",
          receivedQuantity:"null",
          unqualifiedquantity:"null",
          requiredQuantity:"123",
          remark :"null"
          },{
            procurementReviewFormNumber: "123",
          procurementCompanyNumber: "456",
          purchasingPersonNumber:"159",
          procurementContractNumber:"753",
          requiredRawMaterials:"654",
          receivedQuantity:"014",
          unqualifiedquantity:"897",
          requiredQuantity:"456",
          remark :"364"
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
            procurementReviewFormNumber: null,
          procurementCompanyNumber: null,
          purchasingPersonNumber:null,
          procurementContractNumber:null,
          requiredRawMaterials:null,
          receivedQuantity:null,
          unqualifiedquantity:null,
          requiredQuantity:null,
          remark :null,
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
        this.title = "采购审核管理";
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
  