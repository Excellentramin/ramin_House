<template>
  <div class="home">
 <Head />
 <Carousel />
 <Head2/>
 
  <Login />
  </div>
</template>

<script>
// @ is an alias to /src
import Head from '@/components/Head.vue'
import Carousel from '@/components/Carousel.vue'
import Head2 from '@/components/Head2.vue'
import Login from '../components/Login.vue'
export default {
  name: 'Home',
  components: {
   Head,
  Carousel,
  Head2,
  Login
  
  },
  data(){
    return {
      tableData:[]
    }
  },
  methods:{
    toPath(a){
        let _this=this
        this.$axios({
                    url:"http://localhost:8090/musicWed/showSongServlet",
                    method:"get",
                    params:{
                        type:a
                    }
                }).then(function(resp){
        
                    _this.tableData=resp.data
                })
      this.$router.push({path:'/',query:{data:'tableData'}})
    }
  },
  created(){
        let _this=this
        this.$axios({
                    url:"http://localhost:8090/musicWed/showSongServlet",
                    method:"get",
                    params:{
                        type:'全部'
                    }
                }).then(function(resp){
        
                    _this.tableData=resp.data
                })
    },
}
</script>
