<template>
  <div class="page-container">
    <kt-view
      @findPage="findPage"
      :defaultFilters="this.filters"
      permsView="issue::issuedProduction::view"
      :defaultColumns="this.columns"
      :pageResult="pageResult"
      permsEdit="issue::issuedProduction::edit"
      permsDelete="issue::issuedProduction::delete"
      @handleDelete="handleDelete"
      :showEdit="true"
      :showDelete="false"
      :showOperation="true"
      :dataForm="dataForm"
      @submitForm="submitForm"
      :dataFormColumns="this.dataFormColumns"
      :dataFormRules="dataFormRules"
    />
  </div>
</template>

<script>
import KtView from '@/views/Core/KtView'

export default {
  name: "IssuedProduction",
  components: {
    KtView
  },
  data() {
    return {
      test: "1",
      filters: {
        name: ''
      },
      pageResult: {},
      dataForm: {
        id: 0,
        infoId: '',
        waitId: '',
        cardNumber: '',
        cardNo: '',
        serialNo: '',
        obuMac: '',
        issuedStatus: '',
        issuedDate: '',
        userId: '',
        esamUniqueNo: '',
        keyCode: '',
        icKeyCode: '',
      },
      columns: [
        //{prop: "id", label: "ID", minWidth: 50},
        //{prop: "infoId", label: "信息ID", minWidth: 150},
        //{prop: "waitId", label: "审核ID", minWidth: 120},
        {prop: "cardNumber", label: "发行编号", minWidth: 60},
        {prop: "cardNo", label: "IC卡号", minWidth: 90},
        {prop: "serialNo", label: "OBU序列号", minWidth: 90},
        {prop: "obuMac", label: "OBU物理号", minWidth: 70},
        {prop: "issuedStatus", label: "发行状态", minWidth: 60},
        {prop: "issuedDate", label: "发行日期", minWidth: 70},
        {prop: "userId", label: "发行人", minWidth: 50},
        {prop: "esamUniqueNo", label: "IC卡序列号", minWidth: 70},
        {prop: "keyCode", label: "obu密钥进度状态", minWidth: 90},
        {prop: "icKeyCode", label: "ic密钥进度状态", minWidth: 80},
        // {prop:"createBy", label:"创建人", minWidth:120},
        // {prop:"createTime", label:"创建时间", minWidth:120, formatter:this.dateFormat}
        // {prop:"lastUpdateBy", label:"更新人", minWidth:100},
        // {prop:"lastUpdateTime", label:"更新时间", minWidth:120, formatter:this.dateFormat}
      ],
      dataFormRules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ]
      },
      dataFormColumns:[
        {prop: "id", label: "ID",disabled:true,display:true},
        {prop: "infoId", label: "信息ID"},
        {prop: "waitId", label: "审核ID"},
        {prop: "cardNumber", label: "发行编号"},
        {prop: "cardNo", label: "IC卡号"},
        {prop: "serialNo", label: "OBU序列号"},
        {prop: "obuMac", label: "OBU物理号"},
        {prop: "issuedStatus", label: "发行状态"},
        {prop: "issuedDate", label: "发行日期"},
        {prop: "userId", label: "发行人"},
        {prop: "esamUniqueNo", label: "IC卡序列号"},
        {prop: "keyCode", label: "obu密钥进度状态"},
        {prop: "icKeyCode", label: "ic密钥进度状态"},
      ],
    }
  },
  methods: {
    findPage: function (data) {
      console.log(data)
      if (data !== null) {
        this.pageRequest = data.pageRequest
      }
      this.pageRequest.params = {username: this.filters.name}
      this.$api.issuedEsam.findPage(this.pageRequest).then((res) => {
        this.pageResult = res.data
        console.log()
      }).then(data != null ? data.callback : '')
    },
    handleEdit: function (params) {
      console.log()
    },
    handleDelete: function (data) {
      console.log()
    },
    submitForm: function (data) {
      console.log(this.dataForm)
      console.log(this.dataFormColumns)
      data.callback()
    },
  }
}
</script>

<style scoped>

</style>
