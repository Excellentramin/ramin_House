<template>
<div class="container">
        <div class="top">
            <div class="content">
                <div class="logo" > 
                </div>
                <div class="nav1">
                    <span>
                    <a >发现音乐</a>
                    </span>
                    <span>
                    <a @click="mymusic()">我的音乐</a>
                    </span>
                </div>
                <div class="search1">
                    <input class="search-txt" type="text" v-model="ser" placeholder="输入歌曲关键字" @keyup.enter="s">
                </div> 
                <div class="search2">
                </div> 
                <div class="login">                  
                    <el-button type="text" @click="gologin">登录</el-button>
                </div>
                    <div class="login1" v-show="$store.state.noshow">
                 <p>欢迎您:{{$store.state.username}}</p>
                    </div>
                    <div class="upload">           
                    <el-button type="primary" @click="$store.state.dialog=true">上传歌曲<i class="el-icon-upload el-icon--right"></i></el-button>
                </div>
            </div>
        </div>
        <div class="nav2"></div>
<Login></Login>
 <Addmusic></Addmusic>
    </div>

</template>
<script>

import Login from './Login.vue'
import Addmusic from './Addmusic.vue'
export default {
  name: 'Head',
  components: { Login,Addmusic},

  data() {
      return {
        ser:'',
        show:true,
        dialogFormVisible: false,
        form: {
          username: '',
          password: '',
        },
        formLabelWidth: '120px',
          
      };
    },
      created(){
        if(sessionStorage.getItem('username')){
            this.$store.state.noshow=true
            this.$store.state.username=sessionStorage.getItem('username')
        }
    },

     methods:{
      gologin(){
        this.$store.commit('editDialog',true)
 
      },   
        mymusic(){
                        if(sessionStorage.getItem('username')==null){
                  alert("请登录")
              }else{
            this.$router.push('/Mymusic')}
        },
  s(){//实现搜索歌名
        
           var _this=this
              if(this.ser==''){
                 alert("输入为空")
              }else{
        this.$axios({
                    url:"http://localhost:8090/musicWed/searchMusicServlet",
                    method:"get",
                    params:{
                        search:this.ser
                    }
                }).then(function(resp){
                    _this.$store.commit('fresh',resp.data)
                })
              }
              this.$router.push('/Search')
        }
     }, 
};

</script>

<style >
*{margin: 0;padding: 0;}
html{
    --font_color: #dad3d3;
}
body{
    height: 100vh;width: 100vw;
    font-size: 14px;
    font-family: Arial, Helvetica, sans-serif;
}
a:link,a:visited{
    text-decoration: none;
    color: var(--font_color);
    display: block;
}

.container{
    min-width: 1519px;
    top: 0px;
}
.top{
    height: 70px;
    box-sizing: border-box;
    background: #242424;
    border-bottom: 1px solid #000;
    overflow: hidden;
}

.container .top .content{
    width: 1100px;
    margin: 0 auto;
}

.container .top .content .logo{
    position:absolute;
    left: 210px;
    width: 120px;
    height: 70px;
    float: left;
    background: url('~@/assets/images/logo.png') no-repeat;
  background-size: 120px 70px;
}

.container .top .content .nav1{
    position: absolute;
    left: 300px;
    margin: 0 14px;
    width: 320px;height: 70px;
    /* float: left; */
    display: flex;
    justify-content: center;
}

.container .top .content .nav1 span{
    color: var(--font_color);
    padding: 0 14px;
    line-height: 70px;
}

.container .top .content .nav1 span:hover{
    background-color: #000;
}
.container .top .content .nav1 span:hover a{
    color: #fff;
}

.container .top .content .search1{
    position:absolute;
    left:600px;
    width: 200px;height: 32px;
    margin: 19px 0;
    background-color: #fff;
    border-radius: 32px;
    
}
.container .top .content .upload button{
    position:absolute;
    left:1100px;
    top: 10px;
    width: 120px;
    height: 50px;
    
}
.container .top .content .search1 .search-txt{
    line-height: 32px;
    padding-left: 30px;
    border: none;
    background: none;
    outline: none;
    font-size: 12px;
}

.container .top .content .search1 span{
    position: absolute;
    font-size: 16px;
    top: 7px;left: 8px;
}
.search1:before{
  content: '';
  position: absolute;
  width:20px;
  height: 20px;
  top:7px;
  left:7px;
  background: url('~@/assets/images/search.png') no-repeat;
  background-size:20px 20px;
}

.container .top .content .login{
   position:absolute;
   left: 875px;
    width: 100px;
    line-height: 69px;
    color: var(--font_color);
    font-size: 12px;
}

.container .top .content .login a:hover{
    color: #fff;
    text-decoration: underline;
}
.search2{
    position:absolute;
    left:1000px;
}
.login1{
position:absolute;
    left:950px;
    line-height: 70px;
    color: white;
}

</style>
