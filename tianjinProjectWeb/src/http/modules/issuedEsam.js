import axios from '../axios'

// 分页查询
export const findPage = (data) => {
  return axios({
    url: '/issuedEsam/findPage',
    method: 'post',
    data
  })
}
