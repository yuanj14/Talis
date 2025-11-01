import request from '@/utils/request'

export const queryAllDept = () => {
  return request.get('/depts')
}


// @Param 
export const addDept = (dept) => {
  return request.post('/depts', dept)
}

// 路径参数
export const queryDept = (id) => {
  return request.get(`/depts/${id}`)
}

export const updateDept = (dept) => {
  return request.put('/depts', dept)
}

// 简单参数
export const deleteDept = (id) => {
  return request.delete(`/depts?id=${id}`)
}
