<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
        <el-form-item label="原料入库单编号" prop="RawMaterialReceiptNumber">
          <el-input
            v-model="queryParams.RawMaterialReceiptNumber"
            placeholder="请输入原料入库单编号"
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

      <el-table v-loading="loading" :data="raw_material_outboundList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="原料出库单编号" align="center" prop="rawMaterialDeliveryNoteNumber" />
        <el-table-column width = "150" label="物料编号" align="center" prop="materialCode" />
        <el-table-column width = "150" label="出库数量" align="center" prop="outboundQuantity" />
        <el-table-column width = "150" label="生产批号" align="center" prop="batchNumber" />
        <el-table-column width = "150" label="库存数量" align="center" prop="inventoryQuantity" />
        <el-table-column width = "150" label="库管员" align="center" prop="warehouseKeeper" />
        <el-table-column width = "150" label="制单日期" align="center" prop="productionDate" />
        <el-table-column width = "150" label="制单人" align="center" prop="creator" />
        <el-table-column width = "150" label="备注" align="center" prop="remark" />
        <el-table-column width = "150" fixed = "right" label="操作" align="center" class-name="small-padding fixed-width">
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

      <!-- 添加或修改raw_material_storaget对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="原料出库单编号" prop="rawMaterialDeliveryNoteNumber">
            <el-input v-model="form.rawMaterialDeliveryNoteNumber" placeholder="请输入原料出库单编号" />
          </el-form-item>
          <el-form-item label="物料编号" prop="materialCode">
            <el-input v-model="form.materialCode" placeholder="请输入物料编号" />
          </el-form-item>
          <el-form-item label="出库数量 " prop="outboundQuantity">
            <el-input v-model="form.outboundQuantity" placeholder="请输入出库数量 " />
          </el-form-item>
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="库存数量" prop="inventoryQuantity">
            <el-input v-model="form.inventoryQuantity" placeholder="请输入库存数量" />
          </el-form-item>
          <el-form-item label="库管员" prop="warehouseKeeper">
            <el-input v-model="form.warehouseKeeper" placeholder="请输入库管员" />
          </el-form-item>
          <el-form-item label="制单日期" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单日期" />
          </el-form-item>
          <el-form-item label="制单人" prop="creator">
            <el-input v-model="form.creator" placeholder="请输入制单人" />
          </el-form-item>
          <el-form-item label="备注" prop="remark">
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
    name: "raw_material_outbound",
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
        // raw_material_outbound表格数据
        raw_material_outboundList: [],
        // 弹入出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          rawMaterialDeliveryNoteNumber: null,
          materialCode: null,
          outboundQuantity:null,
          batchNumber :null,
          inventoryQuantity:null,
          warehouseKeeper:null,
          productionDate:null,
          creator:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            rawMaterialDeliveryNoteNumber: [
            { required: true, message: "原料出库单编号不能为空", trigger: "blur" }
          ],
          materialCode: [
            { required: true, message: "物料编号不能为空", trigger: "blur" }
          ],
          outboundQuantity: [
            { required: true, message: "出库数量不能为空", trigger: "blur" }
          ],
          batchNumber : [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          warehouseKeeper: [
            { required: true, message: "制单日期不能为空", trigger: "blur" }
          ],
          productionDate: [
            { required: true, message: "库管员不能为空", trigger: "blur" }
          ],
          creator: [
            { required: true, message: "制单人不能为空", trigger: "blur" }
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
        this.raw_material_outboundList=[{
          rawMaterialDeliveryNoteNumber: "null",
          materialCode: "null",
          outboundQuantity:"null",
          batchNumber :"null",
          inventoryQuantity:"null",
          warehouseKeeper:"null",
          productionDate:"null",
          creator:"null",
          remark :"02"
          },{
          rawMaterialDeliveryNoteNumber: "null",
          materialCode: "null",
          outboundQuantity:"null",
          batchNumber :"null",
          inventoryQuantity:"null",
          warehouseKeeper:"null",
          productionDate:"null",
          creator:"null",
          remark :"02"
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
            rawMaterialDeliveryNoteNumber: null,
          materialCode: null,
          outboundQuantity:null,
          batchNumber :null,
          inventoryQuantity:null,
          warehouseKeeper:null,
          productionDate:null,
          creator:null,
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
        this.title = "采购出库管理";
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
        <el-form-item label="原料入库单编号" prop="RawMaterialReceiptNumber">
          <el-input
            v-model="queryParams.RawMaterialReceiptNumber"
            placeholder="请输入原料入库单编号"
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

      <el-table v-loading="loading" :data="raw_material_outboundList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="原料出库单编号" align="center" prop="rawMaterialDeliveryNoteNumber" />
        <el-table-column width = "150" label="物料编号" align="center" prop="materialCode" />
        <el-table-column width = "150" label="出库数量" align="center" prop="outboundQuantity" />
        <el-table-column width = "150" label="生产批号" align="center" prop="batchNumber" />
        <el-table-column width = "150" label="库存数量" align="center" prop="inventoryQuantity" />
        <el-table-column width = "150" label="库管员" align="center" prop="warehouseKeeper" />
        <el-table-column width = "150" label="制单日期" align="center" prop="productionDate" />
        <el-table-column width = "150" label="制单人" align="center" prop="creator" />
        <el-table-column width = "150" label="备注" align="center" prop="remark" />
        <el-table-column width = "150" fixed = "right" label="操作" align="center" class-name="small-padding fixed-width">
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

      <!-- 添加或修改raw_material_storaget对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="原料出库单编号" prop="rawMaterialDeliveryNoteNumber">
            <el-input v-model="form.rawMaterialDeliveryNoteNumber" placeholder="请输入原料出库单编号" />
          </el-form-item>
          <el-form-item label="物料编号" prop="materialCode">
            <el-input v-model="form.materialCode" placeholder="请输入物料编号" />
          </el-form-item>
          <el-form-item label="出库数量 " prop="outboundQuantity">
            <el-input v-model="form.outboundQuantity" placeholder="请输入出库数量 " />
          </el-form-item>
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="库存数量" prop="inventoryQuantity">
            <el-input v-model="form.inventoryQuantity" placeholder="请输入库存数量" />
          </el-form-item>
          <el-form-item label="库管员" prop="warehouseKeeper">
            <el-input v-model="form.warehouseKeeper" placeholder="请输入库管员" />
          </el-form-item>
          <el-form-item label="制单日期" prop="productionDate">
            <el-input v-model="form.productionDate" placeholder="请输入制单日期" />
          </el-form-item>
          <el-form-item label="制单人" prop="creator">
            <el-input v-model="form.creator" placeholder="请输入制单人" />
          </el-form-item>
          <el-form-item label="备注" prop="remark">
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
    name: "raw_material_outbound",
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
        // raw_material_outbound表格数据
        raw_material_outboundList: [],
        // 弹入出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          rawMaterialDeliveryNoteNumber: null,
          materialCode: null,
          outboundQuantity:null,
          batchNumber :null,
          inventoryQuantity:null,
          warehouseKeeper:null,
          productionDate:null,
          creator:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            rawMaterialDeliveryNoteNumber: [
            { required: true, message: "原料出库单编号不能为空", trigger: "blur" }
          ],
          materialCode: [
            { required: true, message: "物料编号不能为空", trigger: "blur" }
          ],
          outboundQuantity: [
            { required: true, message: "出库数量不能为空", trigger: "blur" }
          ],
          batchNumber : [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          warehouseKeeper: [
            { required: true, message: "制单日期不能为空", trigger: "blur" }
          ],
          productionDate: [
            { required: true, message: "库管员不能为空", trigger: "blur" }
          ],
          creator: [
            { required: true, message: "制单人不能为空", trigger: "blur" }
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
        this.raw_material_outboundList=[{
          rawMaterialDeliveryNoteNumber: "null",
          materialCode: "null",
          outboundQuantity:"null",
          batchNumber :"null",
          inventoryQuantity:"null",
          warehouseKeeper:"null",
          productionDate:"null",
          creator:"null",
          remark :"02"
          },{
          rawMaterialDeliveryNoteNumber: "null",
          materialCode: "null",
          outboundQuantity:"null",
          batchNumber :"null",
          inventoryQuantity:"null",
          warehouseKeeper:"null",
          productionDate:"null",
          creator:"null",
          remark :"02"
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
            rawMaterialDeliveryNoteNumber: null,
          materialCode: null,
          outboundQuantity:null,
          batchNumber :null,
          inventoryQuantity:null,
          warehouseKeeper:null,
          productionDate:null,
          creator:null,
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
        this.title = "采购出库管理";
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
