<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="80px">
        <el-form-item label="编号" prop="number">
          <el-input
            v-model="queryParams.number"
            placeholder="请输入编号"
            clearable   
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="全称" prop="fullName">
          <el-input
            v-model="queryParams.fullName"
            placeholder="请输入全称"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="客户类型" prop="customerType">
          <el-input
            v-model="queryParams.customerType"
            placeholder="请输入客户类型"
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
  
      <el-table v-loading="loading" :data="warehouseList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column fixed width="150" label="编号" align="center" prop="number" />
        <el-table-column  width="150" label="全称" align="center" prop="fullName" />
        <el-table-column  width="150" label="简称" align="center" prop="referredToAs" />
        <el-table-column  width="150" label="客户类型" align="center" prop="customerType" />
        <el-table-column  width="150" label="地址" align="center" prop="address" />
        <el-table-column  width="150" label="医疗器械经营许可证" align="center" prop="medicalDeviceBusinessLicense" />
        <el-table-column  width="150" label="经营许可证有效期" align="center" prop="validityPeriodOfBusinessLicense" />
        <el-table-column  width="150" label="营业执照" align="center" prop="businessLicense" />
        <el-table-column  width="150" label="营业执照有效期" align="center" prop="validityPeriodOfBusinessLicense" />
        <el-table-column  width="150" label="统一机构编码" align="center" prop="unifiedInstitutionCode" />
        <el-table-column  width="150" label="经营范围" align="center" prop="businessScope" />
        <el-table-column  width="150" label="备注" align="center" prop="remarks" />
        <el-table-column fixed = "right" width="150" label="操作" align="center" class-name="small-padding fixed-width">
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
        <el-form ref="form" :model="form" :rules="rules" label-width="150px">
          <el-form-item label="编号" prop="number">
            <el-input v-model="form.number" placeholder="请输入编码" />
          </el-form-item>
          <el-form-item label="全程" prop="fullName">
            <el-input v-model="form.fullName" placeholder="请输入全称" />
          </el-form-item>
          <el-form-item label="简称" prop="referredToAs">
            <el-input v-model="form.referredToAs" placeholder="请输入简称" />
          </el-form-item>
          <el-form-item label="客户类型" prop="customerType">
            <el-input v-model="form.customerType" placeholder="请输入客户类型" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="form.address" placeholder="请输入地址" />
          </el-form-item>
          <el-form-item label="医疗器械经营许可证" prop="medicalDeviceBusinessLicense">
            <el-input v-model="form.medicalDeviceBusinessLicense" placeholder="请上传图片" />
          </el-form-item>
          <el-form-item label="经营许可证有效期" prop="validityPeriodOfBusinessLicense">
            <el-input v-model="form.validityPeriodOfBusinessLicense" placeholder="请输入证件有效期限" />
          </el-form-item>
          <el-form-item label="营业执照" prop="businessLicense">
            <el-input v-model="form.businessLicense" placeholder="请上传图片" />
          </el-form-item>
          <el-form-item label="营业执照有效期" prop="validityPeriodOfBusinessLicense">
            <el-input v-model="form.validityPeriodOfBusinessLicense" placeholder="请输入证件有效期限" />
          </el-form-item>
          <el-form-item label="统一机构编码" prop="unifiedInstitutionCode">
            <el-input v-model="form.unifiedInstitutionCode" placeholder="请输入编码" />
          </el-form-item>
          <el-form-item label="经营范围" prop="businessScope">
            <el-input v-model="form.businessScope" placeholder="请输入经营范围" />
          </el-form-item>
          <el-form-item label="备注" prop="remarks">
            <el-input v-model="form.remarks" placeholder="请输入备注" />
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
    name: "sales_customer",
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
        // procurement_enterprise表格数据
        sales_customerList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          number :null,
          fullName:null,
          referredToAs:null,
          customerType:null,
          address:null,
          medicalDeviceBusinessLicense:null,
          validityPeriodOfBusinessLicense :null,
          businessLicense:null,
          validityPeriodOfBusinessLicense:null,
          unifiedInstitutionCode:null,
          businessScope:null,
          remarks:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          number: [
            { required: true, message: "编号不能为空", trigger: "blur" }
          ],
          fullName: [
            { required: true, message: "全称不能为空", trigger: "blur" }
          ],
          customerType: [
            { required: true, message: "客户类型不能为空", trigger: "blur" }
          ],
          address: [
            { required: true, message: "地址不能为空", trigger: "blur" }
          ],
          medicalDeviceBusinessLicense: [
            { required: true, message: "经营许可不能为空", trigger: "blur" }
          ],
          validityPeriodOfBusinessLicense: [
            { required: true, message: "经营许可证有效期不能为空", trigger: "blur" }
          ],
          businessLicense: [
            { required: true, message: "营业执照不能为空", trigger: "blur" }
          ],
          validityPeriodOfBusinessLicense: [
            { required: true, message: "营业执照有效期不能为空", trigger: "blur" }
          ],
          unifiedInstitutionCode: [
            { required: true, message: "同一机构编码不能为空", trigger: "blur" }
          ],
          businessScope: [
            { required: true, message: "经营范围不能为空", trigger: "blur" }
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
        this.warehouseList=[{ 
            number :"001",
          fullName:"010101",
          referredToAs:"001",
          customerType:"1",
          address:"石家庄",
          medicalDeviceBusinessLicense:"null",
          validityPeriodOfBusinessLicense :"2025/1/11",
          businessLicense:"null",
          validityPeriodOfBusinessLicense:"2026/1/15",
          unifiedInstitutionCode:"11111",
          businessScope:"医疗器械",
          remarks:"hhhhh"
          },{
            number :"001",
          fullName:"010101",
          referredToAs:"001",
          customerType:"1",
          address:"石家庄",
          medicalDeviceBusinessLicense:"null",
          validityPeriodOfBusinessLicense :"2025/1/11",
          businessLicense:"null",
          validityPeriodOfBusinessLicense:"2026/1/15",
          unifiedInstitutionCode:"11111",
          businessScope:"医疗器械",
          remarks:"hhhhh"
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
          procurementCompanyNumber :null,
          purchasingPersonNumber:null,
          procurementContractNumber:null,
          expectedDeliveryDate:null,
          statusFlag:null,
          procurementRemark:null,
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
        this.title = "采购企业管理";
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
  