import { defineStore } from 'pinia'
import { getInfo, login, logout } from '@/api/login'
import { getToken, removeToken, setToken } from '@/utils/auth'
import defAva from '@/assets/images/profile.png'
import type { UserProps } from '@/store/modules/user/helper'

const useUserStore = defineStore(
  'user',
  {
    state: () => ({
      token: getToken(),
      name: '',
      avatar: '',
      roles: <any>[],
      permissions: [],
    }),
    actions: {
      // 登录
      login(userInfo: UserProps) {
        const username = userInfo.username.trim()
        const password = userInfo.password
        const code = userInfo.code
        const uuid = userInfo.uuid
        return new Promise((resolve, reject) => {
          login(username, password, code, uuid).then((res: any) => {
            setToken(res.token)
            this.token = res.token
            resolve(res)
          }).catch((error) => {
            reject(error)
          })
        })
      },
      // 获取用户信息
      getInfo() {
        return new Promise((resolve, reject) => {
          getInfo().then((res: any) => {
            const user = res.user
            const avatar = (user.avatar === '' || user.avatar == null) ? defAva : import.meta.env.VITE_APP_BASE_API + user.avatar

            if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
              this.roles = res.roles
              this.permissions = res.permissions
            }
            else {
              this.roles = ['ROLE_DEFAULT']
            }
            this.name = user.userName
            this.avatar = avatar
            resolve(res)
          }).catch((error) => {
            reject(error)
          })
        })
      },
      // 退出系统
      logOut() {
        return new Promise((resolve, reject) => {
          logout().then((res) => {
            this.token = ''
            this.roles = []
            this.permissions = []
            removeToken()
            resolve(res)
          }).catch((error) => {
            reject(error)
          })
        })
      },
    },
  })

export default useUserStore
