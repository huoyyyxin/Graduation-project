<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="检验单编号" prop="inspectionFormNumber">
          <el-input
            v-model="queryParams.inspectionFormNumber"
            placeholder="请输入检验单编号"
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
  
      <el-table v-loading="loading" :data="production_inspectionList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width = "150" label="生产批号" align="center" prop="batchNumber" />
        <el-table-column width = "150" label="生产主管" align="center" prop="productionSupervisor" />
        <el-table-column width = "150" label="检验单编号" align="center" prop="inspectionFormNumber" />
        <el-table-column width = "150" label="生产产品" align="center" prop="yieldProduction" />
        <el-table-column width = "150" label="检验时间" align="center" prop="inspectionTime" />
        <el-table-column width = "150" label="检验结果" align="center" prop="inspectionResults" />
        <el-table-column width = "150" label="备注" align="center" prop="keyRemark" />
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
          <el-form-item label="生产批号" prop="batchNumber">
            <el-input v-model="form.batchNumber" placeholder="请输入生产批号" />
          </el-form-item>
          <el-form-item label="生产主管" prop="productionSupervisor">
            <el-input v-model="form.productionSupervisor" placeholder="请输入生产主管" />
          </el-form-item>
          <el-form-item label="检验单编号" prop="inspectionFormNumber">
            <el-input v-model="form.inspectionFormNumber" placeholder="请输入检验单编号" />
          </el-form-item>
          <el-form-item label="生产产品" prop="yieldProduction">
            <el-input v-model="form.yieldProduction" placeholder="请输入生产产品" />
          </el-form-item>
          <el-form-item label="检验时间" prop="inspectionTime">
            <el-input v-model="form.inspectionTime" placeholder="请输入检验时间" />
          </el-form-item>
          <el-form-item label="检验结果" prop="inspectionResults">
            <el-input v-model="form.inspectionResults" placeholder="请输入检验结果" />
          </el-form-item>
          <el-form-item label="备注" prop="keyRemark">
            <el-input v-model="form.keyRemark" placeholder="请输入备注" />
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
    name: "production_inspection",
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
        // production_inspection表格数据
        production_inspectionList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          batchNumber: null,
          productionSupervisor: null,
          inspectionFormNumber:null,
          yieldProduction: null,
          inspectionTime:null,
          inspectionResults:null,
          keyRemark:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            batchNumber: [
            { required: true, message: "生产批号不能为空", trigger: "blur" }
          ],
          inspectionFormNumber: [
            { required: true, message: "检验单编号不能为空", trigger: "blur" }
          ],
          productionSupervisor: [
            { required: true, message: "生产主管不能为空", trigger: "blur" }
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
        this.production_inspectionList=[{ 
            batchNumber: "1111",
          productionSupervisor: "2222",
          inspectionFormNumber:"333",
          yieldProduction: "4444",
          inspectionTime:"5555",
          inspectionResults:"6666",
          keyRemark:"77777"
          },{
            batchNumber: "1",
          productionSupervisor: "2",
          inspectionFormNumber:"3",
          yieldProduction: "4",
          inspectionTime:"5",
          inspectionResults:"6",
          keyRemark:"7"
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
          materialRequisitionPersonnel: null,
          remainingRawMaterials:null,
          inventoryQuantity: null,
          quantity:null,
          productionSupervisor:null,
          returnMark:null,
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
        this.title = "产品检验管理";
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
  