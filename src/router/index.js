import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import InitView from "@/views/InitView.vue";
import SettingView from "@/views/SettingView.vue"
import WorkoutRoutine from "@/views/WorkoutRoutine.vue";
import WorkoutSelection from "@/views/WorkoutSelection.vue"
import WorkoutInfoView from "@/views/WorkoutInfoView.vue";
import QuickRecordView from "@/views/QuickRecordView.vue";
import workoutDetailsView from "@/views/WorkoutDetailsView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/hello',
      name: 'hello',
      component: HomeView
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path:'/init',
      name: 'init',
      component: InitView
    },
    {
      path:'/routine',
      name:'WorkoutRoutine',
      component: WorkoutRoutine
    },
    {
      path:'/setting',
      name:'SettingView',
      component: SettingView
    },
    {
      path:'/workoutSelection/:name',
      name:'WorkoutSelection',
      component: WorkoutSelection,
      props: true
    },
    {
      path:'/workoutInfo',
      name:'WorkoutInfoView',
      component: WorkoutInfoView
    },
    {
      path:'/quickRecord',
      name:'QuickRecordView',
      component: QuickRecordView
    },
    {
      path:'/workoutDetail/:name/:id/:SetData',
      name:'workoutDetailsView',
      component: workoutDetailsView,
      props:true
    }
  ]
})

export default router
