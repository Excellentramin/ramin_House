<template>
  <div>
      <!-- 登录对话框 -->
      <el-dialog :visible.sync="$store.state.dialogFormVisible" title="登录" width="400px" center>
     <el-form :model="form">
        <el-form-item label="账号" label-width="50px">
        <el-input v-model="form.name" autocomplete="off" :style="a"></el-input>
        </el-form-item>
        <el-form-item label="密码"  label-width="50px">
            <el-input v-model="form.password" autocomplete="off" :style="a" show-password></el-input>
        </el-form-item>
  </el-form>
  <div slot="footer" >
    <el-button @click="reg">注册</el-button>
    <el-button type="primary" @click="login">登录</el-button>
    </div>
</el-dialog>

       <!-- 注册对话框 -->
      
           <el-dialog title="注册" :visible.sync="$store.state.dialogFormVisible1" width="400px" center>
         <el-form :model="form">
            <el-form-item label="账号" label-width="70px">
            <el-input v-model="form.name" autocomplete="off" :style="a"></el-input>
            </el-form-item>
            <el-form-item label="密码" label-width="70px">
                <el-input v-model="form.password" autocomplete="off" :style="a" show-password></el-input>
            </el-form-item>
            <el-form-item label="确认密码" label-width="70px" >
                <el-input v-model="form.password1" autocomplete="off" :style="a" show-password ></el-input>
            </el-form-item>
      </el-form>
      <div slot="footer" >
        
        <el-button type="primary" @click="login1">登录</el-button>
        <el-button @click="reg1">注册</el-button>
      </div>
    </el-dialog>
      </div>
</template>

<script>

export default {
    data(){
        return{
            form: {
          name: '',
         password:'',
         password1:'',
        },
       a:'width:275px',
       ok:false
      }
        },
        computed:{
            
            dialogFormVisible:function(){
                return  this.$store.state.dialogFormVisible
            },

            dialogFormVisible1:function(){
                return  this.$store.state.dialogFormVisible1
            }
        },
        methods:{
            //登录页面的登录按钮点击事件，点击后向登录Servlet发送请求
            login:function(){
              let _this=this
                this.$axios({
                    url:"http://localhost:8090/musicWed/loginServlet",
                    method:"get",
                    params:{
                        username:this.form.name,
                        password:this.form.password
                    }
                }).then(function(resp){
                    if(resp.data==_this.form.name)
                    {alert(resp.data)
                    _this.$store.commit('baocun',resp.data)
                    _this.$store.commit('editNoshow',true)
                    }else{
                        alert("登陆失败")
                    }
                    
                    
                })
                
                this.$store.state.dialogFormVisible=false
            },
            //登录页面的注册按钮点击事件，点击后登录页面隐藏，注册页面显示
            reg:function(){
                
                this.$store.state.dialogFormVisible1=true
                this.$store.state.dialogFormVisible=false
            },
            //注册页面的登录按钮点击事件，点击后注册页面隐藏，登录页面显示
            login1:function(){
                this.$store.state.dialogFormVisible=true
                this.$store.state.dialogFormVisible1=false
            },
            //注册页面的注册按钮点击事件，点击后向regist_Servlet发送请求
            reg1:function(){
              this.$axios({
                    url:"http://localhost:8090/musicWed/registerServlet",
                    method:"get",
                    params:{
                        username:this.form.name,
                        password:this.form.password,
                        password1:this.form.password1
                    }
                }).then(function(resp){
                    alert(resp.data)
                })
                this.$store.state.dialogFormVisible1=false
            }
        }
    }


</script>

<style>


</style>