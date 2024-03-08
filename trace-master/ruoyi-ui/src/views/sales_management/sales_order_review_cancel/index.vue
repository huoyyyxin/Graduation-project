<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="销售单编号" prop="salesOrderNumber">
          <el-input
            v-model="queryParams.salesOrderNumber"
            placeholder="请输入销售单编号"
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

      <el-table v-loading="loading" :data="sales_order_review_cancelList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="销售单编号" align="center" prop="salesOrderNumber" />
        <el-table-column width = "150" label="销售企业编号" align="center" prop="salesCompanyNumber" />
        <el-table-column width = "150" label="销售人编号" align="center" prop="salespersonID" />
        <el-table-column width = "150" label="销售合同编号" align="center" prop="salesContractNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="审核是否通过" align="center" prop="ifPassed" />
        <el-table-column width = "150" label="审核是否作废" align="center" prop="invalid" />
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

      <!-- 添加或修改sales_order_review对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="销售单编号" prop="salesOrderNumber">
            <el-input v-model="form.salesOrderNumber" placeholder="请输入销售单编号" />
          </el-form-item>
          <el-form-item label="销售企业编号" prop="salesCompanyNumber">
            <el-input v-model="form.salesCompanyNumber" placeholder="请输入销售企业编号" />
          </el-form-item>
          <el-form-item label="销售人编号" prop="salespersonID">
            <el-input v-model="form.salespersonID" placeholder="请输入销售人编号" />
          </el-form-item>
          <el-form-item label="销售合同编号" prop="salesContractNumber">
            <el-input v-model="form.salesContractNumber" placeholder="请输入销售合同编号" />
          </el-form-item>
          <el-form-item label="产品品类编号" prop="productCategoryNumber">
            <el-input v-model="form.productCategoryNumber" placeholder="请输入产品品类编号" />
          </el-form-item>
          <el-form-item label="审核是否通过" prop="ifPassed">
            <el-input v-model="form.ifPassed" placeholder="请输入是或否" />
          </el-form-item>
          <el-form-item label="审核是否作废" prop="invalid">
            <el-input v-model="form.ifPassed" placeholder="请输入是或否" />
          </el-form-item>
          <el-form-item label="备注" prop="rawMaterialRemark">
            <el-input v-model="form.rawMaterialRemark" placeholder="请输入备注" />
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
    name: "sales_order_review_cancel",
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
        // sales_order_review表格数据
        sales_order_review_cancelList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          salesOrderNumber: null,
          salesCompanyNumber: null,
          salespersonID: null,
          salesContractNumber: null,
          productCategoryNumber: null,
          ifPassed:null,
          invalid:null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            salesOrderNumber: [
            { required: true, message: "销售单编号不能为空", trigger: "blur" }
          ],
          salesCompanyNumber: [
            { required: true, message: "销售企业编号不能为空", trigger: "blur" }
          ],
          salespersonID: [
            { required: true, message: "销售人编号不能为空", trigger: "blur" }
          ],
          salesContractNumber: [
            { required: true, message: "销售合同编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          invalid: [
            { required: true, message: "审核单作废不能为空", trigger: "blur" }
          ],
          ifPassed: [
            { required: true, message: "审核是否通过不能为空", trigger: "blur" }
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
        this.sales_order_review_cancelList=[{
            salesOrderNumber: "null",
          salesCompanyNumber: "null",
          salespersonID: "null",
          salesContractNumber: "null",
          productCategoryNumber: "null",
          ifPassed:"null",
          invalid:"null",
          remark:"null"
          },{
            salesOrderNumber: "null",
          salesCompanyNumber: "null",
          salespersonID: "null",
          salesContractNumber: "null",
          productCategoryNumber: "null",
          ifPassed:"null",
          invalid:"null",
          remark:"null"
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
            salesOrderNumber: null,
          salesCompanyNumber: null,
          salespersonID: null,
          salesContractNumber: null,
          productCategoryNumber: null,
          ifPassed:null,
          invalid:null,
          remark:null,
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
        this.title = "销售单审核作废管理";
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
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="销售单编号" prop="salesOrderNumber">
          <el-input
            v-model="queryParams.salesOrderNumber"
            placeholder="请输入销售单编号"
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

      <el-table v-loading="loading" :data="sales_order_review_cancelList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="销售单编号" align="center" prop="salesOrderNumber" />
        <el-table-column width = "150" label="销售企业编号" align="center" prop="salesCompanyNumber" />
        <el-table-column width = "150" label="销售人编号" align="center" prop="salespersonID" />
        <el-table-column width = "150" label="销售合同编号" align="center" prop="salesContractNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="审核是否通过" align="center" prop="ifPassed" />
        <el-table-column width = "150" label="审核是否作废" align="center" prop="invalid" />
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

      <!-- 添加或修改sales_order_review对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="销售单编号" prop="salesOrderNumber">
            <el-input v-model="form.salesOrderNumber" placeholder="请输入销售单编号" />
          </el-form-item>
          <el-form-item label="销售企业编号" prop="salesCompanyNumber">
            <el-input v-model="form.salesCompanyNumber" placeholder="请输入销售企业编号" />
          </el-form-item>
          <el-form-item label="销售人编号" prop="salespersonID">
            <el-input v-model="form.salespersonID" placeholder="请输入销售人编号" />
          </el-form-item>
          <el-form-item label="销售合同编号" prop="salesContractNumber">
            <el-input v-model="form.salesContractNumber" placeholder="请输入销售合同编号" />
          </el-form-item>
          <el-form-item label="产品品类编号" prop="productCategoryNumber">
            <el-input v-model="form.productCategoryNumber" placeholder="请输入产品品类编号" />
          </el-form-item>
          <el-form-item label="审核是否通过" prop="ifPassed">
            <el-input v-model="form.ifPassed" placeholder="请输入是或否" />
          </el-form-item>
          <el-form-item label="审核是否作废" prop="invalid">
            <el-input v-model="form.ifPassed" placeholder="请输入是或否" />
          </el-form-item>
          <el-form-item label="备注" prop="rawMaterialRemark">
            <el-input v-model="form.rawMaterialRemark" placeholder="请输入备注" />
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
    name: "sales_order_review_cancel",
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
        // sales_order_review表格数据
        sales_order_review_cancelList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          salesOrderNumber: null,
          salesCompanyNumber: null,
          salespersonID: null,
          salesContractNumber: null,
          productCategoryNumber: null,
          ifPassed:null,
          invalid:null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            salesOrderNumber: [
            { required: true, message: "销售单编号不能为空", trigger: "blur" }
          ],
          salesCompanyNumber: [
            { required: true, message: "销售企业编号不能为空", trigger: "blur" }
          ],
          salespersonID: [
            { required: true, message: "销售人编号不能为空", trigger: "blur" }
          ],
          salesContractNumber: [
            { required: true, message: "销售合同编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          invalid: [
            { required: true, message: "审核单作废不能为空", trigger: "blur" }
          ],
          ifPassed: [
            { required: true, message: "审核是否通过不能为空", trigger: "blur" }
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
        this.sales_order_review_cancelList=[{
            salesOrderNumber: "null",
          salesCompanyNumber: "null",
          salespersonID: "null",
          salesContractNumber: "null",
          productCategoryNumber: "null",
          ifPassed:"null",
          invalid:"null",
          remark:"null"
          },{
            salesOrderNumber: "null",
          salesCompanyNumber: "null",
          salespersonID: "null",
          salesContractNumber: "null",
          productCategoryNumber: "null",
          ifPassed:"null",
          invalid:"null",
          remark:"null"
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
            salesOrderNumber: null,
          salesCompanyNumber: null,
          salespersonID: null,
          salesContractNumber: null,
          productCategoryNumber: null,
          ifPassed:null,
          invalid:null,
          remark:null,
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
        this.title = "销售单审核作废管理";
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
