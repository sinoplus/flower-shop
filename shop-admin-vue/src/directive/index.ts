import hasRole from './permission/hasRole'
import hasAccess from './permission/hasAccess'
import copyText from './common/copyText'

export default function directive(app: any) {
  app.directive('hasRole', hasRole)
  app.directive('hasAccess', hasAccess)
  app.directive('copyText', copyText)
}
