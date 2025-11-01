<template>
  <el-form :model="form" label-width="auto" style="max-width: 600px">
    <el-form-item label="Activity name">
      <el-input v-model="form.name" />
    </el-form-item>
    <el-form-item label="day">
      <el-input v-model="form.day" />
    </el-form-item>
    <el-form-item label="reason">
      <el-input v-model="form.reason" />
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "CeshiForm",
  props: {
    // 接收父组件传递的流程信息
    processInfo: {
      type: Object,
      default: () => ({
        processId: null,
        processName: '',
        deployId: null,
        procDefId: null
      })
    }
  },
  data() {
    return {
      form: {
        name: '',
        day: '',
        reason: ''
      }
    }
  },
  methods: {
    // 获取表单数据，供父组件调用
    getFormData() {
      return {
        name: this.form.name,
        day: this.form.day,
        reason: this.form.reason
      }
    },
    // 重置表单，供父组件调用
    resetForm() {
      this.form = {
        name: '',
        day: '',
        reason: ''
      }
    },
    // 设置表单数据，用于回显
    setFormData(data) {
      if (data) {
        this.form = {
          name: data.name || '',
          day: data.day || '',
          reason: data.reason || ''
        }
      }
    }
  }
}
</script>
