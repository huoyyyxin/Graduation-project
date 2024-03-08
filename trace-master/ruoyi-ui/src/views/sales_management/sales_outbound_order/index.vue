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
        <el-form-item label="销售出库单编号" prop="salesOutboundNumber">
          <el-input
            v-model="queryParams.salesOutboundNumber"
            placeholder="请输入销售出库单编号号"
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

      <el-table v-loading="loading" :data="sales_outbound_orderList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="销售出库单编号" align="center" prop="salesOutboundNumber" />
        <el-table-column width = "150" label="销售单编号" align="center" prop="salesOrderNumber" />
        <el-table-column width = "150" label="库房编号" align="center" prop="warehouseNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="UDI码" align="center" prop="udi" />
        <el-table-column width = "150" label="数量" align="center" prop="Number" />
        <el-table-column width = "150" label="制单人编号" align="center" prop="creatorID" />
        <el-table-column width = "150" label="制单时间" align="center" prop="productionDate" />
        <el-table-column width = "150" label="库管员编号" align="center" prop="warehouseKeeperNumber" />
        <el-table-column width = "150" label="出库日期" align="center" prop="outboundDate" />
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

      <!-- 添加或修改sales_outbound_order对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="销售出库单编号" prop="salesOutboundNumber">
            <el-input v-model="form.salesOutboundNumber" placeholder="请输入销售出库单编号" />
          </el-form-item>
          <el-form-item label="销售单编号" prop="salesOrderNumber">
            <el-input v-model="form.salesOrderNumber" placeholder="请输入销售单编号" />
          </el-form-item>
          <el-form-item label="库房编号" prop="warehouseNumber">
            <el-input v-model="form.warehouseNumber" placeholder="请输入库房编号" />
          </el-form-item>
          <el-form-item label="产品品类编号" prop="productCategoryNumber">
            <el-input v-model="form.productCategoryNumber" placeholder="请输入产品品类编号" />
          </el-form-item>
          <el-form-item label="UDI码" prop="udi">
            <el-input v-model="form.udi" placeholder="请输入UDI码" />
          </el-form-item>
          <el-form-item label="数量" prop="Number">
            <el-input v-model="form.Number" placeholder="请输入数量" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人编号" />
          </el-form-item>
          <el-form-item label="制单时间" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单时间" />
          </el-form-item>
          <el-form-item label="库管员编号" prop="warehouseKeeperNumber">
            <el-input v-model="form.warehouseKeeperNumber" placeholder="请输入库管员编号" />
          </el-form-item>
          <el-form-item label="出库日期" prop="outboundDate">
            <el-input v-model="form.outboundDate" placeholder="请输入出库日期" />
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
    name: "sales_outbound_order",
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
        sales_outbound_orderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          salesOutboundNumber: null,
          salesOrderNumber: null,
          warehouseNumber: null,
          productCategoryNumber: null,
          udi: null,
          Number: null,
          creatorID:null,
          productionDate:null,
          warehouseKeeperNumber:null,
          outboundDate:null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            salesOutboundNumber: [
            { required: true, message: "销售出库单编号不能为空", trigger: "blur" }
          ],
          salesOrderNumber: [
            { required: true, message: "销售单编号不能为空", trigger: "blur" }
          ],
          warehouseNumber: [
            { required: true, message: "库房编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          udi: [
            { required: true, message: "UDI码不能为空", trigger: "blur" }
          ],
          Number: [
            { required: true, message: "数量不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          productionDate: [
            { required: true, message: "制单时间不能为空", trigger: "blur" }
          ],
          warehouseKeeperNumber: [
            { required: true, message: "库管员编号不能为空", trigger: "blur" }
          ],
          outboundDate: [
            { required: true, message: "出库日期不能为空", trigger: "blur" }
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
        this.sales_outbound_orderList=[{
            salesOutboundNumber: "null",
          salesOrderNumber: "null",
          warehouseNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          Number: "null",
          creatorID:"null",
          productionDate:"null",
          warehouseKeeperNumber:"null",
          outboundDate:"null",
          remark:"null"
          },{
            salesOutboundNumber: "null",
          salesOrderNumber: "null",
          warehouseNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          Number: "null",
          creatorID:"null",
          productionDate:"null",
          warehouseKeeperNumber:"null",
          outboundDate:"null",
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
            salesOutboundNumber: null,
          salesOrderNumber: null,
          warehouseNumber: null,
          productCategoryNumber: null,
          udi: null,
          Number: null,
          creatorID:null,
          productionDate:null,
          warehouseKeeperNumber:null,
          outboundDate:null,
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
        this.title = "销售出库单管理";
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
        <el-form-item label="销售出库单编号" prop="salesOutboundNumber">
          <el-input
            v-model="queryParams.salesOutboundNumber"
            placeholder="请输入销售出库单编号号"
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

      <el-table v-loading="loading" :data="sales_outbound_orderList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="销售出库单编号" align="center" prop="salesOutboundNumber" />
        <el-table-column width = "150" label="销售单编号" align="center" prop="salesOrderNumber" />
        <el-table-column width = "150" label="库房编号" align="center" prop="warehouseNumber" />
        <el-table-column width = "150" label="产品品类编号" align="center" prop="productCategoryNumber" />
        <el-table-column width = "150" label="UDI码" align="center" prop="udi" />
        <el-table-column width = "150" label="数量" align="center" prop="Number" />
        <el-table-column width = "150" label="制单人编号" align="center" prop="creatorID" />
        <el-table-column width = "150" label="制单时间" align="center" prop="productionDate" />
        <el-table-column width = "150" label="库管员编号" align="center" prop="warehouseKeeperNumber" />
        <el-table-column width = "150" label="出库日期" align="center" prop="outboundDate" />
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

      <!-- 添加或修改sales_outbound_order对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="销售出库单编号" prop="salesOutboundNumber">
            <el-input v-model="form.salesOutboundNumber" placeholder="请输入销售出库单编号" />
          </el-form-item>
          <el-form-item label="销售单编号" prop="salesOrderNumber">
            <el-input v-model="form.salesOrderNumber" placeholder="请输入销售单编号" />
          </el-form-item>
          <el-form-item label="库房编号" prop="warehouseNumber">
            <el-input v-model="form.warehouseNumber" placeholder="请输入库房编号" />
          </el-form-item>
          <el-form-item label="产品品类编号" prop="productCategoryNumber">
            <el-input v-model="form.productCategoryNumber" placeholder="请输入产品品类编号" />
          </el-form-item>
          <el-form-item label="UDI码" prop="udi">
            <el-input v-model="form.udi" placeholder="请输入UDI码" />
          </el-form-item>
          <el-form-item label="数量" prop="Number">
            <el-input v-model="form.Number" placeholder="请输入数量" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人编号" />
          </el-form-item>
          <el-form-item label="制单时间" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单时间" />
          </el-form-item>
          <el-form-item label="库管员编号" prop="warehouseKeeperNumber">
            <el-input v-model="form.warehouseKeeperNumber" placeholder="请输入库管员编号" />
          </el-form-item>
          <el-form-item label="出库日期" prop="outboundDate">
            <el-input v-model="form.outboundDate" placeholder="请输入出库日期" />
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
    name: "sales_outbound_order",
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
        sales_outbound_orderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          salesOutboundNumber: null,
          salesOrderNumber: null,
          warehouseNumber: null,
          productCategoryNumber: null,
          udi: null,
          Number: null,
          creatorID:null,
          productionDate:null,
          warehouseKeeperNumber:null,
          outboundDate:null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            salesOutboundNumber: [
            { required: true, message: "销售出库单编号不能为空", trigger: "blur" }
          ],
          salesOrderNumber: [
            { required: true, message: "销售单编号不能为空", trigger: "blur" }
          ],
          warehouseNumber: [
            { required: true, message: "库房编号不能为空", trigger: "blur" }
          ],
          productCategoryNumber: [
            { required: true, message: "产品品类编号不能为空", trigger: "blur" }
          ],
          udi: [
            { required: true, message: "UDI码不能为空", trigger: "blur" }
          ],
          Number: [
            { required: true, message: "数量不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          productionDate: [
            { required: true, message: "制单时间不能为空", trigger: "blur" }
          ],
          warehouseKeeperNumber: [
            { required: true, message: "库管员编号不能为空", trigger: "blur" }
          ],
          outboundDate: [
            { required: true, message: "出库日期不能为空", trigger: "blur" }
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
        this.sales_outbound_orderList=[{
            salesOutboundNumber: "null",
          salesOrderNumber: "null",
          warehouseNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          Number: "null",
          creatorID:"null",
          productionDate:"null",
          warehouseKeeperNumber:"null",
          outboundDate:"null",
          remark:"null"
          },{
            salesOutboundNumber: "null",
          salesOrderNumber: "null",
          warehouseNumber: "null",
          productCategoryNumber: "null",
          udi: "null",
          Number: "null",
          creatorID:"null",
          productionDate:"null",
          warehouseKeeperNumber:"null",
          outboundDate:"null",
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
            salesOutboundNumber: null,
          salesOrderNumber: null,
          warehouseNumber: null,
          productCategoryNumber: null,
          udi: null,
          Number: null,
          creatorID:null,
          productionDate:null,
          warehouseKeeperNumber:null,
          outboundDate:null,
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
        this.title = "销售出库单管理";
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
