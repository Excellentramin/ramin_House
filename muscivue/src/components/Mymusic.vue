<template>
 <div class="main">
      
        <div class="my"></div>
        
       
     
      <div class="table">
            <el-table
        :data="$store.state.tableData"
        height="400px"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
        >
       

        <el-table-column
          prop="songName"
          label="歌名"
          width="200">
        </el-table-column>
                <el-table-column
          prop="singer"
          label="歌手"
          width="150">
        </el-table-column>
        <el-table-column
          width="">
          <template slot-scope="scope">
            <i class="el-icon-video-play" @click="playMusic(scope.row)"></i>
          <i class="el-icon-video-pause" @click="puase()"></i>
          </template>
        </el-table-column>
        <el-table-column
          width="100"
          label="">
          <template slot-scope="scope"><el-button type="primary" icon="el-icon-delete" @click="delect(scope.row)"></el-button></template>
        </el-table-column>
      </el-table>
      </div>
       <div class="play">
           <audio  :src="$store.state.song.url" autoplay="" controls="" v-if="flag5" ref="Myaudio" id="audio"></audio>
       </div>
   
 </div>
</template>

<script>
export default {
    data(){
      return {
         flag5:false,   //判定播放器隐藏显示
      }
    },
    created(){
        let _this=this
        this.$axios({
                    url:"http://localhost:8090/musicWed/showMySongServlet",
                    method:"get",
                    params:{
                        userName:sessionStorage.getItem('username'),
                        songName:""
                    }
                }).then(function(resp){
        
                    _this.$store.commit('fresh',resp.data)
                })
    },
    methods: {
      //播放音乐
        playMusic(row){
          this.flag5=false
            this.$store.commit('musicplay',row)
            this.flag5 = true
        },
        //暂停按钮关联事件
        puase(){
            this.$refs.Myaudio.pause()
        },
        //移除音乐
      delect(row){
            let _this=this
             this.$axios({
                    url:"http://localhost:8090/musicWed/showMySongServlet",
                    method:"get",
                    params:{
                        userName:sessionStorage.getItem('username'),
                        songName:row.songName
                    }
                }).then(function(resp){
                  _this.$store.commit('fresh',resp.data)
                })
      }
    },

}
</script>

<style scoped>
.main{
  background: white;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
   height: 750px;
   width: 720px;
   position: relative;
   top:0px;
   left:400px;
}
.my{
position: absolute;
left: 33%;
  width: 660px;
  height: 220px;
  background:url('~@/assets/images/like.png') no-repeat;
  
}

.list{
      position: relative;
      top:5px;
      left: 15px;
      font-size: 20px;
}
  .table{
        height: 200px;
        width: 689px;
        position: absolute;
        top:300px;
        left: 12px;
    }
    i{
        font-size: 25px;
        cursor: pointer;
    }
     .play{
         position: absolute;
         top:750px;
    }
    #audio{
        width: 689px;
        height: 30px;
        border-radius: 32px;
        position:relative;
        left: 10px;
        top:-30px
        
    }
</style>