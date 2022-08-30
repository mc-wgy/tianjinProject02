/*
 * 接口统一集成模块
 */
import * as login from './modules/login'
import * as user from './modules/user'
import * as role from './modules/role'
import * as menu from './modules/menu'
import * as log from './modules/log'
import * as loginlog from './modules/loginlog'
import * as issuedEsam from './modules/issuedEsam'


// 默认全部导出
export default {
  login,
  user,
  role,
  menu,
  log,
  loginlog,
  issuedEsam
}
