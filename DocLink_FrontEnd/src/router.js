import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from './components/Home.vue'
import Login from './components/Login.vue'
import Register from './components/Register.vue'
import UserRegister from './components/Form/UserRegister.vue'
import DoctorRegister from './components/Form/DoctorRegister.vue'
import DashboardAdmin from '@/components/DashboardAdmin'
import Dashboard from '@/components/Dashboard'
import Profile from '@/components/Profile'
import Tables from '@/components/Tables'
import Maps from '@/components/Maps'
import BadGateway from '@/components/BadGateway'

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/register', component: Register},
    {path: '/user-register', component: UserRegister},
    {path: '/doctor-register', component: DoctorRegister},
    {
      path: '/dashboard',
      component: DashboardAdmin,
      props: { page: 1 },
      children: [
        { path: "", component: Dashboard },
        {
          path: '/profile',
          name: 'Profile',
          props: { page: 2 },
          component: Profile
        },
        {
          path: '/tables',
          name: 'Tables',
          props: { page: 3 },
          component: Tables
        },
        {
          path: '/maps',
          name: 'Maps',
          props: { page: 4 },
          component: Maps
        },
      ]
    },
    {
      path: '/404',
      name: 'BadGateway',
      props: { page: 5 },
      component: BadGateway
    },
    {
      path: '*',
      props: { page: 5 },
      redirect: '/404'
    }
]

Vue.use(VueRouter)

export default new VueRouter({ routes });

