<template>
  <div class="search">
      <div class="table">
            <el-table
        :data="$store.state.tableData"
        height="400"
        stripe
        style="width: 100%"
        :header-cell-style="{'text-align':'center'}"
        :cell-style="{'text-align':'center'}"
        >
       

        <el-table-column
          prop="songName"
          label="歌名"
          width="300px">
        </el-table-column>
        
        <el-table-column
          width="">
          <template slot-scope="scope">
            <i class="el-icon-video-play" @click="playMusic(scope.row)"></i>
          <i class="el-icon-video-pause" @click="puase()"></i>
          </template>
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
    }

}

</script>

<style scoped>
.search{
    background: white;
   box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
   height: 700px;
   width: 720px;
   position: relative;
   top:0px;
   left:400px;
}
.table{
        height: 200px;
        width: 689px;
        position: absolute;
        top:10px;
        left: 12px;
    }
    i{
        font-size: 25px;
        cursor: pointer;
    }
     .play{
         height: 300px;
         width: 689px;
         position: absolute;
         top:500px;
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