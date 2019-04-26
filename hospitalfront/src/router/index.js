import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import ArticleList from '@/components/ArticleList'
import CateMana from '@/components/CateMana'
import DataCharts from '@/components/DataCharts'
import PostArticle from '@/components/PostArticle'
import UserMana from '@/components/UserMana'
import BlogDetail from '@/components/BlogDetail'
import patientAdd from "../components/patientAdd";
import prepaySearch from "../components/prepaySearch";
import leavePatientRegister from "../components/leavePatientRegister.vue"
import leavePatientRecall from "../components/leavePatientRecall.vue"
import patientSearch from "../components/patientSearch";
import patientShowAll from "../components/patientShowAll";
import prepayCollect from "../components/prepayCollect"
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    }, {
      path: '/home',
      name: '',
      component: Home,
      hidden: true
    }, {
      path: '/home',
      component: Home,
      name: '文章管理',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/articleList',
          name: '文章列表',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/postArticle',
          name: '发表文章',
          component: PostArticle,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/blogDetail',
          name: '博客详情',
          component: BlogDetail,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑博客',
          component: PostArticle,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '用户管理',
      children: [
        {
          path: '/user',
          iconCls: 'fa fa-user-o',
          name: '用户管理',
          component: UserMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '栏目管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-reorder',
          name: '栏目管理',
          component: CateMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '数据统计',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/charts',
          iconCls: 'fa fa-bar-chart',
          name: '数据统计',
          component: DataCharts
        }
      ]
    },
    {
      path: '/home',
      component: Home,
      name: '住院登记管理',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/patientAdd',
          iconCls: 'fa fa-bar-chart',
          name: '住院登记',
          component: patientAdd
        },
        {
          //path: '/patientSearch',
          path:'/patientShowAll',
          iconCls: 'fa fa-bar-chart',
          name: '患者信息查询',
          component: patientShowAll,
        }
      ]
    },
    {
      path: '/home',
      component: Home,
      name: '预交金管理',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/prepaySearch',
          iconCls: 'fa fa-reorder',
          name: '预交查询',
          component: prepaySearch
        },
        {
          path: '/prepayCollect',
          iconCls: 'fa fa-bar-chart',
          name: '预交金收取',
          component: prepayCollect
        }
      ]
    },
    {
      path: '/home',
      component: Home,
      name: '出院管理',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/leavePatientRegister',
          iconCls: 'fa fa-bar-chart',
          name: '出院登记',
          component: leavePatientRegister
        },
        {
          path: '/leavePatientRecall',
          iconCls: 'fa fa-bar-chart',
          name: '出院召回',
          component: leavePatientRecall
        }
      ]
    },
  ]
})
