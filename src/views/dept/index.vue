<script setup>
import { onMounted, reactive, ref, nextTick } from 'vue';
import { queryAllDept, addDept, queryDept, updateDept, deleteDept } from '../../api/dept';
import { ElMessage, ElMessageBox } from 'element-plus'

const tableData = ref([])
const dialogFormVisible = ref(false)
const dept = ref({ name: '' });
const formRef = ref(null);
const formRules = ref({
  name: [
    { required: true, message: '请输入部门名称', trigger: 'blur' },
    { min: 2, max: 10, message: '长度在 2 到 10 个字符', trigger: 'blur' }
  ]
})
const formTitle = ref('')

const searchAll = async () => {
  const res = await queryAllDept();
  tableData.value = res.data;
}

onMounted(() => {
  searchAll();
});


const save = async () => {
  // 表单校验
  const formEl = formRef.value;
  formEl.validate(async (valid, field) => {
    if (valid === 0) {
      ElMessage.error('error msg' + JSON.stringify(field));
    } else if (valid) {
      // 表单校验通过
      if (dept.value.id) {
        // 编辑部门
        const data = {
          id: dept.value.id,
          name: dept.value.name
        };
        const result = await updateDept(data);
        if (result.code === 1) {
          ElMessage({
            message: ' success message.',
            type: 'success',
          })
          dialogFormVisible.value = false;
          searchAll();
        } else {
          // 失败提示
          ElMessage.error(result.message + "编辑失败");
        }
      } else if (dept.value) {
        // 新增部门
        const result = await addDept(dept.value);
        if (result.code === 1) {
          ElMessage({
            message: ' success message.',
            type: 'success',
          })
          dialogFormVisible.value = false;
          searchAll();
        } else {
          // 添加失败提示
          ElMessage.error(result.message + "添加失败");
        }
      }
    }
  });
}

const showAddDialog = () => {
  dialogFormVisible.value = true;
  formTitle.value = '新增部门'
  dept.value = { name: '' }
  formRef.value.resetFields();
}

const showEditDialog = async (id) => {
  dialogFormVisible.value = true;
  formTitle.value = '编辑部门'
  const result = await queryDept(id)
  dept.value = result.data;
  formRef.value.resetFields();
}

const deleteHandle = (id) => {
  ElMessageBox.confirm(
    '确定删除该部门吗？',
    'Warning',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  ).then(async () => {
    const result = await deleteDept(id);
    if (result.code) {
      ElMessage({
        type: 'success',
        message: '删除成功',
      })
      searchAll();
    }
  })
}

</script>

<template>
  <h2>部门管理</h2>
  <div class="container">
    <el-button type="primary" @click="showAddDialog"
      style="display:block; width:max-content; margin-left:auto; margin-top:20px;"> +
      新增部门</el-button>
  </div>

  <div class="container">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="index" label="序号" width="100" align="center" />
      <el-table-column prop="name" label="部门名称" width="180" align="center" />
      <el-table-column prop="updateTime" label="最后修改时间" width="280" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <!-- {{ scope.row }} -->
          <el-button size="small" type="primary" style="margin-right:8px"
            @click="showEditDialog(scope.row.id)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- // Dialog -->
  <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="dept" :rules="formRules" ref="formRef">
      <el-form-item label="部门名称" label-width="80px" prop="name">
        <el-input v-model="dept.name" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="save"> 确定 </el-button>
        <el-button @click="dialogFormVisible = false">取消</el-button>
      </div>
    </template>
  </el-dialog>

</template>

<style scoped>
.container {
  margin-top: 10px;
}

</style>
