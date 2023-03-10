<template>  
  <div>
  <el-main>
    
       <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      label="歌名"
      prop="songName" width="200">
      
    </el-table-column>
    <el-table-column
      label="歌手"
      prop="singer" width="200">
    </el-table-column>
        <el-table-column
      label="上传时间"
      prop="songTime" width="200">
    </el-table-column>
    <el-table-column
      width="">
      <template slot-scope="scope">
        <i class="el-icon-video-play" @click="playMusic(scope.row)"></i>
      <i class="el-icon-video-pause" @click="puase()"></i>
      <i class="el-icon-star-off" @click="collection(scope.row)"></i>
      <i class="el-icon-star-on" v-show="false"></i>
      </template>
    </el-table-column>

  </el-table>
       <div >
         <audio  :src="$store.state.song.url" autoplay="" controls="" v-if="flag" ref="Myaudio" id="audio"></audio>
     </div>
  </el-main>
  </div>

</template>

<script>
export default {
    props:['tableData'],
    data(){
        return{
            flag:false
        }
    },
    
    methods: {
        playMusic(row){
            
            this.$store.commit('musicplay',row)
            this.flag = true
        },
        puase(){
            this.$refs.Myaudio.pause()
        },
        musictype(a){
            let _this=this
        this.$axios({
                    url:"http://localhost:8090/musicWed/showMySongServlet",
                    method:"get",
                    params:{
                        type:a
                    }
                }).then(function(resp){
        
                    _this.tableData=resp.data
                })
        },
        collection(row){
              if(sessionStorage.getItem('username')==null){
                  alert("请登录")
              }else{
                  this.$axios({
                      url:"http://localhost:8090/musicWed/myMusicServlet",
                      method:"get",
                      params:{
                          id:row.id,
                          userName:sessionStorage.getItem('username'),
                          songName:row.songName,
                          singer:row.singer,
                          songUrl:row.songUrl
                      }
                  }).then(function(resp){
                      alert(resp.data)
                  })
              }
        }
       
    },
    
        
    }

</script>

<style>
  .el-main {
    position: absolute;
    top:50px;
    width: 100%;
    
    box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
    height: 400px;
    
    
  }
  i{
    font-size: 30px;
  }

    #audio{
 
         border-radius: 24px;
         width: 100%;
      
         
    }
</style>