<template>
  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="生产入库单编号" prop="productionWarehousingCode" label-width="85">
              <el-input
                v-model="queryParams.productionWarehousingCode"
                placeholder="请输入生产入库单编号"
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
          v-hasPermi="['warehousing:warehousing:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['warehousing:warehousing:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['warehousing:warehousing:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['warehousing:warehousing:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="warehousingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="生产入库单编号" align="center" prop="productionWarehousingCode" />
      <el-table-column label="生产计划单编号" align="center" prop="productionPlanningCode" />
      <el-table-column label="生产批号" align="center" prop="productionBatchCode" />
      <el-table-column label="灭菌批号" align="center" prop="productionSterilizationCode" />
      <el-table-column label="入库库房编号" align="center" prop="productionWarehouseCode" />
      <el-table-column label="入库时间" align="center" prop="productionWarehousingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productionWarehousingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="库管员" align="center" prop="productionWarehouseKeeper" />
      <el-table-column label="制单日期" align="center" prop="productionListDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.productionListDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="制单人" align="center" prop="productListCreator" />
      <el-table-column label="备注信息" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['warehousing:warehousing:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['warehousing:warehousing:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="生产入库udi表" name="first">
       <udi ref ="mychild"></udi>
      </el-tab-pane>
      <el-tab-pane  label="生产入库明细表" name="second">
        <detail ref ="mychild"></detail>
      </el-tab-pane>
    </el-tabs>
    <!-- 添加或修改生产入库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item v-if="title!='修改生产入库'" label="生产入库单编号" prop="productionWarehousingCode">
          <el-input v-model="form.productionWarehousingCode" placeholder="请输入生产入库单编号" :disabled="form.id"/>
        </el-form-item>
        <el-form-item v-if="title!='修改生产入库'" label="生产计划单编号" prop="productionPlanningCode">
          <el-input v-model="form.productionPlanningCode" placeholder="请输入生产计划单编号" :disabled="form.id"/>
        </el-form-item>
        <el-form-item v-if="title!='修改生产入库'" label="生产批号" prop="productionBatchCode">
          <el-input v-model="form.productionBatchCode" placeholder="请输入生产批号" :disabled="form.id"/>
        </el-form-item>
        <el-form-item v-if="title!='修改生产入库'" label="灭菌批号" prop="productionSterilizationCode">
          <el-input v-model="form.productionSterilizationCode" placeholder="请输入灭菌批号" :disabled="form.id"/>
        </el-form-item>
        <el-form-item label="入库库房编号" prop="productionWarehouseCode">
          <el-input v-model="form.productionWarehouseCode" placeholder="请输入入库库房编号" />
        </el-form-item>
        <el-form-item v-if="title!='修改生产入库'" label="制单日期" prop="productionListDate">
          <el-date-picker clearable
                          v-model="queryParams.productionListDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择制单日期" :disabled="form.id">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="库管员" prop="productionWarehouseKeeper">
          <el-input v-model="form.productionWarehouseKeeper" placeholder="请输入库管员" />
        </el-form-item>
        <el-form-item v-if="title!='修改生产入库'" label="入库时间" prop="productionWarehousingDate">
          <el-date-picker clearable
                          v-model="queryParams.productionWarehousingDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择入库时间" :disabled="form.id">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-if="title!='修改生产入库'" label="制单人" prop="productListCreator">
          <el-input v-model="form.productListCreator" placeholder="请输入制单人" />
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listWarehousing,listWarehousingCode,getWarehousing, delWarehousing, addWarehousing, updateWarehousing } from "@/api/warehousing/warehousing";
import {listDetailAll} from "@/api/warehousing/detail";
import detail from "../detail/index";
import udi from "../udi/index";
//中转文件导入
export default {
  name: "Warehousing",
  components:{
    detail,
    udi
  },
  data() {
    return {
      msg:0,
      //tabs组件
      activeName: 'second',
      // 遮罩层
      loading: true,
      //选中的上产入库单编号数组
      warehousingCodeList: [],
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
      // 生产入库表格数据
      warehousingList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        productionWarehousingCode: null,
        productionPlanningCode: null,
        productionBatchCode: null,
        productionSterilizationCode: null,
        productionWarehouseCode: null,
        productionWarehousingDate: null,
        productionWarehouseKeeper: null,
        productionListDate: null,
        productListCreator: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        productionWarehousingCode: [
          { required: true, message: "生产入库单编号不能为空", trigger: "blur" }
        ],
        productionPlanningCode: [
          { required: true, message: "生产计划单编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getWarehousingCodeList();
  },
  methods: {

    implementSonMethod(val){
      // this.sonMethodData =
       this.$refs.mychild.sonMethod(val)
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    /** 查询生产入库列表 */
    getList() {
      this.loading = true;
      listWarehousing(this.queryParams).then(response => {
        this.warehousingList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    //获取所有生产入库单编号
    getWarehousingCodeList(){
      listWarehousingCode().then(
        response =>{
          this.warehousingCodeList = response.rows;
        }
      );
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        productionWarehousingCode: null,
        productionPlanningCode: null,
        productionBatchCode: null,
        productionSterilizationCode: null,
        productionWarehouseCode: null,
        productionWarehousingDate: null,
        productionWarehouseKeeper: null,
        productionListDate: null,
        productListCreator: null,
        remark: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
      this.implementSonMethod(this.queryParams.productionWarehousingCode);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.msg = ids;
      console.log('选择',this.msg)
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加生产入库";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWarehousing(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改生产入库";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWarehousing(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            console.log(this.form)
           this.form.productionListDate= this.queryParams.productionListDate
            this.form.productionWarehousingDate=this.queryParams.productionWarehousingDate
            addWarehousing(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除该数据项？').then(function() {
        return delWarehousing(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('warehousing/warehousing/export', {
        ...this.queryParams
      }, `warehousing_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
