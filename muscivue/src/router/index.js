import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../components/Login.vue'
import Head from '../components/Head.vue'
import Carousel from '../components/Carousel.vue'
import Head2 from '../components/Head2.vue'
import Musiclist from '../components/Musiclist.vue'
import Addmusic from '../components/Addmusic.vue'
import Mymusic from '../components/Mymusic.vue'
import Search from '../components/Search.vue'


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
  return originalPush.call(this, location).catch(err => err)
}
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    
    
  },
  {
    path:'/Head/',
    name: 'Head',
    component: Head
  },
  {
    path:'/Login',
    name: 'Login',
    component:Login
  
  },
  {
    path:'/Carousel',
    name: 'Carousel',
    component:Carousel
  },
  {
    path:'/Head2',
    name: 'Head2',
    component:Head2,
    children:[
      {path:'/',component:Musiclist}
    ]
    
  }
,
  {
    path:'/Addmusic',
    name: 'Addmusic',
    component:Addmusic
  }  ,
  {
    path:'/Mymusic',
    name: 'Mymusic',
    component:Mymusic
  }
  ,
  {
    path:'/Search',
    name: 'Search',
    component:Search
  }
]

const router = new VueRouter({
  routes
})

export default router
