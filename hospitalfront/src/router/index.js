import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import patientAdd from "../components/patientAdd";
import prepaySearch from "../components/prepaySearch";
import leavePatientRegister from "../components/leavePatientRegister.vue"
import leavePatientRecall from "../components/leavePatientRecall.vue"
import patientSearch from "../components/patientSearch";
import patientShowAll from "../components/patientShowAll";
import prepayCollect from "../components/prepayCollect"
Vue.use(Router)

const router= new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Home
    },{
      path: '/login',
      name: '登录',
      hidden: true,
      component: Login
    }
    , {
      path: '/home',
      name: '',
      component: Home,
      meta: {
        requiresAuth: true
      },
      hidden: true
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
           meta: {
             requiresAuth: true
           },
          component: leavePatientRegister
        },
        {
          path: '/leavePatientRecall',
          iconCls: 'fa fa-bar-chart',
          name: '出院召回',
          component: leavePatientRecall
        }
      ]
    }
  ]
});
router.beforeEach((to, from, next) => {

  //获取store里面的token

//  let token = store.state.token;

  //判断要去的路由有没有requiresAuth

  if (to.meta.requiresAuth) {
    if (false) {
      next();
    } else {
      next({
        path: '/login',
        query: { redirect: to.fullPath } // 将刚刚要去的路由path作为参数，方便登录成功后直接跳转到该路由
      });
    }
  } else {
    next();
  }
});

export default router;
