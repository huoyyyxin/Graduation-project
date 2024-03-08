<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="退货单编号" prop="returnOrderNumber">
          <el-input
            v-model="queryParams.returnOrderNumber"
            placeholder="请输入退货单编号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="产品品类编号" prop="productCategoryNumber">
          <el-input
            v-model="queryParams.productCategoryNumber"
            placeholder="请输入产品品类编号"
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

      <el-table v-loading="loading" :data="sales_returnList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="退货单编号" align="center" prop="returnOrderNumber" />
        <el-table-column width = "150" label="退货企业编号" align="center" prop="returnCompanyNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="UDI码" align="center" prop="udi" />
        <el-table-column width = "150" label="退货数量" align="center" prop="returnNumber" />
        <el-table-column width = "150" label="售后人编号" align="center" prop="afterSalesPersonnelNumber" />
        <el-table-column width = "150" label="制单人编号" align="center" prop="creatorID" />
        <el-table-column width = "150" label="签订日期" align="center" prop="signingDate" />
        <el-table-column width = "150" label="制单日期" align="center" prop="productionDate" />
        <el-table-column width = "150" label="质检员编号" align="center" prop="qualityInspectorNumber" />
        <el-table-column width = "150" label="质检日期" align="center" prop="qualityInspectionDate" />
        <el-table-column width = "150" label="退货原因" align="center" prop="returnReason" />
        <el-table-column width = "150" label="是否二次销售" align="center" prop="ifSecondarySale" />
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

      <!-- 添加或修改sales_return对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="退货单编号" prop="returnOrderNumber">
            <el-input v-model="form.returnOrderNumber" placeholder="请输入退货单编号" />
          </el-form-item>
          <el-form-item label="退货企业编号" prop="returnCompanyNumber">
            <el-input v-model="form.returnCompanyNumber" placeholder="请输入退货企业编号" />
          </el-form-item>
          <el-form-item label="产品品类编号" prop="productCategoryNumber">
            <el-input v-model="form.productCategoryNumber" placeholder="请输入产品品类编号" />
          </el-form-item>
          <el-form-item label="UDI码" prop="udi">
            <el-input v-model="form.udi" placeholder="请输入UDI码" />
          </el-form-item>
          <el-form-item label="退货数量" prop="returnNumber">
            <el-input v-model="form.returnNumber" placeholder="请输入退货数量" />
          </el-form-item>
          <el-form-item label="售后人编号" prop="afterSalesPersonnelNumber">
            <el-input v-model="form.afterSalesPersonnelNumber" placeholder="请输入售后人编号" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人编号" />
          </el-form-item>
          <el-form-item label="签订日期" prop="signingDate">
            <el-input v-model="form.signingDate" placeholder="请输入签订日期" />
          </el-form-item>
          <el-form-item label="制单日期" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单日期" />
          </el-form-item>
          <el-form-item label="质检员编号" prop="qualityInspectorNumber">
            <el-input v-model="form.qualityInspectorNumber" placeholder="请输入质检员编号" />
          </el-form-item>
          <el-form-item label="质检日期" prop="qualityInspectionDate">
            <el-input v-model="form.qualityInspectionDate" placeholder="请输入质检日期" />
          </el-form-item>
          <el-form-item label="退货原因" prop="returnReason">
            <el-input v-model="form.returnReason" placeholder="请输入退货原因" />
          </el-form-item>
          <el-form-item label="是否二次销售" prop="ifSecondarySale">
            <el-input v-model="form.ifSecondarySale" placeholder="请输入是否二次销售" />
          </el-form-item>
          <el-form-item label="状态标志" prop="statusFlag">
            <el-input v-model="form.statusFlag" placeholder="请输入状态标志" />
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
    name: "sales_return",
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
        // sales_outbound_order表格数据
        sales_returnList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          returnOrderNumber: null,
          returnCompanyNumber: null,
          productCategoryNumber: null,
          udi: null,
          returnNumber: null,
          afterSalesPersonnelNumber: null,
          creatorID:null,
          signingDate:null,
          productionDate:null,
          qualityInspectorNumber:null,
          qualityInspectionDate:null,
          returnReason:null,
          ifSecondarySale:null,
          statusFlag:null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            returnOrderNumber: [
            { required: true, message: "退货单编号不能为空", trigger: "blur" }
          ],
          returnCompanyNumber: [
            { required: true, message: "退货企业编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          udi: [
            { required: true, message: "UDI码不能为空", trigger: "blur" }
          ],
          returnNumber: [
            { required: true, message: "退货数量不能为空", trigger: "blur" }
          ],
          afterSalesPersonnelNumber: [
            { required: true, message: "售后人编号不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          qualityInspectorNumber: [
            { required: true, message: "质检员编号不能为空", trigger: "blur" }
          ],
          qualityInspectionDate: [
            { required: true, message: "质检日期不能为空", trigger: "blur" }
          ],
          ifSecondarySale: [
            { required: true, message: "是否进行二次销售不能为空", trigger: "blur" }
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
        this.sales_returnList=[{
            returnOrderNumber: "null",
          returnCompanyNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          returnNumber: "null",
          afterSalesPersonnelNumber: "null",
          creatorID:"null",
          signingDate:"null",
          productionDate:"null",
          qualityInspectorNumber:"null",
          qualityInspectionDate:"null",
          returnReason:"null",
          ifSecondarySale:"null",
          statusFlag:"null",
          remark:"null"
          },{
            returnOrderNumber: "null",
          returnCompanyNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          returnNumber: "null",
          afterSalesPersonnelNumber: "null",
          creatorID:"null",
          signingDate:"null",
          productionDate:"null",
          qualityInspectorNumber:"null",
          qualityInspectionDate:"null",
          returnReason:"null",
          ifSecondarySale:"null",
          statusFlag:"null",
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
            returnOrderNumber: null,
          returnCompanyNumber: null,
          productCategoryNumber: null,
          udi: null,
          returnNumber: null,
          afterSalesPersonnelNumber: null,
          creatorID:null,
          signingDate:null,
          productionDate:null,
          qualityInspectorNumber:null,
          qualityInspectionDate:null,
          returnReason:null,
          ifSecondarySale:null,
          statusFlag:null,
          remark:null
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
        this.title = "销售退货单管理";
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
        <el-form-item label="退货单编号" prop="returnOrderNumber">
          <el-input
            v-model="queryParams.returnOrderNumber"
            placeholder="请输入退货单编号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="产品品类编号" prop="productCategoryNumber">
          <el-input
            v-model="queryParams.productCategoryNumber"
            placeholder="请输入产品品类编号"
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

      <el-table v-loading="loading" :data="sales_returnList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="退货单编号" align="center" prop="returnOrderNumber" />
        <el-table-column width = "150" label="退货企业编号" align="center" prop="returnCompanyNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="UDI码" align="center" prop="udi" />
        <el-table-column width = "150" label="退货数量" align="center" prop="returnNumber" />
        <el-table-column width = "150" label="售后人编号" align="center" prop="afterSalesPersonnelNumber" />
        <el-table-column width = "150" label="制单人编号" align="center" prop="creatorID" />
        <el-table-column width = "150" label="签订日期" align="center" prop="signingDate" />
        <el-table-column width = "150" label="制单日期" align="center" prop="productionDate" />
        <el-table-column width = "150" label="质检员编号" align="center" prop="qualityInspectorNumber" />
        <el-table-column width = "150" label="质检日期" align="center" prop="qualityInspectionDate" />
        <el-table-column width = "150" label="退货原因" align="center" prop="returnReason" />
        <el-table-column width = "150" label="是否二次销售" align="center" prop="ifSecondarySale" />
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

      <!-- 添加或修改sales_return对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="退货单编号" prop="returnOrderNumber">
            <el-input v-model="form.returnOrderNumber" placeholder="请输入退货单编号" />
          </el-form-item>
          <el-form-item label="退货企业编号" prop="returnCompanyNumber">
            <el-input v-model="form.returnCompanyNumber" placeholder="请输入退货企业编号" />
          </el-form-item>
          <el-form-item label="产品品类编号" prop="productCategoryNumber">
            <el-input v-model="form.productCategoryNumber" placeholder="请输入产品品类编号" />
          </el-form-item>
          <el-form-item label="UDI码" prop="udi">
            <el-input v-model="form.udi" placeholder="请输入UDI码" />
          </el-form-item>
          <el-form-item label="退货数量" prop="returnNumber">
            <el-input v-model="form.returnNumber" placeholder="请输入退货数量" />
          </el-form-item>
          <el-form-item label="售后人编号" prop="afterSalesPersonnelNumber">
            <el-input v-model="form.afterSalesPersonnelNumber" placeholder="请输入售后人编号" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人编号" />
          </el-form-item>
          <el-form-item label="签订日期" prop="signingDate">
            <el-input v-model="form.signingDate" placeholder="请输入签订日期" />
          </el-form-item>
          <el-form-item label="制单日期" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单日期" />
          </el-form-item>
          <el-form-item label="质检员编号" prop="qualityInspectorNumber">
            <el-input v-model="form.qualityInspectorNumber" placeholder="请输入质检员编号" />
          </el-form-item>
          <el-form-item label="质检日期" prop="qualityInspectionDate">
            <el-input v-model="form.qualityInspectionDate" placeholder="请输入质检日期" />
          </el-form-item>
          <el-form-item label="退货原因" prop="returnReason">
            <el-input v-model="form.returnReason" placeholder="请输入退货原因" />
          </el-form-item>
          <el-form-item label="是否二次销售" prop="ifSecondarySale">
            <el-input v-model="form.ifSecondarySale" placeholder="请输入是否二次销售" />
          </el-form-item>
          <el-form-item label="状态标志" prop="statusFlag">
            <el-input v-model="form.statusFlag" placeholder="请输入状态标志" />
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
    name: "sales_return",
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
        // sales_outbound_order表格数据
        sales_returnList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          returnOrderNumber: null,
          returnCompanyNumber: null,
          productCategoryNumber: null,
          udi: null,
          returnNumber: null,
          afterSalesPersonnelNumber: null,
          creatorID:null,
          signingDate:null,
          productionDate:null,
          qualityInspectorNumber:null,
          qualityInspectionDate:null,
          returnReason:null,
          ifSecondarySale:null,
          statusFlag:null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            returnOrderNumber: [
            { required: true, message: "退货单编号不能为空", trigger: "blur" }
          ],
          returnCompanyNumber: [
            { required: true, message: "退货企业编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          udi: [
            { required: true, message: "UDI码不能为空", trigger: "blur" }
          ],
          returnNumber: [
            { required: true, message: "退货数量不能为空", trigger: "blur" }
          ],
          afterSalesPersonnelNumber: [
            { required: true, message: "售后人编号不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          qualityInspectorNumber: [
            { required: true, message: "质检员编号不能为空", trigger: "blur" }
          ],
          qualityInspectionDate: [
            { required: true, message: "质检日期不能为空", trigger: "blur" }
          ],
          ifSecondarySale: [
            { required: true, message: "是否进行二次销售不能为空", trigger: "blur" }
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
        this.sales_returnList=[{
            returnOrderNumber: "null",
          returnCompanyNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          returnNumber: "null",
          afterSalesPersonnelNumber: "null",
          creatorID:"null",
          signingDate:"null",
          productionDate:"null",
          qualityInspectorNumber:"null",
          qualityInspectionDate:"null",
          returnReason:"null",
          ifSecondarySale:"null",
          statusFlag:"null",
          remark:"null"
          },{
            returnOrderNumber: "null",
          returnCompanyNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          returnNumber: "null",
          afterSalesPersonnelNumber: "null",
          creatorID:"null",
          signingDate:"null",
          productionDate:"null",
          qualityInspectorNumber:"null",
          qualityInspectionDate:"null",
          returnReason:"null",
          ifSecondarySale:"null",
          statusFlag:"null",
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
            returnOrderNumber: null,
          returnCompanyNumber: null,
          productCategoryNumber: null,
          udi: null,
          returnNumber: null,
          afterSalesPersonnelNumber: null,
          creatorID:null,
          signingDate:null,
          productionDate:null,
          qualityInspectorNumber:null,
          qualityInspectionDate:null,
          returnReason:null,
          ifSecondarySale:null,
          statusFlag:null,
          remark:null
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
        this.title = "销售退货单管理";
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
