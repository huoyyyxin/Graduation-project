<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" >
        <el-form-item width = "140" label="采购计划单编号" prop="inspectionFormNumber">
          <el-input
            v-model="queryParams.inspectionFormNumber"
            placeholder="请输入采购计划单编号"
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
  
      <el-table v-loading="loading" :data="procurement_planningList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="采购计划单编号" align="center" prop="purchasingPlanNumber" />
        <el-table-column width = "150" label="采购企业编号" align="center" prop="procurementCompanyNumber" />
        <el-table-column width = "150" label="采购人编号" align="center" prop="purchasingPersonNumber" />
        <el-table-column width = "150" label="制单人编号" align="center" prop="creatorID" />
        <el-table-column width = "150" label="采购合同编号" align="center" prop="procurementContractNumber" />
        <el-table-column width = "150" label="签订日期" align="center" prop="signingDate" />
        <el-table-column width = "150" label="制单日期" align="center" prop="productionDate" />
        <el-table-column width = "150" label="应到货日期" align="center" prop="expectedDeliveryDate" />
        <el-table-column width = "150" label="状态标志" align="center" prop="statusFlag" />
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
        <el-form ref="form" :model="form" :rules="rules" label-width="100px">
          <el-form-item label="采购计划单编号" prop="purchasingPlanNumber">
            <el-input v-model="form.purchasingPlanNumber" placeholder="请输入采购计划单编号" />
          </el-form-item>
          <el-form-item label="采购企业编号" prop="procurementCompanyNumber">
            <el-input v-model="form.procurementCompanyNumber" placeholder="请输入采购企业编号" />
          </el-form-item>
          <el-form-item label="采购人编号" prop="purchasingPersonNumber">
            <el-input v-model="form.purchasingPersonNumber" placeholder="请输入采购人编号" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人编号" />
          </el-form-item>
          <el-form-item label="采购合同编号" prop="procurementContractNumber">
            <el-input v-model="form.procurementContractNumber" placeholder="请输入采购合同编号" />
          </el-form-item>
          <el-form-item label="签订日期" prop="signingDate">
            <el-input v-model="form.signingDate" placeholder="请输入签订日期" />
          </el-form-item>
          <el-form-item label="制单日期" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单日期" />
          </el-form-item>
          <el-form-item label="应到货日期" prop="expectedDeliveryDate">
            <el-input v-model="form.expectedDeliveryDate" placeholder="请输入应到货日期" />
          </el-form-item>
          <el-form-item label="状态标志" prop="statusFlag">
            <el-input v-model="form.statusFlag" placeholder="请输入状态标志" />
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
    name: "procurement_planning",
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
        procurement_planningList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          purchasingPlanNumber: null,
          procurementCompanyNumber: null,
          purchasingPersonNumber:null,
          creatorID: null,
          procurementContractNumber:null,
          signingDate:null,
          productionDate:null,
          expectedDeliveryDate:null,
          statusFlag:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            purchasingPlanNumber: [
            { required: true, message: "采购企业计划单编号不能为空", trigger: "blur" }
          ],
          procurementCompanyNumber: [
            { required: true, message: "采购企业编号不能为空", trigger: "blur" }
          ],
          purchasingPersonNumber: [
            { required: true, message: "采购人编号不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          procurementContractNumber: [
            { required: true, message: "采购合同编号不能为空", trigger: "blur" }
          ],
          signingDate: [
            { required: true, message: "签订日期不能为空", trigger: "blur" }
          ],
          productionDate: [
            { required: true, message: "制单日期不能为空", trigger: "blur" }
          ],
          expectedDeliveryDate: [
            { required: true, message: "应到货日期不能为空", trigger: "blur" }
          ],
          statusFlag: [
            { required: true, message: "状态标志不能为空", trigger: "blur" }
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
        this.procurement_planningList=[{ 
            purchasingPlanNumber: "1",
          procurementCompanyNumber: "2",
          purchasingPersonNumber:"3",
          creatorID: "4",
          procurementContractNumber:"5",
          signingDate:"6",
          productionDate:"7",
          expectedDeliveryDate:"8",
          statusFlag:"9",
          remark :"0"
          },{
            purchasingPlanNumber: "11",
          procurementCompanyNumber: "22",
          purchasingPersonNumber:"33",
          creatorID: "44",
          procurementContractNumber:"55",
          signingDate:"66",
          productionDate:"77",
          expectedDeliveryDate:"88",
          statusFlag:"99",
          remark :"00"
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
            purchasingPlanNumber: null,
          procurementCompanyNumber: null,
          purchasingPersonNumber:null,
          creatorID: null,
          procurementContractNumber:null,
          signingDate:null,
          productionDate:null,
          expectedDeliveryDate:null,
          statusFlag:null,
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
        this.title = "采购计划管理";
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
  