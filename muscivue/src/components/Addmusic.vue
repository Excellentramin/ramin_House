<template>
    <div>
<el-dialog :visible.sync="$store.state.dialog" title="歌曲上传" width="400px">
    <el-form :model="form" label-position="left" ref="ruleForm">
            <el-form-item label="上传歌名" label-width="80px">
            <el-input v-model="form.name" autocomplete="off" :style="a" prop="name"></el-input>
            </el-form-item>
            <el-form-item label="歌手" label-width="80px">
                <el-input v-model="form.singer" autocomplete="off" :style="a" prop="delivery"></el-input>
            </el-form-item>
              <el-form-item label="歌曲类型" prop="delivery">
                      <el-select v-model="form.type" placeholder="请选择">
                          <el-option v-for="item in $store.state.type" :key="item" :label="item" :value="item">

                          </el-option>
                      </el-select>
              </el-form-item>
            <!-- 歌曲上传 -->
            <div class="uploadfile">
                <el-upload class="upload-demo" drag ref="upload" action="" :on-change="change" :auto-upload="false" :limit="1" :http-request="upload">
                 <i class="el-icon-upload"></i>
                 <div class="el-upload_text">
                    将文件拖到此处或点击上传
                 </div>
                </el-upload>
                <el-button type="primary" plain @click="submitUpload" >上传</el-button>
            </div>
      </el-form>
</el-dialog>
    </div>
</template>
<script>
export default {
    data(){
        return{
            form: {
          name: '',
          singer:'',
          type:''
        },
          a:'width:250px',
        }
    },
    methods: {
        change(filetemp){
            // 上传文件的时候进行判断
            var Name=filetemp.name;
            var idx=Name.lastIndexOf(".");
            if(idx!=-1){
                var ext=Name.substr(idx+1);
                if(ext!='mp3'){
                    alert("仅允许传MP3")
                    return
                }
                this.form.name=Name.substr(0,idx)
            }else{
                alert("文件有误")
            }
            var t=Name.indexOf('-')
            this.form.singer=Name.substr(0,t)
        },
        // 上传按钮点击事件
        submitUpload(){
            this.$refs.upload.submit();
        },
        // 上传歌曲
        upload(filetemp){
            if(this.form.name==""){
             alert("请输入歌名")
            }
            if(this.form.singer==""){
                alert("请输入歌手")
            }
            if(filetemp.file.size==0){
                alert("请上传文件")
                return
            }
            if(this.form.type==""){
                alert("请选择歌曲类型！")
            }
            
            let parms=new FormData()
            parms.append('file',filetemp.file)
            parms.append('name',this.form.name)
            parms.append('singer',this.form.singer)
            parms.append('type',this.form.type)
            var _this=this
            this.$axios.post(
                'http://localhost:8090/musicWed/uploadServlet',
                parms,
                {
                    headers:{
                        'Content-Type':'multipart/form-data'
                    }
                }
            ).then(function(resp){
                alert(resp.data)
                _this.$store.state.dialog=false
            }).catch(function(err){

                alert(err.message)
            })
        }

    },
}
</script>
<style scoped>

</style>