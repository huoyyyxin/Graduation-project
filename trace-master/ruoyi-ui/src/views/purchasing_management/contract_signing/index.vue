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

      <el-table v-loading="loading" :data="contract_signingList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="采购企业编号" align="center" prop="procurementCompanyNumber" />
        <el-table-column label="采购人编号" align="center" prop="purchasingPersonNumber" />
        <el-table-column label="采购合同编号" align="center" prop="procurementContractNumber" />
        <el-table-column label="签约状态" align="center" prop="ifContracted" />
        <el-table-column label="物料编号" align="center" prop="materialCode" />
        <el-table-column label="单价" align="center" prop="unitPrice" />
        <el-table-column label="备注" align="center" prop="remark" />
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

      <!-- 添加或修改contract_signing对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="采购企业编号" prop="procurementCompanyNumber">
            <el-input v-model="form.procurementCompanyNumber" placeholder="请输入采购企业编号" />
          </el-form-item>
          <el-form-item label="采购人编号" prop="purchasingPersonNumber">
            <el-input v-model="form.purchasingPersonNumber" placeholder="请输入采购人编号" />
          </el-form-item>
          <el-form-item label="采购合同编号" prop="procurementContractNumber">
            <el-input v-model="form.procurementContractNumber" placeholder="请输入采购合同编号" />
          </el-form-item>
          <el-form-item label="签约状态" prop="ifContracted">
            <el-input v-model="form.ifContracted" placeholder="请输入签约状态" />
          </el-form-item>
          <el-form-item label="物料编号" prop="materialCode">
            <el-input v-model="form.materialCode" placeholder="请输入物料编号" />
          </el-form-item>
          <el-form-item label="单价" prop="unitPrice">
            <el-input v-model="form.unitPrice" placeholder="请输入单价" />
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
    name: "contract_signing",
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
        contract_signingList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          procurementCompanyNumber: null,
          purchasingPersonNumber: null,
          procurementContractNumber:null,
          ifContracted :null,
          materialCode:null,
          unitPrice:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            procurementCompanyNumber: [
            { required: true, message: "采购企业编号不能为空", trigger: "blur" }
          ],
          purchasingPersonNumber: [
            { required: true, message: "采购人编号不能为空", trigger: "blur" }
          ],
          procurementContractNumber: [
            { required: true, message: "采购合同编号不能为空", trigger: "blur" }
          ],
          ifContracted : [
            { required: true, message: "签约状态不能为空", trigger: "blur" }
          ],
          materialCode: [
            { required: true, message: "物料编号不能为空", trigger: "blur" }
          ],
          unitPrice: [
            { required: true, message: "单价不能为空", trigger: "blur" }
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
        this.contract_signingList=[{
            procurementCompanyNumber: "111",
          purchasingPersonNumber: "222",
          procurementContractNumber:"333",
          ifContracted :"444",
          materialCode:"555",
          unitPrice:"666",
          remark :"777"
          },{
            procurementCompanyNumber: "1",
          purchasingPersonNumber: "2",
          procurementContractNumber:"3",
          ifContracted :"4",
          materialCode:"5",
          unitPrice:"6",
          remark :"7"
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
            procurementCompanyNumber: null,
          purchasingPersonNumber: null,
          procurementContractNumber:null,
          ifContracted :null,
          materialCode:null,
          unitPrice:null,
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
        this.title = "采购签约管理";
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

      <el-table v-loading="loading" :data="contract_signingList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="采购企业编号" align="center" prop="procurementCompanyNumber" />
        <el-table-column label="采购人编号" align="center" prop="purchasingPersonNumber" />
        <el-table-column label="采购合同编号" align="center" prop="procurementContractNumber" />
        <el-table-column label="签约状态" align="center" prop="ifContracted" />
        <el-table-column label="物料编号" align="center" prop="materialCode" />
        <el-table-column label="单价" align="center" prop="unitPrice" />
        <el-table-column label="备注" align="center" prop="remark" />
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

      <!-- 添加或修改contract_signing对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="采购企业编号" prop="procurementCompanyNumber">
            <el-input v-model="form.procurementCompanyNumber" placeholder="请输入采购企业编号" />
          </el-form-item>
          <el-form-item label="采购人编号" prop="purchasingPersonNumber">
            <el-input v-model="form.purchasingPersonNumber" placeholder="请输入采购人编号" />
          </el-form-item>
          <el-form-item label="采购合同编号" prop="procurementContractNumber">
            <el-input v-model="form.procurementContractNumber" placeholder="请输入采购合同编号" />
          </el-form-item>
          <el-form-item label="签约状态" prop="ifContracted">
            <el-input v-model="form.ifContracted" placeholder="请输入签约状态" />
          </el-form-item>
          <el-form-item label="物料编号" prop="materialCode">
            <el-input v-model="form.materialCode" placeholder="请输入物料编号" />
          </el-form-item>
          <el-form-item label="单价" prop="unitPrice">
            <el-input v-model="form.unitPrice" placeholder="请输入单价" />
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
    name: "contract_signing",
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
        contract_signingList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          procurementCompanyNumber: null,
          purchasingPersonNumber: null,
          procurementContractNumber:null,
          ifContracted :null,
          materialCode:null,
          unitPrice:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            procurementCompanyNumber: [
            { required: true, message: "采购企业编号不能为空", trigger: "blur" }
          ],
          purchasingPersonNumber: [
            { required: true, message: "采购人编号不能为空", trigger: "blur" }
          ],
          procurementContractNumber: [
            { required: true, message: "采购合同编号不能为空", trigger: "blur" }
          ],
          ifContracted : [
            { required: true, message: "签约状态不能为空", trigger: "blur" }
          ],
          materialCode: [
            { required: true, message: "物料编号不能为空", trigger: "blur" }
          ],
          unitPrice: [
            { required: true, message: "单价不能为空", trigger: "blur" }
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
        this.contract_signingList=[{
            procurementCompanyNumber: "111",
          purchasingPersonNumber: "222",
          procurementContractNumber:"333",
          ifContracted :"444",
          materialCode:"555",
          unitPrice:"666",
          remark :"777"
          },{
            procurementCompanyNumber: "1",
          purchasingPersonNumber: "2",
          procurementContractNumber:"3",
          ifContracted :"4",
          materialCode:"5",
          unitPrice:"6",
          remark :"7"
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
            procurementCompanyNumber: null,
          purchasingPersonNumber: null,
          procurementContractNumber:null,
          ifContracted :null,
          materialCode:null,
          unitPrice:null,
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
        this.title = "采购签约管理";
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
