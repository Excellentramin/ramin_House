import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    dialogFormVisible:false, //判断登录框的显示和隐藏
   // dialogFormVisible1:false,
    dialog:false,             //判断上传歌曲弹出框显示和隐藏
    noshow:false,
    type:['华语','流行','民谣','电子','摇滚'],  //歌曲类型
    song:{
      url:''},
      tableData:[],
      username:'',  //用于保存登录后的账
      currentSrc:'http://localhost:8090/musicWed/',
  },
  mutations: {
    editDialog :function(state,a){
      state.dialogFormVisible=a
 },
 baocun:function(state,a){
   state.username=a
     sessionStorage.setItem('username',state.username)
 },
 musicplay:function(state,a){
   state.song.url=state.currentSrc+a.songUrl
 },
 
 fresh:function(state,a){
   state.tableData=a
 },
 editNoshow:function(state,a){
   state.noshow=a
 }
 
  },
  actions: {
  },
  modules: {
  }
})
