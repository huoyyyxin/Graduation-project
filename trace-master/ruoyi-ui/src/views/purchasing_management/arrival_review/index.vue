<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="120px">
        <el-form-item label="采购到货单编号" prop="purchaseDeliveryNoteNumber">
          <el-input
            v-model="queryParams.purchaseDeliveryNoteNumber"
            placeholder="请输入采购到货单编号"
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

      <el-table v-loading="loading" :data="arrival_reviewList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="采购到货单编号" align="center" prop="purchaseDeliveryNoteNumber" />
        <el-table-column label="采购计划单编号" align="center" prop="purchasingPlanNumber" />
        <el-table-column label="制单人编号" align="center" prop="creatorID" />
        <el-table-column label="物料编号" align="center" prop="materialCode" />
        <el-table-column label="生产批号" align="center" prop="batchNumber" />
        <el-table-column label="到货数量" align="center" prop="receivedQuantity" />
        <el-table-column label="不合格数量" align="center" prop="unqualifiedquantity" />
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

      <!-- 添加或修改arrival_review对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="采购到货单编号" prop="purchaseDeliveryNoteNumber">
            <el-input v-model="form.purchaseDeliveryNoteNumber" placeholder="请输入采购到货单编号" />
          </el-form-item>
          <el-form-item label="采购计划单编号" prop="purchasingPlanNumber">
            <el-input v-model="form.purchasingPlanNumber" placeholder="请输入采购计划单编号" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人同编号" />
          </el-form-item>
          <el-form-item label="物料编码" prop="materialCode">
            <el-input v-model="form.materialCode" placeholder="请输入物料编码" />
          </el-form-item>
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="到货数量" prop="receivedQuantity">
            <el-input v-model="form.receivedQuantity" placeholder="请输入到货数量" />
          </el-form-item>
          <el-form-item label="不合格数量" prop="unqualifiedquantity">
            <el-input v-model="form.unqualifiedquantity" placeholder="请输入不合格数量" />
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
    name: "arrival_review",
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
        arrival_reviewList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          purchaseDeliveryNoteNumber: null,
          purchasingPlanNumber: null,
          creatorID:null,
          materialCode :null,
          batchNumber:null,
          receivedQuantity:null,
          unqualifiedquantity:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            purchaseDeliveryNoteNumber: [
            { required: true, message: "采购到货单编号不能为空", trigger: "blur" }
          ],
          purchasingPlanNumber: [
            { required: true, message: "采购计划单编号不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          materialCode : [
            { required: true, message: "物料编号不能为空", trigger: "blur" }
          ],
          batchNumber: [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          receivedQuantity: [
            { required: true, message: "到货数量不能为空", trigger: "blur" }
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
        this.arrival_reviewList=[{
            purchaseDeliveryNoteNumber: "111",
          purchasingPlanNumber: "22",
          creatorID:"333",
          materialCode :"444",
          batchNumber:"555",
          receivedQuantity:"666",
          unqualifiedquantity:"777",
          remark :"8888"
          },{
            purchaseDeliveryNoteNumber: "111",
          purchasingPlanNumber: "22",
          creatorID:"333",
          materialCode :"444",
          batchNumber:"555",
          receivedQuantity:"666",
          unqualifiedquantity:"777",
          remark :"8888"
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
          purchaseDeliveryNoteNumber: null,
          purchasingPlanNumber: null,
          creatorID:null,
          materialCode :null,
          batchNumber:null,
          receivedQuantity:null,
          unqualifiedquantity:null,
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
        this.title = "采购到货审核管理";
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
        <el-form-item label="采购到货单编号" prop="purchaseDeliveryNoteNumber">
          <el-input
            v-model="queryParams.purchaseDeliveryNoteNumber"
            placeholder="请输入采购到货单编号"
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

      <el-table v-loading="loading" :data="arrival_reviewList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="采购到货单编号" align="center" prop="purchaseDeliveryNoteNumber" />
        <el-table-column label="采购计划单编号" align="center" prop="purchasingPlanNumber" />
        <el-table-column label="制单人编号" align="center" prop="creatorID" />
        <el-table-column label="物料编号" align="center" prop="materialCode" />
        <el-table-column label="生产批号" align="center" prop="batchNumber" />
        <el-table-column label="到货数量" align="center" prop="receivedQuantity" />
        <el-table-column label="不合格数量" align="center" prop="unqualifiedquantity" />
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

      <!-- 添加或修改arrival_review对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="120px">
          <el-form-item label="采购到货单编号" prop="purchaseDeliveryNoteNumber">
            <el-input v-model="form.purchaseDeliveryNoteNumber" placeholder="请输入采购到货单编号" />
          </el-form-item>
          <el-form-item label="采购计划单编号" prop="purchasingPlanNumber">
            <el-input v-model="form.purchasingPlanNumber" placeholder="请输入采购计划单编号" />
          </el-form-item>
          <el-form-item label="制单人编号" prop="creatorID">
            <el-input v-model="form.creatorID" placeholder="请输入制单人同编号" />
          </el-form-item>
          <el-form-item label="物料编码" prop="materialCode">
            <el-input v-model="form.materialCode" placeholder="请输入物料编码" />
          </el-form-item>
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="到货数量" prop="receivedQuantity">
            <el-input v-model="form.receivedQuantity" placeholder="请输入到货数量" />
          </el-form-item>
          <el-form-item label="不合格数量" prop="unqualifiedquantity">
            <el-input v-model="form.unqualifiedquantity" placeholder="请输入不合格数量" />
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
    name: "arrival_review",
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
        arrival_reviewList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          purchaseDeliveryNoteNumber: null,
          purchasingPlanNumber: null,
          creatorID:null,
          materialCode :null,
          batchNumber:null,
          receivedQuantity:null,
          unqualifiedquantity:null,
          remark :null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            purchaseDeliveryNoteNumber: [
            { required: true, message: "采购到货单编号不能为空", trigger: "blur" }
          ],
          purchasingPlanNumber: [
            { required: true, message: "采购计划单编号不能为空", trigger: "blur" }
          ],
          creatorID: [
            { required: true, message: "制单人编号不能为空", trigger: "blur" }
          ],
          materialCode : [
            { required: true, message: "物料编号不能为空", trigger: "blur" }
          ],
          batchNumber: [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          receivedQuantity: [
            { required: true, message: "到货数量不能为空", trigger: "blur" }
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
        this.arrival_reviewList=[{
            purchaseDeliveryNoteNumber: "111",
          purchasingPlanNumber: "22",
          creatorID:"333",
          materialCode :"444",
          batchNumber:"555",
          receivedQuantity:"666",
          unqualifiedquantity:"777",
          remark :"8888"
          },{
            purchaseDeliveryNoteNumber: "111",
          purchasingPlanNumber: "22",
          creatorID:"333",
          materialCode :"444",
          batchNumber:"555",
          receivedQuantity:"666",
          unqualifiedquantity:"777",
          remark :"8888"
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
          purchaseDeliveryNoteNumber: null,
          purchasingPlanNumber: null,
          creatorID:null,
          materialCode :null,
          batchNumber:null,
          receivedQuantity:null,
          unqualifiedquantity:null,
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
        this.title = "采购到货审核管理";
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
