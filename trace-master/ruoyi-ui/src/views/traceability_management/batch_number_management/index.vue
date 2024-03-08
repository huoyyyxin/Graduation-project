<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="生产批号" prop="batchNumber">
          <el-input
            v-model="queryParams.batchNumber"
            placeholder="请输入生产批号"
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

      <el-table v-loading="loading" :data="traceability_managementList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="生产批号" align="center" prop="batchNumber" />
        <el-table-column width = "150" label="生产产品" align="center" prop="yieldProduction" />
        <el-table-column width = "150" label="物料检验单编号" align="center" prop="materialInspectionFormNumber" />
        <el-table-column width = "150" label="采购到货单编号" align="center" prop="purchaseDeliveryNoteNumber" />
        <el-table-column width = "150" label="原料入库单编号" align="center" prop="RawMaterialReceiptNumber" />
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

      <!-- 添加或修改traceability_management对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="生产产品" prop="yieldProduction">
            <el-input v-model="form.yieldProduction" placeholder="请输入生产产品" />
          </el-form-item>
          <el-form-item label="物料检验单编号" prop="materialInspectionFormNumber">
            <el-input v-model="form.materialInspectionFormNumber" placeholder="请输入物料检验单编号" />
          </el-form-item>
          <el-form-item label="采购到货单编号" prop="purchaseDeliveryNoteNumber">
            <el-input v-model="form.purchaseDeliveryNoteNumber" placeholder="请输入采购到货单编号" />
          </el-form-item>
          <el-form-item label="原料入库单编号" prop="RawMaterialReceiptNumber">
            <el-input v-model="form.RawMaterialReceiptNumber" placeholder="请输入原料入库单编号" />
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
    name: "traceability_management",
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
        // traceability_management表格数据
        traceability_managementList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          batchNumber: null,
          yieldProduction: null,
          materialInspectionFormNumber: null,
          purchaseDeliveryNoteNumber: null,
          RawMaterialReceiptNumber: null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            batchNumber: [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          yieldProduction: [
            { required: true, message: "生产产品不能为空", trigger: "blur" }
          ],
          materialInspectionFormNumber: [
            { required: true, message: "物料检验单编号不能为空", trigger: "blur" }
          ],
          purchaseDeliveryNoteNumber: [
            { required: true, message: "采购到货单编号不能为空", trigger: "blur" }
          ],
          RawMaterialReceiptNumber: [
            { required: true, message: "原料入库单编号不能为空", trigger: "blur" }
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
        this.traceability_managementList=[{
            batchNumber: "1351",
          yieldProduction: "null",
          materialInspectionFormNumber: "null",
          purchaseDeliveryNoteNumber: "null",
          RawMaterialReceiptNumber: "null",
          remark:"null"
          },{
            batchNumber: "1351",
          yieldProduction: "453",
          materialInspectionFormNumber: "78",
          purchaseDeliveryNoteNumber: "45",
          RawMaterialReceiptNumber: "6",
          remark:"12"
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
            batchNumber: null,
          yieldProduction: null,
          materialInspectionFormNumber: null,
          purchaseDeliveryNoteNumber: null,
          RawMaterialReceiptNumber: null,
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
        this.title = "生产批号管理";
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
        <el-form-item label="生产批号" prop="batchNumber">
          <el-input
            v-model="queryParams.batchNumber"
            placeholder="请输入生产批号"
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

      <el-table v-loading="loading" :data="traceability_managementList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="生产批号" align="center" prop="batchNumber" />
        <el-table-column width = "150" label="生产产品" align="center" prop="yieldProduction" />
        <el-table-column width = "150" label="物料检验单编号" align="center" prop="materialInspectionFormNumber" />
        <el-table-column width = "150" label="采购到货单编号" align="center" prop="purchaseDeliveryNoteNumber" />
        <el-table-column width = "150" label="原料入库单编号" align="center" prop="RawMaterialReceiptNumber" />
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

      <!-- 添加或修改traceability_management对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="生产产品" prop="yieldProduction">
            <el-input v-model="form.yieldProduction" placeholder="请输入生产产品" />
          </el-form-item>
          <el-form-item label="物料检验单编号" prop="materialInspectionFormNumber">
            <el-input v-model="form.materialInspectionFormNumber" placeholder="请输入物料检验单编号" />
          </el-form-item>
          <el-form-item label="采购到货单编号" prop="purchaseDeliveryNoteNumber">
            <el-input v-model="form.purchaseDeliveryNoteNumber" placeholder="请输入采购到货单编号" />
          </el-form-item>
          <el-form-item label="原料入库单编号" prop="RawMaterialReceiptNumber">
            <el-input v-model="form.RawMaterialReceiptNumber" placeholder="请输入原料入库单编号" />
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
    name: "traceability_management",
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
        // traceability_management表格数据
        traceability_managementList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          batchNumber: null,
          yieldProduction: null,
          materialInspectionFormNumber: null,
          purchaseDeliveryNoteNumber: null,
          RawMaterialReceiptNumber: null,
          remark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            batchNumber: [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          yieldProduction: [
            { required: true, message: "生产产品不能为空", trigger: "blur" }
          ],
          materialInspectionFormNumber: [
            { required: true, message: "物料检验单编号不能为空", trigger: "blur" }
          ],
          purchaseDeliveryNoteNumber: [
            { required: true, message: "采购到货单编号不能为空", trigger: "blur" }
          ],
          RawMaterialReceiptNumber: [
            { required: true, message: "原料入库单编号不能为空", trigger: "blur" }
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
        this.traceability_managementList=[{
            batchNumber: "1351",
          yieldProduction: "null",
          materialInspectionFormNumber: "null",
          purchaseDeliveryNoteNumber: "null",
          RawMaterialReceiptNumber: "null",
          remark:"null"
          },{
            batchNumber: "1351",
          yieldProduction: "453",
          materialInspectionFormNumber: "78",
          purchaseDeliveryNoteNumber: "45",
          RawMaterialReceiptNumber: "6",
          remark:"12"
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
            batchNumber: null,
          yieldProduction: null,
          materialInspectionFormNumber: null,
          purchaseDeliveryNoteNumber: null,
          RawMaterialReceiptNumber: null,
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
        this.title = "生产批号管理";
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
