<template>
            <div class="headers">
              
            <h3 class="title">热门推荐</h3>
            <ul>
              <li>
                <!-- <a @click="musictype('全部')">全部</a> -->
                <a @click="musictype('全部')">全部</a>
              </li>              
              <li>
                <a  @click="musictype('华语')">华语</a>
              </li>
              <li>
                <a @click="musictype('流行')">流行</a>
              </li>
              <li>
                <a @click="musictype('摇滚')">摇滚</a>
              </li>
              <li>
                <a @click="musictype('民谣')">民谣</a>
              </li>
              <li>
                <a @click="musictype('电子')">电子</a>
              </li>
            </ul>
            <musiclist :tableData="tableData"></musiclist>
          </div>
          
</template>

<script>
import Musiclist from './Musiclist.vue'
export default {
  data(){
    return {
      tableData:[]
      }
  },
methods:{
musictype(a){
  
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
components:{Musiclist }
}
</script>

<style>

.headers
{ position: absolute;
  width: 50%;
  left:25%;
  /* height: 30px; */
  line-height: 30px;
  border-bottom: 2px solid rgba(193, 13, 12);
}
.headers .title{
  position: relative;
  padding-left:30px;
}
.headers .title:before
{
  position: absolute;
  top:6px;
  left:5px;
  content: '';
  width:20px;
  height: 20px;
  background: url('~@/assets/images/round.png') no-repeat;
  background-size: 20px 20px;
}
.headers h3{
  position:absolute;
left: 10%;
  float: left;
}
.headers ul{
  position:absolute;
left: 30%;
  float: left;
  margin-left: 30px;
}
.headers ul li{
  float: left;
  list-style: none;
}
.headers ul li a{
  padding: 0 15px;
  font-size: 16px;
  color: rgba(206, 153, 102);
}
.headers .right{
  float:right;
  font-size: 10px;
  color: rgba(206, 153, 102);
}
</style>