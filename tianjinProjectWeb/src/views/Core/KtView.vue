<template>
  <div>
    <!--    搜索栏-->
    <div class="toolbar" style="float:left;padding-top:10px;padding-left:15px;">
      <el-form :inline="true" :model="defaultFilters" :size="size">
        <el-form-item>
          <el-input v-model="defaultFilters.name" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-search" :label="$t('action.search')" :perms="this.permsView"
                     type="primary"
                     @click="findPage(defaultFilters.name)"/>
        </el-form-item>
        <el-form-item>
          <kt-button icon="fa fa-plus" :label="$t('action.add')" :perms="this.permsAdd" type="primary"
                     @click="handleAdd"/>
        </el-form-item>
        <el-form-item>
          <kt-button :icon="this.extraButton.icon" :label="this.extraButton.label" :perms="this.permsExtra"
                     :type="this.extraButton.type"
                     :show="this.extraButton.show" @click="this.extraButton.extraFunction()"/>
        </el-form-item>
      </el-form>
    </div>
    <div class="toolbar" style="float:right;padding-top:10px;padding-right:15px;">
      <!--      右侧工具栏-->
      <el-form :inline="true" :size="size">
        <el-form-item>
          <el-button-group>
            <el-tooltip content="刷新" placement="top">
              <el-button icon="fa fa-refresh" @click="findPage(null)"></el-button>
            </el-tooltip>
            <el-tooltip content="列显示" placement="top">
              <el-button icon="fa fa-filter" @click="displayFilterColumnsDialog"></el-button>
            </el-tooltip>
            <el-tooltip content="导出" placement="top">
              <el-button icon="fa fa-file-excel-o" @click="importIssuedExcelFile"></el-button>
            </el-tooltip>
          </el-button-group>
        </el-form-item>
      </el-form>
      <!--      表格显示列界面-->
      <table-column-filter-dialog ref="tableColumnFilterDialog" :columns="columns"
                                  @handleFilterColumns="handleFilterColumns">
      </table-column-filter-dialog>
    </div>
    <kt-table :permsEdit="permsEdit" :permsDelete="permsDelete" :showOperation="showOperation"
              :data="pageResult" :columns="filterColumns" :showDelete="showDelete" :showEdit="showEdit"
              @findPage="findPage" @handleEdit="handleEdit" @handleDelete="handleDelete">
    </kt-table>
    <!--    新增/编辑页面-->
    <el-dialog :title="operation?'新增':'编辑'" width="40%" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="dataForm" label-width="80px" :rules="dataFormRules" ref="dataForm" :size="size"
               label-position="right">
        <el-form-item v-for="(column,index) in dataFormColumns" :key="index" :label="column.label" :prop="column.prop"
                      :style="{display:column.display?'none':''}">
          <el-input v-model="dataForm[column.prop]" :disabled="column.disabled" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :size="size" @click.native="dialogVisible = false">{{ $t('action.cancel') }}</el-button>
        <el-button :size="size" type="primary" @click.native="submitForm" :loading="editLoading">
          {{ $t('action.submit') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import KtTable from "@/views/Core/KtTable"
import KtButton from "@/views/Core/KtButton"
import TableColumnFilterDialog from "@/views/Core/TableColumnFilterDialog"
import PopupTreeInput from "@/components/PopupTreeInput"

export default {
  name: "KtView",
  props: {
    permsView: String,//查询权限标识
    permsEdit: String,//修改权限标识
    permsDelete: String,//删除权限标识
    permsAdd: String,//新增权限标识
    permsExtra: String,//额外按钮权限标识
    extraButton: {
      type: Object,
      default: () => {
        return {
          icon: "fa fa-microchip",
          label: "",
          type: "primary",
          show: false,
          extraFunction: function () {
            console.log()
          }
        }
      }
    },//额外按钮参数
    defaultFilters: {//搜索名
      name: String,
      default: () => "",
    },
    defaultColumns: {//表字段列表
      type: Array,
      default: () => [],
    },
    showOperation: {  // 是否显示操作组件
      type: Boolean,
      default: true
    },
    showEdit: {//是否显示修改按钮
      type: Boolean,
      default: true
    },
    showDelete: {//是否显示删除按钮
      type: Boolean,
      default: true
    },
    showAdd: {//是否显示新增按钮
      type: Boolean,
      default: true
    },
    pageResult: Object,//表分页内容
    dataForm: Object,// 新增编辑界面数据
    dataFormColumns: Array,
    dataFormRules: {// 新增编辑界面检查规则
      type: Object,
      default: {}
    },
  },
  components: {
    KtButton,
    KtTable,
    TableColumnFilterDialog,
    PopupTreeInput
  },
  data() {
    return {
      size: 'small',
      pageRequest: {
        pageNum: 1,
        pageSize: 8,
      },
      columns: [],
      filterColumns: [],
      loading: false,  // 加载标识
      operation: false, // true:新增, false:编辑
      editLoading: false,//提交状态，true为提交中
      dialogVisible: false,// 新增编辑界面是否显示
    }
  },
  methods: {
    findPage: function (data) {
      this.$emit('findPage', data)
    },
    //显示过滤表格
    displayFilterColumnsDialog: function () {
      this.$refs.tableColumnFilterDialog.setDialogVisible(true)
    },
    //导入Excel表格
    importIssuedExcelFile: function () {
      console.log()
    },
    //保存过滤结果
    handleFilterColumns: function (data) {
      this.filterColumns = data.filterColumns
      this.$refs.tableColumnFilterDialog.setDialogVisible(false)
    },
    initColumns: function () {
      this.columns = this.defaultColumns
      console.log(this.defaultColumns)
      this.filterColumns = JSON.parse(JSON.stringify(this.columns));
    },
    handleEdit: function (params) {
      this.dialogVisible = true
      this.operation = false
      this.dataForm = Object.assign(this.dataForm, params.row)
      this.$emit("handleEdit")
      console.log(params)
    },
    handleDelete: function (data) {
    },
    handleAdd: function () {
      this.dialogVisible = true
      this.operation = true
      this.$emit("handleAdd")
    },
    submitForm: function () {
      this.$refs.dataForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true
            let callback = res => {
              this.editLoading = false
            }
            this.$emit('submitForm', {callback: callback})
          })
        }
      })
    },
  },
  mounted() {
    this.initColumns()
  }
}
</script>

<style scoped>

</style>
