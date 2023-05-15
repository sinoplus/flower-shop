import { createApp } from 'vue'

import ElementPlus from 'element-plus'

import App from '@/App.vue'

import { setupStore } from '@/store'

import '@/assets/styles/index.scss' // global css

import 'virtual:svg-icons-register' // svg图标

import '@/permission'

async function bootstrap() {
  const app = createApp(App as any)
  setupStore(app)

  // 使用element-plus 并且设置全局的大小
  app.use(ElementPlus)

  app.mount('#app')
}

bootstrap()
