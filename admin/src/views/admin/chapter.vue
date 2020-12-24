<template>
    <div>
        <h3 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/course" class="pink"> {{course.name}} </router-link>
        </h3>
        <hr>

        <p>
            <button v-on:click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit"></i>
                新增
            </button>
            &nbsp;
            <button v-on:click="list(1)" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-refresh"></i>
                刷新
            </button>
            &nbsp;
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left"></i>
                回到课程
            </router-link>
        </p>

        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>名称</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td>{{chapter.id}}</td>
                <td>{{chapter.name}}</td>
                <td>
                    <div class="hidden-sm hidden-xs btn-group">
                        <button class="btn btn-xs btn-round btn-info" @click="edit(chapter)">
<!--                            <i class="ace-icon fa fa-pencil bigger-120"></i>-->
                            编辑
                        </button>
                        &nbsp;
                        <button class="btn btn-xs btn-round btn-danger" @click="del(chapter.id)">
<!--                            <i class="ace-icon fa fa-trash-o bigger-120"></i>-->
                            删除
                        </button>
                        &nbsp;
                        <button class="btn btn-xs btn-round btn-info" @click="toSection(chapter)">
                            小节
                        </button>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>

        <pagination ref="pagination" v-bind:list="list" v-bind:itemCount="5"></pagination>

        <!-- Modal -->
        <div class="modal fade" id="form-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">大章</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="name" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="name" placeholder="请输入大章名称..." v-model="chapter.name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">课程</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">{{ course.name }}</p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" class="btn btn-primary" @click="save">保存</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Pagination from "../../components/pagination";
    export default {
        name: "Chapter",
        components: {Pagination},
        data: function(){
            return {
                chapters: [],
                chapter: {},
                course: {}
            }
        },
        mounted: function() {
            // sidebar激活样式方法一
            // this.$parent.activeSidebar("business-chapter-sidebar");

            let _this = this;
            _this.$refs.pagination.size = 5;    // 初始化每页记录数

            /*
            * 加上 || {} 的目的是为了防止course对象不存在
            * 因为有可能是直接访问大章页面而不是从课程页面跳转过来的，这样的话，缓存中是没有course对象的
            * */
            let course = SessionStorage.get("course") || {};
            if(Tool.isEmpty(course)){
                _this.$router.push("/welcome");
            }
            _this.course = course;

            _this.list(1);                      // 初始化页码
        },
        methods: {
            /**
             * 列表查询
             */
            list(page) {
                let _this = this;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/list',{
                    page: page,
                    size: _this.$refs.pagination.size,
                    courseId: _this.course.id
                }).then((response)=>{
                    Loading.hide();
                    _this.chapters = response.data.content.list;
                    _this.$refs.pagination.render(page, response.data.content.total);
                })
            },

            /**
             * 点击【新增】
             */
            add(){
                let _this = this;
                _this.chapter = {};
                    $('#form-modal').modal('show');
            },

            /**
             * 点击【编辑】
             */
            edit(chapter){
                let _this = this;
                /*将列表中对应的chapter拷贝一份，并赋值给_this.chapter，
                这样的话，模态框中显示的数据和列表中的数据是来自两个不同的对象，因此改变模态框中的值不会改变列表中记录的值*/
                _this.chapter = $.extend({}, chapter);
                $('#form-modal').modal('show');
            },

            /**
             * 点击【保存】
             */
            save(){
                let _this = this;

                // 保存校验
                if (!Validator.require(_this.chapter.name, "名称")
                    || !Validator.length(_this.chapter.courseId, "课程ID", 1, 8)) {
                    return;
                }
                _this.chapter.courseId = _this.course.id;

                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER + '/business/admin/chapter/save', _this.chapter).then((response)=>{
                    Loading.hide();
                    /*保存成功，则隐藏模态框，刷新数据*/
                    if (response.data.success){
                        $('#form-modal').modal('hide');
                        _this.list(1);
                        toast.success("保存成功！");
                    }else {
                        toast.warning(response.data.message)
                    }
                })
            },

            /**
             * 点击【删除】
             */
            del(id){
                let _this = this;
                Confirm.show("删除大章后不可恢复，确认删除？", function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER + '/business/admin/chapter/delete/' + id).then((response)=>{
                        Loading.hide();
                        let resp = response.data;
                        if (resp.success) {
                            _this.list(1);
                            toast.success("删除成功！");
                        }
                    })
                });
            },

            /**
             * 点击【小节】
             */
            toSection(chapter){
                let _this = this;
                SessionStorage.set("chapter", chapter);
                _this.$router.push("/business/section");
            }
        }
    }
</script>

<style scoped>

</style>